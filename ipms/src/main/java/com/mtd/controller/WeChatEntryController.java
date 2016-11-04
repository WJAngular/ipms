package com.mtd.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mtd.service.WeChatService;
import com.wechat.WeChat;

@Controller
@RequestMapping("/wechat_old")
public class WeChatEntryController {

	@Resource(name = "wechatService")
	private WeChatService service;


	@RequestMapping(method= RequestMethod.GET)
	@ResponseBody
	public String wechatEntry(String signature,String timestamp,String nonce,String echostr) throws Exception {
		boolean flag=WeChat.checkSignature("wechat", signature, timestamp, nonce);
		String out=null;
		if(flag){  
			out=echostr;
		}
		return out;
	}

	@RequestMapping(method= RequestMethod.POST)  
	@ResponseBody
	public String wechatHandle(HttpServletRequest request,HttpServletResponse response,String handleResult) {
		response.setCharacterEncoding("utf-8");
		handleResult=service.processRequest(request);
		return handleResult;
	}
	
	@RequestMapping("/test")  
	@ResponseBody
	public String test(){
		String s=service.test();
		System.out.print(s);
		return "test\n"+s;
	}
	
	
	

	
	
		

}
