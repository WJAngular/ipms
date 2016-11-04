package com.mtd.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.sd4324530.fastweixin.api.response.GetSignatureResponse;
import com.github.sd4324530.fastweixin.company.api.config.QYAPIConfig;
import com.mtd.common.utils.SystemConfig;
import com.mtd.entity.WeChat_SignUp;
import com.mtd.service.WeChatJSService;
import com.mtd.service.WeChatSignUpService;
import com.mtd.util.DateUtil;
import com.wechat.WeChatApiConfig;

@Controller
@RequestMapping("/wechat/sign")
public class WeChatSignUpController {
	@Resource
	private WeChatJSService jsService;
	@Resource
	private WeChatSignUpService signService;

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView sign(String user,HttpServletRequest req,ModelAndView m){
		String url=req.getRequestURL().toString()+"?"+req.getQueryString();
		GetSignatureResponse signature=jsService.getSignature(url);
		WeChat_SignUp s=signService.findSign(user, DateUtil.formatDateStr(new Date()));
		if(s!=null){
			m.addObject("sign", s);
			m.setViewName("/wechat/sign_up_success");
		}else{
			m.addObject("user", user);
			m.setViewName("/wechat/sign_up");
		}
		m.addObject("url", url);
		m.addObject("signature", signature);
		m.addObject("appid", WeChatApiConfig.getApiConfig().getAppid());
		
		
		return m;
	}
	
	@RequestMapping("/sign_up")
	@ResponseBody
	public String signUp(String user,String gps,String address,String date,String time,HttpServletRequest req){
		System.out.println(req.getQueryString());
		String[] g=gps.split(",");
		System.out.println(1);
		if(g.length!=2)return "error";
		System.out.println(2);
		WeChat_SignUp sign=new WeChat_SignUp(user, date, time, address, g[0], g[1]);
		System.out.println(3);
		signService.userSignUp(sign);
		System.out.println(4);
		return "ok";
	}
}
