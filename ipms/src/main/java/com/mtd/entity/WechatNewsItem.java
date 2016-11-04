package com.mtd.entity;

import java.util.ArrayList;

import com.github.sd4324530.fastweixin.api.entity.Article;
import com.mtd.util.DateUtil;

public class WechatNewsItem {

	private String mediaId;
	private String update_time;
	private ArrayList<WechatNews> news_item;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(long update_time) {
		this.update_time = DateUtil.getTimeStr(update_time);
	}

	public ArrayList<WechatNews> getNews_item() {
		return news_item;
	}

	public void setNews_item(ArrayList<WechatNews> news_item) {
		this.news_item = news_item;
	}

	
}
