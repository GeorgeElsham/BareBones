package Interpreter;

public abstract class InterpreterException {
  private static abstract class Reason extends Exception {}

  public static class InvalidInteger extends Reason {}
}
