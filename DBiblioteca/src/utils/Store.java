package utils;

public class Store<State> {
  private State state;
  private Reducer<State> reducer;

  public Store(State initialState, Reducer<State> rootReducer) {
    state = initialState;
    reducer = rootReducer;
  }

  public State getState() {
    return state;
  }

  public void dispatch(Action action) {
    state = reducer.reduce(state, action);
  }
}