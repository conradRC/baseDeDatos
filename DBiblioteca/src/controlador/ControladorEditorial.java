package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.util.List;
import complementos.Conectiondb;
import modelo.Editorial;
import modelo.Libro;
import modelo.ModeloEditorial;
import vista.VistaEditorial;
import vista.VistaLibro;

public class ControladorEditorial implements ActionListener, MouseListener{

	private VistaEditorial vistaEditorial;
	private VistaLibro vistaLibro;
	private ModeloEditorial modeloEditorial;
	private Editorial editorial;
	private Conectiondb conexion;
	
	
	public ControladorEditorial(VistaEditorial vista, ModeloEditorial modelo) {
		this.vistaEditorial = vista;
		this.modeloEditorial = modelo;
		cargarTabla();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Editorial editorial;
		String comando  = arg0.getActionCommand();
		
		switch (comando) {
			case "INSERTAR":
			editorial = new Editorial(Integer.parseInt(vistaEditorial.getCampo1()), vistaEditorial.getCampo2());
			modeloEditorial.insertEditorial(editorial);
			break;
			
			case "BORRAR":
				int filaPulsada = vistaEditorial.getTabla().tabla.getSelectedRow();
				 if(filaPulsada>=0){
					 editorial = new Editorial();
	                	int idEditorial = (int) vistaEditorial.getTabla().tableModel.getValueAt(filaPulsada,0);
	                	
						editorial.setIdEditorial(idEditorial);
						modeloEditorial.deleteEditorial(editorial);
	             }
				 break;
			case "MODIFICAR":
				filaPulsada = vistaEditorial.getTabla().tabla.getSelectedRow();
				 if(filaPulsada>=0){
					 editorial = new Editorial();
					 int idEditorial = Integer.parseInt((String) vistaEditorial.getTabla().tableModel.getValueAt(filaPulsada,0));
					 editorial.setIdEditorial(idEditorial);
					 editorial.setNombreeditorial(vistaEditorial.getCampo2()); 
					 modeloEditorial.updateEditorial(editorial);
				 }
				break;
				
			case "SALIR":
				//conexion.closeConexion();
				vistaEditorial.getFrame().dispose();
				break;
				
			default:
				  System.err.println("Comando no definido - Editorial");
				break;
			}	
		limpia();
		cargarTabla();
	}
	
	private void limpia() {
		vistaEditorial.setCampo1("");
		vistaEditorial.setCampo2("");
	}
	
	protected void cargarTabla(){
		  Vector<Object> fila;
		  
		  for(int i = vistaEditorial.getTabla().tableModel.getRowCount(); i>0; i--)
			  vistaEditorial.getTabla().tableModel.removeRow(i-1);
		  
		  List<Editorial> editoriales = modeloEditorial.listEditorial();
		  
	       for(Editorial editorial: editoriales) {
	    	   fila = new Vector<Object>();
	    	   fila.add(editorial.getIdEditorial());
	    	   fila.add(editorial.getNombreeditorial());

	           //vistaLibro.agregarItem(editorial.getIdEditorial());
	           
	    	   vistaEditorial.getTabla().tableModel.addRow(fila);
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
