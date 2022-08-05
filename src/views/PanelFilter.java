package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import presenters.Actions;

public class PanelFilter extends MyGridPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> filtersBox;
	private JTextField dataFilterField;
	
	/**
	 * Contructor de la clase, e inicializa y acomoda los componentes
	 * @param l
	 */
	public PanelFilter(ActionListener l) {
		
		setPreferredSize(new Dimension(200,200));
		setBorder(new EmptyBorder(0, 1, 1, 0));
		setBackground(Color.WHITE);
		
		filtersBox = new JComboBox<String>();
		dataFilterField = new JTextField();
		
		filtersBox.addItem("Año");
		filtersBox.addItem("Tipo");
		filtersBox.addItem("Origen");
		filtersBox.addItem("Normativa");
		filtersBox.addItem("Todos");
		
		JLabel type = new JLabel("Tipo de filtro");
		editLabel(type);
		addComponent(type, 0,1, 13, 1);
		addComponent(filtersBox, 0,2, 13, 1);
		addComponent(new JLabel(), 0,3, 13, 4);
		JLabel value = new JLabel("Valor a filtrar");
		editLabel(value);
		addComponent(value, 0,4, 13, 1);
		addComponent(dataFilterField, 0,5, 13, 1);
		addComponent(new JLabel(), 0,6, 13, 1);
		addComponent(createBtn("Filtrar",Color.decode("#648cda"),l,Actions.FILTER.name()), 0,7, 13, 1);
	}
	
	/**
	 * Crea boton personalizado
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
	
	/**
	 * Cmbia la fuente a un jlabel
	 * @param jLabel
	 */
	public void editLabel(JLabel jLabel){
		jLabel.setFont(new Font("Roboto", Font.BOLD, 15));
	}
	
	/**
	 * @return Los datos para aplicar el filtro
	 */
	public String[] dataFilter(){
		if (((String)filtersBox.getSelectedItem()).equals("Todos")) {
			dataFilterField.setText("");
		}
		return new String[] {(String)filtersBox.getSelectedItem(),dataFilterField.getText()};
	}
}
