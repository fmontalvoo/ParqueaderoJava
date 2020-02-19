package ec.ups.edu.p3.frames;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ec.ups.edu.p3.aplicacion.Ventana;
import ec.ups.edu.p3.entidades.Factura;
import ec.ups.edu.p3.gestionBD.FacturaBD;

/**
 * 
 * @author fmont
 *
 */
public class ListarFacturas extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	public DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new ListarFacturas().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ListarFacturas() {
		setClosable(true);
		setTitle("Facturas");
		setBounds(400, 150, 450, 300);
		getContentPane().setLayout(null);

		Object[][] datos = null;
		Object[] columnas = { "Id_Factura", "FechaEM", "Cedula", "Tarifa", "Ticket", "Estacionamiento", "Total" };
		modelo = new DefaultTableModel(datos, columnas);

		FacturaBD fbd = new FacturaBD();
		ArrayList<Factura> factura = fbd.listar();
		for (Factura f : factura) {

			Object[] fila = { f.getId_factura(), f.getFecha_emision(), f.getCedula(), f.getTarifa(), f.getTicket(),
					f.getEstacionamiento(), f.getTotal() };
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
		String f = (String) table.getColumnModel().getColumn(1).getHeaderValue();
		String c = (String) table.getColumnModel().getColumn(2).getHeaderValue();
		String t = (String) table.getColumnModel().getColumn(3).getHeaderValue();
		String ti = (String) table.getColumnModel().getColumn(4).getHeaderValue();
		String e = (String) table.getColumnModel().getColumn(5).getHeaderValue();
		String to = (String) table.getColumnModel().getColumn(6).getHeaderValue();

		ResourceBundle rb = ResourceBundle.getBundle(propertyFile);
		this.setTitle(rb.getString("Facturas"));
		this.table.getColumn(i).setHeaderValue(rb.getString("clFactura"));
		this.table.getColumn(f).setHeaderValue(rb.getString("clFechaem"));
		this.table.getColumn(c).setHeaderValue(rb.getString("clCedula"));
		this.table.getColumn(t).setHeaderValue(rb.getString("clTarifa"));
		this.table.getColumn(ti).setHeaderValue(rb.getString("clTicket"));
		this.table.getColumn(e).setHeaderValue(rb.getString("clEstacionamiento"));
		this.table.getColumn(to).setHeaderValue(rb.getString("clTotal"));
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