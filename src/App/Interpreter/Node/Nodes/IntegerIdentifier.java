package App.Interpreter.Node.Nodes;

import App.Interpreter.Node.*;

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
  public NodeKind getKind() {
    return NodeKind.INTEGER_IDENTIFIER;
  }
}
