package OszczednyKonsument.DataBaseModel;

public class Sklep {
	public Integer id_sklep;
	public String nazwa;
	public String adres;
	public String miasto;
	public String kod_pocztowy;
	public String godziny_otwarcia;
	public String numer_kontaktowy;
	public String wlasciciel;

	public Sklep(Integer Id, String nazwa, String adres, String miasto,
			String kod_pocztowy, String godziny_otwarcia,
			String numer_kontaktowy, String wlasciciel) {
		this.id_sklep = Id;
		this.nazwa = nazwa;
		this.miasto = miasto;
		this.kod_pocztowy = kod_pocztowy;
		this.godziny_otwarcia = godziny_otwarcia;
		this.numer_kontaktowy = numer_kontaktowy;
		this.wlasciciel = wlasciciel;
	}

	@Override
	public String toString() {
		return id_sklep.toString() + " " + nazwa + " " + nazwa + " " + adres
				+ " " + miasto + " " + kod_pocztowy + " " + godziny_otwarcia
				+ " " + numer_kontaktowy + " " + wlasciciel;
	}

	public Object[] toObject() {
		Object[] w = { id_sklep, nazwa, adres, miasto, kod_pocztowy,
				godziny_otwarcia, numer_kontaktowy, wlasciciel };
		return w;
	}
}
