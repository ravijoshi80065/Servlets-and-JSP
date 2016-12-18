

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ConfigContextInsertServ")
public class ConfigContextInsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
    
    @Override
    public void init() throws ServletException
    {
    	System.out.println("Inside init() of ConfigContextInsertServ");
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
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Inside DoGet method of ConfigContextInsertServ");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("Inside Do Post method of ConfigContextInsertServ");
		response.setContentType("text/html");
		PrintWriter writer=response.getWriter();
		ServletConfig config=getServletConfig();
		
		
		//getting parameter values from servletcofig
		String table=config.getInitParameter("table");
		System.out.println("Tablename fetched from config "+table);
		
		//getting values from request as filled in the form by user
		int roll=Integer.parseInt(request.getParameter("roll"));
		int clas=Integer.parseInt(request.getParameter("clas"));
		String name=request.getParameter("name");
		
		//Database operation
		try{
			PreparedStatement ps=con.prepareStatement("insert into "+table+" values (?,?,?);");
			ps.setInt(1, roll);
			ps.setInt(2, clas);
			ps.setString(3, name);
			int result=ps.executeUpdate();
			writer.println(result + " rows inserted into "+table);
			System.out.println(result + " rows inserted into "+table);
		}
		catch(Exception e)
		{
			System.out.println("Unable to insert data into table Exception "+e);
			writer.println("Unable to insert data into table Exception "+e);
		}
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
