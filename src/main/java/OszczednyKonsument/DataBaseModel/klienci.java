package OszczednyKonsument.DataBaseModel;

public class klienci {
	private int id;
	private String nazwisko;
	private String imie;
	
	public int getId(){
		return id;
	}
	public klienci() {}
    public klienci(int id,String nazwisko,String imie) {
        this.id = id;
        this.nazwisko = nazwisko;
        this.imie = imie;
    }
}
