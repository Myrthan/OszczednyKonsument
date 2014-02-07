package OszczednyKonsument.DataBaseModel;

public class Recenzja {
	public String recenzja;
	public String autor;
	public Recenzja(String recenzja, String autor){
		this.recenzja=recenzja;
		this.autor=autor;
	}
	@Override
	public String toString(){
		return recenzja+"\n"+autor;
	}
}
