package Interpreter.Node;

import Interpreter.Node.Nodes.Nodes;

public abstract class Syntax {
  public final static Nodes[][] all = {
    {Nodes.CLEAR_KEYWORD, Nodes.INTEGER_IDENTIFIER, Nodes.LINE_TERMINATOR},
    {Nodes.COPY_KEYWORD, Nodes.INTEGER_IDENTIFIER, Nodes.TO_KEYWORD, Nodes.INTEGER_IDENTIFIER, Nodes.LINE_TERMINATOR},
    {Nodes.DECR_KEYWORD, Nodes.INTEGER_IDENTIFIER, Nodes.LINE_TERMINATOR},
    {Nodes.INCR_KEYWORD, Nodes.INTEGER_IDENTIFIER, Nodes.LINE_TERMINATOR},
    {Nodes.WHILE_KEYWORD, Nodes.BOOLEAN_EXPRESSION, Nodes.DO_KEYWORD, Nodes.LINE_TERMINATOR, Nodes.BLOCK, Nodes.END_KEYWORD, Nodes.LINE_TERMINATOR},
  };
}
