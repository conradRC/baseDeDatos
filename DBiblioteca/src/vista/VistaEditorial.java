package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import complementos.DbTabla;
import complementos.Inputs;
import controlador.ControladorEditorial;
import modelo.ModeloEditorial;
import modelo.ModeloLibro;

public class VistaEditorial extends JPanel {

	private GridBagConstraints gbcInferior,gbcSuperior,gbcPanelBotones;
	private JPanel panelSuperior;
	private JButton btnAdd,btnDel,btnUpd,btnExit;
	private DbTabla tabla;
	protected JTextField campo1,campo2;
	Font font = new Font("Tahoma",Font.PLAIN,15);
	private JPanel panelBotones;
	private static JFrame frame;
	
	public VistaEditorial() {
		setBackground(Color.WHITE);
		
	
	GridBagLayout gbl = new GridBagLayout();
	gbl.rowWeights = new double[]{0.0, 1.0, 0.0};
	gbl.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0};
	setLayout(gbl);
	
	//**************Panel superior*************//
	
	panelSuperior = new JPanel();
	panelSuperior.setBackground(Color.WHITE);
	//Posición
	gbcSuperior = new GridBagConstraints();
	gbcSuperior.anchor = GridBagConstraints.WEST;
	gbcSuperior.ipady = 20;
	gbcSuperior.gridwidth = 5;
	gbcSuperior.fill = GridBagConstraints.VERTICAL;
	gbcSuperior.insets = new Insets(15, 10, 0, 0);
	gbcSuperior.gridx = 0;
	gbcSuperior.gridy = 0;
	
	add(panelSuperior, gbcSuperior);
	
	panelSuperior.setLayout(new FlowLayout());
	
	
	//******************Inputs*************//
	
	//Etiquetas
	campo1 = new JTextField();
	campo2 = new JTextField();
	String [] name= {"ID : ", "Nombre :"};
	//Campos 
	JTextField [] text= {campo1,campo2};
	
	for (byte c = 0; c < 2; c++) {
		text[c].setColumns(12);
		text[c].setBackground(new Color(238,238,238));
		text[c].setFont(new Font("Tahoma",Font.PLAIN,14));
		text[c].setPreferredSize(new Dimension(10,20));
		panelSuperior.add(new Inputs(name[c], text[c]));
	}
	
	//******************Panel inferior (TABLA)******************//
	String[] colname = {"ID", "Nombre"};
	
	tabla = new DbTabla(colname);
	gbcInferior = new GridBagConstraints();
	gbcInferior.gridwidth = 5;
	gbcInferior.fill = GridBagConstraints.BOTH;
	gbcInferior.insets = new Insets(0, 10, 5, 10);
	gbcInferior.gridx = 0;
	gbcInferior.gridy = 1;
	add(tabla, gbcInferior);
	
	//***********************Panel de Botones********************//
	panelBotones = new JPanel();
	FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
	flowLayout.setAlignment(FlowLayout.LEFT);
	panelBotones.setBackground(Color.WHITE);
	gbcPanelBotones = new GridBagConstraints();
	gbcPanelBotones.gridwidth = 4;
	gbcPanelBotones.insets = new Insets(0, 10, 5, 10);
	gbcPanelBotones.fill = GridBagConstraints.BOTH;
	gbcPanelBotones.gridx = 0;
	gbcPanelBotones.gridy = 2;
	add(panelBotones, gbcPanelBotones);
	
	//*******************Botones inferiores***************//
	
	btnAdd 	= new JButton("Agregar");
	btnDel 	= new JButton("Eliminar");
	btnUpd 	= new JButton("Actualizar");
	btnExit	= new JButton("Salir");
	
	JButton [] botones = {btnAdd,btnDel,btnUpd,btnExit};	
	
	for (int c = 0; c < botones.length; c++) {
		botones[c].setFocusable(false);
		botones[c].setBackground(Color.WHITE);
		panelBotones.add(botones[c]);
	}
}


//************************getBotones************************/
public JButton getBtnAdd()			{return btnAdd; }
public JButton getBtnDel()			{return btnDel; }
public JButton getBtnUpd()			{return btnUpd; }
public JButton getBtnEexit()		{return btnExit; }


//***********************setCampos***********************//
public void setCampo1(String n) { campo1.setText(n); }
public void setCampo2(String n) { campo2.setText(n); }


//****************************getCampos*****************************//
public String getCampo1() { return campo1.getText(); }
public String getCampo2() { return campo2.getText(); }

//****************************getTabla*****************************//
public DbTabla getTabla() { return tabla; }


//*********************Controlador - Libro************************//
 public void conectaControlador( ControladorEditorial c  ){
        btnAdd.addActionListener(c);
        btnAdd.setActionCommand("INSERTAR");
 
        btnDel.addActionListener(c);
        btnDel.setActionCommand("BORRAR");
 
        btnUpd.addActionListener(c);
        btnUpd.setActionCommand("MODIFICAR");
		
		btnExit.addActionListener(c);
		btnExit.setActionCommand("SALIR");
 
        tabla.addMouseListener(c);
  }

  public static JFrame getFrame() {
		return frame;
  }

 //********************** Inir Editorial ***************************//
 	public static void initEditorial() {
 		frame = new JFrame();
 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		frame.setSize(700, 400);
 		
 		VistaEditorial vista = new VistaEditorial();
 		ModeloEditorial modelo = new ModeloEditorial();
 		ControladorEditorial controlador = new ControladorEditorial(vista, modelo);
 		vista.conectaControlador(controlador);
	
 		frame.getContentPane().add(vista);
 		frame.setVisible(true);
 	}




}
