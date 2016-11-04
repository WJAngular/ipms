package com.wechat.myapi;


import java.util.HashMap;
import java.util.Map;

import com.github.sd4324530.fastweixin.api.CustomAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.util.BeanUtil;
import com.github.sd4324530.fastweixin.util.JSONUtil;

/**
 * 
 * 
 * @ClassName: MyCustomAPI
 * @Description: 继承原客服接口CustomAPI类，增加回复微信图文方法
 * @author just_bamboo
 * @date 下午7:33:02
 *
 */
public class MyCustomAPI extends CustomAPI {

	public MyCustomAPI(ApiConfig config) {
		super(config);
	}

	/**
	 * 
	 * 
	 * @param openid
	 * @param mediaID
	 * @return
	 * @Description 新增：客服回复微信图文消息
	 * @author justbamboo
	 * @date 2015年12月14日 下午7:35:13
	 */
	public ResultType sendCustomMpNewsMsg(String openid, String mediaID) {
		BeanUtil.requireNonNull(openid, "openid is null");
		BeanUtil.requireNonNull(mediaID, "mediaID is null");
		String url = BASE_API_URL+ "cgi-bin/message/custom/send?access_token=#";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("touser", openid);
		params.put("msgtype","mpnews");
		Map<String, Object> mpnews = new HashMap<String, Object>();
		mpnews.put("media_id", mediaID);
		params.put("mpnews", mpnews);
		BaseResponse response = executePost(url, JSONUtil.toJson(params));
        return ResultType.get(response.getErrcode());
	}

}
