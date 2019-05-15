package utils;

import modelo.Libro;

public class States {
  public class DataBooks {
    private final Libro[] data;

    public DataBooks(Libro[] data) {
      this.data = data;
    }

    public Libro[] getDataBooks() {
      return data;
    }

    public DataBooks setDataBooks(Libro[] data) {
      return new DataBooks(data);
    }
  }
}