package App.Interpreter;

import App.Interpreter.InterpreterException.RuntimeError;

public interface CodeBlock {
  Object run(Object[] args) throws RuntimeError;
}
