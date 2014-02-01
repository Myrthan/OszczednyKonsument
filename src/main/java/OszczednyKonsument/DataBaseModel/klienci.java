package OszczednyKonsument.DataBaseModel;

/**
 * @author myrthan
 *
 */
public class klienci {
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
	public klienci() {}
    /**
     * @param id
     * @param nazwisko
     * @param imie
     */
    public klienci(int id,String nazwisko,String imie) {
        this.id = id;
        this.nazwisko = nazwisko;
        this.imie = imie;
    }
}
