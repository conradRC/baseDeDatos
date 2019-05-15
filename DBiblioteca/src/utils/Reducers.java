package utils;

import utils.Actions.*;
import utils.States.*;

public class Reducers {
  public class dataBooks implements Reducer<DataBooks> {
    @Override
    public States.DataBooks reduce(DataBooks state, Action action) {
      if (action instanceof SetDataBooks) {
        SetDataBooks data = (SetDataBooks) action;
        return state.setDataBooks(data.getLibros());
      }

      return state;
    }
  }
}