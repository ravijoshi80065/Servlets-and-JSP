<html>
<head>
<title>Declared method example</title>
</head>
<body>
<%! int count=1; %>
<!-- Declaring class method to double the number of hits on the page -->
<%!
int doubleCount()
{
	count=count*2;
	return count;
}
%>
doubling the count of page hits :
<%= doubleCount()
%>
</body>
</html>