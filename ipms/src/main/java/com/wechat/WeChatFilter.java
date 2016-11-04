/**
 * 微信公众平台�?发模�?(JAVA) SDK
 */
package com.wechat;

import com.wechat.bean.InMessage;
import com.wechat.util.Tools;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 请求拦截
 *
 * @author GodSon
 */
public class WeChatFilter implements Filter {

    private final Logger LOGGER = Logger.getLogger(WeChatFilter.class);

    @Override
    public void destroy() {
        LOGGER.info("WeChatFilter已经�?�?");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        Boolean isGet = request.getMethod().equals("GET");

        if (isGet) {
            doGet(request, response);
        }
//        else {
//            doPost(request, response);
//        }
        chain.doFilter(req, res);
    }

    private void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml");
        ServletInputStream in = request.getInputStream();
        String xmlMsg = Tools.inputStream2String(in);
        LOGGER.debug("输入消息:[" + xmlMsg + "]");
        String xml = WeChat.processing(xmlMsg);
        InMessage msg=WeChat.parsingInMessage(xmlMsg);
       // response.getWriter().write(xml);
        boolean flag=msg.getMsgType().equals("event")&&msg.getEvent().equals("click");
        try {
        	//if(flag){
        		WeChat.message.sendText(WeChat.getAccessToken(), "oV4VowOSb45hC8b43DPiZ5kOydf8", xmlMsg);
        	//}
//			WeChat.message.sendText(WeChat.getAccessToken(), msg.getFromUserName(), msg.getContent()+"\n"+xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    

    private void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletPath();
        String pathInfo = path.substring(path.lastIndexOf("/"));
        String _token;
        String outPut = "error";
        if (pathInfo != null) {
            _token = pathInfo.substring(1);
            String signature = request.getParameter("signature");// 微信加密签名
            String timestamp = request.getParameter("timestamp");// 时间�?
            String nonce = request.getParameter("nonce");// 随机�?
            String echostr = request.getParameter("echostr");//
            // 验证
            if (WeChat.checkSignature(_token, signature, timestamp, nonce)) {
                outPut = echostr;
            }
        }
        response.getWriter().write(outPut);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        LOGGER.info("WeChatFilter已经启动�?");
    }

}
