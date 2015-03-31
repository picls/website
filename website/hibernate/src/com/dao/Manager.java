package com.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import test.Event;
import test.EventManager;
import test.HibernateUtil;
import test.Person;

public class Manager {
	public static Object newInstance(String className) {
		try {
			Class c = Class.forName(className);
			return c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	private void createAndStore(String classname) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		Object ci = Manager.newInstance("Article");
		
		session.save(ci);

		tx.commit();
		HibernateUtil.closeSession();
	}
	
	private List listAll(String classname) {
		Session session = HibernateUtil.currentSession();
		// Transaction tx = session.beginTransaction();

		List result = session.createQuery("from " + classname).list();

		// tx.commit();
		session.close();

		return result;
	}

	public static void main(String[] args) {
		Manager m = new Manager();
		List res = m.listAll("Article");
		//Class c = Class.forName("Article");
		//for(int i=0; i<=res.size()-1; i++)
			//System.out.println((c)res.get(i).getId());

		HibernateUtil.sessionFactory.close();
	}

	private List listEvents() {
		Session session = HibernateUtil.currentSession();
		// Transaction tx = session.beginTransaction();

		List result = session.createQuery("from Event").list();

		// tx.commit();
		session.close();

		return result;
	}

	private void addPersonToEvent(int personId, int eventId) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		Person aPerson = (Person) session.load(Person.class, personId);
		Event anEvent = (Event) session.load(Event.class, eventId);

		aPerson.getEvents().add(anEvent);

		tx.commit();
		HibernateUtil.closeSession();
	}

}
