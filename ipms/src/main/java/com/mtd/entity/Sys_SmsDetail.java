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
@Entity(name = "Sys_SmsDetail")
public class Sys_SmsDetail {

	public Sys_SmsDetail() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "smsid")
	private Integer smsid;
	/** 接收者 */
	@Column(name = "recevier", length = 1000)
	private String recevier;
	
	/** 内容 */
	@Column(name = "contents", length = 500)
	private String contents;
	
	/** 电话 */
	@Column(name = "tel", length = 20)
	private String tel;

	/** 状态 */
	@Column(name = "status", length = 10)
	private String status;
	
	/** 返回信息*/
	@Column(name = "info", length = 500)
	private String info;
	
	/** type*/
	@Column(name = "type", length = 10)
	private String type;


	/** 新增日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "addtime")
	private Date addtime;

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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getSmsid() {
		return smsid;
	}

	public void setSmsid(Integer smsid) {
		this.smsid = smsid;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
