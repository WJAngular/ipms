package com.mtd.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.github.sd4324530.fastweixin.api.entity.Article;
import com.github.sd4324530.fastweixin.api.enums.MaterialType;
import com.github.sd4324530.fastweixin.api.enums.MediaType;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.api.response.GetMaterialListResponse;
import com.github.sd4324530.fastweixin.api.response.GetMaterialTotalCountResponse;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.req.MenuEvent;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.mtd.entity.WechatNews;
import com.mtd.entity.WechatNewsVO;


public interface WeChatService {

	/**
	 * 
	 * 
	 * @param request
	 * @return
	 * @Description 处理微信发来的请求
	 * @author justbamboo
	 * @date 2015年11月13日 下午6:47:36
	 */
 public String processRequest(HttpServletRequest request);
 
 public BaseMsg handleMenuClick(MenuEvent event);
 
 public String test();
 
 /**
  * 
  * 
  * @param type
  * @return
  * @Description 根据类型获取临时素材列表
  * @author justbamboo
  * @date 2015年12月3日 上午10:47:23
  */
 public String getMediaList(MediaType type,int page);
 
 /**
  * 
  * 
  * @param type
  * @return
  * @Description 根据类型获取永久素材列表
  * @author justbamboo
  * @date 2015年12月3日 上午11:26:42
  */
 public String getMaterialUrlList(MaterialType type,int page);
 
 /**
  * 
  * 
  * @param type
  * @return
  * @Description 根据类型获取永久素材列表
  * @author justbamboo
  * @date 2015年12月3日 上午11:26:42
  */
 public String getMaterialList(MaterialType type,int page);
 
 /**
  * 
  * 
  * @param newsJson
  * @return
  * @Description 新增永久性图文消息
  * @author justbamboo
  * @date 2015年12月4日 上午10:14:34
  */
 public boolean addMaterialNews(String newsJson);
 
 /**
  * 
  * 
  * @param newsJson
  * @param index
  * @return
  * @Description 修改图文消息
  * @author justbamboo
  * @date 2015年12月4日 上午10:15:42
  */
 public boolean updateMaterialNews(String newsJson,int index);
 
 /**
  * 
  * 
  * @param pege
  * @return
  * @Description 获取图文消息列表
  * @author justbamboo
  * @date 2015年12月28日 下午8:12:06
  */
 public WechatNewsVO getWechatNewsList(int page);
 
 /**
  * 
  * 
  * @param mediaID
  * @param index
  * @return
  * @Description TODO
  * @author justbamboo
  * @date 2015年12月30日 上午9:35:07
  */
 public WechatNews getWechatNews(String mediaID,int index);
 
 /**
  * 
  * 
  * @return
  * @Description TODO
  * @author justbamboo
  * @date 2016年1月4日 上午9:34:00
  */
 public GetMaterialTotalCountResponse getWechatMediaCount();
 
 /**
  * 
  * 
  * @param page
  * @return
  * @Description TODO
  * @author justbamboo
  * @date 2016年1月4日 上午9:36:41
  */
 public GetMaterialListResponse getMediaImage(int page);
 
 /**
  * 
  * 
  * @param mediaID
  * @param openId
  * @return
  * @Description 预览图文
  * @author justbamboo
  * @date 2016年1月5日 下午7:33:37
  */
 public BaseResponse previewMessage(String mediaID, String openId);
 
 /**
  * 
  * 
  * @return
  * @Description TODO
  * @author justbamboo
  * @date 2016年1月17日 下午6:08:45
  */
 public BaseMsg handleSubscribe();
 
 /**
  * 
  * 
  * @param text
  * @return
  * @Description TODO
  * @author justbamboo
  * @date 2016年1月17日 下午6:09:47
  */
 public  BaseMsg handleTextMsg(String text); 
 
 public void saveActiUser(String openedId,String actionType);
}
