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


import com.CAccount;



public class Login extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Login() {
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
/*	public void doGeta(HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		Enumeration e = request.getHeaderNames();
		while(e.hasMoreElements()){
			System.out.println("---------------------");
			String headerName = (String)e.nextElement();
			System.out.println(headerName);
			System.out.println(request.getHeader(headerName));
		}
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		
		Cookie addCookie = new Cookie("age","23");
		addCookie.setMaxAge(60*60*24);
//		addCookie.setValue(newValue)
		response.addCookie(addCookie);
		
		
		
		// request.getContextPath();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("<p>" + request.getParameter("first_name") + "</p>");
		out.println("<p>" + request.getParameter("last_name") + "</p>");
		out.println("<p>" + request.getServletPath() + "</p>");
		out.println("<p>" + request.getContextPath() + "</p>");
		out.println("<p>" + request.getRequestURI() + "</p>");
		out.println("<p>" + request.getRequestURL() + "</p>");
		out.println("<p>");
		if(cookies != null)
			for(int i=0;i<cookies.length;i++){
				cookie = cookies[i];
				out.println(cookie.getName());
				out.println(cookie.getValue());
			}
		out.println("</p>");
		out.println("<p>");
		if(request.getSession()!=null) out.println(request.getSession().getId()); else out.println("no session.");  
		out.println("</p>");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}*/

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置响应内容类型
		response.setContentType("text/html");
		
		
		String login = request.getParameter("login");
		if(login != null){
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			boolean is_cor = false;
			
			CAccount c = new CAccount();
			is_cor = c.checkAccountPassword(username, password);			
			
			String site = "";
			if(is_cor) site = new String("../blog.jsp");
			else site = new String("../admin-404.html");
			Cookie cookieu = new Cookie("username",username);
			Cookie cookiep = new Cookie("password",password);
			cookieu.setPath("/websh");
			cookiep.setPath("/websh");
			cookieu.setSecure(true);
			cookiep.setSecure(true);
			response.addCookie(cookieu);
			response.addCookie(cookiep);
		    response.setStatus(response.SC_MOVED_TEMPORARILY);
		    response.setHeader("Location", site); 
		}
		String forget = request.getParameter("forget");
		if(forget != null){
			String site = new String("../admin-404.html");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
		    response.setHeader("Location", site); 
		}
		
		String x = new String();
		
		Object o = new Object();
		
	    

	    
/*
		PrintWriter out = response.getWriter();
		String title = "读取所有的表单数据";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 "
				+ "transitional//en\">\n";
		out.println(docType + "<html>\n" + "<head><title>" + title
				+ "</title></head>\n" + "<body bgcolor=\"#f0f0f0\">\n"
				+ "<h1 align=\"center\">" + title + "</h1>\n"
				+ "<table width=\"100%\" border=\"1\" align=\"center\">\n"
				+ "<tr bgcolor=\"#949494\">\n" + "<th>参数名称</th><th>参数值</th>\n"
				+ "</tr>\n");

		Enumeration paramNames = request.getParameterNames();
		Cookie[] cookies = request.getCookies();
		Enumeration headerNames = request.getHeaderNames();
		String queryString = request.getQueryString();
		String remoteHost = request.getRemoteHost();

		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			out.print("<tr><td>" + paramName + "</td>\n<td>");
			String[] paramValues = request.getParameterValues(paramName);
			// 读取单个值的数据
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() == 0)
					out.println("<i>No Value</i>");
				else
					out.println(paramValue);
			} else {
				// 读取多个值的数据
				out.println("<ul>");
				for (int i = 0; i < paramValues.length; i++) {
					out.println("<li>" + paramValues[i]);
				}
				out.println("</ul>");
			}
		}
		out.println("</tr>\n</table>\n</body></html>");*/

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



