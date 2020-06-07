package society;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenarator {
	
	public static void incomeReportByMonth(ByteArrayOutputStream baos,String month,String year)
	{
		dataManager d = new dataManager();
		
		//LinkedList<income> in = d.getIncome(month, year);
		HashMap<Integer,String> months = new HashMap<Integer,String>();
    	
    	months.put(1, "January");
    	months.put(2, "February");
    	months.put(3, "March");
    	months.put(4, "April");
    	months.put(5, "May");
    	months.put(6, "June");
    	months.put(7, "July");
    	months.put(8, "August");
    	months.put(9, "September");
    	months.put(10, "October");
    	months.put(11, "November");
    	months.put(12, "December");
		try
		{
			Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.setMargins(20, 20, 10, 10);
			document.open();
			
			Chunk c = new Chunk("LA-TIERRA CO-OPERATIVE HOUSING SOCIETY");
			Font f = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			c.setFont(f);
			Paragraph p = new Paragraph(c);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			document.add(new Paragraph(" "));
			
			String s = "Income Report - "+months.get(Integer.parseInt(month))+" "+year;
			Chunk c1 = new Chunk(s);
			c1.setUnderline(0.1f, -2f);
			Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c1.setFont(f1);
			Paragraph p2 = new Paragraph(c1);
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			
			Paragraph p1 = new Paragraph("Date:   " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            p1.setAlignment(Element.ALIGN_RIGHT);
            document.add(p1);
            
            document.add(new Paragraph(" "));
            
            PdfPTable table = new PdfPTable(6);
            
            Font f2 = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
            
            Paragraph ph1 = new Paragraph("Sr No.");
            ph1.setFont(f2);
            Paragraph ph2 = new Paragraph("Description");
            ph2.setFont(f2);
            Paragraph ph3 = new Paragraph("Payment Date");
            ph3.setFont(f2);
            Paragraph ph4 = new Paragraph("Type");
            ph4.setFont(f2);
            Paragraph ph5 = new Paragraph("Type Detail");
            ph5.setFont(f2);
            Paragraph ph6 = new Paragraph("Amount");
            ph6.setFont(f2);
            
            table.addCell(ph1);
            table.addCell(ph2);
            table.addCell(ph3);
            table.addCell(ph4);
            table.addCell(ph5);
            table.addCell(ph6);
            
            LinkedList<income> ll = d.getIncome(month, year);
            int count = 0;
            while(!ll.isEmpty())
            {
            	income i = ll.removeFirst();
            	count++;     	
            	Paragraph ph7 = new Paragraph(String.valueOf(count));
            	Paragraph ph8 = new Paragraph(i.getDesc());
            	Paragraph ph9 = new Paragraph(i.getDop());
            	Paragraph ph10 = new Paragraph(i.getType().toUpperCase());
            	Paragraph ph11 = new Paragraph(i.getType_detail());
            	Paragraph ph12 = new Paragraph(String.valueOf(i.getAmt()));
            	
            	table.addCell(ph7);
            	table.addCell(ph8);
            	table.addCell(ph9);
            	table.addCell(ph10);
            	table.addCell(ph11);
            	table.addCell(ph12);
            	float[] columnWidths = new float[] {1f,8f,3f,3f,4f,3f};
	            table.setWidths(columnWidths);
            }
            
            table.setTotalWidth(PageSize.A4.getWidth()-20);
            table.setLockedWidth(true);
            document.add(table);

            document.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void incomeReportByYear(ByteArrayOutputStream baos,String year)
	{
		dataManager d = new dataManager();
		
		//LinkedList<income> in = d.getIncomeByYear(year);
		
		try
		{
			Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.setMargins(20, 20, 10, 10);
			document.open();
			
			Chunk c = new Chunk("LA-TIERRA CO-OPERATIVE HOUSING SOCIETY");
			Font f = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			c.setFont(f);
			Paragraph p = new Paragraph(c);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			document.add(new Paragraph(" "));
			
			String s = "Income Report - "+year;
			Chunk c1 = new Chunk(s);
			c1.setUnderline(0.1f, -2f);
			Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c1.setFont(f1);
			Paragraph p2 = new Paragraph(c1);
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			
			Paragraph p1 = new Paragraph("Date:   " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            p1.setAlignment(Element.ALIGN_RIGHT);
            document.add(p1);
            
            document.add(new Paragraph(" "));
            
            PdfPTable table = new PdfPTable(6);
            
            Font f2 = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
            
            Paragraph ph1 = new Paragraph("Sr No.");
            ph1.setFont(f2);
            Paragraph ph2 = new Paragraph("Description");
            ph2.setFont(f2);
            Paragraph ph3 = new Paragraph("Payment Date");
            ph3.setFont(f2);
            Paragraph ph4 = new Paragraph("Type");
            ph4.setFont(f2);
            Paragraph ph5 = new Paragraph("Type Detail");
            ph5.setFont(f2);
            Paragraph ph6 = new Paragraph("Amount");
            ph6.setFont(f2);
            
            table.addCell(ph1);
            table.addCell(ph2);
            table.addCell(ph3);
            table.addCell(ph4);
            table.addCell(ph5);
            table.addCell(ph6);
            
            LinkedList<income> ll = d.getIncomeByYear(year);
            int count = 0;
            while(!ll.isEmpty())
            {
            	income i = ll.removeFirst();
            	count++;     	
            	Paragraph ph7 = new Paragraph(String.valueOf(count));
            	Paragraph ph8 = new Paragraph(i.getDesc());
            	Paragraph ph9 = new Paragraph(i.getDop());
            	Paragraph ph10 = new Paragraph(i.getType().toUpperCase());
            	Paragraph ph11 = new Paragraph(i.getType_detail());
            	Paragraph ph12 = new Paragraph(String.valueOf(i.getAmt()));
            	
            	table.addCell(ph7);
            	table.addCell(ph8);
            	table.addCell(ph9);
            	table.addCell(ph10);
            	table.addCell(ph11);
            	table.addCell(ph12);
            	float[] columnWidths = new float[] {1f,8f,3f,3f,4f,3f};
	            table.setWidths(columnWidths);
            }
            
            table.setTotalWidth(PageSize.A4.getWidth()-20);
            table.setLockedWidth(true);
            document.add(table);

            document.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void flatDetailByALL(ByteArrayOutputStream baos)
	{
		dataManager d = new dataManager();
		
		//LinkedList<income> in = d.getIncomeByYear(year);
		
		try
		{
			Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.setMargins(20, 20, 10, 10);
			document.open();
			
			Chunk c = new Chunk("LA-TIERRA CO-OPERATIVE HOUSING SOCIETY");
			Font f = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			c.setFont(f);
			Paragraph p = new Paragraph(c);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			document.add(new Paragraph(" "));
			
			String s = "ALL FLATS INFO";
			Chunk c1 = new Chunk(s);
			c1.setUnderline(0.1f, -2f);
			Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c1.setFont(f1);
			Paragraph p2 = new Paragraph(c1);
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			
			Paragraph p1 = new Paragraph("Date:   " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            p1.setAlignment(Element.ALIGN_RIGHT);
            document.add(p1);
            
            document.add(new Paragraph(" "));
            
            PdfPTable table = new PdfPTable(6);
            
            Font f2 = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
            
            Paragraph ph1 = new Paragraph("Flat No.");
            ph1.setFont(f2);
            Paragraph ph2 = new Paragraph("Name");
            ph2.setFont(f2);
            Paragraph ph3 = new Paragraph("Area(Sqft)");
            ph3.setFont(f2);
            Paragraph ph4 = new Paragraph("Mobile");
            ph4.setFont(f2);
            Paragraph ph5 = new Paragraph("E-Mail");
            ph5.setFont(f2);
            Paragraph ph6 = new Paragraph("Category");
            ph6.setFont(f2);
            
            table.addCell(ph1);
            table.addCell(ph2);
            table.addCell(ph3);
            table.addCell(ph4);
            table.addCell(ph5);
            table.addCell(ph6);
            
            LinkedList<sortall> ll = d.getAllFlatsDetail();
            //int count = 0;
            while(!ll.isEmpty())
            {
            	sortall i = ll.removeFirst();
            	//count++;     	
            	Paragraph ph7 = new Paragraph(i.getFlatno().toUpperCase());
            	Paragraph ph8 = new Paragraph(i.getName().toUpperCase());
            	Paragraph ph9 = new Paragraph(i.getArea());
            	Paragraph ph10 = new Paragraph(i.getMobile());
            	Paragraph ph11 = new Paragraph(i.getEmail());
            	Paragraph ph12 = new Paragraph(i.getCategory().toUpperCase());
            	
            	table.addCell(ph7);
            	table.addCell(ph8);
            	table.addCell(ph9);
            	table.addCell(ph10);
            	table.addCell(ph11);
            	table.addCell(ph12);
            	float[] columnWidths = new float[] {2f,8f,3f,3f,4f,3f};
	            table.setWidths(columnWidths);
            }
            
            table.setTotalWidth(PageSize.A4.getWidth()-20);
            table.setLockedWidth(true);
            document.add(table);

            document.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void flatDetailByOwner(ByteArrayOutputStream baos)
	{
		dataManager d = new dataManager();
		
		//LinkedList<income> in = d.getIncomeByYear(year);
		
		try
		{
			Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.setMargins(20, 20, 10, 10);
			document.open();
			
			Chunk c = new Chunk("LA-TIERRA CO-OPERATIVE HOUSING SOCIETY");
			Font f = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			c.setFont(f);
			Paragraph p = new Paragraph(c);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			document.add(new Paragraph(" "));
			
			String s = "ALL OWNER's FLATS INFO";
			Chunk c1 = new Chunk(s);
			c1.setUnderline(0.1f, -2f);
			Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c1.setFont(f1);
			Paragraph p2 = new Paragraph(c1);
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			
			Paragraph p1 = new Paragraph("Date:   " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            p1.setAlignment(Element.ALIGN_RIGHT);
            document.add(p1);
            
            document.add(new Paragraph(" "));
            
            PdfPTable table = new PdfPTable(5);
            
            Font f2 = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
            
            Paragraph ph1 = new Paragraph("Flat No.");
            ph1.setFont(f2);
            Paragraph ph2 = new Paragraph("Name");
            ph2.setFont(f2);
            Paragraph ph3 = new Paragraph("Area(Sqft)");
            ph3.setFont(f2);
            Paragraph ph4 = new Paragraph("Mobile");
            ph4.setFont(f2);
            Paragraph ph5 = new Paragraph("E-Mail");
            ph5.setFont(f2);
            
            table.addCell(ph1);
            table.addCell(ph2);
            table.addCell(ph3);
            table.addCell(ph4);
            table.addCell(ph5);
            
            
            LinkedList<sortowners> ll = d.getOwnerFlatsDetail();
            //int count = 0;
            while(!ll.isEmpty())
            {
            	sortowners i = ll.removeFirst();
            	//count++;     	
            	Paragraph ph7 = new Paragraph(i.getFlatno().toUpperCase());
            	Paragraph ph8 = new Paragraph(i.getOwner_name().toUpperCase());
            	Paragraph ph9 = new Paragraph(i.getArea());
            	Paragraph ph10 = new Paragraph(i.getMobile());
            	Paragraph ph11 = new Paragraph(i.getEmail());
            	
            	
            	table.addCell(ph7);
            	table.addCell(ph8);
            	table.addCell(ph9);
            	table.addCell(ph10);
            	table.addCell(ph11);
            	
            	float[] columnWidths = new float[] {2f,8f,2f,3f,4f};
	            table.setWidths(columnWidths);
            }
            
            table.setTotalWidth(PageSize.A4.getWidth()-20);
            table.setLockedWidth(true);
            document.add(table);

            document.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void flatDetailByTenant(ByteArrayOutputStream baos)
	{
		dataManager d = new dataManager();
		
		//LinkedList<income> in = d.getIncomeByYear(year);
		
		try
		{
			Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.setMargins(20, 20, 10, 10);
			document.open();
			
			Chunk c = new Chunk("LA-TIERRA CO-OPERATIVE HOUSING SOCIETY");
			Font f = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			c.setFont(f);
			Paragraph p = new Paragraph(c);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			document.add(new Paragraph(" "));
			
			String s = "ALL TENANTS's FLATS INFO";
			Chunk c1 = new Chunk(s);
			c1.setUnderline(0.1f, -2f);
			Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c1.setFont(f1);
			Paragraph p2 = new Paragraph(c1);
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			
			Paragraph p1 = new Paragraph("Date:   " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            p1.setAlignment(Element.ALIGN_RIGHT);
            document.add(p1);
            
            document.add(new Paragraph(" "));
            
            PdfPTable table = new PdfPTable(5);
            
            Font f2 = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
            
            Paragraph ph1 = new Paragraph("Flat No.");
            ph1.setFont(f2);
            Paragraph ph2 = new Paragraph("Name");
            ph2.setFont(f2);
            Paragraph ph3 = new Paragraph("Area(Sqft)");
            ph3.setFont(f2);
            Paragraph ph4 = new Paragraph("Mobile");
            ph4.setFont(f2);
            Paragraph ph5 = new Paragraph("E-Mail");
            ph5.setFont(f2);
            
            table.addCell(ph1);
            table.addCell(ph2);
            table.addCell(ph3);
            table.addCell(ph4);
            table.addCell(ph5);
            
            
            LinkedList<sortall> ll1 = d.getAllFlatsDetail();
            //int count = 0;
            
            LinkedList<sortall> ll = new LinkedList<sortall>();
            
            Iterator<sortall> itr = ll1.iterator();
            
            while(itr.hasNext())
            {
            	sortall s1 = itr.next();
            	
            	if(s1.getCategory().equalsIgnoreCase("tenant"))
            		ll.addLast(s1);
            }
            		
            while(!ll.isEmpty())
            {
            	sortall i = ll.removeFirst();
            	//count++;     	
            	Paragraph ph7 = new Paragraph(i.getFlatno().toUpperCase());
            	Paragraph ph8 = new Paragraph(i.getName().toUpperCase());
            	Paragraph ph9 = new Paragraph(i.getArea());
            	Paragraph ph10 = new Paragraph(i.getMobile());
            	Paragraph ph11 = new Paragraph(i.getEmail());
            	
            	
            	table.addCell(ph7);
            	table.addCell(ph8);
            	table.addCell(ph9);
            	table.addCell(ph10);
            	table.addCell(ph11);
            	
            	float[] columnWidths = new float[] {2f,8f,2f,3f,4f};
	            table.setWidths(columnWidths);
            }
            
            table.setTotalWidth(PageSize.A4.getWidth()-20);
            table.setLockedWidth(true);
            document.add(table);

            document.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void flatDetailByCOOwner(ByteArrayOutputStream baos)
	{
		dataManager d = new dataManager();
		
		//LinkedList<income> in = d.getIncomeByYear(year);
		
		try
		{
			Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.setMargins(20, 20, 10, 10);
			document.open();
			
			Chunk c = new Chunk("LA-TIERRA CO-OPERATIVE HOUSING SOCIETY");
			Font f = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			c.setFont(f);
			Paragraph p = new Paragraph(c);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			document.add(new Paragraph(" "));
			
			String s = "ALL CO-OWNER's FLATS INFO";
			Chunk c1 = new Chunk(s);
			c1.setUnderline(0.1f, -2f);
			Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c1.setFont(f1);
			Paragraph p2 = new Paragraph(c1);
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			
			Paragraph p1 = new Paragraph("Date:   " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            p1.setAlignment(Element.ALIGN_RIGHT);
            document.add(p1);
            
            document.add(new Paragraph(" "));
            
            PdfPTable table = new PdfPTable(5);
            
            Font f2 = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
            
            Paragraph ph1 = new Paragraph("Flat No.");
            ph1.setFont(f2);
            Paragraph ph2 = new Paragraph("Names");
            ph2.setFont(f2);
            Paragraph ph3 = new Paragraph("Area(Sqft)");
            ph3.setFont(f2);
            Paragraph ph4 = new Paragraph("Mobile");
            ph4.setFont(f2);
            Paragraph ph5 = new Paragraph("E-Mail");
            ph5.setFont(f2);
            
            table.addCell(ph1);
            table.addCell(ph2);
            table.addCell(ph3);
            table.addCell(ph4);
            table.addCell(ph5);
            
            
            LinkedList<sortcoowner> ll = d.getCoownerFlatsDetail();
            //int count = 0;
            while(!ll.isEmpty())
            {
            	sortcoowner i = ll.removeFirst();
            	//count++;     	
            	Paragraph ph7 = new Paragraph(i.getFlatno().toUpperCase());
            	
            	Iterator<String> itr = i.getCoowner_name().iterator();
            	String s1 = "";
            	while(itr.hasNext())
            	{
            		s1 += itr.next()+"\n";
            	}
            	
            	
            	Paragraph ph8 = new Paragraph(s1.toUpperCase());
            	Paragraph ph9 = new Paragraph(i.getArea());
            	
            	Iterator<String> itr1 = i.getMobile().iterator();
            	String s2 = "";
            	while(itr1.hasNext())
            	{
            		s2 += itr1.next()+"\n";
            	}
            	
            	Paragraph ph10 = new Paragraph(s2);
            	
            	Iterator<String> itr2 = i.getEmail().iterator();
            	String s3 = "";
            	while(itr2.hasNext())
            	{
            		s3 += itr2.next()+"\n";
            	}
            	
            	Paragraph ph11 = new Paragraph(s3);
            	
            	
            	table.addCell(ph7);
            	table.addCell(ph8);
            	table.addCell(ph9);
            	table.addCell(ph10);
            	table.addCell(ph11);
            	
            	float[] columnWidths = new float[] {2f,8f,2f,3f,4f};
	            table.setWidths(columnWidths);
            }
            
            table.setTotalWidth(PageSize.A4.getWidth()-20);
            table.setLockedWidth(true);
            document.add(table);

            document.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void pettyReportByMonth(ByteArrayOutputStream baos,String month,String year)
	{
		dataManager d = new dataManager();
		
		//LinkedList<income> in = d.getIncome(month, year);
		HashMap<Integer,String> months = new HashMap<Integer,String>();
    	
    	months.put(1, "January");
    	months.put(2, "February");
    	months.put(3, "March");
    	months.put(4, "April");
    	months.put(5, "May");
    	months.put(6, "June");
    	months.put(7, "July");
    	months.put(8, "August");
    	months.put(9, "September");
    	months.put(10, "October");
    	months.put(11, "November");
    	months.put(12, "December");
		try
		{
			Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.setMargins(20, 20, 10, 10);
			document.open();
			
			Chunk c = new Chunk("LA-TIERRA CO-OPERATIVE HOUSING SOCIETY");
			Font f = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			c.setFont(f);
			Paragraph p = new Paragraph(c);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			document.add(new Paragraph(" "));
			
			String s = "Petty Report - "+months.get(Integer.parseInt(month))+" "+year;
			Chunk c1 = new Chunk(s);
			c1.setUnderline(0.1f, -2f);
			Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c1.setFont(f1);
			Paragraph p2 = new Paragraph(c1);
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			
			Paragraph p1 = new Paragraph("Date:   " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            p1.setAlignment(Element.ALIGN_RIGHT);
            document.add(p1);
            
            document.add(new Paragraph(" "));
            
            String s1 = "Petty Cash Added - "+month+"-"+year;
			Chunk c11 = new Chunk(s1);
			c11.setUnderline(0.1f, -2f);
			Font f11 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c11.setFont(f11);
			Paragraph p21 = new Paragraph(c11);
			p21.setAlignment(Element.ALIGN_LEFT);
			document.add(p21);
            
			document.add(new Paragraph(" "));
			
			LinkedList<String> ll3 = database.getDatabase().getPettyCash();
			
			LinkedList<String> ll2 = new LinkedList<String>();
			
			Iterator<String> itr = ll3.iterator();
			
			while(itr.hasNext())
			{
				String s6 = itr.next();
				
				if(s6.contains("-"+month+"-"))
				{
					ll2.add(s6);
				}
			}
			
			PdfPTable table1 = new PdfPTable(4);
            
            Font f3 = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
            
            Paragraph ph11 = new Paragraph("Sr No.");
            ph11.setFont(f3);
            Paragraph ph21 = new Paragraph("Amount");
            ph21.setFont(f3);
            Paragraph ph31 = new Paragraph("Payment Date");
            ph31.setFont(f3);
            Paragraph ph41 = new Paragraph("Description");
            ph41.setFont(f3);
            table1.addCell(ph11);
            table1.addCell(ph21);
            table1.addCell(ph31);
            table1.addCell(ph41);
            
            int count1 = 0;
            while(!ll2.isEmpty())
            {
            	String p5[] = ll2.removeLast().split(",");
            	count1++;     	
            	Paragraph ph71 = new Paragraph(String.valueOf(count1));
            	Paragraph ph81 = new Paragraph(p5[0]);
            	Paragraph ph91 = new Paragraph(p5[1]);
            	String s5 = "";
            	for(int i=2;i<p5.length;i++)
				{
            		s5 += p5[i];
				}
            	Paragraph ph101 = new Paragraph(s5);
            	
            	
            	
            	table1.addCell(ph71);
            	table1.addCell(ph81);
            	table1.addCell(ph91);
            	table1.addCell(ph101);
            	
            	//table.addCell(ph12);
            	float[] columnWidths = new float[] {1f,8f,3f,3f};
	            table1.setWidths(columnWidths);
            }
            
            table1.setTotalWidth(PageSize.A4.getWidth()-20);
            table1.setLockedWidth(true);
            document.add(table1);
            
            
            
            
            
            
            document.add(new Paragraph(" "));
            
            String s9 = "Petty Cash Spend - "+month+"-"+year;
			Chunk c9 = new Chunk(s9);
			c9.setUnderline(0.1f, -2f);
			Font f9 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c9.setFont(f9);
			Paragraph p20 = new Paragraph(c9);
			p20.setAlignment(Element.ALIGN_LEFT);
			document.add(p20);
            
            
			document.add(new Paragraph(" ")); 
            
            
            
            
            PdfPTable table = new PdfPTable(5);
            
            Font f2 = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
            
            Paragraph ph1 = new Paragraph("Sr No.");
            ph1.setFont(f2);
            Paragraph ph2 = new Paragraph("Description");
            ph2.setFont(f2);
            Paragraph ph3 = new Paragraph("Payment Date");
            ph3.setFont(f2);
            Paragraph ph4 = new Paragraph("Header");
            ph4.setFont(f2);
            Paragraph ph6 = new Paragraph("Amount");
            ph6.setFont(f2);
            
            table.addCell(ph1);
            table.addCell(ph2);
            table.addCell(ph3);
            table.addCell(ph4);
            //table.addCell(ph5);
            table.addCell(ph6);
            
            LinkedList<petty_info> ll = d.getPettyInfo(month, year);
            int count = 0;
            while(!ll.isEmpty())
            {
            	petty_info i = ll.removeFirst();
            	count++;     	
            	Paragraph ph7 = new Paragraph(String.valueOf(count));
            	Paragraph ph8 = new Paragraph(i.getDesc());
            	Paragraph ph9 = new Paragraph(i.getDate());
            	Paragraph ph10 = new Paragraph(i.getHeader().toUpperCase());
            	Paragraph ph112 = new Paragraph(String.valueOf(i.getAmt()));
            	//Paragraph ph12 = new Paragraph(String.valueOf(i.getAmt()));
            	
            	table.addCell(ph7);
            	table.addCell(ph8);
            	table.addCell(ph9);
            	table.addCell(ph10);
            	table.addCell(ph112);
            	//table.addCell(ph12);
            	float[] columnWidths = new float[] {1f,8f,3f,3f,4f};
	            table.setWidths(columnWidths);
            }
            
            table.setTotalWidth(PageSize.A4.getWidth()-20);
            table.setLockedWidth(true);
            document.add(table);

            document.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void pettyReportByYear(ByteArrayOutputStream baos,String year)
	{
		dataManager d = new dataManager();
		
		//LinkedList<income> in = d.getIncomeByYear(year);
		
		try
		{
			Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.setMargins(20, 20, 10, 10);
			document.open();
			
			Chunk c = new Chunk("LA-TIERRA CO-OPERATIVE HOUSING SOCIETY");
			Font f = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			c.setFont(f);
			Paragraph p = new Paragraph(c);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			document.add(new Paragraph(" "));
			
			String s = "Petty Report - "+year;
			Chunk c1 = new Chunk(s);
			c1.setUnderline(0.1f, -2f);
			Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c1.setFont(f1);
			Paragraph p2 = new Paragraph(c1);
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			
			Paragraph p1 = new Paragraph("Date:   " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            p1.setAlignment(Element.ALIGN_RIGHT);
            document.add(p1);
            
            String s1 = "Petty Cash Added - "+year;
			Chunk c11 = new Chunk(s1);
			c11.setUnderline(0.1f, -2f);
			Font f11 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c11.setFont(f11);
			Paragraph p21 = new Paragraph(c11);
			p21.setAlignment(Element.ALIGN_LEFT);
			document.add(p21);
            
			document.add(new Paragraph(" "));
			
			LinkedList<String> ll2 = database.getDatabase().getPettyCash();
			
			PdfPTable table1 = new PdfPTable(4);
            
            Font f3 = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
            
            Paragraph ph11 = new Paragraph("Sr No.");
            ph11.setFont(f3);
            Paragraph ph21 = new Paragraph("Amount");
            ph21.setFont(f3);
            Paragraph ph31 = new Paragraph("Payment Date");
            ph31.setFont(f3);
            Paragraph ph41 = new Paragraph("Description");
            ph41.setFont(f3);
            table1.addCell(ph11);
            table1.addCell(ph21);
            table1.addCell(ph31);
            table1.addCell(ph41);
            
            int count1 = 0;
            while(!ll2.isEmpty())
            {
            	String p5[] = ll2.removeLast().split(",");
            	count1++;     	
            	Paragraph ph71 = new Paragraph(String.valueOf(count1));
            	Paragraph ph81 = new Paragraph(p5[0]);
            	Paragraph ph91 = new Paragraph(p5[1]);
            	String s5 = "";
            	for(int i=2;i<p5.length;i++)
				{
            		s5 += p5[i];
				}
            	Paragraph ph101 = new Paragraph(s5);
            	
            	
            	
            	table1.addCell(ph71);
            	table1.addCell(ph81);
            	table1.addCell(ph91);
            	table1.addCell(ph101);
            	
            	//table.addCell(ph12);
            	float[] columnWidths = new float[] {1f,8f,3f,3f};
	            table1.setWidths(columnWidths);
            }
            
            table1.setTotalWidth(PageSize.A4.getWidth()-20);
            table1.setLockedWidth(true);
            document.add(table1);
            
            
            
            
            
            document.add(new Paragraph(" "));
            
            String s9 = "Petty Cash Spend - "+year;
			Chunk c9 = new Chunk(s9);
			c9.setUnderline(0.1f, -2f);
			Font f9 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c9.setFont(f9);
			Paragraph p20 = new Paragraph(c9);
			p20.setAlignment(Element.ALIGN_LEFT);
			document.add(p20);
            
			document.add(new Paragraph(" "));
            
            
            
            
            
            
            PdfPTable table = new PdfPTable(5);
            
            Font f2 = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
            
            Paragraph ph1 = new Paragraph("Sr No.");
            ph1.setFont(f2);
            Paragraph ph2 = new Paragraph("Description");
            ph2.setFont(f2);
            Paragraph ph3 = new Paragraph("Payment Date");
            ph3.setFont(f2);
            Paragraph ph4 = new Paragraph("Header");
            ph4.setFont(f2);
           
            Paragraph ph6 = new Paragraph("Amount");
            ph6.setFont(f2);
            
            table.addCell(ph1);
            table.addCell(ph2);
            table.addCell(ph3);
            table.addCell(ph4);
            //table.addCell(ph5);
            table.addCell(ph6);
            
            LinkedList<petty_info> ll = d.getPettyInfoByYear(year);
            int count = 0;
            while(!ll.isEmpty())
            {
            	petty_info i = ll.removeFirst();
            	count++;     	
            	Paragraph ph7 = new Paragraph(String.valueOf(count));
            	Paragraph ph8 = new Paragraph(i.getDesc());
            	Paragraph ph9 = new Paragraph(i.getDate());
            	Paragraph ph10 = new Paragraph(i.getHeader());
            	Paragraph ph112 = new Paragraph(String.valueOf(i.getAmt()));
            	//Paragraph ph12 = new Paragraph(String.valueOf(i.getAmt()));
            	
            	table.addCell(ph7);
            	table.addCell(ph8);
            	table.addCell(ph9);
            	table.addCell(ph10);
            	table.addCell(ph112);
            	//table.addCell(ph12);
            	float[] columnWidths = new float[] {1f,8f,3f,3f,4f};
	            table.setWidths(columnWidths);
            }
            
            table.setTotalWidth(PageSize.A4.getWidth()-20);
            table.setLockedWidth(true);
            document.add(table);

            document.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void expReportByMonth(ByteArrayOutputStream baos,String month,String year)
	{
		dataManager d = new dataManager();
		
		//LinkedList<income> in = d.getIncome(month, year);
		HashMap<Integer,String> months = new HashMap<Integer,String>();
    	
    	months.put(1, "January");
    	months.put(2, "February");
    	months.put(3, "March");
    	months.put(4, "April");
    	months.put(5, "May");
    	months.put(6, "June");
    	months.put(7, "July");
    	months.put(8, "August");
    	months.put(9, "September");
    	months.put(10, "October");
    	months.put(11, "November");
    	months.put(12, "December");
		try
		{
			Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.setMargins(20, 20, 10, 10);
			document.open();
			
			Chunk c = new Chunk("LA-TIERRA CO-OPERATIVE HOUSING SOCIETY");
			Font f = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			c.setFont(f);
			Paragraph p = new Paragraph(c);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			document.add(new Paragraph(" "));
			
			String s = "Expenditure Report - "+months.get(Integer.parseInt(month))+" "+year;
			Chunk c1 = new Chunk(s);
			c1.setUnderline(0.1f, -2f);
			Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c1.setFont(f1);
			Paragraph p2 = new Paragraph(c1);
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			
			Paragraph p1 = new Paragraph("Date:   " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            p1.setAlignment(Element.ALIGN_RIGHT);
            document.add(p1);
            
            document.add(new Paragraph(" "));
            
            PdfPTable table = new PdfPTable(6);
            
            Font f2 = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
            
            Paragraph ph1 = new Paragraph("Sr No.");
            ph1.setFont(f2);
            Paragraph ph2 = new Paragraph("Description");
            ph2.setFont(f2);
            Paragraph ph3 = new Paragraph("Payment Date");
            ph3.setFont(f2);
            Paragraph ph4 = new Paragraph("Type");
            ph4.setFont(f2);
            Paragraph ph5 = new Paragraph("Type Detail");
            ph5.setFont(f2);
            Paragraph ph6 = new Paragraph("Amount");
            ph6.setFont(f2);
            
            table.addCell(ph1);
            table.addCell(ph2);
            table.addCell(ph3);
            table.addCell(ph4);
            table.addCell(ph5);
            table.addCell(ph6);
            
            LinkedList<expenditure_info> ll = d.getExpenditureInfo(month, year);
            int count = 0;
            while(!ll.isEmpty())
            {
            	expenditure_info i = ll.removeFirst();
            	count++;     	
            	Paragraph ph7 = new Paragraph(String.valueOf(count));
            	Paragraph ph8 = new Paragraph(i.getDesc());
            	Paragraph ph9 = new Paragraph(i.getDate());
            	Paragraph ph10 = new Paragraph(i.getType().toUpperCase());
            	Paragraph ph11 = new Paragraph(i.getType_detail());
            	Paragraph ph12 = new Paragraph(String.valueOf(i.getAmt()));
            	
            	table.addCell(ph7);
            	table.addCell(ph8);
            	table.addCell(ph9);
            	table.addCell(ph10);
            	table.addCell(ph11);
            	table.addCell(ph12);
            	float[] columnWidths = new float[] {1f,8f,3f,3f,4f,3f};
	            table.setWidths(columnWidths);
            }
            
            table.setTotalWidth(PageSize.A4.getWidth()-20);
            table.setLockedWidth(true);
            document.add(table);

            document.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void expReportByYear(ByteArrayOutputStream baos,String year)
	{
		dataManager d = new dataManager();
		
		//LinkedList<income> in = d.getIncomeByYear(year);
		
		try
		{
			Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.setMargins(20, 20, 10, 10);
			document.open();
			
			Chunk c = new Chunk("LA-TIERRA CO-OPERATIVE HOUSING SOCIETY");
			Font f = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			c.setFont(f);
			Paragraph p = new Paragraph(c);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			document.add(new Paragraph(" "));
			
			String s = "Expenditure Report - "+year;
			Chunk c1 = new Chunk(s);
			c1.setUnderline(0.1f, -2f);
			Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c1.setFont(f1);
			Paragraph p2 = new Paragraph(c1);
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			
			Paragraph p1 = new Paragraph("Date:   " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            p1.setAlignment(Element.ALIGN_RIGHT);
            document.add(p1);
            
            document.add(new Paragraph(" "));
            
            PdfPTable table = new PdfPTable(6);
            
            Font f2 = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
            
            Paragraph ph1 = new Paragraph("Sr No.");
            ph1.setFont(f2);
            Paragraph ph2 = new Paragraph("Description");
            ph2.setFont(f2);
            Paragraph ph3 = new Paragraph("Payment Date");
            ph3.setFont(f2);
            Paragraph ph4 = new Paragraph("Type");
            ph4.setFont(f2);
            Paragraph ph5 = new Paragraph("Type Detail");
            ph5.setFont(f2);
            Paragraph ph6 = new Paragraph("Amount");
            ph6.setFont(f2);
            
            table.addCell(ph1);
            table.addCell(ph2);
            table.addCell(ph3);
            table.addCell(ph4);
            table.addCell(ph5);
            table.addCell(ph6);
            
            LinkedList<expenditure_info> ll = d.getExpenditureInfoByYear(year);
            int count = 0;
            while(!ll.isEmpty())
            {
            	expenditure_info i = ll.removeFirst();
            	count++;     	
            	Paragraph ph7 = new Paragraph(String.valueOf(count));
            	Paragraph ph8 = new Paragraph(i.getDesc());
            	Paragraph ph9 = new Paragraph(i.getDate());
            	Paragraph ph10 = new Paragraph(i.getType().toUpperCase());
            	Paragraph ph11 = new Paragraph(i.getType_detail());
            	Paragraph ph12 = new Paragraph(String.valueOf(i.getAmt()));
            	
            	table.addCell(ph7);
            	table.addCell(ph8);
            	table.addCell(ph9);
            	table.addCell(ph10);
            	table.addCell(ph11);
            	table.addCell(ph12);
            	float[] columnWidths = new float[] {1f,8f,3f,3f,4f,3f};
	            table.setWidths(columnWidths);
            }
            
            table.setTotalWidth(PageSize.A4.getWidth()-20);
            table.setLockedWidth(true);
            document.add(table);

            document.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void pettyVoucher(ByteArrayOutputStream baos,String id)
	{
		dataManager d = new dataManager();
		
		try
		{
			Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.setMargins(20, 20, 10, 10);
			document.open();
			
			Chunk c = new Chunk("LA-TIERRA CO-OPERATIVE HOUSING SOCIETY");
			Font f = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			c.setFont(f);
			Paragraph p = new Paragraph(c);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			document.add(new Paragraph(" "));
			
			String s = "Petty Voucher";
			Chunk c1 = new Chunk(s);
			c1.setUnderline(0.1f, -2f);
			Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c1.setFont(f1);
			Paragraph p2 = new Paragraph(c1);
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			
			petty_info pi = d.getPettyInfoByID(id);
			
			Paragraph p1 = new Paragraph("Date:   " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            p1.setAlignment(Element.ALIGN_RIGHT);
            document.add(p1);
            
            document.add(new Paragraph(" "));
            Chunk c2 = new Chunk(pi.getAmt().toString());
            Chunk c3 = new Chunk(pi.getHeader().toUpperCase());
            Chunk c4 = new Chunk(pi.getDate());
            c2.setFont(f1);
            c3.setFont(f1);
            c4.setFont(f1);
            Paragraph p3 = new Paragraph("Payment of Rs. " + c2 + " have been paid to ________________ for the "+c3+" "+pi.getDesc()+" on "+c4);
            document.add(p3);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("For La-Tierra Co-Op. Hsg Soc, Ltd"));
            document.add(new Paragraph("Treasurer/Manager"));
            document.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void expVoucher(ByteArrayOutputStream baos,String id)
	{
		dataManager d = new dataManager();
		
		try
		{
			Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.setMargins(20, 20, 10, 10);
			document.open();
			
			Chunk c = new Chunk("LA-TIERRA CO-OPERATIVE HOUSING SOCIETY");
			Font f = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			c.setFont(f);
			Paragraph p = new Paragraph(c);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			document.add(new Paragraph(" "));
			
			String s = "Expenditure Voucher";
			Chunk c1 = new Chunk(s);
			c1.setUnderline(0.1f, -2f);
			Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c1.setFont(f1);
			Paragraph p2 = new Paragraph(c1);
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			
			expenditure_info pi = d.getExpenditureById(id);
			
			Paragraph p1 = new Paragraph("Date:   " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            p1.setAlignment(Element.ALIGN_RIGHT);
            document.add(p1);
            
            document.add(new Paragraph(" "));
            Chunk c2 = new Chunk(pi.getAmt().toString());
            Chunk c3 = new Chunk(pi.getHeader().toUpperCase());
            Chunk c4 = new Chunk(pi.getDate());
            c2.setFont(f1);
            c3.setFont(f1);
            c4.setFont(f1);
            Paragraph p3 = new Paragraph("Payment of Rs. " + c2 + " by "+pi.getType().toUpperCase()+" "+pi.getType_detail()+" have been paid to ________________ for the "+c3+" "+pi.getDesc()+" on "+c4);
            document.add(p3);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("For La-Tierra Co-Op. Hsg Soc, Ltd"));
            document.add(new Paragraph("Treasurer/Manager"));
            document.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void incomeVoucher(ByteArrayOutputStream baos,String id)
	{
		dataManager d = new dataManager();
		
		try
		{
			Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.setMargins(20, 20, 10, 10);
			document.open();
			
			Chunk c = new Chunk("LA-TIERRA CO-OPERATIVE HOUSING SOCIETY");
			Font f = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			c.setFont(f);
			Paragraph p = new Paragraph(c);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			document.add(new Paragraph(" "));
			
			String s = "Income Voucher";
			Chunk c1 = new Chunk(s);
			c1.setUnderline(0.1f, -2f);
			Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c1.setFont(f1);
			Paragraph p2 = new Paragraph(c1);
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			
			expenditure_info pi = d.getExpenditureById(id);
			
			Paragraph p1 = new Paragraph("Date:   " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            p1.setAlignment(Element.ALIGN_RIGHT);
            document.add(p1);
            
            document.add(new Paragraph(" "));
            Chunk c2 = new Chunk(pi.getAmt().toString());
            Chunk c3 = new Chunk(pi.getHeader().toUpperCase());
            Chunk c4 = new Chunk(pi.getDate());
            c2.setFont(f1);
            c3.setFont(f1);
            c4.setFont(f1);
            Paragraph p3 = new Paragraph("Income of Rs. " + c2 + " by "+pi.getType().toUpperCase()+" "+pi.getType_detail()+" have been paid by ________________ for the "+c3+" "+pi.getDesc()+" on "+c4);
            document.add(p3);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("For La-Tierra Co-Op. Hsg Soc, Ltd"));
            document.add(new Paragraph("Treasurer/Manager"));
            document.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void advanceReceipt(ByteArrayOutputStream baos,String id,String flatno)
	{
		dataManager d = new dataManager();
		specificflat sf =  d.getSpecificFlatDetails(flatno);
		FlatModel f2 = sf.getFlatInfo();
		advance_table_info ati[] = d.advanceHistory(f2);
		
		Double amt = 0.0;
		Double dis = 0.0;
		String date = "";
		String info = "";
		
		
		for(advance_table_info a:ati)
		{
			if(a.getId().equals(Integer.parseInt(id)))
			{
				amt = a.getAmount();
				dis = a.getDiscount();
				date = a.getDate();
				info = a.getInfo();
				info = removeHTMLTag(info);
			}
		}
		
		
		try
		{
			Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.setMargins(20, 20, 10, 10);
			document.open();
			
			Chunk c = new Chunk("LA-TIERRA CO-OPERATIVE HOUSING SOCIETY");
			Font f = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			c.setFont(f);
			Paragraph p = new Paragraph(c);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			document.add(new Paragraph(" "));
			
			String s = "Advance Receipt";
			Chunk c1 = new Chunk(s);
			c1.setUnderline(0.1f, -2f);
			Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c1.setFont(f1);
			Paragraph p2 = new Paragraph(c1);
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			
			
			Paragraph p1 = new Paragraph("Date:   " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            p1.setAlignment(Element.ALIGN_RIGHT);
            document.add(p1);
            
            document.add(new Paragraph(" "));
            Chunk c2 = new Chunk(amt.toString());
            Chunk c3 = new Chunk(info.toString());
            Chunk c4 = new Chunk(date.toString());
            Chunk c5 = new Chunk(dis.toString());
            c2.setFont(f1);
            c3.setFont(f1);
            c4.setFont(f1);
            c5.setFont(f1);
            Paragraph p3 = new Paragraph("Advance of Rs. " + c2 + " with discount "+ c5 +" have been paid by ________________ of the flatno "+flatno+" on "+c4);
            
            document.add(p3);
            document.add(c3);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("For La-Tierra Co-Op. Hsg Soc, Ltd"));
            document.add(new Paragraph("Treasurer/Manager"));
            document.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void balanceReceipt(ByteArrayOutputStream baos,String id,String flatno)
	{
		dataManager d = new dataManager();
		specificflat sf =  d.getSpecificFlatDetails(flatno);
		FlatModel f2 = sf.getFlatInfo();
		balance_table_info bti[] = d.balanceHistory(f2);
		
		Double amt = 0.0;
		String payment = "";
		String date = "";
		String info = "";
		
		
		for(balance_table_info a:bti)
		{
			if(a.getId().equals(Integer.parseInt(id)))
			{
				amt = a.getBalance_amt();
				info = a.getBalance_info();
				
				date = a.getPayment_date();
				payment = a.getPayment_details();
				payment = removeHTMLTag(payment);
			}
		}
		
		
		try
		{
			Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.setMargins(20, 20, 10, 10);
			document.open();
			
			Chunk c = new Chunk("LA-TIERRA CO-OPERATIVE HOUSING SOCIETY");
			Font f = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			c.setFont(f);
			Paragraph p = new Paragraph(c);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			document.add(new Paragraph(" "));
			
			String s = "Balance Receipt";
			Chunk c1 = new Chunk(s);
			c1.setUnderline(0.1f, -2f);
			Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c1.setFont(f1);
			Paragraph p2 = new Paragraph(c1);
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			
			
			Paragraph p1 = new Paragraph("Date:   " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            p1.setAlignment(Element.ALIGN_RIGHT);
            document.add(p1);
            
            document.add(new Paragraph(" "));
            Chunk c2 = new Chunk(amt.toString());
            Chunk c3 = new Chunk(info.toString());
            Chunk c4 = new Chunk(date.toString());
            Chunk c5 = new Chunk(payment.toString());
            c2.setFont(f1);
            c3.setFont(f1);
            c4.setFont(f1);
            c5.setFont(f1);
            Paragraph p3 = new Paragraph("Balance of Rs. " + c2 + " have been paid by ________________ of the flatno "+flatno+" on "+c4);
            
            document.add(p3);
            document.add(c3);
            document.add(new Paragraph("Payment Details: "));
            document.add(c5);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("For La-Tierra Co-Op. Hsg Soc, Ltd"));
            document.add(new Paragraph("Treasurer/Manager"));
            document.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String removeHTMLTag(String info)
	{
		//info.replace("", replacement)info = 
		info = info.replace("<b>", "");
		info = info.replace("</b>", "");
		info = info.replace("<br>", "");
		System.out.println(info);
		return info;
	}
	
	
	
	public static void flatMonthMaint(ByteArrayOutputStream baos,String flatno,String month,String year)
	{
		dataManager d = new dataManager();
		
		LinkedList<maintenance_model> ll = d.getMaintenanceReport(flatno, Integer.parseInt(month), Integer.parseInt(year), 0, "","");
		
		Double maint_charge = 0.0;
		Double sink_charge = 0.0;
		Double occp_charge = 0.0;
		Double adv_payment = 0.0;
		Double prev_bal = 0.0;
		Double interest = 0.0;
		Double total_amt = 0.0;
		
		if(ll!=null && !ll.isEmpty())
		{
			maintenance_model m = ll.removeFirst();
			maint_charge = m.getMaint_charge();
			sink_charge = m.getSink_charge();
			occp_charge = m.getOccp_charge();
			adv_payment = m.getAdv_payment();
			prev_bal = m.getPrev_balance();
			interest = database.getDatabase().getPrevInterest(m.getFlatid(), Integer.parseInt(month), Integer.parseInt(year));
			total_amt = maint_charge+sink_charge+occp_charge+prev_bal+interest;
		}
		
		HashMap<Integer,String> months = new HashMap<Integer,String>();
    	
    	months.put(1, "January");
    	months.put(2, "February");
    	months.put(3, "March");
    	months.put(4, "April");
    	months.put(5, "May");
    	months.put(6, "June");
    	months.put(7, "July");
    	months.put(8, "August");
    	months.put(9, "September");
    	months.put(10, "October");
    	months.put(11, "November");
    	months.put(12, "December");
    	
    	try
    	{
    		
    		Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.setMargins(20, 20, 10, 10);
			
            document.open();
          
            Image img = Image.getInstance("C:/Users/Owner/SUBAM KUMAR/society/latierra.png");
            img.scaleAbsolute(530, 100);
            img.setAlignment(Element.ALIGN_CENTER);
            //img.setAbsolutePosition(35, 730);
            document.add(img);
            
            Paragraph p = new Paragraph("Bill Generation Date:   " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            p.setAlignment(Element.ALIGN_RIGHT);
            document.add(p);
            
            Chunk underline = new Chunk("Maintenance Bill");
            underline.setUnderline(0.1f, -2f);
            Font f = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            underline.setFont(f);
            Paragraph p1 = new Paragraph();
            p1.add(underline);
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);
            
            document.add(new Paragraph(" "));
            
            FlatModel flat = database.getDatabase().getFlatByNumber(flatno);
            
            PdfPTable table = new PdfPTable(2);
            
            PdfPCell cell = new PdfPCell(new Phrase("Name"));
            cell.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell1 = new PdfPCell(new Phrase(": "+flat.getOwnerName()));
            cell1.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell2 = new PdfPCell(new Phrase("Flat No"));
            cell2.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell3 = new PdfPCell(new Phrase(": "+flat.getFlatNo().toUpperCase()));
            cell3.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell4 = new PdfPCell(new Phrase("Flat Area"));
            cell4.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell5 = new PdfPCell(new Phrase(": "+flat.getArea()+"sqft"));
            cell5.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell6 = new PdfPCell(new Phrase("Category"));
            cell6.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell7 = new PdfPCell(new Phrase(": "+flat.getOwnerCategory().toUpperCase()));
            cell7.setBorder(Rectangle.NO_BORDER);
            
            
            table.addCell(cell);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            table.addCell(cell7);
            float[] columnWidths = new float[] {2f, 10f};
            table.setWidths(columnWidths);
            
            document.add(table);
            
            document.add(new Paragraph("Dear Sir/Madam."));
            
            Chunk cmonth = new Chunk(months.get(Integer.parseInt(month))+" "+year);
            cmonth.setUnderline(0.1f, -2f);
            cmonth.setFont(new Font(Font.FontFamily.TIMES_ROMAN,Font.BOLD));
            
            Paragraph p2 = new Paragraph("The details of society maintenance charges for the month "+cmonth+" are as follows:-");
            p2.setIndentationLeft(20);
        
            document.add(p2);
            
            document.add(new Paragraph(" "));
            
            PdfPTable table1 = new PdfPTable(3);
            
            Font f1 = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
            
            Paragraph ph1 = new Paragraph("S.No");
            ph1.setFont(f1);
            Paragraph ph2 = new Paragraph("Particular");
            ph2.setFont(f1);
            Paragraph ph3 = new Paragraph("Amount");
            ph3.setFont(f1);
            
            table1.addCell(ph1);
            table1.addCell(ph2);
            table1.addCell(ph3);
            //1
            table1.addCell("1");
            table1.addCell("Previous Balance"); 
            //ArrayList<maint_model> mmaint_list  = database.getDatabase().showMentenanceByFlatForAMonth(flatno, month, year);
            //maint_model mmaint = mmaint_list.remove(mmaint_list.size()-1);
            table1.addCell(String.valueOf(prev_bal));
            //2
            table1.addCell("2");
            table1.addCell("Maintenance Charge");
            //Double maint_charge = database.getDatabase().getMaintCharge(month, year);
            table1.addCell(String.valueOf(maint_charge));
            //3
            table1.addCell("3");
            table1.addCell("Sinking Fund");
            //sinkingModel sm = database.getDatabase().getSinkingChargeAndPercentage(month, year);
            //Double charge = (sm.getCharge()*Integer.parseInt(flat.getArea())*0.25)/1200;
            table1.addCell(String.valueOf(Math.round(sink_charge)));
            //4
            table1.addCell("4");
            table1.addCell("Non-Occupancy Charge");	
            table1.addCell(String.valueOf(Math.round(occp_charge)));
         
            table1.addCell("5");
            table1.addCell("Late fees Interest");
            table1.addCell(String.valueOf(interest));
            
            table1.addCell(" ");
            table1.addCell("Total Amount");
            table1.addCell(String.valueOf(total_amt));
            
            table1.addCell(" ");
            table1.addCell("Advance Payment");
            table1.addCell(String.valueOf(adv_payment));
            
            table1.addCell(" ");
            table1.addCell("Net Amount");
            table1.addCell(String.valueOf(total_amt-adv_payment));
            
            float[] columnWidths1 = new float[] {3f, 20f, 10f};
            table1.setWidths(columnWidths1);
            
            document.add(table1);
            
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Notes:-  "));
            
            Paragraph p3;
            
            p3 = new Paragraph("• Payment can be made by cheque/demand draft/NEFT to the society Accounts.");
            p3.setIndentationLeft(20);
            document.add(p3);
            
            p3 = new Paragraph("• Cheque in favour of La-Tierra Co-Operative Housing Society Ltd.");
            p3.setIndentationLeft(20);
            document.add(p3);
            
            p3 = new Paragraph("• Accounts details are as bellow:-");
            p3.setIndentationLeft(20);
            document.add(p3);
            
            document.add(new Paragraph(" "));
            
            PdfPTable table3 = new PdfPTable(3);
            
            table3.addCell("Bank Name");
            table3.addCell("Account Number");
            table3.addCell("IFSC Code");
            table3.addCell("Bank Of India");
            table3.addCell("053910110011263");
            table3.addCell("BKID0000539");
            
            document.add(table3);
            
            p3 = new Paragraph("• Request to pay this bill in full in the month of "+cmonth+", to avoid further penal interest and further action by the society as per rules.");
            p3.setIndentationLeft(20);
            document.add(p3);
            p3 = new Paragraph("• TENANTS are requested to coordinate with your owner to pay the bill in time.");
            p3.setIndentationLeft(20);
            document.add(p3);
            p3 = new Paragraph("• Kindly inform the La-Tierra CHS Ltd. Office in case of any discrepancy to enable timely updating of records.");
            p3.setIndentationLeft(20);
            document.add(p3);
            p3 = new Paragraph("• The society makes expenses for various amenities (viz-Lift, Security services, Staff payment, Electricity bill Housekeeping, Garden maintenance, GYM and other expenses) from these maintenance receipts. Delay in payment may result in discontinuation of certain amenities.");
            p3.setIndentationLeft(20);
            document.add(p3);
            p3 = new Paragraph("• Please ignore if already paid.");
            p3.setIndentationLeft(20);
            document.add(p3);
            
            document.add(new Paragraph(" "));
            
            document.add(new Paragraph("For La-Tierra Co-Op. Hsg Soc, Ltd"));
            document.add(new Paragraph("Treasurer/Manager"));
            document.add(new Paragraph("(This is Computer Generated bill hence no signature required)"));
            
            PdfContentByte canvas = writer.getDirectContent();
            Rectangle rect = new Rectangle(15, 15, 580, 830);
            rect.setBorder(Rectangle.BOX);
            rect.setBorderWidth(2);
            canvas.rectangle(rect);
            
            
            document.close();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
		
		
	}
	
	public static void flatQuarterMaint(ByteArrayOutputStream baos,String flatno,String quarter,String year)
	{
		dataManager d = new dataManager();
		
		LinkedList<maintenance_model> ll = d.getMaintenanceReport(flatno, 0, Integer.parseInt(year), Integer.parseInt(quarter), "","");
		
		Double maint_charge = 0.0;
		Double sink_charge = 0.0;
		Double occp_charge = 0.0;
		Double adv_payment = 0.0;
		Double prev_bal = 0.0;
		Double interest = 0.0;
		Double total_amt = 0.0;
		
		if(ll!=null && !ll.isEmpty())
		{
			maintenance_model m = ll.removeFirst();
			maint_charge = m.getMaint_charge();
			sink_charge = m.getSink_charge();
			occp_charge = m.getOccp_charge();
			
			
			if(quarter.equals("1"))
			{
				interest = database.getDatabase().getPrevInterest(m.getFlatid(), 12, Integer.parseInt(year));
				prev_bal = database.getDatabase().getPrevBalanceByMonth(m.getFlatid(), 1, Integer.parseInt(year));
				adv_payment = database.getDatabase().getAdvancePaymentPDF(m.getFlatid(), 1, Integer.parseInt(year));
			}
			else if(quarter.equals("2"))
			{
				interest = database.getDatabase().getPrevInterest(m.getFlatid(), 3, Integer.parseInt(year));
				prev_bal = database.getDatabase().getPrevBalanceByMonth(m.getFlatid(), 4, Integer.parseInt(year));
				adv_payment = database.getDatabase().getAdvancePaymentPDF(m.getFlatid(), 4, Integer.parseInt(year));
			}
			else if(quarter.equals("3"))
			{	
				interest = database.getDatabase().getPrevInterest(m.getFlatid(), 6, Integer.parseInt(year));
				prev_bal = database.getDatabase().getPrevBalanceByMonth(m.getFlatid(), 7, Integer.parseInt(year));
				adv_payment = database.getDatabase().getAdvancePaymentPDF(m.getFlatid(), 7, Integer.parseInt(year));
			}
			else if(quarter.equals("4"))
			{
				interest = database.getDatabase().getPrevInterest(m.getFlatid(), 9, Integer.parseInt(year));
				prev_bal = database.getDatabase().getPrevBalanceByMonth(m.getFlatid(), 10, Integer.parseInt(year));
				adv_payment = database.getDatabase().getAdvancePaymentPDF(m.getFlatid(), 10, Integer.parseInt(year));
			}
			
			total_amt = maint_charge*3 +sink_charge*3 +occp_charge*3 +prev_bal+interest;
		}
		
		HashMap<Integer,String> months = new HashMap<Integer,String>();
    	
    	months.put(1, "January");
    	months.put(2, "February");
    	months.put(3, "March");
    	months.put(4, "April");
    	months.put(5, "May");
    	months.put(6, "June");
    	months.put(7, "July");
    	months.put(8, "August");
    	months.put(9, "September");
    	months.put(10, "October");
    	months.put(11, "November");
    	months.put(12, "December");
    	
    	try
    	{
    		
    		Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.setMargins(20, 20, 10, 10);
			
            document.open();
          
            Image img = Image.getInstance("C:/Users/Owner/SUBAM KUMAR/society/latierra.png");
            img.scaleAbsolute(530, 100);
            img.setAlignment(Element.ALIGN_CENTER);
            //img.setAbsolutePosition(35, 730);
            document.add(img);
            
            Paragraph p = new Paragraph("Bill Generation Date:   " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            p.setAlignment(Element.ALIGN_RIGHT);
            document.add(p);
            
            Chunk underline = new Chunk("Maintenance Bill");
            underline.setUnderline(0.1f, -2f);
            Font f = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            underline.setFont(f);
            Paragraph p1 = new Paragraph();
            p1.add(underline);
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);
            
            document.add(new Paragraph(" "));
            
            FlatModel flat = database.getDatabase().getFlatByNumber(flatno);
            
            PdfPTable table = new PdfPTable(2);
            
            PdfPCell cell = new PdfPCell(new Phrase("Name"));
            cell.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell1 = new PdfPCell(new Phrase(": "+flat.getOwnerName()));
            cell1.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell2 = new PdfPCell(new Phrase("Flat No"));
            cell2.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell3 = new PdfPCell(new Phrase(": "+flat.getFlatNo().toUpperCase()));
            cell3.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell4 = new PdfPCell(new Phrase("Flat Area"));
            cell4.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell5 = new PdfPCell(new Phrase(": "+flat.getArea()+"sqft"));
            cell5.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell6 = new PdfPCell(new Phrase("Category"));
            cell6.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell7 = new PdfPCell(new Phrase(": "+flat.getOwnerCategory().toUpperCase()));
            cell7.setBorder(Rectangle.NO_BORDER);
            
            
            table.addCell(cell);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            table.addCell(cell7);
            float[] columnWidths = new float[] {2f, 10f};
            table.setWidths(columnWidths);
            
            document.add(table);
            
            document.add(new Paragraph("Dear Sir/Madam."));
            
            String qstring = "";
            
            if(quarter.equals("1"))
            	qstring = "JANUARY-MARCH";
            else if(quarter.equals("1"))
            	qstring = "APRIL-JUNE";
            else if(quarter.equals("1"))
            	qstring = "JULY-SEPTEMBER";
            else if(quarter.equals("1"))
            	qstring = "OCTOBER-DECEMBER";
            
            Chunk cmonth = new Chunk(qstring+" "+year);
            cmonth.setUnderline(0.1f, -2f);
            cmonth.setFont(new Font(Font.FontFamily.TIMES_ROMAN,Font.BOLD));
            
            
            
            Paragraph p2 = new Paragraph("The details of society maintenance charges for the Quarter "+quarter+" ("+cmonth+") are as follows:-");
            p2.setIndentationLeft(20);
        
            document.add(p2);
            
            document.add(new Paragraph(" "));
            
            PdfPTable table1 = new PdfPTable(3);
            
            Font f1 = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
            
            Paragraph ph1 = new Paragraph("S.No");
            ph1.setFont(f1);
            Paragraph ph2 = new Paragraph("Particular");
            ph2.setFont(f1);
            Paragraph ph3 = new Paragraph("Amount");
            ph3.setFont(f1);
            
            table1.addCell(ph1);
            table1.addCell(ph2);
            table1.addCell(ph3);
            //1
            table1.addCell("1");
            table1.addCell("Previous Balance"); 
            //ArrayList<maint_model> mmaint_list  = database.getDatabase().showMentenanceByFlatForAMonth(flatno, month, year);
            //maint_model mmaint = mmaint_list.remove(mmaint_list.size()-1);
            table1.addCell(String.valueOf(prev_bal));
            //2
            table1.addCell("2");
            table1.addCell("Maintenance Charge");
            //Double maint_charge = database.getDatabase().getMaintCharge(month, year);
            table1.addCell(String.valueOf(Math.round(maint_charge*3)));
            //3
            table1.addCell("3");
            table1.addCell("Sinking Fund");
            //sinkingModel sm = database.getDatabase().getSinkingChargeAndPercentage(month, year);
            //Double charge = (sm.getCharge()*Integer.parseInt(flat.getArea())*0.25)/1200;
            table1.addCell(String.valueOf(Math.round(sink_charge*3)));
            //4
            table1.addCell("4");
            table1.addCell("Non-Occupancy Charge");	
            table1.addCell(String.valueOf(Math.round(occp_charge*3)));
         
            table1.addCell("5");
            table1.addCell("Late fees Interest");
            table1.addCell(String.valueOf(interest));
            
            table1.addCell(" ");
            table1.addCell("Total Amount");
            table1.addCell(String.valueOf(total_amt));
            
            //table1.addCell(" ");
            //table1.addCell("Advance Payment");
            //table1.addCell(String.valueOf(adv_payment));
            
            //table1.addCell(" ");
            //table1.addCell("Net Amount");
            //table1.addCell(String.valueOf(total_amt-adv_payment));
            
            float[] columnWidths1 = new float[] {3f, 20f, 10f};
            table1.setWidths(columnWidths1);
            
            document.add(table1);
            
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Notes:-  "));
            
            Paragraph p3;
            
            p3 = new Paragraph("• Payment can be made by cheque/demand draft/NEFT to the society Accounts.");
            p3.setIndentationLeft(20);
            document.add(p3);
            
            p3 = new Paragraph("• Cheque in favour of La-Tierra Co-Operative Housing Society Ltd.");
            p3.setIndentationLeft(20);
            document.add(p3);
            
            p3 = new Paragraph("• Accounts details are as bellow:-");
            p3.setIndentationLeft(20);
            document.add(p3);
            
            document.add(new Paragraph(" "));
            
            PdfPTable table3 = new PdfPTable(3);
            
            table3.addCell("Bank Name");
            table3.addCell("Account Number");
            table3.addCell("IFSC Code");
            table3.addCell("Bank Of India");
            table3.addCell("053910110011263");
            table3.addCell("BKID0000539");
            
            document.add(table3);
            
            p3 = new Paragraph("• Request to pay this bill in full in the quarter of "+cmonth+", to avoid further penal interest and further action by the society as per rules.");
            p3.setIndentationLeft(20);
            document.add(p3);
            p3 = new Paragraph("• TENANTS are requested to coordinate with your owner to pay the bill in time.");
            p3.setIndentationLeft(20);
            document.add(p3);
            p3 = new Paragraph("• Kindly inform the La-Tierra CHS Ltd. Office in case of any discrepancy to enable timely updating of records.");
            p3.setIndentationLeft(20);
            document.add(p3);
            p3 = new Paragraph("• The society makes expenses for various amenities (viz-Lift, Security services, Staff payment, Electricity bill Housekeeping, Garden maintenance, GYM and other expenses) from these maintenance receipts. Delay in payment may result in discontinuation of certain amenities.");
            p3.setIndentationLeft(20);
            document.add(p3);
            p3 = new Paragraph("• Please ignore if already paid.");
            p3.setIndentationLeft(20);
            document.add(p3);
            
            document.add(new Paragraph(" "));
            
            document.add(new Paragraph("For La-Tierra Co-Op. Hsg Soc, Ltd"));
            document.add(new Paragraph("Treasurer/Manager"));
            document.add(new Paragraph("(This is Computer Generated bill hence no signature required)"));
            
            PdfContentByte canvas = writer.getDirectContent();
            Rectangle rect = new Rectangle(15, 15, 580, 830);
            rect.setBorder(Rectangle.BOX);
            rect.setBorderWidth(2);
            canvas.rectangle(rect);
            
            
            document.close();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
	
	public static void allFlatMonthMaint(ByteArrayOutputStream baos,String month,String year,String block,String owner)
	{
		
		HashMap<Integer,String> months = new HashMap<Integer,String>();
    	
    	months.put(1, "January");
    	months.put(2, "February");
    	months.put(3, "March");
    	months.put(4, "April");
    	months.put(5, "May");
    	months.put(6, "June");
    	months.put(7, "July");
    	months.put(8, "August");
    	months.put(9, "September");
    	months.put(10, "October");
    	months.put(11, "November");
    	months.put(12, "December");
    	
    	try
    	{
    		
    		Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.setMargins(20, 20, 10, 10);
			
            document.open();
            
            dataManager d = new dataManager();
    		
            
    		LinkedList<maintenance_model> ll = d.getMaintenanceReport("", Integer.parseInt(month), Integer.parseInt(year), 0, block,owner);
    		
    		while(!ll.isEmpty())
    		{
    			maintenance_model m = ll.removeFirst();
    			Double maint_charge = 0.0;
    			Double sink_charge = 0.0;
    			Double occp_charge = 0.0;
    			Double adv_payment = 0.0;
    			Double prev_bal = 0.0;
    			Double interest = 0.0;
    			Double total_amt = 0.0;
    		
    		if(ll!=null && !ll.isEmpty())
    		{
    			
    			maint_charge = m.getMaint_charge();
    			sink_charge = m.getSink_charge();
    			occp_charge = m.getOccp_charge();
    			adv_payment = m.getAdv_payment();
    			prev_bal = m.getPrev_balance();
    			interest = database.getDatabase().getPrevInterest(m.getFlatid(), Integer.parseInt(month), Integer.parseInt(year));
    			total_amt = maint_charge+sink_charge+occp_charge+prev_bal+interest;
    		}
            
            
            Image img = Image.getInstance("C:/Users/Owner/SUBAM KUMAR/society/latierra.png");
            img.scaleAbsolute(530, 100);
            img.setAlignment(Element.ALIGN_CENTER);
            //img.setAbsolutePosition(35, 730);
            document.add(img);
            
            Paragraph p = new Paragraph("Bill Generation Date:   " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            p.setAlignment(Element.ALIGN_RIGHT);
            document.add(p);
            
            Chunk underline = new Chunk("Maintenance Bill");
            underline.setUnderline(0.1f, -2f);
            Font f = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            underline.setFont(f);
            Paragraph p1 = new Paragraph();
            p1.add(underline);
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);
            
            document.add(new Paragraph(" "));
            
            FlatModel flat = d.getFlatByID(String.valueOf(m.getFlatid()));
            
            PdfPTable table = new PdfPTable(2);
            
            PdfPCell cell = new PdfPCell(new Phrase("Name"));
            cell.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell1 = new PdfPCell(new Phrase(": "+flat.getOwnerName()));
            cell1.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell2 = new PdfPCell(new Phrase("Flat No"));
            cell2.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell3 = new PdfPCell(new Phrase(": "+flat.getFlatNo().toUpperCase()));
            cell3.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell4 = new PdfPCell(new Phrase("Flat Area"));
            cell4.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell5 = new PdfPCell(new Phrase(": "+flat.getArea()+"sqft"));
            cell5.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell6 = new PdfPCell(new Phrase("Category"));
            cell6.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell7 = new PdfPCell(new Phrase(": "+flat.getOwnerCategory().toUpperCase()));
            cell7.setBorder(Rectangle.NO_BORDER);
            
            
            table.addCell(cell);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            table.addCell(cell7);
            float[] columnWidths = new float[] {2f, 10f};
            table.setWidths(columnWidths);
            
            document.add(table);
            
            document.add(new Paragraph("Dear Sir/Madam."));
            
            Chunk cmonth = new Chunk(months.get(Integer.parseInt(month))+" "+year);
            cmonth.setUnderline(0.1f, -2f);
            cmonth.setFont(new Font(Font.FontFamily.TIMES_ROMAN,Font.BOLD));
            
            Paragraph p2 = new Paragraph("The details of society maintenance charges for the month "+cmonth+" are as follows:-");
            p2.setIndentationLeft(20);
        
            document.add(p2);
            
            document.add(new Paragraph(" "));
            
            PdfPTable table1 = new PdfPTable(3);
            
            Font f1 = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
            
            Paragraph ph1 = new Paragraph("S.No");
            ph1.setFont(f1);
            Paragraph ph2 = new Paragraph("Particular");
            ph2.setFont(f1);
            Paragraph ph3 = new Paragraph("Amount");
            ph3.setFont(f1);
            
            table1.addCell(ph1);
            table1.addCell(ph2);
            table1.addCell(ph3);
            //1
            table1.addCell("1");
            table1.addCell("Previous Balance"); 
            //ArrayList<maint_model> mmaint_list  = database.getDatabase().showMentenanceByFlatForAMonth(flatno, month, year);
            //maint_model mmaint = mmaint_list.remove(mmaint_list.size()-1);
            table1.addCell(String.valueOf(prev_bal));
            //2
            table1.addCell("2");
            table1.addCell("Maintenance Charge");
            //Double maint_charge = database.getDatabase().getMaintCharge(month, year);
            table1.addCell(String.valueOf(maint_charge));
            //3
            table1.addCell("3");
            table1.addCell("Sinking Fund");
            //sinkingModel sm = database.getDatabase().getSinkingChargeAndPercentage(month, year);
            //Double charge = (sm.getCharge()*Integer.parseInt(flat.getArea())*0.25)/1200;
            table1.addCell(String.valueOf(Math.round(sink_charge)));
            //4
            table1.addCell("4");
            table1.addCell("Non-Occupancy Charge");	
            table1.addCell(String.valueOf(Math.round(occp_charge)));
         
            table1.addCell("5");
            table1.addCell("Late fees Interest");
            table1.addCell(String.valueOf(interest));
            
            table1.addCell(" ");
            table1.addCell("Total Amount");
            table1.addCell(String.valueOf(total_amt));
            
            table1.addCell(" ");
            table1.addCell("Advance Payment");
            table1.addCell(String.valueOf(adv_payment));
            
            table1.addCell(" ");
            table1.addCell("Net Amount");
            table1.addCell(String.valueOf(total_amt-adv_payment));
            
            float[] columnWidths1 = new float[] {3f, 20f, 10f};
            table1.setWidths(columnWidths1);
            
            document.add(table1);
            
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Notes:-  "));
            
            Paragraph p3;
            
            p3 = new Paragraph("• Payment can be made by cheque/demand draft/NEFT to the society Accounts.");
            p3.setIndentationLeft(20);
            document.add(p3);
            
            p3 = new Paragraph("• Cheque in favour of La-Tierra Co-Operative Housing Society Ltd.");
            p3.setIndentationLeft(20);
            document.add(p3);
            
            p3 = new Paragraph("• Accounts details are as bellow:-");
            p3.setIndentationLeft(20);
            document.add(p3);
            
            document.add(new Paragraph(" "));
            
            PdfPTable table3 = new PdfPTable(3);
            
            table3.addCell("Bank Name");
            table3.addCell("Account Number");
            table3.addCell("IFSC Code");
            table3.addCell("Bank Of India");
            table3.addCell("053910110011263");
            table3.addCell("BKID0000539");
            
            document.add(table3);
            
            p3 = new Paragraph("• Request to pay this bill in full in the month of "+cmonth+", to avoid further penal interest and further action by the society as per rules.");
            p3.setIndentationLeft(20);
            document.add(p3);
            p3 = new Paragraph("• TENANTS are requested to coordinate with your owner to pay the bill in time.");
            p3.setIndentationLeft(20);
            document.add(p3);
            p3 = new Paragraph("• Kindly inform the La-Tierra CHS Ltd. Office in case of any discrepancy to enable timely updating of records.");
            p3.setIndentationLeft(20);
            document.add(p3);
            p3 = new Paragraph("• The society makes expenses for various amenities (viz-Lift, Security services, Staff payment, Electricity bill Housekeeping, Garden maintenance, GYM and other expenses) from these maintenance receipts. Delay in payment may result in discontinuation of certain amenities.");
            p3.setIndentationLeft(20);
            document.add(p3);
            p3 = new Paragraph("• Please ignore if already paid.");
            p3.setIndentationLeft(20);
            document.add(p3);
            
            document.add(new Paragraph(" "));
            
            document.add(new Paragraph("For La-Tierra Co-Op. Hsg Soc, Ltd"));
            document.add(new Paragraph("Treasurer/Manager"));
            document.add(new Paragraph("(This is Computer Generated bill hence no signature required)"));
            
            PdfContentByte canvas = writer.getDirectContent();
            Rectangle rect = new Rectangle(15, 15, 580, 830);
            rect.setBorder(Rectangle.BOX);
            rect.setBorderWidth(2);
            canvas.rectangle(rect);
            
            document.newPage();
    		}
            
            document.close();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
	
	public static void allFlatMonthReport(ByteArrayOutputStream baos,String month,String year,String block,String owner)
	{
		HashMap<Integer,String> months = new HashMap<Integer,String>();
    	
    	months.put(1, "January");
    	months.put(2, "February");
    	months.put(3, "March");
    	months.put(4, "April");
    	months.put(5, "May");
    	months.put(6, "June");
    	months.put(7, "July");
    	months.put(8, "August");
    	months.put(9, "September");
    	months.put(10, "October");
    	months.put(11, "November");
    	months.put(12, "December");
		
		dataManager d = new dataManager();
		LinkedList<maintenance_model> ll = d.getMaintenanceReport("", Integer.parseInt(month),Integer.parseInt(year), 0, block, owner);
		
		try
		{
			Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.setMargins(20, 20, 10, 10);
			
            document.open();
            
            
            Chunk c = new Chunk("LA-TIERRA CO-OPERATIVE HOUSING SOCIETY");
			Font f = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			c.setFont(f);
			Paragraph p = new Paragraph(c);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			document.add(new Paragraph(" "));
			
			String s = months.get(Integer.parseInt(month))+" "+year;
			Chunk c1 = new Chunk(s);
			c1.setUnderline(0.1f, -2f);
			Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c1.setFont(f1);
			Paragraph p2 = new Paragraph(c1);
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			
			String s11 = block.toUpperCase()+" BLOCK FLATS "+owner.toUpperCase()+" OWNERS";
			Chunk c11 = new Chunk(s11);
			c11.setUnderline(0.1f, -2f);
			Font f11 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c11.setFont(f11);
			Paragraph p21 = new Paragraph(c11);
			p21.setAlignment(Element.ALIGN_CENTER);
			document.add(p21);
			
			
			
			Paragraph p1 = new Paragraph("Date:   " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            p1.setAlignment(Element.ALIGN_RIGHT);
            document.add(p1);
            
            document.add(new Paragraph(" "));
            
            PdfPTable table = new PdfPTable(5);
            
            Font f2 = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
            
            Paragraph ph1 = new Paragraph("Sr No.");
            ph1.setFont(f2);
            Paragraph ph2 = new Paragraph("Flat No");
            ph2.setFont(f2);
            Paragraph ph3 = new Paragraph("Total Amount");
            ph3.setFont(f2);
            Paragraph ph4 = new Paragraph("Previous Balance");
            ph4.setFont(f2);
            Paragraph ph5 = new Paragraph("Paid Status");
            ph5.setFont(f2);
            
            
            table.addCell(ph1);
            table.addCell(ph2);
            table.addCell(ph3);
            table.addCell(ph4);
            table.addCell(ph5);
            //table.addCell(ph6);
            
            //LinkedList<income> ll = d.getIncome(month, year);
            int count = 0;
            while(!ll.isEmpty())
            {
            	maintenance_model m = ll.removeFirst();
            	count++;     	
            	Paragraph ph7 = new Paragraph(String.valueOf(count));
            	String fno = d.getFlatByID(m.getFlatid().toString()).getFlatNo().toUpperCase();
            	Paragraph ph8 = new Paragraph(fno);
            	Double total = m.getMaint_charge()+m.getOccp_charge()+m.getSink_charge();
            	Paragraph ph9 = new Paragraph(total.toString());
            	Double prevBalance = m.getPrev_balance();
            	Paragraph ph10 = new Paragraph(prevBalance.toString());
            	String status = "";
				
				if(m.getStatus() == 1)
				{
					status = "PAID";
				}
				else
				{
					status = "NOT PAID";
				}
            	Paragraph ph11 = new Paragraph(status);
            	//Paragraph ph12 = new Paragraph(String.valueOf(i.getAmt()));
            	
            	table.addCell(ph7);
            	table.addCell(ph8);
            	table.addCell(ph9);
            	table.addCell(ph10);
            	table.addCell(ph11);
            	//table.addCell(ph12);
            	float[] columnWidths = new float[] {1f,8f,3f,3f,4f};
	            table.setWidths(columnWidths);
            }
            
            table.setTotalWidth(PageSize.A4.getWidth()-20);
            table.setLockedWidth(true);
            document.add(table);

            document.close();
            
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void allFlatQuarterMaint(ByteArrayOutputStream baos,String quarter,String year,String block,String owner)
	{
		
		HashMap<Integer,String> months = new HashMap<Integer,String>();
    	
    	months.put(1, "January");
    	months.put(2, "February");
    	months.put(3, "March");
    	months.put(4, "April");
    	months.put(5, "May");
    	months.put(6, "June");
    	months.put(7, "July");
    	months.put(8, "August");
    	months.put(9, "September");
    	months.put(10, "October");
    	months.put(11, "November");
    	months.put(12, "December");
    	
    	try
    	{
    		
    		Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.setMargins(20, 20, 10, 10);
			
            document.open();
            
            dataManager d = new dataManager();
    		
            LinkedList<sortall> ll1 = d.getAllFlatsDetail();
            
            while(!ll1.isEmpty())
    		{
            System.out.println("Flat size: "+ll1.size());
            sortall sll = ll1.removeFirst();
            
    		LinkedList<maintenance_model> ll = d.getMaintenanceReport(sll.getFlatno(), 0, Integer.parseInt(year), Integer.parseInt(quarter), block,owner);
    		
    		maintenance_model m = ll.removeFirst();
    		Double maint_charge = 0.0;
    		Double sink_charge = 0.0;
    		Double occp_charge = 0.0;
    		Double adv_payment = 0.0;
    		Double prev_bal = 0.0;
    		Double interest = 0.0;
    		Double total_amt = 0.0;
    		
    		if(ll!=null && !ll.isEmpty())
    		{
    			
    			maint_charge = m.getMaint_charge();
    			sink_charge = m.getSink_charge();
    			occp_charge = m.getOccp_charge();
    			
    			
    			if(quarter.equals("1"))
    			{
    				interest = database.getDatabase().getPrevInterest(m.getFlatid(), 12, Integer.parseInt(year));
    				prev_bal = database.getDatabase().getPrevBalanceByMonth(m.getFlatid(), 1, Integer.parseInt(year));
    				adv_payment = database.getDatabase().getAdvancePaymentPDF(m.getFlatid(), 1, Integer.parseInt(year));
    			}
    			else if(quarter.equals("2"))
    			{
    				interest = database.getDatabase().getPrevInterest(m.getFlatid(), 3, Integer.parseInt(year));
    				prev_bal = database.getDatabase().getPrevBalanceByMonth(m.getFlatid(), 4, Integer.parseInt(year));
    				adv_payment = database.getDatabase().getAdvancePaymentPDF(m.getFlatid(), 4, Integer.parseInt(year));
    			}
    			else if(quarter.equals("3"))
    			{	
    				interest = database.getDatabase().getPrevInterest(m.getFlatid(), 6, Integer.parseInt(year));
    				prev_bal = database.getDatabase().getPrevBalanceByMonth(m.getFlatid(), 7, Integer.parseInt(year));
    				adv_payment = database.getDatabase().getAdvancePaymentPDF(m.getFlatid(), 7, Integer.parseInt(year));
    			}
    			else if(quarter.equals("4"))
    			{
    				interest = database.getDatabase().getPrevInterest(m.getFlatid(), 9, Integer.parseInt(year));
    				prev_bal = database.getDatabase().getPrevBalanceByMonth(m.getFlatid(), 10, Integer.parseInt(year));
    				adv_payment = database.getDatabase().getAdvancePaymentPDF(m.getFlatid(), 10, Integer.parseInt(year));
    			}
    			
    			total_amt = maint_charge*3 +sink_charge*3 +occp_charge*3 +prev_bal+interest;
    		}
            
          
            Image img = Image.getInstance("C:/Users/Owner/SUBAM KUMAR/society/latierra.png");
            img.scaleAbsolute(530, 100);
            img.setAlignment(Element.ALIGN_CENTER);
            //img.setAbsolutePosition(35, 730);
            document.add(img);
            
            Paragraph p = new Paragraph("Bill Generation Date:   " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            p.setAlignment(Element.ALIGN_RIGHT);
            document.add(p);
            
            Chunk underline = new Chunk("Maintenance Bill");
            underline.setUnderline(0.1f, -2f);
            Font f = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            underline.setFont(f);
            Paragraph p1 = new Paragraph();
            p1.add(underline);
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);
            
            document.add(new Paragraph(" "));
            
            FlatModel flat = d.getFlatByID(String.valueOf(m.getFlatid()));
            
            PdfPTable table = new PdfPTable(2);
            
            PdfPCell cell = new PdfPCell(new Phrase("Name"));
            cell.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell1 = new PdfPCell(new Phrase(": "+flat.getOwnerName()));
            cell1.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell2 = new PdfPCell(new Phrase("Flat No"));
            cell2.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell3 = new PdfPCell(new Phrase(": "+flat.getFlatNo().toUpperCase()));
            cell3.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell4 = new PdfPCell(new Phrase("Flat Area"));
            cell4.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell5 = new PdfPCell(new Phrase(": "+flat.getArea()+"sqft"));
            cell5.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell6 = new PdfPCell(new Phrase("Category"));
            cell6.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell7 = new PdfPCell(new Phrase(": "+flat.getOwnerCategory().toUpperCase()));
            cell7.setBorder(Rectangle.NO_BORDER);
            
            
            table.addCell(cell);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            table.addCell(cell7);
            float[] columnWidths = new float[] {2f, 10f};
            table.setWidths(columnWidths);
            
            document.add(table);
            
            document.add(new Paragraph("Dear Sir/Madam."));
            
            String qstring = "";
            
            if(quarter.equals("1"))
            	qstring = "JANUARY-MARCH";
            else if(quarter.equals("1"))
            	qstring = "APRIL-JUNE";
            else if(quarter.equals("1"))
            	qstring = "JULY-SEPTEMBER";
            else if(quarter.equals("1"))
            	qstring = "OCTOBER-DECEMBER";
            
            Chunk cmonth = new Chunk(qstring+" "+year);
            cmonth.setUnderline(0.1f, -2f);
            cmonth.setFont(new Font(Font.FontFamily.TIMES_ROMAN,Font.BOLD));
            
            
            
            Paragraph p2 = new Paragraph("The details of society maintenance charges for the Quarter "+quarter+" ("+cmonth+") are as follows:-");
            p2.setIndentationLeft(20);
        
            document.add(p2);
            
            document.add(new Paragraph(" "));
            
            PdfPTable table1 = new PdfPTable(3);
            
            Font f1 = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
            
            Paragraph ph1 = new Paragraph("S.No");
            ph1.setFont(f1);
            Paragraph ph2 = new Paragraph("Particular");
            ph2.setFont(f1);
            Paragraph ph3 = new Paragraph("Amount");
            ph3.setFont(f1);
            
            table1.addCell(ph1);
            table1.addCell(ph2);
            table1.addCell(ph3);
            //1
            table1.addCell("1");
            table1.addCell("Previous Balance"); 
            //ArrayList<maint_model> mmaint_list  = database.getDatabase().showMentenanceByFlatForAMonth(flatno, month, year);
            //maint_model mmaint = mmaint_list.remove(mmaint_list.size()-1);
            table1.addCell(String.valueOf(prev_bal));
            //2
            table1.addCell("2");
            table1.addCell("Maintenance Charge");
            //Double maint_charge = database.getDatabase().getMaintCharge(month, year);
            table1.addCell(String.valueOf(Math.round(maint_charge*3)));
            //3
            table1.addCell("3");
            table1.addCell("Sinking Fund");
            //sinkingModel sm = database.getDatabase().getSinkingChargeAndPercentage(month, year);
            //Double charge = (sm.getCharge()*Integer.parseInt(flat.getArea())*0.25)/1200;
            table1.addCell(String.valueOf(Math.round(sink_charge*3)));
            //4
            table1.addCell("4");
            table1.addCell("Non-Occupancy Charge");	
            table1.addCell(String.valueOf(Math.round(occp_charge*3)));
         
            table1.addCell("5");
            table1.addCell("Late fees Interest");
            table1.addCell(String.valueOf(interest));
            
            table1.addCell(" ");
            table1.addCell("Total Amount");
            table1.addCell(String.valueOf(total_amt));
            
            //table1.addCell(" ");
            //table1.addCell("Advance Payment");
            //table1.addCell(String.valueOf(adv_payment));
            
            //table1.addCell(" ");
            //table1.addCell("Net Amount");
            //table1.addCell(String.valueOf(total_amt-adv_payment));
            
            float[] columnWidths1 = new float[] {3f, 20f, 10f};
            table1.setWidths(columnWidths1);
            
            document.add(table1);
            
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Notes:-  "));
            
            Paragraph p3;
            
            p3 = new Paragraph("• Payment can be made by cheque/demand draft/NEFT to the society Accounts.");
            p3.setIndentationLeft(20);
            document.add(p3);
            
            p3 = new Paragraph("• Cheque in favour of La-Tierra Co-Operative Housing Society Ltd.");
            p3.setIndentationLeft(20);
            document.add(p3);
            
            p3 = new Paragraph("• Accounts details are as bellow:-");
            p3.setIndentationLeft(20);
            document.add(p3);
            
            document.add(new Paragraph(" "));
            
            PdfPTable table3 = new PdfPTable(3);
            
            table3.addCell("Bank Name");
            table3.addCell("Account Number");
            table3.addCell("IFSC Code");
            table3.addCell("Bank Of India");
            table3.addCell("053910110011263");
            table3.addCell("BKID0000539");
            
            document.add(table3);
            
            p3 = new Paragraph("• Request to pay this bill in full in the quarter of "+cmonth+", to avoid further penal interest and further action by the society as per rules.");
            p3.setIndentationLeft(20);
            document.add(p3);
            p3 = new Paragraph("• TENANTS are requested to coordinate with your owner to pay the bill in time.");
            p3.setIndentationLeft(20);
            document.add(p3);
            p3 = new Paragraph("• Kindly inform the La-Tierra CHS Ltd. Office in case of any discrepancy to enable timely updating of records.");
            p3.setIndentationLeft(20);
            document.add(p3);
            p3 = new Paragraph("• The society makes expenses for various amenities (viz-Lift, Security services, Staff payment, Electricity bill Housekeeping, Garden maintenance, GYM and other expenses) from these maintenance receipts. Delay in payment may result in discontinuation of certain amenities.");
            p3.setIndentationLeft(20);
            document.add(p3);
            p3 = new Paragraph("• Please ignore if already paid.");
            p3.setIndentationLeft(20);
            document.add(p3);
            
            document.add(new Paragraph(" "));
            
            document.add(new Paragraph("For La-Tierra Co-Op. Hsg Soc, Ltd"));
            document.add(new Paragraph("Treasurer/Manager"));
            document.add(new Paragraph("(This is Computer Generated bill hence no signature required)"));
            
            PdfContentByte canvas = writer.getDirectContent();
            Rectangle rect = new Rectangle(15, 15, 580, 830);
            rect.setBorder(Rectangle.BOX);
            rect.setBorderWidth(2);
            canvas.rectangle(rect);
            
            document.newPage();
    		}
            
            document.close();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
	
	public static void allFlatQuarterReport(ByteArrayOutputStream baos,String quarter,String year,String block,String owner)
	{
		HashMap<Integer,String> months = new HashMap<Integer,String>();
    	
    	months.put(1, "January");
    	months.put(2, "February");
    	months.put(3, "March");
    	months.put(4, "April");
    	months.put(5, "May");
    	months.put(6, "June");
    	months.put(7, "July");
    	months.put(8, "August");
    	months.put(9, "September");
    	months.put(10, "October");
    	months.put(11, "November");
    	months.put(12, "December");
		
		dataManager d = new dataManager();
		LinkedList<maintenance_model> ll = d.getMaintenanceReport("", 0,Integer.parseInt(year), Integer.parseInt(quarter), block, owner);
		
		try
		{
			Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.setMargins(20, 20, 10, 10);
			
            document.open();
            
            
            Chunk c = new Chunk("LA-TIERRA CO-OPERATIVE HOUSING SOCIETY");
			Font f = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			c.setFont(f);
			Paragraph p = new Paragraph(c);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			document.add(new Paragraph(" "));
			
			String s = "";
			if(quarter.equals("1"))
				s = "Quarter 1 - (JAN-MARCH) "+year;
			else if(quarter.equals("2"))
				s = "Quarter 2 - (APRIL-JUNE) "+year;
			else if(quarter.equals("3"))
				s = "Quarter 3 - (JULY-SEPTEMBER) "+year;
			else if(quarter.equals("4"))
				s = "Quarter 4 - (OCTOBER-DECEMBER) "+year;
				
			Chunk c1 = new Chunk(s);
			c1.setUnderline(0.1f, -2f);
			Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c1.setFont(f1);
			Paragraph p2 = new Paragraph(c1);
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			
			String s11 = block.toUpperCase()+" BLOCK FLATS "+owner.toUpperCase()+" OWNERS";
			Chunk c11 = new Chunk(s11);
			c11.setUnderline(0.1f, -2f);
			Font f11 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			c11.setFont(f11);
			Paragraph p21 = new Paragraph(c11);
			p21.setAlignment(Element.ALIGN_CENTER);
			document.add(p21);
			
			
			
			Paragraph p1 = new Paragraph("Date:   " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            p1.setAlignment(Element.ALIGN_RIGHT);
            document.add(p1);
            
            document.add(new Paragraph(" "));
            
            PdfPTable table = new PdfPTable(4);
            
            Font f2 = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
            
            Paragraph ph1 = new Paragraph("Sr No.");
            ph1.setFont(f2);
            Paragraph ph2 = new Paragraph("Flat No");
            ph2.setFont(f2);
            Paragraph ph3 = new Paragraph("Total Amount");
            ph3.setFont(f2);
            //Paragraph ph4 = new Paragraph("Previous Balance");
            //ph4.setFont(f2);
            Paragraph ph4 = new Paragraph("Paid Status");
            ph4.setFont(f2);
            
            
            table.addCell(ph1);
            table.addCell(ph2);
            table.addCell(ph3);
            table.addCell(ph4);
            //table.addCell(ph5);
            //table.addCell(ph6);
            
            //LinkedList<income> ll = d.getIncome(month, year);
            int count = 0;
            Double total = 0.0;
			int flag = 0;
			int statusflag = 1;
			Double prev = 0.0;
			String status = "";

			HashMap<String,String> hp = new HashMap<String,String>();
            while(!ll.isEmpty())
            {
            	maintenance_model m = ll.removeFirst();
				Double t = m.getMaint_charge()+m.getSink_charge()+m.getOccp_charge();
				if(hp.containsKey(m.getFlatid().toString()))
				{
					String str = hp.get(m.getFlatid().toString());
					String str1[] = str.split(",");
					
					Double v = Double.parseDouble(str1[0]);
					v += t;
					Integer st = Integer.parseInt(str1[1]);
					
					if(m.getStatus() == 0)
					{
						st = 0;
					}
					
					hp.replace(m.getFlatid().toString(), v.toString()+","+st.toString());
					
				}
				else
				{
					hp.put(m.getFlatid().toString(), t.toString()+","+m.getStatus().toString());
				}
				
            }
            
            Set<String> set = hp.keySet();
			SortedSet<String> sset = new TreeSet<String>();
			sset.addAll(set);
			Iterator<String> itr = sset.iterator();
			
			while(itr.hasNext())
			{
				String k = itr.next();
				String val = hp.get(k);
				
				String sd[] = val.split(",");
				
				statusflag = Integer.parseInt(sd[1]);
				
				count++;     	
            	Paragraph ph7 = new Paragraph(String.valueOf(count));
            	String fno = d.getFlatByID(k).getFlatNo().toUpperCase();
            	Paragraph ph8 = new Paragraph(fno);
            	total = Double.parseDouble(sd[0]);
            	Paragraph ph9 = new Paragraph(total.toString());
            	//Double prevBalance = m.getPrev_balance();
            	//Paragraph ph10 = new Paragraph(prevBalance.toString());
            	status = "";
				
				if(statusflag == 1)
				{
					status = "PAID";
				}
				else
				{
					status = "NOT PAID";
				}
            	Paragraph ph11 = new Paragraph(status);
            	//Paragraph ph12 = new Paragraph(String.valueOf(i.getAmt()));
            	
            	table.addCell(ph7);
            	table.addCell(ph8);
            	table.addCell(ph9);
            	//table.addCell(ph10);
            	table.addCell(ph11);
            	//table.addCell(ph12);
            	float[] columnWidths = new float[] {1f,8f,3f,3f};
	            table.setWidths(columnWidths);
				
			}
            
            table.setTotalWidth(PageSize.A4.getWidth()-20);
            table.setLockedWidth(true);
            document.add(table);

            document.close();

            
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
