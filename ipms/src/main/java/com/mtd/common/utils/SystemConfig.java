package com.mtd.common.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;



public class SystemConfig {

	private static Properties props = new Properties();

	static {
		try {
			props.load(SystemConfig.class.getResourceAsStream("/system.properties"));
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
		System.out.println(SystemConfig.get("iswechat"));
	}
}
