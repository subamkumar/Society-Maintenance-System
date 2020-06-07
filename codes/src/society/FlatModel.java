package society;

public class FlatModel {
	
	private Integer flatid;
	private String flatno;
	private String area;
	private String owner_name;
	private String category;
	private String mobile;
	private String email;
	
	public FlatModel(Integer flatid,String flatno,String owner,String type,String mobile,String email,String area)
	{
		this.flatid = flatid;
		this.flatno = flatno;
		this.area = area;
		this.owner_name = owner;
		this.category = type;
		this.mobile = mobile;
		this.email = email;
	}
	
	public Integer getFlatID()
	{
		return flatid;
	}
	
	public String getArea()
	{
		return area;
	}
	
	public String getMobile()
	{
		return mobile;
	}

	public String getEmail()
	{
		return email;
	}
	
	public String getFlatNo()
	{
		return flatno;
	}
	
	public String getOwnerName()
	{
		return owner_name;
	}
	
	public String getOwnerCategory()
	{
		return category;
	}
}
