package utils;

public class Connect {
  public interface Subscription {
    void unsubscribe();
  }

  public interface Subscriber<State> {
    void onChange(State state);
  }
}