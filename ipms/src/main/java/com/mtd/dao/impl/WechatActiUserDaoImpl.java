package com.mtd.dao.impl;

import java.util.Date;
import java.util.List;

import com.mtd.dao.WechatActiUserDao;
import com.mtd.entity.WeChat_ActiUser;
@SuppressWarnings("unchecked")
public class WechatActiUserDaoImpl extends UsersDaoImpl implements WechatActiUserDao {

	@Override
	public List<Object> getActiUser() {
		//获取四十八小时内的用户
//		String sql="from WeChat_ActiUser w where w.lastTime>=dateadd(HOUR,-48,getdate()) order by w.openedId desc";
//		String sql="select distinct w from WeChat_ActiUser w where w.lastTime>=dateadd(HOUR,-48,getdate()) order by w.lastTime desc";
		String sql="from WeChat_ActiUser w where w.lastTime>=dateadd(HOUR,-48,getdate()) order by w.lastTime desc";
		return FindAll(sql);
	}

	@Override
	public void saveActiUser(String openedId, String actionType) {
//		String sql="from WeChat_ActiUser w where w.openedId='"+openedId+"'";
//		List<Object> l=FindAll(sql);
//		if(l!=null&&l.size()>0){
//			WeChat_ActiUser a=(WeChat_ActiUser)l.get(0);
//			System.out.println(a.getId()+","+a.getAction()+","+a.getOpenedId());
////			delete(a);
//			a.setAction(actionType);
//			a.setLastTime(new Date());
//			System.out.println(a.getId()+","+a.getAction()+","+a.getOpenedId());
//			update(a);
////			create(a);
//			System.out.println("已存在，修改状态："+actionType);
//		}else{
//			create(new WeChat_ActiUser(openedId, actionType));
//		}
		create(new WeChat_ActiUser(openedId, actionType));
	}

}
