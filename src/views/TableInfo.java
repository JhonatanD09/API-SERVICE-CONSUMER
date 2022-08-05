package views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import models.PublicService;

public class TableInfo extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String[] COLUMNS = {"Año","Normativa", "Tipo","Origen","Destino","Tarifa","Recargo"};

	private TablePanel data; 

	/**
	 * Contructor de la clase
	 * @param dataOperator
	 */
	public TableInfo(Object [][] dataOperator) {
		setBackground(Color.WHITE);
		data = new TablePanel(dataOperator, COLUMNS);
		data.setPreferredSize(new Dimension(1000,680));
		add(data);
	}
	
	/**
	 * Cambia la info a mostrar en la tabla
	 * @param dataOperator
	 */
	public void setDataOperator(Object [][] dataOperator) {
		data.setData(dataOperator, COLUMNS);
		revalidate();
		repaint();
	}
	
	/**
	 * 
	 * @return El registro seleccionado, si no se ha selecionado retorna null
	 */
	public PublicService  getDataIndex() {
		int rowSelect = data.getTable().getSelectedRow();
		if (rowSelect >= 0) {			
			Double value = (Double)data.getTable().getValueAt(rowSelect, 5);
			PublicService publicService = new PublicService((String)data.getTable().getValueAt(rowSelect, 0),
					(String)data.getTable().getValueAt(rowSelect, 6), 
					(String)data.getTable().getValueAt(rowSelect, 1), 
					(String)data.getTable().getValueAt(rowSelect, 2), 
					(String)data.getTable().getValueAt(rowSelect, 3), 
					(String)data.getTable().getValueAt(rowSelect, 4),
					value
					);
			return publicService ;
		}else {
			return null;
		}
	}
}
