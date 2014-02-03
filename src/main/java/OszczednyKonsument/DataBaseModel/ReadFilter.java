package OszczednyKonsument.DataBaseModel;

import java.sql.ResultSet;

public interface ReadFilter<T> {
	@SuppressWarnings("javadoc")
	public abstract boolean accept(T filtered);
}
