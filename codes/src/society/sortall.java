package society;

public class sortall extends sortmodel{

	private String flatno;
	private String name;
	private String area;
	private String category;
	private String email;
	private String mobile;
	
	public sortall(String no,String n,String a,String c,String e,String m)
	{
		this.flatno = no;
		this.name = n;
		this.area = a;
		this.category = c;
		this.email = e;
		this.mobile = m;
	}
	
	
	public String getFlatno() {
		return flatno;
	}
	public String getName() {
		return name;
	}
	public String getArea() {
		return area;
	}
	public String getCategory() {
		return category;
	}
	public String getEmail() {
		return email;
	}
	public String getMobile() {
		return mobile;
	}
}
