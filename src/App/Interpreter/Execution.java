package App.Interpreter;

import App.ExitCode;
import App.Interpreter.InterpreterException.InvalidInteger;
import java.util.*;
import java.util.stream.*;

public class Execution {
  private final LinkedHashMap<String, List<Integer>> record = new LinkedHashMap<>();

  public void clearVariable(String name) {
    try {
      setVariable(name, 0);
    } catch (InvalidInteger invalidInteger) {
      System.exit(ExitCode.IMPOSSIBLE_STATE);
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
      System.exit(ExitCode.IMPOSSIBLE_STATE);
    }
  }

  public void setVariable(String name, int value) throws InvalidInteger {
    System.out.println("Set " + name + " to " + value);
    if (value < 0) {
      throw new InvalidInteger(name, value);
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

  /**
   * Prints execution table. If variables names are passed in,
   * only those are displayed and in the order given.
   */
  public void printExecutionTable(String... limited) {
    // Create array of variable names
    final String[] variables;
    if (limited.length == 0) {
      variables = record.keySet().toArray(String[]::new);
    } else {
      variables = Arrays.stream(limited)
          .filter(x -> record.get(x) != null)
          .toArray(String[]::new);
    }

    // Create array of variable values
    @SuppressWarnings("unchecked")
    final List<Integer>[] values = Stream.of(variables).map(record::get).toArray(List[]::new);

    // Create widths for each variable column
    final int[] widths = IntStream.range(0, variables.length)
        .map(variableIndex -> {
          final IntStream valueLengths = values[variableIndex].stream().mapToInt(x -> String.valueOf(x).length());
          return Math.max(valueLengths.max().orElse(0), variables[variableIndex].length()) + 2;
        })
        .toArray();

    // Print top row
    System.out.println("╔" + Arrays.stream(widths).mapToObj("═"::repeat).collect(Collectors.joining("╦")) + "╗");

    // Create variable heading line
    final Stream<String> headings = IntStream.range(0, variables.length)
        .mapToObj(variableIndex -> centerAlignString(variables[variableIndex], widths[variableIndex]));
    System.out.println("║" + headings.collect(Collectors.joining("║")) + "║");

    // Create header separator
    System.out.println("╠" + Arrays.stream(widths).mapToObj("═"::repeat).collect(Collectors.joining("╬")) + "╣");

    // Print each line of values
    for (int valuesIndex = 0; valuesIndex < Arrays.stream(values).mapToInt(Collections::max).max().orElse(0); valuesIndex++) {
      int finalValuesIndex = valuesIndex;
      final Stream<String> valueStrings = IntStream.range(0, variables.length).mapToObj(variableIndex -> {
        if (finalValuesIndex < values[variableIndex].size()) {
          return " " + rightAlignString(String.valueOf(values[variableIndex].get(finalValuesIndex)), widths[variableIndex] - 2) + " ";
        } else {
          return " ".repeat(widths[variableIndex]);
        }
      });

      System.out.println("║" + valueStrings.collect(Collectors.joining("║")) + "║");
    }

    // Print bottom line
    System.out.println("╚" + Arrays.stream(widths).mapToObj("═"::repeat).collect(Collectors.joining("╩")) + "╝");
  }

  /**
   * Align a string to the center of the space.
   *
   * @param string String to align.
   * @param width Available space.
   * @return String padded with spaces.
   */
  private String centerAlignString(String string, int width) {
    final int diff = width - string.length();
    final int leftLength = (int) Math.ceil((double) diff / 2);
    final int rightLength = (int) Math.floor((double) diff / 2);
    return " ".repeat(leftLength) + string + " ".repeat(rightLength);
  }

  /**
   * Align a string to the right of the space.
   *
   * @param string String to align.
   * @param width Available space.
   * @return String padded with spaces.
   */
  private String rightAlignString(String string, int width) {
    final int diff = width - string.length();
    return " ".repeat(diff) + string;
  }
}
