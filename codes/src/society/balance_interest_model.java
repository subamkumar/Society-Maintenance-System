package society;

public class balance_interest_model {
	
	private Integer id;
	private Integer flatid;
	private Double interest;
	private Integer payment_id;
	private Integer status;
	private Integer month;
	private Integer year;
	private Double how_much_paid;
	
	public balance_interest_model(Integer id,Integer flatid,Double interest,Integer pay_id,Integer s,Integer m,Integer y,Double hmp)
	{
		this.id = id;
		this.flatid = flatid;
		this.interest = interest;
		this.payment_id = pay_id;
		this.status = s;
		this.month = m;
		this.year = y;
		this.how_much_paid = hmp;
	}
	
	public Integer getId() {
		return id;
	}
	public Integer getFlatid() {
		return flatid;
	}
	public Double getInterest() {
		return interest;
	}
	public Integer getPayment_id() {
		return payment_id;
	}
	public Integer getStatus() {
		return status;
	}
	public Integer getMonth() {
		return month;
	}
	public Integer getYear() {
		return year;
	}
	public Double getHow_much_paid() {
		return how_much_paid;
	}
	
}
