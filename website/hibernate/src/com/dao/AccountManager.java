package com.dao;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

import base.*;


public class AccountManager extends BaseManager{
	
	public void insert(String name, String password, String email, long qq) {
		UserManager mgr = new UserManager();
		User user = new User(name, "", "");
		mgr.insert(user);
		
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		
		Account account = new Account();
		account.setName(name);
		account.setPassword(password);
		account.setEmail(email);
		account.setCreateTime(new Date());
		account.setLastLoginTime(new Date());
		account.setUser(user);

		session.save(account);

		tx.commit();
	}

	//create and insert one record
	public void insert(String name, String password, String email, long qq, User user) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		Account account = new Account();
		account.setName(name);
		account.setPassword(password);
		account.setEmail(email);
		account.setCreateTime(new Date());
		account.setLastLoginTime(new Date());
		account.setUser(user);

		session.save(account);

		tx.commit();
		// HibernateUtil.closeSession();
	}

	//select one record by primary key
	public Account select(long id) {
		return (Account)BaseManager.select("com.dao.Account", id);
	}
	
	//select all records 
	public List selectAll() {
		return BaseManager.selectAll("Account");
	}

	//select one record by property
	public Account selectOneByProperty(String property, String value) {
		return (Account) BaseManager.selectOneByProperty("Account", property, value);
	}

	//select all record by property
	public List selectAllByProperty(String property, String value) {
		return (List) BaseManager.selectAllByProperty("Account", property, value);
	}

	public void delete(long id) {
		BaseManager.delete("com.dao.Account", id);
	}

	public void deleteAllByProperty(String property, String value) {
		BaseManager.deleteAllByProperty("Account", property, value);
	}
	
	public void updateAllByProperty(String property, String value) {
		BaseManager.updateProperty("Account", property, value);
	}

	public static void main(String[] args) {
		AccountManager mgr = new AccountManager();
		
/*		Account accountx = mgr.select(2l);
		System.out.println("Name: " + accountx.getName() + " Email: "
				+ accountx.getEmail());
		System.exit(0);*/

		String act = "list";

		if (act.equals("insert")) {
			User user = new User("test","test","");
			UserManager umgr = new UserManager();
			umgr.insert(user);
			mgr.insert("admin", "admin", "azx-c@163.com", 0l, user);
		} else if(act.equals("insertx")) {
			mgr.insert("testb", "testb", "testb@163.com", 0l);
		} else if (act.equals("list")) {
			List accounts = mgr.selectAll();
			for (int i = 0; i < accounts.size(); i++) {
				Account account = (Account) accounts.get(i);
				System.out.println("Name: " + account.getName() + " Email: "
						+ account.getEmail());
			}
		} else if (act.equals("get")) {
			Account account = mgr.selectOneByProperty("qq", "0");
			System.out.println("Name: " + account.getName() + " Email: "
					+ account.getEmail());
		} else if (act.equals("delete")) {
			mgr.delete(1l);
		} else if (act.equals("user")) {
			Account account = mgr.selectOneByProperty("qq", "0");
			System.out.println(account.toString());
			System.out.println(account.getUser().toString());
		}

		HibernateUtil.closeSession();
	}

}