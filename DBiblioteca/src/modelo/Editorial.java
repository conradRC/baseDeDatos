 package modelo;

public class Editorial {
	
	private int idEditorial;
	private String nombreeditorial;
	
	public Editorial() {}

	public Editorial(int idEditorial, String nombreeditorial) {
		super();
		this.idEditorial = idEditorial;
		this.nombreeditorial = nombreeditorial;
	}
	
	public int getIdEditorial() {
		return idEditorial;
	}
	public void setIdEditorial(int idEditorial) {
		this.idEditorial = idEditorial;
	}
	public String getNombreeditorial() {
		return nombreeditorial;
	}
	public void setNombreeditorial(String nombreeditorial) {
		this.nombreeditorial = nombreeditorial;
	}
	
	
}
