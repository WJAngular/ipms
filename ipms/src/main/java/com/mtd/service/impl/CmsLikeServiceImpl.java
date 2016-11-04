package com.mtd.service.impl;

import java.util.List;

import com.mtd.dao.CmsLikeDao;
import com.mtd.entity.Cms_Likes;
import com.mtd.entity.Cms_LikesVo;
import com.mtd.service.CmsLikeService;

public class CmsLikeServiceImpl implements CmsLikeService {

	private CmsLikeDao like;

	public CmsLikeDao getLike() {
		return like;
	}

	public void setLike(CmsLikeDao like) {
		this.like = like;
	}

	@Override
	public void createCmsLike(String type, String renfo, String userId) {
		like.create(new Cms_Likes(type, renfo, userId));
	}

	@Override
	public void deleteCmsLike(int id) {
		Object obj = like.findByID(Cms_Likes.class, id);
		if (obj != null) {
			like.delete(obj);
		}
	}

	@Override
	public Cms_LikesVo getCmsLike(String type, String renfo, String userId) {
		String h1 = "From Cms_Likes l where l.likeType='" + type + "' and l.likeRefno='" + renfo + "'";
		String h2 = "From Cms_Likes l where l.likeType='" + type + "' and l.likeRefno='" + renfo + "' and l.likeUser='"
				+ userId+"'";
		List<Object> likes = like.listByHQL(h1);
		int likeCount = likes == null ? 0 : likes.size();
		List<Object> userLike = like.listByHQL(h2);
		Cms_Likes ul = null;
		if (userLike!= null&&userLike.size()>0) {
			ul = (Cms_Likes) userLike.get(0);
		}
		return new Cms_LikesVo(likeCount, ul);
	}

	@Override
	public Cms_Likes findCmsLike(String type, String renfo, String userId) {
		String h = "From Cms_Likes l where l.likeType='" + type + "' and l.likeRefno='" + renfo + "' and l.likeUser='"
				+ userId+"'";
		List<Object> userLike = like.listByHQL(h);
		if (userLike!= null&&userLike.size()>0) {
			return (Cms_Likes) userLike.get(0);
		}
		return null;
	}

}
