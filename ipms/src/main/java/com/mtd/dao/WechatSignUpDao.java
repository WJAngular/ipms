package com.mtd.dao;

import com.mtd.entity.WeChat_SignUp;


public interface WechatSignUpDao extends UsersDao {
	
	/**
	 * 
	 * 
	 * @param user
	 * @param date
	 * @return
	 * @Description 查询当天签到记录
	 * @author justbamboo
	 * @date 2016年1月25日 下午5:15:09
	 */
	public WeChat_SignUp findSignByDate(String user,String date);

}