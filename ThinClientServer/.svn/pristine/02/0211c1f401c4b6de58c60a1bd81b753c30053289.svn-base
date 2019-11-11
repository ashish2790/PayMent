package com.awl.tch.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public interface GenericDao<T> {
	
	//Create
    public int save(T t);
    //Read
    public T getById(String columnNames, Object... id);
    
    // Read overload
    public T getById(Object id);
    
    //Update
    public int update(T t);
    //Delete
    public int deleteById(Object id);
    //Get All
    public List<T> getAll();
    
    public long getSequence(String sequenceName);
    
    // save information in other schema
    public int save(T t, JdbcTemplate jdbcTemplate);
}
