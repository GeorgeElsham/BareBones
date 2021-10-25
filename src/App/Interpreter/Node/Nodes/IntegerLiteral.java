package App.Interpreter.Node.Nodes;

import App.Interpreter.Node.*;
import App.Interpreter.Node.Protocols.Equatable;

public class IntegerLiteral implements Node, Equatable<Integer> {
  private final int value;

  public IntegerLiteral(int value) {
    this.value = value;
  }

  /**
   * Gets the kind of node this is.
   *
   * @return Kind of node.
   */
  @Override
  public NodeKind getKind() {
    return NodeKind.INTEGER_LITERAL;
  }

  /**
   * Gets value given by implementation to be used to check for equality.
   *
   * @return Value to check for equality.
   */
  @Override
  public Integer getValue() {
    return value;
  }
}
