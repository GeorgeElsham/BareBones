package App.Interpreter.Node.Nodes;

import App.Interpreter.InterpreterException.UnknownKeyword;
import App.Interpreter.Node.*;
import java.util.HashMap;

public class Keyword implements Node {
  private static final HashMap<String, NodeKind> all;
  private final String name;

  static {
    HashMap<String, NodeKind> map = new HashMap<>();
    map.put("clear", NodeKind.CLEAR_KEYWORD);
    map.put("copy", NodeKind.COPY_KEYWORD);
    map.put("decr", NodeKind.DECR_KEYWORD);
    map.put("do", NodeKind.DO_KEYWORD);
    map.put("end", NodeKind.END_KEYWORD);
    map.put("incr", NodeKind.INCR_KEYWORD);
    map.put("to", NodeKind.TO_KEYWORD);
    map.put("while", NodeKind.WHILE_KEYWORD);
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
  public NodeKind getKind() {
    return all.get(getName());
  }
}
