package util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;


/**
 * cookie的增加、删除、查询
 */
public class CookieUtil {

	// 添加一个cookie
	public Cookie addCookie(String name, String value) {
		Cookie cookie = new Cookie(name,value);
		//System.out.println("添加cookie");
		cookie.setMaxAge(60 * 60 * 24 * 1);// cookie保存一天
		return cookie;
	}
	
	// 添加一个cookie
	public Cookie addCookie(String name, String value, int time) {
		Cookie cookie = new Cookie(name,value);
		//System.out.println("添加cookie");
		cookie.setMaxAge(time);// cookie保存两周
		return cookie;
	}

/*	// 得到cookie
	public boolean getCookie(HttpServletRequest request, UserDao userDAO) {
		Cookie[] cookies = request.getCookies();
		System.out.println("cookies: " + cookies);
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				System.out.println("cookie: " + cookie.getName());
				if (CookieUtils.USER_COOKIE.equals(cookie.getName())) {
					String value = cookie.getValue();
					if (StringUtils.isNotBlank(value)) {
						String[] split = value.split(",");
						String username = split[0];
						String password = split[1];
						User user = userDAO.checkUser(username, password);
						if (user != null) {
							HttpSession session = request.getSession();
							session.setAttribute(LoginAction.USER_SESSION, user);// 添加用户到session中
							return true;
						}
					}
				}
			}
		}
		return false;
	}*/
	
	// 删除cookie
	public Cookie delCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					cookie.setValue("");
					cookie.setMaxAge(0);
					return cookie;
				}
			}
		}
		return null;
	}

}
