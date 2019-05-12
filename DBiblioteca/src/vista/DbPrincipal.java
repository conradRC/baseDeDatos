package vista;

import javax.swing.JFrame;
import controlador.ControladorLibro;
import modelo.ModeloLibro;
import vista.VistaLibro;

public class DbPrincipal extends JFrame{
	//********************** Frame ***************************//
	public DbPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		VistaLibro vista = new VistaLibro();
		ModeloLibro modelo = new ModeloLibro();
		ControladorLibro control = new ControladorLibro(vista,modelo);
		vista.conectaControlador(control);
			
		add(vista);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		System.out.println("Loading ...");
		new DbPrincipal();
	}
	
}
