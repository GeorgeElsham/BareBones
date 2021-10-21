package Interpreter.Node.Nodes.Keyword;

import Interpreter.Node.Nodes.Nodes;

public enum Keywords {
  CLEAR(Nodes.INTEGER_IDENTIFIER),
  COPY(Nodes.INTEGER_IDENTIFIER, Nodes.TO_KEYWORD, Nodes.INTEGER_IDENTIFIER),
  DECR(Nodes.INTEGER_IDENTIFIER),
  INCR(Nodes.INTEGER_IDENTIFIER),
  WHILE(Nodes.BOOLEAN_EXPRESSION, Nodes.DO_KEYWORD, Nodes.BLOCK, Nodes.END_KEYWORD);

  public final Keyword keyword;

  Keywords(Nodes... structure) {
    keyword = new Keyword(name(), structure);
  }
}
