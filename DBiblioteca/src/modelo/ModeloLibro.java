package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;

import complementos.Conectiondb;
import vista.VistaLibro;

public  class ModeloLibro {
	private Conectiondb conectiondb;
	private String db ="dbbiblioteca";
	private VistaLibro vistaLibro;
	private List<Editorial> editoriales;
	
	public ModeloLibro(VistaLibro vistaLibro) {
		conectiondb = new Conectiondb(db, "127.0.0.1");
		this.vistaLibro = vistaLibro;
		listEditorial();
	}

	public boolean insertLibro(Libro l){
		
        PreparedStatement ps;
	    String sqlInsertLibro = "insert into scbiblioteca.libro values (?,?,?,?,?,?);";
        try{
            ps  = conectiondb.getConexion().prepareStatement(sqlInsertLibro);
            ps.setString(1, l.getIsbn());
            ps.setString(2, l.getTitulo());
            ps.setInt	(3, l.getEdicion());
            ps.setInt	(4, l.getAnioPublicacion());
            ps.setInt	(5, l.getIdEditorial());
            ps.setString(6, l.getAutor());
            
            ps.executeUpdate();
            return true;
        }catch (SQLException exception) {
            System.err.println("Error en la INSERCIÓN  (Libro)" + exception );
			return false;
        }
	}
	
	public boolean deleteLibro(Libro l){
        PreparedStatement ps;
		String sqlDeleteLibro = "delete from scbiblioteca.libro where isbn  = ?;";
		
        try{
            ps  = conectiondb.getConexion().prepareStatement(sqlDeleteLibro);
            ps.setString(1, l.getIsbn());

            ps.executeUpdate();

	    return true;
        }catch (SQLException exception) {
            System.err.println("Error en el BORRADO (Libro)"+ exception);
			return false;
        }
	}
	
	public boolean updateLibro(Libro l){
       
        PreparedStatement ps;
		String sqlUpdateLibro = "update scbiblioteca.libro set titulo = ?, edicion = ?, anioPublicacion = ?, idEditorial = ? where isbn = ?;";
        try{
       
            ps  = conectiondb.getConexion().prepareStatement(sqlUpdateLibro);
       
            ps.setString(1, l.getIsbn());
            ps.setString(2, l.getTitulo());
            ps.setInt	(3, l.getEdicion());
            ps.setInt	(4, l.getAnioPublicacion());
            ps.setInt	(5, l.getIdEditorial());
            ps.setString(6, l.getAutor());
            
            ps.executeUpdate();
            
			return true;
        }catch (SQLException exception) {
            System.err.println("Error en la MODIFICACION (Libro " + exception);
			return false;
        }
	}
	
