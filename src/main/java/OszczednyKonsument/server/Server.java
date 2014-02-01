package OszczednyKonsument.server;

import java.sql.Connection;

import OszczednyKonsument.DataBase.Database;
import OszczednyKonsument.DataBaseModel.DataBaseGet;

/**
 * Server
 * 
 * @author myrthan
 *
 */
public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = Database.getConnection();
		//System.out.println(DataBaseGet.selectProdukty());
		Database.closeConnection();
	}

}
