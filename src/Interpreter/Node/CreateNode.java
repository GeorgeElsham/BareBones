package Interpreter.Node;

import Interpreter.InterpreterException.*;
import Interpreter.Node.Nodes.*;
import java.util.Objects;

public class CreateNode {
  public static Node from(String string) {
    final Node node = readNode(
        string,
        CreateNode::createLineTerminator,
        CreateNode::createIntegerLiteral,
        CreateNode::createKeyword,
        CreateNode::createOperator,
        CreateNode::createIntegerIdentifier
    );

    if (node != null) {
      return node;
    } else {
      System.out.println("Unknown: " + string);
      return null;
    }
  }

  private static Node readNode(String string, StringToNode... expressions) {
    for (StringToNode expression : expressions) {
      final Node node = expression.convert(string);
      if (node != null) {
        return node;
      }
    }
    return null;
  }

  private static IntegerIdentifier createIntegerIdentifier(String string) {
    try {
      if (!string.matches("^[A-Za-z][A-Za-z0-9]*$")) {
        throw new InvalidIdentifier();
      }
      return new IntegerIdentifier(string);
    } catch (InvalidIdentifier invalidIdentifier) {
      return null;
    }
  }

  private static IntegerLiteral createIntegerLiteral(String string) {
    try {
      if (!string.matches("\\d+")) {
        throw new InvalidInteger();
      }
      final long integer = Long.parseLong(string);
      return new IntegerLiteral(integer);
    } catch (InvalidInteger invalidInteger) {
      return null;
    }
  }

  private static Keyword createKeyword(String string) {
    try {
      return new Keyword(string);
    } catch (UnknownKeyword unknownKeyword) {
      return null;
    }
  }

  private static LineTerminator createLineTerminator(String string) {
    if (Objects.equals(string, ";")) {
      return new LineTerminator();
    } else {
      return null;
    }
  }

  private static Operator createOperator(String string) {
    try {
      return new Operator(string);
    } catch (UnknownOperator unknownOperator) {
      return null;
    }
  }
}

interface StringToNode {
  Node convert(String string);
}
