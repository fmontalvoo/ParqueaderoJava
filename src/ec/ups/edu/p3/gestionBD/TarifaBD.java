package ec.ups.edu.p3.gestionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ec.ups.edu.p3.conexion.ConexionBD;
import ec.ups.edu.p3.entidades.Tarifa;
import ec.ups.edu.p3.frames.SeleccionarTarifa;

/**
 * 
 * @author fmont
 *
 */
public class TarifaBD {

	public int consultarID() {

		Connection con = null;
		int id = 0;
		String sql = "SELECT TOP 1 id_tarifa FROM tarifa ORDER BY id_tarifa DESC;";

		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				id = rs.getInt("ID_TARIFA");

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}
		return id;

	}

	public boolean verificarTarifa(int id) {

		String sql = "SELECT ID_TARIFA FROM TARIFA  WHERE ID_TARIFA = ?";
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

	@SuppressWarnings("unchecked")
	public void Tarifas() {

		Connection con = null;
		String sql = "SELECT * FROM tarifa;";
		SeleccionarTarifa.tipoTarifa.addItem("");
		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				SeleccionarTarifa.tipoTarifa.addItem(rs.getObject("TIPO").toString());

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}

	}

	public void insertar(Tarifa tarifa) {

		Connection con = null;
		String sql = "INSERT INTO TARIFA(ID_TARIFA, TIPO, TIEMPO, PRECIO" + " )\n" + "VALUES (?,?,?,?);";

		try {

			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, tarifa.getId_tarifa());
			ps.setString(2, tarifa.getTipo());
			ps.setString(3, tarifa.getTiempo());
			ps.setDouble(4, tarifa.getPrecio());

			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Tarifa Almacenada");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}

	}

	public Tarifa consultarTarifa(String tipo) {

		Tarifa t = new Tarifa();
		Connection con = null;
		String sql = "SELECT * FROM tarifa WHERE tipo = ?;";
		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tipo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				t.setId_tarifa(rs.getInt("ID_TARIFA"));
				t.setTiempo(rs.getString("TIEMPO"));
				t.setPrecio(rs.getDouble("PRECIO"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}
		return t;

	}

	public void actualizar(Tarifa tarifa) {

		Connection con = null;
		String sql = "UPDATE TARIFA" + "    SET TIPO = ?," + "  TIEMPO = ?, " + "   PRECIO = ? "
				+ "    WHERE ID_TARIFA = ?";

		try {

			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, tarifa.getTipo());
			ps.setString(2, tarifa.getTiempo());
			ps.setDouble(3, tarifa.getPrecio());
			ps.setInt(4, tarifa.getId_tarifa());

			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Tarifa Almacenada");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}

	}

	public void borrar(int id) {

		Connection con = null;
		String sql = "   DELETE FROM tarifa" + "   WHERE id_tarifa = ?";

		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Tarifa Eliminada");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}

	}

	public Tarifa leer(int id) {

		Tarifa t = new Tarifa();
		Connection con = null;
		String sql = "SELECT * FROM tarifa WHERE id_tarifa = ?;";
		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				t.setId_tarifa(rs.getInt("ID_TARIFA"));
				t.setTiempo(rs.getString("TIEMPO"));
				t.setTipo(rs.getString("TIPO"));
				t.setPrecio(rs.getDouble("PRECIO"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}
		return t;

	}

	public ArrayList<Tarifa> listar() {

		ArrayList<Tarifa> tarifas = new ArrayList<Tarifa>();
		String sql = "SELECT * FROM TARIFA;";
		Connection con = null;
		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Tarifa t = new Tarifa();
				t.setId_tarifa(rs.getInt("ID_TARIFA"));
				t.setTipo(rs.getString("TIPO"));
				t.setTiempo(rs.getString("TIEMPO"));
				t.setPrecio(rs.getDouble("PRECIO"));

				tarifas.add(t);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}
		return tarifas;
	}

}
