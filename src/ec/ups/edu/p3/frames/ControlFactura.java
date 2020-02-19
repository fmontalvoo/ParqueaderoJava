package ec.ups.edu.p3.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ec.ups.edu.p3.aplicacion.Ventana;
import ec.ups.edu.p3.entidades.Factura;
import ec.ups.edu.p3.gestionBD.FacturaBD;

/**
 * 
 * @author fmont
 *
 */
public class ControlFactura extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtFacn;
	private JTextField txtFechaem;
	private JTextField txtCedula;
	private JTextField txtTarifa;
	private JTextField txtTicket;
	private JTextField txtLote;
	private JTextField txtTotal;

	private FacturaBD fbd;

	private JLabel lblFacn;//
	private JLabel lblFechaem;//
	private JLabel lblCedula;//
	private JLabel lblTarifa;//
	private JLabel lblTicket;//
	private JLabel lblLote;
	private JLabel lblTotal;//

	private JButton btnModificar;
	private JButton btnEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new ControlFactura().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ControlFactura() {
		setTitle("Factura");
		setClosable(true);
		setBounds(400, 150, 450, 200);
		getContentPane().setLayout(null);

		lblFacn = new JLabel("Fac_N#: ");
		lblFacn.setBounds(10, 11, 60, 14);
		getContentPane().add(lblFacn);

		txtFacn = new JTextField();
		txtFacn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				fbd = new FacturaBD();
				char key = e.getKeyChar();
				if (key == KeyEvent.VK_ENTER) {
					int id = Integer.parseInt(txtFacn.getText());
					if (fbd.verificarFactura(id)) {
						Factura f = fbd.leer(id);
						txtCedula.setText(f.getCedula());
						txtTarifa.setText("" + f.getTarifa());
						txtTicket.setText("" + f.getTicket());
						txtLote.setText("" + f.getEstacionamiento());
						txtTotal.setText("" + f.getTotal());
						try {
							txtFechaem.setText("" + new SimpleDateFormat("yyyy-MM-dd").format(f.getFecha_emision()));
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "FACTURA NO EXISTE!!!!!", "Advertencia", 0);
					}
				}

			}
		});
		txtFacn.setBounds(80, 8, 105, 20);
		getContentPane().add(txtFacn);
		txtFacn.setColumns(10);

		txtFechaem = new JTextField();
		txtFechaem.setBounds(319, 8, 105, 20);
		getContentPane().add(txtFechaem);
		txtFechaem.setColumns(10);

		lblFechaem = new JLabel("FechaEM:");
		lblFechaem.setBounds(249, 11, 60, 14);
		getContentPane().add(lblFechaem);

		lblCedula = new JLabel("Cedula:");
		lblCedula.setBounds(10, 36, 60, 14);
		getContentPane().add(lblCedula);

		txtCedula = new JTextField();
		txtCedula.setBounds(80, 33, 105, 20);
		getContentPane().add(txtCedula);
		txtCedula.setColumns(10);

		lblTarifa = new JLabel("Tarifa:");
		lblTarifa.setBounds(249, 36, 60, 14);
		getContentPane().add(lblTarifa);

		txtTarifa = new JTextField();
		txtTarifa.setBounds(319, 33, 105, 20);
		getContentPane().add(txtTarifa);
		txtTarifa.setColumns(10);

		lblTicket = new JLabel("Ticket:");
		lblTicket.setBounds(10, 61, 60, 14);
		getContentPane().add(lblTicket);

		txtTicket = new JTextField();
		txtTicket.setBounds(80, 58, 105, 20);
		getContentPane().add(txtTicket);
		txtTicket.setColumns(10);

		lblLote = new JLabel("Lote:");
		lblLote.setBounds(249, 61, 60, 14);
		getContentPane().add(lblLote);

		txtLote = new JTextField();
		txtLote.setBounds(319, 58, 105, 20);
		getContentPane().add(txtLote);
		txtLote.setColumns(10);

		lblTotal = new JLabel("Total:");
		lblTotal.setBounds(10, 86, 60, 14);
		getContentPane().add(lblTotal);

		txtTotal = new JTextField();
		txtTotal.setBounds(80, 83, 105, 20);
		getContentPane().add(txtTotal);
		txtTotal.setColumns(10);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Factura f = new Factura();
				f.setId_factura(Integer.parseInt(txtFacn.getText()));
				f.setCedula(txtCedula.getText());
				f.setTarifa(Integer.parseInt(txtTarifa.getText()));
				f.setTicket(Integer.parseInt(txtTicket.getText()));
				f.setEstacionamiento(Integer.parseInt(txtLote.getText()));
				f.setTotal(Double.parseDouble(txtTotal.getText()));

				try {
					f.setFecha_emision(new SimpleDateFormat("yyyy-MM-dd").parse(txtFechaem.getText()));
				} catch (ParseException ex) {
					ex.printStackTrace();
				}

				fbd.actualizar(f);

			}
		});
		btnModificar.setBounds(10, 136, 89, 23);
		getContentPane().add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				fbd.borrar(Integer.parseInt(txtFacn.getText()));

			}
		});
		btnEliminar.setBounds(180, 136, 89, 23);
		getContentPane().add(btnEliminar);

		JButton button = new JButton(".....");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtFacn.setText("");
				txtFechaem.setText("");
				txtCedula.setText("");
				txtTicket.setText("");
				txtTarifa.setText("");
				txtLote.setText("");
				txtTotal.setText("");

			}
		});
		button.setBounds(335, 136, 89, 23);
		getContentPane().add(button);

		if (Ventana.idioma) {
			setBundles("ec.ups.edu.p3.idioma.mensaje_es_EC");
		} else {
			setBundles("ec.ups.edu.p3.idioma.mensaje_en_US");
		}

	}

	private void setBundles(String propertyFile) {

		ResourceBundle rb = ResourceBundle.getBundle(propertyFile);
		this.setTitle(rb.getString("Factura"));
		this.lblFacn.setText(rb.getString("Facn"));
		this.lblFechaem.setText(rb.getString("Fechaem"));
		this.lblCedula.setText(rb.getString("Cedula"));
		this.lblTarifa.setText(rb.getString("lblTarifa"));
		this.lblTicket.setText(rb.getString("lblTicket"));
		this.lblLote.setText(rb.getString("lblLote"));
		this.btnModificar.setText(rb.getString("Modificar"));
		this.btnEliminar.setText(rb.getString("Eliminar"));

	}

	public void idioma(boolean idioma) {
		if (idioma) {
			setBundles("ec.ups.edu.p3.idioma.mensaje_es_EC");
		} else {
			setBundles("ec.ups.edu.p3.idioma.mensaje_en_US");
		}
	}

}
