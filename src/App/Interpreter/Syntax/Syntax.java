package App.Interpreter.Syntax;

import App.ExitCode;
import App.Interpreter.InterpreterException.RuntimeError;
import App.Interpreter.Node.NodeKind;
import App.Interpreter.Node.Nodes.*;
import App.Interpreter.ParseTree;

public abstract class Syntax {
  public final static SyntaxElement[] all = {
      new SyntaxElement(
          new NodeKind[] {
              NodeKind.CLEAR_KEYWORD,
              NodeKind.INTEGER_IDENTIFIER,
              NodeKind.LINE_TERMINATOR
          },
          input -> {
            final IntegerIdentifier identifier = (IntegerIdentifier) input.nodes[1];
            identifier.clear();
          }
      ),
      new SyntaxElement(
          new NodeKind[] {
              NodeKind.COPY_KEYWORD,
              NodeKind.INTEGER_IDENTIFIER,
              NodeKind.TO_KEYWORD,
              NodeKind.INTEGER_IDENTIFIER,
              NodeKind.LINE_TERMINATOR
          },
          input -> {
            final IntegerIdentifier identifierLeft = (IntegerIdentifier) input.nodes[1];
            final IntegerIdentifier identifierRight = (IntegerIdentifier) input.nodes[3];
            try {
              identifierRight.set(identifierLeft.getValue());
            } catch (RuntimeError runtimeError) {
              System.exit(ExitCode.IMPOSSIBLE_STATE);
            }
          }
      ),
      new SyntaxElement(
          new NodeKind[] {
              NodeKind.DECR_KEYWORD,
              NodeKind.INTEGER_IDENTIFIER,
              NodeKind.LINE_TERMINATOR
          },
          input -> {
            final IntegerIdentifier identifier = (IntegerIdentifier) input.nodes[1];
            identifier.decrement();
          }
      ),
      new SyntaxElement(
          new NodeKind[] {
              NodeKind.FUNC_KEYWORD,
              NodeKind.INTEGER_IDENTIFIER,
              NodeKind.OPEN_BRACKET,
              NodeKind.CLOSE_BRACKET,
              NodeKind.LINE_TERMINATOR,
              NodeKind.BLOCK,
              NodeKind.END_KEYWORD,
              NodeKind.LINE_TERMINATOR
          },
          input -> {
            final IntegerIdentifier identifier = (IntegerIdentifier) input.nodes[1];

            input.execution.addFunction(identifier.getName(), args -> {
              for (ParseTree parseTree : input.parseTrees[0].parseTrees()) {
                parseTree.run(input.execution);
              }
              return null;
            });
          }
      ),
      new SyntaxElement(
          new NodeKind[] {
              NodeKind.IF_KEYWORD,
              NodeKind.BOOLEAN_EXPRESSION,
              NodeKind.DO_KEYWORD,
              NodeKind.LINE_TERMINATOR,
              NodeKind.BLOCK,
              NodeKind.END_KEYWORD,
              NodeKind.LINE_TERMINATOR
          },
          input -> {
            final BooleanExpression expression = (BooleanExpression) input.nodes[1];

            if (expression.evaluate()) {
              for (ParseTree parseTree : input.parseTrees) {
                parseTree.run(input.execution);
              }
            }
          }
      ),
      new SyntaxElement(
          new NodeKind[] {
              NodeKind.INTEGER_IDENTIFIER,
              NodeKind.OPEN_BRACKET,
              NodeKind.CLOSE_BRACKET,
              NodeKind.LINE_TERMINATOR
          },
          input -> {
            final IntegerIdentifier identifier = (IntegerIdentifier) input.nodes[0];
            input.execution.runFunction(identifier.getName(), new Object[] {});
          }
      ),
      new SyntaxElement(
          new NodeKind[] {
              NodeKind.INCR_KEYWORD,
              NodeKind.INTEGER_IDENTIFIER,
              NodeKind.LINE_TERMINATOR
          },
          input -> {
            final IntegerIdentifier identifier = (IntegerIdentifier) input.nodes[1];
            identifier.increment();
          }
      ),
      new SyntaxElement(
          new NodeKind[] {
              NodeKind.WHILE_KEYWORD,
              NodeKind.BOOLEAN_EXPRESSION,
              NodeKind.DO_KEYWORD,
              NodeKind.LINE_TERMINATOR,
              NodeKind.BLOCK,
              NodeKind.END_KEYWORD,
              NodeKind.LINE_TERMINATOR
          },
          input -> {
            final BooleanExpression expression = (BooleanExpression) input.nodes[1];

            while (expression.evaluate()) {
              for (ParseTree parseTree : input.parseTrees) {
                parseTree.run(input.execution);
              }
            }
          }
      )
  };
}
