package App.Interpreter.Node.Nodes;

import App.Interpreter.Node.*;
import App.Interpreter.Node.Protocols.Equatable;

public class IntegerIdentifier implements Node, Equatable<Integer> {
  private final String name;
  private final Execution execution;

  public IntegerIdentifier(String name, Execution execution) {
    this.name = name;
    this.execution = execution;
  }

  public String getName() {
    return name;
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
  public Integer getValue() {
    return execution.getVariable(name);
  }
}
