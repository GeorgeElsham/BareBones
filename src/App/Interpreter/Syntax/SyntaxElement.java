package App.Interpreter.Syntax;

import App.Interpreter.InterpreterException.InvalidInteger;
import App.Interpreter.Node.*;
import App.Interpreter.ParseTree;

public class SyntaxElement {
  private final NodeKind[] nodeKinds;
  private final SyntaxToCode syntaxToCode;

  public SyntaxElement(NodeKind[] nodeKinds, SyntaxToCode syntaxToCode) {
    this.nodeKinds = nodeKinds;
    this.syntaxToCode = syntaxToCode;
  }

  public int numberOfParts() {
    return nodeKinds.length;
  }

  public NodeKind getNodeKind(int index) {
    return nodeKinds[index];
  }

  public void run(Node[] nodes, ParseTree[] parseTrees) throws InvalidInteger {
    syntaxToCode.run(nodes, parseTrees);
  }
}

interface SyntaxToCode {
  void run(Node[] nodes, ParseTree[] parseTrees) throws InvalidInteger;
}
