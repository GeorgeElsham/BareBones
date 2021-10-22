package Interpreter.Node.Nodes;

import Interpreter.InterpreterException.UnknownKeyword;
import Interpreter.Node.Node;
import java.util.HashMap;

public class Keyword implements Node {
  private static final HashMap<String, Nodes> all;
  private final String name;

  static {
    HashMap<String, Nodes> map = new HashMap<>();
    map.put("clear", Nodes.CLEAR_KEYWORD);
    map.put("copy", Nodes.COPY_KEYWORD);
    map.put("decr", Nodes.DECR_KEYWORD);
    map.put("do", Nodes.DO_KEYWORD);
    map.put("end", Nodes.END_KEYWORD);
    map.put("incr", Nodes.INCR_KEYWORD);
    map.put("to", Nodes.TO_KEYWORD);
    map.put("while", Nodes.WHILE_KEYWORD);
    all = map;
  }

  public Keyword(String name) throws UnknownKeyword {
    if (all.get(name) == null) {
      throw new UnknownKeyword();
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
