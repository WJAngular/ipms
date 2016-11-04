package com.mtd.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.github.sd4324530.fastweixin.api.entity.Article;
import com.github.sd4324530.fastweixin.api.enums.MaterialType;
import com.github.sd4324530.fastweixin.api.enums.MediaType;
import com.github.sd4324530.fastweixin.api.response.GetMaterialListResponse;
import com.github.sd4324530.fastweixin.api.response.GetMaterialTotalCountResponse;
import com.github.sd4324530.fastweixin.api.response.UploadMaterialResponse;
import com.mtd.entity.WechatNews;


/**
 * 
 * 
 * @ClassName: WechatMediaDao
 * @Description: 素材管理业务处理接口
 * @author just_bamboo
 * @date 2015年12月3日 上午10:49:36
 *
 */
public interface WechatMediaDao {
	
	/**
	 * 
	 * 
	 * @param type
	 * @return
	 * @Description 获取各个种类素材列表
	 * @author justbamboo
	 * @date 2015年12月3日 上午11:08:11
	 */
	public String gatMediaList(MediaType type,int page);
	
	/**
	 * 
	 * 
	 * @param type
	 * @param page
	 * @return
	 * @Description 获取各个种类永久素材Url列表
	 * @author justbamboo
	 * @date 2015年12月3日 上午11:14:33
	 */
	public String getMaterialUrlList(MaterialType type,int page);
	
	/**
	 * 
	 * 
	 * @param type
	 * @param page
	 * @return
	 * @Description 获取各个种类永久素材Url和MediaID列表
	 * @author justbamboo
	 * @date 2015年12月4日 下午4:47:19
	 */
	public String getMaterialList(MaterialType type,int page);
	/**
	 * 
	 * 
	 * @param articles
	 * @return 返回boolean判断操作是否成功
	 * @Description 新增永久图文素材
	 * @author justbamboo
	 * @date 2015年12月3日 下午4:22:32
	 */
	public UploadMaterialResponse addMaterialNews(List<Article> articles);
	
	/**
	 * 
	 * 
	 * @param file
	 * @return
	 * @Description 新增永久素材
	 * @author justbamboo
	 * @date 2015年12月3日 下午4:27:07
	 */
	public UploadMaterialResponse addMaterialFile(File file);
	
	/**
	 * 
	 * 
	 * @param type
	 * @param page
	 * @return
	 * @Description 获取永久素材
	 * @author justbamboo
	 * @date 2015年12月28日 下午7:58:15
	 */
	public GetMaterialListResponse getMaterial(MaterialType type,int page);
	
	/**
	 * 
	 * @param mediaID
	 * @return
	 * @Description 获取该图文消息内容列表
	 * @author justbamboo
	 */
	public ArrayList<WechatNews> getMaterialNews(String mediaID);
	
	/**
	 * 
	 * 
	 * @return
	 * @Description TODO
	 * @author justbamboo
	 * @date 2016年1月4日 上午9:34:52
	 */
	public GetMaterialTotalCountResponse MaterialTotalCount();
	

}