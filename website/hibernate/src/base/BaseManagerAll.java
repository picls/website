package base;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

public class BaseManagerAll {
	
	private String classname;
	private String classnameFull;
	

	public static Object selectByProperty(String classname, String property,
			String value, int num) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		Query query = session.createQuery("from " + classname + " where "
				+ property + " = '" + value + "'");
		List result = query.list();

		tx.commit();
		// session.close();
		if (num == 0)
			return result;
		else if (num == 1)
			return result.get(0);
		else {
			List back = null;
			if (num <= result.size()) {
				for (int i = 0; i < num; i++)
					back.add(result.get(i));
				return back;
			} else
				return result;
		}
	}

	public static Object select(String className, long id) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		Object o = null;
		try {
			o = session.get(Class.forName(className), id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		tx.commit();
		session.close();
		return o;
	}

	public static Object selectByProperty(String classname, String property,
			String value) {
		return selectByProperty(classname, property, value, 1);
	}

	public static Object selectOneByProperty(String classname, String property,
			String value) {
		return selectByProperty(classname, property, value, 1);
	}

	public static Object selectAllByProperty(String classname, String property,
			String value) {
		return selectByProperty(classname, property, value, 0);
	}

	public static List selectAll(String classname) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = session.createQuery("from " + classname).list();

		tx.commit();
		// session.close();
		return result;
	}

	public static void delete(String className, long id) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		Object o = null;
		try {
			o = session.get(Class.forName(className), id);
			session.delete(o);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		tx.commit();
		session.close();
	}

	public static void deleteAllByProperty(String classname, String property,
			String value) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		Query query = session.createQuery("delete from " + classname
				+ " where " + property + " = " + value);

		tx.commit();
		// session.close();
	}

	public static void updateProperty(String classname, String property,
			String value) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		Query query = session.createQuery("update from " + classname
				+ " where " + property + " = " + value);

		tx.commit();
		// session.close();
	}

}
