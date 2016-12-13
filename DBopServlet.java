
//import com.mysql.jdbc.Driver;
/*
 * This Servlet program performs simple operation like insert and retrieve on
 * a mysql table(DBopTab).It uses HTML form to get user request. 
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DBopServlet")
public class DBopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
    public DBopServlet() {
        super();
    }

    @Override
    public void init() throws ServletException
    {
    	final String JDBC_DRIVER="com.mysql.jdbc.Driver";
        final String DB_URL="jdbc:mysql://localhost:3306/prj1";	
        System.out.println("Inside init() of DBopServlet");
        try
        {
        	Class.forName(JDBC_DRIVER);
        	System.out.println("Connecting to database...............");
        	con =DriverManager.getConnection(DB_URL,"root","root");        	
        }
        catch(Exception e)
        {
        	System.out.println("Exception caught in init() :"+e);
        }
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter writer=response.getWriter();
		String sql="select * from DBopTab";
		int roll;
		String name,address;
		try
		{
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			writer.println("<table>");
			writer.println("<th><strong><td>Roll</td><td>Name</td><td>Address</td></strong></th>");
			while(rs.next())
			{
				roll=rs.getInt(1);
				name=rs.getString(2);
				address=rs.getString(3);
				writer.println("<tr><td>"+roll+"</td><td>"+name+"</td><td>"+address+"</td></tr>");
			}
			writer.println("</table>");
		}
		catch(Exception e)
		{
			System.out.println("Exception Caught :"+e);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter writer=response.getWriter();
		writer.println("<h4>Got request for inserting data into Table</h4>");
		int result;
		String sql=null;
		String sroll=request.getParameter("roll");
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		int roll=Integer.parseInt(sroll);
		try
		{

	        Statement stmt=con.createStatement();
			sql="insert into DBopTab values (" + roll + ",'" + name + "' , '" + address+"');";
			result=stmt.executeUpdate(sql);
			writer.println(result+" rows inserted into table DBopTab");
		}
		catch(Exception e)
		{
			writer.println("Exception caught while inserting :"+e);
			System.out.println("Exception caught while inserting :"+e);
		}
		
	}
	
	@Override
	public void destroy()
	{
		System.out.println("Inside Destroy()");
	}

}
