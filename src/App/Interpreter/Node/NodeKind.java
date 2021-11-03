package App.Interpreter.Node;

public enum NodeKind {
  BLOCK,
  BOOLEAN_EXPRESSION,
  CLEAR_KEYWORD,
  CLOSE_BRACKET,
  COPY_KEYWORD,
  DECR_KEYWORD,
  DO_KEYWORD,
  END_KEYWORD,
  EQUALS_OPERATOR,
  FUNC_KEYWORD,
  INCR_KEYWORD,
  INTEGER_IDENTIFIER,
  INTEGER_LITERAL,
  LINE_TERMINATOR,
  NOT_EQUALS_OPERATOR,
  NOT_OPERATOR,
  OPEN_BRACKET,
  TO_KEYWORD,
  WHILE_KEYWORD;

  public boolean isExpression() {
    return this == BOOLEAN_EXPRESSION;
  }

  public String readableName() {
    final String spaced = name().replace("_", " ");
    return spaced.toLowerCase();
  }
}
