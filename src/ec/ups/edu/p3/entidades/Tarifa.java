package ec.ups.edu.p3.entidades;

/**
 * 
 * @author fmont
 *
 */
public class Tarifa {

	private int id_tarifa;
	private String tipo;
	private String tiempo;
	private double precio;

	public int getId_tarifa() {
		return id_tarifa;
	}

	public void setId_tarifa(int id_tarifa) {
		this.id_tarifa = id_tarifa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
