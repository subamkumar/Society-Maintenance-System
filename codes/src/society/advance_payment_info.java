package society;

public class advance_payment_info {

	private Integer id;
	private Integer flatid;
	private Integer payment_id;
	private Double discount;
	private Integer receipt;
	
	public advance_payment_info(Integer id,Integer fid,Integer pay_id,Double dis,Integer rec)
	{
		this.id = id;
		this.flatid = fid;
		this.payment_id = pay_id;
		this.discount = dis;
		this.receipt = rec;
	}
	
	public Integer getReceipt()
	{
		return receipt;
	}
	
	public Integer getId() {
		return id;
	}
	public Integer getFlatid() {
		return flatid;
	}
	public Integer getPayment_id() {
		return payment_id;
	}
	public Double getDiscount() {
		return discount;
	}
	
	
}
