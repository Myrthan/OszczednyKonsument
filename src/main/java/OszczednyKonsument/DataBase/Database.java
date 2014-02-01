package OszczednyKonsument.DataBase;
import java.sql.*;

public class Database {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://192.168.1.6/oszczednykonsumentdatabase", "myrthan",
					"oszczedny");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
