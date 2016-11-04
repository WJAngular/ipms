package com.mtd.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import com.github.sd4324530.fastweixin.api.MenuAPI;
import com.github.sd4324530.fastweixin.api.OauthAPI;
import com.github.sd4324530.fastweixin.api.entity.Menu;
import com.github.sd4324530.fastweixin.api.entity.MenuButton;
import com.github.sd4324530.fastweixin.api.enums.MenuType;
import com.github.sd4324530.fastweixin.api.enums.OauthScope;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.mtd.dao.WechatMenuDao;
import com.mtd.entity.Cms_Content;
import com.wechat.WeChatApiConfig;
import com.wechat.bean.Articles;
import com.wechat.bean.NewsOutMessage;
import com.wechat.util.ConfKit;
import com.wechat.util.MessageUtil;

public class WechatMenuDaoImpl extends UsersDaoImpl implements WechatMenuDao {
	private OauthAPI oauth;

	public WechatMenuDaoImpl() {
		oauth=new OauthAPI(new WeChatApiConfig().getApiConfig());
//		oauth=new OauthAPI(WeChatApiConfig.getApiConfig());
	}
	
	
	@Override
	public String handleRequest(String fromUserName, String toUserName,
			String eventKey) {
		if (eventKey.equals("user")) {
			// return getTextOutMessage(fromUserName, toUserName,"用户绑定");
//			String s = getNewsMessage(fromUserName, toUserName);
		}
		return null;
	}

	private String getNewsMessage(String fromUserName, String toUserName) {
		NewsOutMessage news = new NewsOutMessage();
		news.setToUserName(toUserName);
		news.setFromUserName(fromUserName);
		news.setCreateTime(new Date().getTime());
		// news.setTitle("");
		news.setArticles(getNewsList(toUserName));
		// news.setArticles(getList());
		System.out.println(news);
		return MessageUtil.newsMessageToXml(news);
	}

	private ArrayList<Articles> getNewsList(String toUserName) {
		ArrayList<Articles> list = new ArrayList<Articles>();
		Articles a = new Articles();
		a.setUrl(ConfKit.get("Host") + "/user/wechat/getUserinfo.do?wechat="
				+ toUserName);
		a.setTitle("用户绑定");
		a.setDescription("党员信息绑定");
		list.add(a);
		return list;
	}

	private ArrayList<Articles> getList() {
		String hql = "from Cms_Content as cms where cms.status='Y' and cms.topic=? and cms.isvaild=? ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, "党建新闻");
		query.setString(1, "Y");
		List<Cms_Content> cms = query.list();
		ArrayList<Articles> list = new ArrayList<Articles>();
		for (Cms_Content c : cms) {
			Articles a = new Articles();
			a.setDescription(c.getAbstracts());
			a.setPicUrl(c.getPicMedia());
			a.setUrl(ConfKit.get("Host"));
			a.setTitle(c.getTitle());
			list.add(a);
		}
		return list;
	}

	public static void main(String[] args){
		// String s=new WechatMenuDaoImpl().handleRequest("111", "222", "user");
//		System.out.print(WeChat.getAccessToken());
		new WechatMenuDaoImpl().createMenu();
//		new WechatMenuDaoImpl().test();
	}

//	@Override
//	public String test() {
//		// ArrayList<Articles> list=getList();
//		String s = "<xml>"
//				+ "<MsgType><![CDATA[text]]></MsgType>"
//				+ "<Content><![CDATA[处理点击菜单选项]]></Content>"
//				+ "<ToUserName><![CDATA[oV4VowOSb45hC8b43DPiZ5kOydf8]]</ToUserName>"
//				+ "<FromUserName><![CDATA[gh_3242a3f93876]]></FromUser>"
//				+ "<CreateTime>"+new Date().getTime()+"</CreateTime>"
//				+ "</xml>";
//		return s;
//	}
	
	
	public void test(){
		System.out.println(oauth.getOauthPageUrl(WeChatApiConfig.WEB_HOST+"/wechat/service.do", OauthScope.SNSAPI_BASE, "009"));
	}

