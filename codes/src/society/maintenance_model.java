package society;

public class maintenance_model {
	
	private Integer id;
	private Integer flatid;
	private Integer month;
	private Integer year;
	private Double maint_charge;
	private Double sink_charge;
	private Double occp_charge;
	private Double prev_balance;
	private Double adv_payment;
	private Integer payment_id;
	private Integer status;
	private Double how_much_paid;
	private Integer mail;
	private Integer receipt;
	
	public maintenance_model(Integer id,Integer flatid,Integer month,Integer year,Double m_charge,Double s_charge,Double o_charge,
			Double p_balance,Double a_payment,Integer pay_id,Integer status,Double hmp,Integer mail,Integer receipt)
	{
		this.id = id;
		this.flatid = flatid;
		this.month = month;
		this.year = year;
		this.maint_charge = m_charge;
		this.sink_charge = s_charge;
		this.occp_charge = o_charge;
		this.prev_balance = p_balance;
		this.adv_payment = a_payment;
		this.payment_id = pay_id;
		this.status = status;
		this.how_much_paid = hmp;
		this.mail = mail;
		this.receipt = receipt;
	}
	
	
	public Integer getId() {
		return id;
	}
	public Integer getFlatid() {
		return flatid;
	}
	public Integer getMonth() {
		return month;
	}
	public Integer getYear() {
		return year;
	}
	public Double getMaint_charge() {
		return maint_charge;
	}
	public Double getSink_charge() {
		return sink_charge;
	}
	public Double getOccp_charge() {
		return occp_charge;
	}
	public Double getPrev_balance() {
		return prev_balance;
	}
	public Double getAdv_payment() {
		return adv_payment;
	}
	public Integer getPayment_id() {
		return payment_id;
	}
	public Integer getStatus() {
		return status;
	}
	public Double getHow_much_paid() {
		return how_much_paid;
	}
	public Integer getMail() {
		return mail;
	}
	public Integer getReceipt() {
		return receipt;
	}
}
