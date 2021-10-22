package Interpreter.Node.Nodes;

import Interpreter.Node.Node;

public class BooleanExpression implements Node {

  /**
   * Gets the kind of node this is.
   *
   * @return Kind of node.
   */
  @Override
  public Nodes getKind() {
    return Nodes.BOOLEAN_EXPRESSION;
  }
}
