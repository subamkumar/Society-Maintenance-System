package society;

public class sortowners extends sortmodel{

	private String flatno;
	private String owner_name;
	private String area;
	private String email;
	private String mobile;
	
	public sortowners(String no,String n,String a,String e,String m)
	{
		this.flatno = no;
		this.owner_name = n;
		this.area = a;
		this.email = e;
		this.mobile = m;
	}
	
	public String getFlatno() {
		return flatno;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public String getArea() {
		return area;
	}
	public String getEmail() {
		return email;
	}
	public String getMobile() {
		return mobile;
	}
}
