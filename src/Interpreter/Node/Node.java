package Interpreter.Node;

import Interpreter.Node.Nodes.Nodes;

public interface Node {
  /**
   * Gets the kind of node this is.
   *
   * @return Kind of node.
   */
  Nodes getKind();
}
