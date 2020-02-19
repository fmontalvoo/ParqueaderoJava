package ec.ups.edu.p3.gestionBD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ec.ups.edu.p3.conexion.ConexionBD;
import ec.ups.edu.p3.entidades.Ticket;

/**
 * 
 * @author fmont
 *
 */
public class TicketBD {

	public int consultarID() {

		Connection con = null;
		int id = 0;
		String sql = "SELECT TOP 1 id_ticket FROM ticket ORDER BY id_ticket DESC;";

		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				id = rs.getInt("ID_TICKET");

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}
		return id;

	}

	public boolean verificarTicket(int id) {

		String sql = "SELECT ID_TICKET FROM TICKET  WHERE ID_TICKET = ?";
		Connection con = null;
		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}
		return false;

	}

	public void insertar(Ticket ticket) {

		Connection con = null;
		String sql = "INSERT INTO TICKET(ID_TICKET, PLACA, FECHA_IN, HORA_IN" + " )\n" + "VALUES (?,?,?,?);";

		try {

			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, ticket.getId_ticket());
			ps.setString(2, ticket.getPlaca());
			ps.setString(4, ticket.getHora_in());

			try {
				ps.setDate(3, new Date(ticket.getFecha_in().getTime()));
			} catch (Exception e) {
				ps.setDate(3, null);
			}
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Ticket Guardado");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}

	}

	public void actualizar(Ticket ticket) {

		Connection con = null;

		String sql = "UPDATE TICKET" + "    SET PLACA = ?," + "  FECHA_IN = ?, " + "   HORA_IN = ? "
				+ "    WHERE ID_TICKET = ?";

		try {

			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, ticket.getPlaca());
			ps.setString(3, ticket.getHora_in());
			ps.setInt(4, ticket.getId_ticket());

			try {
				ps.setDate(2, new Date(ticket.getFecha_in().getTime()));
			} catch (Exception e) {
				ps.setDate(2, null);
			}
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Ticket Guardado");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}

	}

	public void borrar(int id) {

		Connection con = null;
		String sql = "   DELETE FROM ticket" + "   WHERE id_ticket = ?";

		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Ticket Eliminado");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}

	}

	public Ticket leer(int id) {

		Ticket ticket = new Ticket();

		Connection con = null;
		String sql = "SELECT * FROM TICKET WHERE ID_TICKET = ?";

		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				ticket.setId_ticket(rs.getInt("ID_TICKET"));
				ticket.setPlaca(rs.getString("PLACA").trim());
				ticket.setFecha_in(rs.getDate("FECHA_IN"));
				ticket.setHora_in(rs.getString("HORA_IN").trim());

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}
		return ticket;

	}

	public ArrayList<Ticket> listar() {

		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		String sql = "SELECT * FROM TICKET;";
		Connection con = null;
		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Ticket t = new Ticket();
				t.setId_ticket(rs.getInt("ID_TICKET"));
				t.setPlaca(rs.getString("PLACA").trim());
				t.setFecha_in(rs.getDate("FECHA_IN"));
				t.setHora_in(rs.getString("HORA_IN").trim());

				tickets.add(t);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}
		return tickets;
	}

}
