package com.mtd.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.sd4324530.fastweixin.api.MaterialAPI;
import com.github.sd4324530.fastweixin.api.MediaAPI;
import com.github.sd4324530.fastweixin.api.entity.Article;
import com.github.sd4324530.fastweixin.api.enums.MaterialType;
import com.github.sd4324530.fastweixin.api.enums.MediaType;
import com.github.sd4324530.fastweixin.api.response.DownloadMaterialResponse;
import com.github.sd4324530.fastweixin.api.response.GetMaterialListResponse;
import com.github.sd4324530.fastweixin.api.response.GetMaterialTotalCountResponse;
import com.github.sd4324530.fastweixin.api.response.UploadMaterialResponse;
import com.mtd.dao.WechatMediaDao;
import com.mtd.entity.MediaJsonBody;
import com.mtd.entity.WechatNews;
import com.mtd.entity.WechatNewsItem;
import com.wechat.WeChatApiConfig;

public class WechatMediaDaoImpl implements WechatMediaDao {
	private MaterialAPI material;// 永久素材接口
	private MediaAPI media;// 临时素材接口
	private static final int PAGE_SIZE = 20;

	public WechatMediaDaoImpl() {
		material = new MaterialAPI(new WeChatApiConfig().getApiConfig());
		media = new MediaAPI(new WeChatApiConfig().getApiConfig());
		// material=new MaterialAPI(WeChatApiConfig.getApiConfig());
		// media=new MediaAPI(WeChatApiConfig.getApiConfig());
	}

	@Override
	public String gatMediaList(MediaType type, int page) {

		return null;
	}

	@Override
	public String getMaterialUrlList(MaterialType type, int page) {
		// GetMaterialListResponse resp=material.batchGetMaterial(type,
		// (page-1)*PAGE_SIZE, PAGE_SIZE);
		return getListRespJson(getMaterial(type, page), 0);
	}

	@Override
	public UploadMaterialResponse addMaterialNews(List<Article> articles) {
		UploadMaterialResponse resp = material.uploadMaterialNews(articles);
		return resp;
	}

	@Override
	public UploadMaterialResponse addMaterialFile(File file) {
		material.uploadMaterialFile(file);
		return null;
	}

	@Override
	public String getMaterialList(MaterialType type, int page) {
		// GetMaterialListResponse resp=material.batchGetMaterial(type,
		// (page-1)*PAGE_SIZE, PAGE_SIZE);
		return getListRespJson(getMaterial(type, page), 1);
	}

	/**
	 * 
	 * 
	 * @param resp
	 * @param type
	 * @return
	 * @Description TODO
	 * @author justbamboo
	 * @date 2015年12月4日 下午4:53:35
	 */
	private String getListRespJson(GetMaterialListResponse resp, int type) {

		String code = resp.getErrcode();
		if (code != null && !code.equals("0")) {
			System.out.println(code + "," + resp.getErrmsg());
			return resp.getErrmsg();
		}
		List<Map<String, Object>> m = resp.getItems();
		ArrayList<MediaJsonBody> urlList = new ArrayList<MediaJsonBody>();

		for (Map<String, Object> item : m) {
			if (type == 0) {
				urlList.add(new MediaJsonBody((String) item.get("url")));
			} else if (type == 1) {
				urlList.add(new MediaJsonBody((String) item.get("url"),
						(String) item.get("media_id")));
			}
		}
		return JSON.toJSONString(urlList);
	}

	@Override
	public GetMaterialListResponse getMaterial(MaterialType type, int page) {
		return material.batchGetMaterial(type, (page - 1) * PAGE_SIZE,
				PAGE_SIZE);
	}

	@Override
	public ArrayList<WechatNews> getMaterialNews(String mediaID) {
		DownloadMaterialResponse resp = material.downloadMaterial(mediaID,
				MaterialType.NEWS);
		System.out.println(resp.getErrcode());
		if (resp.getErrcode().equals("0")) {
			WechatNewsItem item = JSON.parseObject(resp.getErrmsg(),
					WechatNewsItem.class);
			System.out.println(JSON.toJSON(item.getNews_item().get(0)));
			return item.getNews_item();
		} else {
			System.out.println(resp.getErrcode()+","+resp.getErrmsg());
			return null;
		}
		// return resp.getNews();
	}

	@Override
	public GetMaterialTotalCountResponse MaterialTotalCount() {
		return material.countMaterial();
	}

	

}