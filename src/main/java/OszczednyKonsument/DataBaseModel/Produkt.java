package OszczednyKonsument.DataBaseModel;

public class Produkt {
	public Integer id_produkt;
	public String nazwa;
	public String producent;
	public String typ;
	public Integer size;
	public Produkt(Integer Id,String nazwa,String producent,String typ){
		this.id_produkt=Id;
		this.nazwa=nazwa;
		this.producent=producent;
		this.typ=typ;
		this.size = 5;
	}
	@Override
	public String toString(){
		return id_produkt.toString()+" "+nazwa+" "+producent+" "+typ;
	}
	public Object[] toObject() {
		Object[] w = {id_produkt,nazwa,producent,typ};
		return w;
	}
}
