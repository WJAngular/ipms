package com.wechat.bean;



import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @ClassName: MediaRespone
 * @Description: 获取素材文件响应数据体
 * @author just_bamboo
 * @date 下午12:13:43
 *
 */
public class MediaRespone {
	private int total_count;
	private int item_count;
	private ArrayList<MediaItem> item;

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

	public int getItem_count() {
		return item_count;
	}

	public void setItem_count(int item_count) {
		this.item_count = item_count;
	}

	public ArrayList<MediaItem> getItem() {
		return item;
	}

	public void setItem(ArrayList<MediaItem> item) {
		this.item = item;
	}




	/**
	 * 
	 * 
	 * @ClassName: MediaItem
	 * @Description: TODO
	 * @author just_bamboo
	 * @date 下午12:16:31
	 *
	 */
	class MediaItem {
		private String media_id;
		private MediaBaseItem content;
		private String update_time;
		private String name;
		private String url;

		public String getMedia_id() {
			return media_id;
		}

		public void setMedia_id(String media_id) {
			this.media_id = media_id;
		}

		public MediaBaseItem getContent() {
			return content;
		}

		public void setContent(MediaBaseItem content) {
			this.content = content;
		}

		public String getUpdate_time() {
			return update_time;
		}

		public void setUpdate_time(String update_time) {
			this.update_time = update_time;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

	}
	
	class MediaBaseItem {
		private ArrayList<Article> news_item;

		public ArrayList<Article> getNews_item() {
			return news_item;
		}

		public void setNews_item(ArrayList<Article> news_item) {
			this.news_item = news_item;
		}
		
	}
}
