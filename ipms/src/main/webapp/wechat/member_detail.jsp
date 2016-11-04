<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" ng-app="mtd">
<head>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>个人信息</title>
	<link href="<%=request.getContextPath()%>/wechat/css/ionic.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/wechat/css/mystyle.css" rel="stylesheet">
</head>
<body >
	<ion-content>
		<div class="row personInfo">
			<div class="col col-33">
				<img src="<%=request.getContextPath()%>/wechat/img/photo.png" width="80" style="border-radius:80px;"/>
			</div>
			<div class="col col-67" style="line-height: 40px;border-left: none;">
				<h4>${user.name}</h4>
				<p class="birth">出生日期：<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/></p>
			</div>
		</div>
		<!-- <div class="item item-divider">
			党组织信息
		</div> -->
		<div class="list infomate">
			<div class="row">
				<div class="col personlist col-33"><strong>性别</strong></div>
	  			<div class="col personlist col-67">${user.sex}</div>
			</div>
			<div class="row">
				<div class="col personlist col-33"><strong>民族</strong></div>
	  			<div class="col personlist col-67">${user.nation}</div>
			</div>
			<div class="row">
				<div class="col personlist col-33"><strong>身份证号码</strong></div>
	  			<div class="col personlist col-67">${user.idCard}</div>
			</div>
			<div class="row">
				<div class="col personlist col-33"><strong>联系电话</strong></div>
	  			<div class="col personlist col-67">${user.tel}</div>
			</div>
			<div class="row">
				<div class="col personlist col-33"><strong>电子邮箱</strong></div>
	  			<div class="col personlist col-67">${user.email}</div>
			</div>
			<div class="row">
				<div class="col personlist col-33"><strong>联系地址</strong></div>
	  			<div class="col personlist col-67">${user.address}</div>
			</div>
			<div class="item item-divider member">党组织信息</div>
			<div class="row" ng-show="true">
				<div class="col personlist col-33"><strong>所属党委</strong></div>
	  			<div class="col personlist col-67">${user.organization}</div>
			</div>
			<div class="row" ng-show="true">
				<div class="col personlist col-33"><strong>所属支部</strong></div>
	  			<div class="col personlist col-67">${user.branch}</div>
			</div>
			<div class="row" ng-show="true">
				<div class="col personlist col-33"><strong>支部书记</strong></div>
	  			<div class="col personlist col-67">${user.branchSecretary}</div>
			</div>
			<div class="row" ng-show="true">
				<div class="col personlist col-33"><strong>支部书记电话</strong></div>
	  			<div class="col personlist col-67">${user.branchSecretaryTel}</div>
			</div>
		</div>
	</ion-content>
</body>
<script src="<%=request.getContextPath()%>/wechat/js/ionic.bundle.js"></script>
<script src="<%=request.getContextPath()%>/wechat/controller/app.js"></script>
</html>