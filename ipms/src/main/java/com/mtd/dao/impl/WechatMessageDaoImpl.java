package com.mtd.dao.impl;

import javax.annotation.Resource;

import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.api.response.GetSendMessageResponse;
import com.github.sd4324530.fastweixin.message.MpNewsMsg;
import com.mtd.dao.UserInfoDao;
import com.mtd.dao.WechatMessageDao;
import com.wechat.WeChatApiConfig;
import com.wechat.myapi.MyMessageAPI;


public class WechatMessageDaoImpl implements WechatMessageDao {

	private MyMessageAPI api;
	@Resource
	private UserInfoDao userInfo;

	public WechatMessageDaoImpl() {
		api = new MyMessageAPI(WeChatApiConfig.getApiConfig());
	}

	@Override
	public GetSendMessageResponse sendMessageToUsers(String mediaId,
			String[] openIds) {
		return api.sendMessageToUser(new MpNewsMsg(mediaId), false, null, openIds);
	}

	@Override
	public BaseResponse previewMessageToUser(String mediaId, String openId) {
		return api.previewMessageToUser(mediaId, openId);
	}

	@Override
	public GetSendMessageResponse sendMessageToUsers(String mediaId, String branch) {
		String[] opendIds=userInfo.findWechatsByBranch(branch);
		if(opendIds!=null){
			sendMessageToUsers(mediaId, opendIds);
		}
		return null;
	}

}
