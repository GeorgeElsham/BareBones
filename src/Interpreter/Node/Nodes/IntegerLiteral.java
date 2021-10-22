package Interpreter.Node.Nodes;

import Interpreter.Node.Node;

public class IntegerLiteral implements Node {
  private final long value;

  public IntegerLiteral(long value) {
    this.value = value;
  }

  public long getValue() {
    return value;
  }

  /**
   * Gets the kind of node this is.
   *
   * @return Kind of node.
   */
  @Override
  public Nodes getKind() {
    return Nodes.INTEGER_LITERAL;
  }
}
