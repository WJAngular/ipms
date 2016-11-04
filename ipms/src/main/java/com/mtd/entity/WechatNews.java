package com.mtd.entity;

import com.github.sd4324530.fastweixin.api.entity.Article;

/**
 * 
 * 
 * @ClassName: WechatNews
 * @Description: 请求图文消息返回图文实体类，增加url
 * @author just_bamboo
 * @date 2015年12月30日 上午9:54:31
 *
 */
public class WechatNews extends Article  {
	
	private String url;

	@Override
	public String getContent() {
		return super.getContent().replace("\"", "\'");
	}
	
	public WechatNews() {
		super();
	}

	public WechatNews(String thumbMediaId, String author, String title,
			String contentSourceUrl, String content, String digest,
			Integer showConverPic,String url) {
		super(thumbMediaId, author, title, contentSourceUrl, content, digest,
				showConverPic);
		this.url=url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
