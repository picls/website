import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





//扩展 HttpServlet 类
public class refresh extends HttpServlet {

	// 处理 GET 方法请求的方法
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置刷新自动加载时间为 5 秒
		response.setIntHeader("Refresh", 5);
		//response.setStatus(200);
		//response.sendRedirect("http://www.baidu.com");
		response.sendError(403,"just not open.");
		request.getRequestDispatcher("").forward(null, null);
		

		// 设置响应内容类型
		response.setContentType("text/html");

		// Get current time
		Calendar calendar = new GregorianCalendar();
		String am_pm;
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		if (calendar.get(Calendar.AM_PM) == 0)
			am_pm = "AM";
		else
			am_pm = "PM";

		String CT = hour + ":" + minute + ":" + second + " " + am_pm;

		PrintWriter out = response.getWriter();
		String title = "自动刷新 Header 设置";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 "
				+ "transitional//en\">\n";
		out.println(docType + "<html>\n" + "<head><title>" + title
				+ "</title></head>\n" + "<body bgcolor=\"#f0f0f0\">\n"
				+ "<h1 align=\"center\">" + title + "</h1>\n" + "<p>当前时间是："
				+ CT + "</p>\n");
	}

	// 处理 POST 方法请求的方法
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}