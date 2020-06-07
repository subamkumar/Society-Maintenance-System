package society;

public class payment_bounce {
	
	private Integer id;
	private Integer payment_id;
	private Double penalty;
	private String desc;
	
	public payment_bounce(Integer i,Integer p_id,Double pen,String d)
	{
		this.id = i;
		this.payment_id = p_id;
		this.penalty = pen;
		this.desc = d;
	}
	
	public Integer getId() {
		return id;
	}
	public Integer getPayment_id() {
		return payment_id;
	}
	public Double getPenalty() {
		return penalty;
	}
	public String getDesc() {
		return desc;
	}
	
	
}
