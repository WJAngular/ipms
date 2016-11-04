package com.mtd.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mtd.entity.Cms_Likes;
import com.mtd.service.CmsLikeService;

/**
 * 
 * 
 * @ClassName: Cms_LikesController
 * @Description: TODO
 * @author just_bamboo
 * @date 2016年1月18日 上午10:35:07
 *
 */
@Controller
@RequestMapping("/cms/likes")
public class Cms_LikesController {
	@Resource
	private CmsLikeService cmsLikeService;

	@RequestMapping("/wechat/update")
	@ResponseBody
	public String updateCmsLike(String type,String renfo,String userId){
		System.out.println("click like");
		Cms_Likes l=cmsLikeService.findCmsLike(type, renfo, userId);
		System.out.println(l==null?null:JSON.toJSON(l));
		if(l==null){
			cmsLikeService.createCmsLike(type, renfo, userId);
		}else{
			cmsLikeService.deleteCmsLike(l.getId());
		}
		return "ok";
	}
	
	
}
