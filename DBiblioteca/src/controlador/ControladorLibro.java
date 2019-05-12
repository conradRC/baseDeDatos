package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import complementos.Conectiondb;
import modelo.Libro;
import modelo.ModeloLibro;
import vista.DbPrincipal;
import vista.VistaEditorial;
import vista.VistaLibro;

public class ControladorLibro implements ActionListener, MouseListener{

	private VistaLibro vistaLibro;
	private ModeloLibro modeloLibro;
	private Libro libro;
	private Conectiondb conexion;
	
	public ControladorLibro(VistaLibro vista, ModeloLibro modelo) {
		this.vistaLibro = vista;
		this.modeloLibro = modelo;
		cargarTabla();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Libro libro;
		String comando  = arg0.getActionCommand();
		
		switch (comando) {
		case "INSERTAR":
			
			libro = new Libro(this.vistaLibro.getCampo1(),vistaLibro.getCampo2(), Integer.parseInt(vistaLibro.getCampo3()), 
								Integer.parseInt(vistaLibro.getCampo4()), Integer.parseInt(vistaLibro.getCampo5()));
			 modeloLibro.insertLibro(libro);
			break;
			
		case "BORRAR":
			int filaPulsada = vistaLibro.getTabla().tabla.getSelectedRow();
			 if(filaPulsada>=0){
				 libro = new Libro();
                	String isbn = (String) vistaLibro.getTabla().tableModel.getValueAt(filaPulsada,0);
					libro.setIsbn(isbn);
					modeloLibro.deleteLibro(libro);
             }
			 
			break;
		case "MODIFICAR":
			filaPulsada = vistaLibro.getTabla().tabla.getSelectedRow();
			 if(filaPulsada>=0){
				 libro = new Libro();
				 String isbn = (String) vistaLibro.getTabla().tableModel.getValueAt(filaPulsada,0);
				 libro.setIsbn(isbn);
				 libro.setTitulo(vistaLibro.getCampo2());
				 libro.setEdicion(Integer.parseInt(vistaLibro.getCampo3()));
				 libro.setAnioPublicacion(Integer.parseInt(vistaLibro.getCampo4()));
				 libro.setIdEditorial(Integer.parseInt(vistaLibro.getCampo5()));
				 modeloLibro.updateLibro(libro);
			 }
			
			break;
		case "SALIR":
			 conexion.closeConexion();
			 //principal.dispose();
			break;
			
		case "EDITORIAL":
			VistaEditorial.initEditorial();
			break;
		default:
			  System.err.println("Comando no definido");
			break;
		}
		
		limpia();
		cargarTabla();
	}
	
	private void limpia(){
        vistaLibro.setCampo1("");
        vistaLibro.setCampo2("");
        vistaLibro.setCampo3("");
        vistaLibro.setCampo4("");
        vistaLibro.setCampo5("");
    }
	
	
	protected void cargarTabla(){
        Vector<Object> fila;
        
        for(int i = vistaLibro.getTabla().tableModel.getRowCount(); i>0; i--){
        	vistaLibro.getTabla().tableModel.removeRow(i-1);
        }
        
		List<Libro> libros = modeloLibro.listLibro();
        //public Libro(String isbn, String titulo, int edicion, int anioPublicacion, int idEditorial) {
        for(Libro libro: libros){
                fila    = new Vector<Object>();
                fila.add(libro.getIsbn());
                fila.add(libro.getTitulo());
                fila.add(libro.getEdicion());
                fila.add(libro.getAnioPublicacion());
                fila.add(libro.getIdEditorial());
                
                vistaLibro.getTabla().tableModel.addRow(fila);
            }
    }
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
