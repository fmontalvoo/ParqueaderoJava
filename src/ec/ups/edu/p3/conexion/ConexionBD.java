package ec.ups.edu.p3.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * @author fmont
 *
 */
public class ConexionBD {

	public static Connection getConnection() {
		try {

			// Selecciona el driver
			Class.forName("org.hsqldb.jdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:hsqldb:file:bd/parqueadero", "SA", "");

			return con;
		} catch (Exception ex) {
			System.out.println("Database.getConnection() Error -->" + ex.getMessage());
			return null;
		}
	}

	public static void close(Connection con) {
		try {
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
