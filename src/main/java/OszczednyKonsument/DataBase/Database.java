package OszczednyKonsument.DataBase;

import java.sql.*;

import javax.naming.ConfigurationException;

/**
 * Baza danych. Zaimplementowane funkcje: -Łączenie się z bazą danych
 * 
 * 
 * @author myrthan
 * 
 * 
 */
public class Database {

	private static boolean isPrepared = false;
	private static Connection connection = null;
	private static String url = "jdbc:postgresql://192.168.1.6/oszczednykonsumentdatabase";
	/**
	 * @param args
	 */
	public static void prepare() {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isPrepared = true;
	}

	/**
	 * @return
	 */
	public static Connection getConnection() {
		if (!isPrepared)
			prepare();

		try {
			connection = DriverManager.getConnection(
					url,
					"postgres", "oszczedny");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * 
	 */
	public static void closeConnection() {

		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
