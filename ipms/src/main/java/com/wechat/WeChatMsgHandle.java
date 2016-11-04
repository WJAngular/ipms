package com.wechat;


import com.github.sd4324530.fastweixin.handle.MessageHandle;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.BaseReqMsg;
/**
 * 
 * 
 * @ClassName: WeChatMsgHandle
 * @Description: 自定义消息处理器
 * @author just_bamboo
 * @date 2015年11月25日 下午5:55:03
 *
 */
public class WeChatMsgHandle implements MessageHandle {

	@Override
	public BaseMsg handle(BaseReqMsg message) {
		return new TextMsg("消息处理器，接收到"+message.getMsgType()+"信息");
	}

	@Override
	public boolean beforeHandle(BaseReqMsg message) {
		// TODO Auto-generated method stub
		return false;
	}

}
