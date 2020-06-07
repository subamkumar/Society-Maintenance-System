package society;

public class payment {

	private Integer id;
	private Integer flatid;
	private Double amount;
	private String paid_date;
	private String type;
	private String type_detail;
	private String payment_info;
	
	public payment(Integer id,Integer f,Double a,String date,String type,String t_detail,String p_info)
	{
		this.id = id;
		this.flatid = f;
		this.amount = a;
		this.paid_date = date;
		this.type = type;
		this.type_detail = t_detail;
		this.payment_info = p_info;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Integer getFlatid() {
		return flatid;
	}
	public Double getAmount() {
		return amount;
	}
	public String getPaid_date() {
		return paid_date;
	}
	public String getType() {
		return type;
	}
	public String getType_detail() {
		return type_detail;
	}
	public String getPayment_info() {
		return payment_info;
	}
	
	
}
