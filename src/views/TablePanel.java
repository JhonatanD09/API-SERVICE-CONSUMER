package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TablePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private DefaultTableModel model;
    private JTable table;

    /**
     * Contructor de la clase
     */
    public TablePanel() {
        setBackground(Color.decode("#FDFEFE"));
        setLayout(new GridLayout(1, 1));
        
    }

    /**
     * Crea una tabla, recibe el encabezado y la informacion
     * @param info
     * @param columnNames
     */
    public TablePanel(Object[][] info, String[] columnNames) {
        setBackground(Color.decode("#FDFEFE"));
        setLayout(new GridLayout(1, 1));
        createTable(info, columnNames);
    }

    /**
     * Metodo para crear y editar parametros de la tabla
     * @param info
     * @param columnNames
     */
    public void createTable(Object[][] info, String[] columnNames) {
        model = new DefaultTableModel(info, columnNames);
        table = new JTable(model){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setBackground(Color.decode("#3267cc"));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        table.setRowHeight(20);
        table.setBackground(Color.white);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setFillsViewportHeight(true);
        table.setBorder(null);
        add(new JScrollPane(table));
    }

    /**
     * Cambiar valores en la tabla
     * @param data
     * @param names
     */
    public void setData(Object[][] data, String[] names) {
        table.setModel(new DefaultTableModel(data, names));
    }

    public DefaultTableModel getModel() {
        return (DefaultTableModel) table.getModel();
    }

    public JTable getTable() {
        return table;
    }
}