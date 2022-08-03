package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ReadAndWriteData {

	private File operatorsFile = new File("data.json");
	
	/**
	 * Escribir datos en el archivo 
	 * @param publicServices  es la lista de datos a escribir
	 */
	public void writeData(ArrayList<PublicService> publicServices) {
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String jsonProgramList = prettyGson.toJson(publicServices);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(operatorsFile))) {
		    bw.write(jsonProgramList);
		    System.out.println("Fichero creado");
		} catch (IOException ex) {
		    ex.getMessage();
		}
	}
	
	/**
	 * lee los datos del archivo
	 * @return la lista de PublicService con los datos leidos
	 */
	public ArrayList<PublicService> readData() {
		ArrayList<PublicService> publicServices = new ArrayList<>();
		String stringProgramList = "";
		try (BufferedReader br = new BufferedReader(new FileReader(operatorsFile))) {
		    String linea;
		    while ((linea = br.readLine()) != null) {
		        stringProgramList += linea;
		    }
		} catch (FileNotFoundException ex) {
		    System.out.println(ex.getMessage());
		} catch (IOException ex) {
		    System.out.println(ex.getMessage());
		}
		
		Gson gson = new Gson();
		Type operatorsList = new TypeToken<ArrayList<PublicService>>(){}.getType();	
		publicServices = gson.fromJson(stringProgramList, operatorsList);
		return publicServices;
	}
}
