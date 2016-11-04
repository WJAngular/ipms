package com.wechat.util;

public class WeixinUtil {

	/**
	 * 
	 * @Description TODO
	 * @param contentType
	 * @return
	 * @author justbamboo
	 * @date 2015年11月2日 下午4:45:02
	 */
	public static  String getFileEndWitsh(String contentType){
//		String s="."+contentType.substring(contentType.indexOf("/")+1, contentType.length());
//		System.out.println(s);
//		return s;
		String fileEndWitsh = "";
		if ("image/jpeg".equals(contentType))
			fileEndWitsh = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileEndWitsh = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileEndWitsh = ".amr";
		else if ("video/mp4".equals(contentType))
			fileEndWitsh = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileEndWitsh = ".mp4";
		else if ("image/png".equals(contentType))
			fileEndWitsh = ".png";
		return fileEndWitsh;
	}
	
	/**
	 * 
	 * @Description TODO
	 * @param str
	 * @return
	 * @author justbamboo
	 * @date 2015年11月2日 下午4:44:56
	 */
	public static String URLEncoder(String str){
		String result = str ;
		try {
		result = java.net.URLEncoder.encode(result,"UTF-8");	
		} catch (Exception e) {
        e.printStackTrace();
		}
		return result;
	}
	
	
	public static void main(String[] args){
		System.out.print(getFileEndWitsh("image\\png"));
	}
	
	
}
