package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class MyService {
	
		private static final String URL_CONSUME= "https://www.datos.gov.co/resource/n4rc-4ysg.json";

		/**
		 * Cargar los datos de la api
		 * @return booleano true si respondio 200
		 */
		public boolean loadData()  {
			URL obj;
			StringBuffer response = null;
			int responseCode = 0;
			try {
//				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.73", 8080));
				obj = new URL(URL_CONSUME);
				HttpURLConnection con = (HttpURLConnection) obj.openConnection(/*proxy*/);
				responseCode = con.getResponseCode();
				Logger.getGlobal().log(Level.INFO, "Response Code : " + responseCode);
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
			} catch (IOException e) {
				Logger.getGlobal().log(Level.INFO, "error en conexion " + e.getMessage());
			}
			convertAndWrite(response);
			return responseCode == 200;
		}

		/**
		 * Convierte a lista de Public Service y escribe en data.json
		 * @param response es el string del json que lee web
		 */
		private void convertAndWrite(StringBuffer response) {
			Gson gson = new Gson();
			Type operatorsList = new TypeToken<ArrayList<PublicService>>(){}.getType();
			ArrayList<PublicService> publicServices = gson.fromJson(response.toString(), operatorsList);
			ReadAndWriteData readAndWriteData = new ReadAndWriteData();
			readAndWriteData.writeData(publicServices);
		}
		
}
