package society;

public class income {
	
	private Integer id;
	private Double amt;
	private String type;
	private String type_detail;
	private String desc;
	private String dop;
	private String header;
	
	public income(Integer i,Double amt,String t,String ty,String desc,String dop,String h)
	{
		this.id = i;
		this.amt = amt;
		this.type = t;
		this.type_detail = ty;
		this.desc = desc;
		this.dop = dop;
		this.header = h;
	}
	
	public Integer getId() {
		return id;
	}
	public Double getAmt() {
		return amt;
	}
	public String getType() {
		return type;
	}
	public String getHeader() {
		return header;
	}
	public String getType_detail() {
		return type_detail;
	}
	public String getDesc() {
		return desc;
	}
	public String getDop() {
		return dop;
	}
	
	
}
