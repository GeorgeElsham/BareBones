package App.Interpreter.Node;

import App.Interpreter.InterpreterException.*;
import App.Interpreter.Node.Nodes.*;
import java.util.Objects;

public class CreateNode {
  private final Execution execution;

  public CreateNode(Execution execution) {
    this.execution = execution;
  }

  public Node from(String string) {
    final Node node = readNode(
        string,
        this::createLineTerminator,
        this::createIntegerLiteral,
        this::createKeyword,
        this::createOperator,
        this::createIntegerIdentifier
    );

    if (node != null) {
      return node;
    } else {
      System.out.println("Unknown: " + string);
      return null;
    }
  }

  private Node readNode(String string, StringToNode... expressions) {
    for (StringToNode expression : expressions) {
      final Node node = expression.convert(string);
      if (node != null) {
        return node;
      }
    }
    return null;
  }

  private IntegerIdentifier createIntegerIdentifier(String string) {
    if (string.matches("^[A-Za-z][A-Za-z0-9]*$")) {
      return new IntegerIdentifier(string, execution);
    } else {
      return null;
    }
  }

  private IntegerLiteral createIntegerLiteral(String string) {
    if (string.matches("\\d+")) {
      final int integer = Integer.parseInt(string);
      return new IntegerLiteral(integer);
    } else {
      return null;
    }
  }

  private Keyword createKeyword(String string) {
    try {
      return new Keyword(string);
    } catch (UnknownKeyword unknownKeyword) {
      return null;
    }
  }

  private LineTerminator createLineTerminator(String string) {
    if (Objects.equals(string, ";")) {
      return new LineTerminator();
    } else {
      return null;
    }
  }

  private Operator createOperator(String string) {
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
