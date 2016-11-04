package com.mtd.dao;

import java.util.List;

import com.mtd.entity.Sys_Dictionary;

public interface DictionaryDao {
	public List listByHQL(String hql);
	public Sys_Dictionary getEntity(String hql);
}
