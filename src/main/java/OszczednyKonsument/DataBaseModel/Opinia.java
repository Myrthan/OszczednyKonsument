package OszczednyKonsument.DataBaseModel;

public class Opinia {
	public String nick;
	public String komentarz;
	public Integer ocena;
	public Opinia(String nick, String komentarz, Integer ocena){
		this.nick=nick;
		this.komentarz=komentarz;
		this.ocena=ocena;
	}
	@Override
	public String toString(){
		return nick+" "+komentarz+" "+ocena;
	}
}
