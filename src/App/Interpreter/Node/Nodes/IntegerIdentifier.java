package App.Interpreter.Node.Nodes;

import App.Interpreter.Execution;
import App.Interpreter.InterpreterException.InvalidInteger;
import App.Interpreter.Node.*;
import App.Interpreter.Node.Protocols.*;

public class IntegerIdentifier implements Node, Equatable<Integer>, IntegerValue {
  private final String name;
  private final Execution execution;

  public IntegerIdentifier(String name, Execution execution) {
    this.name = name;
    this.execution = execution;
  }

  public void clear() {
    execution.clearVariable(name);
  }

  public void decrement() throws InvalidInteger {
    execution.decrementVariable(name);
  }

  public void increment() {
    execution.incrementVariable(name);
  }

  public void set(int value) throws InvalidInteger {
    execution.setVariable(name, value);
  }

  /**
   * Gets the kind of node this is.
   *
   * @return Kind of node.
   */
  @Override
  public NodeKind getKind() {
    return NodeKind.INTEGER_IDENTIFIER;
  }

  /**
   * Gets value given by implementation to be used to check for equality.
   *
   * @return Value to check for equality.
   */
  @Override
  public Integer getEquatableValue() {
    return execution.getVariable(name);
  }

  /**
   * Get integer value.
   *
   * @return Integer value.
   */
  @Override
  public int getValue() {
    return execution.getVariable(name);
  }
}
