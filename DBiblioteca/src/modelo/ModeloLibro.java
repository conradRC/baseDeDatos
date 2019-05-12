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
            ps.setInt	(3, l.getAnioPublicacion());
            ps.setInt	(4, l.getEdicion());
            ps.setInt	(5, l.getIdEditorial());
            //Ejecutar el comando insert
            ps.executeUpdate();
            return true;
        }catch (SQLException exception) {
            System.err.println("Error en la INSERCIÓN  (Libro)" + exception );
			return false;
        }
	}
	
	/*public boolean deleteLibro(Libro l){
        //Objeto para ejecutar los procedimientos almacenados en la base de datos
        PreparedStatement ps;
		String sqlDeleteLibro = "delete from scbiblioteca.libro where isbn  = ?;";
        try{
            //Preparar la llamada
            ps  = conectiondb.getConexion().prepareStatement(sqlDeleteLibro);

            //Indicar qué información se pasa al Statement
            ps.setString(1, l.getIsbn());
            //Ejecutar el procedimiento
            ps.executeUpdate();
            //System.out.println(this.view.dtm.getValueAt(filaPulsada, 0));
	    return true;
        }catch (SQLException exception) {
            System.err.println("Error en el BORRADO "+ exception);
			return false;
        }
	}
	
	public boolean updateLibro(Libro l){
        //Objeto para ejecutar los procedimientos almacenados en la base de datos
        PreparedStatement ps;
		String sqlUpdateLibro = "update scbiblioteca.libro set titulo = ?, edicion = ?, anioPublicacion = ?, idEditorial = ? where isbn = ?;";
        try{
            //Preparar la llamada
            ps  = conectiondb.getConexion().prepareStatement(sqlUpdateLibro);
                
            //Indicar qué información se pasa al procedimiento
            ps.setString(1, l.getIsbn());
            ps.setString(2, l.getTitulo());
            ps.setInt	(3, l.getAnioPublicacion());
            ps.setInt	(4, l.getEdicion());
            ps.setInt	(5, l.getIdEditorial());
            //Ejecutar el procedimiento
            ps.executeUpdate();
            //System.out.println(this.view.dtm.getValueAt(filaPulsada, 0));
			return true;
        }catch (SQLException exception) {
            System.err.println("Error en la MODIFICACION " + exception);
			return false;
        }
	}
	
	public Libro selectLibro(Libro l){
        //Objeto para ejecutar los procedimientos almacenados en la base de datos
        PreparedStatement ps;
        //Objeto para recoger los datos devueltos por el procedimiento almacenado
        ResultSet rs;
		
		// Objeto con el cliente encontrado
		Libro libroEncontrado= null;
		
		
		String sqlConsulta = "select isbn, titulo, edicion, anioPublicacion, idEditorial from scbiblioteca.libro where isbn = ?;";
        try{
            //Preparar la llamada
            ps  = conectiondb.getConexion().prepareStatement(sqlConsulta);
                       
            //Indicar qué información se pasa al procedimiento
            ps.setString(1, l.getIsbn());
            //Ejecutar el procedimiento
            rs  = ps.executeQuery();
            //Cargar los datos devueltos en los cuadros de texto
            if(rs.next()){
            	libroEncontrado = new Libro(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));  
            }
			
            //System.out.println(this.view.dtm.getValueAt(filaPulsada, 0));
        }catch (SQLException exception) {
            System.err.println("Error al CARGAR UN ESTUDIANTE");
        }
		return libroEncontrado;
	}
	*/
	
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
	
	public Conectiondb getConectiondb() {
		return conectiondb;
	}

}
