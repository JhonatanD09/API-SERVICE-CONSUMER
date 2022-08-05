package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import models.PublicService;
import presenters.Actions;


public class AddPanel extends MyGridPanel{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField year,normative,type,origin,destination,rate,surchage;
    private JButton addBtn;

    /**
     * Contructor de la clase
     * @param listener
     * @param isEditing
     */
    public AddPanel(ActionListener listener, boolean isEditing) {
        setBackground(Color.decode("#FDFEFE"));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        initComponents(listener, isEditing);
    }

    /**
     * Inicia los componentes de agregae
     * @param listener
     * @param isEditing
     */
    private void initComponents(ActionListener listener, boolean isEditing) {
        initTitle(isEditing);
        year();
        normative();
        type();
        origin();
        destination();
        rate();
        surchage();
        if(isEditing){
            initButtons(listener, Actions.ACEPT_EDIT.toString(), isEditing);
        }else{
            initButtons(listener, Actions.ACEPT_ADD.toString(), isEditing);
        }
    }

    /**
     * Los metodos siguientes agregan componentes al panel en diferentes posiciones
     */
    
    private void initTitle(boolean isEditing){
        String title = isEditing ? "Editar" : "Agregar";
        JTextArea titleTa = new JTextArea();
        titleTa.setText(title);
        titleTa.setEditable(false);
        titleTa.setFont(new Font("Arial", Font.BOLD, 16));
        addComponent(new JLabel(" "), 0, 0, 11, 0.1);
        addComponent(titleTa, 4, 1, 4, 0.2);
        addComponent(new JLabel(" "), 0, 3, 11, 0.1);
    }

    private void year(){
        JLabel nameLb = createLb("   Año:", new Font("Arial", Font.BOLD, 14));
        addComponent(nameLb, 2, 5, 2, 0.1);
        year = new JTextField();
        year.setText("");
        addComponent(year, 5, 5, 5, 0.1);
        addComponent(new JLabel(" "), 0, 6, 11, 0.1);
    }

    private void normative() {
        JLabel timeLb = createLb("   Normativa: ", new Font("Arial", Font.BOLD, 14));
        addComponent(timeLb, 2, 7, 2, 0.1);
        normative = new JTextField();
        normative.setText("");
        addComponent(normative, 5, 7, 5, 0.1);
        addComponent(new JLabel(" "), 0, 8, 11, 0.1);
    }

    private void type(){
        JLabel sizeLb = createLb("   Tipo: ", new Font("Arial", Font.BOLD, 14));
        addComponent(sizeLb, 2, 9, 2, 0.1);
        type = new JTextField();
        type.setText("");
        addComponent(type, 5, 9, 5, 0.1);
        addComponent(new JLabel(" "), 0, 10, 11, 0.1);
    }
    
    private void origin(){
        JLabel sizeLb = createLb("   Origen: ", new Font("Arial", Font.BOLD, 14));
        addComponent(sizeLb, 2, 11, 2, 0.1);
        origin = new JTextField();
        origin.setText("");
        addComponent(origin, 5, 11, 5, 0.1);
        addComponent(new JLabel(" "), 0, 12, 11, 0.1);
    }
    
    private void destination(){
        JLabel sizeLb = createLb("   Destino: ", new Font("Arial", Font.BOLD, 14));
        addComponent(sizeLb, 2, 13, 2, 0.1);
        destination = new JTextField();
        destination.setText("");
        addComponent(destination, 5, 13, 5, 0.1);
        addComponent(new JLabel(" "), 0, 14, 11, 0.1);
    }

    private void rate(){
        JLabel sizeLb = createLb("   Tarifa: ", new Font("Arial", Font.BOLD, 14));
        addComponent(sizeLb, 2, 15, 2, 0.1);
        rate = new JTextField();
        rate.setText("");
        addComponent(rate, 5, 15, 5, 0.1);
        addComponent(new JLabel(" "), 0, 16, 11, 0.1);
    }
    
    private void surchage(){
        JLabel sizeLb = createLb("   Sobrecargo: ", new Font("Arial", Font.BOLD, 14));
        addComponent(sizeLb, 2, 17, 2, 0.1);
        surchage = new JTextField();
        surchage.setText("");
        addComponent(surchage, 5, 17, 5, 0.1);
        addComponent(new JLabel(" "), 0, 18, 11, 0.1);
    }

    /**
     * Agregar boton a la vista
     */
    private void initButtons(ActionListener listener, String acceptEvent, boolean isEditing){
        String addBtnTxt = isEditing ? "Editar" : "Agregar";
        addBtn = createBtn(addBtnTxt, isEditing ?Color.decode("#FFB520"):Color.decode("#4BD358"), listener, acceptEvent);
        addComponent(addBtn, 3, 20, 2, 0.12);
    }
    
    /**
     * Metodo para crear labels y editar estilos
     */
    private JLabel createLb(String txt, Font font){
        JLabel lb = new JLabel(txt);
        lb.setFont(font);
        return lb;
    }

    /**
     * Metodo para crear botones y editar estilos
     */
    private JButton createBtn(String txt, Color color, ActionListener listener, String command){
        JButton btn = new JButton(txt);
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setFont(new Font("Arial", Font.BOLD, 15));
        btn.addActionListener(listener);
        btn.setActionCommand(command);
        return btn;
    }



 
	public String[] data() {
		return new String[] {year.getText(),normative.getText(),type.getText(),origin.getText(),destination.getText(),rate.getText(),surchage.getText()};
	}

	/**
	 * Setea la informacion en caso de que el panel sea editar
	 * @param publicService info a setear
	 */
	public void setInitialInfo(PublicService publicService) {
		year.setText(publicService.getYear());
		normative.setText(publicService.getNormative());
		type.setText(publicService.getType());
		origin.setText(publicService.getOrigin());
		destination.setText(publicService.getDestination());
		rate.setText(""+publicService.getRate());
		surchage.setText(publicService.getSurcharge());
	}
}