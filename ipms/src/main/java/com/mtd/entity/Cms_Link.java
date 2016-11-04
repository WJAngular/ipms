package com.mtd.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "Cms_Link")
public class Cms_Link {

	public Cms_Link() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	/** 编号*/
	@Column(name = "refno", length = 50)
	private String refno;

	/** 链接名称 */
	@Column(name = "linkname", length = 50)
	private String linkname;

	/** 链接地址 */
	@Column(name = "linkUrl", length = 1000)
	private String linkUrl;

	/** 备注 */
	@Column(name = "remark", length = 1000)
	private String remark;

	/** 链接方式 */
	@Column(name = "linktype", length = 50)
	private String linktype;

	/** 图标地址 */
	@Column(name = "iconurl", length = 500)
	private String iconurl;

	/** 是否有效 */
	@Column(name = "isvaild", length = 2)
	private String isvaild;

	/** 排序 */
	@Column(name = "orderby")
	private Integer orderby;
	
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
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLinkname() {
		return linkname;
	}

	public void setLinkname(String linkname) {
		this.linkname = linkname;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLinktype() {
		return linktype;
	}

	public void setLinktype(String linktype) {
		this.linktype = linktype;
	}

	public String getIsvaild() {
		return isvaild;
	}

	public void setIsvaild(String isvaild) {
		this.isvaild = isvaild;
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

	public String getIconurl() {
		return iconurl;
	}

	public void setIconurl(String iconurl) {
		this.iconurl = iconurl;
	}

	public String getRefno() {
		return refno;
	}

	public void setRefno(String refno) {
		this.refno = refno;
	}

	public Integer getOrderby() {
		return orderby;
	}

	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}

}
