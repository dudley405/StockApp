package com.dudley.app.dao;

import org.hibernate.Session;

public interface AbstractDao {
	
    Session getSession();
 
    void persist(Object entity);
 
    void delete(Object entity);
    
    void update(Object entity);
    
    void refresh(Object entity);
    
    void flush();

}
