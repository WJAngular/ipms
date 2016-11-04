package com.mtd.dao;


import java.util.List;

import com.mtd.entity.Sys_Users;

 /**
  * 
  * 
  * @ClassName: UserInfoDao
  * @Description: 党员信息业务接口
  * @author just_bamboo
  * @date 2015年11月14日 下午5:53:58
  *
  */
public interface UserInfoDao{
	
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
	
	/**
	 * 
	 * 
	 * @param wechat
	 * @return
	 * @Description 通过微信OpenId查询党员信息
	 * @author justbamboo
	 * @date 2015年11月14日 下午6:06:33
	 */
	public Sys_Users findUserInfoByWechat(String wechat);
	
	/**
	 * 
	 * 
	 * @return
	 * @Description 获取已绑定微信的管理员列表
	 * @author justbamboo
	 * @date 2016年1月5日 下午7:50:01
	 */
	public List<Object> findAdminUsersByWechat();
	
	/**
	 * 
	 * @param branch
	 * @return
	 * @Description 根据党委分组获取成员微信列表
	 */
	public String[] findWechatsByBranch(String branch);
}