/**
 * 微信公众平台�?发模�?(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.wechat.inf;

import com.wechat.bean.InMessage;
import com.wechat.bean.OutMessage;
/**
 * 消息处理�?
 * @author GodSon
 *
 */
public interface MessageProcessingHandler {
	
	/**
	 * 统一处理�?
	 * @param msg
	 * @return
	 */
	public void allType(InMessage msg);
	
	/**
	 * 文字内容的消息处�?
	 * @param msg
	 * @return
	 */
	public void textTypeMsg(InMessage msg);
	
	/**
	 * 地理位置类型的消息处�?
	 * @param msg
	 * @return
	 */
	public void locationTypeMsg(InMessage msg);
	
	/**
	 * 图片类型的消息处�?
	 * @param msg
	 * @return
	 */
	public void imageTypeMsg(InMessage msg);
	
	/**
	 * 视频类型的消息处�?
	 * @param msg
	 * @return
	 */
	public void videoTypeMsg(InMessage msg);
	
	/**
	 * 链接类型的消息处�?
	 * @param msg
	 * @return
	 */
	public void linkTypeMsg(InMessage msg);

	/**
	 * 语音类型的消息处�?
	 * @param msg
	 * @return
	 */
	public void voiceTypeMsg(InMessage msg);
	
	/**
	 * 验证消息处理
	 * @param msg
	 * @return
	 */
	public void verifyTypeMsg(InMessage msg);

	/**
	 * 事件类型的消息处理�??<br/>
	 * 在用户首次关注公众账号时，系统将会推送一条subscribe的事�?
	 * @param msg
	 * @return
	 */
	public void eventTypeMsg(InMessage msg);

	/**
	 * 处理流程结束，返回输出信息之前执�?
	 */
	public void afterProcess(InMessage inMsg,OutMessage outMsg);
	
	/**
	 * 设置输出
	 * @param outMessage
	 */
	public void setOutMessage(OutMessage outMessage);
	
	/**
	 * 处返回输出对�?
	 */
	public OutMessage getOutMessage();
	
}
