package ec.ups.edu.p3.gestionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ec.ups.edu.p3.conexion.ConexionBD;
import ec.ups.edu.p3.entidades.Estacionamiento;

/**
 * 
 * @author fmont
 *
 */
public class EstacionamientoBD {

	public void insertar(Estacionamiento estacionamiento) {

		Connection con = null;
		String sql = "INSERT INTO ESTACIONAMIENTO (ID_ESTACIONAMIENTO, LOTE, ESTADO" + " )\n" + "VALUES (?,?,?);";

		try {

			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, estacionamiento.getId_estacionamiento());
			ps.setInt(2, estacionamiento.getLote());
			ps.setBoolean(3, estacionamiento.Estado());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}

	}

	public boolean verificarLote(int id) {

		String sql = "SELECT ID_ESTACIONAMIENTO FROM ESTACIONAMIENTO  WHERE ID_ESTACIONAMIENTO = ?";
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

	public void actualizar(Estacionamiento estacionamiento) {

		Connection con = null;

		String sql = "UPDATE ESTACIONAMIENTO" + "    SET LOTE = ?," + "  ESTADO = ? "
				+ "    WHERE ID_ESTACIONAMIENTO = ?";
		try {

			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, estacionamiento.getLote());
			ps.setBoolean(2, estacionamiento.Estado());
			ps.setInt(3, estacionamiento.getId_estacionamiento());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}

	}

	public void borrar(int id) {

		Connection con = null;
		String sql = "   DELETE FROM estacionamiento" + "   WHERE id_estacionamiento = ?";

		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}

	}

	public Estacionamiento leer(int id) {

		Estacionamiento es = new Estacionamiento();

		String sql = "SELECT * FROM ESTACIONAMIENTO WHERE ID_ESTACIONAMIENTO = ?;";
		Connection con = null;
		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				es.setId_estacionamiento(rs.getInt("ID_ESTACIONAMIENTO"));
				es.setLote(rs.getInt("LOTE"));
				es.setEstado(rs.getBoolean("ESTADO"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}
		return es;
	}

	public ArrayList<Estacionamiento> listar() {

		ArrayList<Estacionamiento> estacionamientos = new ArrayList<Estacionamiento>();
		String sql = "SELECT * FROM ESTACIONAMIENTO;";
		Connection con = null;
		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Estacionamiento e = new Estacionamiento();
				e.setId_estacionamiento(rs.getInt("ID_ESTACIONAMIENTO"));
				e.setLote(rs.getInt("LOTE"));
				e.setEstado(rs.getBoolean("ESTADO"));

				estacionamientos.add(e);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}
		return estacionamientos;
	}

}
