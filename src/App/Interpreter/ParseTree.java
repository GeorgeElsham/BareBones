package App.Interpreter;

import App.Interpreter.InterpreterException.InvalidInteger;
import App.Interpreter.Node.Node;
import App.Interpreter.Syntax.Syntax;
import java.util.Arrays;

public class ParseTree {
  private final int syntaxIndex;
  private final Node[] nodes;
  private final ParseTree[] subtrees;

  public ParseTree(int syntaxIndex, Node[] nodes, ParseTree[] subtrees) {
    this.syntaxIndex = syntaxIndex;
    this.nodes = nodes;
    this.subtrees = subtrees;
  }

  public void run() throws InvalidInteger {
    Syntax.all[syntaxIndex].run(nodes, subtrees);
  }

  @Override
  public String toString() {
    return "ParseTree{" +
        "syntaxIndex=" + syntaxIndex +
        ", nodes=" + Arrays.toString(nodes) +
        ", subtrees=" + Arrays.toString(subtrees) +
        '}';
  }
}
