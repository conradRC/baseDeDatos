package vista;

import javax.swing.JFrame;

import controlador.ControladorLibro;
import modelo.ModeloLibro;

    public class DPrincipal {
    	
    	public static void main(String[] args) {
    	JFrame frame = new JFrame();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(800, 600);
    	
    	VistaLibro vista = new VistaLibro();
    	
    	ModeloLibro modelo = new ModeloLibro(vista);
    	ControladorLibro control = new ControladorLibro(vista,modelo);
    	
    	vista.conectaControlador(control);
    	
    	frame.add(vista);
    	frame.setVisible(true);
    }
}

