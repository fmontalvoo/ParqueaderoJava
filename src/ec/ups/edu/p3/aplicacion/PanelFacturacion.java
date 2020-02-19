package ec.ups.edu.p3.aplicacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ec.ups.edu.p3.entidades.Auto;
import ec.ups.edu.p3.entidades.Estacionamiento;
import ec.ups.edu.p3.entidades.Lote;
import ec.ups.edu.p3.entidades.Ticket;
import ec.ups.edu.p3.frames.SeleccionarTarifa;
import ec.ups.edu.p3.gestionBD.EstacionamientoBD;
import ec.ups.edu.p3.gestionBD.TicketBD;
import ec.ups.edu.p3.graficos.PanelParqueadero;
import ec.ups.edu.p3.hilo.Movimiento;
import ec.ups.edu.p3.hilo.Reloj;

/**
 * 
 * @author fmont
 *
 */
public class PanelFacturacion extends JPanel {

	private static final long serialVersionUID = 1L;
	private int ancho;
	private int alto;
	private static ArrayList<Auto> autos = new ArrayList<Auto>();
	private static ArrayList<Lote> lotes = new ArrayList<Lote>();
	private static Movimiento mover = new Movimiento();
	private Thread in;
	private static Thread out;
	private static JTextField txtPlaca;
	private JTextField txtFecha;
	private JTextField txtTicket;
	private JTextArea txtArea;
	public static JButton btnIngresar;
	public static JButton btnSalir;
	private JLabel label;

	private static JLabel lblPlaca;
	private static JLabel lblFecha;
	private static JLabel lblTicket;

	private static SeleccionarTarifa seleccion;

	private static int ocupados;
	private int numLote;
	private Reloj r;
	private static boolean estado = false;
	private static boolean estado1 = false;

	public PanelFacturacion(int ancho, int alto) {

		this.ancho = ancho;
		this.alto = alto;
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);

		lblPlaca = new JLabel("Placa:");
		lblPlaca.setBounds(10, 17, 46, 14);
		this.add(lblPlaca);

		lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(10, 55, 46, 14);
		this.add(lblFecha);

		lblTicket = new JLabel("Ticket:");
		lblTicket.setBounds(10, 101, 46, 14);
		this.add(lblTicket);

		txtPlaca = new JTextField();
		txtPlaca.setBounds(66, 14, 170, 20);
		this.add(txtPlaca);
		txtPlaca.setColumns(10);

		txtFecha = new JTextField();
		txtFecha.setBounds(66, 52, 170, 20);
		txtFecha.setEditable(false);
		this.add(txtFecha);
		txtFecha.setColumns(10);

		txtTicket = new JTextField();
		txtTicket.setEditable(false);
		txtTicket.setText("");
		txtTicket.setBounds(66, 98, 86, 20);
		this.add(txtTicket);
		txtTicket.setColumns(10);

