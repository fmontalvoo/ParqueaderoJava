package ec.ups.edu.p3.hilo;

import java.util.ArrayList;

import ec.ups.edu.p3.aplicacion.PanelFacturacion;
import ec.ups.edu.p3.entidades.Auto;
import ec.ups.edu.p3.entidades.Lote;
import ec.ups.edu.p3.graficos.PanelParqueadero;

/**
 * 
 * @author fmont
 *
 */
public class Movimiento implements Runnable {

	private static ArrayList<Lote> lotes = new ArrayList<Lote>();
	private static ArrayList<Auto> autos = new ArrayList<Auto>();
	private int h = 5;
	private int sleep = 15;
	private int numLote;
	private int indiceSalida;
	public static boolean salida;
	private boolean proceso;

	public void iniciar(boolean p, int numLote) {
		proceso = p;
		this.numLote = numLote;
	}

	public void salir(boolean p, int i) {
		proceso = p;
		indiceSalida = i;
	}

	public void run() {
		PanelParqueadero.autos(autos);

		while (proceso) {
			for (int i = 0; i < autos.size(); i++) {
				ingresoAuto(i, numLote);
				if (salida) {
					salidaAuto(indiceSalida);
					salida = false;
				}
			}
			proceso = false;
		}
	}

	public void ingresoAuto(int i, int numLote) {

		int posX = lotes.get(numLote).getX();
		int posY = lotes.get(numLote).getY();

		if (!autos.get(i).estaParqueado()) {
			PanelFacturacion.btnIngresar.setEnabled(false);
			PanelFacturacion.btnSalir.setEnabled(false);
			PanelFacturacion.abrirB1();
			moverX(i, posX, "auto1.png");
			if (lotes.get(numLote).getY() < 150) {
				moverYArriba(i, posY, "auto2.png");
			} else if (lotes.get(numLote).getY() > 150) {
				moverYAbajo(i, posY + 5, "auto3.png");
			}
			autos.get(i).setParqueado(true);
			autos.get(i).setLote(numLote);
			lotes.get(numLote).setEstado(true);
			PanelFacturacion.btnIngresar.setEnabled(true);
			PanelFacturacion.btnSalir.setEnabled(true);
			PanelFacturacion.cerrarB1();
		}

	}

	public void salidaAuto(int i) {
		int posX = 1060;
		int posY = 210;
		int j = autos.get(i).getLote();
		PanelFacturacion.abrirB2();
		PanelFacturacion.btnSalir.setEnabled(false);
		PanelFacturacion.btnIngresar.setEnabled(false);
		if (autos.get(i).getY() > 150) {
			moverYArriba(i, posY, "auto3.png");
		} else if (autos.get(i).getY() < 150) {
			moverYAbajo(i, posY, "auto2.png");
		}
		moverX(i, posX, "auto1.png");
		lotes.get(j).setEstado(false);
		autos.remove(i);
		PanelFacturacion.btnSalir.setEnabled(true);
		PanelFacturacion.btnIngresar.setEnabled(true);
		PanelFacturacion.cerrarB2();
	}

	public void moverX(int i, int pos, String autoPNG) {
		Auto a = new Auto();
		for (int j = autos.get(i).getX(); j < pos - 30; j += h) {
			a.setPlaca(autos.get(i).getPlaca());
			a.setX(j);
			a.setY(autos.get(i).getY());
			a.setImagen(autoPNG);
			autos.set(i, a);
			pausa();
		}
	}

	public void moverYArriba(int i, int pos, String autoPNG) {
		Auto a = new Auto();
		for (int j = autos.get(i).getY(); j > pos; j -= h) {
			a.setPlaca(autos.get(i).getPlaca());
			a.setX(autos.get(i).getX());
			a.setY(j);
			a.setImagen(autoPNG);
			autos.set(i, a);
			pausa();
		}
	}

	public void moverYAbajo(int i, int pos, String autoPNG) {
		Auto a = new Auto();
		for (int j = autos.get(i).getY(); j <= pos; j += h) {
			a.setPlaca(autos.get(i).getPlaca());
			a.setX(autos.get(i).getX());
			a.setY(j);
			a.setImagen(autoPNG);
			autos.set(i, a);
			pausa();
		}
	}

	public void pausa() {
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void array(ArrayList<Lote> lote) {
		lotes = lote;
	}

	public void autos(ArrayList<Auto> auto) {
		autos = auto;
	}

}
