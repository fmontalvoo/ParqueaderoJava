package ec.ups.edu.p3.entidades;

import java.util.Date;

/**
 * 
 * @author fmont
 *
 */
public class Factura {

	private int id_factura;
	private Date fecha_emision;
	private String cedula;
	private int tarifa;
	private int ticket;
	private int estacionamiento;
	private double total;

	public int getId_factura() {
		return id_factura;
	}

	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}

	public Date getFecha_emision() {
		return fecha_emision;
	}

	public void setFecha_emision(Date fecha_emision) {
		this.fecha_emision = fecha_emision;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public int getTarifa() {
		return tarifa;
	}

	public void setTarifa(int tarifa) {
		this.tarifa = tarifa;
	}

	public int getTicket() {
		return ticket;
	}

	public void setTicket(int ticket) {
		this.ticket = ticket;
	}

	public int getEstacionamiento() {
		return estacionamiento;
	}

	public void setEstacionamiento(int estacionamiento) {
		this.estacionamiento = estacionamiento;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
