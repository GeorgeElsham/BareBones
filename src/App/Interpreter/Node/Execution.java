package App.Interpreter.Node;

import java.util.*;

public class Execution {
  private HashMap<String, List<Integer>> record;

  public int getVariable(String name) {
    final List<Integer> value = record.get(name);
    return value.get(value.size() - 1);
  }

  public void setVariable(String name, int value) {
    List<Integer> currentValue = record.get(name);

    if (currentValue == null) {
      record.put(name, List.of(value));
    } else {
      currentValue.addAll(Collections.singleton(value));
      record.put(name, currentValue);
    }
  }

  public void clearVariable(String name) {
    setVariable(name, 0);
  }
}
