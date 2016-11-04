package com.mtd.service.impl;

import org.springframework.stereotype.Service;

import com.mtd.dao.WechatSignUpDao;
import com.mtd.entity.WeChat_SignUp;
import com.mtd.service.WeChatSignUpService;

@Service
public class WeChatSignUpServiceImpl implements WeChatSignUpService {
	
	private WechatSignUpDao sign;

	@Override
	public WeChat_SignUp findSign(String user, String date) {
		return sign.findSignByDate(user, date);
	}

	@Override
	public void userSignUp(WeChat_SignUp sign_up) {
		sign.create(sign_up);
	}

	public WechatSignUpDao getSign() {
		return sign;
	}

	public void setSign(WechatSignUpDao sign) {
		this.sign = sign;
	}

	

}
