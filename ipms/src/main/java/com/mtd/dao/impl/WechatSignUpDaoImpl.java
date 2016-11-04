package com.mtd.dao.impl;

import java.util.List;

import com.mtd.dao.WechatSignUpDao;
import com.mtd.entity.WeChat_SignUp;


@SuppressWarnings("unchecked")
public class WechatSignUpDaoImpl extends UsersDaoImpl implements WechatSignUpDao {

	@Override
	public WeChat_SignUp findSignByDate(String user, String date) {
		String hql="from WeChat_SignUp w where w.sign_user='"+user+"' and w.sign_date='"+date+"'";
		List<Object> l=listByHQL(hql);
		if(l!=null&&l.size()!=0){
			return (WeChat_SignUp)l.get(0);
		}
		return null;
	}

	

}