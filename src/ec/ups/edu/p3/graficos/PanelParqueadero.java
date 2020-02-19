package ec.ups.edu.p3.graficos;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ec.ups.edu.p3.aplicacion.PanelFacturacion;
import ec.ups.edu.p3.entidades.Auto;
import ec.ups.edu.p3.entidades.Estacionamiento;
import ec.ups.edu.p3.entidades.Lote;
import ec.ups.edu.p3.gestionBD.EstacionamientoBD;
import ec.ups.edu.p3.hilo.Movimiento;

/**
 * 
 * @author fmont
 *
 */
public class PanelParqueadero extends JPanel {

	private static final long serialVersionUID = 1L;
	private int ancho;
	private int alto;
	private int limiteX = 15;
	private int limiteY = 15;
	private int entrada = 40;
	private int mltX = 7;
	private int mltY = 5;
	private int numLotes = 5;
	private int indice = 0;
	private int indice1 = numLotes;
	private int indice2 = 0;
	public static int ocupados;
	private static boolean brazo = false;
	private static boolean brazo1 = false;
	private static ArrayList<Auto> autos = new ArrayList<Auto>();
	private ArrayList<Lote> lotes = new ArrayList<Lote>();

	/*
	 * Contructor que reicebe las dimensiones que debe tener el panel grafico
	 */
	public PanelParqueadero(int ancho, int alto) {

		this.ancho = ancho;
		this.alto = alto;

		setBackground(Color.DARK_GRAY);

	}

	public void paint(Graphics g) {

		int x = mltX * limiteX;
		int x1 = (ancho - mltX * limiteX);
		int y = (mltY * limiteY) + 20;
		int y1 = (((alto / 2) + entrada)) + 20;
		double h = ((ancho - 2 * x) / numLotes) + 1;
		int numLoteX = (int) (h / 2) - 2;
		int ax = 0;

		super.paint(g);
		// Lineas que delimitan los bordes externos del parqueadero
		g.setColor(Color.WHITE);
		g.drawLine(limiteX, limiteY, ancho - limiteX, limiteY);// Linea_Vertical_1
		g.drawLine(limiteX, alto - limiteY, ancho - limiteX, alto - limiteY);// Linea_Vertical_1
		g.drawLine(limiteX, limiteY, limiteX, ((alto / 2) - entrada));// Linea_Horizontal_1
		g.drawLine(limiteX, ((alto / 2) + entrada), limiteX, alto - limiteY);// Linea_Horizontal_1.1
		g.drawLine(ancho - limiteX, limiteY, ancho - limiteX, ((alto / 2) - entrada));// Linea_Horizontal_1.2
		g.drawLine(ancho - limiteX, ((alto / 2) + entrada), ancho - limiteX, alto - limiteY);// Linea_Horizontal_1.3
		// Fin lineas que delimitan los bordes externos del parqueadero

		// Condiciones if que controlan si el brazo se abre o se cierra
		g.setColor(Color.RED);
		if (brazo) {
			g.drawLine(limiteX, (alto / 2) - entrada, limiteX + 2 * entrada, (alto / 2) - entrada);
		} else {
			g.drawLine(limiteX, (alto / 2) - entrada, limiteX, (alto / 2) + entrada);
		}
		if (brazo1) {
			g.drawLine(ancho - limiteX, (alto / 2) - entrada, ancho - limiteX - 2 * entrada, (alto / 2) - entrada);
		} else {
			g.drawLine(ancho - limiteX, (alto / 2) - entrada, ancho - limiteX, (alto / 2) + entrada);
		}
		// Fin condiciones if que controlan si el brazo se abre o se cierra

		// Lineas para dibujar los lotes para los parqueaderos
		g.setColor(Color.YELLOW);
		g.drawLine(mltX * limiteX, mltY * limiteY, ancho - mltX * limiteX, mltY * limiteY);
		g.drawLine(mltX * limiteX, alto - mltY * limiteY, ancho - mltX * limiteX, alto - mltY * limiteY);
		g.drawLine(mltX * limiteX, (alto / 2) - entrada, ancho - mltX * limiteX, (alto / 2) - entrada);
		g.drawLine(mltX * limiteX, (alto / 2) + entrada, ancho - mltX * limiteX, (alto / 2) + entrada);

		g.drawLine(ancho - mltX * limiteX, mltY * limiteY, ancho - mltX * limiteX, ((alto / 2) - entrada));
		g.drawLine(ancho - mltX * limiteX, ((alto / 2) + entrada), ancho - mltX * limiteX, alto - mltY * limiteY);
		for (int i = x, j = 1; i < x1; i += h, j++) {
			g.setColor(Color.YELLOW);
			g.drawLine(i, mltY * limiteY, i, ((alto / 2) - entrada));
			g.setColor(Color.WHITE);
			g.drawString("" + j, i + numLoteX, y);
			ax = j;
		}

		for (int i = x, j = ax + 1; i < x1; i += h, j++) {
			g.setColor(Color.YELLOW);
			g.drawLine(i, ((alto / 2) + entrada), i, alto - mltY * limiteY);
			g.setColor(Color.WHITE);
			g.drawString("" + j, i + numLoteX, y1);
		}
		// Fin lineas para dibujar los lotes para los parqueaderos

		// Metodo para dibujar los autos
		for (int i = 0, j = 20; i < autos.size(); i++, j++) {
			g.drawImage(autos.get(i).getImagen(), autos.get(i).getX() + j, autos.get(i).getY(), this);
		}
		// Fin metodo para dibujar los autos

		// Metodo para dibujar el semaforo
		try {
			g.drawImage(ImageIO.read(getClass().getClassLoader().getResource("V.png")), limiteX + 5, limiteY + 5, null);
			if (ocupados > lotes.size() - 5) {
				g.drawImage(ImageIO.read(getClass().getClassLoader().getResource("A.png")), limiteX + 5, limiteY + 5,
						null);
			}
			if (ocupados >= lotes.size()) {
				g.drawImage(ImageIO.read(getClass().getClassLoader().getResource("R.png")), limiteX + 5, limiteY + 5,
						null);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Fin metodo para dibujar el semaforo

		for (int j = x; indice < numLotes; indice++, j += h) {
			Lote l = new Lote();
			l.setX(j + numLoteX);
			l.setY(y);
			l.setEstado(false);
			l.setLote(indice + 1);
			lotes.add(indice, l);
		}

		for (int i = x; indice1 < 2 * numLotes; indice1++, i += h) {
			Lote l = new Lote();
			l.setX(i + numLoteX);
			l.setY(y1);
			l.setEstado(false);
			l.setLote(indice1 + 1);
			lotes.add(indice1, l);
		}

		for (; indice2 < lotes.size(); indice2++) {
			Estacionamiento e = new Estacionamiento();
			EstacionamientoBD ebd = new EstacionamientoBD();

			e.setId_estacionamiento(lotes.get(indice2).getLote());
			e.setLote(lotes.get(indice2).getLote());
			e.setEstado(lotes.get(indice2).getEstado());

			ebd.actualizar(e);
			// ebd.insertar(e);

		}

		PanelFacturacion.array(lotes);
		Movimiento.array(lotes);
		repaint();
	}

	public static void control(boolean b) {
		brazo = b;
	}

	public static void control1(boolean b) {
		brazo1 = b;
	}

	public static void autos(ArrayList<Auto> auto) {
		autos = auto;
	}

}
