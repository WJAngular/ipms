package com.mtd.dao;


import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.api.response.GetSendMessageResponse;

/**
 * 
 * 
 * @ClassName: WechatMessageDao
 * @Description: 微信发送信息业务类
 * @author just_bamboo
 * @date 2016年1月5日 下午4:04:30
 *
 */
public interface WechatMessageDao {
	
	/**
	 * 
	 * 
	 * @param mediaId
	 * @param opendIds
	 * @return
	 * @Description 群发消息
	 * @author justbamboo
	 * @date 2016年1月5日 下午4:07:27
	 */
	public GetSendMessageResponse  sendMessageToUsers(String mediaId,String[] opendIds);
	
	/**
	 * 
	 * @param mediaId
	 * @param branch
	 * @return
	 * @Description 根据党委分组群发消息
	 * @author justbamboo
	 */
	public GetSendMessageResponse  sendMessageToUsers(String mediaId,String branch);
	/**
	 * 
	 * 
	 * @param mediaId
	 * @param opendId
	 * @return
	 * @Description 图文预览
	 * @author justbamboo
	 * @date 2016年1月5日 下午4:07:30
	 */
	public BaseResponse  previewMessageToUser(String mediaId,String opendId);
}
