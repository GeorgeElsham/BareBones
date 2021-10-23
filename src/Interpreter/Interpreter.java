package Interpreter;

import Interpreter.InterpreterException.*;
import Interpreter.Node.*;
import Interpreter.Node.Nodes.*;
import java.util.*;
import java.util.stream.IntStream;

public class Interpreter {
  public void execute(String code) {
    final String cleaned = cleanUpCode(code);
    System.out.println(cleaned);

    final List<Node> tokens = tokenizer(cleaned);
    System.out.println(Arrays.toString(tokens.stream().map(x -> x == null ? "null" : x.getKind()).toArray()));

    passesSyntacticAnalysis(tokens);
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
    final List<Node> tokens = partsList.stream().map(this::createNode).toList();

    System.out.println(Arrays.toString(parts));
    return tokens;
  }

  private Node createNode(String string) {
    if (Objects.equals(string, ";")) {
      return new LineTerminator();
    }

    try {
      if (!string.matches("\\d+")) {
        throw new InvalidInteger();
      }
      final long integer = Long.parseLong(string);
      return new IntegerLiteral(integer);
    } catch (InvalidInteger invalidInteger) {
      try {
        return new Keyword(string);
      } catch (UnknownKeyword unknownKeyword) {
        try {
          return new Operator(string);
        } catch (UnknownOperator unknownOperator) {
          try {
            if (!string.matches("^[A-Za-z][A-Za-z0-9]*$")) {
              throw new InvalidIdentifier();
            }
            return new IntegerIdentifier(string);
          } catch (InvalidIdentifier invalidIdentifier) {
            System.out.println("Unknown: " + string);
            return null;
          }
        }
      }
    }
  }

  private boolean passesSyntacticAnalysis(List<Node> tokens) {
    try {
      final int endIndex = validateSyntax(tokens);
      System.out.println("final index: " + endIndex);
    } catch (InvalidSyntax invalidSyntax) {
      invalidSyntax.printStackTrace();
    }

    return false;
  }

  private int validateSyntax(List<Node> tokens) throws InvalidSyntax {
    int startOffset = 0;

    while (startOffset < tokens.size()) {
      final int finalStartOffset = startOffset;
      final int syntaxIndex =
          IntStream.range(0, Syntax.all.length)
              .filter(x -> Syntax.all[x][0] == tokens.get(finalStartOffset).getKind())
              .findFirst()
              .getAsInt();

      for (int i = 0; i < Syntax.all[syntaxIndex].length; i++) {
        final Node token = tokens.get(i + startOffset);
        final NodeKind tokenKind = token.getKind();
        final NodeKind expectedTokenKind = Syntax.all[syntaxIndex][i];

        if (tokenKind == NodeKind.END_KEYWORD) {
          return i;
        } else if (expectedTokenKind == NodeKind.BLOCK) {
          final int endIndex = validateSyntax(tokens.subList(i + startOffset, tokens.size())) + i + startOffset;
          i = endIndex;
          continue;
        } else if (tokenKind != expectedTokenKind) {
          throw new InvalidSyntax(startOffset + i, tokenKind, expectedTokenKind);
        }
      }

      startOffset += Syntax.all[syntaxIndex].length;
    }

    return tokens.size();
  }
}
