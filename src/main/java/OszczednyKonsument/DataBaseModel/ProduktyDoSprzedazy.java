package OszczednyKonsument.DataBaseModel;

import java.math.BigDecimal;

public class ProduktyDoSprzedazy {
	public Integer id_pds;
	public Double cena;
	public Integer id_produktu;
	public Integer id_sklepu;
	public ProduktyDoSprzedazy(Integer Id,Double cena,Integer id_produktu,Integer id_sklepu){
		this.id_pds=Id;
		this.cena=cena;
		this.id_produktu=id_produktu;
		this.id_sklepu=id_sklepu;
	}
	@Override
	public String toString(){
		return id_pds.toString()+" "+cena.toString()+ " "+ id_produktu.toString() + " " + id_sklepu.toString();
	}
	public Object[] toObject() {
		return new Object[]{id_pds,cena,id_produktu,id_sklepu};
	}

}
