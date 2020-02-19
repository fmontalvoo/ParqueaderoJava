package ec.ups.edu.p3.frames;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ec.ups.edu.p3.aplicacion.Ventana;
import ec.ups.edu.p3.entidades.Ticket;
import ec.ups.edu.p3.gestionBD.TicketBD;

/**
 * 
 * @author fmont
 *
 */
public class ListarTickets extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	public DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new ListarTickets().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ListarTickets() {
		setTitle("Tickets");
		setClosable(true);
		setBounds(400, 150, 450, 300);
		getContentPane().setLayout(null);

		Object[][] datos = null;
		Object[] columnas = { "Id_Ticket", "Placa", "FechaIN", "HoraIN" };
		modelo = new DefaultTableModel(datos, columnas);

		TicketBD tbd = new TicketBD();
		ArrayList<Ticket> ticket = tbd.listar();
		for (Ticket t : ticket) {

			Object[] fila = { t.getId_ticket(), t.getPlaca(), t.getFecha_in(), t.getHora_in() };
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
		String p = (String) table.getColumnModel().getColumn(1).getHeaderValue();
		String f = (String) table.getColumnModel().getColumn(2).getHeaderValue();
		String h = (String) table.getColumnModel().getColumn(3).getHeaderValue();

		ResourceBundle rb = ResourceBundle.getBundle(propertyFile);
		this.table.getColumn(i).setHeaderValue(rb.getString("colTicket"));
		this.table.getColumn(p).setHeaderValue(rb.getString("clPlaca"));
		this.table.getColumn(f).setHeaderValue(rb.getString("clFechain"));
		this.table.getColumn(h).setHeaderValue(rb.getString("clHorain"));
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