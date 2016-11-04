package com.mtd.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 短信表
 * @author Chao
 *
 */
@Entity(name = "Sys_Sms")
public class Sys_Sms {

	public Sys_Sms() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	/** 接收者 */
	@Column(name = "recevier", length = 5000)
	private String recevier;

	/** 内容 */
	@Column(name = "contents", length = 2000)
	private String contents;

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

	/** 失败手机 */
	@Column(name = "errorNumber", length = 2000)
	private String errorNumber;

	/** 返回信息*/
	@Column(name = "info", length = 2000)
	private String info;
	
	/** type*/
	@Column(name = "type", length = 10)
	private String type;

	/** error*/
	@Column(name = "error", length = 10)
	private String error;
	
	/** 党支部 */
	@Column(name = "branch", length = 50)
	private String branch;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRecevier() {
		return recevier;
	}

	public void setRecevier(String recevier) {
		this.recevier = recevier;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAdduser() {
		return adduser;
	}

	public void setAdduser(String adduser) {
		this.adduser = adduser;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getErrorNumber() {
		return errorNumber;
	}

	public void setErrorNumber(String errorNumber) {
		this.errorNumber = errorNumber;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

}
