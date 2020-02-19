package ec.ups.edu.p3.entidades;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author fmont
 *
 */
public class Auto {

	private int id_auto;
	private String placa;
	private int x;
	private int y;
	private int lote;
	private BufferedImage imagen;
	private boolean parqueado = false;

	public int getId_auto() {
		return id_auto;
	}

	public void setId_auto(int id_auto) {
		this.id_auto = id_auto;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLote() {
		return lote;
	}

	public void setLote(int lote) {
		this.lote = lote;
	}

	public BufferedImage getImagen() {
		return imagen;
	}

	public void setImagen(String ruta) {
		try {
			imagen = ImageIO.read(getClass().getClassLoader().getResource(ruta));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean estaParqueado() {
		return parqueado;
	}

	public void setParqueado(boolean parqueado) {
		this.parqueado = parqueado;
	}

}