	public Libro selectLibro(Libro l){
     
		PreparedStatement ps;
        
		ResultSet rs;
		
		Libro libroEncontrado= null;
		
		String sqlConsulta = "select * from scbiblioteca.libro where isbn = ?;";
        try{
           
            ps  = conectiondb.getConexion().prepareStatement(sqlConsulta);
            ps.setString(1, l.getIsbn());
           
            rs  = ps.executeQuery();
            
            if(rs.next()){
            	libroEncontrado = new Libro(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),rs.getString(6));  
            }
        }catch (SQLException exception) {
            System.err.println("Error al CARGAR UN Libro");
        }
		return libroEncontrado;
	}
	
	
	public List<Libro> listLibro(){
	PreparedStatement ps;
	
        ResultSet rs;
       		String consultaSQL = "Select * from scbiblioteca.libro;";
       		
       		String consultaSQL2 = "Select rfc, nombre,nacionalidad from scbiblioteca.autores;";
       		
		List<Libro> libros = new ArrayList<Libro>();
		
        try {
            ps  = conectiondb.getConexion().prepareStatement(consultaSQL);
            rs  = ps.executeQuery();
            while(rs.next()){
            	
            	Libro l = new Libro();
                l.setIsbn(rs.getString("isbn"));
                l.setTitulo(rs.getString("titulo"));
                l.setEdicion(rs.getInt("edicion"));
                l.setAnioPublicacion(rs.getInt("anioPublicacion"));
                l.setIdEditorial(rs.getInt("idEditorial"));
                l.setAutor(rs.getString("rfc"));
                libros.add(l);
            }
           
            list();
 
        } catch (SQLException exception) {
            System.err.println("Error al CARGAR DATOS (Libro)" + exception);
        }
		return libros;
	}
	
	public List<Autor> list(){
		PreparedStatement ps;
		
	        ResultSet rs;
	       		String consultaSQL = "Select rfc, nombre,nacionalidad from scbiblioteca.autores;";
			
	       		List<Autor> autores = new ArrayList<Autor>();
	        try {
	            ps  = conectiondb.getConexion().prepareStatement(consultaSQL);
	            rs  = ps.executeQuery();
	            while(rs.next()){
	            	Autor autor = new Autor();
	            	
	            	autor.setRfc(rs.getString("rfc"));
	                autor.setNombre(rs.getString("nombre"));
	                autor.setNacionalidad(rs.getString("nacionalidad"));
	                autores.add(autor);
	            }
	            
	            for (int c = 0; c < autores.size(); c++)
	            	vistaLibro.agregarItemA(autores.get(c).getRfc());
	 
	        } catch (SQLException exception) {
	            System.err.println("Error al CARGAR DATOS (Libro)" + exception);
	        }
			return autores;
		}
	
	public boolean insertEditorial(Editorial l){
        PreparedStatement ps;
	    String sqlInsertEditorial = "insert into scbiblioteca.editorial values (?,?);";
        try{
            ps  = conectiondb.getConexion().prepareStatement(sqlInsertEditorial);
            ps.setInt	(1, l.getIdEditorial());
            ps.setString(2, l.getNombreeditorial());
            
            ps.executeUpdate();
            return true;
        }catch (SQLException exception) {
            System.err.println("Error en la INSERCIÓN (Editorial)" + exception );
			return false;
        }
	}
	
	public boolean deleteEditorial(Editorial l){
        PreparedStatement ps;
		String sqlDeleteEditorial = "delete from scbiblioteca.editorial where idEditorial  = ?;";
		
        try{
            ps  = conectiondb.getConexion().prepareStatement(sqlDeleteEditorial);
            ps.setInt(1, l.getIdEditorial());

            ps.executeUpdate();
	    return true;
        }catch (SQLException exception) {
            System.err.println("Error en el BORRADO (Editorial)"+ exception);
			return false;
        }
	}
	
	public boolean updateEditorial(Editorial l){
	       
        PreparedStatement ps;
		String sqlUpdateLibro = "update scbiblioteca.editorial set nombreeditorial = ?, where idEditorial = ?;";
        try{
       
            ps  = conectiondb.getConexion().prepareStatement(sqlUpdateLibro);
       
            ps.setInt(1, l.getIdEditorial());
            ps.setString(2, l.getNombreeditorial());
            ps.executeUpdate();
            
			return true;
        }catch (SQLException exception) {
            System.err.println("Error en la MODIFICACION (Editorial " + exception);
			return false;
        }
	}
	
	public Editorial selectEditorial(Editorial l){
	     
		PreparedStatement ps;
        
		ResultSet rs;
		
		Editorial editorialEncontrado= null;
		
		String sqlConsulta = "select idEditorial, nombreeditorial from scbiblioteca.editorial where idEditorial = ?;";
        try{
           
            ps  = conectiondb.getConexion().prepareStatement(sqlConsulta);
            ps.setInt(1, l.getIdEditorial());
            rs  = ps.executeQuery();
            
            if(rs.next()){
            	editorialEncontrado = new Editorial(rs.getInt(1), rs.getString(2));  
            }
        }catch (SQLException exception) {
            System.err.println("Error al CARGAR UN Editorial");
        }
		return editorialEncontrado;
	}
	
	
	public List<Editorial> listEditorial(){
		PreparedStatement ps;
	        ResultSet rs;
	       		String consultaSQL = "Select idEditorial, nombreeditorial from scbiblioteca.editorial;";
			
			editoriales = new ArrayList<Editorial>();
	        try {
	            ps  = conectiondb.getConexion().prepareStatement(consultaSQL);
	            rs  = ps.executeQuery();
	            while(rs.next()){
	            	Editorial e = new Editorial();
	            	e.setIdEditorial(rs.getInt("idEditorial"));
	                e.setNombreeditorial(rs.getString("nombreeditorial"));
	                editoriales.add(e);
	            }	 
	            for (int c = 0; c < editoriales.size(); c++) {
	            	vistaLibro.agregarItem(editoriales.get(c).getIdEditorial());
	            }
	            	
	            
	        } catch (SQLException exception) {
	            System.err.println("Error al CARGAR DATOS (Editorial) " + exception);
	        }
			return editoriales;
		}
	
	
	
}
