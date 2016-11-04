package com.mtd.entity;

public class Cms_LikesVo {

	private int likeCount;
	
	private Cms_Likes isLike;

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}



	public Cms_Likes getIsLike() {
		return isLike;
	}

	public void setIsLike(Cms_Likes isLike) {
		this.isLike = isLike;
	}

	public Cms_LikesVo() {
		super();
	}

	public Cms_LikesVo(int likeCount, Cms_Likes isLike) {
		super();
		this.likeCount = likeCount;
		this.isLike = isLike;
	}


	
	
}
