package com.dao.s;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import base.BaseManager;
import base.HibernateUtil;

import com.dao.s.SUser;

public class SUserManager {

	final String className = "com.dao.s.SUser";

	public void insert(String name, String nickname, String level,
			String constellation) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		SUser suser = new SUser(name, nickname, level, constellation);
		session.save(suser);

		tx.commit();
		HibernateUtil.closeSession();
	}

	// create and insert one record
	public void insert(String name, String nickname, String level, int article,
			int integration, String constellation) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		SUser suser = new SUser(name, nickname, level, constellation, article,
				integration);

		session.save(suser);

		tx.commit();
		// HibernateUtil.closeSession();
	}

	// select one record by primary key
	public SUser select(long id) {
		return (SUser) BaseManager.select(className, id);
	}

	// select all records
	public List selectAll() {
		return BaseManager.selectAll(className);
	}
	
	public List selectAllUnderCon(String con, String ord) {
		return (List)BaseManager.select(className, 0, con, ord);
	}

	// select one record by property
	public SUser selectOneByProperty(String property, String value) {
		return (SUser) BaseManager.selectOneByProperty(className, property,
				value);
	}

	// select all record by property
	public List selectAllByProperty(String property, String value) {
		return (List) BaseManager.selectAllByProperty(className, property,
				value);
	}

	public void delete(long id) {
		BaseManager.delete(className, id);
	}

	public void deleteAllByProperty(String property, String value) {
		BaseManager.deleteAllByProperty(className, property, value);
	}

	public void updateAllByProperty(String property, String value) {
		BaseManager.updateProperty(className, property, value);
	}

	public static void main(String[] args) {
		SUserManager mgr = new SUserManager();

		String act = "list";

		if (act.equals("insert")) {
			// mgr.insert("admin", "admin", "admin", 99999);
		} else if (act.equals("list")) {
			List susers = mgr.selectAll();
			for (int i = 0; i < susers.size(); i++) {
				SUser suser = (SUser) susers.get(i);
				System.out.println("Name: " + suser.getName() + " Nickname: "
						+ suser.getNickname());
			}
		} else if (act.equals("get")) {
			SUser suser = mgr.selectOneByProperty("name", "admin");
			System.out.println("Name: " + suser.getName() + " Nickname: "
					+ suser.getNickname());
		} else if (act.equals("delete")) {
			mgr.delete(1l);
		}

		HibernateUtil.closeSession();
	}

}
