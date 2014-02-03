import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import OszczednyKonsument.DataBase.Database;
import OszczednyKonsument.DataBaseModel.DataBaseGet;
import OszczednyKonsument.DataBaseModel.DataBaseUpdate;
import OszczednyKonsument.DataBaseModel.Produkt;


public class TestMainApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = Database.getConnection();
		testDataBaseGet();
		Database.closeConnection();
	}
	public static void testDataBaseGet() {

		List<Produkt> lista = DataBaseGet.selectProdukty();
		Iterator<Produkt> w = lista.iterator();
		while(w.hasNext())
			System.out.println(w.next().nazwa);
	}

}
