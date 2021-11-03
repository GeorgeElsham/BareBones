package App.Interpreter.Node.Nodes;

import App.Interpreter.Execution;
import App.Interpreter.InterpreterException.RuntimeError;
import App.Interpreter.Node.*;
import App.Interpreter.Node.Protocols.*;

/**
 * Represents an integer identifier.
 */
public class IntegerIdentifier implements Node, Equatable<Integer>, IntegerValue {
  private final String name;
  private final Execution execution;

  public IntegerIdentifier(String name, Execution execution) {
    this.name = name;
    this.execution = execution;
  }

  /**
   * Gets name of identifier.
   *
   * @return Name string.
   */
  public String getName() {
    return name;
  }

  /**
   * Clear variable value.
   */
  public void clear() {
    execution.clearVariable(name);
  }

  /**
   * Decrement variable value by 1.
   *
   * @throws RuntimeError Invalid integer error if it goes negative.
   */
  public void decrement() throws RuntimeError {
    execution.decrementVariable(name);
  }

  /**
   * Increment variable value by 1.
   */
  public void increment() {
    execution.incrementVariable(name);
  }

  /**
   * Set exact variable value.
   *
   * @param value New value.
   * @throws RuntimeError Invalid integer error if negative.
   */
  public void set(int value) throws RuntimeError {
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
