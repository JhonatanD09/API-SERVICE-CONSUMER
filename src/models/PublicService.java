package models;

public class PublicService {

	/**
	 *  Los parametros se definen con el mismo nombre que traen del servicio web
	 *  para poder consultarlos con la libreria JSON 
	 */
	
	private String a_o;
	private String normatividad_que_rige_la,tipo, origen, destino,recargo_nocturno_o_festivo;
	private double tarifa;
	
	/**
	 * Contructor de la clase
	 * @param a_o  anio
	 * @param recargo_nocturno_o_festivo
	 * @param normatividad_que_rige_la
	 * @param tipo
	 * @param origen
	 * @param destino
	 * @param tarifa
	 */
	public PublicService(String a_o, String recargo_nocturno_o_festivo, String normatividad_que_rige_la, String tipo,
			String origen, String destino, double tarifa) {
		super();
		this.a_o = a_o;
		this.recargo_nocturno_o_festivo = recargo_nocturno_o_festivo;
		this.normatividad_que_rige_la = normatividad_que_rige_la;
		this.tipo = tipo;
		this.origen = origen;
		this.destino = destino;
		this.tarifa = tarifa;
	}
	
	/**
	 * 
	 * GETTERS AND SETTERS DE LA CLASE
	 */
	public String getYear() {
		return a_o;
	}
	public void setYear(String year) {
		this.a_o = year;
	}
	public String getSurcharge() {
		return recargo_nocturno_o_festivo;
	}
	public void setSurcharge(String surcharge) {
		this.recargo_nocturno_o_festivo = surcharge;
	}
	public String getNormative() {
		return normatividad_que_rige_la;
	}
	public void setNormative(String normative) {
		this.normatividad_que_rige_la = normative;
	}
	public String getType() {
		return tipo;
	}
	public void setType(String type) {
		this.tipo = type;
	}
	public String getOrigin() {
		return origen;
	}
	public void setOrigin(String origin) {
		this.origen = origin;
	}
	public String getDestination() {
		return destino;
	}
	public void setDestination(String destination) {
		this.destino = destination;
	}
	public double getRate() {
		return tarifa;
	}
	public void setRate(double rate) {
		this.tarifa = rate;
	}
	
}
