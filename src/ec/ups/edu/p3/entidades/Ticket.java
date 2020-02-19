package ec.ups.edu.p3.entidades;

import java.util.Date;

/**
 * 
 * @author fmont
 *
 */
public class Ticket {

	private int id_ticket;
	private String placa;
	private Date fecha_in;
	private String hora_in;

	public int getId_ticket() {
		return id_ticket;
	}

	public void setId_ticket(int id_ticket) {
		this.id_ticket = id_ticket;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Date getFecha_in() {
		return fecha_in;
	}

	public void setFecha_in(Date fecha_in) {
		this.fecha_in = fecha_in;
	}

	public String getHora_in() {
		return hora_in;
	}

	public void setHora_in(String hora_in) {
		this.hora_in = hora_in;
	}

}
