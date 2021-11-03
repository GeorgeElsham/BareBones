package App.Interpreter.Syntax;

import App.Interpreter.InterpreterException.InvalidInteger;
import App.Interpreter.Node.*;

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

  public void run(SyntaxInput input) throws InvalidInteger {
    syntaxToCode.run(input);
  }
}

interface SyntaxToCode {
  void run(SyntaxInput input) throws InvalidInteger;
}
