package com.mtd.dao;


 
public interface WechatMenuDao extends UsersDao  {
	

	/**
	 * 
	 * 
	 * @param fromUserName
	 * @param toUserName
	 * @param eventKey
	 * @return
	 * @Description 处理菜单点击事件
	 * @author justbamboo
	 * @date 2015年11月14日 下午7:31:15
	 */
	public String handleRequest(String fromUserName, String toUserName,
			String eventKey);
	
	/**
	 * 
	 * 
	 * @Description 创建自定义菜单
	 * @author justbamboo
	 * @date 2015年12月16日 下午5:23:44
	 */
	public void createMenu();
}