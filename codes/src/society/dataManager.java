package society;

import java.util.Iterator;
import java.util.LinkedList;

public class dataManager {
	
	public specificflat getSpecificFlatDetails(String flatno)
	{
		if(flatno.length() <= 6)
			return new specificflat(flatno);
		else
			return null;
	}
	
	public int updateFlat(String id,String name,String area,String email,String mobile,String cat)
	{
		try
		{
			Integer pid = Integer.parseInt(id);
			
			return database.getDatabase().updateFlatByID(pid,name,area,email,mobile,cat);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public FlatModel getFlatByID(String id)
	{
		try
		{
			Integer pid = Integer.parseInt(id);
			FlatModel f = database.getDatabase().getFlat(pid);
			
			return f;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public int insertCoowner(String flatid,String name,String mail,String mob)
	{
		int res = 0;
		try
		{
			Integer flat = Integer.parseInt(flatid);
			FlatModel f = database.getDatabase().getFlat(flat);
			
			if(f!=null)
			{
				if(mail.equals(""))
					mail = "none";
				
				if(mob.equals(""))
					mob = "0";
				res = database.getDatabase().insertCoowner(flat, name, mail, mob);
			}
			
			return res;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int deleteCoowner(String id)
	{
		try
		{
			Integer pid = Integer.parseInt(id);
			return database.getDatabase().deleteCoowner(pid);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateCoowner(String id,String cname,String cmail,String cmobile)
	{
		try
		{
			Integer pid = Integer.parseInt(id);
			return database.getDatabase().updateCoowner(pid, cname, cmail, cmobile);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public LinkedList<sortall> getAllFlatsDetail()
	{
		
		LinkedList<FlatModel> ll = database.getDatabase().getAllFlats();
		LinkedList<sortall> ll1 = new LinkedList<sortall>();
		
		if(ll!=null)
		{
			while(!ll.isEmpty())
			{
				FlatModel f = ll.removeFirst();
			
				sortall all = new sortall(f.getFlatNo(),f.getOwnerName(),f.getArea(),f.getOwnerCategory(),f.getEmail(),f.getMobile());
			
				ll1.addLast(all);
			}
		}
		
		if(ll1.isEmpty())
			return null;
		else
			return ll1;
	}
	
	public LinkedList<sortowners> getOwnerFlatsDetail()
	{
		LinkedList<FlatModel> ll = database.getDatabase().getAllFlats();
		LinkedList<sortowners> ll1 = new LinkedList<sortowners>();
		
		if(ll!=null)
		{
			while(!ll.isEmpty())
			{
				FlatModel f = ll.removeFirst();
				
				if(f.getOwnerCategory().equalsIgnoreCase("owner"))
				{
					sortowners so = new sortowners(f.getFlatNo(),f.getOwnerName(),f.getArea(),f.getEmail(),f.getMobile());
					
					ll1.addLast(so);
				}
			}
		}
		
		if(ll1.isEmpty())
			return null;
		else
			return ll1;
	}
	
	public LinkedList<sorttenant> getTenantFlatsDetail()
	{
		LinkedList<FlatModel> ll = database.getDatabase().getAllFlats();
		LinkedList<sorttenant> ll1 = new LinkedList<sorttenant>();
		
		
		
		if(ll!=null)
		{
			while(!ll.isEmpty())
			{
				FlatModel f = ll.removeFirst();
			
				if(f.getOwnerCategory().equalsIgnoreCase("tenant"))
				{
					LinkedList<TenantModel> llt = database.getDatabase().getTenant(f.getFlatID());
					
					if(llt!=null)
					{
						while(!llt.isEmpty())
						{
							TenantModel tm = llt.removeFirst();
							if(tm.getActive() == 1)
							{
								sorttenant st = new sorttenant(f.getFlatNo(),tm.getTenant_name(),f.getArea(),tm.getEmail(),tm.getMobile());
								
								ll1.addLast(st);
							}
						}
					}
				}	
			}
		}
		
		if(ll1.isEmpty())
			return null;
		else
			return ll1;
	}
	
	public LinkedList<sortcoowner> getCoownerFlatsDetail()
	{
		LinkedList<FlatModel> ll = database.getDatabase().getAllFlats();
		LinkedList<String> cn = new LinkedList<String>();
		LinkedList<String> ce = new LinkedList<String>();
		LinkedList<String> cm = new LinkedList<String>();
		LinkedList<sortcoowner> scoll = new LinkedList<sortcoowner>();
		
		if(ll!=null)
		{
			while(!ll.isEmpty())
			{
				FlatModel f = ll.removeFirst();
			
				LinkedList<CoownerModel> cmd = database.getDatabase().getCoowner(f.getFlatID());
			
				if(cmd!=null)
				{
					while(!cmd.isEmpty())
					{
						CoownerModel cm1 = cmd.removeFirst();
						cn.addLast(cm1.getCoowner_name());
						ce.addLast(cm1.getEmail());
						cm.addLast(cm1.getMobile());
					}
					sortcoowner sco = new sortcoowner(f.getFlatNo(),cn,f.getArea(),ce,cm);
			
					scoll.addLast(sco);
				}
			}
		}
		
		if(scoll.isEmpty())
			return null;
		else
			return scoll;
	}
	
	
	public int createMaintenance(Integer month,Integer year)
	{
		int res = -1;
		int first = 0;
		int duplicate  = 0;
		
		if(month!=null || year!=null)
		{
			LinkedList<maintenance_model> ll = new LinkedList<maintenance_model>();
			
			if(database.getDatabase().checkFirstMaintenanceEntry() == 1)
			{
				 ll = database.getDatabase().getMaintenanceReport(month, 0, year, "month");
				 if(ll!=null)
					 duplicate = 1;
			}
			else
				first = 1;
			
			Integer settedQuarter = 0;
			
			if(month >= 1 && month <= 3)
				settedQuarter = 1;
			else if(month >= 4 && month <= 6)
				settedQuarter = 2;
			else if(month >= 7 && month <= 9)
				settedQuarter = 3;
			else if(month >= 10 && month <= 12)
				settedQuarter = 4;
			
			
			
			//For the given month
			String date = database.getDatabase().getFinalMonth();
			Integer month1 = 0;
			Integer year1 = 0;
			Integer quarter = 0;
			Integer year2 = 0;
			Integer testyear = 0;
			
			if(date != null)
			{
				String []data = date.split("-");
				month1 = Integer.parseInt(data[0]);
				year1 = Integer.parseInt(data[1]);
				if(month1==12)
					testyear = year1+1;
				else
					testyear = year1;
				
				String date1 = database.getDatabase().getFinalQuarter();
				
				
				if(date1 != null)
				{
					String []data1 = date1.split("-");
					quarter = Integer.parseInt(data1[0]);
					year2 = Integer.parseInt(data1[1]);
				}
			}
			
			
			if(duplicate == 1)
			{
				res = -1;
			}
			else if(quarter!=settedQuarter && year!=year2)
			{
				res = -1;
			}
			else if(month>=(month1%12)+2 && year!=testyear)
			{
				res = -1;
			}
			else
			{
				LinkedList<maintenance_model> ll1 = database.getDatabase().getAllMaintenance();
				if(ll1!=null)
				{
					if(month == 1 && first == 0)
					{
						Iterator<maintenance_model> itr = ll1.iterator();
						while(itr.hasNext())
						{
							maintenance_model mm1 = itr.next();
							if(mm1.getMonth() == 12 && mm1.getYear().equals(year-1))
							{
								res = 1;
								break;
							}	
						}
					}
					else if(month!=1 && first == 0)
					{
						Iterator<maintenance_model> itr = ll1.iterator();
						while(itr.hasNext())
						{
							maintenance_model mm1 = itr.next();
							if(mm1.getMonth().equals(month-1) && mm1.getYear().equals(year))
							{
								res = 1;
								break;
							}	
						}
					}
				}
				else if(first == 1)
				{
					res = 1;
				}
				
			}
			
			if(res == 1)
			{
				res = database.getDatabase().createMaintenance(month, year);
			}
		}
		
		if(res == -1 || res == 0)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
	
	public int AddMaintenanceCharge(String charge,String month,String year)
	{
		try
		{
			Double ch = Double.parseDouble(charge);
			Integer m = Integer.parseInt(month);
			Integer y = Integer.parseInt(year);
			
			if(database.getDatabase().insertMaintenanceCharge(ch, m, y) == 1)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
		
	}
	
	public maintenance_charge_model[] getMaintenanceCharge()
	{
		LinkedList<maintenance_charge_model> mcm = database.getDatabase().getAllMaintenanceCharge();
		
		if(mcm!=null)
		{
			maintenance_charge_model arr[] = new maintenance_charge_model[mcm.size()];
			
			int cnt = 0;
			
			while(!mcm.isEmpty())
			{
				arr[cnt] = mcm.removeFirst();
				cnt++;
			}
			return arr;
		}
		else
			return null;
	}
	
	public int AddSinkingCharge(String charge,String percentage,String month,String year)
	{
		try
		{
			Double ch = Double.parseDouble(charge);
			Double per = Double.parseDouble(percentage);
			Integer m = Integer.parseInt(month);
			Integer y = Integer.parseInt(year);
			
			if(database.getDatabase().insertSinkingCharge(ch, per, m, y) == 1)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	public sinking_charge_model[] getSinkingCharge()
	{
		LinkedList<sinking_charge_model> mcm = database.getDatabase().getAllSinkingCharge();
		
		if(mcm!=null)
		{
			sinking_charge_model arr[] = new sinking_charge_model[mcm.size()];
			
			int cnt = 0;
			
			while(!mcm.isEmpty())
			{
				arr[cnt] = mcm.removeFirst();
				cnt++;
			}
			return arr;
		}
		else
			return null;
	}
	
	public int AddOccupancyCharge(String percentage,String month,String year)
	{
		try
		{
			Double per = Double.parseDouble(percentage);
			Integer m = Integer.parseInt(month);
			Integer y = Integer.parseInt(year);
			
			if(database.getDatabase().insertOccupancyCharge(per, m, y) == 1)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	public occupancy_charge_model[] getOccupancyCharge()
	{
		LinkedList<occupancy_charge_model> mcm = database.getDatabase().getAllOccupancyCharge();
		
		if(mcm!=null)
		{
			occupancy_charge_model arr[] = new occupancy_charge_model[mcm.size()];
			
			int cnt = 0;
			
			while(!mcm.isEmpty())
			{
				arr[cnt] = mcm.removeFirst();
				cnt++;
			}
			return arr;
		}
		else
			return null;
	}
	
	public int AddInterestPercentage(String percentage,String month,String year)
	{
		try
		{
			Double per = Double.parseDouble(percentage);
			Integer m = Integer.parseInt(month);
			Integer y = Integer.parseInt(year);
			
			if(database.getDatabase().insertInterestPercentage(per, m, y) == 1)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	
	public interest_percentage_model[] getInterestPercentage()
	{
		LinkedList<interest_percentage_model> mcm = database.getDatabase().getAllInterestPercentage();
		
		if(mcm!=null)
		{
			interest_percentage_model arr[] = new interest_percentage_model[mcm.size()];
			
			int cnt = 0;
			
			while(!mcm.isEmpty())
			{
				arr[cnt] = mcm.removeFirst();
				cnt++;
			}
			return arr;
		}
		else
			return null;
	}
	
	public int AddAdvanceDiscount(String percentage,String month,String year)
	{
		try
		{
			Double per = Double.parseDouble(percentage);
			Integer m = Integer.parseInt(month);
			Integer y = Integer.parseInt(year);
			
			if(database.getDatabase().insertAdvanceDiscount(per, m, y) == 1)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	public advance_discount_model[] getAdvanceDiscount()
	{
		LinkedList<advance_discount_model> mcm = database.getDatabase().getAllAdvanceDiscount();
		
		if(mcm!=null)
		{
			advance_discount_model arr[] = new advance_discount_model[mcm.size()];
			
			int cnt = 0;
			
			while(!mcm.isEmpty())
			{
				arr[cnt] = mcm.removeFirst();
				cnt++;
			}
			return arr;
		}
		else
			return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public LinkedList<maintenance_model> getMaintenanceReport(String flatno,Integer month,Integer year,Integer quarter,String block,String users)
	{
		LinkedList<maintenance_model> ll = null;
		LinkedList<maintenance_model> returnll = new LinkedList<maintenance_model>();
		
		if(!flatno.equalsIgnoreCase(""))
		{
			if(flatno.length() > 6)
				return null;
			else
			{
				String type = "";
				if(month == 0 && quarter == 0)
					type = "none";
				else if(month == 0)
					type = "quarter";
				else if(quarter == 0)
					type = "month";
				
				
				
				if(type.equals("quarter"))
				{
					ll = database.getDatabase().getMaintenanceReport(0, quarter, year, type);
				}
				else if(type.equals("month"))
				{
					ll = database.getDatabase().getMaintenanceReport(month, 0, year, type);
				}
				else
				{
					ll = database.getDatabase().getMaintenanceReport(0, 0, year, type);
				}
				
				if(ll!=null)
				{
					while(!ll.isEmpty())
					{
						maintenance_model mm = ll.removeFirst();
						
						FlatModel f = database.getDatabase().getFlat(mm.getFlatid());
						
						if(f.getFlatNo().equalsIgnoreCase(flatno))
						{
							returnll.addLast(mm);
						}
					}
					return returnll;
				}
				else
					return null;
				
			}
			
		}
		else
		{
			String type = "";
			if(month == 0 && quarter == 0)
				type = "none";
			else if(month == 0)
				type = "quarter";
			else if(quarter == 0)
				type = "month";
			
			
			
			if(type.equals("quarter"))
			{
				ll = database.getDatabase().getMaintenanceReport(0, quarter, year, type);
			}
			else if(type.equals("month"))
			{
				ll = database.getDatabase().getMaintenanceReport(month, 0, year, type);
			}
			else
			{
				ll = database.getDatabase().getMaintenanceReport(0, 0, year, type);
			}
			
			if(ll!=null)
			{
				if(block.equals("all") && users.equals("all"))
				{
					return ll;
				}
				else
				{
					//block can be A,B,C.... and users can be Unpaid and Paid
					if(block.equals("all") && users.equals("unpaid"))
					{
					
						while(!ll.isEmpty())
						{
							maintenance_model mm = ll.removeFirst();
								
							if(mm.getStatus() == 0)
							{
								returnll.addLast(mm);
							}		
						}
						
						return returnll;
					}
					else if(block.equals("all") && users.equals("paid"))
					{
						while(!ll.isEmpty())
						{
							maintenance_model mm = ll.removeFirst();
								
							if(mm.getStatus() == 1)
							{
								returnll.addLast(mm);
							}
						}
						
						return returnll;
					}
					else
					{
						if(users.equals("paid"))
						{
							char s = block.toLowerCase().charAt(0);
							System.out.println(s);
							while(!ll.isEmpty())
							{
								maintenance_model mm = ll.removeFirst();
								
								FlatModel f = database.getDatabase().getFlat(mm.getFlatid());
								
								if(f.getFlatNo().toLowerCase().charAt(0) == s && mm.getStatus() == 1)
								{
									returnll.addLast(mm);
								}
								
							}
							
							return returnll;
						}
						else if(users.equals("unpaid"))
						{
							char s = block.toLowerCase().charAt(0);
							
							while(!ll.isEmpty())
							{
								maintenance_model mm = ll.removeFirst();
								FlatModel f = database.getDatabase().getFlat(mm.getFlatid());
								
								
								if(f.getFlatNo().toLowerCase().charAt(0) == s && mm.getStatus() == 0)
								{
									returnll.addLast(mm);
								}
								
							}
							return returnll;
						}
						else if(users.equals("all"))
						{
							char s = block.toLowerCase().charAt(0);
							
							while(!ll.isEmpty())
							{
								maintenance_model mm = ll.removeFirst();
								FlatModel f = database.getDatabase().getFlat(mm.getFlatid());
								
								
								if(f.getFlatNo().toLowerCase().charAt(0) == s)
								{
									returnll.addLast(mm);
								}
								
							}
							return returnll;
						}
					}
				}
			}
			else
				return null;
						
		}
		if(returnll.isEmpty())
			return ll;
		else
			return returnll;
	}
	
	public String getFinalMonth()
	{
		String s = database.getDatabase().getFinalMonth();
		if(s != null)
		{
			String s1[] = s.split("-");
			String rs = String.valueOf(Integer.parseInt(s1[0])+1);
			rs = rs + "-" + s1[1];
			return rs;
		}
		else
			return "Error";
	}
	
	public String getFinalQuarter()
	{
		String s = database.getDatabase().getFinalQuarter();
		if(s != null)
		{
			return s;
		}
		else
			return "Error";
	}
	
	//Check Finalization
	public int finalizeMonthAndQuarter(String quarter,String quarteryear,String month,String monthyear)
	{
		int res = -1;
		
		if(quarter!=null && quarter!=null)
		{
			Integer q = Integer.parseInt(quarter);
			Integer qy = Integer.parseInt(quarteryear);
			
			if(q == 1)
			{
				if(database.getDatabase().isFinalized(12, qy-1) == 1 && database.getDatabase().checkDuplicateQuarter(q, qy) == 0)
				{
					if(database.getDatabase().insertFinalQuarter(1,qy) == 1)
					{
						res = 1;
					}
					else
					{
						res = -1;
					}
					
				}
				else
				{
					res = 0;
				}
			}
			else if(q == 2)
			{
				if(database.getDatabase().isFinalized(3, qy) == 1 && database.getDatabase().checkDuplicateQuarter(q, qy) == 0)
				{
					if(database.getDatabase().insertFinalQuarter(2,qy) == 1)
					{
						res = 1;
					}
					else
						res = -1;
				}
				else
				{
					res = 0;
				}
			}
			else if(q == 3)
			{
				if(database.getDatabase().isFinalized(6, qy) == 1 && database.getDatabase().checkDuplicateQuarter(q, qy) == 0)
				{
					if(database.getDatabase().insertFinalQuarter(3,qy) == 1)
					{
						res = 1;
					}
					else
						res = -1;
				}
				else
				{
					res = 0;
				}
			}
			else if(q == 4)
			{
				if(database.getDatabase().isFinalized(9, qy) == 1 && database.getDatabase().checkDuplicateQuarter(q, qy) == 0)
				{
					if(database.getDatabase().insertFinalQuarter(4,qy) == 1)
					{
						res = 1;
					}
					else
						res = -1;
				}
				else
				{
					res = 0;
				}
			}
		}
		else
		{
			Integer mon = Integer.parseInt(month);
			Integer year = Integer.parseInt(monthyear);
			
			if(mon == 1)
			{
				if(database.getDatabase().isFinalized(12, year-1) == 1 && database.getDatabase().isFinalized(mon, year)==0)
				{
					if(database.getDatabase().insertFinalMonth(mon, year) == 1)
					{
						res = 1;
					}
					else
					{
						res = -1;
					}
				}
				else
				{
					res = 0;
				}
			}
			else
			{
				if(database.getDatabase().isFinalized(mon-1, year) == 1 && database.getDatabase().isFinalized(mon, year)==0)
				{
					if(database.getDatabase().insertFinalMonth(mon, year) == 1)
					{
						res = 1;
					}
					else
					{
						res = -1;
					}
				}
				else
				{
					res = 0;
				}
			}
		}
		return res;
	}
	
	
	//getAdvanceInfo
	public advance_UI_Info getAdvanceInfo(FlatModel f)
	{
		Double aamt = database.getDatabase().getAdvancePayment(f.getFlatID());
		
		if(aamt >= 0)
		{
			maintenance_charge_model mcm = database.getDatabase().getMaintenanceCharge();
			sinking_charge_model scm = database.getDatabase().getSinkingCharge();
			occupancy_charge_model ocm = database.getDatabase().getOccupancyCharge();
			advance_discount_model adm = database.getDatabase().getAdvanceDiscount();
			
			if(mcm!=null && scm!=null && ocm!=null && adm!=null)
			{
				Double maint_charge = mcm.getCharge();
				Double sink_charge = (scm.getCharge() * Double.parseDouble(f.getArea()) * scm.getPercentage())/1200;
				Double occp_charge = 0.0;
				
				if(f.getOwnerCategory().equalsIgnoreCase("tenant"))
					occp_charge = (ocm.getPercentage() * maint_charge)/100;
				
				Double total_amt = (maint_charge + sink_charge + occp_charge)*12;
				total_amt = Math.ceil(total_amt);
				Double disc = (adm.getPercentage() * total_amt)/100;
				disc = Math.ceil(disc);
				Double payable_amt = total_amt - disc;
				
				return new advance_UI_Info(aamt,total_amt,disc,payable_amt);
			}
			else
			{
				System.out.println("h2");
				return null;	
			}
				
		}
		else
		{
			System.out.println("h1");
			return null;
		}
			
		
	}
	
	public int payAdvanceMaintenance(Integer flatid,String amt,String paid_date,String type,String type_detail,String payment_info)
	{
		//validating the Inputs
		try
		{
			Double amount = Double.parseDouble(amt);
			String data[] = paid_date.split("-");
			//System.out.println(paid_date);
			//System.out.println(data.length);
			String newS = "";
			newS += data[2]+"/"+data[1]+"/"+data[0];
			paid_date = newS;
			data[1] = String.valueOf(Integer.parseInt(data[1]));
			String d = database.getDatabase().getFinalMonth();
			String data1[] = d.split("-");

			Integer m = Integer.parseInt(data1[0]);
			Integer y = Integer.parseInt(data1[1]);
			
			if(m!=12)
			{
				m++;
				data1[0] = String.valueOf(m);
			}
			else
			{
				data1[0] = "1";
				y++;
				data1[1] = String.valueOf(y);
			}
			
			Integer result = -2;
			//System.out.println(data1[0]+" "+data[1]+" "+data1[1]+" "+data[0]);
			if(data1[0].equals(data[1]) && data1[1].equals(data[0]))
			{
				result = database.getDatabase().payAdvance(flatid, amount, paid_date, type, type_detail, payment_info);
			}
			
			return result;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public advance_table_info[] advanceHistory(FlatModel f)
	{
		LinkedList<advance_payment_info> api = database.getDatabase().getAllAdvancePaymentInfo(f.getFlatID());
		/*while(!api.isEmpty())
		{
			advance_payment_info pp = api.removeFirst();
			System.out.println(pp.getFlatid()+" "+pp.getDiscount()+" "+pp.getPayment_id());
		}*/
		LinkedList<payment> ll = database.getDatabase().getPayment();
		int res = 0;
		advance_table_info ati[] = null;
		int count = 0;
		if(api!=null && ll!=null)
		{
			ati = new advance_table_info[api.size()];
			while(!api.isEmpty())
			{
				advance_payment_info api1 = api.removeFirst();
				Iterator<payment> itr = ll.iterator();
				payment p1 = null;
				while(itr.hasNext())
				{
					payment p = itr.next();
					if(p.getId().equals(api1.getPayment_id()))
					{
						p1 = p;
						break;
					}
				}
				if(p1!=null)
				{
					String inf = "<b>Type: </b>"+p1.getType()+"<br>"+p1.getType_detail();
					ati[count] = new advance_table_info(api1.getId(),p1.getAmount(),api1.getDiscount(),p1.getPaid_date(),inf,api1.getReceipt());
					count++;
				}
				else
					res = -1;
			}
		}
		else
			res = -1;
		
		if(res == -1)
			return null;
		else
			return ati;	
	}
	
	
	public balance_UI_Info getBalanceInfo(FlatModel f)
	{
		Double bal = database.getDatabase().getPreviousBalance(f.getFlatID());
		bal = Math.ceil(bal);
		LinkedList<maintenance_model> ll = database.getDatabase().getAllMaintenance();
		LinkedList<balance_interest_model> ll1 = database.getDatabase().getInterest(f.getFlatID());
		
		if(bal!=-1.0)
		{
			if(bal == 0)
			{
				return new balance_UI_Info(0.0,"");
			}
			else
			{
				String s = "";
				
				if(ll!=null)
				{
					Iterator<maintenance_model> itr = ll.iterator();
					while(itr.hasNext())
					{
						maintenance_model m = itr.next();
						
						if(m.getStatus() == 0 && m.getFlatid().equals(f.getFlatID()))
						{
							Double p = m.getMaint_charge()+m.getSink_charge()+m.getOccp_charge() - m.getHow_much_paid();
							p = Math.ceil(p);
							s += "Maintenance Due for <b>"+m.getMonth()+"/"+m.getYear()+"</b> Summing to Rs. <span style=\"color:red;\">"+p+"</span><br>";
						}
					}
				}
				
				if(ll1!=null)
				{
					Iterator<balance_interest_model> itr1 = ll1.iterator();
					while(itr1.hasNext())
					{
						balance_interest_model b = itr1.next();
						
						if(b.getStatus() == 0)
						{
							Double p = b.getInterest() - b.getHow_much_paid();
							p = Math.ceil(p);
							s += "Interest Due for <b>"+b.getMonth()+"/"+b.getYear()+"</b> Summing to Rs. <span style=\"color:red;\">"+p+"</span><br>";
						}
					}
				}
		
				return new balance_UI_Info(bal,s);
			}
		}
		else
		{
			System.out.println("h1");
			return null;
		}
	}
	
	public int payBalanceMaintenance(Integer flatid,String amt,String paid_date,String type,String type_detail,String payment_info)
	{
		//validating the Inputs
		try
		{
			Double amount = Double.parseDouble(amt);
			String data[] = paid_date.split("-");
			//System.out.println(paid_date);
			//System.out.println(data.length);
			String newS = "";
			newS += data[2]+"/"+data[1]+"/"+data[0];
			paid_date = newS;
			data[1] = String.valueOf(Integer.parseInt(data[1]));
			String d = database.getDatabase().getFinalMonth();
			String data1[] = d.split("-");

			Integer m = Integer.parseInt(data1[0]);
			Integer y = Integer.parseInt(data1[1]);
			
			if(m!=12)
			{
				m++;
				data1[0] = String.valueOf(m);
			}
			else
			{
				data1[0] = "1";
				y++;
				data1[1] = String.valueOf(y);
			}
			
			Integer result = -2;
			System.out.println(data1[0]+" "+data1[1]+" "+data[0]+" "+data[1]+" ");
			if(data1[0].equals(data[1]) && data1[1].equals(data[0]))
			{
				result = database.getDatabase().payBalance(flatid, amount, paid_date, type, type_detail, payment_info);
			}
			
			return result;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	
	
	public balance_table_info[] balanceHistory(FlatModel f)
	{
		LinkedList<balance_payment_info> ll = database.getDatabase().getAllBalancePaymentInfo(f.getFlatID());
		LinkedList<payment> ll1 = database.getDatabase().getPayment();
		
		
		int res = 0;
		balance_table_info bti[] = null;
		int count = 0;
		if(ll!=null && ll1!=null)
		{
			bti = new balance_table_info[ll.size()];
			while(!ll.isEmpty())
			{
				balance_payment_info bpi = ll.removeFirst();
				Iterator<payment> itr = ll1.iterator();
				payment p1 = null;
				
				while(itr.hasNext())
				{
					payment p = itr.next();
					if(p.getId().equals(bpi.getPayment_id()))
					{
						p1 = p;
						break;
					}
				}
				
				if(p1!=null)
				{
					balance_info bi = database.getDatabase().getLastIDBalanceInfo(bpi.getId());
					String inf = "<b>Type: </b>"+p1.getType()+"<br>"+p1.getType_detail();
					bti[count] = new balance_table_info(bpi.getId(),p1.getAmount(),p1.getPaid_date(),bi.getInfo(),inf,bpi.getReceipt());
					count++;
				}
				else
					res = -1;
			}
		}
		else
			res = -1;
		
		if(res == -1)
			return null;
		else
			return bti;
		
	}
	
	
	
	public payment[] paymentInfo(String type,String number,String date)
	{
		if(!date.equals(""))
		{
			String data[] = date.split("-");
			date = data[2]+"/"+data[1]+"/"+data[0];
		}
		
		LinkedList<payment> ll = database.getDatabase().getPaymentInfo(type, number, date);
		
		payment p[] = null;
		
		if(ll!=null)
		{
			p = new payment[ll.size()];
			int count = 0;
			
			while(!ll.isEmpty())
			{
				p[count] = ll.removeFirst();
				count++;
			}
		}
		
		return p;
	}
	
	
	public maintenance_model getMaintenancePaymentInfo(String id)
	{
		
		try
		{
			Integer id1 = Integer.parseInt(id);
			maintenance_model m1 = null;
			
			LinkedList<maintenance_model> ll = database.getDatabase().getAllMaintenance();
			
			while(!ll.isEmpty())
			{
				maintenance_model m2 = ll.removeFirst();
				
				if(m2.getId().equals(id1))
				{
					m1 = m2;
					break;
				}
			}
			return m1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	public payment getPaymentByID(String id)
	{
		try
		{
			Integer pid = Integer.parseInt(id);
			LinkedList<payment> ll = database.getDatabase().getPayment();
			payment p = null;
			Iterator<payment> itr = ll.iterator();
			
			while(itr.hasNext())
			{
				payment q = itr.next();
				
				if(q.getId().equals(pid))
				{
					//System.out.println(q.getId());
					//System.out.println(pid);
					p = q;
					break;
				}
			}
			return p;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	public LinkedList<payment_bounce> getPaymentBounce(String id)
	{
		LinkedList<payment_bounce> ll = database.getDatabase().getPaymentBounce();
		
		if(ll == null)
		{
			return null;
		}
		else
		{
			Integer pid = Integer.parseInt(id);
			
			Iterator<payment_bounce> itr = ll.iterator();
			
			while(itr.hasNext())
			{
				payment_bounce p = itr.next();
				
				if(p.getPayment_id().equals(pid))
				{
					ll.addLast(p);
				}
			}
			
			if(ll.isEmpty())
				return null;
			else
				return ll;
		}	
	}
	
	
	public int updatePayment(String id,String type,String oldString,String newString)
	{
		int res = 0;
		try
		{
			Integer pid = Integer.parseInt(id);
			Double penalty = 0.0;
			if(type.equalsIgnoreCase("cheque"))
			{
				String olddata[] = oldString.split(",");
				String newdata[] = newString.split(",");
				String finalString = "";
				
				if(olddata.length == 2)
				{
					if(newdata.length == 2)
						finalString = "Cheque No: "+newdata[0]+",Cheque Date: "+newdata[1];
					else if(newdata.length == 3)
						finalString = "Cheque No: "+newdata[0]+",Cheque Date: "+newdata[1]+",Bounced: "+newdata[2];
					else if(newdata.length > 3)
					{
						penalty = Double.parseDouble(newdata[3]);
						finalString = "Cheque No: "+newdata[0]+",Cheque Date: "+newdata[1]+",Bounced: "+newdata[2]+
								",Bounced_Info: [{Penalty: "+newdata[3]+",Desc: "+newdata[4]+"}]";
					}
						
				}
				else if(olddata.length == 3)
				{
					if(newdata.length == 2)
						finalString = "Cheque No: "+newdata[0]+",Cheque Date: "+newdata[1]+","+olddata[2];
					else if(newdata.length == 3)
						finalString = "Cheque No: "+newdata[0]+",Cheque Date: "+newdata[1]+",Bounced: "+newdata[2];
					else if(newdata.length > 3)
					{
						penalty = Double.parseDouble(newdata[3]);
						finalString = "Cheque No: "+newdata[0]+",Cheque Date: "+newdata[1]+",Bounced: "+newdata[2]+
								",Bounced_Info: [{Penalty: "+newdata[3]+",Desc: "+newdata[4]+"}]";
					}
						
				}
				else if(olddata.length == 4)
				{
					if(newdata.length == 2)
						finalString = "Cheque No: "+newdata[0]+",Cheque Date: "+newdata[1]+olddata[2]+","+olddata[3];
					if(newdata.length == 3)
						finalString = "Cheque No: "+newdata[0]+",Cheque Date: "+newdata[1]+",Bounced: "+newdata[2]+","+olddata[3];
					else if(newdata.length > 3)
					{
						String s = olddata[3];
						String s1 = "{Penalty: "+newdata[3]+",Desc: "+newdata[4]+"}";
						String p = s.substring(0, s.length()-1)+","+s1+"]";
						penalty = Double.parseDouble(newdata[3]);
						finalString = "Cheque No: "+newdata[0]+",Cheque Date: "+newdata[1]+",Bounced: "+newdata[2]+p;
					}
						
				}
				
				
				res = database.getDatabase().updatePayment(pid, finalString,"cheque",penalty);
				
				
			}
			else if(type.equalsIgnoreCase("neft"))
			{
				String olddata[] = oldString.split(",");
				olddata[0] = "NEFT No: "+newString;
				String finalString = olddata[0]+","+olddata[1];
				res = database.getDatabase().updatePayment(pid, finalString,"neft",penalty);
			}
			else if(type.equalsIgnoreCase("imps"))
			{
				String olddata[] = oldString.split(",");
				olddata[0] = "IMPS No: "+newString;
				String finalString = olddata[0]+","+olddata[1];
				res = database.getDatabase().updatePayment(pid, finalString,"imps",penalty);
			}
			
			return res;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public int addPettyBalance(String amt,String pettydate,String pettydesc)
	{
		try
		{
			Double b = Double.parseDouble(amt);
			if(database.getDatabase().insertPettyBalance(b,pettydate,pettydesc) == 0)
				return 0;
			else
				return 1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public int spendPettyBalance(String amount,String header,String desc,String date)
	{
		try
		{
			Double amt = Double.parseDouble(amount);
			Integer id = database.getDatabase().getPettyHeaderByID(header);
			
			if(database.getDatabase().getPettyBalance() >= amt)
				return database.getDatabase().spendPettyCash(amt, date, id, desc);
			else
				return 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public int addExpenditure(String desc,String type,String type_detail,String amt,String dop,String header)
	{
		try
		{
			Double a = Double.parseDouble(amt);
			Integer id = database.getDatabase().getPettyHeaderByID(header);
			
			int res = database.getDatabase().addExpenditure(desc, type, type_detail, a, dop,id);
			
			if(res == 1)
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
	
	public LinkedList<expenditure_info> getExpenditureInfo(String month,String year)
	{
		String data = year+"-"+month;
		
		LinkedList<expenditure_info> ll = database.getDatabase().getExpenditureInfo();
		
		LinkedList<expenditure_info> ll1 = new LinkedList<expenditure_info>();
		
		if(ll!=null)
		{
			Iterator<expenditure_info> itr = ll.iterator();
			
			while(itr.hasNext())
			{
				expenditure_info ei = itr.next();
				
				if(ei.getDate().contains(data))
				{
					ll1.addLast(ei);
				}
			}
		}
		
		if(ll1.isEmpty())
			return null;
		else 
			return ll1;
	}
	
	public LinkedList<expenditure_info> getExpenditureInfoByYear(String year)
	{
		//String data = year+"-"+month;
		
		LinkedList<expenditure_info> ll = database.getDatabase().getExpenditureInfo();
		
		LinkedList<expenditure_info> ll1 = new LinkedList<expenditure_info>();
		
		if(ll!=null)
		{
			Iterator<expenditure_info> itr = ll.iterator();
			
			while(itr.hasNext())
			{
				expenditure_info ei = itr.next();
				
				if(ei.getDate().contains(year))
				{
					ll1.addLast(ei);
				}
			}
		}
		
		if(ll1.isEmpty())
			return null;
		else 
			return ll1;
	}
	
	public LinkedList<petty_info> getPettyInfo(String month,String year)
	{
		String data = year+"-"+month;
		
		LinkedList<petty_info> ll = database.getDatabase().getPettyInfo();
		
		LinkedList<petty_info> ll1 = new LinkedList<petty_info>();
		
		if(ll!=null)
		{
			Iterator<petty_info> itr = ll.iterator();
			
			while(itr.hasNext())
			{
				petty_info p = itr.next();
				
				if(p.getDate().contains(data))
				{
					ll1.addLast(p);
				}
			}
		}
		
		if(ll1.isEmpty())
			return null;
		else 
			return ll1;
		
	}
	
	public LinkedList<petty_info> getPettyInfoByYear(String year)
	{
		LinkedList<petty_info> ll = database.getDatabase().getPettyInfo();
		
		LinkedList<petty_info> ll1 = new LinkedList<petty_info>();
		
		if(ll!=null)
		{
			Iterator<petty_info> itr = ll.iterator();
			
			while(itr.hasNext())
			{
				petty_info p = itr.next();
				
				if(p.getDate().contains(year))
				{
					ll1.addLast(p);
				}
			}
		}
		
		if(ll1.isEmpty())
			return null;
		else 
			return ll1;
	}
	
	public int addIncome(String desc,String type,String type_detail,String amt,String dop,String header)
	{
		try
		{
			Double a = Double.parseDouble(amt);
			Integer id = database.getDatabase().getPettyHeaderByID(header);
			int res = database.getDatabase().addIncome(desc, type, type_detail, a, dop,id);
			
			if(res == 1)
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
	
	public int updateIncomeSpecific(String id,String amount,String type,String detail,String desc,String dop)
	{
		
		try
		{
			Integer pid = Integer.parseInt(id);
			Double pamt = Double.parseDouble(amount);
			
			int res = database.getDatabase().updateIncome(pid, pamt, type, detail, desc, dop);
			
			return res;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public income getIncomeById(String id)
	{
		try
		{
			Integer pid = Integer.parseInt(id);
			LinkedList<income> ll = database.getDatabase().getIncome();
			income q = null;
			
			if(ll!=null)
			{
				Iterator<income> itr = ll.iterator();
				
				while(itr.hasNext())
				{
					income p = itr.next();
					
					if(p.getId().equals(pid))
					{
						q = p;
						break;
					}
				}
			}
			
			return q;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public LinkedList<income> getIncome(String month,String year)
	{
		String data = year+"-"+month;
		LinkedList<income> ll = database.getDatabase().getIncome();
		
		LinkedList<income> ll1 = new LinkedList<income>();
		
		if(ll!=null)
		{
			Iterator<income> itr = ll.iterator();
			
			while(itr.hasNext())
			{
				income p = itr.next();
				
				if(p.getDop().contains(data))
				{
					ll1.addLast(p);
				}
			}
		}
		
		if(ll1.isEmpty())
			return null;
		else
			return ll1;
		
	}
	
	public LinkedList<income> getIncomeByYear(String year)
	{
		LinkedList<income> ll = database.getDatabase().getIncome();
		
		LinkedList<income> ll1 = new LinkedList<income>();
		
		if(ll!=null)
		{
			Iterator<income> itr = ll.iterator();
			
			while(itr.hasNext())
			{
				income p = itr.next();
				
				if(p.getDop().contains(year))
				{
					ll1.addLast(p);
				}
			}
		}
		
		if(ll1.isEmpty())
			return null;
		else
			return ll1;
	}
	
	public petty_info getPettyInfoByID(String id)
	{
		try
		{
			Integer pid = Integer.parseInt(id);
			return database.getDatabase().getPettyInfoByID(pid);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public int updatePettyBalance(String id,String amt,String pettyamount,String spendheader,String pettydesc,String pettydate)
	{
		int res = 0;
		try
		{
			Integer pid = Integer.parseInt(id);
			Double aamt = Double.parseDouble(amt);
			Double uamt = Double.parseDouble(pettyamount);
			if(aamt!=uamt)
			{
				Double bal = database.getDatabase().getPettyBalance();
				
				if(uamt > aamt)
				{
					if((uamt-aamt) < bal)
					{
						res = database.getDatabase().editPettyInfo(pid,"subtract",(uamt-aamt),uamt,spendheader,pettydesc,pettydate);
					}
				}
				else
				{
					res = database.getDatabase().editPettyInfo(pid,"add",(aamt-uamt),uamt,spendheader,pettydesc,pettydate);
				}
			}
			else
			{
				res = database.getDatabase().editPettyInfo(pid,"",0.0,uamt,spendheader,pettydesc,pettydate);
			}
			
			return res;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public expenditure_info getExpenditureById(String id)
	{
		try
		{
			Integer pid = Integer.parseInt(id);
			LinkedList<expenditure_info> ll = database.getDatabase().getExpenditureInfo();
			
			if(ll!=null)
			{
				Iterator<expenditure_info> itr = ll.iterator();
				
				while(itr.hasNext())
				{
					expenditure_info e = itr.next();
					if(e.getId().equals(pid))
					{
						return e;
					}
				}
				
				return null;
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
	
	public int updateExpenditureSpecific(String id,String cashamt,String type,String detail,String expdesc,String cashdop)
	{
		try
		{
			Integer pid = Integer.parseInt(id);
			Double amt = Double.parseDouble(cashamt);
			
			return database.getDatabase().updateExpenditureSpecific(pid,amt,type,detail,expdesc,cashdop);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
}
