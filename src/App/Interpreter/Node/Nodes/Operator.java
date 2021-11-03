package App.Interpreter.Node.Nodes;

import App.Interpreter.InterpreterException.UnknownOperator;
import App.Interpreter.Node.*;
import App.Interpreter.Node.Protocols.Equatable;
import java.util.HashMap;

/**
 * Represents an operator.
 */
public class Operator implements Node {
  private static final HashMap<String, OperatorElement> all;
  private final String name;

  static {
    HashMap<String, OperatorElement> map = new HashMap<>();
    map.put("not", new OperatorElement(NodeKind.NOT_EQUALS_OPERATOR, OperatorPrecedence.NOT_EQUAL, new NotEqualsOperator<>()));
    map.put("!", new OperatorElement(NodeKind.NOT_OPERATOR, OperatorPrecedence.NOT, new NotOperator()));
    all = map;
  }

  public Operator(String name) throws UnknownOperator {
    if (all.get(name) == null) {
      throw new UnknownOperator(name);
    }
    this.name = name;
  }

  /**
   * Gets operator name.
   *
   * @return Name of operator in code.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets operator instance.
   *
   * @return Operator instance.
   */
  public AnyOperator getOperator() {
    return all.get(name).operator;
  }

  /**
   * Gets the kind of node this is.
   *
   * @return Kind of node.
   */
  @Override
  public NodeKind getKind() {
    return all.get(name).nodeKind;
  }
}


/**
 * Operator element, representing how the operator works.
 */
class OperatorElement {
  public final NodeKind nodeKind;
  public final OperatorPrecedence precedence;
  public final AnyOperator operator;

  OperatorElement(NodeKind nodeKind, OperatorPrecedence precedence, AnyOperator operator) {
    this.nodeKind = nodeKind;
    this.precedence = precedence;
    this.operator = operator;
  }
}


/**
 * Operator precedence, determining the priority of adjacent operators.
 */
enum OperatorPrecedence {
  NOT_EQUAL(1),
  NOT(2);

  private final int priority;

  OperatorPrecedence(int priority) {
    this.priority = priority;
  }

  /**
   * Gets if this operator has a higher
   * precedence than the given operator.
   *
   * @param precedence Other operator precedence to compare.
   * @return If this operator has a higher precedence.
   */
  public boolean isHigherThan(OperatorPrecedence precedence) {
    return priority > precedence.priority;
  }
}


/**
 * Represents any operator kind.
 */
interface AnyOperator {}

/**
 * Represents a prefix operator.
 *
 * @param <Right> Right side input.
 * @param <Output> Output of operation.
 */
abstract class PrefixOperator<Right, Output> implements AnyOperator {
  /**
   * Calculates output from the given input.
   *
   * @param right Value to right of operator.
   * @return Output result of calculation.
   */
  public abstract Output calculate(Right right);
}

/**
 * Represents an infix operator.
 *
 * @param <Left> Left side input.
 * @param <Right> Right side input.
 * @param <Output> Output of operation.
 */
abstract class InfixOperator<Left, Right, Output> implements AnyOperator {
  /**
   * Calculates output from the given input.
   *
   * @param left Value to left of operator.
   * @param right Value to right of operator.
   * @return Output result of calculation.
   */
  public abstract Output calculate(Left left, Right right);
}


/**
 * Represents a not operator. Inverts a boolean value.
 */
class NotOperator extends PrefixOperator<Boolean, Boolean> {
  /**
   * Calculates output from the given input.
   *
   * @param right Value to right of operator.
   * @return Output result of calculation.
   */
  @Override
  public Boolean calculate(Boolean right) {
    return !right;
  }
}

/**
 * Represents a not equals operator. Checks if two values are not equal.
 *
 * @param <Value> Generic equatable value to compare.
 */
class NotEqualsOperator<Value extends Equatable<?>> extends InfixOperator<Value, Value, Boolean> {
  /**
   * Calculates output from the given input.
   *
   * @param left  Value to left of operator.
   * @param right Value to right of operator.
   * @return Output result of calculation.
   */
  @Override
  public Boolean calculate(Value left, Value right) {
    return Equatable.isNotEqual(left, right);
  }
}
