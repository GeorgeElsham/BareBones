package App.Interpreter;

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
    System.out.println("Set " + name + " to " + value);
    if (value < 0) {
      throw new InvalidInteger(value);
    }
    final List<Integer> currentValue = record.get(name);

    if (currentValue == null) {
      record.put(name, List.of(value));
    } else {
      List<Integer> copy = new ArrayList<>(currentValue);
      copy.add(value);
      record.put(name, copy);
    }
  }
}
