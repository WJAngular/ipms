package com.mtd.util;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.sd4324530.fastweixin.api.response.GetMaterialListResponse;
import com.mtd.service.WeChatService;

@Component
public class SessionAttrUtil {
	
	@Resource
	private WeChatService wechatService;

	private static final String SE_IMAGE_MAP="se_imag_map";
	
	/**
	 * 
	 * @param req
	 * @return
	 * @Description TODO
	 * @author justbamboo
	 * @date 2016年1月3日 下午10:50:05
	 */
	public Map<String, Object> getWechatImageList(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Map<String, Object>map=null;
		if (session.getAttribute(SE_IMAGE_MAP)==null) {
			map=updateImageMap(session);
		}else{
			map=(HashMap<String, Object>)session.getAttribute(SE_IMAGE_MAP);
		}
		return map;
	}

	/**
	 * 
	 *
	 * @param page
	 * @param req
	 * @return
	 * @Description TODO
	 * @author justbamboo
	 * @date 2016年1月3日 下午11:05:26
	 */
	private Map<String, Object> updateImageMap(HttpSession session) {
		Map<String, Object>map=getAllImageMap();
		session.setAttribute(SE_IMAGE_MAP, map);
		return map;
	}
	
	/**
	 * 
	*
	* @return
	* @Description 初始化获取图片列表
	* @author justbamboo
	* @date 2016年1月4日 上午12:34:40
	 */
	private Map<String, Object> getAllImageMap(){
		int total=wechatService.getWechatMediaCount().getImage();
		int totalPage=total%20==0?total/20:(total/20)+1;
		Map<String, Object>map=new HashMap<String, Object>();
		for (int i = 1; i <=totalPage; i++) {
			GetMaterialListResponse resp=wechatService.getMediaImage(i);
			if(resp.getItems()!=null) {
				for (Map<String, Object> m : resp.getItems()) {
					map.put((String) m.get("media_id"), m.get("url"));
				}
			}
		}
		return map;
	}
}
