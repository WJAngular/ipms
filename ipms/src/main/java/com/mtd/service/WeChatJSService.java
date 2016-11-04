package com.mtd.service;

import com.github.sd4324530.fastweixin.api.response.GetSignatureResponse;

public interface WeChatJSService {

	/**
	 * 
	 * 
	 * @param url
	 * @return
	 * @Description 获取微信js验证
	 * @author justbamboo
	 * @date 2015年12月25日 上午10:36:54
	 */
	public GetSignatureResponse getSignature(String url);
 
}
