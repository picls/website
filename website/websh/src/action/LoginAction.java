package action;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import com.CAccount;
import util.CookieUtil;

public class LoginAction extends ActionSupport implements ServletRequestAware,
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
		if (getUsername() == null || getUsername().trim().equals("")) {
			addFieldError("username", getText("user.required"));
		}
		if (getPassword() == null || getPassword().trim().equals("")) {
			addFieldError("password", getText("pass.required"));
		}
	}

/*	// �û���¼��ת
	public String login() {
		if (cookieUtil.getCookie(request, )) {
			return SUCCESS;
		} else
			return "login";
	}*/

	

	public String execute() throws Exception {
		username = getUsername();
		password = getPassword();
		CAccount c = new CAccount();
		if (c.checkAccountPassword(username, password)){
			FORWARD = "success";
			Cookie cookie = cookieUtil.addCookie("username",username);
			response.addCookie(cookie);// ���cookie��response��
			cookie = cookieUtil.addCookie("password",password);
			response.addCookie(cookie);// ���cookie��response��
		}
		else
			FORWARD = "input";

		return FORWARD;
	}
	
	/*@Override
	// ������¼
	public String execute() throws Exception {
		System.out.println(username + "\t" + password + "\t" + userCookie);
		String username = getUsername().trim();
		if (username != null && !"".equals(username) && password != null
				&& !"".equals(password)) {
			User user = userDao.checkUser(username, password);
			System.out.println(user);
			if (user != null) {
				// �ж��Ƿ�Ҫ��ӵ�cookie��
				if (userCookie) {
					Cookie cookie = cookieUtils.addCookie(user);
					response.addCookie(cookie);// ���cookie��response��
				}
				// 2.��user ���õ�session��
				session.put(USER_SESSION, user);
				return SUCCESS;
			}
		}
		this.addFieldError("username", "�û������������!");
		return "login";
	}*/

	// �û��˳�
	public String logout() {
		HttpSession session = request.getSession(false);
		if (session != null)
			session.removeAttribute(USER_SESSION);
		Cookie cookieu = cookieUtil.delCookie(request, "username");
		cookieu = cookieUtil.delCookie(request, "password");
		if (cookieu != null)
			response.addCookie(cookieu);
		return "login";
	}
	
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
