package ec.ups.edu.p3.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ec.ups.edu.p3.aplicacion.Ventana;
import ec.ups.edu.p3.entidades.Estacionamiento;
import ec.ups.edu.p3.gestionBD.EstacionamientoBD;

/**
 * 
 * @author fmont
 *
 */
public class ControlLote extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtIdLote;
	private JTextField txtLote;
	private JTextField txtEstado;

	private EstacionamientoBD ebd;

	private JLabel lblIdlote;
	private JLabel lblLote;
	private JLabel lblEstado;

	private JButton btnModificar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new ControlLote().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ControlLote() {
		setTitle("Lote");
		setClosable(true);
		setBounds(400, 150, 355, 120);
		getContentPane().setLayout(null);

		lblIdlote = new JLabel("Id_Lote:");
		lblIdlote.setBounds(10, 11, 60, 14);
		getContentPane().add(lblIdlote);

		txtIdLote = new JTextField();
		txtIdLote.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {

				ebd = new EstacionamientoBD();
				char key = e.getKeyChar();
				if (key == KeyEvent.VK_ENTER) {
					int id = Integer.parseInt(txtIdLote.getText());
					if (ebd.verificarLote(id)) {
						Estacionamiento es = ebd.leer(id);
						txtIdLote.setText("" + es.getId_estacionamiento());
						txtLote.setText("" + es.getLote());
						txtEstado.setText("" + es.Estado());
					} else {
						JOptionPane.showMessageDialog(null, "LOTE NO EXISTE!!!!!", "Advertencia", 0);
					}
				}

			}
		});
		txtIdLote.setBounds(80, 8, 100, 20);
		getContentPane().add(txtIdLote);
		txtIdLote.setColumns(10);

		lblLote = new JLabel("Lote:");
		lblLote.setBounds(10, 36, 60, 14);
		getContentPane().add(lblLote);

		txtLote = new JTextField();
		txtLote.setBounds(80, 33, 100, 20);
		getContentPane().add(txtLote);
		txtLote.setColumns(10);

		lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(10, 61, 60, 14);
		getContentPane().add(lblEstado);

		txtEstado = new JTextField();
		txtEstado.setBounds(80, 58, 100, 20);
		getContentPane().add(txtEstado);
		txtEstado.setColumns(10);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Estacionamiento es = new Estacionamiento();
				es.setId_estacionamiento(Integer.parseInt(txtIdLote.getText()));
				es.setLote(Integer.parseInt(txtLote.getText()));
				es.setEstado(Boolean.valueOf(txtEstado.getText()));
				ebd.actualizar(es);

			}
		});
		btnModificar.setBounds(240, 7, 89, 23);
		getContentPane().add(btnModificar);

		JButton button = new JButton(".....");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtIdLote.setText("");
				txtLote.setText("");
				txtEstado.setText("");

			}
		});
		button.setBounds(240, 57, 89, 23);
		getContentPane().add(button);

		if (Ventana.idioma) {
			setBundles("ec.ups.edu.p3.idioma.mensaje_es_EC");
		} else {
			setBundles("ec.ups.edu.p3.idioma.mensaje_en_US");
		}

	}

	private void setBundles(String propertyFile) {

		ResourceBundle rb = ResourceBundle.getBundle(propertyFile);
		this.setTitle(rb.getString("Lote"));
		this.lblIdlote.setText(rb.getString("lblIdlote"));
		this.lblLote.setText(rb.getString("lblLote"));
		this.lblEstado.setText(rb.getString("Estado"));
		this.btnModificar.setText(rb.getString("Modificar"));

	}

	public void idioma(boolean idioma) {
		if (idioma) {
			setBundles("ec.ups.edu.p3.idioma.mensaje_es_EC");
		} else {
			setBundles("ec.ups.edu.p3.idioma.mensaje_en_US");
		}
	}

}
