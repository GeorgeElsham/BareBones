package Interpreter.Node.Nodes;

import Interpreter.Node.Node;

public class LineTerminator implements Node {
  /**
   * Gets the kind of node this is.
   *
   * @return Kind of node.
   */
  @Override
  public Nodes getKind() {
    return Nodes.LINE_TERMINATOR;
  }
}
