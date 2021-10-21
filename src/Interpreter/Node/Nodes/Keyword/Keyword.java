package Interpreter.Node.Nodes.Keyword;

import Interpreter.Node.Nodes.Nodes;

public class Keyword {
  private final String name;
  private final Nodes[] structure;

  public Keyword(String name, Nodes[] structure) {
    this.name = name;
    this.structure = structure;
  }

  public String getName() {
    return name;
  }

  public Nodes[] getStructure() {
    return structure;
  }
}
