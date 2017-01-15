<html>
<head>
<title>Using Servlet Context and Config in JSP</title>
</head>
<!-- This jsp uses the servletConfig and ServletContext objects of servlet created for it-->
<body>
<%!
public void jspInit()
{
	//overriding jspInit() method,this method will take the init parameters name,age
	//from web.xml file.After that it will calculte age status based on age and
	//attach the name and age status attribute to servlet context object.	
	ServletConfig conf=getServletConfig();
	ServletContext cont=getServletContext();
	System.out.println("Picking name from web.xml");
	String name=conf.getInitParameter("name");
	int age=Integer.parseInt(conf.getInitParameter("age"));
	System.out.println("name :"+name);
	System.out.println("age :"+age);
	String status;
	if(age>60)
		status="Senior Citizen";
	else
		if(age>18)
			status="adult";
		else
			status="young";
	cont.setAttribute("ageStatus",status);
	cont.setAttribute("userName",name);
}
%>
<%
ServletContext cont=getServletContext();
String aStatus,uName;
uName=(String)cont.getAttribute("userName");
aStatus=(String)cont.getAttribute("ageStatus");
%>
Name provided in web.xml file's is 
<%= uName %>
<br />
Age status is 
<%= aStatus %>
</body>
</html>