

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConfigContextSelectServ
 */
@WebServlet("/ConfigContextSelectServ")
public class ConfigContextSelectServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;   

    @Override
    public void init() throws ServletException
    {
    	System.out.println("Inside init() of ConfigContextSelectServ");
    	ServletContext context=getServletContext();
		
		//getting parameter value from context parameter
		String driver=context.getInitParameter("driver");
		String DBurl=context.getInitParameter("DBurl");
		String user=context.getInitParameter("user");
		String password=context.getInitParameter("password");
		System.out.println("driver name fetched from context "+driver);
		System.out.println("DBurl fetched from context "+DBurl);
		
		//connecting to database
    	try
    	{

			Class.forName(driver);
			System.out.println("Connecting to a database");
			con=DriverManager.getConnection(DBurl,user,password);
    	}
        catch(Exception e)
        {
        	System.out.println("Exception caught in init() :"+e);
        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Inside doget() method of ConfigContextSelectServ"); 
		response.setContentType("text/html");
		ServletConfig config=getServletConfig();
		PrintWriter writer=response.getWriter();
		
		String table=config.getInitParameter("table");
		ResultSet rs=null;
		int roll,clas;
		String name;
		
		try
		{
			Statement stmt=con.createStatement();
			rs=stmt.executeQuery("Select * from "+table+";");
			writer.println("<table>");
			writer.println("<th><td>Roll</td><td>Class</td><td>Name</td></th>");
			while(rs.next())
			{
				roll=rs.getInt(1);
				clas=rs.getInt(2);
				name=rs.getString(3);
				writer.println("<tr><td>"+roll+"</td><td>"+clas+"</td><td>"+name+"</td></tr>");
				
			}
			writer.println("</table>");
		}
		catch(Exception e)
		{
			System.out.println("Exception caught while fetching data from table "+e);
			writer.println("Exception caught while fetching data from table "+e);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside do post method of ConfigContextSelectServ");
	}

	@Override
	public void destroy()
	{
		System.out.println("Inside Destroy()");
		try{
		System.out.println("Closing Connection");
		con.close();
		}
		catch(Exception e)
		{
			System.out.println("Unable to close connection exception:"+e);
		}
	}	
}
