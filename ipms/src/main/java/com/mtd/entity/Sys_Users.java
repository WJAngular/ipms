package com.mtd.entity;

import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 党员表
 * 
 * @author Chao
 *
 */
@Entity(name = "Sys_Users")
public class Sys_Users {

	public Sys_Users() {
		super();
	}

	public Sys_Users(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	/** 账号 */
	@Column(name = "username", length = 50, unique = true)
	private String username;
	
	/** 账号 */
	@Column(name = "name", length = 50 )
	private String name;

	/** 密码 */
	@Column(name = "password", length = 50)
	private String password;

	/** 用户类型 */
	@Column(name = "type", length = 50)
	private String type;

	/** IP地址 */
	@Column(name = "ip", length = 50)
	private String ip;

	/** 状态 */
	@Column(name = "status", length = 10)
	private String status;

	/** 新增日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "addtime")
	private Date addtime;

	/** 新增者 */
	@Column(name = "adduser", length = 50)
	private String adduser;

	/** 更新日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "updtime")
	private Date updtime;

	/** 更新者 */
	@Column(name = "upduser", length = 50)
	private String upduser;

	/** 是否有效 */
	@Column(name = "isvaild", length = 2)
	private String isvaild;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getAdduser() {
		return adduser;
	}

	public void setAdduser(String adduser) {
		this.adduser = adduser;
	}

	public Date getUpdtime() {
		return updtime;
	}

	public void setUpdtime(Date updtime) {
		this.updtime = updtime;
	}

	public String getUpduser() {
		return upduser;
	}

	public void setUpduser(String upduser) {
		this.upduser = upduser;
	}

	public String getIsvaild() {
		return isvaild;
	}

	public void setIsvaild(String isvaild) {
		this.isvaild = isvaild;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
