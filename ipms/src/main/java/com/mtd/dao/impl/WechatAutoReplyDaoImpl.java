package com.mtd.dao.impl;

import java.util.List;



import org.hibernate.Query;

import com.mtd.dao.WechatAutoReplyDao;
import com.mtd.entity.WeChat_AutoReply;

@SuppressWarnings("unchecked")
public class WechatAutoReplyDaoImpl extends UsersDaoImpl implements WechatAutoReplyDao {

	@Override
	public List<Object> getAllAutoReply() {
		String hql="from WeChat_AutoReply r where r.ruleStatus='Y'";
		return listByHQL(hql);
	}

	@Override
	public WeChat_AutoReply getAutoReplyByName(String name) {
		String hql=null;
		System.out.println(name);
		if("关注回复".equals(name)){
			hql="from WeChat_AutoReply r where r.ruleKeyword='subcribe_text'  order by id desc ";
//			hql="from WeChat_AutoReply r where r.ruleName='"+name+"'  order by id desc ";
		}else{
			hql="from WeChat_AutoReply r where r.ruleName='"+name+"' and r.ruleStatus ='Y'  order by id desc ";
		}
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List list=query.list();
		System.out.println(list.size());
		return list==null?null:(WeChat_AutoReply)list.get(0);
	}

	@Override
	public WeChat_AutoReply getAutoReplyByKey(String key) {
		String hql="from WeChat_AutoReply r where r.ruleStatus ='Y' and r.ruleKeyword  like '%"+key+"%' order by r.id desc";
		List<Object> list=listByHQL(hql);
		if(list!=null)return (WeChat_AutoReply)list.get(0);
		return null;
	}

	

}