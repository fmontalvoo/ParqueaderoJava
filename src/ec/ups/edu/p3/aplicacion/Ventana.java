package ec.ups.edu.p3.aplicacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ec.ups.edu.p3.frames.ControlCliente;
import ec.ups.edu.p3.frames.ControlFactura;
import ec.ups.edu.p3.frames.ControlLote;
import ec.ups.edu.p3.frames.ControlTarifa;
import ec.ups.edu.p3.frames.ControlTicket;
import ec.ups.edu.p3.frames.Gestion;
import ec.ups.edu.p3.frames.ListarClientes;
import ec.ups.edu.p3.frames.ListarEstacionamiento;
import ec.ups.edu.p3.frames.ListarFacturas;
import ec.ups.edu.p3.frames.ListarTarifas;
import ec.ups.edu.p3.frames.ListarTickets;

/**
 * 
 * @author fmont
 *
 */
public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;

	private Gestion gestion;
	private ControlCliente cc;
	private ControlFactura cf;
	private ControlTicket ct;
	private ControlTarifa tr;
	private ControlLote cl;

	private ListarClientes lc;
	private ListarFacturas lf;
	private ListarTickets lt;
	private ListarTarifas ltr;
	private ListarEstacionamiento le;

	public static JDesktopPane dpkEscritorio;

	private JMenu mnInicio;
	private JMenuItem mntmGestion;

	private JMenu mnControl;
	private JMenuItem mntmCliente;
	private JMenuItem mntmFactura;
	private JMenuItem mntmTicket;
	private JMenuItem mntmTarifa;
	private JMenuItem mntmLote;

	private JMenu mnTablas;
	private JMenuItem mntmClientes;
	private JMenuItem mntmFacturas;
	private JMenuItem mntmTickets;
	private JMenuItem mntmTarifas;
	private JMenuItem mntmEstacionamiento;

	private JMenu mnIdioma;
	private JMenuItem mntmEspaniol;
	private JMenuItem mntmIngles;

	public static boolean idioma = true;

	public static void main(String[] args) {
		new Ventana().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public Ventana() {

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		setTitle("Parqueadero");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, d.width, d.height - 40);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnInicio = new JMenu("Inicio");
		menuBar.add(mnInicio);

		dpkEscritorio = new JDesktopPane();

		mntmGestion = new JMenuItem("Gestion");
		mntmGestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gestion = new Gestion();
				dpkEscritorio.add(gestion);
				gestion.show();

			}
		});
		mnInicio.add(mntmGestion);

		mnControl = new JMenu("Control");
		menuBar.add(mnControl);

		mntmCliente = new JMenuItem("Cliente");
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cc = new ControlCliente();
				dpkEscritorio.add(cc);
				cc.show();

			}
		});
		mnControl.add(mntmCliente);

		mntmFactura = new JMenuItem("Factura");
		mntmFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cf = new ControlFactura();
				dpkEscritorio.add(cf);
				cf.show();

			}
		});
		mnControl.add(mntmFactura);

		mntmTicket = new JMenuItem("Ticket");
		mntmTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ct = new ControlTicket();
				dpkEscritorio.add(ct);
				ct.show();

			}
		});
		mnControl.add(mntmTicket);

		mntmTarifa = new JMenuItem("Tarifa");
		mntmTarifa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tr = new ControlTarifa();
				dpkEscritorio.add(tr);
				tr.show();

			}
		});
		mnControl.add(mntmTarifa);

		mntmLote = new JMenuItem("Lote");
		mntmLote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cl = new ControlLote();
				dpkEscritorio.add(cl);
				cl.show();

			}
		});
		mnControl.add(mntmLote);

		mnTablas = new JMenu("Tablas");
		menuBar.add(mnTablas);

		mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lc = new ListarClientes();
				dpkEscritorio.add(lc);
				lc.show();

			}
		});
		mnTablas.add(mntmClientes);

		mntmFacturas = new JMenuItem("Facturas");
		mntmFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lf = new ListarFacturas();
				dpkEscritorio.add(lf);
				lf.show();

			}
		});
		mnTablas.add(mntmFacturas);

		mntmTickets = new JMenuItem("Tickets");
		mntmTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lt = new ListarTickets();
				dpkEscritorio.add(lt);
				lt.show();

			}
		});
		mnTablas.add(mntmTickets);

		mntmTarifas = new JMenuItem("Tarifas");
		mntmTarifas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ltr = new ListarTarifas();
				dpkEscritorio.add(ltr);
				ltr.show();

			}
		});
		mnTablas.add(mntmTarifas);

		mntmEstacionamiento = new JMenuItem("Estacionamiento");
		mntmEstacionamiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				le = new ListarEstacionamiento();
				dpkEscritorio.add(le);
				le.show();

			}
		});
		mnTablas.add(mntmEstacionamiento);

		mnIdioma = new JMenu("Idioma");
		menuBar.add(mnIdioma);

		mntmEspaniol = new JMenuItem("Espa\u00F1ol");
		mntmEspaniol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setBundles("ec.ups.edu.p3.idioma.mensaje_es_EC");
				idioma = true;
				idiomaGestion(idioma);
				idiomaRegistro(idioma);
				idiomaFactura(idioma);
				idiomaTicket(idioma);
				idiomaTarifa(idioma);
				idiomaLote(idioma);

				idiomaLClientes(idioma);
				idiomaLFacturas(idioma);
				idiomaLTickets(idioma);
				idiomaLTarifas(idioma);
				idiomaLEstacionamiento(idioma);

			}
		});
		mnIdioma.add(mntmEspaniol);

		mntmIngles = new JMenuItem("Ingles");
		mntmIngles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setBundles("ec.ups.edu.p3.idioma.mensaje_en_US");
				idioma = false;
				idiomaGestion(idioma);
				idiomaRegistro(idioma);
				idiomaFactura(idioma);
				idiomaTicket(idioma);
				idiomaTarifa(idioma);
				idiomaLote(idioma);

				idiomaLClientes(idioma);
				idiomaLFacturas(idioma);
				idiomaLTickets(idioma);
				idiomaLTarifas(idioma);
				idiomaLEstacionamiento(idioma);

			}
		});
		mnIdioma.add(mntmIngles);

		dpkEscritorio.setBackground(SystemColor.activeCaption);
		getContentPane().add(dpkEscritorio, BorderLayout.CENTER);
		dpkEscritorio.setLayout(null);

	}

	private void setBundles(String propertyFile) {

		ResourceBundle rb = ResourceBundle.getBundle(propertyFile);
		this.setTitle(rb.getString("Parqueadero"));

		this.mnInicio.setText(rb.getString("Inicio"));
		this.mntmGestion.setText(rb.getString("Gestion"));

		this.mnControl.setText(rb.getString("Control"));
		this.mntmCliente.setText(rb.getString("Cliente"));
		this.mntmFactura.setText(rb.getString("Factura"));
		this.mntmTicket.setText(rb.getString("Ticket"));
		this.mntmTarifa.setText(rb.getString("Tarifa"));
		this.mntmLote.setText(rb.getString("Lote"));

		this.mnTablas.setText(rb.getString("Tablas"));
		this.mntmClientes.setText(rb.getString("Clientes"));
		this.mntmFacturas.setText(rb.getString("Facturas"));
		this.mntmTickets.setText(rb.getString("Tickets"));
		this.mntmTarifas.setText(rb.getString("Tarifas"));
		this.mntmEstacionamiento.setText(rb.getString("Estacionamiento"));

		this.mnIdioma.setText(rb.getString("Idioma"));
		this.mntmEspaniol.setText(rb.getString("Español"));
		this.mntmIngles.setText(rb.getString("Ingles"));

	}

	public void idiomaGestion(boolean idioma) {
		try {
			gestion.idioma(idioma);
			PanelFacturacion.idioma(idioma);
		} catch (Exception e) {

		}
	}

	public void idiomaRegistro(boolean idioma) {
		try {
			cc.idioma(idioma);
		} catch (Exception e) {

		}
	}

	public void idiomaFactura(boolean idioma) {
		try {
			cf.idioma(idioma);
		} catch (Exception e) {

		}
	}

	public void idiomaTicket(boolean idioma) {
		try {
			ct.idioma(idioma);
		} catch (Exception e) {

		}
	}

	public void idiomaTarifa(boolean idioma) {
		try {
			tr.idioma(idioma);
		} catch (Exception e) {

		}
	}

	public void idiomaLote(boolean idioma) {
		try {
			cl.idioma(idioma);
		} catch (Exception e) {

		}
	}

	public void idiomaLClientes(boolean idioma) {
		try {
			lc.idioma(idioma);
		} catch (Exception e) {

		}
	}

	public void idiomaLFacturas(boolean idioma) {
		try {
			lf.idioma(idioma);
		} catch (Exception e) {

		}
	}

	public void idiomaLTickets(boolean idioma) {
		try {
			lt.idioma(idioma);
		} catch (Exception e) {

		}
	}

	public void idiomaLTarifas(boolean idioma) {
		try {
			ltr.idioma(idioma);
		} catch (Exception e) {

		}
	}

	public void idiomaLEstacionamiento(boolean idioma) {
		try {
			le.idioma(idioma);
		} catch (Exception e) {

		}
	}

}
