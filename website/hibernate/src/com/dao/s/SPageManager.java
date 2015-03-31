package com.dao.s;

import java.util.Date;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import base.*;

public class SPageManager extends BaseManager{

	final String className = "com.dao.s.SPage";

	// create and insert one record
	public void insert(long boardId, String url, String title, int pageNum,
			Date createTime, int replyNum, long creator) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		SPage spage = new SPage();
		spage.setBoardId(boardId);
		spage.setUrl(url);
		spage.setTitle(title);
		spage.setCreator(creator);
		spage.setPageNum(pageNum);
		spage.setCreateTime(new Date());
		spage.setReplyNum(replyNum);
		session.save(spage);

		tx.commit();
		// HibernateUtil.closeSession();
	}

	// select one record by primary key
	public SPage select(long id) {
		return (SPage) BaseManager.select(className, id);
	}

	// select all records
	public List selectAll() {
		return BaseManager.selectAll(className);
	}

	public List selectAllUnderCon(String con, String ord) {
		return (List)BaseManager.select(className, 0, con, ord);
	}

	// select one record by property
	public SPage selectOneByProperty(String property, String value) {
		return (SPage) BaseManager.selectOneByProperty(className, property,
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
		SPageManager mgr = new SPageManager();
		String act = "list";

		if (act.equals("insert")) {
			mgr.insert(0, "test", "test", 0, null, 0, 0);
		} else if (act.equals("list")) {
			List posts = mgr.selectAll();
			for (int i = 0; i < posts.size(); i++) {
				SPage spage = (SPage) posts.get(i);
				System.out.println("BoardId: " + spage.getBoardId()
						+ " Title: " + spage.getTitle() + " Creator:" + spage.getCreator());
			}
		} else if (act.equals("get")) {
			SPage spage = mgr.selectOneByProperty("creator", "27");
			System.out.println("BoardId: " + spage.getBoardId() + " Title: "
					+ spage.getTitle());
		} else if (act.equals("getall")) {
			List posts = mgr.selectAllByProperty("creator", "2");
			for (int i = 0; i < posts.size(); i++) {
				SPage spage = (SPage) posts.get(i);
				System.out.println(spage.toString());
				// System.out.println("BoardId: " + spage.getBoardId()
				// + " Title: " + spage.getTitle());
			}
		} else if (act.equals("delete")) {
			mgr.delete(1l);
		} else if (act.equals("post")) {
			SPage spage = new SPage();
			spage = mgr.selectOneByProperty("id", "1548919");
			System.out.println(spage.toString());
			Set s = spage.getSposts();
			Iterator it = s.iterator();
			while (it.hasNext()) {
				SPost spost = (SPost) it.next();
				System.out.println(spost.toString());
				// System.out.println("PageId: " + spost.getPageId() +
				// " Content: "
				// + spost.getContent());
			}
		} else if (act.equals("user")) {
			SPage spage = new SPage();
			spage = mgr.selectOneByProperty("id", "1548919");
			System.out.println(spage);
			SUser suser = new SUser();
			suser = spage.getSuser();
			System.out.println(suser);
		}

		HibernateUtil.closeSession();
	}
}
