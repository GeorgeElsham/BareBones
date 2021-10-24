package Interpreter;

public abstract class InterpreterException {
  private static abstract class Reason extends Exception {}

  public static class InvalidIdentifier extends Reason {}
  public static class InvalidInteger extends Reason {}
  public static class UnknownKeyword extends Reason {}
  public static class UnknownOperator extends Reason {}

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
}
