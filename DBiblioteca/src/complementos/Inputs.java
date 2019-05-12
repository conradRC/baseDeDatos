package complementos;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Inputs extends JPanel {
	private JComponent componente;
	private JLabel label;
	private String nombre;
	private GridBagConstraints gbclb, gbcComp;
	public Inputs(String nombre, JComponent componente) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[]{1.0};
		setLayout(gridBagLayout);
		
		this.setBackground(Color.WHITE);
		this.nombre = nombre;
		
		label = new JLabel(nombre);
		gbclb = new GridBagConstraints();
		gbclb.anchor = GridBagConstraints.EAST;
		gbclb.insets = new Insets(0, 0, 0, 5);
		gbclb.gridx = 0;
		gbclb.gridy = 0;
		add(label, gbclb);
		
		gbcComp = new GridBagConstraints();
		gbcComp.ipady = 5;
		gbcComp.ipady = 10;
		gbcComp.insets = new Insets(0, 0, 0, 5);
		gbcComp.anchor = GridBagConstraints.EAST;
		gbcComp.gridx = 1;
		gbcComp.gridy = 0;
		add(componente, gbcComp);	
	}
	
	public JComponent getComponente() {
		return componente;
	}

	public JLabel getLabel() {
		return label;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


}
