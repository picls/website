package base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

import com.dao.Account;

public class BaseManager {

	public static void main(String args[]) {
		List l = (List) BaseManager.select("com.dao.s.SUser", 10,
				"where article > 10", "order by article");
		l = (List) BaseManager.selectByProperty("com.dao.s.SPage", "boardId",
				"4", 0, "where page_num >=5 order by time desc");
		// System.out.println(l);
		// l = BaseManager.selectAll("SUser");
		System.out.println(l.get(0).toString());
	}

	private String className = null;
	private static boolean closeOnce = false;

	public static void setCloseOnce(boolean flag) {
		closeOnce = flag;
	}
	
	//create and insert one record
	public void insert(Object object) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		session.save(object);

		tx.commit();
		// HibernateUtil.closeSession();
	}

	// 根据id返回选择结果
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
		if (closeOnce)
			session.close();
		return o;
	}

	//
	public static Object select(String classname, String con, String ord) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		con = con.trim();
		ord = ord.trim();
		if ((!con.startsWith("where")) && (con != null) && (con != ""))
			con = "where " + con;
		// con = con.replaceFirst("where", "");
		if ((!ord.startsWith("order by")) && (ord != null) && (ord != ""))
			ord = "order by " + con;
		// ord = ord.replaceFirst("order by", "");

		Query query = session.createQuery("from " + classname + " " + con + " " + ord);
		List result = query.list();

		tx.commit();
		if (closeOnce)
			session.close();

		return result;
	}

	//
	public static Object select(String classname, int num, String con,
			String ord) {
		List result = (List) BaseManager.select(classname, con, ord);

		if (num == 0)
			return result;
		else if (num == 1)
			if (result.size() > 0)
				return result.get(0);
			else
				return null;
		else {
			List back = new ArrayList();
			if (num <= result.size()) {
				for (int i = 0; i < num; i++)
					back.add(result.get(i));
				return back;
			} else
				return result;
		}
	}

	//
	public static Object selectByProperty(String classname, String property,
			String value, int num, String con) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		if (con.startsWith("where"))
			con = con.replaceFirst("where", "and");

		Query query = session.createQuery("from " + classname + " where "
				+ property + " = '" + value + "' " + con);
		List result = query.list();

		tx.commit();
		if (closeOnce)
			session.close();

		if (num == 0)
			return result;
		else if (num == 1)
			if (result.size() > 0)
				return result.get(0);
			else
				return null;
		else {
			List back = new ArrayList();
			if (num <= result.size()) {
				for (int i = 0; i < num; i++)
					back.add(result.get(i));
				return back;
			} else
				return result;
		}
	}

	public static Object selectByProperty(String classname, String property,
			String value, int num) {
		return selectByProperty(classname, property, value, num, "");
	}

	// 选择一个符合条件的结果
	public static Object selectByProperty(String classname, String property,
			String value) {
		return selectByProperty(classname, property, value, 1);
	}

	// 选择一个符合条件的结果
	public static Object selectOneByProperty(String classname, String property,
			String value) {
		return selectByProperty(classname, property, value, 1);
	}

	// 选作所有符合条件的结果
	public static Object selectAllByProperty(String classname, String property,
			String value) {
		return selectByProperty(classname, property, value, 0);
	}

	// 选择所有符合条件、排序的结果
	public static Object selectAllByProperty(String classname, String property,
			String value, String con) {
		return selectByProperty(classname, property, value, 0, con);
	}

	public static List selectAll(String classname, String con) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = session.createQuery("from " + classname + con).list();

		tx.commit();
		if (closeOnce)
			session.close();
		return result;
	}

	public static List selectAll(String classname) {
		return selectAll(classname, "");
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
		if (closeOnce)
			session.close();
	}

	public static void deleteAllByProperty(String classname, String property,
			String value) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		Query query = session.createQuery("delete from " + classname
				+ " where " + property + " = " + value);

		tx.commit();
		if (closeOnce)
			session.close();
	}

	public static void updateProperty(String classname, String property,
			String value) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		Query query = session.createQuery("update from " + classname
				+ " where " + property + " = " + value);

		tx.commit();
		if (closeOnce)
			session.close();
	}

}
