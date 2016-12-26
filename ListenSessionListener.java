
import com.mysql.jdbc.Driver;
import java.sql.*;
import java.sql.DriverManager;

import javax.servlet.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
/*
 * This will be called at web-app startup and end
 * It will create a database connection and create a coonection object hich can be used
 * by other servlets in the program
 */

@WebListener
public class ListenSessionListener implements ServletContextListener {

    public ListenSessionListener() {
        System.out.println("inside ListenSessionListener:Listener class");
    }
    
    public void contextInitialized(ServletContextEvent event)  { 
         
    	ServletContext scon=event.getServletContext();
    	String driver=scon.getInitParameter("driver");
    	String dburl=scon.getInitParameter("dburl");
    	String user=scon.getInitParameter("user");
    	String password=scon.getInitParameter("password");
    	System.out.println("driver name: "+driver);
    	System.out.println("dburl : "+dburl);
    	System.out.println("user name: "+user);
    	System.out.println("password: "+password);
    	System.out.println("Setting up database connction");
    	try
    	{
    		Class.forName(driver);
    		System.out.println("Connecting to database...............");
    		Connection con=DriverManager.getConnection(dburl,user,password);
    		scon.setAttribute("connection", con);
    	}
    	catch(Exception e)
    	{
    		System.out.println("Exception caught in Listener class "+e);
    	}
    }
    

    public void contextDestroyed(ServletContextEvent event)  { 
    	ServletContext scon=event.getServletContext();
    	Connection con=(Connection)scon.getAttribute("connection");
    	try
    	{
    	System.out.println("Closing the connection ");
    	con.close();
    	}
    	catch(Exception e)
    	{
    	System.out.println("Exception caught while closing the connection "+e);
    	}
    }
	
}
