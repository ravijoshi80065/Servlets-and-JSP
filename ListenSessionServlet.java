

import java.io.IOException;
import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ListenSessionServlet")
public class ListenSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Inside doget of ListenSessionServlet ");
		PrintWriter writer=response.getWriter();
		ServletContext scon=getServletContext();
		String tableName=scon.getInitParameter("table");
		Connection con=(Connection)scon.getAttribute("connection");
		String name,fname,mname;
		int roll,marks,total;
		try
		{
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("Select * from "+tableName);
			writer.println("<table>");
			writer.println("<tr><td>Name</td><td>Roll no</td><td>Mother's name</td><td>Father's name</td><td>Marks</td><td>Total</td></tr>");
			while (rs.next())
			{
			name=rs.getString(1);
			roll=rs.getInt(2);
			mname=rs.getString(3);
			fname=rs.getString(4);
			marks=rs.getInt(5);
			total=rs.getInt(6);
			writer.println("<tr><td>"+name+"</td><td>"+roll+"</td><td>"+mname+"</td><td>"+fname+"</td><td>"+marks+"</td><td>"+total+"</td></tr>");					
			}
			writer.println("</table>");
		}
		catch(Exception e)
		{
			System.out.println("Exception Caught in doget of ListenSessionServlet "+e);
			writer.println("Exception Caught in doget of ListenSessionServlet "+e);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Inside doPost of ListenSessionServlet ");
		HttpSession session=null;
		ServletContext scon=getServletContext();
		String tableName=scon.getInitParameter("table");
		PrintWriter writer=response.getWriter();
		int formno=Integer.parseInt(request.getParameter("formno"));
		if(formno==1)
		{
			System.out.println("Form no 1 is recieved");
			int roll=Integer.parseInt(request.getParameter("roll"));
			String name=request.getParameter("name");
			session=request.getSession();
			session.setAttribute("roll", roll);
			session.setAttribute("name", name);
			//After fetching information from form1 redirecting to form 2
			response.sendRedirect("ListenSessionForm2.html");
		}
		if(formno==2)
		{
			System.out.println("Form no 2 is recieved");
			String mname=request.getParameter("mname");
			String fname=request.getParameter("fname");
			session=request.getSession();
			session.setAttribute("mname", mname);
			session.setAttribute("fname", fname);
			//After fetching information from form2 redirecting to form3
			response.sendRedirect("ListenSessionForm3.html");
		}
		if(formno==3)
		{
			System.out.println("Form no 3 is recieved ");
			int marks=Integer.parseInt(request.getParameter("marks"));
			int total=Integer.parseInt(request.getParameter("total"));
			session=request.getSession();
			//New variables to insert data fetched from session into database table
			//form 1 data
			int droll=(int)(session.getAttribute("roll"));
			String dname=(String)session.getAttribute("name");
			
			//form 2 data
			String dmname=(String)session.getAttribute("mname");
			String dfname=(String)session.getAttribute("fname");
			
			Connection con=(Connection)scon.getAttribute("connection");
			try
				{
				PreparedStatement ps=con.prepareStatement("insert into "+tableName+" values (?,?,?,?,?,?);");
				ps.setString(1, dname);
				ps.setInt(2, droll);
				ps.setString(3, dmname);
				ps.setString(4, dfname);
				ps.setInt(5, marks);
				ps.setInt(6, total);
				int result=ps.executeUpdate();
				writer.println(result + " rows inserted.....into table");
				}
			catch(Exception e)
				{
				System.out.println("Exception caught while inserting data in ListenSessionServlet");
				writer.println("Exception caught while inserting data in ListenSessionServlet");
				}
		}
	}

}
