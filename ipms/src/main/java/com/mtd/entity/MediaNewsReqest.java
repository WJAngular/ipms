package com.mtd.entity;

import java.util.ArrayList;
import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import com.github.sd4324530.fastweixin.api.entity.Article;

/**
 * 
 * 
 * @ClassName: MediaNewsReqest
 * @Description: TODO
 * @author just_bamboo
 * @date 2015年12月29日 下午12:05:33
 *
 */
public class MediaNewsReqest {

	private String title;
	private String author;
	private String picId;
	private String abstracts;
	private String url;
	private String contents;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