		Random rnd = new Random();

		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtPlaca.getText().isEmpty()) {
					if (autos.size() <= lotes.size() - 1) {
						Auto a = new Auto();
						a.setPlaca(txtPlaca.getText());
						a.setX(-30);
						a.setY(210);
						a.setParqueado(false);
						autos.add(a);
						mover.autos(autos);
						do {
							numLote = (int) (rnd.nextDouble() * lotes.size());
							if (!lotes.get(numLote).getEstado()) {
								Estacionamiento es = new Estacionamiento();
								EstacionamientoBD ebd = new EstacionamientoBD();

								es.setId_estacionamiento((numLote + 1));
								es.setLote((numLote + 1));
								es.setEstado(true);

								ebd.actualizar(es);
								mover.iniciar(true, numLote);
								PanelParqueadero.ocupados++;
							}
						} while (lotes.get(numLote).getEstado());
						in = new Thread(mover, "Ingreso");
						in.start();
					} else {
						JOptionPane.showMessageDialog(null, "YA NO HAY CHANCE!!!!!!!", "Advertencia", 0);
					}
				}

			}
		});
		btnIngresar.setBounds(10, 138, 89, 23);
		this.add(btnIngresar);

		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				seleccion = new SeleccionarTarifa();
				SeleccionarTarifa.fecha = txtFecha.getText();
				SeleccionarTarifa.txtHoraSA.setText(r.hora());
				Ventana.dpkEscritorio.add(seleccion);
				seleccion.show();

			}
		});
		btnSalir.setBounds(147, 138, 89, 23);
		this.add(btnSalir);

		label = new JLabel();
		label.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 18));
		r = new Reloj(label);
		label.setBounds(800, 55, 200, 36);
		this.add(label);

		JButton btnImprimir = new JButton("");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtArea.setText("");
				Ticket t = new Ticket();
				TicketBD tbd = new TicketBD();
				txtTicket.setText("" + (tbd.consultarID() + 1));
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

				try {
					Date fecha_in = formatoDelTexto.parse(txtFecha.getText());
					t.setId_ticket(Integer.parseInt(txtTicket.getText()));
					t.setPlaca(txtPlaca.getText());
					t.setFecha_in(fecha_in);
					t.setHora_in(r.hora());
					tbd.insertar(t);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				txtArea.append("Ticket N#: " + t.getId_ticket() + "\nPlaca: " + t.getPlaca() + "\nFecha Emision: "
						+ formatoDelTexto.format(t.getFecha_in()) + "\nHora Ingreso: " + t.getHora_in());

			}
		});
		btnImprimir.setIcon(new ImageIcon(getClass().getClassLoader().getResource("impresora.png")));
		btnImprimir.setBounds(261, 117, 46, 44);
		this.add(btnImprimir);

		JButton btnRefrescar = new JButton("");
		btnRefrescar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtPlaca.setText("");
				txtArea.setText("");

			}
		});
		btnRefrescar.setIcon(new ImageIcon(getClass().getClassLoader().getResource("refresh.png")));
		btnRefrescar.setBounds(261, 17, 46, 44);
		this.add(btnRefrescar);
		JScrollPane pictureScrollPane = new JScrollPane();
		pictureScrollPane.setBounds(379, 11, 360, 150);
		this.add(pictureScrollPane);

		txtArea = new JTextArea();
		txtArea.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 20));
		pictureScrollPane.setViewportView(txtArea);
		txtArea.setEditable(false);

		Calendar cal = Calendar.getInstance();
		int año = cal.get(Calendar.YEAR);
		String mes = (cal.get(Calendar.MONTH) + 1) > 9 ? "" + (cal.get(Calendar.MONTH) + 1)
				: "0" + (cal.get(Calendar.MONTH) + 1);
		String dd = cal.get(Calendar.DAY_OF_MONTH) > 9 ? "" + cal.get(Calendar.DAY_OF_MONTH)
				: "0" + cal.get(Calendar.DAY_OF_MONTH);

		txtFecha.setText(año + "-" + mes + "-" + dd);

		if (Ventana.idioma) {
			setBundles("ec.ups.edu.p3.idioma.mensaje_es_EC");
		} else {
			setBundles("ec.ups.edu.p3.idioma.mensaje_en_US");
		}

	}

	private static void setBundles(String propertyFile) {

		ResourceBundle rb = ResourceBundle.getBundle(propertyFile);

		lblPlaca.setText(rb.getString("Placa"));
		lblFecha.setText(rb.getString("Fecha"));
		lblTicket.setText(rb.getString("lblTicket"));
		btnIngresar.setText(rb.getString("Ingresar"));
		btnSalir.setText(rb.getString("Salir"));

	}

	public static void idioma(boolean idioma) {
		idiomaSLTarifa(idioma);
		if (idioma) {
			setBundles("ec.ups.edu.p3.idioma.mensaje_es_EC");
		} else {
			setBundles("ec.ups.edu.p3.idioma.mensaje_en_US");
		}

	}

	public static void idiomaSLTarifa(boolean idioma) {
		try {
			seleccion.idioma(idioma);
		} catch (Exception e) {

		}
	}

	public static void array(ArrayList<Lote> lote) {
		lotes = lote;
	}

	public static void abrirB1() {
		if (!estado) {
			estado = true;
			PanelParqueadero.control(estado);
		}
	}

	public static void cerrarB1() {
		if (estado) {
			estado = false;
			PanelParqueadero.control(estado);
		}
	}

	public static void abrirB2() {
		if (!estado1) {
			estado1 = true;
			PanelParqueadero.control1(estado1);
		}
	}

	public static void cerrarB2() {
		if (estado1) {
			estado1 = false;
			PanelParqueadero.control1(estado1);
		}
	}

	public static void ocupados(int ocupado) {
		ocupados = ocupado;
	}

	public static int salida() {
		int lote = 0;
		for (int i = 0; i < autos.size(); i++) {
			if (txtPlaca.getText().equals(autos.get(i).getPlaca())) {
				lote = (autos.get(i).getLote() + 1);
				Movimiento.salida = true;
				mover.autos(autos);
				Estacionamiento es = new Estacionamiento();
				EstacionamientoBD ebd = new EstacionamientoBD();

				es.setId_estacionamiento((lote));
				es.setLote((lote));
				es.setEstado(false);
				ebd.actualizar(es);
				mover.salir(true, i);
				PanelParqueadero.ocupados--;
				out = new Thread(mover, "Salida");
				out.start();
			}
		}
		return lote;
	}

}
