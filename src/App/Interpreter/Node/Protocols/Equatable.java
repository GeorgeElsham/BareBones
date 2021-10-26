package App.Interpreter.Node.Protocols;

/**
 * Interface to mark a type as equatable. This
 * means they can be compared for equality.
 *
 * @param <Value> Value to compare.
 */
public interface Equatable<Value> {
  /**
   * Gets value given by implementation to
   * be used to check for equality.
   *
   * @return Value to check for equality.
   */
  Value getEquatableValue();

  /**
   * Get if two objects are equal.
   *
   * @param left Left object.
   * @param right Right object.
   * @param <Value> Object types.
   * @return Whether they are equal.
   */
  static <Value extends Equatable<?>> boolean isEqual(Value left, Value right) {
    return left.getEquatableValue().equals(right.getEquatableValue());
  }

  /**
   * Get if two objects are not equal.
   *
   * @param left Left object.
   * @param right Right object.
   * @param <Value> Object types.
   * @return Whether they are not equal.
   */
  static <Value extends Equatable<?>> boolean isNotEqual(Value left, Value right) {
    return !isEqual(left, right);
  }
}
