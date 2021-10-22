package Interpreter;

import Interpreter.InterpreterException.*;
import Interpreter.Node.Node;
import Interpreter.Node.Nodes.*;
import java.util.*;

public class Interpreter {
  public void execute(String code) {
    final String cleaned = cleanUpCode(code);
    System.out.println(cleaned);
    final List<Node> tokens = tokenizer(cleaned);
    System.out.println(Arrays.toString(tokens.stream().map(x -> x == null ? "null" : x.getKind()).toArray()));
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
}
