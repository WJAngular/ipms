package com.mtd.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SendSmsUtil {

	private static Properties props = new Properties();

	private static String url = "http://www.mxtong.net.cn/GateWay/Services.asmx/DirectSend?";

	static {
		try {
			// play妗嗘灦涓嬭鐢ㄨ繖绉嶆柟寮忓姞杞�
			// props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("/wechat.properties"));
			props.load(ConfigUtil.class.getResourceAsStream("/Sms.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		return props.getProperty(key);
	}

	public static void main(String[] args) {
		System.out.println(SendMs("13531748447;15018424562", "", "杩欐槸fdgfdgdf鍐呭锛岃秴闀垮瓙"));
		//String s=SendMs("13531748447;15018424562", "", "sss");
//		String a="";
//		if(s.indexOf("<ErrPhones>")!=-1)
//		{
//		   a=s.substring(s.indexOf("<ErrPhones>")+11,s.indexOf("</ErrPhones>"));
//		}else
//		{
//			s="ss";
//			System.out.println(s);
//		}
		//System.out.println(s);
	}

	/**
	 * 
	 * @param phones
	 *            鎺ユ敹鎵嬫満鍙凤紝澶氫釜鐢�";"鍒嗛殧
	 * @param sendTime
	 *            鍙戦�佹椂闂达紙鏍煎紡锛歽yyy-MM-dd HH:mm:ss锛夛紝濡傛灉绔嬪嵆鍙戦�侊紝鏃堕棿鍙负绌哄瓧绗︿覆
	 */
	public static boolean Send(String phones, String sendTime, String content) {

		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
		PostMethod getMethod = new PostMethod(url);
		String Content = null;
		try {
			Content = java.net.URLEncoder.encode(content + "銆�" + get("Signature") + "銆�", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		NameValuePair[] data = { new NameValuePair("UserID", get("UserID")), new NameValuePair("Account", get("Account")), new NameValuePair("Password", get("Password")),
				new NameValuePair("Phones", phones), new NameValuePair("SendType", "1"), new NameValuePair("SendTime", sendTime), new NameValuePair("PostFixNumber", ""),
				new NameValuePair("Content", Content) };
		getMethod.setRequestBody(data);
		getMethod.addRequestHeader("Connection", "close");
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}
			byte[] responseBody = getMethod.getResponseBody();
			String str = new String(responseBody, "utf-8");
			int beginPoint = str.indexOf("<RetCode>");
			int endPoint = str.indexOf("</RetCode>");
			String result = "RetCode=";
			System.out.println("Content====:"+Content);
			System.out.println(result + str.substring(beginPoint + 9, endPoint));
			System.out.println(str);
			return "Sucess".equals(str.substring(beginPoint + 9, endPoint));
		} catch (HttpException e) {
			System.out.println(1);
		} catch (IOException e) {
			System.out.println(2);
		} finally {
			getMethod.releaseConnection();
		}
		return false;
	}

	/**
	 * 
	 * @param phones
	 *            鎺ユ敹鎵嬫満鍙凤紝澶氫釜鐢�";"鍒嗛殧
	 * @param sendTime
	 *            鍙戦�佹椂闂达紙鏍煎紡锛歽yyy-MM-dd HH:mm:ss锛夛紝濡傛灉绔嬪嵆鍙戦�侊紝鏃堕棿鍙负绌哄瓧绗︿覆
	 */
	public static String SendMs(String phones, String sendTime, String content) {
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
		PostMethod getMethod = new PostMethod(url);
		String Content = null;
		try {
			Content = java.net.URLEncoder.encode(content + "銆�" + get("Signature") + "銆�", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		NameValuePair[] data = { new NameValuePair("UserID", get("UserID")), new NameValuePair("Account", get("Account")), new NameValuePair("Password", get("Password")),
				new NameValuePair("Phones", phones), new NameValuePair("SendType", "1"), new NameValuePair("SendTime", sendTime), new NameValuePair("PostFixNumber", ""),
				new NameValuePair("Content", Content) };
		getMethod.setRequestBody(data);
		getMethod.addRequestHeader("Connection", "close");
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}
			byte[] responseBody = getMethod.getResponseBody();
			String str = new String(responseBody, "utf-8");
			int beginPoint = str.indexOf("<RetCode>");
			int endPoint = str.indexOf("</RetCode>");
			String result = "RetCode=";
			System.out.println("Content====:"+Content);
			System.out.println(result + str.substring(beginPoint + 9, endPoint));
			System.out.println(str);
			if ("Sucess".equals(str.substring(beginPoint + 9, endPoint))) {
				return str;
			} else {
				if(str.indexOf("<Message>")>-1){
					String msg = str.substring(str.indexOf("<Message>") + 9, str.indexOf("</Message>"));
                    return  "error-"+msg;
				}
				return "error";
			}
		} catch (HttpException e) {
			System.out.println(1);
		} catch (IOException e) {
			System.out.println(2);
		} finally {
			getMethod.releaseConnection();
		}
		return "error";
	}
}
