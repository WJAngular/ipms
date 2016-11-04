package com.mtd.service.impl;

import java.util.List;

import com.mtd.dao.UsersDao;
import com.mtd.entity.Sys_Users;
import com.mtd.service.UserService;

public class UserServiceImpl implements UserService {

	private UsersDao usersDao;

	public void setusersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}
	

	public UsersDao getUsersDao() {
		return usersDao;
	}


	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}


	public void create(Object object) {
		usersDao.create(object);
	}

	public void update(Object object) {
		usersDao.update(object);
	}

	public void delete(Object object) {
		usersDao.delete(object);
	}

	public List<Object> FindAll(String HQL) {
		return usersDao.FindAll(HQL);
	}

	public Object findByID(Class<?> clazz, Integer id) {
		return usersDao.findByID(clazz, id);
	}

	public Object getByID(Class<?> clazz, long id) {
		return usersDao.getByID(clazz, id);
	}
	
	public Sys_Users Login(String username,String password ){
		return usersDao.Login(username, password);
	}

	public Sys_Users findUserByName(String username) {
		return usersDao.findUserByName(username);
	}

	public List<Object> findByPage(String hql, int pagesize, int offset) {
		return usersDao.findByPage(hql, pagesize, offset);
	}
	
	@SuppressWarnings("rawtypes")
	public List listByHQL(String hql) {
		return usersDao.listByHQL(hql);
	}
}
