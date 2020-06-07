package society;

public class balance_payment_info {
	
	private Integer id;
	private Integer flatid;
	private Integer payment_id;
	private Integer info_id;
	private Integer receipt;
	
	public balance_payment_info(Integer i,Integer f,Integer p,Integer info,Integer rec)
	{
		this.id = i;
		this.flatid = f;
		this.payment_id = p;
		this.info_id = info;
		this.receipt = rec;
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

	public Integer getInfo_id() {
		return info_id;
	}

	public Integer getReceipt() {
		return receipt;
	}
	
	
}
