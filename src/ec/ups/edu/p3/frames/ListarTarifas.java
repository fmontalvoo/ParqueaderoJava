package ec.ups.edu.p3.frames;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ec.ups.edu.p3.aplicacion.Ventana;
import ec.ups.edu.p3.entidades.Tarifa;
import ec.ups.edu.p3.gestionBD.TarifaBD;

/**
 * 
 * @author fmont
 *
 */
public class ListarTarifas extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	public DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new ListarTarifas().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ListarTarifas() {
		setTitle("Tarifas");
		setClosable(true);
		setBounds(400, 150, 450, 300);
		getContentPane().setLayout(null);

		Object[][] datos = null;
		Object[] columnas = { "Id_Tarifa", "Tipo", "Tiempo", "Precio" };
		modelo = new DefaultTableModel(datos, columnas);

		TarifaBD tbd = new TarifaBD();
		ArrayList<Tarifa> tarifa = tbd.listar();
		for (Tarifa t : tarifa) {

			Object[] fila = { t.getId_tarifa(), t.getTipo(), t.getTiempo(), t.getPrecio() };
			modelo.addRow(fila);

		}

		table = new JTable(modelo);
		getContentPane().add(table);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 414, 248);
		getContentPane().add(scrollPane);

		if (Ventana.idioma) {
			setBundles("ec.ups.edu.p3.idioma.mensaje_es_EC");
		} else {
			setBundles("ec.ups.edu.p3.idioma.mensaje_en_US");
		}

	}

	private void setBundles(String propertyFile) {

		String i = (String) table.getColumnModel().getColumn(0).getHeaderValue();
		String t = (String) table.getColumnModel().getColumn(1).getHeaderValue();
		String ti = (String) table.getColumnModel().getColumn(2).getHeaderValue();
		String p = (String) table.getColumnModel().getColumn(3).getHeaderValue();

		ResourceBundle rb = ResourceBundle.getBundle(propertyFile);
		this.setTitle(rb.getString("Tarifas"));
		this.table.getColumn(i).setHeaderValue(rb.getString("clTarifa"));
		this.table.getColumn(t).setHeaderValue(rb.getString("clTipo"));
		this.table.getColumn(ti).setHeaderValue(rb.getString("clTiempo"));
		this.table.getColumn(p).setHeaderValue(rb.getString("clPrecio"));
		repaint();
	}

	public void idioma(boolean idioma) {
		if (idioma) {
			setBundles("ec.ups.edu.p3.idioma.mensaje_es_EC");
		} else {
			setBundles("ec.ups.edu.p3.idioma.mensaje_en_US");
		}
	}
}