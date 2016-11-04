package com.mtd.service.impl;

import org.springframework.stereotype.Service;

import com.github.sd4324530.fastweixin.api.JsAPI;
import com.github.sd4324530.fastweixin.api.response.GetSignatureResponse;
import com.mtd.service.WeChatJSService;
import com.wechat.WeChatApiConfig;



@Service
public class WeChatJSServiceImpl implements WeChatJSService {
	
	private JsAPI api;
	
	public WeChatJSServiceImpl() {
		api=new JsAPI(new WeChatApiConfig().getApiConfig());
	}

	@Override
	public GetSignatureResponse getSignature(String url) {
		return api.getSignature(url);
	}
	
	
	public static void main(String[] args){
//		String s=WeChatApiConfig.getApiConfig().getJsApiTicket();
//		System.out.println(s);
	}
}
