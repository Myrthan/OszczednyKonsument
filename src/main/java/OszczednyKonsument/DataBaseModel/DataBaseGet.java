package OszczednyKonsument.DataBaseModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import OszczednyKonsument.DataBase.Database;

public class DataBaseGet {
	public List<Produkt> selectProdukty(){
        List<Produkt> produkty = new LinkedList<Produkt>();
        try {
            ResultSet result = Database.getConnection().prepareStatement("select * from produkty").executeQuery();
            Integer id_produkt;
            String nazwa;
            String producent;
            String typ;
            while(result.next()) {
                id_produkt = result.getInt("id_produkt");
                nazwa = result.getString("nazwa");
                producent = result.getString("producent");
                typ=result.getString("typ");
                produkty.add(new Produkt(id_produkt,nazwa,producent,typ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return produkty;
	}

}

