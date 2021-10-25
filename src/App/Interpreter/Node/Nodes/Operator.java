package App.Interpreter.Node.Nodes;

import App.Interpreter.InterpreterException.UnknownOperator;
import App.Interpreter.Node.*;
import App.Interpreter.Node.Protocols.Equatable;
import java.util.HashMap;

public class Operator implements Node {
  private static final HashMap<String, OperatorElement> all;
  private final String name;

  static {
    HashMap<String, OperatorElement> map = new HashMap<>();
    map.put("not", new OperatorElement(NodeKind.NOT_EQUAL_OPERATOR, OperatorPresence.NOT_EQUAL, new NotEqualOperator<>()));
    map.put("!", new OperatorElement(NodeKind.NOT_OPERATOR, OperatorPresence.NOT, new NotOperator<>()));
    all = map;
  }

  public Operator(String name) throws UnknownOperator {
    if (all.get(name) == null) {
      throw new UnknownOperator(name);
    }
    this.name = name;
  }

  public String getName() {
    return name;
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


class OperatorElement {
  public final NodeKind nodeKind;
  public final OperatorPresence presence;
  public final AnyOperator operator;

  OperatorElement(NodeKind nodeKind, OperatorPresence presence, AnyOperator operator) {
    this.nodeKind = nodeKind;
    this.presence = presence;
    this.operator = operator;
  }
}


enum OperatorPresence {
  NOT_EQUAL(1),
  NOT(2);

  private final int priority;

  OperatorPresence(int priority) {
    this.priority = priority;
  }

  public boolean isHigherThan(OperatorPresence presence) {
    return priority > presence.priority;
  }
}


interface AnyOperator {};

abstract class PrefixOperator<Right, Output> implements AnyOperator {
  /**
   * Calculates output from the given input.
   *
   * @param right Value to right of operator.
   * @return Output result of calculation.
   */
  public abstract Output calculate(Right right);
}

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


class NotOperator<Value extends Equatable<?>> extends PrefixOperator<Boolean, Boolean> {
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

class NotEqualOperator<Value extends Equatable<?>> extends InfixOperator<Value, Value, Boolean> {
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
