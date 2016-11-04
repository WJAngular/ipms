package com.mtd.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "Sys_Log")
public class Sys_Log {
	
	public Sys_Log() {
		super();
	}

	public Sys_Log(String username,String password,String status,String rmk,String ip,String type) {
		this.username=username;
		this.password=password;
		this.status=status;
		this.rmk=rmk;
		this.ip=ip;
		this.type=type;
		this.addtime=new Date();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "username", length = 50)
	private String username;
	
	@Column(name = "password", length = 50)
	private String password;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date addtime;
	
	@Column(name = "status", length = 500)
	private String status;
	
	@Column(name = "rmk", length = 1000)
	private String rmk;
	
	@Column(name = "type", length = 50)
	private String type;
	
	@Column(name = "ip", length = 50)
	private String ip;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
