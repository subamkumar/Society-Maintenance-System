package society;

public class sorttenant extends sortmodel{

	private String flatno;
	private String tenants_name;
	private String area;
	private String email;
	private String mobile;
	
	public sorttenant(String no,String t,String a,String e,String m)
	{
		this.flatno = no;
		this.tenants_name = t;
		this.area = a;
		this.email = e;
		this.mobile = m;
	}
	
	public String getFlatno() {
		return flatno;
	}
	public String getTenants_name() {
		return tenants_name;
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
