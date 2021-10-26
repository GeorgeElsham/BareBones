package App;

import java.io.*;
import java.util.*;

public class FileHandler {
  public static String readFile(String name) {
    try {
      final File file = new File(name);
      final Scanner scanner = new Scanner(file);

      List<String> lines = new ArrayList<>();
      while (scanner.hasNextLine()) {
        final String line = scanner.nextLine();
        lines.add(line);
      }

      scanner.close();
      return String.join("\n", lines);
    } catch (FileNotFoundException e) {
      return null;
    }
  }
}
