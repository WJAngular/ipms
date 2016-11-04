package com.mtd.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.sd4324530.fastweixin.handle.EventHandle;
import com.github.sd4324530.fastweixin.handle.MessageHandle;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import com.github.sd4324530.fastweixin.message.req.MenuEvent;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;
import com.mtd.service.WeChatService;
import com.wechat.WeChatEventHandle;
import com.wechat.WeChatMsgHandle;

/**
 * 
 * 
 * @ClassName: WeChatController
 * @Description: 微信控制器(新)20151125
 * @author just_bamboo
 * @date 2015年11月25日 下午5:52:53
 *
 */
@Controller
@RequestMapping("/wechat")
public class WeChatController extends WeixinControllerSupport {
	@Resource
	private WeChatService wechatService;

	@Override
	protected String getToken() {
		return "wechat";
	}

	@Override
	protected String getAppId() {
		return super.getAppId();
	}
	
	@Override
	protected String getAESKey() {
		return super.getAESKey();
	}
	
	@Override
	protected BaseMsg handleMenuClickEvent(MenuEvent event) {
		System.out.println(event);
		wechatService.saveActiUser(event.getFromUserName(), "menuclick");
//		return new TextMsg("点击菜单，key:"+s);
		BaseMsg msg=wechatService.handleMenuClick(event);
		return msg;
	}
	
	@Override
	protected BaseMsg handleTextMsg(TextReqMsg msg) {
		System.out.println(msg);
		wechatService.saveActiUser(msg.getFromUserName(), "text");
		return wechatService.handleTextMsg(msg.getContent());
	}
	
	@Override
	protected BaseMsg handleSubscribe(BaseEvent event) {
		wechatService.saveActiUser(event.getFromUserName(), "handleSubscribe");
		return wechatService.handleSubscribe();
	}
	
	/**
	 * 注册事件统一处理器
	 */
	@Override
	protected List<EventHandle> initEventHandles() {
		System.out.println("initEventHandles");
		List<EventHandle> eventList=new ArrayList<EventHandle>();
		eventList.add(new WeChatEventHandle());
		return eventList;
	}

	/**
	 * 注册消息统一处理器
	 */
	@Override
	protected List<MessageHandle> initMessageHandles() {
		System.out.println("initMessageHandles");
		List<MessageHandle> msgList=new ArrayList<MessageHandle>();
		msgList.add(new WeChatMsgHandle());
		return msgList;
	}

}
