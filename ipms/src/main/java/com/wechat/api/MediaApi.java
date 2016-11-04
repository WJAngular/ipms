package com.wechat.api;



import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.print.attribute.standard.MediaName;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wechat.WeChat;
import com.wechat.bean.Article;
import com.wechat.bean.MediaRespone;
import com.wechat.bean.WeChatMedia;
import com.wechat.util.HttpKit;
import com.wechat.util.WeixinUtil;

/**
 * 
 * 
 * @ClassName: Media
 * @Description: 素材管理接口类
 * @author just_bamboo
 * @date 下午2:21:58
 *
 */
public class MediaApi {
	private static final String MEDIA_BASE = "https://api.weixin.qq.com/cgi-bin/media/";
	private static final String MATERIAL_BASE = "https://api.weixin.qq.com/cgi-bin/material/";

	private static final String MEDIA_UPLOAD = MEDIA_BASE
			+ "upload?access_token=ACCESS_TOKEN&type=TYPE";
	private static final String MEDIA_GET = MEDIA_BASE
			+ "get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	private static final String MEDIA_MATERIAL_ADD_NEWS = MATERIAL_BASE
			+ "add_news?access_token=";
	private static final String MEDIA_MATERIAL_ADD = MATERIAL_BASE
			+ "add_material?access_token=ACCESS_TOKEN";
	private static final String MEDIA_MATERIAL_GET = MATERIAL_BASE
			+ "get_material?access_token=";
	private static final String MEDIA_MATERIAL_DEL = MATERIAL_BASE
			+ "del_material?access_token=";
	private static final String MEDIA_MATERIAL_UPDATE = MATERIAL_BASE
			+ "update_news?access_token=";
	private static final String MEDIA_MATERIAL_ACCOUNT = MATERIAL_BASE
			+ "get_materialcount?access_token=";
	private static final String MEDIA_MATERIAL_LIST = MATERIAL_BASE
			+ "batchget_material?access_token=";
	
	private static final String MEDIA_UPLOADIMAGE = MEDIA_BASE
			+ "uploadimg?access_token=";

	public static final String TYPE_IMAGE = "image";
	public static final String TYPE_NEWS = "news";
	public static final String TYPE_VIDEO = "video";
	public static final String TYPE_VOICE = "voice";

	
	
	
	/**
	 * 
	 * 
	 * @param accessToken
	 * @param articles
	 * @return
	 * @throws Exception
	 * @Description TODO
	 * @author justbamboo
	 * @date 2015年11月14日 下午3:38:51
	 */
	public String addNews(String accessToken,ArrayList<Article> articles) throws Exception{
		MediaParam param=new MediaParam(articles);
		System.out.println(JSON.toJSONString(param));
		String result=HttpKit.post(MEDIA_MATERIAL_ADD_NEWS.concat(accessToken),JSON.toJSONString(param));
		System.out.println("结果" + result);
		return result;
	}
	
	
	/**
	 * 
	 * @param accessToken
	 * @param mediaFileUrl
	 * @return
	 * @Description 上传永久素材
	 * @author justbamboo
	 * @date 2015年11月14日 下午2:53:25
	 */
	public WeChatMedia uploadMedia(String accessToken,String mediaFileUrl) {
		WeChatMedia weixinMedia = null;
		// 拼装请求地址
		String uploadMediaUrl = MEDIA_MATERIAL_ADD;
		uploadMediaUrl = uploadMediaUrl.replace("ACCESS_TOKEN", accessToken);

		// 定义数据分隔符
		String boundary = "------------7da2e536604c8";
		try {
			URL uploadUrl = new URL(uploadMediaUrl);
			HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl
					.openConnection();
			uploadConn.setDoOutput(true);
			uploadConn.setDoInput(true);
			uploadConn.setRequestMethod("POST");
			// 设置请求头Content-Type
			uploadConn.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + boundary);
			// 获取媒体文件上传的输出流（往微信服务器写数据）
			OutputStream outputStream = uploadConn.getOutputStream();

			URL mediaUrl = new URL(mediaFileUrl);
			HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl
					.openConnection();
			meidaConn.setDoOutput(true);
			meidaConn.setRequestMethod("GET");

			// 从请求头中获取内容类型
			String contentType = meidaConn.getHeaderField("Content-Type");
			// 根据内容类型判断文件扩展名
			String fileExt = WeixinUtil.getFileEndWitsh(contentType);
			// 请求体开始
			outputStream.write(("--" + boundary + "\r\n").getBytes());
			outputStream
					.write(String
							.format("Content-Disposition: form-data; name=\"media\"; filename=\"file1%s\"\r\n",
									fileExt).getBytes());
			outputStream.write(String.format("Content-Type: %s\r\n\r\n",
					contentType).getBytes());

			// 获取媒体文件的输入流（读取文件）
			BufferedInputStream bis = new BufferedInputStream(
					meidaConn.getInputStream());
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				// 将媒体文件写到输出流（往微信服务器写数据）
				outputStream.write(buf, 0, size);
			}
			// 请求体结束
			outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
			outputStream.close();
			bis.close();
			meidaConn.disconnect();

