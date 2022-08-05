package models;

import java.io.IOException;
import java.util.ArrayList;


public class Manager {

	/**
	 * Clase Manager, encargada de unir la logica
	 */
	private ArrayList<PublicService> publicServices;
	private MyService myService;
	private ReadAndWriteData rwData;
	
	/**
	 * Constructor de la clase
	 */
	public Manager() {
		myService = new MyService();
		rwData = new ReadAndWriteData();
		if (readData()!= null) {			
			publicServices = readData();
		}else {
			publicServices = new ArrayList<PublicService>();
		}
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
		publicServices.remove(search(publicService));
		write(publicServices);
		publicServices = readData();
	}
	
	/**
	 * Crea un nuevo registro
	 * @param publicService el dato a crear
	 */
	public void addRegister(PublicService publicService) {
		publicServices.add(publicService);
		write(publicServices);
		publicServices = readData();
	}
	
	
	/**
	 * Editar un registro
	 * @param publicService el dato a editar
	 * @param publicServiceEdit 
	 */
	public void editRegister(PublicService publicService, PublicService publicServiceEdit) {
		String [] data = new String[] {publicService.getYear(), publicService.getNormative(), publicService.getType(),
				publicService.getOrigin(), publicService.getDestination(), publicService.getSurcharge()};
		for (PublicService publicS : publicServices) {
			if (validate(publicS, publicService.getRate(), data)) {
				publicS.setYear(publicServiceEdit.getYear());
				publicS.setNormative(publicServiceEdit.getNormative());
				publicS.setType(publicService.getType());
				publicS.setOrigin(publicService.getOrigin());
				publicS.setDestination(publicServiceEdit.getDestination());
				publicS.setRate(publicServiceEdit.getRate());
				publicS.setSurcharge(publicServiceEdit.getSurcharge());
			}
		}
		write(publicServices);
		publicServices = readData();
	}
	
	/**
	 * Buscar un PublicService
	 * @param publicService a buscar
	 * @return si lo encuentra en la lista
	 */
	public PublicService search(PublicService publicService) {
		String [] data = new String[] {publicService.getYear(), publicService.getNormative(), publicService.getType(),
										publicService.getOrigin(), publicService.getDestination(), publicService.getSurcharge()};
		for (PublicService publicService2 : publicServices) {
			if (validate(publicService2,publicService.getRate(), data)) {
				return publicService2;
			}
		}
		return null;
	}
	
	/**
	 * Condiciones de busqueda
	 * @param publicService a comparar
	 * @param value rate
	 * @param data	datos
	 * @return condicion de busqueda
	 */
	private boolean validate(PublicService publicService, double value, String ... data) {
		return publicService.getYear().equals(data[0]) && publicService.getNormative().equals(data[1])&&
				publicService.getType().equals(data[2]) && publicService.getOrigin().equals(data[3])&&
				publicService.getDestination().equals(data[4])&&publicService.getRate() == value &&
				publicService.getSurcharge().equals(data[5]);
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
		case "Año":
			for (PublicService publicService : dataLoad) {
				if(publicService.getYear().equals(filterValue)) {
					publicServices.add(publicService);
				}
			}
			break;
		case "Tipo":
			for (PublicService publicService : dataLoad) {
				if(publicService.getType().equals(filterValue)) {
					publicServices.add(publicService);
				}
			}
			break;
		case "Origen":
			for (PublicService publicService : dataLoad) {
				if(publicService.getOrigin().equals(filterValue)) {
					publicServices.add(publicService);
				}
			}
			break;
		case "Normativa":
			for (PublicService publicService : dataLoad) {
				if(publicService.getNormative().equals(filterValue)) {
					publicServices.add(publicService);
				}
			}
			break;
		case "Todos":
			publicServices = dataLoad;
			break;
		}
		return publicServices;
	}
	
	/**
	 * 
	 * @param lista traida del json de la data
	 * @return objeto para pintar en la tabla
	 */
	public Object[][] dataInfo(ArrayList<PublicService> publicServices ) {
		Object[][] processInfo = new Object[publicServices.size()][7];
		for (int i = 0; i < publicServices.size(); i++) {
			processInfo[i][0] = publicServices.get(i).getYear();
			processInfo[i][1] = publicServices.get(i).getNormative();
			processInfo[i][2] = publicServices.get(i).getType();
			processInfo[i][3] = publicServices.get(i).getOrigin();
			processInfo[i][4] = publicServices.get(i).getDestination();
			processInfo[i][5] = publicServices.get(i).getRate();
			processInfo[i][6] = publicServices.get(i).getSurcharge();
		}
		return processInfo;
	}
	
	/**
	 * 
	 * @return Lista de datos leidos
	 */
	public ArrayList<PublicService> getPublicServices() {
		return publicServices;
	}

	/**
	 * Carga los datos recien leidos de la api
	 */
	public void updateData() {
		publicServices = readData();
	}
	
}
