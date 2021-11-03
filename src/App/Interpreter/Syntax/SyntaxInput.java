package App.Interpreter.Syntax;

import App.Interpreter.*;
import App.Interpreter.Node.Node;

public class SyntaxInput {
  public final Node[] nodes;
  public final ParseTree[] parseTrees;
  public final Execution execution;

  public SyntaxInput(Node[] nodes, ParseTree[] parseTrees, Execution execution) {
    this.nodes = nodes;
    this.parseTrees = parseTrees;
    this.execution = execution;
  }
}
