package action;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import util.CookieUtil;

import com.CAccount;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware, SessionAware {
	private static String FORWARD = null;
	private String username;
	private String password;
	private static final long serialVersionUID = 6650955874307814247L;
	public static final String USER_SESSION = "user.session";
	private HttpServletResponse response;
	private HttpServletRequest request;
	private Map<String, Object> session;
	private boolean userCookie;
	private CookieUtil cookieUtil = new CookieUtil();

	//
	public void volidate() {

	}

	/*
	 * // 用户登录跳转 public String login() { if (cookieUtil.getCookie(request, )) {
	 * return SUCCESS; } else return "login"; }
	 */

	public String execute() throws Exception {
		Cookie[] cookies;
		cookies = request.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("username"))
				c.setMaxAge(0);
			if (c.getName().equals("password"))
				c.setMaxAge(0);
			c.setPath("/websh");
			response.addCookie(c);
		}

		FORWARD = "out";

		return FORWARD;
	}

	/*
	 * // 用户退出 public String logout() { HttpSession session =
	 * request.getSession(false); if (session != null)
	 * session.removeAttribute(USER_SESSION); Cookie cookieu =
	 * cookieUtil.delCookie(request, "username"); cookieu =
	 * cookieUtil.delCookie(request, "password"); if (cookieu != null)
	 * response.addCookie(cookieu); return "login"; }
	 */

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public boolean getUserCookie() {
		return userCookie;
	}

	public void setUserCookie(boolean userCookie) {
		this.userCookie = userCookie;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
