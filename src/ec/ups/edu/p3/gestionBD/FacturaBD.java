package ec.ups.edu.p3.gestionBD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ec.ups.edu.p3.conexion.ConexionBD;
import ec.ups.edu.p3.entidades.Factura;

/**
 * 
 * @author fmont
 *
 */
public class FacturaBD {

	public int consultarID() {

		Connection con = null;
		int id = 0;
		String sql = "SELECT TOP 1 id_factura FROM factura ORDER BY id_factura DESC;";

		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				id = rs.getInt("ID_FACTURA");

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}
		return id;

	}

	public boolean verificarFactura(int id) {

		String sql = "SELECT ID_FACTURA    FROM FACTURA      WHERE id_factura = ?";
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

	public void insertar(Factura factura) {

		Connection con = null;
		String sql = "INSERT INTO FACTURA (ID_FACTURA, FECHA_EM, CEDULA, TARIFA, TICKET, ESTACIONAMIENTO, TOTAL"
				+ " )\n" + "VALUES (?,?,?,?,?,?,?);";

		try {

			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, factura.getId_factura());
			ps.setString(3, factura.getCedula());
			ps.setInt(4, factura.getTarifa());
			ps.setInt(5, factura.getTicket());
			ps.setInt(6, factura.getEstacionamiento());
			ps.setDouble(7, factura.getTotal());

			try {
				ps.setDate(2, new Date(factura.getFecha_emision().getTime()));
			} catch (Exception e) {
				ps.setDate(2, null);
			}

			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Factura Guardada");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}

	}

	public void actualizar(Factura factura) {

		Connection con = null;

		String sql = "UPDATE FACTURA   SET FECHA_EM = ?, CEDULA = ?,TARIFA = ?, TICKET = ?, ESTACIONAMIENTO = ?, TOTAL = ? "
				+ " WHERE ID_FACTURA = ?";

		try {

			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);

			try {
				ps.setDate(1, new Date(factura.getFecha_emision().getTime()));
			} catch (Exception e) {
				ps.setDate(1, null);
			}

			ps.setString(2, factura.getCedula());
			ps.setInt(3, factura.getTarifa());
			ps.setInt(4, factura.getTicket());
			ps.setInt(5, factura.getEstacionamiento());
			ps.setDouble(6, factura.getTotal());
			ps.setInt(7, factura.getId_factura());

			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Factura Guardada");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}

	}

	public void borrar(int id) {

		Connection con = null;
		String sql = "   DELETE FROM factura" + "   WHERE id_factura = ?";

		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Factura Eliminada");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}

	}

	public Factura leer(int id) {

		Factura factura = new Factura();

		String sql = "SELECT  *  FROM  FACTURA  WHERE ID_FACTURA = ?";
		Connection con = null;
		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				factura.setId_factura(rs.getInt("ID_FACTURA"));
				factura.setFecha_emision(rs.getDate("FECHA_EM"));
				factura.setCedula(rs.getString("CEDULA").trim());
				factura.setTarifa(rs.getInt("TARIFA"));
				factura.setTicket(rs.getInt("TICKET"));
				factura.setEstacionamiento(rs.getInt("ESTACIONAMIENTO"));
				factura.setTotal(rs.getDouble("TOTAL"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}
		return factura;

	}

	public ArrayList<Factura> listar() {

		ArrayList<Factura> facturas = new ArrayList<Factura>();

		String sql = "SELECT  *  FROM  FACTURA";
		Connection con = null;
		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Factura f = new Factura();
				f.setId_factura(rs.getInt("ID_FACTURA"));
				f.setFecha_emision(rs.getDate("FECHA_EM"));
				f.setCedula(rs.getString("CEDULA").trim());
				f.setTarifa(rs.getInt("TARIFA"));
				f.setTicket(rs.getInt("TICKET"));
				f.setEstacionamiento(rs.getInt("ESTACIONAMIENTO"));
				f.setTotal(rs.getDouble("TOTAL"));

				facturas.add(f);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}
		return facturas;

	}

}
