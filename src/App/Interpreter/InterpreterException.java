package App.Interpreter;

import App.Interpreter.Node.Node;
import java.util.Arrays;

public abstract class InterpreterException {
  private static abstract class Reason extends Exception {}

  public static class InvalidBooleanExpression extends Reason {
    private final Node[] nodes;

    public InvalidBooleanExpression(Node[] nodes) {
      this.nodes = nodes;
    }

    @Override
    public String toString() {
      return "InvalidBooleanExpression{" +
          "nodes=" + Arrays.toString(nodes) +
          '}';
    }
  }

//  public static class InvalidInteger extends Reason {
//    public final String name;
//    public final int value;
//
//    public InvalidInteger(String name, int value) {
//      this.name = name;
//      this.value = value;
//    }
//
//    @Override
//    public String toString() {
//      return "InvalidInteger{" +
//          "name='" + name + '\'' +
//          ", value=" + value +
//          '}';
//    }
//  }

  public static class InvalidSyntax extends Reason {
    public final int tokenIndex;
    public final String message;

    public InvalidSyntax(int tokenIndex, String message) {
      this.tokenIndex = tokenIndex;
      this.message = message;
    }

    public InvalidSyntax offsetTokenIndex(int distance) {
      return new InvalidSyntax(tokenIndex + distance, message);
    }

    @Override
    public String toString() {
      return "InvalidSyntax{" +
          "tokenIndex=" + tokenIndex +
          ", message='" + message + '\'' +
          '}';
    }
  }

  public static class RuntimeError extends Reason {
    public final String message;

    public RuntimeError(String message) {
      this.message = message;
    }
  }

  public static class UnexpectedToken extends Reason {
    public final String token;

    public UnexpectedToken(String token) {
      this.token = token;
    }

    @Override
    public String toString() {
      return "UnexpectedToken{" +
          "token='" + token + '\'' +
          '}';
    }
  }

  public static class UnknownKeyword extends Reason {
    private final String name;

    public UnknownKeyword(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return "UnknownKeyword{" +
          "name='" + name + '\'' +
          '}';
    }
  }

  public static class UnknownOperator extends Reason {
    private final String name;

    public UnknownOperator(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return "UnknownOperator{" +
          "name='" + name + '\'' +
          '}';
    }
  }
}
