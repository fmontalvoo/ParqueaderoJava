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
import ec.ups.edu.p3.entidades.Cliente;
import ec.ups.edu.p3.gestionBD.ClienteBD;

/**
 * 
 * @author fmont
 *
 */
public class ControlCliente extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	public JTextField txtCedula;
	private JTextField txtNombres;
	private JTextField txtTelefono;
	private JTextField txtDireccion;

	private JLabel lblCedula;
	private JLabel lblNombres;
	private JLabel lblTelefono;
	private JLabel lblDireccion;

	private JButton btnRegistrar;
	private JButton btnModificar;
	private JButton btnEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new ControlCliente().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ControlCliente() {
		setTitle("Registro");
		setClosable(true);
		setBounds(400, 150, 450, 150);
		getContentPane().setLayout(null);

		lblCedula = new JLabel("Cedula:");
		lblCedula.setBounds(10, 11, 65, 14);
		getContentPane().add(lblCedula);

		txtCedula = new JTextField();
		txtCedula.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {

				ClienteBD cliente = new ClienteBD();
				char key = e.getKeyChar();
				if (key == KeyEvent.VK_ENTER) {
					String cedula = txtCedula.getText();
					if (cliente.verificarCliente(cedula)) {
						Cliente c = cliente.leer(cedula);
						txtNombres.setText(c.getNombres());
						txtTelefono.setText(c.getTelefono());
						txtDireccion.setText(c.getDireccion());
					} else {
						JOptionPane.showMessageDialog(null, "CLIENTE NO EXISTE!!!!!", "Advertencia", 0);
					}
				}
			}
		});
		txtCedula.setBounds(85, 8, 240, 20);
		getContentPane().add(txtCedula);
		txtCedula.setColumns(10);

		txtNombres = new JTextField();
		txtNombres.setBounds(85, 33, 240, 20);
		getContentPane().add(txtNombres);
		txtNombres.setColumns(10);

		lblNombres = new JLabel("Nombres:");
		lblNombres.setBounds(10, 36, 65, 14);
		getContentPane().add(lblNombres);

		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(10, 61, 65, 14);
		getContentPane().add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(85, 58, 240, 20);
		getContentPane().add(txtTelefono);
		txtTelefono.setColumns(10);

		lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(10, 86, 65, 14);
		getContentPane().add(lblDireccion);

		txtDireccion = new JTextField();
		txtDireccion.setBounds(85, 83, 240, 20);
		getContentPane().add(txtDireccion);
		txtDireccion.setColumns(10);

		ClienteBD cbd = new ClienteBD();

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Cliente c = new Cliente();
				c.setCedula(txtCedula.getText());
				c.setNombres(txtNombres.getText());
				c.setTelefono(txtTelefono.getText());
				c.setDireccion(txtDireccion.getText());
				cbd.insertar(c);

			}
		});
		btnRegistrar.setBounds(335, 7, 89, 23);
		getContentPane().add(btnRegistrar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Cliente c = new Cliente();
				c.setCedula(txtCedula.getText());
				c.setNombres(txtNombres.getText());
				c.setTelefono(txtTelefono.getText());
				c.setDireccion(txtDireccion.getText());
				cbd.actualizar(c);

			}
		});
		btnModificar.setBounds(335, 32, 89, 23);
		getContentPane().add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cbd.borrar(txtCedula.getText());

			}
		});
		btnEliminar.setBounds(335, 57, 89, 23);
		getContentPane().add(btnEliminar);

		JButton button = new JButton("....");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtCedula.setText("");
				txtNombres.setText("");
				txtTelefono.setText("");
				txtDireccion.setText("");

			}
		});
		button.setBounds(335, 82, 89, 23);
		getContentPane().add(button);

		if (Ventana.idioma) {
			setBundles("ec.ups.edu.p3.idioma.mensaje_es_EC");
		} else {
			setBundles("ec.ups.edu.p3.idioma.mensaje_en_US");
		}

	}

	private void setBundles(String propertyFile) {

		ResourceBundle rb = ResourceBundle.getBundle(propertyFile);
		this.setTitle(rb.getString("Registro"));
		this.lblCedula.setText(rb.getString("Cedula"));
		this.lblNombres.setText(rb.getString("Nombres"));
		this.lblTelefono.setText(rb.getString("Telefono"));
		this.lblDireccion.setText(rb.getString("Direccion"));

		this.btnRegistrar.setText(rb.getString("Registrar"));
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
