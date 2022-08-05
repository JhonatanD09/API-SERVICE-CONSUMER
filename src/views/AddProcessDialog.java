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

    public AddProcessDialog(ActionListener listener, boolean isEditing) {
        setInfo();
        addProcessPanel = new AddPanel(listener, isEditing);
        add(addProcessPanel);
    }

    private void setInfo(){
        setSize(400, 400);
        setModal(true);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public String[] data() {
    	return addProcessPanel.data();
    }
    
    public void setInitialInfo(PublicService publicService){
        addProcessPanel.setInitialInfo(publicService);
    }
}
