package modelo;

public class Libro {

	private String isbn,titulo, autor;
	private int edicion,anioPublicacion,idEditorial;
	
public Libro(String isbn, String titulo, int edicion, int anioPublicacion, int idEditorial, String autor) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.edicion = edicion;
		this.anioPublicacion = anioPublicacion;
		this.idEditorial = idEditorial;
		this.autor = autor;
	}
	/*
	public Libro(String isbn, String titulo, int edicion, int anioPublicacion, int idEditorial) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.edicion = edicion;
		this.anioPublicacion = anioPublicacion;
		this.idEditorial = idEditorial;
	}*/
	public Libro() {}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getEdicion() {
		return edicion;
	}
	public void setEdicion(int edicion) {
		this.edicion = edicion;
	}
	public int getAnioPublicacion() {
		return anioPublicacion;
	}
	public void setAnioPublicacion(int anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}
	public int getIdEditorial() {
		return idEditorial;
	}
	public void setIdEditorial(int idEditorial) {
		this.idEditorial = idEditorial;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
}
