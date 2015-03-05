package com.dudley.app.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AbstractDaoImpl {
	@Autowired
    private SessionFactory sessionFactory;
 
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
 
    public void persist(Object entity) {
        getSession().persist(entity);
    }
 
    public void delete(Object entity) {
        getSession().delete(entity);
    }
    
    public void update(Object entity) {
    	getSession().update(entity);
    }
    
    public void refresh(Object entity) {
    	getSession().flush();
    }
    
    public void flush() {
    	getSession().flush();
    }
    
    public void clear() {
    	getSession().clear();
    }
}
