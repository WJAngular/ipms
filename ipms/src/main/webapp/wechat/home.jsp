<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" ng-app="mtd">
<head>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>微首页</title>
	<link href="<%=request.getContextPath()%>/wechat/css/ionic.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/wechat/css/mystyle.css" rel="stylesheet">
</head>
<body id="home" ng-cloak>
	<ion-content>
		<!-- 焦点切换 -->
		<ion-slide-box does-continue="true" auto-play="true" class="bannar">
			<ion-slide><img src="img/slide-1.jpg"></ion-slide>
			<ion-slide><img src="img/slide-2.jpg"></ion-slide>
			<ion-slide><img src="img/slide-3.jpg"></ion-slide>
			<ion-slide><img src="img/slide-1.jpg"></ion-slide>
			
		</ion-slide-box>
		<!-- 快捷按钮图标 -->
		<div class="row">
			<div class="col col-33">
				<a href="<%=request.getContextPath()%>/cms_content/wechat/getNewList.do?topic=djyw"><div class="circle cla">
					<i class="icon ion-chatbox"></i>
				</div></a>
				<p>党建要闻</p>
			</div>
			<div class="col col-33">
				<a href="<%=request.getContextPath()%>/cms_content/wechat/getNewList.do?topic=hdjy"><div class="circle clb">
					<i class="icon ion-images"></i>
				</div></a>
				<p>活动剪影</p>
			</div>
			<div class="col col-33">
				<a href="<%=request.getContextPath()%>/cms_content/wechat/getNewList.do?topic=hytz"><div class="circle clc">
					<i class="icon ion-android-clipboard"></i>
				</div></a>
				<p>会议公告</p>
			</div>
		</div>
		<div class="row">
			<div class="col col-33">
			<a href="<%=request.getContextPath()%>/cms_course/wechat/getNewList.do"><div class="circle cld">
					<i class="icon ion-ios-book"></i>
				</div></a>
				<p>党课学习</p>
			</div>
			<div class="col col-33">
				<a href="<%=request.getContextPath()%>/cms_studio/wechat/getNewList.do"><div class="circle cle">
					<i class="icon ion-briefcase"></i>
				</div></a>
				<p>党代表工作室</p>
			</div>
		</div>
	</ion-content>
</body>
<script src="<%=request.getContextPath()%>/wechat/js/ionic.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/wechat/controller/app.js"></script>
</html>