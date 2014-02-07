package OszczednyKonsument.DataBaseModel;

import java.sql.Timestamp;

public class Opinia {
	public String nick;
	public String komentarz;
	public Integer ocena;
	public Timestamp time;
	public Opinia(String nick, String komentarz, Integer ocena, Timestamp t){
		this.nick=nick;
		this.komentarz=komentarz;
		this.ocena=ocena;
		this.time=t;
	}
	@Override
	public String toString(){
		return nick+" "+komentarz+" "+ocena;
	}
}
