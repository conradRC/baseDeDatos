package modelo;

public class Autor {
	
	private String rfc,nombre, nacionalidad;

	public Autor() {}
	public Autor(String rfc, String nombre, String nacionalidad) {
		this.rfc = rfc;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
}
