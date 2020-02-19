package ec.ups.edu.p3.frames;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ec.ups.edu.p3.aplicacion.Ventana;
import ec.ups.edu.p3.entidades.Cliente;
import ec.ups.edu.p3.gestionBD.ClienteBD;

/**
 * 
 * @author fmont
 *
 */
public class ListarClientes extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	public DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new ListarClientes().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ListarClientes() {
		setTitle("Clientes");
		setClosable(true);
		setBounds(400, 150, 450, 300);
		getContentPane().setLayout(null);

		Object[][] datos = null;
		Object[] columnas = { "Cedula", "Nombres", "Telefono", "Direccion" };
		modelo = new DefaultTableModel(datos, columnas);

		ClienteBD cbd = new ClienteBD();
		ArrayList<Cliente> cliente = cbd.listar();
		for (Cliente c : cliente) {

			Object[] fila = { c.getCedula(), c.getNombres(), c.getTelefono(), c.getDireccion() };
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

		String c = (String) table.getColumnModel().getColumn(0).getHeaderValue();
		String n = (String) table.getColumnModel().getColumn(1).getHeaderValue();
		String t = (String) table.getColumnModel().getColumn(2).getHeaderValue();
		String d = (String) table.getColumnModel().getColumn(3).getHeaderValue();

		ResourceBundle rb = ResourceBundle.getBundle(propertyFile);
		this.setTitle(rb.getString("Clientes"));
		this.table.getColumn(c).setHeaderValue(rb.getString("clCedula"));
		this.table.getColumn(n).setHeaderValue(rb.getString("clNombres"));
		this.table.getColumn(t).setHeaderValue(rb.getString("clTelefono"));
		this.table.getColumn(d).setHeaderValue(rb.getString("clDireccion"));
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