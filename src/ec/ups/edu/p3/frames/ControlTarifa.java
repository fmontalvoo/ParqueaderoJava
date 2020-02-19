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
import ec.ups.edu.p3.entidades.Tarifa;
import ec.ups.edu.p3.gestionBD.TarifaBD;

/**
 * 
 * @author fmont
 *
 */
public class ControlTarifa extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtID_Tarifa;
	private JTextField txtTipo;
	private JTextField txtTiempo;
	private JTextField txtPrecio;

	private TarifaBD tbd;

	private JLabel lblIdtarifa;
	private JLabel lblTipo;//
	private JLabel lblTiempo;//
	private JLabel lblPrecio;//

	private JButton btnIngresar;//
	private JButton btnModificar;//
	private JButton btnEliminar;//

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new ControlTarifa().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ControlTarifa() {
		setTitle("Tarifa");
		setClosable(true);
		setBounds(400, 150, 450, 160);
		getContentPane().setLayout(null);

		lblIdtarifa = new JLabel("ID_Tarifa:");
		lblIdtarifa.setBounds(10, 11, 65, 14);
		getContentPane().add(lblIdtarifa);

		txtID_Tarifa = new JTextField();
		txtID_Tarifa.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {

				tbd = new TarifaBD();
				char key = e.getKeyChar();
				if (key == KeyEvent.VK_ENTER) {
					int id = Integer.parseInt(txtID_Tarifa.getText());
					if (tbd.verificarTarifa(id)) {
						Tarifa t = tbd.leer(id);
						txtTipo.setText(t.getTipo());
						txtTiempo.setText(t.getTiempo());
						txtPrecio.setText("" + t.getPrecio());
					} else {
						JOptionPane.showMessageDialog(null, "TARIFA NO EXISTE!!!!!", "Advertencia", 0);
					}
				}
			}
		});
		txtID_Tarifa.setBounds(85, 8, 100, 20);
		getContentPane().add(txtID_Tarifa);
		txtID_Tarifa.setColumns(10);

		txtTipo = new JTextField();
		txtTipo.setBounds(324, 8, 100, 20);
		getContentPane().add(txtTipo);
		txtTipo.setColumns(10);

		lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(249, 11, 65, 14);
		getContentPane().add(lblTipo);

		lblTiempo = new JLabel("Tiempo:");
		lblTiempo.setBounds(10, 36, 65, 14);
		getContentPane().add(lblTiempo);

		txtTiempo = new JTextField();
		txtTiempo.setBounds(85, 33, 100, 20);
		getContentPane().add(txtTiempo);
		txtTiempo.setColumns(10);

		lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(249, 36, 65, 14);
		getContentPane().add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setBounds(324, 33, 100, 20);
		getContentPane().add(txtPrecio);
		txtPrecio.setColumns(10);

		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tbd = new TarifaBD();
				Tarifa t = new Tarifa();
				txtID_Tarifa.setText("" + (tbd.consultarID() + 1));

				t.setId_tarifa(Integer.parseInt(txtID_Tarifa.getText()));
				t.setTipo(txtTipo.getText());
				t.setTiempo(txtTiempo.getText());
				t.setPrecio(Double.parseDouble(txtPrecio.getText()));

				tbd.insertar(t);

			}
		});
		btnIngresar.setBounds(10, 61, 89, 23);
		getContentPane().add(btnIngresar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Tarifa t = new Tarifa();
				t.setId_tarifa(Integer.parseInt(txtID_Tarifa.getText()));
				t.setTipo(txtTipo.getText());
				t.setTiempo(txtTiempo.getText());
				t.setPrecio(Double.parseDouble(txtPrecio.getText()));

				tbd.actualizar(t);

			}
		});
		btnModificar.setBounds(172, 61, 89, 23);
		getContentPane().add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tbd.borrar(Integer.parseInt(txtID_Tarifa.getText()));
			}
		});
		btnEliminar.setBounds(335, 61, 89, 23);
		getContentPane().add(btnEliminar);

		JButton button = new JButton("....");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtID_Tarifa.setText("");
				txtTipo.setText("");
				txtTiempo.setText("");
				txtPrecio.setText("");

			}
		});
		button.setBounds(172, 96, 89, 23);
		getContentPane().add(button);

		if (Ventana.idioma) {
			setBundles("ec.ups.edu.p3.idioma.mensaje_es_EC");
		} else {
			setBundles("ec.ups.edu.p3.idioma.mensaje_en_US");
		}

	}

	private void setBundles(String propertyFile) {

		ResourceBundle rb = ResourceBundle.getBundle(propertyFile);
		this.setTitle(rb.getString("Tarifa"));
		this.lblIdtarifa.setText(rb.getString("lblIdtarifa"));
		this.lblTipo.setText(rb.getString("Tipo"));
		this.lblTiempo.setText(rb.getString("Tiempo"));
		this.lblPrecio.setText(rb.getString("Precio"));
		this.btnModificar.setText(rb.getString("Modificar"));
		this.btnEliminar.setText(rb.getString("Eliminar"));
		this.btnIngresar.setText(rb.getString("btnIngresar"));

	}

	public void idioma(boolean idioma) {
		if (idioma) {
			setBundles("ec.ups.edu.p3.idioma.mensaje_es_EC");
		} else {
			setBundles("ec.ups.edu.p3.idioma.mensaje_en_US");
		}
	}

}