			// 获取媒体文件上传的输入流（从微信服务器读数据）
			InputStream inputStream = uploadConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			uploadConn.disconnect();

			// 测试打印结果
			System.out.println("结果" + buffer.toString());
			String s = buffer.toString().replace("\\", "");
			System.out.println("结果" + s);

			weixinMedia = JSON
					.parseObject(buffer.toString(), WeChatMedia.class);
			// // 测试打印结果
			System.out.println("打印测试结果" + weixinMedia.getMedia_id());

		} catch (Exception e) {
			weixinMedia = null;
			String error = String.format("上传媒体文件失败：%s", e);
			System.out.println(e + "\n" + error);
		}
		return weixinMedia;
	}

	/**
	 * 
	 * 
	 * @param accessToken
	 * @param type
	 *            获取素材类型
	 * @param page
	 *            页码
	 * @throws Exception
	 * @Description 获取永久素材列表
	 * @author justbamboo
	 * @date 2015年11月14日 下午12:25:00
	 */
	public MediaRespone getMediaList(String accessToken, String type, int page)
			throws Exception {
		String param = JSONObject.toJSONString(new MediaParam(type, page));
		String rs = HttpKit
				.post(MEDIA_MATERIAL_LIST.concat(accessToken), param);
		System.out.print(rs);
		return JSON.parseObject(rs, MediaRespone.class);
	}

	
	/**
	 * 
	 * 
	 * @return 返回素材url
	 * @Description 上传图文消息内图片素材
	 * @author justbamboo
	 * @date 2015年11月20日 下午6:20:23
	 */
	public WeChatMedia uploadImage(String accessToken,String mediaFileUrl){
		String uploadMediaUrl=MEDIA_UPLOADIMAGE.concat(accessToken);
		WeChatMedia r=null;
		try {
			String rs=uploadFile(uploadMediaUrl, mediaFileUrl);
			r=JSONObject.parseObject(rs, WeChatMedia.class);
		} catch (Exception e) {
			e.printStackTrace();
			String error = String.format("上传媒体文件失败：%s", e);
			System.out.println(e + "\n" + error);
		}
		return r;
	}
	
	/**
	 * 
	 * 
	 * @param uploadMediaUrl
	 * @param mediaFileUrl
	 * @return
	 * @throws Exception
	 * @Description TODO
	 * @author justbamboo
	 * @date 2015年11月20日 下午6:29:44
	 */
	private String uploadFile(String uploadMediaUrl, String mediaFileUrl)
			throws Exception {
		// 定义数据分隔符
		String boundary = "------------7da2e536604c8";
		URL uploadUrl = new URL(uploadMediaUrl);
		HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl
				.openConnection();
		uploadConn.setDoOutput(true);
		uploadConn.setDoInput(true);
		uploadConn.setRequestMethod("POST");
		// 设置请求头Content-Type
		uploadConn.setRequestProperty("Content-Type",
				"multipart/form-data;boundary=" + boundary);
		// 获取媒体文件上传的输出流（往微信服务器写数据）
		OutputStream outputStream = uploadConn.getOutputStream();

		URL mediaUrl = new URL(mediaFileUrl);
		HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl
				.openConnection();
		meidaConn.setDoOutput(true);
		meidaConn.setRequestMethod("GET");

		// 从请求头中获取内容类型
		String contentType = meidaConn.getHeaderField("Content-Type");
		// 根据内容类型判断文件扩展名
		String fileExt = WeixinUtil.getFileEndWitsh(contentType);
		// 请求体开始
		outputStream.write(("--" + boundary + "\r\n").getBytes());
		outputStream
				.write(String
						.format("Content-Disposition: form-data; name=\"media\"; filename=\"file1%s\"\r\n",
								fileExt).getBytes());
		outputStream.write(String.format("Content-Type: %s\r\n\r\n",
				contentType).getBytes());

		// 获取媒体文件的输入流（读取文件）
		BufferedInputStream bis = new BufferedInputStream(
				meidaConn.getInputStream());
		byte[] buf = new byte[8096];
		int size = 0;
		while ((size = bis.read(buf)) != -1) {
			// 将媒体文件写到输出流（往微信服务器写数据）
			outputStream.write(buf, 0, size);
		}
		// 请求体结束
		outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
		outputStream.close();
		bis.close();
		meidaConn.disconnect();

		// 获取媒体文件上传的输入流（从微信服务器读数据）
		InputStream inputStream = uploadConn.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(
				inputStream, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		StringBuffer buffer = new StringBuffer();
		String str = null;
		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
		}
		bufferedReader.close();
		inputStreamReader.close();
		// 释放资源
		inputStream.close();
		inputStream = null;
		uploadConn.disconnect();

		// 测试打印结果
		System.out.println("结果" + buffer.toString());
		String s = buffer.toString().replace("\\", "");
		System.out.println("结果" + s);

		return s;
	}

	/**
	 * 
	 * 
	 * @ClassName: MediaParam
	 * @Description: TODO
	 * @author just_bamboo
	 * @date 上午11:36:06
	 *
	 */
	class MediaParam {
		private static final int PAGE_SIZE = 10;

		private String type;
		private int offset;
		private int count;
		
		private ArrayList<Article> articles;

		public MediaParam(String type, int page) {
			this.type = type;
			this.count = PAGE_SIZE;
			this.offset = (page - 1) * PAGE_SIZE;
		}
		
		public MediaParam(ArrayList<Article> articles) {
			this.articles=articles;
		}

		public String getType() {
			return type;
		}

		public int getOffset() {
			return offset;
		}

		public void setOffset(int offset) {
			this.offset = offset;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public void setType(String type) {
			this.type = type;
		}

		public ArrayList<Article> getArticles() {
			return articles;
		}

		public void setArticles(ArrayList<Article> articles) {
			this.articles = articles;
		}

		
		
	}

	/**
	 * 
	 * @param args
	 * @Description TODO
	 * @author justbamboo
	 * @date 2015年11月14日 下午3:47:40
	 */
	public static void main(String[] args) {
		try {
//			MediaRespone r = new Media().getMediaList(WeChat.getAccessToken(),
//					Media.TYPE_IMAGE, 1);
//			System.out.print(r.getItem_count());
			Article article=new Article();
			article.setAuthor("福田区梅岭街道党工委");
			article.setContent("<p style=\"text-align: center;\">"
					+ "<img src=\"http://mmbiz.qpic.cn/mmbiz/8iahme"
					+ "Gy1Dpa2QZzrGNcCWW8vs2qQkP0EOFQOgibPabKFibBtUD"
					+ "5d76s9EYibW82Udy9aOZKyoUkic3d4yrt3EFcEVg/0\" "
					+ "title=\"1448016490244067679.jpg\" alt=\"liucheng.jpg\"/></p>");
			article.setTitle("党员发展流程");
			article.setDigest("党员发展流程");
			article.setContent_source_url("#");
			article.setThumb_media_id("i7oFOllIm3FLwQCyuX2f-Yd7DlVpFbMEWUTPrSj30vI");
			ArrayList<Article> a=new ArrayList<Article>();
			a.add(article);
			new MediaApi().addNews(WeChat.getAccessToken(), a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}