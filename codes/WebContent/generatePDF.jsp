<%@ page import="society.*,com.itextpdf.text.*,com.itextpdf.text.pdf.PdfWriter,java.io.*,java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    	response.setContentType("application/pdf");
    	
    	String type = request.getParameter("type");
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	
    	
    	if(type.equalsIgnoreCase("income"))
    	{
    		String month = request.getParameter("m");
    		String year = request.getParameter("y");
    		
    		if(month!=null && year!=null)
    			PDFGenarator.incomeReportByMonth(baos, month, year);
    		else if(year!=null)
    			PDFGenarator.incomeReportByYear(baos, year);
    	}
    	else if(type.equalsIgnoreCase("flat"))
    	{
    		String q = request.getParameter("q");
    		
    		if(q!=null)
    		{
    			if(q.equals("all"))
    				PDFGenarator.flatDetailByALL(baos);
    			else if(q.equals("owner"))
    				PDFGenarator.flatDetailByOwner(baos);
    			else if(q.equals("tenant"))
    				PDFGenarator.flatDetailByTenant(baos);
    			else if(q.equals("coowner"))
    				PDFGenarator.flatDetailByCOOwner(baos);
    		}
    	}
    	else if(type.equalsIgnoreCase("petty"))
    	{
    		String month = request.getParameter("m");
    		String year = request.getParameter("y");
    		
    		if(month!=null && year!=null)
    			PDFGenarator.pettyReportByMonth(baos, month, year);
    		else if(year!=null)
    			PDFGenarator.pettyReportByYear(baos, year);
    	}
    	else if(type.equalsIgnoreCase("exp"))
    	{
    		String month = request.getParameter("m");
    		String year = request.getParameter("y");
    		
    		if(month!=null && year!=null)
    			PDFGenarator.expReportByMonth(baos, month, year);
    		else if(year!=null)
    			PDFGenarator.expReportByYear(baos, year);
    	}
    	else if(type.equalsIgnoreCase("voucher"))
    	{
    		String q = request.getParameter("q");
    		if(q.equalsIgnoreCase("petty"))
    		{
    			String id = request.getParameter("id");
    			PDFGenarator.pettyVoucher(baos, id);
    		}
    		else if(q.equalsIgnoreCase("exp"))
    		{
    			String id = request.getParameter("id");
    			PDFGenarator.expVoucher(baos, id);
    		}
    		else if(q.equalsIgnoreCase("income"))
    		{
    			String id = request.getParameter("id");
    			PDFGenarator.incomeVoucher(baos, id);
    		}
    	}
    	else if(type.equalsIgnoreCase("advrec"))
    	{
    		String id = request.getParameter("id");
    		String fn = request.getParameter("f");
    		PDFGenarator.advanceReceipt(baos, id, fn);
    	}
    	else if(type.equalsIgnoreCase("balrec"))
    	{
    		String id = request.getParameter("id");
    		String fn = request.getParameter("f");
    		PDFGenarator.balanceReceipt(baos, id, fn);
    	}
    	else if(type.equalsIgnoreCase("maint"))
    	{
    		//flatno,month,year
    		//flatno,quarter,year
    		//month,quarter,all or blocks    		
    		//month,quarter, all or block,all or paid, unpaid
    		String f = request.getParameter("f");
    		String q = request.getParameter("q");
    		String m = request.getParameter("m");
    		String y = request.getParameter("y");
    		String b = request.getParameter("b");
    		String o = request.getParameter("o");
    		String d = request.getParameter("d");
    		
    		if(f!=null)
    		{
    			if(m!=null)
    			{
    				PDFGenarator.flatMonthMaint(baos, f, m, y);
    			}
    			else if(q!=null)
    			{
    				PDFGenarator.flatQuarterMaint(baos, f, q, y);
    			}
    		}
    		else
    		{
    			if(m!=null)
    			{
    				if(d.equals("m"))
    				{
    					PDFGenarator.allFlatMonthMaint(baos, m, y, b, o);	
    				}
    				else if(d.equals("r"))
    				{
    					PDFGenarator.allFlatMonthReport(baos, m, y, b, o);
    				}
    			}
    			else if(q!=null)
    			{
    				if(d.equals("m"))
    				{
    					PDFGenarator.allFlatQuarterMaint(baos, q, y, b, o);
    				}
    				else if(d.equals("r"))
    				{
    					PDFGenarator.allFlatQuarterReport(baos, q, y, b, o);
    				}
    			}
    		}
    		
    	}
    	
    	OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
        os.close();
    %>