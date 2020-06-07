package society;

public class advance_table_info {
	
	private Integer id;
	private Double amount;
	private Double discount;
	private String date;
	private String Info;
	private Integer receipt;
	
	public advance_table_info(Integer id,Double amt,Double dis,String date,String inf,Integer rec){
		
		this.id = id;
		this.amount = amt;
		this.discount = dis;
		this.date = date;
		this.Info = inf;
		this.receipt = rec;
	}
	
	public Integer getId() {
		return id;
	}
	public Double getAmount() {
		return amount;
	}
	public Double getDiscount() {
		return discount;
	}
	public String getDate() {
		return date;
	}
	public String getInfo() {
		return Info;
	}
	public Integer getReceipt() {
		return receipt;
	}
	
	
}
