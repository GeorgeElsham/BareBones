package App.Interpreter.Node.Nodes;

import App.Interpreter.InterpreterException.InvalidBooleanExpression;
import App.Interpreter.Node.*;
import App.Interpreter.Node.Protocols.IntegerValue;

public class BooleanExpression implements Node {
  private final IntegerValue left;
  private final InfixOperator<IntegerValue, IntegerValue, Boolean> operator;
  private final IntegerValue right;

  public BooleanExpression(Node[] nodes) throws InvalidBooleanExpression {
    if (nodes.length == 3 &&
        nodes[0] instanceof IntegerValue &&
        nodes[1] instanceof final Operator op &&
        nodes[2] instanceof IntegerValue &&
        op.getOperator() instanceof InfixOperator
    ) {
      left = (IntegerValue) nodes[0];
      right = (IntegerValue) nodes[2];

      @SuppressWarnings("unchecked")
      final InfixOperator<IntegerValue, IntegerValue, Boolean> operator = (InfixOperator<IntegerValue, IntegerValue, Boolean>) op.getOperator();
      this.operator = operator;
    } else {
      throw new InvalidBooleanExpression(nodes);
    }
  }

  public boolean evaluate() {
    return operator.calculate(left, right);
  }

  /**
   * Gets the kind of node this is.
   *
   * @return Kind of node.
   */
  @Override
  public NodeKind getKind() {
    return NodeKind.BOOLEAN_EXPRESSION;
  }
}
