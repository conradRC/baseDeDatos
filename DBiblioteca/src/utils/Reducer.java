package utils;

public interface Reducer<State> {
  State reduce(State state, Action action);
}