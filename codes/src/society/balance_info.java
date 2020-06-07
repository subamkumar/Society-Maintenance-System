package society;

public class balance_info {
	
	private Integer id;
	private Integer bid;
	private String info;
	
	public balance_info(Integer id,Integer bid,String info)
	{
		this.id = id;
		this.bid = bid;
		this.info = info;
	}
	
	public Integer getId() {
		return id;
	}
	public Integer getBid() {
		return bid;
	}
	public String getInfo() {
		return info;
	}
}
