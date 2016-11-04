package com.mtd.dao;

import java.util.List;

import com.mtd.entity.WeChat_ActiUser;

public interface WechatActiUserDao extends UsersDao {
	
	/**
	 * 获取有效期内的活跃用户
	 * @return
	 */
	public List<Object> getActiUser();
	
	/**
	 * 登记一个活跃用户信息
	 * @param openedId
	 * @param actionType
	 */
	public void saveActiUser(String openedId,String actionType);
}
