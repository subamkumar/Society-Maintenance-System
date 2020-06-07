package society;

public class TenantModel {

	private Integer id;
	private Integer fid;
	private String tenant_name;
	private String email;
	private String mobile;
	private Integer active;
	
	public TenantModel(Integer id,Integer flatid,String name,String email,String mobile,Integer a)
	{
		this.id = id;
		this.fid = flatid;
		this.tenant_name = name;
		this.email = email;
		this.mobile = mobile;
		this.active = a;
	}
	
	
	
	public Integer getId() {
		return id;
	}
	public Integer getFid() {
		return fid;
	}
	public String getTenant_name() {
		return tenant_name;
	}
	public String getEmail() {
		return email;
	}
	public String getMobile() {
		return mobile;
	}
	public Integer getActive() {
		return active;
	}
}
