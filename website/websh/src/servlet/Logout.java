package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Logout extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Logout() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置响应内容类型
		response.setContentType("text/html");
		response.setStatus(response.SC_MOVED_TEMPORARILY);
	    response.setHeader("Location", "http://www.baidu.com"); 
		
/*		Cookie[] cookies;
		cookies = request.getCookies();
		for(Cookie c : cookies){
			if(c.getName().equals("username"))
				c.setMaxAge(0);
			if(c.getName().equals("username"))
				c.setMaxAge(0);
			response.addCookie(c);
		}
		response.setStatus(response.SC_MOVED_TEMPORARILY);
	    response.setHeader("Location", "../login_new.jsp"); */

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

		/*
		 * response.setContentType("text/html"); PrintWriter out =
		 * response.getWriter(); out.println(
		 * "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		 * out.println("<HTML>");
		 * out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		 * out.println("  <BODY>"); out.print("    This is ");
		 * out.print(this.getClass()); out.println(", using the POST method");
		 * out.println("  </BODY>"); out.println("</HTML>"); out.flush();
		 * out.close();
		 */
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		super.init();
		// Put your code here
	}

}
