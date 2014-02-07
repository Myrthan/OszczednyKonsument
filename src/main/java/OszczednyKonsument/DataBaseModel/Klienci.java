package OszczednyKonsument.DataBaseModel;

/**
 * @author myrthan
 *
 */
public class Klienci {
	private int id;
	private String nazwisko;
	private String imie;
	
	/**
	 * @return
	 */
	public int getId(){
		return id;
	}
	/**
	 * 
	 */
	public Klienci() {}
    /**
     * @param id
     * @param nazwisko
     * @param imie
     */
    public Klienci(int id,String nazwisko,String imie) {
        this.id = id;
        this.nazwisko = nazwisko;
        this.imie = imie;
    }
}
