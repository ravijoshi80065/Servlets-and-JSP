<html>
<head>
<title>Hit counter</title>
</head>
<!-- This JSP counts the number of times page was requested and displays it to user -->
<body>
<%! int count=0;
%>
You are accessing the page 
<%
count++;
out.println(count+" time.");
%>
</body>
</html>