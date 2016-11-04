
package com.wechat;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.wechat.util.ConfKit;


public class WeChatApiConfig {

	private static ApiConfig config;

	private static String AppId =ConfKit.get("AppId");
	private static String AppSecret =ConfKit.get("AppSecret");
	public static final String WEB_HOST=ConfKit.get("Host"); 
	
	public synchronized static ApiConfig getApiConfig() {
		if (config == null) {
			config = new ApiConfig(AppId, AppSecret,true);
		}
		return config;
	};

	/*测试*/
//	 private static String AppId="wx5f3b6b77a244e897";
//	 private static String AppSecret="3ffe8b0c4c1a543e4f93c9a8fdbc8bda";
	/* 香密 */
//	private static String AppId = "wxd654f918c418d0b1";
//	private static String AppSecret = "30e3beeb377fa604fcd6fc457363542e";

//	public ApiConfig getApiConfig() {
//		if (config == null) {
//			config = new ApiConfig(AppId, AppSecret,true);
//		}
//		return 
//	};
	

	

	public static void main(String[] args) {
//		System.out.println("1:"+new WeChatApiConfig().getApiConfig().getAccessToken());
//		System.out.println("2:"+new WeChatApiConfig().getApiConfig().getAccessToken());
//		System.out.println("1:"+WeChatApiConfig.getApiConfig().getAccessToken());
//		System.out.println("2:"+WeChatApiConfig.getApiConfig().getAccessToken());
	}
}

