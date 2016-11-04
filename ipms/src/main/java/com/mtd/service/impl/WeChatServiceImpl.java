package com.mtd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.sd4324530.fastweixin.api.entity.Article.ShowConverPic;
import com.github.sd4324530.fastweixin.api.enums.MaterialType;
import com.github.sd4324530.fastweixin.api.enums.MediaType;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.api.response.GetMaterialListResponse;
import com.github.sd4324530.fastweixin.api.response.GetMaterialTotalCountResponse;
import com.github.sd4324530.fastweixin.api.response.UploadMaterialResponse;
import com.github.sd4324530.fastweixin.message.Article;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.NewsMsg;
import com.github.sd4324530.fastweixin.message.req.MenuEvent;
import com.mtd.dao.WechatActiUserDao;
import com.mtd.dao.WechatMediaDao;
import com.mtd.dao.WechatMenuDao;
import com.mtd.dao.WechatMessageDao;
import com.mtd.entity.MediaNewsReqest;
import com.mtd.entity.WechatNews;
import com.mtd.entity.WechatNewsItem;
import com.mtd.entity.WechatNewsVO;
import com.mtd.service.UserService;
import com.mtd.service.WeChatService;
import com.wechat.bean.TextOutMessage;
import com.wechat.util.MessageUtil;

@Service
public class WeChatServiceImpl implements WeChatService {

	private WechatMenuDao menu;

	private WechatMediaDao media;

	private WechatMessageDao message;
	
	private UserService userService;

	
	private WechatActiUserDao actiDao;

