package com.mtd.service;

import java.util.List;

import com.mtd.entity.Sys_Users;

public interface UserService {

    public void create(Object object);
	
   	public void update(Object object);
   	
   	public void delete(Object object);
   	
   	public List<Object> FindAll(String HQL) ;
   	
    public Object findByID(Class<?> clazz, Integer id) ;
   	
   	public Object getByID(Class<?> clazz, long id);

   	public Sys_Users Login(String username,String password );

	public Sys_Users findUserByName(String username);
   	
   	public List<Object> findByPage(String hql,int pagesize,int offset);
   	
   	public List listByHQL(String hql) ;
   	
}
