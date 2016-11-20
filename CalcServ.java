package com.example.web;

import java.io.*;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/CalcServ")
public class CalcServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CalcServ() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		CalcModel ob1= new CalcModel();
		PrintWriter out = response.getWriter();
		out.println("Sum of two numbers<br>");
		String sn1= request.getParameter("num1");
		String sn2= request.getParameter("num2");
		int n1 = Integer.parseInt(sn1);
		int n2 = Integer.parseInt(sn2);
		int sum=ob1.getSum(n1, n2);
		//out.println("<br>Sum of numbers " + n1 + " and " + n2 +" is "+(n1+n2));
		//out.println("The sum of "+n1+" and "+n2+" using model class is "+sum);
		request.setAttribute("total", sum);
		RequestDispatcher view =request.getRequestDispatcher("CalcView.jsp");
		view.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
