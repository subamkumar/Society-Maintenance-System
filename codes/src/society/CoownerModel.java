package society;


public class CoownerModel {

	private Integer id;
	private Integer fid;
	private String coowner_name;
	private String email;
	private String mobile;
	
	public CoownerModel(Integer id,Integer flatid,String name,String email,String mobile)
	{
		this.id = id;
		this.fid = flatid;
		this.coowner_name = name;
		this.email = email;
		this.mobile = mobile;
	}
	
	public Integer getId() {
		return id;
	}
	public Integer getFid() {
		return fid;
	}
	
	public String getCoowner_name() {
		return coowner_name;
	}
	public String getEmail() {
		return email;
	}
	public String getMobile() {
		return mobile;
	}
	
	/*public void insertCoownerName(String name)
	{
		coowner_name.add(name);
	}
	
	public void insertCoownerEmail(String e)
	{
		email.add(e);
	}
	
	public void insertCoownerMobile(String m)
	{
		mobile.add(m);
	}*/
}
