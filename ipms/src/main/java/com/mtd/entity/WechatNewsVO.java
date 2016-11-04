package com.mtd.entity;

import java.util.ArrayList;
import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import com.github.sd4324530.fastweixin.api.entity.Article;

/**
 * 
 * 
 * @ClassName: WechatNewsVO
 * @Description: 图文消息展示类
 * @author just_bamboo
 * @date 2015年12月28日 下午8:03:47
 *
 */
public class WechatNewsVO {
	private static final int PAGE_SIZE = 20;

	private int page;// 当前页数

	private int total;// 总页数

	private ArrayList<WechatNewsItem> items;
	
	private int totalCount;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total_count) {
		this.totalCount=total_count;
		int i = total_count % PAGE_SIZE;
		if (i == 0) {
			this.total = total_count / PAGE_SIZE;
		} else {
			this.total = total_count / PAGE_SIZE + 1;
		}
	}

	public ArrayList<WechatNewsItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<WechatNewsItem> items) {
		this.items = items;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	

}
