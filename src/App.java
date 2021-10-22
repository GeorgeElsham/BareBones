import Interpreter.Interpreter;

public class App {
  public static void main(String[] args) {
    final App app = new App();
    app.run();
  }

  private void run() {
    final Interpreter interpreter = new Interpreter();
    final String code =
        "clear X;\n"
            + "incr X;\n"
            + "incr X;\n"
            + "incr X;\n"
            + "while X not 0 do;\n"
            + "   decr X;\n"
            + "end;";
    interpreter.execute(code);
  }
}
