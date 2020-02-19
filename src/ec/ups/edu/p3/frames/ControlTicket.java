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
import ec.ups.edu.p3.entidades.Ticket;
import ec.ups.edu.p3.gestionBD.TicketBD;

/**
 * 
 * @author fmont
 *
 */
public class ControlTicket extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtTicket;
	private JTextField txtPlaca;
	private JTextField txtFechain;
	private JTextField txtHorain;

	private TicketBD tbd;

	private JLabel lblTicket;//
	private JLabel lblPlaca;//
	private JLabel lblFechain;//
	private JLabel lblHorain;//

	private JButton btnModificar;//
	private JButton btnEliminar;//

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new ControlTicket().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ControlTicket() {
		setTitle("Ticket");
		setClosable(true);
		setBounds(400, 150, 450, 140);
		getContentPane().setLayout(null);

		lblTicket = new JLabel("Ticket N#:");
		lblTicket.setBounds(10, 11, 60, 14);
		getContentPane().add(lblTicket);

		txtTicket = new JTextField();
		txtTicket.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				tbd = new TicketBD();
				char key = e.getKeyChar();
				if (key == KeyEvent.VK_ENTER) {
					int id = Integer.parseInt(txtTicket.getText());
					if (tbd.verificarTicket(id)) {
						Ticket t = tbd.leer(id);
						txtPlaca.setText(t.getPlaca());
						txtFechain.setText(new SimpleDateFormat("yyyy-MM-dd").format(t.getFecha_in()));
						txtHorain.setText(t.getHora_in());
					} else {
						JOptionPane.showMessageDialog(null, "TICKET NO EXISTE!!!!!", "Advertencia", 0);
					}
				}
			}
		});
		txtTicket.setBounds(80, 8, 100, 20);
		getContentPane().add(txtTicket);
		txtTicket.setColumns(10);

		txtPlaca = new JTextField();
		txtPlaca.setBounds(324, 8, 100, 20);
		getContentPane().add(txtPlaca);
		txtPlaca.setColumns(10);

		lblPlaca = new JLabel("Placa:");
		lblPlaca.setBounds(254, 11, 60, 14);
		getContentPane().add(lblPlaca);

		lblFechain = new JLabel("FechaIN:");
		lblFechain.setBounds(10, 36, 60, 14);
		getContentPane().add(lblFechain);

		txtFechain = new JTextField();
		txtFechain.setBounds(80, 33, 100, 20);
		getContentPane().add(txtFechain);
		txtFechain.setColumns(10);

		lblHorain = new JLabel("HoraIN:");
		lblHorain.setBounds(254, 36, 60, 14);
		getContentPane().add(lblHorain);

		txtHorain = new JTextField();
		txtHorain.setBounds(324, 33, 100, 20);
		getContentPane().add(txtHorain);
		txtHorain.setColumns(10);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Ticket t = new Ticket();
				t.setId_ticket(Integer.parseInt(txtTicket.getText()));
				t.setPlaca(txtPlaca.getText());
				t.setHora_in(txtHorain.getText());
				try {
					t.setFecha_in(new SimpleDateFormat("yyyy-MM-dd").parse(txtFechain.getText()));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				tbd.actualizar(t);

			}
		});
		btnModificar.setBounds(10, 76, 89, 23);
		getContentPane().add(btnModificar);

		JButton button = new JButton(".....");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtTicket.setText("");
				txtPlaca.setText("");
				txtFechain.setText("");
				txtHorain.setText("");

			}
		});
		button.setBounds(335, 76, 89, 23);
		getContentPane().add(button);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tbd.borrar(Integer.parseInt(txtTicket.getText()));

			}
		});
		btnEliminar.setBounds(170, 76, 89, 23);
		getContentPane().add(btnEliminar);

		if (Ventana.idioma) {
			setBundles("ec.ups.edu.p3.idioma.mensaje_es_EC");
		} else {
			setBundles("ec.ups.edu.p3.idioma.mensaje_en_US");
		}

	}

	private void setBundles(String propertyFile) {

		ResourceBundle rb = ResourceBundle.getBundle(propertyFile);

		this.lblTicket.setText(rb.getString("lblTicket"));
		this.lblPlaca.setText(rb.getString("Placa"));
		this.lblFechain.setText(rb.getString("Fechain"));
		this.lblHorain.setText(rb.getString("Horain"));
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
