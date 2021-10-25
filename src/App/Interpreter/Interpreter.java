package App.Interpreter;

import App.Interpreter.InterpreterException.*;
import App.Interpreter.Node.*;
import java.util.*;
import java.util.stream.IntStream;

public class Interpreter {
  private final Execution execution = new Execution();

  public void execute(String code) {
    final String cleaned = cleanUpCode(code);
    System.out.println(cleaned);

    final List<Node> tokens = tokenizer(cleaned);
    System.out.println(Arrays.toString(tokens.stream().map(x -> x == null ? "null" : x.getKind()).toArray()));

    try {
      final int endIndex = syntacticAnalysis(tokens);
      System.out.println("final index: " + endIndex);
    } catch (InvalidSyntax invalidSyntax) {
      invalidSyntax.printStackTrace();
    }
  }

  private String cleanUpCode(String code) {
    final String removedComments = code.replaceAll("^ *#.*", "");
    final String removedNewLines = removedComments.replaceAll(" *\\n *", "");
    final String removedSemicolonSpaces = removedNewLines.replaceAll(" *; *", " ; ");
    final String removedInlineSpaces = removedSemicolonSpaces.replaceAll(" {2,}", " ");
    return removedInlineSpaces;
  }

  private List<Node> tokenizer(String code) {
    final String[] parts = code.split(" ");
    final List<String> partsList = new ArrayList<>(Arrays.asList(parts));
    final CreateNode createNode = new CreateNode(execution);
    final List<Node> tokens = partsList.stream().map(createNode::from).toList();

    System.out.println(Arrays.toString(parts));
    return tokens;
  }

  private int syntacticAnalysis(List<Node> tokens) throws InvalidSyntax {
    int startOffset = 0;

    while (startOffset < tokens.size()) {
      final int finalStartOffset = startOffset;
      final NodeKind search = tokens.get(finalStartOffset).getKind();

      if (search == NodeKind.END_KEYWORD) {
        return startOffset;
      }

      final int syntaxIndex =
          IntStream.range(0, Syntax.all.length)
              .filter(x -> Syntax.all[x][0] == search)
              .findFirst()
              .orElseThrow(() -> {
                final String msg = "Failed to find syntax starting with '%s'.";
                final String formattedMsg = String.format(msg, search);
                return new InvalidSyntax(finalStartOffset, formattedMsg);
              });

      final int originalLoopEndOffset = startOffset + Syntax.all[syntaxIndex].length;
      int sizeOffset = 0;

      iLoop: for (int i = 0; i < Syntax.all[syntaxIndex].length; i++) {
        final int tokenIndex = i + startOffset + sizeOffset;
        if (tokenIndex >= tokens.size()) {
          throw new InvalidSyntax(startOffset, "Missing end of block.");
        }

        final Node token = tokens.get(tokenIndex);
        final NodeKind tokenKind = token.getKind();
        final NodeKind expectedTokenKind = Syntax.all[syntaxIndex][i];

        if (expectedTokenKind == NodeKind.BLOCK) {
          try {
            final int blockLength = syntacticAnalysis(tokens.subList(tokenIndex, tokens.size()));
            sizeOffset += blockLength - 1;
          } catch (InvalidSyntax invalidSyntax) {
            throw invalidSyntax.offsetTokenIndex(tokenIndex);
          }
        } else if (expectedTokenKind.isExpression()) {
          final NodeKind nextExpectedTokenKind = Syntax.all[syntaxIndex][i + 1];

          for (int j = 1; tokenIndex + j < tokens.size(); j++) {
            if (tokens.get(tokenIndex + j).getKind() == nextExpectedTokenKind) {
              sizeOffset += j - 1;
              continue iLoop;
            }
          }

          final String msg = "No '%s' expression end found.";
          final String formattedMsg = String.format(msg, expectedTokenKind);
          throw new InvalidSyntax(tokenIndex, formattedMsg);
        } else if (tokenKind != expectedTokenKind) {
          final String msg = "Found '%s', expected '%s'.";
          final String formattedMsg = String.format(msg, tokenKind, expectedTokenKind);
          throw new InvalidSyntax(tokenIndex, formattedMsg);
        }
      }

      startOffset = originalLoopEndOffset + sizeOffset;
    }

    return tokens.size();
  }
}
