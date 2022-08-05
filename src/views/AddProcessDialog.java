package views;


import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import models.PublicService;


public class AddProcessDialog extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AddPanel addProcessPanel;

	/**
	 * Contructor de la classe, llama al panel de inputs y lo agrega en el dialog
	 * @param listener
	 * @param isEditing
	 */
    public AddProcessDialog(ActionListener listener, boolean isEditing) {
        setInfo();
        addProcessPanel = new AddPanel(listener, isEditing);
        add(addProcessPanel);
    }

    /**
     * Especificalos parametros o configuraciones del input
     */
    private void setInfo(){
        setSize(400, 400);
        setModal(true);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /**
     * Trae los datos de los inputs
     * @return
     */
    public String[] data() {
    	return addProcessPanel.data();
    }
    
    /**
     * Metodo fachada de set inormacion para editar
     * @param publicService
     */
    public void setInitialInfo(PublicService publicService){
        addProcessPanel.setInitialInfo(publicService);
    }
}
