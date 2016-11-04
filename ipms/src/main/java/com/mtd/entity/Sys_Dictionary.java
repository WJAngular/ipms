package com.mtd.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "Sys_Dictionary")
public class Sys_Dictionary {

	public Sys_Dictionary() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	/** 编号 */
	@Column(name = "code", length = 50, nullable = true)
	private String code;

	/** 上级CODE */
	@Column(name = "pcode", length = 50)
	private String pcode;

	/** 栏目CODE */
	@Column(name = "mcode", length = 50)
	private String mcode;

	/** 其他CODE */
	@Column(name = "ocode", length = 50)
	private String ocode;

	/** 名称 */
	@Column(name = "name", length = 100)
	private String name;

	/** 描述*/
	@Column(name = "descripton", length = 200)
	private String descripton;

	/** 层次 */
	@Column(name = "levels")
	private Integer levels;

	/** 状态*/
	@Column(name = "status")
	private Integer status;

	//** 固定标示 */
	@Column(name = "fixed", length = 1)
	private String fixed;

	/** 是否有效*/
	@Column(name = "isvalid", length = 1)
	private String isvalid;
	
	/** 所属机构 */
	@Column(name = "org_id", length = 32)
	private String org_id;

	/** 排序*/
	@Column(name = "orderby")
	private Integer orderby;

	/** 备注 */
	@Column(name = "remark", length = 4000)
	private String remark;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getMcode() {
		return mcode;
	}

	public void setMcode(String mcode) {
		this.mcode = mcode;
	}

	public String getOcode() {
		return ocode;
	}

	public void setOcode(String ocode) {
		this.ocode = ocode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescripton() {
		return descripton;
	}

	public void setDescripton(String descripton) {
		this.descripton = descripton;
	}

	public Integer getLevels() {
		return levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFixed() {
		return fixed;
	}

	public void setFixed(String fixed) {
		this.fixed = fixed;
	}

	public String getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public Integer getOrderby() {
		return orderby;
	}

	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

}
