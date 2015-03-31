package base;

public class Base{
	
}
/*
package com.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;


public class Base extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(BaseDao.class);
	 (non-Javadoc)
	 * @see com.weibo.model.IBaseDao#save(java.lang.Object)
	 
    public void save(Object vo) throws DataAccessException{
    	try {
			getHibernateTemplate().save(vo);
		} catch (RuntimeException re) {
			throw re;
		}
    } 
     (non-Javadoc)
	 * @see com.weibo.model.IBaseDao#update(java.lang.Object)
	 
    public void update(Object vo) throws DataAccessException{
        getHibernateTemplate().update(vo);
    } 
     (non-Javadoc)
	 * @see com.weibo.model.IBaseDao#deleteAll(java.util.List)
	 
    public void deleteAll(List<Object> delList){
    	getHibernateTemplate().deleteAll(delList);
    }
     (non-Javadoc)
	 * @see com.weibo.model.IBaseDao#delete(java.lang.Object)
	 
    public void delete(Object vo) throws DataAccessException{
        getHibernateTemplate().delete(vo);
    }  
	 (non-Javadoc)
	 * @see com.weibo.model.IBaseDao#HQLQuery(java.lang.String)
	 
    public List HQLQuery(String hql) throws DataAccessException{
         return getHibernateTemplate().find(hql);
    }
     (non-Javadoc)
	 * @see com.weibo.model.IBaseDao#HQLQuery(java.lang.String, java.lang.Object[])
	 
    public List HQLQuery(String hql,Object[] objects) throws DataAccessException{
         return getHibernateTemplate().find(hql,objects);
    }
    *//**
     * ÷¥––sql≤È—Ø
     * @param sql
     * @return
     *//*
    @SuppressWarnings("finally")
    public List execSQLQuery(String sql) {
    	List sucess = null;
    	Session sess = null;
    	try {
    		SessionFactory sf = getHibernateTemplate().getSessionFactory();
    		sess = sf.openSession(); 
    		Query query = sess.createSQLQuery(sql);
    		sucess = query.list(); 
    	} catch (DataAccessException e) {
    		log.error("DataAccessException",e);
    		sucess = new ArrayList();
    	} finally {
    		sess.close();
    		return sucess;
    	}
    }
}*/