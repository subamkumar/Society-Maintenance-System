package society;

public class maintenance_charge_model {
	
	private Double charge;
	private Integer month;
	private Integer year;
	
	public maintenance_charge_model(Double c,Integer m,Integer y)
	{
		this.charge = c;
		this.month = m;
		this.year = y;
	}
	
	public Double getCharge() {
		return charge;
	}
	public Integer getMonth() {
		return month;
	}
	public Integer getYear() {
		return year;
	}
}
