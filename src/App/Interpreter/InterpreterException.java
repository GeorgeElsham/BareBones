package App.Interpreter;

public abstract class InterpreterException {
  private static abstract class Reason extends Exception {}

  public static class InvalidInteger extends Reason {
    private final int value;

    public InvalidInteger(int value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return "InvalidInteger{" +
          "value=" + value +
          '}';
    }
  }

  public static class InvalidSyntax extends Reason {
    private final int tokenIndex;
    private final String message;

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
