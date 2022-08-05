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
			addToData(data);
			break;
		case ACEPT_EDIT:
			String[] dataEdit = mainFrame.data();
			editToData(dataEdit);
			break;
		case ADD:
			mainFrame.showAdd();
			break;
		case DELETE:
			PublicService pService =  mainFrame.selectedItem();
			delete(pService);
			break;
		case EDIT:
			pservice = mainFrame.selectedItem();
			edit();
			break;
		case FILTER:
			String [] filter = mainFrame.dataFilter();
			mainFrame.updateData(manager.dataInfo(filters(filter[0],filter[1])));
			break;
		case DOWLOAND:
			dowloandData();
			break;
		default:
			break;
		}
	}



	/**
	 * Llama al metodo de la logica que descarga los datos, y dveuelve error en caso de no poder
	 */
	private void dowloandData() {
		if(loadData()) {
			JOptionPane.showMessageDialog(mainFrame, "Datos descargados con exito",null, JOptionPane.DEFAULT_OPTION);
			manager.updateData();
			mainFrame.updateData(manager.dataInfo(manager.getPublicServices()));
		}else {
			JOptionPane.showMessageDialog(mainFrame, "Error en la descarga de datos",null, JOptionPane.ERROR_MESSAGE);
		}
	}


/**
 * Llama al metodo logico de editar un registro, muestra exepcion en caso de no seleccionar ninguno
 */
	private void edit() {
		if (pservice==null) {
			JOptionPane.showMessageDialog(mainFrame, "No has seleccionado ningun registro",null, JOptionPane.ERROR_MESSAGE);
		}else {
			mainFrame.showEdit(pservice);
		}
	}

	
	/**
	 * Llama al metodo de la logica de eliminar un registro
	 * @param pService registro a eliminar
	 * Muestra un mensjaje de erro si no se selecciona ningun dato
	 */

	private void delete(PublicService pService) {
		if (pService==null) {
			JOptionPane.showMessageDialog(mainFrame, "No has seleccionado ningun registro",null, JOptionPane.ERROR_MESSAGE);
		}else {
			deleteRegister(manager.search(pService));
			JOptionPane.showMessageDialog(mainFrame, "Registro eliminado con exito",null, JOptionPane.DEFAULT_OPTION);
			mainFrame.updateData(manager.dataInfo(manager.getPublicServices()));
		}
	}



	/**
	 * Realiza las validaciones de los campos de entrada en el metodo de agregar
	 * @param data los valores de los inputs
	 * si encuentra algun error devuelbe un mensaje de advertencia y no permite agregar
	 */
	private void addToData(String[] data) {
		if(valideEmtyInputs(data)) {
			JOptionPane.showMessageDialog(mainFrame, "Tienes algunos campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(!valideNumberInput(data[0])) {
			JOptionPane.showMessageDialog(mainFrame, "El año debe ser un numero", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(!valideNumberInput(data[5])){
			JOptionPane.showMessageDialog(mainFrame, "La tarifa debe ser un numero", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if(!valideNumberInput(data[6])){
			JOptionPane.showMessageDialog(mainFrame, "El sobrecargo ser un numero", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else{
		addRegister(new PublicService(data[0], data[6], data[1], data[2], data[3], data[4], Double.parseDouble(data[5])));
		mainFrame.updateData(manager.dataInfo(manager.getPublicServices()));
		mainFrame.deleteDialogAdd();
		}
	}


	/**
	 * Realiza las validaciones de los campos de entrada en el metodo de editar
	 * @param dataEdit los valores de los inputs
	 * si encuentra algun error devuelbe un mensaje de advertencia y no permite editar
	 */
	private void editToData(String[] dataEdit) {
		if(valideEmtyInputs(dataEdit)) {
			JOptionPane.showMessageDialog(mainFrame, "Tienes algunos campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(!valideNumberInput(dataEdit[0])) {
			JOptionPane.showMessageDialog(mainFrame, "El año debe ser un numero", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(!valideNumberInput(dataEdit[5])){
			JOptionPane.showMessageDialog(mainFrame, "La tarifa debe ser un numero", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if(!valideNumberInput(dataEdit[6])){
			JOptionPane.showMessageDialog(mainFrame, "El sobrecargo ser un numero", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else{
		editRegister(pservice,new PublicService(dataEdit[0], dataEdit[6], dataEdit[1], dataEdit[2], dataEdit[3], dataEdit[4], Double.parseDouble(dataEdit[5])));
		mainFrame.updateData(manager.dataInfo(manager.getPublicServices()));
		mainFrame.deleteDialogAdd();
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
	
	/**
	 * Validacion de campos vacios
	 * @param data datos de los inputs
	 * @return booleanos de si existe o no campos vacios
	 */
	private boolean valideEmtyInputs(String [] data) {
		for (String string : data) {
			if (string.equals("")) {
				return true;
			}
		}
		return false;
	}
	
	/***
	 * Valida inputs numericos
	 * @param data el campo numerico a evaluar
	 * @return boolean de si el valor es o no numerico
	 */
	private boolean valideNumberInput(String data) {
		return data.matches("\\d*")&&!data.equals("N/A") ;
	}
	
	public static void main(String[] args) {
		new Presenter();
	}


}