	@Override
	public String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 事件类型
			String event = requestMap.get("Event");
			// 事件KEY值
			String eventKey = requestMap.get("EventKey");

			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT) && event.equals(MessageUtil.EVENT_TYPE_CLICK)) {
				// TODO 处理点击菜单选项事件
				respMessage = menu.handleRequest(toUserName, fromUserName, eventKey);
				if (respMessage == null) {
					respContent = "处理点击菜单选项";
					respMessage = getTextOutMessage(toUserName, fromUserName, respContent);
				}
				respMessage = (respMessage != null ? respMessage : "");
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)
					&& event.equals(MessageUtil.EVENT_TYPE_SCANCODE_WAITING)) {
				// TODO 处理二维码扫描事件

				// respMessage = new QRCodeDao().handlerQRCore(toUserName,
				// fromUserName, requestMap.get("ScanResult"));
				// respMessage = (respMessage != null ? respMessage : "");
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)
					&& event.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
				respContent = "感谢您的关注！！！";
				respMessage = getTextOutMessage(toUserName, fromUserName, respContent);
			} else {
				respContent = "无效操作";
				respMessage = getTextOutMessage(toUserName, fromUserName, respContent);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(respMessage);
		return respMessage;
		// return null;
	}

	private String getTextOutMessage(String fromUserName, String toUserName, String content) {
		TextOutMessage textMessage = new TextOutMessage();
		textMessage.setToUserName(toUserName);
		textMessage.setFromUserName(fromUserName);
		textMessage.setCreateTime(new Date().getTime());
		// textMessage.setFuncFlag(0);
		textMessage.setContent(content);
		return MessageUtil.textMessageToXml(textMessage);
	}

	public WechatMenuDao getMenu() {
		return menu;
	}

	public void setMenu(WechatMenuDao menu) {
		this.menu = menu;
	}

	public WechatMediaDao getMedia() {
		return media;
	}

	public void setMedia(WechatMediaDao media) {
		this.media = media;
	}

	public WechatMessageDao getMessage() {
		return message;
	}

	public void setMessage(WechatMessageDao message) {
		this.message = message;
	}

	@Override
	public String test() {
		NewsMsg n = (NewsMsg) respMsgByKey("news_list","");
		return n.toString();
	}


	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public BaseMsg handleMenuClick(MenuEvent event) {
		String key = ((MenuEvent) event).getEventKey();
		return respMsgByKey(key,event.getFromUserName());
	}

	/**
	 * 
	 * 
	 * @param key
	 * @return
	 * @Description TODO
	 * @author justbamboo
	 * @date 2015年11月27日 下午6:27:01
	 */
	private BaseMsg respMsgByKey(String key,String target) {
		ArrayList<Article> list = null;
		if (key.equals("news_list")) {
			list = getArticleList("党建要闻",target);
		} else if (key.equals("activity_news")) {
			list = getArticleList("社区动态",target);
		}else if (key.equals("notice")) {
			list = getArticleList("公告公示",target);
		}else if (key.equals("zc_news_list")) {
			list = getArticleList("政策文件",target);
		}
		return new NewsMsg(list);
	}

	

	/**
	 * 
	 * 
	 * @param type
	 * @return
	 * @Description TODO
	 * @author justbamboo
	 * @date 2015年11月27日 下午6:37:08
	 */
	private ArrayList<Article> getArticleList(String type,String target) {
		/*ArrayList<Cms_Content> list = newsService.getNewslist(type);
		ArrayList<Article> artList = new ArrayList<Article>();
		for (int i = 0; i < list.size(); i++) {
			Cms_Content con = list.get(i);
			Article a = new Article(con.getTitle(), con.getAbstracts(), con.getPicMediaUrl(),
					ConfKit.get("Host") + "/newslist/wechat/showDetail.do?cmsID=" + con.getId()+"&&user="+target);
			artList.add(a);
		}*/
		return null;
	}

	/*private ArrayList<Article> getSurveyArticleList(String target) {
		List<Cms_Survey> list = userService.listByHQL("from Cms_Survey c where c.isvaild='Y'  and c.status='Y' order by c.id desc");
		ArrayList<Article> artList = new ArrayList<Article>();
		for (int i = 0; i < list.size(); i++) {
			Cms_Survey survey=list.get(i);
			Article a = new Article(survey.getTitle(), survey.getAbstracts(), survey.getPicMediaUrl(),
					ConfKit.get("Host") + "/cms_survey/wechat/getWechatSurveyChoice.do?id=" + survey.getId()+"&&user="+target);
			artList.add(a);
		}
		return artList;
	}*/

	
	@Override
	public String getMediaList(MediaType type, int page) {

		return null;
	}

	@Override
	public String getMaterialUrlList(MaterialType type, int page) {
		return media.getMaterialUrlList(type, page);
	}

	@Override
	public boolean addMaterialNews(String newsJson) {
		ArrayList<MediaNewsReqest> lists = new ArrayList<MediaNewsReqest>(
				JSONArray.parseArray(newsJson, MediaNewsReqest.class));
		List<com.github.sd4324530.fastweixin.api.entity.Article> list = new ArrayList<>();
		for (int i = (lists.size() - 1); i >= 0; i--) {// 需要倒序
			MediaNewsReqest obj = lists.get(i);
			String thumbMediaId = obj.getPicId();
			String author = obj.getAuthor();
			String title = obj.getTitle();
			String contentSourceUrl = obj.getUrl();
			String content = obj.getContents();
			String digest = obj.getAbstracts();
			com.github.sd4324530.fastweixin.api.entity.Article article = new com.github.sd4324530.fastweixin.api.entity.Article(
					thumbMediaId, author, title, contentSourceUrl, content, digest, ShowConverPic.YES);
			list.add(article);
		}
		UploadMaterialResponse rsp = media.addMaterialNews(list);
		System.out.println(rsp.getErrcode() + "," + rsp.getErrmsg() + "," + rsp.getMediaId());
		return rsp.getMediaId() != null;
	}

	@Override
	public boolean updateMaterialNews(String newsJson, int index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getMaterialList(MaterialType type, int page) {
		return media.getMaterialList(type, page);
	}

	public static void main(String[] args) {
//		BaseMsg m = new WeChatServiceImpl().respMsgByKey("news_list");
//		System.out.println(m == null ? "null" : m);
	}

	@Override
	public WechatNewsVO getWechatNewsList(int page) {
		GetMaterialListResponse resp = media.getMaterial(MaterialType.NEWS, page);
		WechatNewsVO vo = new WechatNewsVO();
		if (resp != null) {
			vo.setPage(page);
			vo.setTotal(resp.getTotalCount());
			List<Map<String, Object>> itemList = resp.getItems();
			ArrayList<WechatNewsItem> items = new ArrayList<WechatNewsItem>();
			if (itemList == null) {
				System.out.println("图文列表获取失败" + resp.getErrcode() + "," + resp.getErrmsg());
				return vo;
			}
			for (Map<String, Object> m : itemList) {
				WechatNewsItem item = JSON.parseObject(m.get("content").toString(), WechatNewsItem.class);
				item.setMediaId((String) m.get("media_id"));
				item.setUpdate_time(Long.valueOf((Integer) m.get("update_time")));
				items.add(item);
			}
			vo.setItems(items);
			System.out.println(JSON.toJSON(vo));

		} else {
			System.out.print("图文列表获取失败" + resp.getErrcode() + "," + resp.getErrmsg());
			vo.setPage(page);
			vo.setTotal(page);
		}
		return vo;
	}

	@Override
	public WechatNews getWechatNews(String mediaID, int index) {
		ArrayList<WechatNews> newsList = media.getMaterialNews(mediaID);
		if (newsList != null) {
			return newsList.get(index);
		}
		return null;
	}

	@Override
	public GetMaterialTotalCountResponse getWechatMediaCount() {
		return media.MaterialTotalCount();
	}

	@Override
	public GetMaterialListResponse getMediaImage(int page) {
		return media.getMaterial(MaterialType.IMAGE, page);
	}

	@Override
	public BaseResponse previewMessage(String mediaID, String openId) {
		return message.previewMessageToUser(mediaID, openId);
	}


	@Override
	public void saveActiUser(String openedId, String actionType) {
		actiDao.saveActiUser(openedId, actionType);
	}

	public WechatActiUserDao getActiDao() {
		return actiDao;
	}

	public void setActiDao(WechatActiUserDao actiDao) {
		this.actiDao = actiDao;
	}

	@Override
	public BaseMsg handleSubscribe() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public BaseMsg handleTextMsg(String text) {
		// TODO 自动生成的方法存根
		return null;
	}
	
	

}
