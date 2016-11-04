package com.mtd.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.sd4324530.fastweixin.api.OauthAPI;
import com.github.sd4324530.fastweixin.api.response.OauthGetTokenResponse;
import com.wechat.WeChatApiConfig;


/**
 * 
 * 
 * @ClassName: WeChatServiceController
 * @Description: TODO
 * @author just_bamboo
 * @date 2016年1月18日 下午1:26:09
 *
 */
@Controller
public class WeChatServiceController {
	
	@RequestMapping("/wechat/service")
	public ModelAndView toServicePage(String code,ModelAndView m,HttpServletRequest req){
		Object u=req.getSession().getAttribute("userid");
		if(u==null){
			OauthGetTokenResponse response = new OauthAPI(WeChatApiConfig.getApiConfig()).getToken(code);
			String user=response.getOpenid();
			req.getSession().setAttribute("userid", user);
		};
		System.out.println("U:"+u);
//		m.addObject("userid", user);
		m.setViewName("/wechat/services");
		return m;
	}
	
}
