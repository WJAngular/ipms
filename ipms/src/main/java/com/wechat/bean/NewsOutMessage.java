/* 
 * jeasyPro
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/
 * 2013-8-11 下午3:32:55
 */
package com.wechat.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 输出图文消息
 * 
 *
 * 
 */
public class NewsOutMessage  {

	private String MsgType = "news";
	private Integer ArticleCount;
	private String Title;
	private String Description;
	private String PicUrl;
	private String Url;

	private List<Articles> Articles;

	protected String ToUserName;
	protected String FromUserName;
	protected Long CreateTime;

	public String getMsgType() {
		return MsgType;
	}

	public int getArticleCount() {
		return ArticleCount;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public List<Articles> getArticles() {
		return Articles;
	}

	public void setArticles(List<Articles> articles) {
		if (articles != null) {
			if (articles.size() > 10)
				articles = new ArrayList<Articles>(articles.subList(0, 10));

			ArticleCount = articles.size();
		}
		Articles = articles;
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.FromUserName + "," + this.ToUserName + "," + this.MsgType
				+ "," + this.Description;
	}
}
