package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import complementos.Conectiondb;

public class ModeloLibro {
	private Conectiondb conectiondb;
	private String db ="dbbiblioteca";
	public ModeloLibro() {
		conectiondb = new Conectiondb(db, "127.0.0.1");
	}
	
	public boolean insertLibro(Libro l){
        PreparedStatement ps;
	    String sqlInsertLibro = "insert into scbiblioteca.libro values (?,?,?,?,?);";
        try{
            ps  = conectiondb.getConexion().prepareStatement(sqlInsertLibro);
            ps.setString(1, l.getIsbn());
            ps.setString(2, l.getTitulo());
            ps.setInt	(3, l.getEdicion());
            ps.setInt	(4, l.getAnioPublicacion());
            ps.setInt	(5, l.getIdEditorial());
       
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
            //System.out.println(this.view.dtm.getValueAt(filaPulsada, 0));
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
		
		String sqlConsulta = "select isbn, titulo, edicion, anioPublicacion, idEditorial from scbiblioteca.libro where isbn = ?;";
        try{
           
            ps  = conectiondb.getConexion().prepareStatement(sqlConsulta);
            ps.setString(1, l.getIsbn());
           
            rs  = ps.executeQuery();
            
            if(rs.next()){
            	libroEncontrado = new Libro(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));  
            }
        }catch (SQLException exception) {
            System.err.println("Error al CARGAR UN Libro");
        }
		return libroEncontrado;
	}
	
	
	public List<Libro> listLibro(){
	PreparedStatement ps;
        ResultSet rs;
       		String consultaSQL = "Select isbn, titulo, edicion, anioPublicacion, idEditorial from scbiblioteca.libro;";
		
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
                libros.add(l);
            }
 
        } catch (SQLException exception) {
            System.err.println("Error al CARGAR DATOS (Libro)" + exception);
        }
		return libros;
	}
	
}
