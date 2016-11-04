package com.mtd.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Cms_Likes")
public class Cms_Likes {

	public Cms_Likes() {
	}
	
	public Cms_Likes(String likeType, String likeRefno, String likeUser) {
		this.likeType = likeType;
		this.likeRefno = likeRefno;
		this.likeUser = likeUser;
		this.createTime = new Date();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "likeType",length=50)
	private String likeType;
	
	@Column(name = "likeRefno",length=50)
	private String likeRefno;
	
	@Column(name = "likeUser")
	private String likeUser;//openid
	
	@Column(name = "createTime")
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLikeType() {
		return likeType;
	}

	public void setLikeType(String likeType) {
		this.likeType = likeType;
	}

	public String getLikeRefno() {
		return likeRefno;
	}

	public void setLikeRefno(String likeRefno) {
		this.likeRefno = likeRefno;
	}

	public String getLikeUser() {
		return likeUser;
	}

	public void setLikeUser(String likeUser) {
		this.likeUser = likeUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}
