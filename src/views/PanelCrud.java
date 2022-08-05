package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import presenters.Actions;
import javax.swing.JButton;
import javax.swing.JLabel;

public class PanelCrud extends MyGridPanel{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Contructor de la clase
	 * @param listener
	 */
	public PanelCrud(ActionListener listener) {
		setPreferredSize(new Dimension(300, 100));
		setBackground(Color.WHITE);
		initComponents(listener);
	}

	/**
	 * Agrega los botones de crud
	 * @param listener
	 */
	private void initComponents(ActionListener listener) {
		addComponent(new JLabel(), 0,0,13,1);
		addComponent(createBtn("Agregar",Color.decode("#4BD358"), listener,Actions.ADD.name()), 0, 1, 13, 1);
		addComponent(new JLabel(), 0,2,13,0.5);
		addComponent(createBtn("Editar",Color.decode("#FFB520"), listener, Actions.EDIT.name()), 0, 3, 13, 1);
		addComponent(new JLabel(), 0,4,13,0.5);
		addComponent(createBtn("Eliminar",Color.decode("#F26953"), listener, Actions.DELETE.name()), 0, 5, 13, 1);
		addComponent(new JLabel(), 0,6,13,1);
	}
	
	
	/**
	 * Metodo para crear un boton personalizado
	 */
	private JButton createBtn(String txt, Color color, ActionListener listener, String command){
        JButton btn = new JButton(txt);
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setFocusable(false);
        btn.setFont(new Font("Roboto", Font.BOLD, 25));
        btn.addActionListener(listener);
        btn.setActionCommand(command);
        return btn;
    }

}
