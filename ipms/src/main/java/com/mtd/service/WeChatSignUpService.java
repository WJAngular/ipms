package com.mtd.service;

import com.mtd.entity.WeChat_SignUp;

public interface WeChatSignUpService {
	
	public WeChat_SignUp findSign(String user,String date);
	
	public void userSignUp(WeChat_SignUp sign);
}
