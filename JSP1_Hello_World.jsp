<html>
<head>
<title>First JSP program</title>
</head>
<body>
<%
int i;
for(i=0;i<10;i++)
out.println("Hello World!");
%>
<br />
<%
java.util.Date dt=new java.util.Date();
out.println(dt);
%>
</body>
</html>