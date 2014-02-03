package OszczednyKonsument.DataBaseModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ReadGetter <T> {
	ResultSet rs;
	public void takeResultSet(ResultSet rs){
		this.rs=rs;
	}
	public abstract T read();
}

