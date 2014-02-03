package OszczednyKonsument.DataBaseModel;

import java.sql.SQLException;

public class ProduktGetter extends ReadGetter<Produkt>{

	@Override
	public Produkt read() {
		try {
			return new Produkt(rs.getInt("id_produkt"), rs.getString("nazwa"),rs.getString("producent"),
																rs.getString("typ"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}