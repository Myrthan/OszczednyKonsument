package OszczednyKonsument.DataBaseModel;

public class SerachResult {
	public Double resultSum;
	public Integer id_sklep;

	public SerachResult(Integer id_sklep, Double resultSum) {
		this.resultSum = resultSum;
		this.id_sklep = id_sklep;
	}

	@Override
	public String toString() {
		return id_sklep.toString() + " " + resultSum.toString();
	}

	public Object[] toObject() {
		return new Object[]{ id_sklep, resultSum};
	}
}
