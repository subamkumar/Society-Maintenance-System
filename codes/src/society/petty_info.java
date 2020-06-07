package society;

public class petty_info {
	
	private Integer id;
	private String desc;
	private Double amt;
	private String date;
	private String header;
	
	public petty_info(Integer i,String d,Double a,String date,String h)
	{
		this.id = i;
		this.desc = d;
		this.amt = a;
		this.date = date;
		this.header = h;
	}
	
	public Integer getId() {
		return id;
	}
	public String getDesc() {
		return desc;
	}
	public Double getAmt() {
		return amt;
	}
	public String getDate() {
		return date;
	}
	public String getHeader() {
		return header;
	}
}
