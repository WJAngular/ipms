package com.mtd.dao;

import java.util.List;

import com.mtd.entity.Sys_Users;
 
public interface UsersDao {
	
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
	
	/**
	 * 
	 * 
	 * @param wechat
	 * @param name
	 * @param idCard
	 * @param tel
	 * @return
	 * @Description 用户信息绑定微信
	 * @author justbamboo
	 * @date 2015年11月14日 下午5:43:09
	 */
	public String bindUserInfo(String wechat,String name,String idCard,String tel);

	//public Users findUserByIdCard(String idcard);
}