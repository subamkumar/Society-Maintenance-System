package society;

public class balance_UI_Info {

	private Double balance;
	private String balance_details;
	
	public balance_UI_Info(Double b,String s){
		this.balance = b;
		this.balance_details = s;
	}
	
	public Double getBalance() {
		return balance;
	}
	public String getBalance_details() {
		return balance_details;
	}
	
	
}
