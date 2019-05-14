package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import complementos.Conectiondb;
import vista.VistaLibro;

public class ModeloEditorial {
	private Conectiondb conectiondb;
	private String db ="dbbiblioteca";
	private VistaLibro vista;
	private VistaLibro vistaLibro;
	private List<Editorial> editoriales;
	
	private static ModeloEditorial libro;
	
	public static ModeloEditorial getModeloEditorial() {
		if(libro == null) libro = new ModeloEditorial();
		return libro;
	}
	
	
	
	public ModeloEditorial() {
		conectiondb = new Conectiondb(db, "127.0.0.1");
		this.vistaLibro= vistaLibro;
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
	        } catch (SQLException exception) {
	            System.err.println("Error al CARGAR DATOS (Editorial) " + exception);
	        }
			return editoriales;
		}
	
	
	public void getEditorial() {
		for (int c = 0; c < editoriales.size(); c++)
        	vistaLibro.agregarItem(editoriales.get(c).getIdEditorial());
	}
}
