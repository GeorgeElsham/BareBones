package Interpreter;

public abstract class InterpreterException {
  private static abstract class Reason extends Exception {}

  public static class InvalidIdentifier extends Reason {}
  public static class InvalidInteger extends Reason {}
  public static class UnknownKeyword extends Reason {}
  public static class UnknownOperator extends Reason {}
}
