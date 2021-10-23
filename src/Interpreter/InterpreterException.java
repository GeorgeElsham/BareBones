package Interpreter;

import Interpreter.Node.NodeKind;

public abstract class InterpreterException {
  private static abstract class Reason extends Exception {}

  public static class InvalidIdentifier extends Reason {}
  public static class InvalidInteger extends Reason {}
  public static class UnknownKeyword extends Reason {}
  public static class UnknownOperator extends Reason {}

  public static class InvalidSyntax extends Reason {
    private final int tokenIndex;
    private final NodeKind tokenKind;
    private final NodeKind expectedTokenKind;

    public InvalidSyntax(int tokenIndex, NodeKind tokenKind, NodeKind expectedTokenKind) {
      this.tokenIndex = tokenIndex;
      this.tokenKind = tokenKind;
      this.expectedTokenKind = expectedTokenKind;
    }

    @Override
    public String toString() {
      return "InvalidSyntax{" +
          "tokenIndex=" + tokenIndex +
          ", tokenKind=" + tokenKind +
          ", expectedTokenKind=" + expectedTokenKind +
          '}';
    }
  }
}
