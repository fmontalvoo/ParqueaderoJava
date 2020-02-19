package ec.ups.edu.p3.entidades;

/**
 * 
 * @author fmont
 *
 */
public class Estacionamiento {

	private int id_estacionamiento;
	private int lote;
	private boolean estado;

	public int getId_estacionamiento() {
		return id_estacionamiento;
	}

	public void setId_estacionamiento(int id_estacionamiento) {
		this.id_estacionamiento = id_estacionamiento;
	}

	public int getLote() {
		return lote;
	}

	public void setLote(int lote) {
		this.lote = lote;
	}

	public boolean Estado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
