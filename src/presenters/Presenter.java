package presenters;

import java.io.IOException;
import java.util.ArrayList;

import models.Manager;
import models.PublicService;

public class Presenter {
	
	/**
	 * Controlador de la app
	 * une modelos y vistas
	 */
	private Manager manager;
	
	public Presenter() {
		manager = new Manager();
		System.out.println(loadData());
		
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
	 */
	private void editRegister(PublicService publicService) {
		manager.editRegister(publicService);
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
