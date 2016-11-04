package com.wechat;

import java.util.ArrayList;

import com.github.sd4324530.fastweixin.handle.EventHandle;
import com.github.sd4324530.fastweixin.message.Article;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.NewsMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import com.github.sd4324530.fastweixin.message.req.EventType;
import com.github.sd4324530.fastweixin.message.req.MenuEvent;

/**
 * 
 * 
 * @ClassName: WeChatEventHandle
 * @Description: 自定义微信菜单处理器
 * @author just_bamboo
 * @date 2015年11月25日 下午5:55:34
 *
 */
public class WeChatEventHandle implements EventHandle<BaseEvent> {

	@Override
	public BaseMsg handle(BaseEvent event) {
		String type = event.getEvent();
		if (type.equals(EventType.CLICK)) {
			String key = ((MenuEvent) event).getEventKey();
			return respMsgByKey(key);
		}
		return null;
	}

	@Override
	public boolean beforeHandle(BaseEvent event) {
		return false;
	}

	/**
	 * 
	 * 
	 * @param key
	 * @return
	 * @Description TODO
	 * @author justbamboo
	 * @date 2015年11月27日 上午10:10:34
	 */
	private BaseMsg respMsgByKey(String key) {
		ArrayList<Article> list = null;
		if (key.equals("news_list")) {
			list = getArticleList("党建要闻");
		} else if (key.equals("activity_news")) {
			list = getArticleList("活动剪影");
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
	 * @date 2015年11月27日 上午11:13:03
	 */
	private ArrayList<Article> getArticleList(String type) {
		/*ArrayList<Cms_Content> list = newsService.getNewslist(type);
		ArrayList<Article> artList = new ArrayList<Article>();
		for (int i = 0; i < list.size(); i++) {
			Cms_Content con = list.get(i);
			Article a = new Article(con.getTitle(), con.getAbstracts(),
					con.getPicUrl(), "http://m.baidu.com");
			artList.add(a);
		}*/
		return null;
	}

}
