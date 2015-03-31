package filter;

import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.CAccount;

public class Status implements Filter {
	public void init(FilterConfig config) throws ServletException {
		// ��ȡ��ʼ������
		String testParam = config.getInitParameter("test-param");

		// �����ʼ������
		System.out.println("Test Param: " + testParam);
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws java.io.IOException, ServletException {

		// ��ȡ�ͻ����� IP ��ַ
		String ipAddress = request.getRemoteAddr();

		// ��¼ IP ��ַ�͵�ǰʱ���
		System.out.println("IP " + ipAddress + ", Time "
				+ new Date().toString());

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		System.out.println(req.getRequestURI());

		if (req.getRequestURI().contains(".jsp")
				|| req.getRequestURI().contains(".html")) {
			Cookie cookie;
			Cookie[] cookies = req.getCookies();
			String username = null, password = null;

			if (cookies != null)
				for (Cookie c : cookies) {
					if (c.getName().equals("username"))
						username = c.getValue();
					if (c.getName().equals("password"))
						password = c.getValue();
				}

			System.out.println("username:" + username + ", password:"
					+ password);

			int status = 0;
			if ((username != null) && (!username.equals("")))
				if ((password != null) && (!password.equals(""))) {
					CAccount c = new CAccount();
					if (c.checkAccountPassword(username, password))
						status = 1;
					else
						status = 2;
				}

			System.out.println("status:" + status);
			if (status == 1) {
				if ((req.getRequestURI().indexOf("login") >= 0)
						|| (req.getRequestURI().indexOf("register") >= 0)) {
					res.sendRedirect("/websh/blog.jsp");
					/*
					 * res.setContentType("text/html");
					 * res.setStatus(res.SC_MOVED_TEMPORARILY);
					 * res.setHeader("Location", "/websh/blog.jsp");
					 */
				}
			} else {
				if ((req.getRequestURI().indexOf("login") < 0)
						&& (req.getRequestURI().indexOf("register") < 0)) {
					if (status == 0) {
						response.setContentType("text/html");
						res.setStatus(res.SC_MOVED_TEMPORARILY);
						res.setHeader("Location", "/websh/login.html");
						// res.sendRedirect("/websh/login.html");
					}
					if (status == 2) {
						response.setContentType("text/html");
						res.setStatus(res.SC_MOVED_TEMPORARILY);
						res.setHeader("Location", "/websh/login.html");
						// res.sendRedirect("/websh/login.html");
					}
				}
			}
		}

		// �����󴫻ع�����
		chain.doFilter(request, response);

	}

	public void destroy() {
		/* �� Filter ʵ���� Web �����ӷ����Ƴ�֮ǰ���� */
	}

}
