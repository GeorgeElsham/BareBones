package App.Interpreter.Node.Nodes;

import App.Interpreter.Node.Node;
import App.Interpreter.Node.NodeKind;

public class Bracket implements Node {
  public enum Kind {
    OPEN, CLOSE
  }

  public final Kind kind;

  public Bracket(Kind kind) {
    this.kind = kind;
  }

  /**
   * Gets the kind of node this is.
   *
   * @return Kind of node.
   */
  @Override
  public NodeKind getKind() {
    return switch (kind) {
      case OPEN -> NodeKind.OPEN_BRACKET;
      case CLOSE -> NodeKind.CLOSE_BRACKET;
    };
  }
}
