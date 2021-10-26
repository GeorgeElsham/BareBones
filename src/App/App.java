package App;

import App.Interpreter.Interpreter;

public class App {
  public static void main(String[] args) {
    final App app = new App();
    app.run();
  }

  private void run() {
    final Interpreter interpreter = new Interpreter();

    final String code = FileHandler.readFile("examples/counter.bb");
    if (code != null) {
      interpreter.execute(code);
    } else {
      System.err.println("Missing program");
      System.exit(ExitCode.MISSING_PROGRAM);
    }
  }
}
