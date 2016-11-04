package com.mtd.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.mtd.dao.DictionaryDao;
import com.mtd.entity.Sys_Dictionary;

public class DictionaryDaoImpl implements DictionaryDao {


	
	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List listByHQL(String hql) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public Sys_Dictionary getEntity(String hql) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		try{
		return (Sys_Dictionary)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
