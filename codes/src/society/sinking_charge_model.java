package society;

public class sinking_charge_model {

	private Double charge;
	private Double percentage;
	private Integer month_fixed;
	private Integer year_fixed;
	
	public sinking_charge_model(Double c,Double p,Integer m,Integer y)
	{
		this.charge = c;
		this.percentage = p;
		this.month_fixed = m;
		this.year_fixed = y;
	}
	
	
	public Double getCharge() {
		return charge;
	}
	public Double getPercentage() {
		return percentage;
	}
	public Integer getMonth_fixed() {
		return month_fixed;
	}
	public Integer getYear_fixed() {
		return year_fixed;
	}
}
