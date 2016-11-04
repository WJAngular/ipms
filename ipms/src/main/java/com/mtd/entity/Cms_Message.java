package com.mtd.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 图文表
 * 
 * @author Chao
 *
 */
@Entity(name = "Cms_Message")
public class Cms_Message {

	public Cms_Message() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	/** 编号 */
	@Column(name = "refno", length = 50)
	private String refno;

	/** 栏目 */
	@Column(name = "topic", length = 50)
	private String topic;

	/** 主题 */
	@Column(name = "title", length = 200)
	private String title;

	/** 摘要*/
	@Column(name = "abstracts", length = 500)
	private String abstracts;

	/** 内容 */
	@Column(name = "contents")
	private String contents;

	/** 有效日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "vaildtime", length = 20)
	private Date vaildtime;

	/** 作者 */
	@Column(name = "author", length = 20)
	private String author;

	/** 权限 */
	@Column(name = "authority", length = 20)
	private String authority;

	/** 是否有效 */
	@Column(name = "isvaild", length = 2)
	private String isvaild;

	/** 图片链接 */
	@Column(name = "picUrl", length = 500)
	private String picUrl;

	@Column(name = "picMedia", length = 500)
	private String picMedia;

	@Column(name = "picMediaUrl", length = 500)
	private String picMediaUrl;
	
	
	@Column(name = "groups", length = 50)
	private String groups;
	/** 审核状态 */
	@Column(name = "status", length = 2)
	private String status;

	/** 是否短信 */
	@Column(name = "ismessage", length = 2)
	private String ismessage;

	/** 党委*/
	@Column(name = "organization", length = 50)
	private String organization;
	

	/** 支部 */
	@Column(name = "branch", length = 50)
	private String branch;

	/** 审核日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "apptime")
	private Date apptime;

	/** 审核人*/
	@Column(name = "appuser", length = 20)
	private String appuser;

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

	public String getPicMediaUrl() {
		return picMediaUrl;
	}

	public void setPicMediaUrl(String picMediaUrl) {
		this.picMediaUrl = picMediaUrl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getRefno() {
		return refno;
	}

	public void setRefno(String refno) {
		this.refno = refno;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
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

	public String getUpduser() {
		return upduser;
	}

	public void setUpduser(String upduser) {
		this.upduser = upduser;
	}

	public Date getUpdtime() {
		return updtime;
	}

	public void setUpdtime(Date updtime) {
		this.updtime = updtime;
	}

	public String getIsvaild() {
		return isvaild;
	}

	public void setIsvaild(String isvaild) {
		this.isvaild = isvaild;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Date getVaildtime() {
		return vaildtime;
	}

	public void setVaildtime(Date vaildtime) {
		this.vaildtime = vaildtime;
	}

	public String getPicMedia() {
		return picMedia;
	}

	public void setPicMedia(String picMedia) {
		this.picMedia = picMedia;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getIsmessage() {
		return ismessage;
	}

	public void setIsmessage(String ismessage) {
		this.ismessage = ismessage;
	}

	public Date getApptime() {
		return apptime;
	}

	public void setApptime(Date apptime) {
		this.apptime = apptime;
	}

	public String getAppuser() {
		return appuser;
	}

	public void setAppuser(String appuser) {
		this.appuser = appuser;
	}

}
