package App.Interpreter.Node;

import App.ExitCode;
import App.Interpreter.InterpreterException.InvalidInteger;
import java.util.*;

public class Execution {
  private final HashMap<String, List<Integer>> record = new HashMap<>();

  public void clearVariable(String name) {
    try {
      setVariable(name, 0);
    } catch (InvalidInteger invalidInteger) {
      System.exit(ExitCode.IMPOSSIBLE_STATE.code);
    }
  }

  public void decrementVariable(String name) throws InvalidInteger {
    final int currentValue = getVariable(name);
    setVariable(name, currentValue - 1);
  }

  public int getVariable(String name) {
    final List<Integer> value = record.get(name);
    if (value == null) {
      clearVariable(name);
      return 0;
    } else {
      return value.get(value.size() - 1);
    }
  }

  public void incrementVariable(String name) {
    final int currentValue = getVariable(name);

    try {
      setVariable(name, currentValue + 1);
    } catch (InvalidInteger invalidInteger) {
      System.exit(ExitCode.IMPOSSIBLE_STATE.code);
    }
  }

  public void setVariable(String name, int value) throws InvalidInteger {
    if (value < 0) {
      throw new InvalidInteger(value);
    }
    List<Integer> currentValue = record.get(name);

    if (currentValue == null) {
      record.put(name, List.of(value));
    } else {
      currentValue.addAll(Collections.singleton(value));
      record.put(name, currentValue);
    }
  }
}
