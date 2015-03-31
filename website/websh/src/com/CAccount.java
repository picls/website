package com;

import java.util.List;

import com.dao.Account;
import com.dao.AccountManager;
import com.dao.User;
import com.dao.UserManager;

public class CAccount {
	
	public boolean checkAccountPassword(String name, String password) {
		boolean is_cor = false;
		AccountManager mgr = new AccountManager();
		List result = mgr.selectAllByProperty("name", name);

		if (result.size() == 1)
			if (((Account) result.get(0)).getPassword().equals(password))
				return true;
		return false;
	}

	public static int checkAccountStatus(String name) {
		AccountManager mgr = new AccountManager();
		Account account = mgr.selectOneByProperty("name", name);
		if (account == null)
			return 1;
		else
			return 0;
	}

	public int checkAccountStatus(String name, String email) {
		if (checkAccountStatus(name) == 1) {
			AccountManager mgr = new AccountManager();
			Account account = mgr.selectOneByProperty("email", email);
			if (account == null)
				return 1;
			else
				return 0;
		} else
			return 0;
	}

	public int addAccount(String name, String password, String email) {
		if (checkAccountStatus(name, email) == 1) {
			AccountManager amgr = new AccountManager();
			amgr.insert(name, password, email, 0);
			return 1;
		} else
			return 0;

	}

	
	public static void main(String args[]) {
		CAccount c = new CAccount();
		c.addAccount("test1", "test1", "test1");
	}
}
