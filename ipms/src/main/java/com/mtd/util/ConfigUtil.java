/**
 * 微信公众平台�?发模�?(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.mtd.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;





/**
 * 配置文件
 * @author L.cm
 * email: 596392912@qq.com
 * site:  http://www.dreamlu.net
 *
 */
public class ConfigUtil {

	private static Properties props = new Properties();

	static {
		try {
			//play框架下要用这种方式加�?
			//props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("/wechat.properties"));
			props.load(ConfigUtil.class.getResourceAsStream("/qywechart.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		return props.getProperty(key);
	}

    public static void setProps(Properties p){
        props = p;
    }
    
    public static void main(String[] args){
		System.out.println(ConfigUtil.get("AppId"));
	}
}
