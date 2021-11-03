package App.Interpreter;

import App.Interpreter.InterpreterException.RuntimeError;
import App.Interpreter.Node.Node;
import App.Interpreter.Syntax.Syntax;
import App.Interpreter.Syntax.SyntaxInput;
import java.util.Arrays;

public record ParseTree(int syntaxIndex, Node[] nodes, ParseTree[] parseTrees) {
  public void run(Execution execution) throws RuntimeError {
    Syntax.all[syntaxIndex].run(new SyntaxInput(nodes, parseTrees, execution));
  }

  @Override
  public String toString() {
    return "ParseTree{" +
        "syntaxIndex=" + syntaxIndex +
        ", nodes=" + Arrays.toString(nodes) +
        ", parseTrees=" + Arrays.toString(parseTrees) +
        '}';
  }
}
