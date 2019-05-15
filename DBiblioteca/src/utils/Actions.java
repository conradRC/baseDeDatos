package utils;

import modelo.Libro;

public class Actions {
  public class SetDataBooks implements Action {
    private final Libro[] libros;

    public SetDataBooks(Libro[] libros) {
      this.libros = libros;
    }

    public Libro[] getLibros() {
      return libros;
    }
  }
}