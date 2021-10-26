package App.Interpreter.Syntax;

import App.ExitCode;
import App.Interpreter.InterpreterException.InvalidInteger;
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
          (nodes, parseTrees) -> {
            final IntegerIdentifier identifier = (IntegerIdentifier) nodes[1];
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
          (nodes, parseTrees) -> {
            final IntegerIdentifier identifierLeft = (IntegerIdentifier) nodes[1];
            final IntegerIdentifier identifierRight = (IntegerIdentifier) nodes[3];
            try {
              identifierRight.set(identifierLeft.getValue());
            } catch (InvalidInteger invalidInteger) {
              System.exit(ExitCode.IMPOSSIBLE_STATE.code);
            }
          }
      ),
      new SyntaxElement(
          new NodeKind[] {
              NodeKind.DECR_KEYWORD,
              NodeKind.INTEGER_IDENTIFIER,
              NodeKind.LINE_TERMINATOR
          },
          (nodes, parseTrees) -> {
            final IntegerIdentifier identifier = (IntegerIdentifier) nodes[1];
            identifier.decrement();
          }
      ),
      new SyntaxElement(
          new NodeKind[] {
              NodeKind.INCR_KEYWORD,
              NodeKind.INTEGER_IDENTIFIER,
              NodeKind.LINE_TERMINATOR
          },
          (nodes, parseTrees) -> {
            final IntegerIdentifier identifier = (IntegerIdentifier) nodes[1];
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
          (nodes, parseTrees) -> {
            final BooleanExpression expression = (BooleanExpression) nodes[1];

            while (expression.evaluate()) {
              for (ParseTree parseTree : parseTrees) {
                parseTree.run();
              }
            }
          }
      )
  };
}
