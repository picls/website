package com.dao;

import org.hibernate.Transaction;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

import base.*;

public class UserManager extends BaseManager{
	private long id;
	private String name;
	private String nickname;
	private String description;
	private int passageNumber;
	private int integration;
	
	public void insert(User user) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		
		session.save(user);
		
		tx.commit();
	}
	
	//create and insert one record
	public void insert(String name, String nickname, String description) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		User user = new User();
		user.setName(name);
		user.setNickname(nickname);
		user.setDescription(description);
		user.setPassageNumber(0);
		user.setIntegration(0);

		session.save(user);

		tx.commit();
		// HibernateUtil.closeSession();
	}
	
	public void insert(String name, String nickname, String description, int Integration) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		User user = new User();
		user.setName(name);
		user.setNickname(nickname);
		user.setDescription(description);
		user.setPassageNumber(0);
		user.setIntegration(0);

		session.save(user);

		tx.commit();
		HibernateUtil.closeSession();
	}

	//select one record by primary key
	public User select(long id) {
		return (User)BaseManager.select("com.dao.User", id);
	}
	
	//select all records 
	public List selectAll() {
		return BaseManager.selectAll("User");
	}

	//select one record by property
	public User selectOneByProperty(String property, String value) {
		return (User) BaseManager.selectOneByProperty("User", property, value);
	}

	//select all record by property
	public List selectAllByProperty(String property, String value) {
		return (List) BaseManager.selectAllByProperty("User", property, value);
	}

	public void delete(long id) {
		BaseManager.delete("com.dao.User", id);
	}

	public void deleteAllByProperty(String property, String value) {
		BaseManager.deleteAllByProperty("User", property, value);
	}
	
	public void updateAllByProperty(String property, String value) {
		BaseManager.updateProperty("User", property, value);
	}
	
	public static void main(String[] args) {
		UserManager mgr = new UserManager();

		String act = "insert";

		if (act.equals("insert")) {
			mgr.insert("test","test","test",0);
		} else if (act.equals("list")) {
			List accounts = mgr.selectAll();
			for (int i = 0; i < accounts.size(); i++) {
				User user = (User) accounts.get(i);
				System.out.println("Name: " + user.getName() + " Nickname: "
						+ user.getNickname());
			}
		} else if (act.equals("get")) {
			User user = mgr.selectOneByProperty("name", "admin");
			System.out.println("Name: " + user.getName() + " Nickname: "
					+ user.getNickname());
		} else if (act.equals("delete")) {
			mgr.delete(1l);
		} else if (act.equals("account")) {
			User user = mgr.selectOneByProperty("name", "limingze");
			System.out.println(user.toString());
			System.out.println(user.getAccount().toString());
		}

		HibernateUtil.closeSession();
	}

	
}
