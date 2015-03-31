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

public class RegisterAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, SessionAware {
	
	private static String FORWARD = null;
	private String email;
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

	public String execute() throws Exception {
		email = getEmail();
		username = getUsername();
		password = getPassword();
		
/*		System.out.println("-------------------------");
		System.out.println("-------------------------");
		System.out.println("-------------------------");
		System.out.println("email:" + email);
		System.out.println("username:" + username);
		System.out.println("password:" + password);*/
		
		CAccount c = new CAccount();
		if(c.addAccount(username, password, email) == 1) {
			FORWARD = "success";
			Cookie cookie = cookieUtil.addCookie("username", username);
			response.addCookie(cookie);// 添加cookie到response中
			cookie = cookieUtil.addCookie("password", password);
			response.addCookie(cookie);// 添加cookie到response中
		}else{
			FORWARD = "fail";
		}
		return FORWARD;
	}

	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
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