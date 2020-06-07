package society;

public class advance_UI_Info {
	
	private Double remaining_advance;
	private Double one_year_maintenance;
	private Double discount;
	private Double payable_amt;
	
	public advance_UI_Info(Double r,Double one,Double dis,Double p_amt){
		this.remaining_advance = r;
		this.one_year_maintenance = one;
		this.discount = dis;
		this.payable_amt = p_amt;
	}
	
	public Double getPayable_amt(){
		return payable_amt;
	}
	public Double getRemaining_advance() {
		return remaining_advance;
	}
	public Double getOne_year_maintenance() {
		return one_year_maintenance;
	}
	public Double getDiscount() {
		return discount;
	}
}
