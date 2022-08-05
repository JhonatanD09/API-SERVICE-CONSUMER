package presenters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import models.Manager;
import models.PublicService;
import views.MainFrame;

public class Presenter implements ActionListener{
	
	/**
	 * Controlador de la app
	 * une modelos y vistas
	 */
	private Manager manager;
	private MainFrame mainFrame;
	private PublicService pservice;
	
	public Presenter() {
		manager = new Manager();
		mainFrame = new MainFrame(manager.dataInfo(manager.getPublicServices()), this);
		mainFrame.setVisible(true);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Actions.valueOf(e.getActionCommand())) {
		case ACEPT_ADD:
			String[] data = mainFrame.data();
			addRegister(new PublicService(data[0], data[6], data[1], data[2], data[3], data[4], Double.parseDouble(data[5])));
			mainFrame.updateData(manager.dataInfo(manager.getPublicServices()));
			mainFrame.deleteDialogAdd();
			break;
		case ACEPT_EDIT:
			String[] dataEdit = mainFrame.data();
			editRegister(pservice,new PublicService(dataEdit[0], dataEdit[6], dataEdit[1], dataEdit[2], dataEdit[3], dataEdit[4], Double.parseDouble(dataEdit[5])));
			mainFrame.updateData(manager.dataInfo(manager.getPublicServices()));
			mainFrame.deleteDialogAdd();
			break;
		case ADD:
			mainFrame.showAdd();
			break;
		case DELETE:
			PublicService pService =  mainFrame.selectedItem();
			if (pService==null) {
				JOptionPane.showMessageDialog(mainFrame, "No has seleccionado ningun registro",null, JOptionPane.ERROR_MESSAGE);
			}else {
				deleteRegister(manager.search(pService));
				JOptionPane.showMessageDialog(mainFrame, "Registro eliminado con exito",null, JOptionPane.DEFAULT_OPTION);
				mainFrame.updateData(manager.dataInfo(manager.getPublicServices()));
			}
			break;
		case EDIT:
			pservice = mainFrame.selectedItem();
			if (pservice==null) {
				JOptionPane.showMessageDialog(mainFrame, "No has seleccionado ningun registro",null, JOptionPane.ERROR_MESSAGE);
			}else {
				mainFrame.showEdit(pservice);
			}
			break;
		case FILTER:
			String [] filter = mainFrame.dataFilter();
			mainFrame.updateData(manager.dataInfo(filters(filter[0],filter[1])));
			break;
		case DOWLOAND:
			if(loadData()) {
				JOptionPane.showMessageDialog(mainFrame, "Datos descargados con exito",null, JOptionPane.DEFAULT_OPTION);
				manager.updateData();
				mainFrame.updateData(manager.dataInfo(manager.getPublicServices()));
			}else {
				JOptionPane.showMessageDialog(mainFrame, "Error en la descarga de datos",null, JOptionPane.ERROR_MESSAGE);
			}
			break;
		default:
			break;
		}
	}
	
	/**
	 * Carga los datos de la api
	 * @return boolean si puso o no consultar
	 */
	private boolean loadData() {
		try {
			return manager.loadDataToService();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Metodo fachada para agregar registro en manager
	 * @param publicService registro
	 */
	private void addRegister(PublicService publicService) {
		manager.addRegister(publicService);
	}
	
	/**
	 * Metodo fachada para editar registro en manager
	 * @param publicService registro
	 * @param publicServiceEdit 
	 */
	private void editRegister(PublicService publicService, PublicService publicServiceEdit) {
		manager.editRegister(publicService,publicServiceEdit);
	}
	
	/**
	 * Metodo fachada para eliminar registro en manager
	 * @param publicService registro
	 */
	private void deleteRegister(PublicService publicService) {
		manager.deleteRegister(publicService);
	}
	
	/**
	 * Metodo fachada para llamar los filtros desde manager
	 * @param typeFilter tipo de filtro
	 * @param value valor para filtrar
	 */
	private ArrayList<PublicService> filters(String typeFilter, String value){
		return manager.filters(typeFilter, value);
	}
	
	public static void main(String[] args) {
		new Presenter();
	}


}
