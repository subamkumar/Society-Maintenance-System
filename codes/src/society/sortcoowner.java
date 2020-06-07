package society;

import java.util.List;

public class sortcoowner extends sortmodel{

	private String flatno;
	private List<String> coowner_name;
	private String area;
	private List<String> email;
	private List<String> mobile;
	
	public sortcoowner(String no,List<String> cn,String a,List<String> ce,List<String> cm)
	{
		this.flatno = no;
		this.coowner_name = cn;
		this.area = a;
		this.email = ce;
		this.mobile = cm;
	}
	
	public String getFlatno() {
		return flatno;
	}
	public List<String> getCoowner_name() {
		return coowner_name;
	}
	public String getArea() {
		return area;
	}
	public List<String> getEmail() {
		return email;
	}
	public List<String> getMobile() {
		return mobile;
	}
	
}
