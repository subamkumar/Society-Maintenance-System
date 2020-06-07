package society;

public class expenditure_info {

	private Integer id;
	private String desc;
	private Double amt;
	private String date;
	private String type;
	private String type_detail;
	private String header;
	
	public expenditure_info(Integer id,String d,Double a,String date,String t,String td,String h)
	{
		this.id = id;
		this.desc = d;
		this.amt = a;
		this.date = date;
		this.type = t;
		this.type_detail = td;
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
	public String getType() {
		return type;
	}
	public String getType_detail() {
		return type_detail;
	}
	public String getHeader() {
		return header;
	}
}
