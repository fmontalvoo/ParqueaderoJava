package ec.ups.edu.p3.gestionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ec.ups.edu.p3.conexion.ConexionBD;
import ec.ups.edu.p3.entidades.Cliente;

/**
 * 
 * @author fmont
 *
 */
public class ClienteBD {

	public boolean verificarCliente(String cedula) {

		String sql = "SELECT CEDULA" + "       FROM CLIENTE" + "       WHERE cedula = ?";
		Connection con = null;
		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cedula);
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

	public void insertar(Cliente cliente) {

		Connection con = null;
		String sql = "INSERT INTO CLIENTE(CEDULA, NOMBRES, TELEFONO, DIRECCION" + " )\n" + "VALUES (?,?,?,?);";

		try {

			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, cliente.getCedula());
			ps.setString(2, cliente.getNombres());
			ps.setString(3, cliente.getTelefono());
			ps.setString(4, cliente.getDireccion());

			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Cliente Registrado");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}

	}

	public void actualizar(Cliente cliente) {

		Connection con = null;

		String sql = "UPDATE CLIENTE " + "    SET NOMBRES = ?," + "    TELEFONO = ?, " + "    DIRECCION = ? "
				+ "    WHERE CEDULA = ?";

		try {

			con = ConexionBD.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cliente.getNombres());
			ps.setString(2, cliente.getTelefono());
			ps.setString(3, cliente.getDireccion());
			ps.setString(4, cliente.getCedula());

			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Cliente Actualizado");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}

	}

	public void borrar(String cedula) {

		Connection con = null;
		String sql = "   DELETE FROM cliente" + "   WHERE cedula = ?";

		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cedula);

			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Cliente Eliminado");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}

	}

	public Cliente leer(String cedula) {

		Cliente cliente = new Cliente();

		String sql = "SELECT  *  FROM  CLIENTE  WHERE CEDULA = ?";
		Connection con = null;
		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cedula);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				cliente.setCedula(rs.getString("CEDULA").trim());
				cliente.setNombres(rs.getString("NOMBRES").trim());
				cliente.setTelefono(rs.getString("TELEFONO").trim());
				cliente.setDireccion(rs.getString("DIRECCION").trim());

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}
		return cliente;

	}

	public ArrayList<Cliente> listar() {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		String sql = "SELECT * FROM CLIENTE;";
		Connection con = null;
		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Cliente c = new Cliente();
				c.setCedula(rs.getString("CEDULA").trim());
				c.setNombres(rs.getString("NOMBRES").trim());
				c.setTelefono(rs.getString("TELEFONO").trim());
				c.setDireccion(rs.getString("DIRECCION").trim());

				clientes.add(c);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}
		return clientes;
	}

}
