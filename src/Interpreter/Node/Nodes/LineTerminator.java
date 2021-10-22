package Interpreter.Node.Nodes;

import Interpreter.Node.*;

public class LineTerminator implements Node {
  /**
   * Gets the kind of node this is.
   *
   * @return Kind of node.
   */
  @Override
  public NodeKind getKind() {
    return NodeKind.LINE_TERMINATOR;
  }
}
