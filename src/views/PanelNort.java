package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelNort extends JPanel{
	

	private static final long serialVersionUID = 1L;
	
	private static final String IMG_URL = "/img/logo.png";
	private static final String TITLE = "Tarifas del servicio de transporte público individual de pasajeros en Acacías";

	/**
	 * Metodo constructor de la clase, agrega la imagen y el titulo
	 */
	public PanelNort() {
		setBackground(Color.decode("#3267cc"));
		setPreferredSize(new Dimension(300,140));
		setLayout(new BorderLayout());
		
		JLabel picLabel = new JLabel(new ImageIcon(getClass().getResource(IMG_URL)));
		JPanel img = new JPanel();
		img.add(picLabel);
		
		add(picLabel, BorderLayout.WEST);
		
		JLabel title = new JLabel(TITLE, SwingConstants.CENTER);
		editLabel(title);
		add(title, BorderLayout.CENTER);
	}

	/**
	 * metodo para editar un jlabel
	 * @param jLabel
	 */
	public void editLabel(JLabel jLabel){
		jLabel.setForeground(Color.WHITE);
		jLabel.setFont(new Font("Roboto", Font.BOLD, 25));
	}
	
}
