package App.Interpreter.Node;

import App.Interpreter.InterpreterException.*;
import App.Interpreter.Node.Nodes.*;
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
    if (string.matches("^[A-Za-z][A-Za-z0-9]*$")) {
      return new IntegerIdentifier(string);
    } else {
      return null;
    }
  }

  private static IntegerLiteral createIntegerLiteral(String string) {
    if (string.matches("\\d+")) {
      final int integer = Integer.parseInt(string);
      return new IntegerLiteral(integer);
    } else {
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
