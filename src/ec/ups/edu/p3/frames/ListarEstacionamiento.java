package ec.ups.edu.p3.frames;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ec.ups.edu.p3.aplicacion.Ventana;
import ec.ups.edu.p3.entidades.Estacionamiento;
import ec.ups.edu.p3.gestionBD.EstacionamientoBD;

/**
 * 
 * @author fmont
 *
 */
public class ListarEstacionamiento extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	public DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new ListarEstacionamiento().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ListarEstacionamiento() {
		setTitle("Estacionamiento");
		setClosable(true);
		setBounds(400, 150, 450, 300);
		getContentPane().setLayout(null);

		Object[][] datos = null;
		Object[] columnas = { "Id_Estacionamiento", "Lote", "Estado" };
		modelo = new DefaultTableModel(datos, columnas);

		EstacionamientoBD ebd = new EstacionamientoBD();
		ArrayList<Estacionamiento> estacionamiento = ebd.listar();
		for (Estacionamiento e : estacionamiento) {

			Object[] fila = { e.getId_estacionamiento(), e.getLote(), e.Estado() };
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
		String l = (String) table.getColumnModel().getColumn(1).getHeaderValue();
		String e = (String) table.getColumnModel().getColumn(2).getHeaderValue();

		ResourceBundle rb = ResourceBundle.getBundle(propertyFile);
		this.setTitle(rb.getString("Estacionamiento"));
		this.table.getColumn(i).setHeaderValue(rb.getString("clEstacionamiento"));
		this.table.getColumn(l).setHeaderValue(rb.getString("clLote"));
		this.table.getColumn(e).setHeaderValue(rb.getString("clEstado"));
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