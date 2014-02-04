package OszczednyKonsument.DataBaseModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import OszczednyKonsument.DataBase.Database;

public class DataBaseGet {
	/**
	 * @param query
	 * @param rg
	 * @param rf
	 * @return returns list of objects returned by query
	 */
	public static  <T> List<T> select(String query,ReadGetter<T> rg, ReadFilter<T> rf){
        List<T> container = new LinkedList<T>();
        try {
            ResultSet result = Database.getConnection().prepareStatement(query).executeQuery();
            rg.takeResultSet(result);
            T ref;
            while(result.next()) {
            	ref=rg.read();
            	if(rf==null || rf.accept(ref))
            		container.add(ref);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return container;
	}
	public static List<Produkt> selectProdukty(){
		return select("select * from produkty", new ReadGetter<Produkt>(){
			@Override
			public Produkt read() {
				try {
					return new Produkt(rs.getInt("id_produkt"),rs.getString("nazwa"),rs.getString("producent"),
									rs.getString("typ"));
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}, null);
	}
}

