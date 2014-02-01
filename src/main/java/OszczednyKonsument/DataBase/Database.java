package OszczednyKonsument.DataBase;

import java.sql.*;

import javax.naming.ConfigurationException;

/**
 * Baza danych.
 * Zaimplementowane funkcje:
 * -Łączenie się z bazą danych
 *
 * 
 * @author myrthan
 * 
 *
 */
public class Database {

	private static boolean isPrepared = false;
	private static Connection connection = null;
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
					"jdbc:postgresql://localhost/piotr",
					"piotr", "kocham1ale12");
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
