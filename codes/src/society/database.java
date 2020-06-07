package society;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class database {

	private Connection con;
	private static database db = null;
	private database(){
		try
		{
			
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = 	DriverManager.getConnection("jdbc:mysql://localhost:3306/society_latierra?user=root&password=root");
		}
		catch(Exception e){
			try
			{
				FileWriter fw=new FileWriter("D:/a2.txt");
				fw.append("Error: "+ e.getMessage());
				System.out.println("Println: "+ e.getMessage());
		        fw.close();
			}
			catch(Exception e1)
			{
				System.out.println("****************Error**************");
			}
			e.printStackTrace();
		}
	}
	
	public static database getDatabase()
	{
		if(db == null)
		{
			db = new database();
			return db;
		}
		else
		{
			return db;
		}
	}

	public int checkAuthentication(String username,String password)
	{
		try
		{
			String sql = "select id from user where username=? and password=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				return rs.getInt("id");
			else 
				return 0;
		}
		catch(Exception e)
		{
			try
			{
				FileWriter fw=new FileWriter("D:/a1.txt");
				fw.append("Error1: "+ e.getMessage());    
		        fw.close();
			}
			catch(Exception e1)
			{
				System.out.println("****************Error1**************");
			}
			e.printStackTrace();
			return 0;
		}
	}
	
	
/*
 ****************************
 *	FLATS DETAILS 			*		
 *	SECTION STARTS 			*
 ****************************
*/	
	
	
	public int insertFlats(String flatno,String area,String owner_name,String email,String mobile,String category)
	{
		try
		{
			String sql = "insert into flats(flatno,owner_name,area,email,mobile,category) values(?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, flatno);
			pstmt.setString(2, owner_name);
			pstmt.setString(3, area);
			pstmt.setString(4, email);
			pstmt.setString(5, mobile);
			pstmt.setString(6, category);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public int updateFlats(Integer flatid,String flatno,String ownername,String area,String email,String mobile,
			String category,String tenant_name,String tenant_email,String tenant_mobile)
	{
		FlatModel f = getFlat(flatid);
		
		if(f!=null)
		{
			try
			{
				con.setAutoCommit(false);
				if(f.getOwnerCategory().equalsIgnoreCase("tenant") && category.equalsIgnoreCase("owner"))
				{
					//update Flats
					String sql = "update flats set flatno=?,owner_name=?,area=?,email=?,mobile=?,category=? where flatid=?";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setString(1, flatno);
					pstmt.setString(2, ownername);
					pstmt.setString(3, area);
					pstmt.setString(4, email);
					pstmt.setString(5, mobile);
					pstmt.setString(6, category);
					pstmt.setInt(7, flatid);
					
					if(pstmt.executeUpdate() > 0)
					{
						//remove tenant associating with this flatid
						int r = deactivateTenant(flatid);
						if(r > 0)
						{
							con.commit();
							return r;
						}
						else
						{
							con.rollback();
							return r;
						}
					}
					else
					{
						con.rollback();
						return 0;
					}
				}
				else if(f.getOwnerCategory().equalsIgnoreCase("owner") && category.equalsIgnoreCase("tenant"))
				{
					//update Flats
					String sql = "update flats set flatno=?,owner_name=?,area=?,email=?,mobile=?,category=? where flatid=?";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setString(1, flatno);
					pstmt.setString(2, ownername);
					pstmt.setString(3, area);
					pstmt.setString(4, email);
					pstmt.setString(5, mobile);
					pstmt.setString(6, category);
					pstmt.setInt(7, flatid);
					
					if(pstmt.executeUpdate() > 0)
					{
						//insert tenants associating with this flatid
						int r = insertTenant(flatid,tenant_name,tenant_email,tenant_mobile,1);
						if(r > 0)
						{
							con.commit();
							return r;
						}
						else
						{
							con.rollback();
							return r;
						}
					}
					else
					{
						con.rollback();
						return 0;
					}
					 
				}
				else
				{
					//update flats
					String sql = "update flats set flatno=?,owner_name=?,area=?,email=?,mobile=?,category=? where flatid=?";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setString(1, flatno);
					pstmt.setString(2, ownername);
					pstmt.setString(3, area);
					pstmt.setString(4, email);
					pstmt.setString(5, mobile);
					pstmt.setString(6, category);
					pstmt.setInt(7, flatid);
					
					int res = pstmt.executeUpdate();
					
					if(res > 0)
					{
						con.commit();
						return res;
					}
					else
					{
						con.rollback();
						return res;
					}
				}
		
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return 0;
			}
		}
		else
			return 0;
	}
	
	public int updateFlatByID(Integer pid,String name,String area,String email,String mobile,String cat)
	{
		try
		{
			
			String sql = "update flats set owner_name=?,area=?,email=?,mobile=?,category=? where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, area);
			pstmt.setString(3, email);
			pstmt.setString(4, mobile);
			pstmt.setString(5, cat);
			pstmt.setInt(6, pid);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public FlatModel getFlat(Integer flatid)
	{
		try
		{
			String sql = "select * from flats where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, flatid);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				return new FlatModel(rs.getInt("id"),rs.getString("flatno"),rs.getString("owner_name"),
						rs.getString("category"),rs.getString("mobile"),rs.getString("email"),rs.getString("area"));
			}
			else
				return null;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public LinkedList<FlatModel> getAllFlats()
	{
		LinkedList<FlatModel> ll = new LinkedList<FlatModel>();
		try
		{
			String sql = "select * from flats";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				ll.addLast(new FlatModel(rs.getInt("id"),rs.getString("flatno"),rs.getString("owner_name"),
						rs.getString("category"),rs.getString("mobile"),rs.getString("email"),rs.getString("area")));
			}
			
			if(ll.isEmpty())
				return null;
			else
				return ll;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public FlatModel getFlatByNumber(String flatno)
	{
		try
		{
			String sql = "select * from flats where flatno=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, flatno);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				return new FlatModel(rs.getInt("id"),rs.getString("flatno"),rs.getString("owner_name"),
						rs.getString("category"),rs.getString("mobile"),rs.getString("email"),rs.getString("area"));
			}
			else
				return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public int deactivateTenant(Integer flatid)
	{
		try
		{
			String sql = "update tenants set active=? where fid=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, flatid);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int insertTenant(Integer flatid,String tenant_name,String tenant_email,String tenant_mobile,Integer active)
	{
		try
		{
			String sql = "insert into tenants(fid,tenant_name,email,mobile,active) values(?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, flatid);
			pstmt.setString(2, tenant_name);
			pstmt.setString(3, tenant_email);
			pstmt.setString(4, tenant_mobile);
			pstmt.setInt(5, active);
			
			return pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateTenant(Integer flatid,String tenant_name,String email,String mobile)
	{
		try
		{
			String sql = "update tenants set tenant_name=?,email=?,mobile=? where fid=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tenant_name);
			pstmt.setString(2, email);
			pstmt.setString(3, mobile);
			pstmt.setInt(4, flatid);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public LinkedList<TenantModel> getTenant(Integer flatid)
	{
		LinkedList<TenantModel> ll = new LinkedList<TenantModel>();
		
		try
		{
			String sql = "select * from tenants where fid=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,flatid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				ll.addLast(new TenantModel(rs.getInt("id"),rs.getInt("fid"),rs.getString("tenant_name"),rs.getString("email"),rs.getString("mobile"),rs.getInt("active")));
			}
			
			if(ll.isEmpty())
				return null;
			else
				return ll;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	public int insertCoowner(Integer flatid,String name,String email,String mobile)
	{
		try
		{
			String sql = "insert into coowner(fid,coowner_name,email,mobile) values(?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, flatid);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, mobile);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int deleteCoowner(Integer id)
	{
		try
		{
			String sql = "delete from coowner where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateCoowner(Integer flatid,String coowner_name,String email,String mobile)
	{
		try
		{
			String sql = "update coowner set coowner_name=?,email=?,mobile=? where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, coowner_name);
			pstmt.setString(2, email);
			pstmt.setString(3, mobile);
			pstmt.setInt(4, flatid);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int deactivateCoowner(Integer flatid)
	{
		try
		{
			String sql = "update coowner set active=? where fid=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, flatid);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	/*public CoownerModel getCoownerByID(Integer id)
	{
		try
		{
			
		}
		catch(Exception e)
		{
			
		}
	}*/
	
	public LinkedList<CoownerModel> getCoowner(Integer flatid)
	{
		LinkedList<CoownerModel> ll = new LinkedList<CoownerModel>();
		
		try
		{
			String sql = "select * from coowner where fid=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, flatid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				ll.addLast(new CoownerModel(rs.getInt("id"),rs.getInt("fid"),rs.getString("coowner_name"),rs.getString("email"),rs.getString("mobile")));
			}
			
			if(ll.isEmpty())
				return null;
			else
				return ll;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/*public LinkedList<CoownerModel> getCoowner(Integer flatid)
	{
		LinkedList<CoownerModel> ll = new LinkedList<CoownerModel>();
		HashMap<Integer,CoownerModel> hmap = new HashMap<Integer,CoownerModel>();
		
		
		try
		{
			String sql = "select * from coowner where fid=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,flatid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				if(hmap.containsKey(rs.getInt("fid")))
				{
					CoownerModel cm = hmap.remove(rs.getInt("fid"));
					cm.insertCoownerEmail(rs.getString("email"));
					cm.insertCoownerMobile(rs.getString("mobile"));
					cm.insertCoownerName(rs.getString("coowner_name"));
					hmap.put(rs.getInt("fid"), cm);
				}
				else
				{
					List<String> n = new ArrayList<String>();
					n.add(rs.getString("coowner_name"));
					List<String> e = new ArrayList<String>();
					e.add(rs.getString("email"));
					List<String> m = new ArrayList<String>();
					m.add(rs.getString("mobile"));
					ll.add(new CoownerModel(rs.getInt("id"),rs.getInt("fid"),n,e,m));
				}
			}
			
			if(ll.isEmpty())
				return null;
			else
				return ll;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}*/
	
	
	
	/*
	 ****************************
	 *	FLATS DETAILS 			*		
	 *	SECTION ENDS 			*
	 ****************************
	*/
	
//==================================================================================================================	
	
	/*
	 ****************************
	 *	MAINTENANCE DETAILS		*		
	 *	SECTION STARTS 			*
	 ****************************
	*/
	
	public int createMaintenance(Integer month,Integer year)
	{
		int res = 1;
		
		try
		{
			con.setAutoCommit(false);
			LinkedList<FlatModel> flist = getAllFlats();
			
			if(flist == null)
			{
				System.out.println("Yess2");
				res = 0;
				con.rollback();
			}
				
			
			while(!flist.isEmpty())
			{
				FlatModel f = flist.removeFirst();
				maintenance_charge_model m_charge = getMaintenanceCharge();
				sinking_charge_model s_charge = getSinkingCharge();
				occupancy_charge_model o_charge = getOccupancyCharge();
				Double total_amt = 0.0;
				Double occp_charge = 0.0;
				Double maint_charge = 0.0;
				Double sink_charge = 0.0;		
						
				if(m_charge != null || s_charge != null || o_charge!=null)
				{
					maint_charge = Math.ceil(m_charge.getCharge());
					sink_charge = Math.ceil((s_charge.getCharge() * Double.parseDouble(f.getArea()) * s_charge.getPercentage())/1200);
					if(f.getOwnerCategory().equalsIgnoreCase("tenant"))
						occp_charge = Math.ceil((o_charge.getPercentage() * m_charge.getCharge())/100);
				
					total_amt = Math.ceil(maint_charge + sink_charge + occp_charge);
				}
				else
				{
					System.out.println("Yess21");
					res = 0;
					con.rollback();
				}
				
				Double prev_balance = getPreviousBalance(f.getFlatID());
				Double adv_payment = getAdvancePayment(f.getFlatID());
				
				if(prev_balance == -1.0 || adv_payment == -1.0)
				{
					System.out.println("Yess22");
					res = 0;
					con.rollback();
				}
				
				
				
				
				
				if(adv_payment > 0)
				{
					if(adv_payment >= total_amt)
					{
						advance_payment_info api = getAdvancePaymentInfo(f.getFlatID());
						
						if(api!=null)
						{
							int im = insertMaintenance(f.getFlatID(), month, year, maint_charge, sink_charge, occp_charge, prev_balance, total_amt, api.getPayment_id(), 1, 0.0, 0, 0);
							int uap = updateAdvancePayment(f.getFlatID(), total_amt, "subtract");
							
							if(im == -1 || uap == -1)
							{
								System.out.println("Yess25");
								res = 0;
								con.rollback();
							}
						}
						else
						{
							System.out.println("Yess26");
							res = 0;
							con.rollback();
						}
						
						
						
						
					}
					else
					{
						int im = insertMaintenance(f.getFlatID(), month, year, maint_charge, sink_charge, occp_charge, prev_balance, 0.0, 0, 0, adv_payment, 0, 0);
						int uap = updateAdvancePayment(f.getFlatID(), adv_payment, "subtract");
						int ubp = updateBalancePayment(f.getFlatID(), (total_amt-adv_payment), "add");
						
						if(im == -1 || uap == -1 || ubp == -1)
						{
							System.out.println("Yess27");
							res = 0;
							con.rollback();
						}
						
					}
				}
				else
				{
					
					/*Main logic starts from here
					 *if the month is JAN(1),APRIL(4),JULY(7),OCT(10) then if there is any prev balance 21% interest enter will be enter
					 * 
					*/
					Double interest = 0.0;
					if(month == 1 || month == 4 || month == 7 || month == 10)
					{
						if(prev_balance > 0)
						{
							//Double interest = 0.0;
							
							interest_percentage_model ipm = getInterestPercentage();
							
							if(ipm!=null)
							{
								interest = ((ipm.getPercentage()/4) * prev_balance)/100;
								interest = Math.ceil(interest);
							}
							else
							{
								System.out.println("Yess23");
								res = 0;
								con.rollback();
							}
							
							
							if(insertInterest(f.getFlatID(), interest, 0, 0, month, year, 0.0) == 0)
							{
								System.out.println("Yess24");
								res = 0;
								con.rollback();
							}
						}
					}
					
					
					
					int ubp = updateBalancePayment(f.getFlatID(), (total_amt+interest), "add");
					prev_balance = getPreviousBalance(f.getFlatID());
					int im = insertMaintenance(f.getFlatID(), month, year, maint_charge, sink_charge, occp_charge, prev_balance, 0.0, 0, 0, adv_payment, 0, 0);
					if(ubp == -1 || im == -1)
					{
						System.out.println("Yess28");
						res = 0;
						con.rollback();
					}
				}
				
			}
			
			if(res == 1)
			{
				con.commit();
				con.setAutoCommit(true);
				return 1;
			}	
			else
			{
				con.commit();
				con.setAutoCommit(true);
				return 0;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public LinkedList<maintenance_model> getMaintenanceReport(Integer month,Integer quarter,Integer year,String type)
	{
		LinkedList<maintenance_model> ll = new LinkedList<maintenance_model>();
		
		try
		{
			if(type.equalsIgnoreCase("month"))
			{
				String sql = "select * from maintenance where month=? and year=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, month);
				pstmt.setInt(2, year);
				
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next())
				{
					maintenance_model m = new maintenance_model(rs.getInt("id"),rs.getInt("flatid"),rs.getInt("month"),
							rs.getInt("year"),rs.getDouble("maint_charge"),rs.getDouble("sink_charge"),rs.getDouble("occp_charge"),
							rs.getDouble("prev_balance"),rs.getDouble("adv_payment"),rs.getInt("payment_id"),rs.getInt("status"),
							rs.getDouble("how_much_paid"),rs.getInt("mail"),rs.getInt("receipt"));
					ll.addLast(m);
				}
				
			}
			else if(type.equalsIgnoreCase("quarter"))
			{
				String sql = "";
				
				if(quarter == 1)
				{
					sql = "select * from maintenance where year=? and month in(1,2,3)";
				}
				else if(quarter == 2)
				{
					sql = "select * from maintenance where year=? and month in(4,5,6)";
				}
				else if(quarter == 3)
				{
					sql = "select * from maintenance where year=? and month in(7,8,9)";
				}
				else if(quarter == 4)
				{
					sql = "select * from maintenance where year=? and month in(10,11,12)";
				}
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,year);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next())
				{
					maintenance_model m = new maintenance_model(rs.getInt("id"),rs.getInt("flatid"),rs.getInt("month"),
							rs.getInt("year"),rs.getDouble("maint_charge"),rs.getDouble("sink_charge"),rs.getDouble("occp_charge"),
							rs.getDouble("prev_balance"),rs.getDouble("adv_payment"),rs.getInt("payment_id"),rs.getInt("status"),
							rs.getDouble("how_much_paid"),rs.getInt("mail"),rs.getInt("receipt"));
					ll.addLast(m);
				}
			}
			else
			{
				String sql = "select * from maintenance where year=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, year);
				
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next())
				{
					maintenance_model m = new maintenance_model(rs.getInt("id"),rs.getInt("flatid"),rs.getInt("month"),
							rs.getInt("year"),rs.getDouble("maint_charge"),rs.getDouble("sink_charge"),rs.getDouble("occp_charge"),
							rs.getDouble("prev_balance"),rs.getDouble("adv_payment"),rs.getInt("payment_id"),rs.getInt("status"),
							rs.getDouble("how_much_paid"),rs.getInt("mail"),rs.getInt("receipt"));
					ll.addLast(m);
				}
			}
			
			if(ll.isEmpty())
				return null;
			else
				return ll;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public LinkedList<maintenance_model> getAllMaintenance()
	{
		LinkedList<maintenance_model> ll = new LinkedList<maintenance_model>();
		try
		{
			String sql = "select * from maintenance";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				maintenance_model m = new maintenance_model(rs.getInt("id"),rs.getInt("flatid"),rs.getInt("month"),
						rs.getInt("year"),rs.getDouble("maint_charge"),rs.getDouble("sink_charge"),rs.getDouble("occp_charge"),
						rs.getDouble("prev_balance"),rs.getDouble("adv_payment"),rs.getInt("payment_id"),rs.getInt("status"),
						rs.getDouble("how_much_paid"),rs.getInt("mail"),rs.getInt("receipt"));
				ll.addLast(m);
			}
			
			if(ll.isEmpty())
				return null;
			else
				return ll;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Double getPreviousBalance(Integer flatid)
	{
		try
		{
			String sql = "select Balance from balance_payment where flatid=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, flatid);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				return rs.getDouble("Balance");
			else
				return -1.0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1.0;
		}
	}
	
	public Double getPrevBalanceByMonth(Integer flatid,Integer month,Integer year)
	{
		
		try
		{
			String sql = "select * from maintenance where flatid=? and month=? and year=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, flatid);
			pstmt.setInt(2, month);
			pstmt.setInt(3, year);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				return rs.getDouble("prev_balance");
			}
			else
			{
				return 0.0;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public int updateBalancePayment(Integer flatid,Double amt,String query)
	{
		try
		{
			if(query.equalsIgnoreCase("subtract"))
			{
				String sql = "update balance_payment set Balance = Balance - ? where flatid=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setDouble(1, amt);
				pstmt.setInt(2, flatid);
				
				return pstmt.executeUpdate();
			}	
			else
			{
				String sql = "update balance_payment set Balance = Balance + ? where flatid=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setDouble(1, amt);
				pstmt.setInt(2, flatid);
				
				return pstmt.executeUpdate();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateAdvancePayment(Integer flatid,Double amt,String query)
	{
		try
		{
			if(query.equalsIgnoreCase("subtract"))
			{
				String sql = "update advance_payment set advance = advance - ? where flatid=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setDouble(1, amt);
				pstmt.setInt(2, flatid);
				
				return pstmt.executeUpdate();
			}	
			else
			{
				String sql = "update advance_payment set advance = advance + ? where flatid=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setDouble(1, amt);
				pstmt.setInt(2, flatid);
				
				return pstmt.executeUpdate();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public Double getAdvancePayment(Integer flatid)
	{
		try
		{
			String sql = "select advance from advance_payment where flatid=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, flatid);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				return rs.getDouble("advance");
			else
				return -1.0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1.0;
		}
	}
	
	
	public int insertAdvancePaymentInfo(Integer flatid,Integer payment_id,Double dis,Integer rec)
	{
		try
		{
			String sql = "insert into advance_payment_info(flatid,payment_id,discount,receipt) values(?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, flatid);
			pstmt.setInt(2, payment_id);
			pstmt.setDouble(3, dis);
			pstmt.setInt(4, rec);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public advance_payment_info getAdvancePaymentInfo(Integer flatid)
	{
		try
		{
			String sql = "select * from advance_payment_info where flatid=? order by id desc limit 1";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, flatid);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				return new advance_payment_info(rs.getInt("id"),rs.getInt("flatid"),rs.getInt("payment_id"),rs.getDouble("discount"),rs.getInt("receipt"));
			else
				return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public LinkedList<advance_payment_info> getAllAdvancePaymentInfo(Integer flatid)
	{
		LinkedList<advance_payment_info> ll = new LinkedList<advance_payment_info>();
		try
		{
			String sql = "select * from advance_payment_info where flatid=? order by id desc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, flatid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				ll.addLast(new advance_payment_info(rs.getInt("id"),rs.getInt("flatid"),rs.getInt("payment_id"),rs.getDouble("discount"),rs.getInt("receipt")));
			}
			
			if(ll.isEmpty())
				return null;
			else 
				return ll;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	public int insertMaintenance(Integer flatid,Integer month,Integer year,Double m_charge,Double s_charge,Double o_charge,Double prev_balance,
			Double adv_payment,Integer pay_id,Integer status,Double how_much_paid,Integer mail,Integer receipt)
	{
		try
		{
			String sql = "insert into maintenance(flatid,month,year,maint_charge,sink_charge,occp_charge,prev_balance,adv_payment,"
					+ "payment_id,status,how_much_paid,mail,receipt) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, flatid);
			pstmt.setInt(2, month);
			pstmt.setInt(3, year);
			pstmt.setDouble(4, m_charge);
			pstmt.setDouble(5, s_charge);
			pstmt.setDouble(6, o_charge);
			pstmt.setDouble(7, prev_balance);
			pstmt.setDouble(8, adv_payment);
			pstmt.setInt(9, pay_id);
			pstmt.setInt(10, status);
			pstmt.setDouble(11, how_much_paid);
			pstmt.setInt(12, mail);
			pstmt.setInt(13, receipt);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int checkFirstMaintenanceEntry()
	{
		try
		{
			String sql = "select * from maintenance";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				return 1;
			else
				return 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public maintenance_model getMaintenance(Integer flatid,Integer month,Integer year)
	{
		try
		{
			String sql = "select * from maintenance where flatid=? and month=? and year=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, flatid);
			pstmt.setInt(2, month);
			pstmt.setInt(3, year);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				return new maintenance_model(rs.getInt("id"),rs.getInt("flatid"),rs.getInt("month"),rs.getInt("year"),
						rs.getDouble("maint_charge"),rs.getDouble("sink_charge"),rs.getDouble("occp_charge"),
						rs.getDouble("prev_balance"),rs.getDouble("adv_payment"),rs.getInt("payment_id"),rs.getInt("status"),
						rs.getDouble("how_much_paid"),rs.getInt("mail"),rs.getInt("receipt"));
			}
			else
				return null;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public int insertMaintenanceCharge(Double charge,Integer month,Integer year)
	{
		try
		{
			String sql = "insert into maintenance_charge(charge,month_fixed,year_fixed) values(?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setDouble(1, charge);
			pstmt.setInt(2, month);
			pstmt.setInt(3, year);
			
			return pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public maintenance_charge_model getMaintenanceCharge()//Getting the latest Maintenance Charge
	{
		
		try
		{
			String sql = "select * from maintenance_charge order by year_fixed desc,month_fixed desc,id desc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				return new maintenance_charge_model(rs.getDouble("charge"),rs.getInt("month_fixed"),rs.getInt("year_fixed"));
			else
				return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public LinkedList<maintenance_charge_model> getAllMaintenanceCharge()//Getting the latest Maintenance Charge
	{
		LinkedList<maintenance_charge_model> ll = new LinkedList<maintenance_charge_model>();
		try
		{
			String sql = "select * from maintenance_charge order by year_fixed desc,month_fixed desc,id desc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				ll.add(new maintenance_charge_model(rs.getDouble("charge"),rs.getInt("month_fixed"),rs.getInt("year_fixed")));
			}
			if(!ll.isEmpty())
				return ll;
			else
				return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public int insertSinkingCharge(Double charge,Double percentage,Integer month,Integer year)
	{
		try
		{
			String sql = "insert into sinking_charge(charge,percentage,month_fixed,year_fixed) values(?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setDouble(1, charge);
			pstmt.setDouble(2, percentage);
			pstmt.setInt(3, month);
			pstmt.setInt(4, year);
			
			return pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public sinking_charge_model getSinkingCharge()//Getting the latest Sinking Charge
	{
		
		try
		{
			String sql = "select * from sinking_charge order by year_fixed desc,month_fixed desc,id desc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				return new sinking_charge_model(rs.getDouble("charge"),rs.getDouble("percentage"),rs.getInt("month_fixed"),rs.getInt("year_fixed"));
			else
				return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public LinkedList<sinking_charge_model> getAllSinkingCharge()//Getting the latest Sinking Charge
	{
		LinkedList<sinking_charge_model> ll = new LinkedList<sinking_charge_model>();
		
		try
		{
			String sql = "select * from sinking_charge order by year_fixed desc,month_fixed desc,id desc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				ll.add(new sinking_charge_model(rs.getDouble("charge"),rs.getDouble("percentage"),rs.getInt("month_fixed"),rs.getInt("year_fixed")));
			}
			if(!ll.isEmpty())
				return ll;
			else
				return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public int insertOccupancyCharge(Double percentage,Integer month,Integer year)
	{
		try
		{
			String sql = "insert into occupancy_charge(percentage,month_fixed,year_fixed) values(?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setDouble(1, percentage);
			pstmt.setInt(2, month);
			pstmt.setInt(3, year);
			
			return pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public occupancy_charge_model getOccupancyCharge()//Getting the latest Non-Occupancy Charge
	{
		
		try
		{
			String sql = "select * from occupancy_charge order by year_fixed desc,month_fixed desc,id desc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				return new occupancy_charge_model(rs.getDouble("percentage"),rs.getInt("month_fixed"),rs.getInt("year_fixed"));
			else
				return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public LinkedList<occupancy_charge_model> getAllOccupancyCharge()//Getting the latest Non-Occupancy Charge
	{
		LinkedList<occupancy_charge_model> ll = new LinkedList<occupancy_charge_model>();
		
		try
		{
			String sql = "select * from occupancy_charge order by year_fixed desc,month_fixed desc,id desc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				ll.add(new occupancy_charge_model(rs.getDouble("percentage"),rs.getInt("month_fixed"),rs.getInt("year_fixed")));
			}
			
			if(!ll.isEmpty())
				return ll;
			else
				return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public int insertInterestPercentage(Double percentage,Integer month,Integer year)
	{
		try
		{
			String sql = "insert into interest_percentage(percentage,month_fixed,year_fixed) values(?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setDouble(1, percentage);
			pstmt.setInt(2, month);
			pstmt.setInt(3, year);
			
			return pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public interest_percentage_model getInterestPercentage()//Getting the latest Interest Charge
	{
		
		try
		{
			String sql = "select * from interest_percentage order by year_fixed desc,month_fixed desc,id desc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				return new interest_percentage_model(rs.getDouble("percentage"),rs.getInt("month_fixed"),rs.getInt("year_fixed"));
			else
				return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public LinkedList<interest_percentage_model> getAllInterestPercentage()//Getting the latest Interest Charge
	{
		LinkedList<interest_percentage_model> ll = new LinkedList<interest_percentage_model>();
		try
		{
			String sql = "select * from interest_percentage order by year_fixed desc,month_fixed desc,id desc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				ll.add(new interest_percentage_model(rs.getDouble("percentage"),rs.getInt("month_fixed"),rs.getInt("year_fixed")));
			}
			
			if(!ll.isEmpty())
				return ll;
			else
				return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public int insertAdvanceDiscount(Double percentage,Integer month,Integer year)
	{
		try
		{
			String sql = "insert into advance_discount(percentage,month_fixed,year_fixed) values(?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setDouble(1, percentage);
			pstmt.setInt(2, month);
			pstmt.setInt(3, year);
			
			return pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public advance_discount_model getAdvanceDiscount()//Getting the latest Interest Charge
	{
		
		try
		{
			String sql = "select * from advance_discount order by year_fixed desc,month_fixed desc,id desc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			
			if(rs.next())
				return new advance_discount_model(rs.getDouble("percentage"),rs.getInt("month_fixed"),rs.getInt("year_fixed"));
			else
				return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public LinkedList<advance_discount_model> getAllAdvanceDiscount()//Getting the latest Interest Charge
	{
		LinkedList<advance_discount_model> ll = new LinkedList<advance_discount_model>();
		try
		{
			String sql = "select * from advance_discount order by year_fixed desc,month_fixed desc,id desc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				ll.add(new advance_discount_model(rs.getDouble("percentage"),rs.getInt("month_fixed"),rs.getInt("year_fixed")));
			}
			
			if(!ll.isEmpty())
				return ll;
			else
				return null;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public int insertInterest(Integer flatid,Double interest,Integer payment_id,Integer status,Integer month,Integer year,Double how_much_paid)
	{
		try
		{
			String sql = "insert into balance_interest(flatid,interest,payment_id,status,month,year,how_much_paid) values(?,?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, flatid);
			pstmt.setDouble(2, interest);
			pstmt.setInt(3,payment_id);
			pstmt.setInt(4,status);
			pstmt.setInt(5,month);
			pstmt.setInt(6,year);
			pstmt.setDouble(7, how_much_paid);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public LinkedList<balance_interest_model> getInterest(Integer flatid)
	{
		
		LinkedList<balance_interest_model> ll = new LinkedList<balance_interest_model>();
		
		try
		{
			String sql = "select * from balance_interest where flatid=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, flatid);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				ll.addLast(new balance_interest_model(rs.getInt("id"),rs.getInt("flatid"),rs.getDouble("interest"),rs.getInt("payment_id"),rs.getInt("status"),rs.getInt("month"),rs.getInt("year"),rs.getDouble("how_much_paid")));
			}
			
			if(ll.isEmpty())
				return null;
			else
				return ll;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Double getPrevInterest(Integer flatid,Integer month,Integer year)
	{
		Double interest = 0.0;
		try
		{
			String sql = "select * from balance_interest where flatid=? and month <= ? and year <= ? and status=0";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, flatid);
			pstmt.setInt(2, month);
			pstmt.setInt(3, year);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				interest += rs.getDouble("interest");
			}
			return interest;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public int insertFinalMonth(Integer month,Integer year)
	{
		try
		{
			String sql = "insert into final_month(month,year) values(?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, month);
			pstmt.setInt(2, year);
			
			return pstmt.executeUpdate(); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public String getFinalMonth()
	{
		try
		{
			String sql = "select * from final_month order by id desc limit 1";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				return rs.getInt("month")+"-"+rs.getInt("year");
			}
			else
				return null;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public int isFinalized(Integer month,Integer year)
	{
		int res = 0;
		try
		{
			String sql = "select * from final_month where month=? and year=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, month);
			pstmt.setInt(2, year);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				res = 1;
			
			return res;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return res;
		}
	}
	
	public int insertFinalQuarter(Integer quarter,Integer year)
	{
		try
		{
			String sql = "insert into final_quarter(quarter_no,quarter_year) values(?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, quarter);
			pstmt.setInt(2, year);
			
			return pstmt.executeUpdate(); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public String getFinalQuarter()
	{
		try
		{
			String sql = "select * from final_quarter order by id desc limit 1";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				return rs.getInt("quarter_no")+"-"+rs.getInt("quarter_year");
			}
			else
				return null;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public int checkDuplicateQuarter(Integer quarter,Integer year)
	{
		try
		{
			String sql = "select * from final_quarter where quarter_no=? and quarter_year=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, quarter);
			pstmt.setInt(2, year);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				return 1;
			else
				return 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 1;
		}
	}
	
	
	public int insertPayment(Integer flatid,Double amt,String paid_date,String type,String type_detail,String payment_info)
	{
		try
		{
			
			String sql = "insert into payment(flatid,amount,paid_date,type,type_detail,payment_info) values(?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, flatid);
			pstmt.setDouble(2, amt);
			pstmt.setString(3, paid_date);
			pstmt.setString(4, type);
			pstmt.setString(5, type_detail);
			pstmt.setString(6, payment_info);
			
			return pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updatePayment(Integer id,String detail,String type,Double p)
	{
		try
		{
			if(!type.equalsIgnoreCase("cheque"))
			{
				String sql = "update payment set type_detail=? where id=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, detail);
				pstmt.setInt(2, id);
				return pstmt.executeUpdate();
			}
			else
			{
				con.setAutoCommit(false);
				String sql = "update payment set type_detail=? where id=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, detail);
				pstmt.setInt(2, id);
				
				String sql1 = "insert into payment_bounce(payment_id,penalty) values(?,?)";
				PreparedStatement pstmt1 = con.prepareStatement(sql1);
				pstmt1.setInt(1, id);
				pstmt1.setDouble(2, p);
				
				int p1 = pstmt.executeUpdate();
				int p2 = pstmt1.executeUpdate();
				
				if(p1 == 1 && p2 == 1)
				{
					con.commit();
					con.setAutoCommit(true);
					return 1;
				}
				else
				{
					con.rollback();
					return 0;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public LinkedList<payment> getPayment()
	{
		LinkedList<payment> ll = new LinkedList<payment>();
		try
		{
			String sql = "select * from payment";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				payment p = new payment(rs.getInt("id"),rs.getInt("flatid"),rs.getDouble("amount"),rs.getString("paid_date"),rs.getString("type"),rs.getString("type_detail"),rs.getString("payment_info"));
				ll.addLast(p);
			}
			
			if(ll.isEmpty())
				return null;
			else
				return ll;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public int insertPaymentBalance(Integer p_id,Double penalty,String desc)
	{
		try
		{
			String sql = "insert into payment_balance(payment_id,penalty,desc) values(?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, p_id);
			pstmt.setDouble(2, penalty);
			pstmt.setString(3, desc);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updatePaymentBalance(Integer id,Double penalty,String desc)
	{
		try
		{
			String sql = "update payment_bounce set penalty=? and desc=? where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setDouble(1,penalty);
			pstmt.setString(2,desc);
			pstmt.setInt(3,id);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int deletePaymentBalance(Integer id)
	{
		try
		{
			String sql = "delete from payment_bounce where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public LinkedList<payment_bounce> getPaymentBounce()
	{
		LinkedList<payment_bounce> ll = new LinkedList<payment_bounce>();
		try
		{
			String sql = "select * from payment_bounce";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				ll.addLast(new payment_bounce(rs.getInt("id"),rs.getInt("payment_id"),rs.getDouble("penalty"),rs.getString("desc")));
			}
			
			if(ll.isEmpty())
				return null;
			else
				return ll;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 ****************************
	 *	MAINTENANCE DETAILS		*		
	 *	SECTION ENDS 			*
	 ****************************
	*/	
	
//==================================================================================================================	
	
	
	/*
	 ****************************
	 *	ADVANCE PAYMENT DETAILS *		
	 *	SECTION STARTS 			*
	 ****************************
	*/	
	
	public int payAdvance(Integer flatid,Double amt,String paid_date,String type,String type_detail,String payment_info)
	{
		int res = 0;
		
		try
		{
			con.setAutoCommit(false);
			
			maintenance_charge_model m_model = getMaintenanceCharge();
			sinking_charge_model s_model = getSinkingCharge();
			occupancy_charge_model o_model = getOccupancyCharge();
			advance_discount_model d_model = getAdvanceDiscount();
			FlatModel f = getFlat(flatid);
			
			if(m_model == null || s_model == null || o_model == null || d_model == null || f == null)
			{
				con.rollback();
				return res;
			}
			
			Double m_charge = m_model.getCharge();
			Double s_charge = (s_model.getCharge() * Integer.parseInt(f.getArea()) * s_model.getPercentage())/1200;
			Double o_charge = 0.0;
			
			if(f.getOwnerCategory().equalsIgnoreCase("tenant"))
				o_charge = (o_model.getPercentage() * m_charge)/100;
			
			Double one_month_advance = m_charge + s_charge + o_charge;
			Double one_year_advance = one_month_advance * 12;
			Double Discount = (d_model.getPercentage() * one_year_advance)/100;
			
			Integer p_id = 0;
			Double insertedamt = 0.0;
			Double bal = getPreviousBalance(flatid);
			
			if(bal == -1.0)
			{
				con.rollback();
				return res;
			}
			
			System.out.println(bal);
			System.out.println(one_month_advance);
			
			if(bal > one_month_advance)
			{
				res = -1;
			}
			else
			{
				/*System.out.println(amt);
				System.out.println(one_year_advance);
				System.out.println(Discount);
				System.out.println(one_year_advance - Discount);*/
				if(amt == (Math.ceil(one_year_advance) - Math.ceil(Discount)))
				{
					if(insertPayment(flatid, amt, paid_date, type, type_detail, payment_info) == 0)
					{
						con.rollback();
						return res;
					}
					LinkedList<payment> lp = getPayment();
					
					if(lp == null)
					{
						con.rollback();
						return res;
					}
					else
					{
						p_id = lp.removeLast().getId();
					}
					
					if(insertAdvancePaymentInfo(flatid, p_id, Discount, 0) == 0)
					{
						con.rollback();
						return res;
					}
					insertedamt = amt+Discount;
				}
				else if(amt < (Math.ceil(one_year_advance) - Math.ceil(Discount)))
				{
					if(insertPayment(flatid, amt, paid_date, type, type_detail, payment_info) == 0)
					{
						con.rollback();
						return res;
					}
					LinkedList<payment> lp = getPayment();
					
					if(lp == null)
					{
						con.rollback();
						return res;
					}
					else
					{
						p_id = lp.removeLast().getId();
					}
					
					if(insertAdvancePaymentInfo(flatid, p_id, 0.0, 0) == 0)
					{
						con.rollback();
						return res;
					}
					insertedamt = amt;
				}
				
				if(bal != 0 && insertedamt >= bal)
				{
					//clear maintenance for a month
					int c = clearAdvanceMaintenance(flatid,paid_date,p_id,bal);
					//inserted the deducted advance 
					int u = updateAdvancePayment(flatid,insertedamt-bal,"add");
					int ub = updateBalancePayment(flatid, bal, "subtract");
					if(c == 0 || u == 0 || ub == 0)
					{
						con.rollback();
						return res;
					}
					else
					{
						res = 1;
					}
				}
				else
				{
					int u = updateAdvancePayment(flatid,insertedamt,"add");
					
					if(u == 0)
					{
						con.rollback();
						return res;
					}
					else
					{
						res = 1;
					}
				}
			}
			
			con.commit();
			con.setAutoCommit(true);
			return res;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public Double getAdvancePaymentPDF(Integer flatid,Integer month,Integer year)
	{
		Double adv = 0.0;
		maintenance_model m = getMaintenance(flatid, month, year);
		Double ch = m.getMaint_charge()+m.getOccp_charge()+m.getSink_charge();
		
		adv = getAdvancePayment(flatid);
		
		//if(adv)
		
		return adv;
	}
	
	public int clearAdvanceMaintenance(Integer flatid,String date,Integer p_id,Double bal)
	{
		int res = 0;
		
		try
		{
			String d[] = date.split("/");
			Integer month = Integer.parseInt(d[1]);
			Integer year = Integer.parseInt(d[2]);
			
			String sql = "select * from maintenance where flatid=? and month=? and year=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, flatid);
			pstmt.setInt(2, month);
			pstmt.setInt(3, year);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				if(rs.getDouble("how_much_paid") > 0)
				{
					//update payment_id,status to 1,hmp to hmp+bal
					String sql1 = "update maintenance set status=1,payment_id=?,how_much_paid=? where flatid=?";
					PreparedStatement ps1 = con.prepareStatement(sql1);
					ps1.setInt(1, p_id);
					ps1.setDouble(2, bal);
					ps1.setInt(3, flatid);
					
					res = ps1.executeUpdate();
				}
				else
				{
					//update payment_id,status to 1
					String sql1 = "update maintenance set status=1,payment_id=? where flatid=?";
					PreparedStatement ps1 = con.prepareStatement(sql1);
					ps1.setInt(1, p_id);
					ps1.setInt(2, flatid);
					
					res = ps1.executeUpdate();
				}
			}
			return res;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	
	
	/*
	 ****************************
	 *	ADVANCE PAYMENT DETAILS *		
	 *	SECTION ENDS 			*
	 ****************************
	*/	
	
//===================================================================================================================	
	
	
	/*
	 ****************************
	 *	BALANCE PAYMENT DETAILS *		
	 *	SECTION STARTS 			*
	 ****************************
	*/	
	
	public int payBalance(Integer flatid,Double amt,String paid_date,String type,String type_detail,String payment_info)
	{
		int res = 0;
		
		try
		{
			//for the given flatid i have given amt amount
			con.setAutoCommit(false);
			Double bal = getPreviousBalance(flatid);
			int p_id=0;
			
			if(bal > 0)
			{
				if(amt <= bal)
				{
					int u = updateBalancePayment(flatid, amt, "subtract");
					int i = insertPayment(flatid, amt, paid_date, type, type_detail, payment_info);
					
					if(u > 0 && i > 0)
					{
						LinkedList<payment> lp = getPayment();
						
						if(lp!=null)
						{
							p_id = lp.getLast().getId();
							String info = "";
							
							String str[] = clearMaintenance(flatid,amt,p_id);//return should be info string amt remains
							
							if(str!=null)
							{
								info += str[0];
								
								if(Double.parseDouble(str[1]) > 0.0)
								{
									String str1[] = clearInterest(flatid,amt,p_id);
									
									if(str1!=null)
									{
										info += str1[0];
									}
									else
									{
										con.rollback();
										return res;
									}
								}
								
									
								int i1 = insertBalancePaymentInfo(flatid,p_id,0,0);
								balance_payment_info bpi = getLastIDBalancePaymentInfo(flatid);
								int i2 = insertBalanceInfo(bpi.getId(),info);
								balance_info bi = getLastIDBalanceInfo(bpi.getId());
								
								if(i1 > 0 && i2 > 0 && bpi!=null && bi!=null)
								{
									if(updateBalancePaymentInfo(bpi.getId(),bpi.getPayment_id(),bi.getId(),bpi.getReceipt()) == 0)
									{
										con.rollback();
										return res;
									}
										
								}
								else
								{
									con.rollback();
									return res;
								}
								res = 1;
							}
							else
							{
								con.rollback();
								return res;
							}
							
						}
						else
						{
							con.rollback();
							return res;
						}	
					}
					else
					{
						con.rollback();
						return res;
					}
				}
				
				con.commit();
				con.setAutoCommit(true);
				return res;
			}
			else
			{
				con.rollback();
				return res;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public balance_info getLastIDBalanceInfo(Integer bid)
	{
		try
		{
			String sql = "select * from balance_info where bid=? order by id desc limit 1";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bid);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				return new balance_info(rs.getInt("id"),rs.getInt("bid"),rs.getString("info"));
			}
			else
				return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public balance_payment_info getLastIDBalancePaymentInfo(Integer flatid)
	{
		//int res = 0;
		try
		{
			String sql = "select * from balance_payment_info where flatid=? order by id desc limit 1";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, flatid);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				return new balance_payment_info(rs.getInt("id"),rs.getInt("flatid"),rs.getInt("payment_id"),rs.getInt("info_id"),rs.getInt("receipt"));
			}
			else
				return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	public int insertBalancePaymentInfo(Integer flatid,Integer pay_id,Integer info_id,Integer rec)
	{
		try
		{
			String sql = "insert into balance_payment_info(flatid,payment_id,info_id,receipt) values(?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, flatid);
			pstmt.setInt(2, pay_id);
			pstmt.setInt(3, info_id);
			pstmt.setInt(4, rec);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public LinkedList<balance_payment_info> getAllBalancePaymentInfo(Integer flatid)
	{
		LinkedList<balance_payment_info> ll = new LinkedList<balance_payment_info>();
		
		try
		{
			String sql = "select * from balance_payment_info where flatid=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, flatid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				ll.addLast(new balance_payment_info(rs.getInt("id"),rs.getInt("flatid"),rs.getInt("payment_id"),rs.getInt("info_id"),rs.getInt("receipt")));
			}
			
			if(ll.isEmpty())
				return null;
			else
				return ll;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	public int insertBalanceInfo(Integer bid,String info)
	{
		try
		{
			String sql = "insert into balance_info(bid,info) values(?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bid);
			pstmt.setString(2, info);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateBalancePaymentInfo(Integer id,Integer pay_id,Integer info_id,Integer rec)
	{
		try
		{
			String sql = "update balance_payment_info set payment_id=?,info_id=?,receipt=? where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, pay_id);
			pstmt.setInt(2, info_id);
			pstmt.setInt(3, rec);
			pstmt.setInt(4, id);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public String[] clearInterest(Integer flatid,Double amt,Integer p_id)
	{
		try
		{
			//get all the unpaid interest
			String sql = "select * from balance_interest where flatid=? and status=0";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, flatid);
			ResultSet rs = pstmt.executeQuery();
			//and pay it one by one until all the amt is reduced to zero
			String s = "";
			while(rs.next())
			{
				if(amt > 0)
				{
					Double tot_amt = rs.getDouble("interest")-rs.getDouble("how_much_paid");
					if(amt >= tot_amt)
					{
						Integer data = clearIanciellary(rs.getInt("id"),amt,p_id,"more");
						if(data > 0)
						{
							s += "\n Cleared Interest amounting Rs. "+ rs.getDouble("interest")+" dated: "+rs.getInt("month")+"/"+rs.getInt("year");
							amt = amt - tot_amt;
						}
					}
					else
					{
						Integer data = clearIanciellary(rs.getInt("id"),amt,p_id,"more");
						if(data > 0)
						{
							s += "\n Cleared partial Interest amounting Rs. "+ amt+" dated: "+rs.getInt("month")+"/"+rs.getInt("year");
							amt = 0.0;
						}
					}
				}
			}
			
			String str[] = new String[2];
			str[0] = s;
			str[1] = amt.toString();
			
			return str;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public int clearIanciellary(Integer id,Double amt,Integer p_id,String q)
	{
		try
		{
			if(q.equals("more"))
			{
				String sql = "update balance_interest set status=1,payment_id=? where id=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, p_id);
				pstmt.setInt(2, id);
				
				return pstmt.executeUpdate();
			}
			else
			{
				String sql = "update balance_interest set how_much_paid = how_much_paid + ?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setDouble(1, amt);
				return pstmt.executeUpdate();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public String[] clearMaintenance(Integer flatid,Double amt,Integer p_id)
	{
		//get all the maintenance where status is 0 in ascending order of there ID
		try
		{
			String sql = "select * from maintenance where flatid=? and status=0 order by id asc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, flatid);
			
			ResultSet rs = pstmt.executeQuery();
			LinkedList<maintenance_model> ll = new LinkedList<maintenance_model>();
			
			while(rs.next())
			{
				maintenance_model m = new maintenance_model(rs.getInt("id"),rs.getInt("flatid"),rs.getInt("month"),
						rs.getInt("year"),rs.getDouble("maint_charge"),rs.getDouble("sink_charge"),rs.getDouble("occp_charge"),
						rs.getDouble("prev_balance"),rs.getDouble("adv_payment"),rs.getInt("payment_id"),rs.getInt("status"),
						rs.getDouble("how_much_paid"),rs.getInt("mail"),rs.getInt("receipt"));
				ll.addLast(m);
			}
			
			//remove first and check whether it is finalized or not
			
			String s = "";
			while(!ll.isEmpty())
			{
				if(amt > 0)
				{
					maintenance_model m = ll.removeFirst();
					
					//checking whether it is finalized or not
					if(isFinalized(m.getMonth(), m.getYear()) == 1)
					{
						//clear it
						Double tot_amt = m.getMaint_charge()+m.getOccp_charge()+m.getSink_charge() - m.getHow_much_paid();
						if(amt >= tot_amt)
						{
							Integer data = clearManciellary(m.getId(),amt,p_id,"more");
							if(data > 0)
							{
								s += "\nPaid Maintenance for "+m.getMonth()+"/"+m.getYear()+" amounting: Rs. "+amt;
								amt = amt - tot_amt;
							}
							System.out.println("iF >");
						}
						else
						{
							Integer data = clearManciellary(m.getId(),amt,p_id,"less");
							if(data > 0)
							{
								s += "\nPaid Partial Maintenance for "+m.getMonth()+"/"+m.getYear()+" amounting: Rs. "+amt;
								amt = 0.0;
							}
							System.out.println("iF <");
						}
						
					}
					else
					{
						Double tot_amt = m.getMaint_charge()+m.getOccp_charge()+m.getSink_charge() - m.getHow_much_paid();
						if(amt >= tot_amt)
						{
							Integer data = clearManciellary(m.getId(),amt,p_id,"more");
							
							if(data > 0)
							{
								s += "\nPaid Maintenance for "+m.getMonth()+"/"+m.getYear()+" amounting: Rs. "+amt;
								amt = amt - tot_amt;
								Iterator<maintenance_model> itr = ll.iterator();
								while(itr.hasNext())
								{
									maintenance_model m1 = itr.next();
									
									updateMaintenanceBalance(m1.getId(),tot_amt);
								}
							}
							
						}
						else
						{
							Integer data = clearManciellary(m.getId(),amt,p_id,"less");
							
							if(data > 0)
							{
								s += "\nPaid Partial Maintenance for "+m.getMonth()+"/"+m.getYear()+" amounting: Rs. "+amt;
								//amt = 0.0;
								Iterator<maintenance_model> itr = ll.iterator();
								while(itr.hasNext())
								{
									maintenance_model m1 = itr.next();
									updateMaintenanceBalance(m1.getId(),amt);
								}
								amt = 0.0;
							}
							
						}
					}
				}
				else
				{
					break;
				}
			}
			
			String str[] = new String[2];
			str[0] = s;
			str[1] = amt.toString();
			
			return str;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public int clearManciellary(Integer id,Double amt,Integer p_id,String q)
	{
		try
		{
			if(q.equals("more"))
			{
				String sql = "update maintenance set status=1,payment_id=? where id=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, p_id);
				pstmt.setInt(2, id);
				return pstmt.executeUpdate();
			}
			else
			{
				String sql = "update maintenance set how_much_paid = how_much_paid + ? where id=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setDouble(1, amt);
				pstmt.setInt(2, id);
				return pstmt.executeUpdate();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateMaintenanceBalance(Integer id,Double bal)
	{
		try
		{
			String sql = "update maintenance set prev_balance = prev_balance-? where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setDouble(1, bal);
			pstmt.setInt(2, id);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	
	/*
	 ****************************
	 *	BALANCE PAYMENT DETAILS *		
	 *	SECTION ENDS 			*
	 ****************************
	*/	
	
//===================================================================================================================	
	
	
//===================================================================================================================
	
	/*
	 ****************************
	 *	PAYMENT INFO DETAILS *		
	 *	SECTION STARTS 			*
	 ****************************
	*/
	
	public LinkedList<payment> getPaymentInfo(String type,String number,String date)
	{
		String sql = "";
		LinkedList<payment> ll = new LinkedList<payment>();
		
		try
		{
			if(date.equals(""))
			{
				PreparedStatement pstmt = null;
				if(type.equalsIgnoreCase("all"))
				{
					sql = "select * from payment where type_detail like '%"+number+"%'";
					pstmt = con.prepareStatement(sql);
					
					//pstmt.setString(1, type.toUpperCase());
					//pstmt.setString(2, number);
				}
				else
				{
					sql = "select * from payment where type=? type_detail like '%"+number+"%'";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, type.toUpperCase());
					//pstmt.setString(2, number);
				}
				
				
				
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next())
				{
					payment p = new payment(rs.getInt("id"),rs.getInt("flatid"),rs.getDouble("amount"),rs.getString("paid_date"),rs.getString("type"),rs.getString("type_detail"),rs.getString("payment_info"));
					ll.addLast(p);
				}
			}
			else
			{
				PreparedStatement pstmt = null;
				if(type.equalsIgnoreCase("all"))
				{
					sql = "select * from payment where paid_date=?";
					pstmt = con.prepareStatement(sql);
					
					//pstmt.setString(1, type.toUpperCase());
					pstmt.setString(1, date);
				}
				else
				{
					sql = "select * from payment where type=? and paid_date=?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, type.toUpperCase());
					pstmt.setString(2, date);
				}
				
				
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next())
				{
					payment p = new payment(rs.getInt("id"),rs.getInt("flatid"),rs.getDouble("amount"),rs.getString("paid_date"),rs.getString("type"),rs.getString("type_detail"),rs.getString("payment_info"));
					ll.addLast(p);
				}
			}
			
			if(ll.isEmpty())
				return null;
			else
				return ll;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	/*
	 ****************************
	 *	PAYMENT INFO DETAILS *		
	 *	SECTION ENDS 			*
	 ****************************
	*/
	
	
//===================================================================================================================
	
//===================================================================================================================
	/*
	 ****************************
	 *	PETTY CASH INFO DETAILS *		
	 *	SECTION STARTS 			*
	 ****************************
	*/
	
	public double getPettyBalance()
	{
		try
		{
			String sql = "select balance from petty_balance";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				return rs.getDouble("balance");
			}
			else
			{
				return 0.0;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1.0;
		}
	}
	
	public int insertPettyBalance(Double b,String d,String pd)
	{
		try
		{
			con.setAutoCommit(false);
			String sql = "update petty_balance set balance = balance + ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setDouble(1, b);
			
			String sql1 = "insert petty_cash(cash_added,adding_date,des) values(?,?,?)";
			PreparedStatement pstmt1 = con.prepareStatement(sql1);
			
			pstmt1.setDouble(1, b);
			pstmt1.setString(2, d);
			pstmt1.setString(3, pd);
			
			if(pstmt.executeUpdate() > 0 && pstmt1.executeUpdate() > 0)
			{
				con.commit();
				con.setAutoCommit(true);
				return 1;
			}
			else
			{
				con.rollback();
				return 0;
			}
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
			return 0;
		}
	}
	
	public int addPettyCash(Double b,String date,String pd)
	{
		try
		{
			String sql = "insert into petty_cash(cash_added,adding_date,des) values(?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setDouble(1, b);
			pstmt.setString(2, date);
			pstmt.setString(3, pd);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int insertPettyHeaders(String h)
	{
		try
		{
			String sql = "insert into headers(header) values(?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, h);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public LinkedList<String> getPettyHeaders()
	{
		LinkedList<String> ll = new LinkedList<String>();
		try
		{
			String sql = "select * from headers";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				ll.addLast(rs.getString("header"));
			}
			
			if(ll.isEmpty())
				return null;
			else
				return ll;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public int getPettyHeaderByID(String header)
	{
		try
		{
			String sql = "select * from headers where header=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, header);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				return rs.getInt("id");
			else 
				return 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int spendPettyCash(Double amt,String date,Integer hid,String desc)
	{
		try
		{
			con.setAutoCommit(false);
			String sql = "update petty_balance set balance = balance - ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setDouble(1, amt);
			
			
			
			String sql1 = "insert into petty_info(description,paid_amount,paid_date,header_id) values(?,?,?,?)";
			PreparedStatement pstmt1 = con.prepareStatement(sql1);
			
			pstmt1.setString(1, desc);
			pstmt1.setDouble(2, amt);
			pstmt1.setString(3, date);
			pstmt1.setInt(4, hid);
			
			if(pstmt.executeUpdate() > 0 && pstmt1.executeUpdate() >0)
			{
				con.commit();
				con.setAutoCommit(true);
				return 1;
			}
			else
			{
				con.rollback();
				return 0;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public LinkedList<String> getPettyCash()
	{
		LinkedList<String> ll = new LinkedList<String>();
		try
		{
			String sql = "select * from petty_cash";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				String s = rs.getString("cash_added")+","+rs.getString("adding_date")+","+rs.getString("des");
				ll.addLast(s);
			}
			
			if(ll.isEmpty())
				return null;
			else
				return ll;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public String getPettyHeaderByName(Integer id)
	{
		try
		{
			String sql = "select * from headers where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				return rs.getString("header");
			}
			else
				return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public LinkedList<petty_info> getPettyInfo()
	{
		LinkedList<petty_info> ll = new LinkedList<petty_info>();
		try
		{
			String sql = "select * from petty_info";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				String header = getPettyHeaderByName(rs.getInt("header_id"));
				petty_info p = new petty_info(rs.getInt("id"),rs.getString("description"),rs.getDouble("paid_amount"),rs.getString("paid_date"),header);
				ll.addLast(p);
			}
			
			if(ll.isEmpty())
				return null;
			else
				return ll;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	public petty_info getPettyInfoByID(Integer id)
	{
		try
		{
			String sql = "select * from petty_info where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				String h = getPettyHeaderByName(rs.getInt("header_id"));
				return new petty_info(rs.getInt("id"),rs.getString("description"), rs.getDouble("paid_amount"),rs.getString("paid_date"), h);
			}
			else
				return null;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public int editPettyInfo(Integer id,String todo,Double val,Double uamt,String spendheader,String pettydesc,String pettydate)
	{
		try
		{
			Integer hid = database.getDatabase().getPettyHeaderByID(spendheader);
			if(todo.equals(""))
			{
				
				String sql = "update petty_info set description=?,paid_amount=?,paid_date=?,header_id=? where id=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, pettydesc);
				pstmt.setDouble(2, uamt);
				pstmt.setString(3, pettydate);
				pstmt.setInt(4, hid);
				pstmt.setInt(5, id);
				
				return pstmt.executeUpdate();
			}
			else
			{
				if(todo.equalsIgnoreCase("add"))
				{
					con.setAutoCommit(false);
					String sql = "update petty_balance set balance = balance + ?";
					String sql1 = "update petty_info set description=?,paid_amount=?,paid_date=?,header_id=? where id=?";
					
					PreparedStatement pstmt = con.prepareStatement(sql);
					PreparedStatement pstmt1 = con.prepareStatement(sql1);
					
					pstmt.setDouble(1, val);
					
					pstmt1.setString(1, pettydesc);
					pstmt1.setDouble(2, uamt);
					pstmt1.setString(3, pettydate);
					pstmt1.setInt(4, hid);
					pstmt1.setInt(5, id);
					
					int u1 = pstmt.executeUpdate();
					int u2 = pstmt1.executeUpdate();
					
					if(u1 > 0 && u2 > 0)
					{
						con.commit();
						con.setAutoCommit(true);
						return u1;
					}
					else
					{
						con.rollback();
						return 0;
					}
				}
				else
				{
					con.setAutoCommit(false);
					String sql = "update petty_balance set balance = balance - ?";
					String sql1 = "update petty_info set description=?,paid_amount=?,paid_date=?,header_id=? where id=?";
					
					PreparedStatement pstmt = con.prepareStatement(sql);
					PreparedStatement pstmt1 = con.prepareStatement(sql1);
					
					pstmt.setDouble(1, val);
					
					pstmt1.setString(1, pettydesc);
					pstmt1.setDouble(2, uamt);
					pstmt1.setString(3, pettydate);
					pstmt1.setInt(4, hid);
					pstmt1.setInt(5, id);
					
					int u1 = pstmt.executeUpdate();
					int u2 = pstmt1.executeUpdate();
					
					if(u1 > 0 && u2 > 0)
					{
						con.commit();
						con.setAutoCommit(true);
						return u1;
					}
					else
					{
						con.rollback();
						return 0;
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	/*
	 ****************************
	 *	PETTY CASH INFO DETAILS *		
	 *	SECTION ENDS 			*
	 ****************************
	*/
//===================================================================================================================
	
	
//===================================================================================================================	
	/*
	 ****************************
	 *	EXPENDITURE INFO DETAILS*		
	 *	SECTION STARTS 			*
	 ****************************
	*/
	
	public int insertExpenditure(String desc,int id)
	{
		try
		{
			String sql = "insert into expenditure(des,pay_id,header_id) values(?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, desc);
			pstmt.setInt(2, 0);
			pstmt.setInt(3, id);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int insertExpenditurePayment(Double amt,String date,String type,String detail)
	{
		try
		{
			String sql = "insert into exp_payment(exp_id,amt,date,type,type_detail) values(?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, 0);
			pstmt.setDouble(2, amt);
			pstmt.setString(3, date);
			pstmt.setString(4, type);
			pstmt.setString(5, detail);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int getLastExpenditureID()
	{
		try
		{
			String sql = "select * from expenditure order by id desc limit 1";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				return rs.getInt("id");
			}
			else
			{
				return 0;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int getLastExpenditurePaymentID()
	{
		try
		{
			String sql = "select * from exp_payment order by id desc limit 1";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				return rs.getInt("id");
			}
			else
			{
				return 0;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateExpenditure(Integer id,String desc,Integer pay_id)
	{
		try
		{
			String sql = "update expenditure set des=?,pay_id=? where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, desc);
			pstmt.setInt(2, pay_id);
			pstmt.setInt(3, id);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateExpenditurePayment(Integer id,Integer exp_id,Double amt,String date,String type,String detail)
	{
		try
		{
			String sql = "update exp_payment set exp_id=?,amt=?,date=?,type=?,type_detail=? where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, exp_id);
			pstmt.setDouble(2, amt);
			pstmt.setString(3, date);
			pstmt.setString(4, type);
			pstmt.setString(5, detail);
			pstmt.setInt(6, id);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public int addExpenditure(String desc,String type,String type_detail,Double amt,String dop,int id)
	{
		int res = 0;
		int i1 = insertExpenditure(desc,id);
		
		int i2 = 0;
		
		if(i1 > 0)
			i2 = insertExpenditurePayment(amt, dop, type, type_detail);
		
		if(i1 > 0 && i2 > 0)
		{
			int eid = getLastExpenditureID();
			int pid = getLastExpenditurePaymentID();
			if(eid!=0 && pid!=0)
			{
				int u1 = updateExpenditure(eid, desc, pid);
				int u2 = updateExpenditurePayment(pid, eid, amt, dop, type, type_detail);
				
				if(u1 > 0 && u2 > 0)
				{
					res = 1;
				}
			}
		}
		return res;
	}
	
	public ResultSet getExpenditurePayment(Integer id)
	{
		try
		{
			String sql = "select * from exp_payment where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			return rs;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
		
	public LinkedList<expenditure_info> getExpenditureInfo()
	{
		LinkedList<expenditure_info> ll = new LinkedList<expenditure_info>();
		try
		{
			String sql = "select * from expenditure";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				String header = getPettyHeaderByName(rs.getInt("header_id"));
				ResultSet rs1 = getExpenditurePayment(rs.getInt("id"));
				rs1.next();
				ll.addLast(new expenditure_info(rs.getInt("id"), rs.getString("des"), rs1.getDouble("amt"), rs1.getString("date"),  rs1.getString("type"), rs1.getString("type_detail"),header));
			}
			if(ll.isEmpty())
				return null;
			else
				return ll;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	public int updateExpenditureSpecific(Integer pid,Double amt,String type,String detail,String desc,String dop)
	{
		try
		{
			con.setAutoCommit(false);
			String sql = "select pay_id from expenditure where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pid);
			
			ResultSet rs = pstmt.executeQuery();
			
			Integer pid2 = 0;
			
			if(rs.next())
				pid2 = rs.getInt("pay_id");
			
			
			if(pid2!=0)
			{
				String sql1 = "update expenditure set des=? where id=?";
				String sql2 = "update exp_payment set amt=?,date=?,type=?,type_detail=? where id=?";
				
				PreparedStatement p1 = con.prepareStatement(sql1);
				p1.setString(1, desc);
				p1.setInt(2, pid);
				
				PreparedStatement p2 = con.prepareStatement(sql2);
				p2.setDouble(1, amt);
				p2.setString(2, dop);
				p2.setString(3, type);
				p2.setString(4, detail);
				p2.setInt(5, pid2);
				
				
				int u1 = p1.executeUpdate();
				int u2 = p2.executeUpdate();
				
				if(u1 > 0 && u2 > 0)
				{
					con.commit();
					con.setAutoCommit(true);
					return 1;
				}
				else
				{
					con.rollback();
					return 0;
				}
				
			}
			else
				return 0;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	/*
	 ****************************
	 *	EXPENDITURE INFO DETAILS*		
	 *	SECTION ENDS 			*
	 ****************************
	*/
	
//===================================================================================================================
	
	
	public int addIncome(String desc,String type,String type_detail,Double amt,String dop,int id)
	{
		try
		{
			System.out.println(dop);
			String sql = "insert into income(amount,type,type_detail,des,dop,header_id) values(?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setDouble(1, amt);
			pstmt.setString(2, type);
			pstmt.setString(3, type_detail);
			pstmt.setString(4, desc);
			pstmt.setString(5, dop);
			pstmt.setInt(6, id);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateIncome(Integer id,Double amount,String type,String type_detail,String desc,String dop)
	{
		try
		{
			String sql = "update income set amount=?,type=?,type_detail=?,des=?,dop=? where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, amount);
			pstmt.setString(2, type);
			pstmt.setString(3, type_detail);
			pstmt.setString(4, desc);
			pstmt.setString(5, dop);
			pstmt.setInt(6, id);
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public LinkedList<income> getIncome()
	{
		LinkedList<income> ll = new LinkedList<income>();
		try
		{
			String sql = "select * from income";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				String header = getPettyHeaderByName(rs.getInt("header_id"));
				ll.addLast(new income(rs.getInt("id"),rs.getDouble("amount"),rs.getString("type"),rs.getString("type_detail"),rs.getString("des"),rs.getString("dop"),header));
			}			
			
			if(ll.isEmpty())
				return null;
			else
				return ll;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
