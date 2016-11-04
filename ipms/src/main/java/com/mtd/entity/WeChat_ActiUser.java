package com.mtd.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 记录活动人群表
 * 
 * @author JustBamboo
 *
 */
@Entity(name = "WeChat_ActiUser")
public class WeChat_ActiUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="openedId")
	private String openedId;

	@Column(name="lastTime")
	private Date lastTime;

	@Column(name="action")
	private String action;

	
	
	public WeChat_ActiUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WeChat_ActiUser(String openedId,String action) {
		super();
		this.openedId = openedId;
		this.lastTime = new Date();
		this.action = action;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpenedId() {
		return openedId;
	}

	public void setOpenedId(String openedId) {
		this.openedId = openedId;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
