# Servlets-and-JSP
Contains working codes for servlets and jsp operations

1:Calc**** Files are used to make a simple MVC model app which calculates sum of two numbers.It uses servlet controller,java model class and JSP view.

2:DBop**** files are source files for a simple app which performs simple insert and retrieve operation on a MySql table present on server

3:ConfigContext**** files are source files for a simple app which shows how to use Servletconfig and ServletContext to pass information to a servlet. We are passing database driver name,database URL tablename,username,passsword to servlets through web.xml.

4:ListenSesssion**** files are source files for a web-app which uses ServletContextListener to initialize database connection,Database table details are passed via context parameters.It takes three input forms from user.It stores data from first 2 forms in httpsession object and when the third form arrives,it loads the full data(consisting of 3 form inputs) into the table.
