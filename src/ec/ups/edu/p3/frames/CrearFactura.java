package ec.ups.edu.p3.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ec.ups.edu.p3.aplicacion.PanelFacturacion;
import ec.ups.edu.p3.aplicacion.Ventana;
import ec.ups.edu.p3.entidades.Cliente;
import ec.ups.edu.p3.entidades.Factura;
import ec.ups.edu.p3.entidades.Tarifa;
import ec.ups.edu.p3.gestionBD.ClienteBD;
import ec.ups.edu.p3.gestionBD.FacturaBD;
import ec.ups.edu.p3.gestionBD.TarifaBD;

/**
 * 
 * @author fmont
 *
 */
public class CrearFactura extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtCedula;
	private JTextField txtNombres;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	public static JTextField txtTicket;
	public static JTextField txtPlaca;
	public static JTextField txtFechaIN;
	public static JTextField txtHoraIN;
	public static JTextField txtHoraSA;
	public static JTextField txtTiempo;
	public static JTextField txtTarifa;
	public static JTextField txtTotal;
	private JTextField txtFacturaN;
	private JTextField txtFechaEM;

	private ControlCliente cl;

	private JRadioButton rdbtnDatos;
	private JRadioButton rdbtnFinal;
	private JLabel lblFacn;
	private JLabel lblFechaem;
	private JLabel lblCedula;//
	private JLabel lblNombres;//
	private JLabel lblDireccion;//
	private JLabel lblTelefono;//
	private JLabel lblTicket;//
	private JLabel lblPlaca;//
	private JLabel lblFechain;//
	private JLabel lblHorain;//
	private JLabel lblHorasa;//
	private JLabel lblTiempo;//
	private JLabel lblTarifa;
	private JLabel lblTotal;//
	private JButton btnImprimir;

	public static void main(String[] args) {
		new CrearFactura().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public CrearFactura() {

		setTitle("Factura");
		setClosable(true);
		setBounds(400, 150, 520, 285);
		getContentPane().setLayout(null);

		rdbtnDatos = new JRadioButton("Datos");
		rdbtnDatos.setSelected(true);
		rdbtnDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnDatos.isSelected()) {
					rdbtnFinal.setSelected(false);
				}

				txtCedula.setText("");
				txtNombres.setText("");

				txtCedula.setEditable(true);
				txtNombres.setEditable(true);
				txtTelefono.setEditable(true);
				txtDireccion.setEditable(true);
				txtPlaca.setEditable(true);
				txtTicket.setEditable(true);
				txtFechaIN.setEditable(true);
				txtFechaEM.setEditable(true);
				txtHoraIN.setEditable(true);
				txtHoraSA.setEditable(true);
				txtTiempo.setEditable(true);
				txtTarifa.setEditable(true);
				txtTotal.setEditable(true);

			}
		});
		rdbtnDatos.setBounds(82, 7, 62, 23);
		getContentPane().add(rdbtnDatos);

		rdbtnFinal = new JRadioButton("Final");
		rdbtnFinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (rdbtnFinal.isSelected()) {
					rdbtnDatos.setSelected(false);
				}

				ClienteBD cliente = new ClienteBD();
				Cliente c = cliente.leer("9999999999999");
				txtCedula.setText(c.getCedula());
				txtNombres.setText(c.getNombres());

				txtTelefono.setText("");
				txtDireccion.setText("");

				txtCedula.setEditable(false);
				txtNombres.setEditable(false);
				txtTelefono.setEditable(false);
				txtDireccion.setEditable(false);
				txtPlaca.setEditable(false);
				txtTicket.setEditable(false);
				txtFechaIN.setEditable(false);
				txtFechaEM.setEditable(false);
				txtHoraIN.setEditable(false);
				txtHoraSA.setEditable(false);
				txtTiempo.setEditable(false);
				txtTarifa.setEditable(false);
				txtTotal.setEditable(false);

			}
		});
		rdbtnFinal.setBounds(344, 7, 52, 23);
		getContentPane().add(rdbtnFinal);

		lblCedula = new JLabel("Cedula:");
		lblCedula.setBounds(10, 68, 52, 14);
		getContentPane().add(lblCedula);

		txtCedula = new JTextField();
		txtCedula.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {

				ClienteBD cliente = new ClienteBD();
				char key = e.getKeyChar();
				if (key == KeyEvent.VK_ENTER) {
					if (cliente.verificarCliente(txtCedula.getText())) {
						Cliente c = cliente.leer(txtCedula.getText());
						txtCedula.setText(c.getCedula());
						txtNombres.setText(c.getNombres());
						txtTelefono.setText(c.getTelefono());
						txtDireccion.setText(c.getDireccion());
					} else {

						cl = new ControlCliente();
						cl.txtCedula.setText(txtCedula.getText());
						Ventana.dpkEscritorio.add(cl);
						cl.show();

					}
				}

			}
		});
		txtCedula.setBounds(82, 62, 150, 20);
		getContentPane().add(txtCedula);
		txtCedula.setColumns(10);

		lblNombres = new JLabel("Nombres:");
		lblNombres.setBounds(282, 68, 62, 14);
		getContentPane().add(lblNombres);

		txtNombres = new JTextField();
		txtNombres.setBounds(344, 62, 150, 20);
		getContentPane().add(txtNombres);
		txtNombres.setColumns(10);

		lblDireccion = new JLabel("Direccion: ");
		lblDireccion.setBounds(10, 93, 62, 14);
		getContentPane().add(lblDireccion);

		txtDireccion = new JTextField();
		txtDireccion.setBounds(82, 90, 150, 20);
		getContentPane().add(txtDireccion);
		txtDireccion.setColumns(10);

		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(282, 93, 52, 14);
		getContentPane().add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(344, 90, 150, 20);
		getContentPane().add(txtTelefono);
		txtTelefono.setColumns(10);

		lblTicket = new JLabel("Ticket N#:");
		lblTicket.setBounds(10, 118, 62, 14);
		getContentPane().add(lblTicket);

		txtTicket = new JTextField();
		txtTicket.setBounds(82, 115, 150, 20);
		getContentPane().add(txtTicket);
		txtTicket.setColumns(10);

		lblPlaca = new JLabel("Placa: ");
		lblPlaca.setBounds(282, 118, 46, 14);
		getContentPane().add(lblPlaca);

		txtPlaca = new JTextField();
		txtPlaca.setBounds(344, 115, 150, 20);
		getContentPane().add(txtPlaca);
		txtPlaca.setColumns(10);

		lblFechain = new JLabel("FechaIN:");
		lblFechain.setBounds(10, 143, 62, 14);
		getContentPane().add(lblFechain);

		txtFechaIN = new JTextField();
		txtFechaIN.setBounds(82, 140, 150, 20);
		getContentPane().add(txtFechaIN);
		txtFechaIN.setColumns(10);

		lblHorain = new JLabel("HoraIN:");
		lblHorain.setBounds(282, 143, 52, 14);
		getContentPane().add(lblHorain);

		txtHoraIN = new JTextField();
		txtHoraIN.setBounds(344, 140, 150, 20);
		getContentPane().add(txtHoraIN);
		txtHoraIN.setColumns(10);

		lblHorasa = new JLabel("HoraSA:");
		lblHorasa.setBounds(10, 168, 62, 14);
		getContentPane().add(lblHorasa);

		txtHoraSA = new JTextField();
		txtHoraSA.setBounds(82, 165, 150, 20);
		getContentPane().add(txtHoraSA);
		txtHoraSA.setColumns(10);

		lblTiempo = new JLabel("Tiempo:");
		lblTiempo.setBounds(282, 168, 52, 14);
		getContentPane().add(lblTiempo);

		txtTiempo = new JTextField();
		txtTiempo.setBounds(344, 165, 150, 20);
		getContentPane().add(txtTiempo);
		txtTiempo.setColumns(10);

		FacturaBD fbd = new FacturaBD();

		btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Factura f = new Factura();
				TarifaBD tbd = new TarifaBD();
				Tarifa t = tbd.consultarTarifa(txtTarifa.getText());
				txtFacturaN.setText("" + (fbd.consultarID() + 1));

				int lote = PanelFacturacion.salida();

				f.setId_factura(Integer.parseInt(txtFacturaN.getText()));
				f.setCedula(txtCedula.getText());
				f.setTicket(Integer.parseInt(txtTicket.getText()));
				f.setTarifa(t.getId_tarifa());
				f.setEstacionamiento(lote);
				f.setTotal(Double.parseDouble(txtTotal.getText()));
				try {
					f.setFecha_emision(new SimpleDateFormat("yyyy-MM-dd").parse(txtFechaEM.getText()));
				} catch (ParseException ex) {
					ex.printStackTrace();
				}

				fbd.insertar(f);

			}
		});
		btnImprimir.setBounds(82, 221, 89, 23);
		getContentPane().add(btnImprimir);

		lblTarifa = new JLabel("Tarifa:");
		lblTarifa.setBounds(10, 193, 46, 14);
		getContentPane().add(lblTarifa);

		txtTarifa = new JTextField();
		txtTarifa.setBounds(82, 190, 150, 20);
		getContentPane().add(txtTarifa);
		txtTarifa.setColumns(10);

		lblTotal = new JLabel("Total");
		lblTotal.setBounds(282, 193, 62, 14);
		getContentPane().add(lblTotal);

		txtTotal = new JTextField();
		txtTotal.setBounds(344, 190, 150, 20);
		getContentPane().add(txtTotal);
		txtTotal.setColumns(10);

		lblFacn = new JLabel("Fac_N#:");
		lblFacn.setBounds(10, 34, 52, 14);
		getContentPane().add(lblFacn);

		txtFacturaN = new JTextField();
		txtFacturaN.setEditable(false);
		txtFacturaN.setBounds(82, 31, 150, 20);
		getContentPane().add(txtFacturaN);
		txtFacturaN.setColumns(10);

		lblFechaem = new JLabel("FechaEm:");
		lblFechaem.setBounds(282, 34, 62, 14);
		getContentPane().add(lblFechaem);

		Calendar cal = Calendar.getInstance();
		int año = cal.get(Calendar.YEAR);
		String mes = (cal.get(Calendar.MONTH) + 1) > 9 ? "" + (cal.get(Calendar.MONTH) + 1)
				: "0" + (cal.get(Calendar.MONTH) + 1);
		String dd = cal.get(Calendar.DAY_OF_MONTH) > 9 ? "" + cal.get(Calendar.DAY_OF_MONTH)
				: "0" + cal.get(Calendar.DAY_OF_MONTH);

		txtFechaEM = new JTextField();
		txtFechaEM.setBounds(344, 31, 150, 20);
		txtFechaEM.setText(año + "-" + mes + "-" + dd);
		getContentPane().add(txtFechaEM);
		txtFechaEM.setColumns(10);

		if (Ventana.idioma) {
			setBundles("ec.ups.edu.p3.idioma.mensaje_es_EC");
		} else {
			setBundles("ec.ups.edu.p3.idioma.mensaje_en_US");
		}

	}

	private void setBundles(String propertyFile) {

		ResourceBundle rb = ResourceBundle.getBundle(propertyFile);
		this.setTitle(rb.getString("Factura"));
		this.rdbtnDatos.setText(rb.getString("Datos"));
		this.rdbtnFinal.setText(rb.getString("Final"));
		this.lblFacn.setText(rb.getString("Facn"));
		this.lblFechaem.setText(rb.getString("Fechaem"));
		this.lblCedula.setText(rb.getString("Cedula"));
		this.lblNombres.setText(rb.getString("Nombres"));
		this.lblDireccion.setText(rb.getString("Direccion"));
		this.lblTelefono.setText(rb.getString("Telefono"));
		this.lblTicket.setText(rb.getString("lblTicket"));
		this.lblPlaca.setText(rb.getString("Placa"));
		this.lblFechain.setText(rb.getString("Fechain"));
		this.lblHorain.setText(rb.getString("Horain"));
		this.lblHorasa.setText(rb.getString("Horasa"));
		this.lblTiempo.setText(rb.getString("Tiempo"));
		this.lblTarifa.setText(rb.getString("lblTarifa"));
		this.lblTotal.setText(rb.getString("Total"));
		this.btnImprimir.setText(rb.getString("Imprimir"));

	}

	public void idioma(boolean idioma) {
		idiomaRegistro(idioma);
		if (idioma) {
			setBundles("ec.ups.edu.p3.idioma.mensaje_es_EC");
		} else {
			setBundles("ec.ups.edu.p3.idioma.mensaje_en_US");
		}
	}

	public void idiomaRegistro(boolean idioma) {
		try {
			cl.idioma(idioma);
		} catch (Exception e) {

		}
	}

}
