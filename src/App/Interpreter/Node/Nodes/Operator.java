package App.Interpreter.Node.Nodes;

import App.Interpreter.InterpreterException.UnknownOperator;
import App.Interpreter.Node.*;
import java.util.HashMap;

public class Operator implements Node {
  private static final HashMap<String, NodeKind> all;
  private final String name;

  static {
    HashMap<String, NodeKind> map = new HashMap<>();
    map.put("not", NodeKind.NOT_OPERATOR);
    all = map;
  }

  public Operator(String name) throws UnknownOperator {
    if (all.get(name) == null) {
      throw new UnknownOperator();
    }
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
    return all.get(getName());
  }
}
