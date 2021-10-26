package App.Interpreter;

import App.Interpreter.InterpreterException.InvalidInteger;
import App.Interpreter.Node.Node;
import App.Interpreter.Syntax.Syntax;
import java.util.Arrays;

public record ParseTree(int syntaxIndex, Node[] nodes, ParseTree[] parseTrees) {
  public void run() throws InvalidInteger {
    Syntax.all[syntaxIndex].run(nodes, parseTrees);
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
