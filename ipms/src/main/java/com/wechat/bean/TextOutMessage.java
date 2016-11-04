/* 
 * jeasyPro
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/
 * 2013-8-11 下午3:31:50
 */
package com.wechat.bean;

/**
 * 输出文字消息
 * 
 *
 * 
 */
public class TextOutMessage {

	private String	MsgType	= "text";
	// 文本消息
	private String	Content;
	
	protected String ToUserName;
	protected String FromUserName;
	protected Long CreateTime;
	
	public TextOutMessage() {
	 
	}
	
	public TextOutMessage(String content) {
		Content = content;
	}

	public String getMsgType() {
		return MsgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
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
	
	
	
	
	
}
