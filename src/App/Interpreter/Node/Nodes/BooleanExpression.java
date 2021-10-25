package App.Interpreter.Node.Nodes;

import App.Interpreter.Node.*;

public class BooleanExpression implements Node {
  private final Node[] nodes;

  public BooleanExpression(Node[] nodes) {
    this.nodes = nodes;
  }

  public boolean evaluate() {
    // Temporary
    return true;
  }

  /**
   * Gets the kind of node this is.
   *
   * @return Kind of node.
   */
  @Override
  public NodeKind getKind() {
    return NodeKind.BOOLEAN_EXPRESSION;
  }
}
