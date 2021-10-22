package Interpreter.Node.Nodes;

import Interpreter.InterpreterException.UnknownOperator;
import Interpreter.Node.Node;
import java.util.HashMap;

public class Operator implements Node {
  private static final HashMap<String, Nodes> all;
  private final String name;

  static {
    HashMap<String, Nodes> map = new HashMap<>();
    map.put("not", Nodes.NOT_OPERATOR);
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
  public Nodes getKind() {
    return all.get(getName());
  }
}
