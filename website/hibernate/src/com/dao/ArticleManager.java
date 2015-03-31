package com.dao;

import org.hibernate.Transaction;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

import base.*;

public class ArticleManager extends BaseManager{

	//create and insert one record
	public void insert(String board, String title, long creator) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		Article article = new Article();
		article.setBoard(board);
		article.setTitle(title);
		article.setCreator(creator);
		article.setNumber(1);
		article.setCreateTime(new Date());
		article.setLastChangeTime(new Date());

		session.save(article);

		tx.commit();
		// HibernateUtil.closeSession();
	}

	//select one record by primary key
	public Article select(long id) {
		return (Article)BaseManager.select("com.dao.Article", id);
	}
	
	//select all records 
	public List selectAll() {
		return BaseManager.selectAll("Article");
	}

	//select one record by property
	public Article selectOneByProperty(String property, String value) {
		return (Article) BaseManager.selectOneByProperty("Article", property, value);
	}

	//select all record by property
	public List selectAllByProperty(String property, String value) {
		return (List) BaseManager.selectAllByProperty("Article", property, value);
	}

	public void delete(long id) {
		BaseManager.delete("com.dao.Article", id);
	}

	public void deleteAllByProperty(String property, String value) {
		BaseManager.deleteAllByProperty("Article", property, value);
	}
	
	public void updateAllByProperty(String property, String value) {
		BaseManager.updateProperty("Article", property, value);
	}
	
	public static void main(String[] args) {
		ArticleManager mgr = new ArticleManager();
		String act = "get";

		if (act.equals("insert")) {
			mgr.insert("football", "an article.", 1l);
		} else if (act.equals("list")) {
			List articles = mgr.selectAll();
			for (int i = 0; i < articles.size(); i++) {
				Article article = (Article) articles.get(i);
				System.out.println("Title: " + article.getTitle() + " Number: "
						+ article.getNumber());
			}
		} else if (act.equals("get")) {
			Article article = mgr.selectOneByProperty("title", "an article.");
			System.out.println("Title: " + article.getTitle() + " Number: "
					+ article.getNumber());
		} else if (act.equals("delete")) {
			mgr.delete(1l);
		}

		HibernateUtil.closeSession();
	}

}
