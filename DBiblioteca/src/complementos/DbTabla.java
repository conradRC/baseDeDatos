package complementos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel; 

public class DbTabla extends JPanel {
	public JTable tabla;
	public DefaultTableModel tableModel;
	public DbTabla(String []  colname){
		super (new GridLayout(1,0));
		setBackground(Color.white); 
		Object [][] data = {};
		
		tableModel= new DefaultTableModel(data, colname){
            @Override
            public boolean isCellEditable(int row, int column) {
                return !(this.getColumnClass(column).equals(java.lang.String.class));
            }
			};
		
		tabla = new JTable (tableModel);
		
		tabla.setPreferredScrollableViewportSize(new Dimension(100,50));		
		JScrollPane scrollpane = new JScrollPane(tabla);		
		add(scrollpane);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}
	
	//Método para agregar datos en la fila especificada
	public void agregarDatosTabla(Object [] datos) {
		tableModel.addRow(datos);
	}
	//Método para eliminar un dato de la fila especificada
	public void removerDatosTabla(int fila) {
		tableModel.removeRow(fila);
	}
	
	
}