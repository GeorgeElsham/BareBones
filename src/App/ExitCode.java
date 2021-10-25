package App;

public enum ExitCode {
  NORMAL(0),
  IMPOSSIBLE_STATE(1);

  public final int code;

  ExitCode(int code) {
    this.code = code;
  }
}