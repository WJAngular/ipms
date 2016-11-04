package com.mtd.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "WeChat_AutoReply")
public class WeChat_AutoReply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="ruleName")
	private String ruleName;
	
	@Column(name="ruleKeyword")
	private String ruleKeyword;
	
	/**回复类别（text，image，mpnews）*/
	@Column(name="replyType")
	private String replyType;
	
	/**回复内容*/
	@Column(name="replyContent")
	private String replyContent;
	
	@Column(name="ruleCreateTime")
	private Date ruleCreateTime;
	
	@Column(name="ruleStatus")
	private String ruleStatus;

	
	public WeChat_AutoReply() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WeChat_AutoReply(String ruleName, String ruleKeyword,
			String replyType, String replyContent) {
		this.ruleName = ruleName;
		this.ruleKeyword = ruleKeyword;
		this.replyType = replyType;
		this.replyContent = replyContent;
		this.ruleCreateTime = new Date();
		this.ruleStatus = "Y";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleKeyword() {
		return ruleKeyword;
	}

	public void setRuleKeyword(String ruleKeyword) {
		this.ruleKeyword = ruleKeyword;
	}

	public String getReplyType() {
		return replyType;
	}

	public void setReplyType(String replyType) {
		this.replyType = replyType;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getRuleCreateTime() {
		return ruleCreateTime;
	}

	public void setRuleCreateTime(Date ruleCreateTime) {
		this.ruleCreateTime = ruleCreateTime;
	}

	public String getRuleStatus() {
		return ruleStatus;
	}

	public void setRuleStatus(String ruleStatus) {
		this.ruleStatus = ruleStatus;
	}
	
	public String[] getKeyList(){
		return this.ruleKeyword.split(",");
	}
	
	
}
