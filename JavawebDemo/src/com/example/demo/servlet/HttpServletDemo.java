package com.example.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.listener.HttpSessionBindingListenerDemo;

public class HttpServletDemo extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
//		req.getSession().setAttribute("user", new HttpSessionBindingListenerDemo());
//		req.getSession().setAttribute("user", "zhangsan");
//		req.getSession().setAttribute("user", "lisi");
//		req.getSession().removeAttribute("user");
//		req.getSession().invalidate();
		System.out.println("sessionid:	"+req.getSession(true).getId());
//		System.out.println(req.getSession().getAttribute("name"));
//		req.getSession().setAttribute("name", "zhangsan");
//		for(Cookie cookie:req.getCookies()) {
//			System.out.println("cookie:	"+cookie.getName()+"|"+cookie.getValue());
//		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
	
	public void init() {
		System.out.println("servlet init");
	}
}
