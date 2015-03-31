package com.dao.s;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import base.*;

public class SPostManager extends BaseManager {

	final String className = "com.dao.s.SPost";

	// create and insert one record
	public void insert(long pageId, long creator, String content, String source) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		SPost spost = new SPost();
		spost.setPageId(pageId);
		spost.setCreator(creator);
		spost.setContent(content);
		spost.setCreateTime(new Date());
		spost.setSource(source);

		session.save(spost);

		tx.commit();
		// HibernateUtil.closeSession();
	}

	// select one record by primary key
	public SPost select(long id) {
		return (SPost) BaseManager.select(className, id);
	}

	// select all records
	public List selectAll() {
		return BaseManager.selectAll(className);
	}
	
	public List selectAllUnderCon(String con, String ord) {
		return (List)BaseManager.select(className, 0, con, ord);
	}

	// select one record by property
	public SPost selectOneByProperty(String property, String value) {
		return (SPost) BaseManager.selectOneByProperty(className, property,
				value);
	}

	// select all record by property
	public List selectAllByProperty(String property, String value) {
		return (List) BaseManager.selectAllByProperty(className, property,
				value);
	}

	public List selectAllByPropertyUnderCon(String property, String value,
			String con) {
		return (List) BaseManager.selectAllByProperty(className, property,
				value, con);
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
		SPostManager mgr = new SPostManager();
		String act = "list";

		if (act.equals("insert")) {
			mgr.insert(0, 0, "test", "test");
		} else if (act.equals("list")) {
			List posts = mgr.selectAll();
			for (int i = 0; i < posts.size(); i++) {
				SPost spost = (SPost) posts.get(i);
				System.out.println(spost.getSpage().getId());
				System.out.println(spost.toString());
				//System.out.println("PageId: " + spost.getPageId()
				//		+ " Content: " + spost.getContent());
			}
		} else if (act.equals("get")) {
			SPost spost = mgr.selectOneByProperty("creator", "27");
			System.out.println("PageId: " + spost.getPageId() + " Content: "
					+ spost.getContent());
		} else if (act.equals("getall")) {
			List posts = mgr.selectAllByProperty("creator", "12");
			for (int i = 0; i < posts.size(); i++) {
				SPost spost = (SPost) posts.get(i);
				System.out.println(spost.toString());
				//System.out.println("PageId: " + spost.getPageId()
				//		+ " Content: " + spost.getContent());
			}
		} else if (act.equals("delete")) {
			mgr.delete(1l);
		} else if (act.equals("page")) {
			SPost spost = mgr.selectOneByProperty("creator", "27");
			SPage spage = spost.getSpage();
			System.out.println(spage.toString());
		} else if (act.equals("user")) {
			SPost spost = mgr.selectOneByProperty("creator", "27");
			SUser suser = spost.getSuser();
			System.out.println(suser.toString());
		}

		HibernateUtil.closeSession();
	}
}
