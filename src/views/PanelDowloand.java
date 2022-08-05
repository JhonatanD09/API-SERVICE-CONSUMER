package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import presenters.Actions;

public class PanelDowloand extends MyGridPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase y agregar la imagen y el titulo al boton de descargar
	 * @param l
	 */
	public PanelDowloand(ActionListener l) {
		
		Image img= new ImageIcon(getClass().getResource("/img/dowloand.png")).getImage();
		ImageIcon img2=new ImageIcon(img.getScaledInstance(170, 100, Image.SCALE_SMOOTH));

		JButton dowloandButton= new JButton(img2);
		dowloandButton.setActionCommand(Actions.DOWLOAND.name());
		dowloandButton.addActionListener(l);
		dowloandButton.setBackground(Color.WHITE);
		dowloandButton.setFocusable(false);
		dowloandButton.setBorder(null);
		JLabel dlwJLabel = new JLabel("Descargar datos", SwingConstants.CENTER);
		editLabel(dlwJLabel);
		addComponent(dlwJLabel, 0, 0, 13,0.1);
		addComponent(dowloandButton, 0, 2, 13,0.5);
		addComponent(new JLabel(), 0, 3, 13,5);
	}
	
	/**
	 * Metodo para editar opciones de un jlabel
	 */
	public void editLabel(JLabel jLabel){
		jLabel.setFont(new Font("Roboto", Font.BOLD, 15));
		setOpaque(true);
		setBackground(Color.WHITE);
	}
	
}
