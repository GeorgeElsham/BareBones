package App.Interpreter;

import App.ExitCode;
import App.Interpreter.InterpreterException.*;
import App.Interpreter.Node.*;
import App.Interpreter.Node.Nodes.BooleanExpression;
import App.Interpreter.Syntax.Syntax;
import java.util.*;
import java.util.stream.IntStream;

public class Interpreter {
  private final Execution execution = new Execution();

  public void execute(String code) {
    final String cleaned = cleanUpCode(code);
    final List<Node> tokens = tokenizer(cleaned);

    try {
      final SyntacticAnalysisResult result = syntacticAnalysis(tokens);
      result.execute();
      execution.printExecutionTable();
    } catch (InvalidSyntax invalidSyntax) {
      final String msg = "Invalid syntax for token index '%d': %s";
      final String formattedMsg = String.format(msg, invalidSyntax.tokenIndex, invalidSyntax.message);
      System.err.println(formattedMsg);
      System.exit(ExitCode.PROGRAM_ERROR);
    } catch (InvalidInteger invalidInteger) {
      final String msg = "Invalid integer '%d' in '%s'";
      final String formattedMsg = String.format(msg, invalidInteger.value, invalidInteger.name);
      System.err.println(formattedMsg);
      System.exit(ExitCode.PROGRAM_ERROR);
    }
  }

  private String cleanUpCode(String code) {
    final String removedComments = code.replaceAll("^ *#.*", "");
    final String removedNewLines = removedComments.replaceAll(" *\\n *", "");
    final String removedSemicolonSpaces = removedNewLines.replaceAll(" *; *", " ; ");
    return removedSemicolonSpaces.replaceAll(" {2,}", " ");
  }

  private List<Node> tokenizer(String code) {
    final String[] parts = code.split(" ");
    final List<String> partsList = new ArrayList<>(Arrays.asList(parts));
    return partsList.stream().map(this::createNode).toList();
  }

  private Node createNode(String part) {
    try {
      final CreateNode createNode = new CreateNode(execution);
      return createNode.from(part);
    } catch (UnexpectedToken unexpectedToken) {
      final String msg = "Unexpected token: '%s'";
      final String formattedMsg = String.format(msg, unexpectedToken.token);
      System.err.println(formattedMsg);
      System.exit(ExitCode.PROGRAM_ERROR);
      return null;
    }
  }

  private SyntacticAnalysisResult syntacticAnalysis(List<Node> tokens) throws InvalidSyntax {
    int startOffset = 0;
    List<ParseTree> parseTrees = new ArrayList<>();

    while (startOffset < tokens.size()) {
      final int finalStartOffset = startOffset;
      final NodeKind search = tokens.get(finalStartOffset).getKind();

      final int syntaxIndex =
          IntStream.range(0, Syntax.all.length)
              .filter(x -> Syntax.all[x].getNodeKind(0) == search)
              .findFirst()
              .orElseThrow(() -> {
                final String msg = "Failed to find syntax starting with '%s'";
                final String formattedMsg = String.format(msg, search.readableName());
                return new InvalidSyntax(finalStartOffset, formattedMsg);
              });

      final int originalLoopEndOffset = startOffset + Syntax.all[syntaxIndex].numberOfParts();
      int sizeOffset = 0;
      List<Node> currentNodes = new ArrayList<>();
      List<ParseTree> currentParseTrees = new ArrayList<>();

      iLoop: for (int i = 0; i < Syntax.all[syntaxIndex].numberOfParts(); i++) {
        final int tokenIndex = i + startOffset + sizeOffset;
        if (tokenIndex >= tokens.size()) {
          throw new InvalidSyntax(startOffset, "Missing end of block");
        }

        final Node token = tokens.get(tokenIndex);
        final NodeKind tokenKind = token.getKind();
        final NodeKind expectedTokenKind = Syntax.all[syntaxIndex].getNodeKind(i);

        if (expectedTokenKind == NodeKind.BLOCK) {
          try {
            final SyntacticAnalysisResult result = syntacticAnalysis(tokens.subList(tokenIndex, tokens.size()));
            currentParseTrees.add(new ParseTree(syntaxIndex, currentNodes.toArray(Node[]::new), result.getParseTrees()));
            sizeOffset += result.getBlockLength() - 1;
          } catch (InvalidSyntax invalidSyntax) {
            throw invalidSyntax.offsetTokenIndex(tokenIndex);
          }
        } else if (expectedTokenKind.isExpression()) {
          final NodeKind nextExpectedTokenKind = Syntax.all[syntaxIndex].getNodeKind(i + 1);

          for (int j = 1; tokenIndex + j < tokens.size(); j++) {
            if (tokens.get(tokenIndex + j).getKind() == nextExpectedTokenKind) {
              final Node[] nodes = tokens.subList(tokenIndex, tokenIndex + j).toArray(Node[]::new);
              try {
                currentNodes.add(new BooleanExpression(nodes));
                sizeOffset += j - 1;
                continue iLoop;
              } catch (InvalidBooleanExpression invalidBooleanExpression) {
                throw new InvalidSyntax(tokenIndex, "Invalid boolean expression");
              }
            }
          }

          final String msg = "No '%s' expression end found";
          final String formattedMsg = String.format(msg, expectedTokenKind.readableName());
          throw new InvalidSyntax(tokenIndex, formattedMsg);
        } else if (tokenKind == expectedTokenKind) {
          currentNodes.add(token);
        } else {
          final String msg = "Found '%s', expected '%s'";
          final String formattedMsg = String.format(msg, tokenKind.readableName(), expectedTokenKind.readableName());
          throw new InvalidSyntax(tokenIndex, formattedMsg);
        }
      }

      startOffset = originalLoopEndOffset + sizeOffset;

      final Node[] newNodes = currentNodes.toArray(Node[]::new);
      final ParseTree[] newParseTrees = currentParseTrees.toArray(ParseTree[]::new);
      parseTrees.add(new ParseTree(syntaxIndex, newNodes, newParseTrees));

      if (startOffset < tokens.size() && tokens.get(startOffset).getKind() == NodeKind.END_KEYWORD) {
        return new SyntacticAnalysisResult(startOffset, parseTrees.toArray(ParseTree[]::new));
      }
    }

    return new SyntacticAnalysisResult(tokens.size(), parseTrees.toArray(ParseTree[]::new));
  }
}


record SyntacticAnalysisResult(int blockLength, ParseTree[] parseTrees) {
  public int getBlockLength() {
    return blockLength;
  }

  public ParseTree[] getParseTrees() {
    return parseTrees;
  }

  public void execute() throws InvalidInteger {
    for (ParseTree parseTree : parseTrees) {
      parseTree.run();
    }
  }
}
