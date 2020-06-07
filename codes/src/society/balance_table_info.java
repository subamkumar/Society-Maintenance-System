package society;

public class balance_table_info {
	
	private Integer id;
	private Double balance_amt;
	private String payment_date;
	private String balance_info;
	private String payment_details;
	private Integer receipt;
	
	public balance_table_info(Integer id,Double bal_amt,String pay_date,String bal_info,String pay_detail,Integer rec){
		
		this.id = id;
		this.balance_amt = bal_amt;
		this.payment_date = pay_date;
		this.balance_info = bal_info;
		this.payment_details = pay_detail;
		this.receipt = rec;
	}
	
	public Integer getId() {
		return id;
	}
	public Double getBalance_amt() {
		return balance_amt;
	}
	public String getPayment_date() {
		return payment_date;
	}
	public String getBalance_info() {
		return balance_info;
	}
	public String getPayment_details() {
		return payment_details;
	}
	public Integer getReceipt() {
		return receipt;
	}

	
}
