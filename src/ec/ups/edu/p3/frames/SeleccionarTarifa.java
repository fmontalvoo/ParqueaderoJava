package ec.ups.edu.p3.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ec.ups.edu.p3.aplicacion.CalculoTiempo;
import ec.ups.edu.p3.aplicacion.Ventana;
import ec.ups.edu.p3.entidades.Tarifa;
import ec.ups.edu.p3.entidades.Ticket;
import ec.ups.edu.p3.gestionBD.TarifaBD;
import ec.ups.edu.p3.gestionBD.TicketBD;

/**
 * 
 * @author fmont
 *
 */
public class SeleccionarTarifa extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtTicket;
	private JTextField txtPlaca;
	private JTextField txtFechaIN;
	private JTextField txtHoraIN;
	public static JTextField txtHoraSA;
	private JTextField txtTiempo;
	private JTextField txtTotal;
	public static JComboBox tipoTarifa;
	private TarifaBD trbd;
	private JTextField txtPrecio;
	private JTextField txtTiempoTotal;

	private CrearFactura factura;

	private JLabel lblTicket;//
	private JLabel lblPlaca;//
	private JLabel lblFechain;
	private JLabel lblHorain;
	private JLabel lblHorasa;
	private JLabel lblTmptotal;
	private JLabel lblTipo;
	private JLabel lblTiempo;
	private JLabel lblPrecio;
	private JLabel lblTotal;
	private JButton btnFacturar;
	private JTextField txtIngreso;

	public static String fecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new SeleccionarTarifa().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public SeleccionarTarifa() {
		setClosable(true);
		setTitle("Tarifa");
		setBounds(400, 150, 520, 200);
		getContentPane().setLayout(null);

		trbd = new TarifaBD();

		lblTicket = new JLabel("Ticket N#:");
		lblTicket.setBounds(10, 14, 62, 14);
		getContentPane().add(lblTicket);

		txtTicket = new JTextField();
		txtTicket.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				TicketBD tbd = new TicketBD();
				char key = e.getKeyChar();
				if (key == KeyEvent.VK_ENTER) {
					if (tbd.verificarTicket(Integer.parseInt(txtTicket.getText()))) {
						Ticket t = tbd.leer(Integer.parseInt(txtTicket.getText()));
						txtPlaca.setText(t.getPlaca());
						txtFechaIN.setText(new SimpleDateFormat("yyyy-MM-dd").format(t.getFecha_in()));
						txtHoraIN.setText(t.getHora_in());
					} else {
						JOptionPane.showMessageDialog(null, "TICKET NO EXISTE!!!!!", "Advertencia", 0);
					}
				}
			}
		});
		txtTicket.setColumns(10);
		txtTicket.setBounds(82, 11, 150, 20);
		getContentPane().add(txtTicket);

		lblPlaca = new JLabel("Placa: ");
		lblPlaca.setBounds(276, 14, 46, 14);
		getContentPane().add(lblPlaca);

		txtPlaca = new JTextField();
		txtPlaca.setColumns(10);
		txtPlaca.setBounds(344, 11, 150, 20);
		getContentPane().add(txtPlaca);

		lblFechain = new JLabel("FechaIN:");
		lblFechain.setBounds(10, 39, 62, 14);
		getContentPane().add(lblFechain);

		txtFechaIN = new JTextField();
		txtFechaIN.setColumns(10);
		txtFechaIN.setBounds(82, 36, 150, 20);
		getContentPane().add(txtFechaIN);

		lblHorain = new JLabel("HoraIN:");
		lblHorain.setBounds(276, 39, 52, 14);
		getContentPane().add(lblHorain);

		txtHoraIN = new JTextField();
		txtHoraIN.setColumns(10);
		txtHoraIN.setBounds(344, 36, 150, 20);
		getContentPane().add(txtHoraIN);

		lblHorasa = new JLabel("HoraSA:");
		lblHorasa.setBounds(10, 64, 62, 14);
		getContentPane().add(lblHorasa);

		txtHoraSA = new JTextField();
		txtHoraSA.setColumns(10);
		txtHoraSA.setBounds(82, 61, 150, 20);
		getContentPane().add(txtHoraSA);

		lblTiempo = new JLabel("Tiempo:");
		lblTiempo.setBounds(276, 89, 52, 14);
		getContentPane().add(lblTiempo);

		txtTiempo = new JTextField();
		txtTiempo.setColumns(10);
		txtTiempo.setBounds(344, 86, 150, 20);
		getContentPane().add(txtTiempo);

		lblTipo = new JLabel("Tipo: ");
		lblTipo.setBounds(10, 89, 62, 14);
		getContentPane().add(lblTipo);

		tipoTarifa = new JComboBox();
		tipoTarifa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tipoTarifa.getSelectedItem() != "") {
					Tarifa t = trbd.consultarTarifa(tipoTarifa.getSelectedItem().toString());
					txtTiempo.setText(t.getTiempo());
					txtPrecio.setText("" + t.getPrecio());
				}

			}
		});
		trbd.Tarifas();
		tipoTarifa.setBounds(82, 86, 150, 20);
		getContentPane().add(tipoTarifa);

		lblTotal = new JLabel("Total:");
		lblTotal.setBounds(276, 143, 62, 14);
		getContentPane().add(lblTotal);

		txtTotal = new JTextField();
		txtTotal.setBounds(344, 140, 150, 20);
		getContentPane().add(txtTotal);
		txtTotal.setColumns(10);

		lblPrecio = new JLabel("Precio: ");
		lblPrecio.setBounds(10, 114, 62, 14);
		getContentPane().add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setBounds(82, 111, 150, 20);
		getContentPane().add(txtPrecio);
		txtPrecio.setColumns(10);

		lblTmptotal = new JLabel("TmpTotal:");
		lblTmptotal.setBounds(276, 64, 56, 14);
		getContentPane().add(lblTmptotal);

		txtTiempoTotal = new JTextField();
		txtTiempoTotal.setBounds(344, 61, 150, 20);
		getContentPane().add(txtTiempoTotal);
		txtTiempoTotal.setColumns(10);

		btnFacturar = new JButton("Facturar");
		btnFacturar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				factura = new CrearFactura();
				CrearFactura.txtTicket.setText(txtTicket.getText());
				CrearFactura.txtPlaca.setText(txtPlaca.getText());
				CrearFactura.txtFechaIN.setText(txtFechaIN.getText());
				CrearFactura.txtHoraIN.setText(txtHoraIN.getText());
				CrearFactura.txtHoraSA.setText(txtHoraSA.getText());
				CrearFactura.txtTiempo.setText(txtTiempo.getText());
				CrearFactura.txtTotal.setText(txtTotal.getText());
				CrearFactura.txtTarifa.setText(tipoTarifa.getSelectedItem().toString());
				Ventana.dpkEscritorio.add(factura);
				factura.show();

			}
		});
		btnFacturar.setBounds(10, 139, 89, 23);
		getContentPane().add(btnFacturar);

		JLabel label = new JLabel("$$$:");
		label.setBounds(276, 114, 62, 14);
		getContentPane().add(label);

		txtIngreso = new JTextField();
		txtIngreso.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {

				char key = e.getKeyChar();
				if (key == KeyEvent.VK_ENTER) {

					double ingreso = Double.parseDouble(txtIngreso.getText());

					double total = 0;

					String h1 = txtHoraIN.getText();
					String h2 = txtHoraSA.getText();
					String f1 = txtFechaIN.getText() + " " + h1.substring(0, h1.length() - 3);
					String f2 = fecha + " " + h2.substring(0, h2.length() - 3);

					CalculoTiempo ct = new CalculoTiempo();
					String resultado = ct.calcularTiempo(f1, f2);
					String hm[] = resultado.split("\t");

					int horas = Integer.parseInt(hm[0]);
					int minutos = Integer.parseInt(hm[1]);

					txtTiempoTotal.setText(
							(horas < 9 ? "0" + horas : horas) + ":" + (minutos < 9 ? "0" + minutos : minutos) + ":00");

					if (horas <= 0 && minutos >= 10 && minutos <= 59) {
						total += 0.5;
					} else if (horas >= 1 && minutos >= 30 && minutos <= 59) {
						total += 0.5;
					}

					if (horas < 24) {
						total += (1.0 * horas);
					}

					if (horas >= 24) {
						double aux = horas / 24;
						total += (aux) * 5.0;
					}
					txtIngreso.setText("" + (ingreso - total));
					txtTotal.setText("" + total);

				}
			}
		});
		txtIngreso.setBounds(344, 111, 150, 20);
		getContentPane().add(txtIngreso);
		txtIngreso.setColumns(10);

		if (Ventana.idioma) {
			setBundles("ec.ups.edu.p3.idioma.mensaje_es_EC");
		} else {
			setBundles("ec.ups.edu.p3.idioma.mensaje_en_US");
		}

	}

	private void setBundles(String propertyFile) {

		ResourceBundle rb = ResourceBundle.getBundle(propertyFile);
		this.setTitle(rb.getString("SeleccionarTarifa"));
		this.lblTicket.setText(rb.getString("lblTicket"));
		this.lblPlaca.setText(rb.getString("Placa"));
		this.lblFechain.setText(rb.getString("Fechain"));
		this.lblHorain.setText(rb.getString("Horain"));
		this.lblHorasa.setText(rb.getString("Horasa"));
		this.lblTmptotal.setText(rb.getString("Tmptotal"));
		this.lblTipo.setText(rb.getString("Tipo"));
		this.lblTiempo.setText(rb.getString("Tiempo"));
		this.lblPrecio.setText(rb.getString("Precio"));
		this.lblTotal.setText(rb.getString("Total"));
		this.btnFacturar.setText(rb.getString("Facturar"));

	}

	public void idioma(boolean idioma) {
		idiomaFactura(idioma);
		if (idioma) {
			setBundles("ec.ups.edu.p3.idioma.mensaje_es_EC");
		} else {
			setBundles("ec.ups.edu.p3.idioma.mensaje_en_US");
		}
	}

	public void idiomaFactura(boolean idioma) {
		try {
			factura.idioma(idioma);
		} catch (Exception e) {

		}
	}
}
