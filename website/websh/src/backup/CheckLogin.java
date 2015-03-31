package backup;

import java.util.ArrayList;
import java.util.List;

import com.dao.AccountManager;
import com.dao.Account;

public class CheckLogin {

	public boolean check(String username, String password) {
		boolean is_cor = false;
		AccountManager mgr = new AccountManager();
		List result = mgr.selectAllByProperty("name", username);

		if (result.size() == 1)
			if (((Account) result.get(0)).getPassword().equals(password))
				return true;
		return false;
	}

	public static void main(String[] args) {
		CheckLogin c = new CheckLogin();
		System.out.println(c.check("admin","admin"));
		System.out.println(c.check("user","user"));
	}
}
