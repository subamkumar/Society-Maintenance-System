package society;

public class advance_discount_model {

	private Double percentage;
	private Integer month_fixed;
	private Integer year_fixed;
		
	public advance_discount_model(Double p,Integer m,Integer y)
	{
		this.percentage = p;
		this.month_fixed = m;
		this.year_fixed = y;
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
