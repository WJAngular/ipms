package com.mtd.dao;

import java.util.List;

import com.mtd.entity.WeChat_AutoReply;

/**
 * 
 * 
 * @ClassName: WechatAutoReplyDao
 * @Description: 自动回复业务类
 * @author just_bamboo
 * @date 2016年1月16日 上午11:38:11
 *
 */
public interface WechatAutoReplyDao extends UsersDao {
	
	public List<Object> getAllAutoReply();
	/**
	 * 
	 * 
	 * @param name
	 * @return
	 * @Description TODO
	 * @author justbamboo
	 * @date 2016年1月16日 上午11:38:07
	 */
	public WeChat_AutoReply getAutoReplyByName(String name);
	
	/**
	 * 
	 * 
	 * @param key
	 * @return
	 * @Description TODO
	 * @author justbamboo
	 * @date 2016年1月16日 上午11:39:49
	 */
	public WeChat_AutoReply getAutoReplyByKey(String key);
}