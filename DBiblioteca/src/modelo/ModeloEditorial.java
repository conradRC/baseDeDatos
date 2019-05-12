package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import complementos.Conectiondb;

public class ModeloEditorial {
	private Conectiondb conectiondb;
	private String db ="dbbiblioteca";
	
	
	public ModeloEditorial() {
		conectiondb = new Conectiondb(db, "127.0.0.1");
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
	
	public List<Editorial> listEditorial(){
		PreparedStatement ps;
	        ResultSet rs;
	       		String consultaSQL = "Select idEditorial, nombreeditorial from scbiblioteca.editorial;";
			
			List<Editorial> editoriales = new ArrayList<Editorial>();
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

}
