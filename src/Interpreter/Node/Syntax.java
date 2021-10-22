package Interpreter.Node;

public abstract class Syntax {
  public final static NodeKind[][] all = {
    {NodeKind.CLEAR_KEYWORD, NodeKind.INTEGER_IDENTIFIER, NodeKind.LINE_TERMINATOR},
    {NodeKind.COPY_KEYWORD, NodeKind.INTEGER_IDENTIFIER, NodeKind.TO_KEYWORD, NodeKind.INTEGER_IDENTIFIER, NodeKind.LINE_TERMINATOR},
    {NodeKind.DECR_KEYWORD, NodeKind.INTEGER_IDENTIFIER, NodeKind.LINE_TERMINATOR},
    {NodeKind.INCR_KEYWORD, NodeKind.INTEGER_IDENTIFIER, NodeKind.LINE_TERMINATOR},
    {NodeKind.WHILE_KEYWORD, NodeKind.BOOLEAN_EXPRESSION, NodeKind.DO_KEYWORD, NodeKind.LINE_TERMINATOR, NodeKind.BLOCK, NodeKind.END_KEYWORD, NodeKind.LINE_TERMINATOR},
  };
}
