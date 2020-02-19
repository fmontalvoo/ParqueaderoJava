package ec.ups.edu.p3.frames;

import java.util.ResourceBundle;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ec.ups.edu.p3.aplicacion.PanelFacturacion;
import ec.ups.edu.p3.aplicacion.Ventana;
import ec.ups.edu.p3.graficos.PanelParqueadero;

/**
 * 
 * @author fmont
 *
 */
public class Gestion extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Gestion() {
		setClosable(true);
		setTitle("Gestionador");

		setResizable(false);
		setBounds(125, 0, 1080, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		PanelParqueadero panelPark = new PanelParqueadero(1044, 450);
		panelPark.setBounds(10, 11, 1044, 450);
		contentPane.add(panelPark);

		PanelFacturacion panelFact = new PanelFacturacion(1044, 208);
		panelFact.setBounds(10, 469, 1044, 170);
		contentPane.add(panelFact);

		if (Ventana.idioma) {
			setBundles("ec.ups.edu.p3.idioma.mensaje_es_EC");
		} else {
			setBundles("ec.ups.edu.p3.idioma.mensaje_en_US");
		}

	}

	private void setBundles(String propertyFile) {

		ResourceBundle rb = ResourceBundle.getBundle(propertyFile);
		this.setTitle(rb.getString("Gestion"));

	}

	public void idioma(boolean idioma) {
		if (idioma) {
			setBundles("ec.ups.edu.p3.idioma.mensaje_es_EC");
		} else {
			setBundles("ec.ups.edu.p3.idioma.mensaje_en_US");
		}
	}

}
