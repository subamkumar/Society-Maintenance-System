package society;

import java.util.LinkedList;

public class specificflat {
	
	Integer flatid = 0;
	TenantModel ActiveTenant = null;
	LinkedList<TenantModel> tenantsinfo = null;//new LinkedList<TenantModel>();
	LinkedList<CoownerModel> coownerinfo = null;//new LinkedList<CoownerModel>();
	LinkedList<TenantModel> Inactivetenantsinfo = null;//new LinkedList<TenantModel>();
	
	public specificflat(String flatno)
	{
		FlatModel f = database.getDatabase().getFlatByNumber(flatno);
		
		if(f!=null)
			flatid = f.getFlatID();
		
		tenantsinfo = database.getDatabase().getTenant(flatid);
		coownerinfo = database.getDatabase().getCoowner(flatid);
		if(tenantsinfo!=null)
		{
			int loop = 0;
			while(loop < tenantsinfo.size())
			{
				TenantModel t = tenantsinfo.get(loop+1);
				if(t.getActive() == 0)
				{
					Inactivetenantsinfo.addLast(t);
				}	
				else
				{
					ActiveTenant = t;
				}
				loop++;
			}
		}
	}
	
	
	public FlatModel getFlatInfo()
	{
		if(flatid == 0)
			return null;
		else
			return database.getDatabase().getFlat(flatid);
	}
	
	public LinkedList<TenantModel> getInactiveTenant()
	{
		return Inactivetenantsinfo;
	}
	
	public TenantModel getActiveTenant()
	{
		return ActiveTenant;
	}
	
	public LinkedList<CoownerModel> getCoownerInfo()
	{
		return coownerinfo;
	}
}
