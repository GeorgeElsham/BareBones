package App.Interpreter;

import App.Interpreter.InterpreterException.InvalidInteger;

public interface CodeBlock {
  Object run(Object[] args) throws InvalidInteger;
}