	@Override
	public void createMenu() {
		//TODO 补充创建菜单
		
		Menu menu=new Menu();
		MenuAPI api=new MenuAPI(new WeChatApiConfig().getApiConfig());
		
		
		/** 微动态 */
		MenuButton menu1 = new MenuButton();
		menu1.setName("微动态");
		List<MenuButton> sub1 = new ArrayList<MenuButton>();
		// MenuButton mb11 = new MenuButton();
		// mb11.setName("微首页");
		// mb11.setType(MenuType.VIEW);
		// mb11.setUrl(WeChatApiConfig.WEB_HOST+"/wechat/home.jsp");
		MenuButton mb12 = new MenuButton();
		mb12.setName("公告公示");
		mb12.setType(MenuType.CLICK);
		mb12.setKey("notice");
		MenuButton mb13 = new MenuButton();
		mb13.setName("党建要闻 ");
		mb13.setType(MenuType.CLICK);
		mb13.setKey("news_list");
		MenuButton mb14 = new MenuButton();
		mb14.setName("社区动态");
		mb14.setType(MenuType.CLICK);
		mb14.setKey("activity_news");
		// sub1.add(mb11);
		sub1.add(mb13);
		sub1.add(mb12);
		sub1.add(mb14);
		menu1.setSubButton(sub1);
		/** 微党校 */
		MenuButton menu2 = new MenuButton();
		menu2.setName("微党校");
		List<MenuButton> sub2 = new ArrayList<MenuButton>();
		MenuButton mb21 = new MenuButton();
		mb21.setName("在线学习");
		mb21.setType(MenuType.VIEW);
		mb21.setUrl(oauth.getOauthPageUrl(WeChatApiConfig.WEB_HOST+"/cms_course/wechat/getWechat_Course.do", OauthScope.SNSAPI_BASE, "#"));
		MenuButton mb22 = new MenuButton();
		mb22.setName("政策文件");
		mb22.setType(MenuType.CLICK);
		mb22.setKey("zc_news_list");
		// mb22.setUrl(WeChatApiConfig.WEB_HOST+"/wechat/farming.jsp");
		// MenuButton mb23 = new MenuButton();
		/*mb23.setName("民主评论");
		mb23.setType(MenuType.CLICK);
		mb23.setKey("user");*/
		MenuButton mb24 = new MenuButton();
		mb24.setName("互动交流");
		mb24.setType(MenuType.VIEW);
		mb24.setUrl(oauth.getOauthPageUrl(WeChatApiConfig.WEB_HOST+"/wechat/topic/toTopicPage.do", OauthScope.SNSAPI_BASE, "#"));
		sub2.add(mb22);
		sub2.add(mb21);
		/*sub2.add(mb23);*/
		sub2.add(mb24);
		menu2.setSubButton(sub2);
		/**微服务 */
		MenuButton menu3 = new MenuButton();
		menu3.setName("微服务");
		List<MenuButton> sub3= new ArrayList<MenuButton>();
		MenuButton mb31=new MenuButton();
		mb31.setName("我是党员");
		mb31.setType(MenuType.VIEW);
		String url=oauth.getOauthPageUrl(WeChatApiConfig.WEB_HOST+"/user/wechat/binduser.do", OauthScope.SNSAPI_USERINFO, "#");
		mb31.setUrl(url);
		// MenuButton mb32=new MenuButton();
		// mb32.setName("党员发展流程");
		// mb32.setType(MenuType.VIEW_LIMITED);
		// mb32.setMediaId("3CKHMerPu14wj77OQhKMlWPTFU1k00DjXvMJx3dK9y4");
//		mb32.setType(MenuType.VIEW);
//		mb32.setUrl(WeChatApiConfig.WEB_HOST+"/wechat/interact.html");
		MenuButton mb33=new MenuButton();
		mb33.setName("在线调查");
		mb33.setType(MenuType.CLICK);
		mb33.setKey("online_survey_list");
//		mb33.setType(MenuType.VIEW);
//		mb33.setUrl(oauth.getOauthPageUrl(WeChatApiConfig.WEB_HOST+"/cms_survey/wechat/getWechatSurvetList.do", OauthScope.SNSAPI_BASE, "#"));
		// MenuButton mb34=new MenuButton();
		// mb34.setName("服务机构地图 ");
		// mb34.setType(MenuType.VIEW);
		// mb34.setUrl(WeChatApiConfig.WEB_HOST+"/wechat/map.jsp");
		MenuButton mb35=new MenuButton();
		mb35.setName("便民服务");
		mb35.setType(MenuType.VIEW);
		mb35.setUrl(oauth.getOauthPageUrl(WeChatApiConfig.WEB_HOST+"/wechat/service.do", OauthScope.SNSAPI_BASE, "#"));
//		mb35.setUrl(WeChatApiConfig.WEB_HOST+"/wechat/services.jsp");
		sub3.add(mb31);
		// sub3.add(mb32);
		sub3.add(mb33);
		// sub3.add(mb34);
		sub3.add(mb35);
		menu3.setSubButton(sub3);
		
		List<MenuButton> menuList = new ArrayList<MenuButton>();
		menuList.add(menu1);
		menuList.add(menu2);
		menuList.add(menu3);
		menu.setButton(menuList);
		ResultType s=api.createMenu(menu);
		System.out.println(s);
	}
	
	

}
