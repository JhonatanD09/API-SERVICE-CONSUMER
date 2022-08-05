package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import models.PublicService;

public class MainFrame extends JFrame{

	private static final String TITLE = "Consumo api web";
	private static final long serialVersionUID = 1L;
	private Object[][] publicServices;
	private ActionListener actionListener;
	private PanelNort panelNort;
	private TableInfo tableInfo;
	private PanelCrud panelCrud;
	private PanelFilter panelFilter;
	private PanelDowloand panelDowloand;
	private JScrollPane jScrollPane;
	private AddProcessDialog addProcessDialog;

	/**
	 * Contructor de la clase principal de la vista
	 * @param dataInfo
	 * @param listener
	 */
	public MainFrame(Object[][] dataInfo, ActionListener listener) {
		this.publicServices = dataInfo;
		this.actionListener = listener;
		
		setTitle(TITLE);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		initComponents();
	}

/**
 * Inicia los componentes a mostrar
 */
	private void initComponents() {
		panelNort = new PanelNort();
		tableInfo = new TableInfo(publicServices);
		panelCrud = new PanelCrud(actionListener);
		panelFilter = new PanelFilter(actionListener);
		panelDowloand = new PanelDowloand(actionListener);
		add(panelNort, BorderLayout.NORTH);
		jScrollPane = new  JScrollPane(tableInfo);
		add(jScrollPane, BorderLayout.CENTER);
		JPanel panelWest = new JPanel();
		panelWest.setLayout(new BorderLayout());
		panelWest.add(panelFilter, BorderLayout.NORTH);
		panelWest.add(panelCrud, BorderLayout.CENTER);
		add(panelWest, BorderLayout.WEST);
		add(panelDowloand, BorderLayout.EAST);
	}
	
	/**
	 * Metodo fachada que retorna el registro seleccionado
	 * @return
	 */
	public PublicService selectedItem() {
		return tableInfo.getDataIndex();
	}

/**
 * Actualiza la info de la tabla en caso de una operacion de crud
 * @param dataInfo
 */
	public void updateData(Object[][] dataInfo) {
		this.publicServices = dataInfo;
		remove(jScrollPane);
		tableInfo = new TableInfo(publicServices);
		jScrollPane = new  JScrollPane(tableInfo);
		add(jScrollPane, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	
	/**
	 * Muestra el dialogo de agregar
	 */
	public void showAdd() {
		addProcessDialog = new AddProcessDialog(actionListener, false);
		addProcessDialog.setVisible(true);
	}
	
	/**
	 * Muestra el dialogo de editar
	 * @param publicService
	 */
	public void showEdit(PublicService publicService) {
		addProcessDialog = new AddProcessDialog(actionListener, true);
		addProcessDialog.setInitialInfo(publicService);
		addProcessDialog.setVisible(true);
	}
	
	/**
	 * Retorna los datos de los inputs
	 * @return
	 */
	public String[] data() {
		return addProcessDialog.data();
	}
	
	/**
	 * Retorna los datos de los filtros
	 * @return
	 */
	public String[] dataFilter() {
		return panelFilter.dataFilter();
	}
	
	/**
	 * Elimina los dialogos de agregar o editar
	 */
	public void deleteDialogAdd() {
		addProcessDialog.dispose();
	}
	
}
