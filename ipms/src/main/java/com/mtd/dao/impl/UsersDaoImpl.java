package com.mtd.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.mtd.dao.UsersDao;
import com.mtd.entity.Sys_Users;

public class UsersDaoImpl implements UsersDao {

	
	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void create(Object object) {
		sessionFactory.getCurrentSession().save(object);
	}

	public void delete(Object object) {
		sessionFactory.getCurrentSession().delete(object);
	}

	public void update(Object object) {
		sessionFactory.getCurrentSession().saveOrUpdate(object);
	}

	public Object findByID(Class<?> clazz, Integer id) {
		return sessionFactory.getCurrentSession().get(clazz, id);
	}

	public Object getByID(Class<?> clazz, long id) {
		return sessionFactory.getCurrentSession().get(clazz, id);
	}

	public void executeSql(String sql) {
	}

	@SuppressWarnings("unchecked")
	public List<Object> FindAll(String hql) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Object> l=query.list();
		return l;
	}
	
	public Sys_Users Login(String username,String password )
	{
		String hql = "from Users u where u.username=? and u.password=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, username);
		query.setString(1, password);

		return (Sys_Users) query.uniqueResult();
	}

	public Sys_Users findUserByName(String username) {
		String hql = "from Sys_Users u where u.username=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, username);

		return (Sys_Users) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Object> findByPage(String hql, int pagesize, int offset) {
		List<Object> list = sessionFactory.getCurrentSession().createQuery(hql)
				.setFirstResult((offset-1)*pagesize).setMaxResults(pagesize).list();
		return list;
	}

	public List listByHQL(String hql) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public String bindUserInfo(String wechat, String name, String idCard,
			String tel) {
		// TODO Auto-generated method stub
		return null;
	}
}
