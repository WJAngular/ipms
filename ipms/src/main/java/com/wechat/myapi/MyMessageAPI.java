package com.wechat.myapi;

import java.util.HashMap;
import java.util.Map;
import com.github.sd4324530.fastweixin.api.MessageAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.api.response.GetSendMessageResponse;
import com.github.sd4324530.fastweixin.util.JSONUtil;

/**
 * 
 * 
 * @ClassName: MyMessageAPI
 * @Description: TODO
 * @author just_bamboo
 * @date 2016年1月5日 下午3:58:29
 *
 */
public class MyMessageAPI extends MessageAPI {

	public MyMessageAPI(ApiConfig config) {
		super(config);
	}

	/**
	 * 
	 * 
	 * @param mediaId
	 * @param openId
	 * @return
	 * @Description 新增图文预览接口
	 * @author justbamboo
	 * @date 2016年1月5日 下午3:57:59
	 */
	public BaseResponse previewMessageToUser(String mediaId,
			String openId) {
		String url = BASE_API_URL+ "cgi-bin/message/mass/preview?access_token=#";
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("msgtype", "mpnews");
		Map<String, Object> mpNews = new HashMap<String, Object>();
		mpNews.put("media_id", mediaId);
		params.put("mpnews", mpNews);
		params.put("touser", openId);
		BaseResponse response = executePost(url, JSONUtil.toJson(params));
		return response;
	}

}
