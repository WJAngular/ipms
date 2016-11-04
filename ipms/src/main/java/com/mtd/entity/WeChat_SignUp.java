package com.mtd.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "WeChat_SignUp")
public class WeChat_SignUp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="sign_user")
	private String sign_user;
	
	@Column(name="sign_date")
	private String sign_date;
	
	@Column(name="sign_time")
	private String sign_time;
	
	@Column(name="sign_address")
	private String sign_address;
	
	@Column(name="sign_latitude")
	private String sign_latitude;
	
	@Column(name="sign_longtitude")
	private String sign_longtitude;

	public WeChat_SignUp() {
		super();
	}
	
	public WeChat_SignUp(String sign_user, String sign_date, String sign_time,
			String sign_address, String sign_latitude, String sign_longtitude) {
		super();
		this.sign_user = sign_user;
		this.sign_date = sign_date;
		this.sign_time = sign_time;
		this.sign_address = sign_address;
		this.sign_latitude = sign_latitude;
		this.sign_longtitude = sign_longtitude;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSign_user() {
		return sign_user;
	}

	public void setSign_user(String sign_user) {
		this.sign_user = sign_user;
	}

	public String getSign_date() {
		return sign_date;
	}

	public void setSign_date(String sign_date) {
		this.sign_date = sign_date;
	}

	public String getSign_time() {
		return sign_time;
	}

	public void setSign_time(String sign_time) {
		this.sign_time = sign_time;
	}

	public String getSign_address() {
		return sign_address;
	}

	public void setSign_address(String sign_address) {
		this.sign_address = sign_address;
	}

	public String getSign_latitude() {
		return sign_latitude;
	}

	public void setSign_latitude(String sign_latitude) {
		this.sign_latitude = sign_latitude;
	}

	public String getSign_longtitude() {
		return sign_longtitude;
	}

	public void setSign_longtitude(String sign_longtitude) {
		this.sign_longtitude = sign_longtitude;
	}

	
	
}
