package com.mtd.service;

import com.mtd.entity.Cms_Likes;
import com.mtd.entity.Cms_LikesVo;

public interface CmsLikeService {

	public void createCmsLike(String type,String renfo,String userId);
	
	public void deleteCmsLike(int id);
	
	public Cms_LikesVo getCmsLike(String type,String renfo,String userId);
	
	public Cms_Likes findCmsLike(String type,String renfo,String userId);
}
