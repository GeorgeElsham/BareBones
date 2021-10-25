package App.Interpreter.Node.Protocols;

public interface Equatable<Value> {
  /**
   * Gets value given by implementation to
   * be used to check for equality.
   *
   * @return Value to check for equality.
   */
  Value getEquatableValue();

  static <Value extends Equatable<?>> boolean isEqual(Value left, Value right) {
    return left.getEquatableValue().equals(right.getEquatableValue());
  }

  static <Value extends Equatable<?>> boolean isNotEqual(Value left, Value right) {
    return !isEqual(left, right);
  }
}
