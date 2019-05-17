package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import complementos.DbTabla;
import complementos.Inputs;
import controlador.ControladorLibro;
import modelo.ModeloLibro;

import java.awt.FlowLayout;

public class VistaLibro extends JPanel{
	
	private GridBagConstraints gbcInferior,gbcSuperior,gbcPanelBotones;
	private JPanel panelSuperior;
	private JButton btnAdd,btnDel,btnUpd,btnExit,btnEditorial;
	private DbTabla tabla;
	protected JTextField campo1,campo2,campo3,campo4,campo5;
	protected JComboBox<Integer> comboBox;
	protected JComboBox<String> comboBoAutor;
	
	private static VistaLibro libro;
	private static JFrame frame;
	
	public static VistaLibro getVistaLibro() {
		if(libro==null) libro = new VistaLibro();
		return libro;
	}

	Font font = new Font("Tahoma",Font.PLAIN,15);
	private JPanel panelBotones;
	
	
	
	public VistaLibro() {
		setBackground(Color.WHITE);
		
		GridBagLayout gbl = new GridBagLayout();
		gbl.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		gbl.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0};
		setLayout(gbl);
		
		
		//**************Panel superior*************//
		
		panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.WHITE);
		gbcSuperior = new GridBagConstraints();
		gbcSuperior.anchor = GridBagConstraints.WEST;
		gbcSuperior.ipady = 20;
		gbcSuperior.gridwidth = 5;
		gbcSuperior.fill = GridBagConstraints.VERTICAL;
		gbcSuperior.insets = new Insets(15, 10, 20, 0);
		gbcSuperior.gridx = 0;
		gbcSuperior.gridy = 0;
		add(panelSuperior, gbcSuperior);
		
		panelSuperior.setLayout(new GridLayout(2,3,5,5));
		
		//******************Inputs*************//
		
		campo1 = new JTextField();
		campo2 = new JTextField();
		campo3 = new JTextField();
		campo4 = new JTextField();
		campo5 = new JTextField();
		comboBox = new JComboBox<Integer>();
		comboBoAutor = new JComboBox<String>(); 
		
		//Titulos de las Etiquetas
		String [] name= {"ISBN : ", "Titulo : ","Edición : ", "Publicación : "};
		//Campos 
		JTextField [] text= {campo1,campo2,campo3,campo4};
		
		for (byte c = 0; c < 4; c++) {
			text[c].setColumns(12);
			text[c].setBackground(new Color(238,238,238));
			text[c].setFont(new Font("Tahoma",Font.PLAIN,14));
			text[c].setPreferredSize(new Dimension(10,20));
			panelSuperior.add(new Inputs(name[c], text[c]));
		}
		comboBox.setPreferredSize(new Dimension(147,20));
		panelSuperior.add(new Inputs("ID Editorial : ", comboBox));
		
		comboBoAutor.setPreferredSize(new Dimension(147,20));
		panelSuperior.add(new Inputs("Autor : ", comboBoAutor));
		
		
		
		//******************Panel inferior (TABLA)******************//
		String[] colname = {"ISBN", "TITULO", "EDICIÓN", "AÑO DE PUBLICACIÓN","ID EDITORIAL","AUTOR"};
		
		tabla = new DbTabla(colname);
		gbcInferior = new GridBagConstraints();
		gbcInferior.gridwidth = 5;
		gbcInferior.fill = GridBagConstraints.BOTH;
		gbcInferior.insets = new Insets(0, 10, 5, 10);
		gbcInferior.gridx = 0;
		gbcInferior.gridy = 2;
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
		gbcPanelBotones.gridy = 3;
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
		
		btnEditorial = new JButton("Agregar Editorial");
		btnEditorial.setFocusable(false);
		btnEditorial.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbcEditorial = new GridBagConstraints();
		gbcEditorial.insets = new Insets(0, 0, 5, 10);
		gbcEditorial.anchor = GridBagConstraints.EAST;
		gbcEditorial.gridx = 4;
		gbcEditorial.gridy = 3;
		add(btnEditorial, gbcEditorial);
	}
	
	
	//************************getBotones************************/
	public JButton getBtnAdd()			{return btnAdd; }
	public JButton getBtnDel()			{return btnDel; }
	public JButton getBtnUpd()			{return btnUpd; }
	public JButton getBtnEexit()		{return btnExit; }
	public JButton getBtnEditorial()	{return btnEditorial; }
	
	
	//***********************setCampos***********************//
	public void setCampo1(String n) { campo1.setText(n); }
	public void setCampo2(String n) { campo2.setText(n); }
	public void setCampo3(String n) { campo3.setText(n); }
	public void setCampo4(String n) { campo4.setText(n); }
	//public void setCampo5(String n) { campo5.setText(n); }
	
	public void setComboBox(String n) { comboBox.setSelectedItem(n); }
	public void setComboBoAutor(String n) {comboBoAutor.setSelectedItem(n); }
	
	//****************************getCampos*****************************//
	public String getCampo1() { return campo1.getText(); }
	public String getCampo2() { return campo2.getText(); }
	public String getCampo3() { return campo3.getText(); }
	public String getCampo4() { return campo4.getText(); }
	public String getComboBox() { return String.valueOf(comboBox.getSelectedItem()); }
	public String getComboBoAutor() {return String.valueOf(comboBoAutor.getSelectedItem());}


	
	//****************************getTabla*****************************//
	public DbTabla getTabla() { return tabla; }
	
	
	public void agregarItem(int n) {
		comboBox.addItem(n);
	}
	
	public void agregarItemA(String n) {
		comboBoAutor.addItem(n);
	}
	
	
	//*********************Controlador - Libro************************//
	 public void conectaControlador( ControladorLibro c  ){
	        btnAdd.addActionListener(c);
	        btnAdd.setActionCommand("INSERTAR");
	 
	        btnDel.addActionListener(c);
	        btnDel.setActionCommand("BORRAR");
	 
	        btnUpd.addActionListener(c);
	        btnUpd.setActionCommand("MODIFICAR");
			
			btnExit.addActionListener(c);
			btnExit.setActionCommand("SALIR");
	 
			btnEditorial.addActionListener(c);
			btnEditorial.setActionCommand("EDITORIAL");
			
	        tabla.addMouseListener(c);
	  }
	
	 
	 public static JFrame getFrame() {
			return frame;
	 }
	 
		
		
}

