package models;

import java.io.IOException;
import java.util.ArrayList;

public class Manager {

	/**
	 * Clase Manager, encargada de unir la logica
	 */
	
	private MyService myService;
	private ReadAndWriteData rwData;
	
	/**
	 * Constructor de la clase
	 */
	public Manager() {
		myService = new MyService();
		rwData = new ReadAndWriteData();
	}
	
	/**
	 * lee los datos del fichero de datos
	 * @return lista de los datos leidos
	 */
	public ArrayList<PublicService> readData(){
		return rwData.readData();
	}
	
	/**
	 * Escribir el archivo modificado
	 * @param publicServices lista modificada
	 */
	public void write(ArrayList<PublicService> publicServices) {
		rwData.writeData(publicServices);
	}
	
	/**
	 * Carga los datos de la api web y los guarda
	 * @return booleano de si puso o no cargar los datos
	 * @throws IOException
	 */
	public boolean loadDataToService() throws IOException {
		return myService.loadData();
	}
	
	/**
	 * Elimina un registro
	 * @param publicService el dato a eliminar
	 */
	public void deleteRegister(PublicService publicService) {
		ArrayList<PublicService> publicServices = readData();
		publicServices.remove(publicService);
		write(publicServices);
	}
	
	/**
	 * Crea un nuevo registro
	 * @param publicService el dato a crear
	 */
	public void addRegister(PublicService publicService) {
		ArrayList<PublicService> publicServices = readData();
		publicServices.add(publicService);
		write(publicServices);
	}
	
	/**
	 * Editar un registro
	 * @param publicService el dato a editar
	 */
	public void editRegister(PublicService publicService) {
		ArrayList<PublicService> publicServices = readData();
		for (PublicService publicS : publicServices) {
			if (publicS.equals(publicService)) {
				publicS = publicService;
			}
		}
		write(publicServices);
	}
	
	/**
	 * Realiza 4 filtros sobre los datos
	 * Recorre la lista dependiendo cada filtro para hacer la comparacion y
	 * listar los datos que cumplen con la misma
	 * @param filter el tipo de filtro que se hace
	 * @param filterValue  el valor del filtro
	 * @return
	 */
	
	public ArrayList<PublicService> filters(String filter, String filterValue){
		ArrayList<PublicService> publicServices = new ArrayList<>();
		ArrayList<PublicService> dataLoad = readData();
		switch (filter) {
		case "AÑO":
			for (PublicService publicService : dataLoad) {
				if(publicService.getYear().equals(filterValue)) {
					publicServices.add(publicService);
				}
			}
			break;
		case "TIPO":
			for (PublicService publicService : dataLoad) {
				if(publicService.getType().equals(filterValue)) {
					publicServices.add(publicService);
				}
			}
			break;
		case "ORIGEN":
			for (PublicService publicService : dataLoad) {
				if(publicService.getOrigin().equals(filterValue)) {
					publicServices.add(publicService);
				}
			}
			break;
		case "NORMATIVA":
			for (PublicService publicService : dataLoad) {
				if(publicService.getNormative().equals(filterValue)) {
					publicServices.add(publicService);
				}
			}
			break;
		case "ALL":
			publicServices = dataLoad;
			break;
		}
		return publicServices;
	}
	
}
