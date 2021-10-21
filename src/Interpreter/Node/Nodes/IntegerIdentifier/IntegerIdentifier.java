package Interpreter.Node.Nodes.IntegerIdentifier;

import Interpreter.InterpreterException.InvalidInteger;

public class IntegerIdentifier {
  private long value;

  public IntegerIdentifier(long value) {
    this.value = value;
  }

  public long getValue() {
    return value;
  }

  public void setValue(long newValue) throws InvalidInteger {
    if (newValue < 0) {
      throw new InvalidInteger();
    }
    value = newValue;
  }
}
