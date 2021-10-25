package App.Interpreter.Node.Nodes;

import App.Interpreter.Node.*;

public class IntegerLiteral implements Node {
  private final int value;

  public IntegerLiteral(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
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
}
