package Interpreter.Node.Nodes;

import Interpreter.Node.Node;

public class IntegerIdentifier implements Node {
  private final String name;

  public IntegerIdentifier(String name) {
    this.name = name;
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
  public Nodes getKind() {
    return Nodes.INTEGER_IDENTIFIER;
  }
}
