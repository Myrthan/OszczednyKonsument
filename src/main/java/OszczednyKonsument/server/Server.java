package OszczednyKonsument.server;

import java.sql.Connection;

import OszczednyKonsument.DataBase.Database;

public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = Database.getConnection();
		Database.closeConnection();
	}

}
