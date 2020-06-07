package society;

public class occupancy_charge_model {

	private Double percentage;
	private Integer month;
	private Integer year;
	
	public occupancy_charge_model(Double p,Integer m,Integer y)
	{
		this.percentage = p;
		this.month = m;
		this.year = y;
	}
	
	public Double getPercentage() {
		return percentage;
	}
	public Integer getMonth() {
		return month;
	}
	public Integer getYear() {
		return year;
	}
}
