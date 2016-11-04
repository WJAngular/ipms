<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" ng-app="mtd">
<head>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>便民服务</title>
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
			<!--<ion-slide><img src="img/slide-1.jpg"></ion-slide>-->
		</ion-slide-box>
		<!-- 快捷按钮图标 -->
		<div class="row">
			<div class="col col-50">
				<a href="<%=request.getContextPath()%>/newslist/wechat/${userid }/showFlowsDetail.do"><div class="circle cla">
					<i class="icon ion-ios-paper"></i>
				</div></a>
				<p>党员发展流程</p>
			</div>
			<div class="col col-50">
				<a href="<%=request.getContextPath()%>/cms_content/${userid }/wechat/getXZSWList.do"><div class="circle clb">
					<i class="icon ion-briefcase"></i>
				</div></a>
				<p>办事指南</p>
			</div>
			
		</div>
		<div class="row">
			<div class="col col-50">
				<a href="<%=request.getContextPath()%>/wechat/map.jsp"><div class="circle clc">
					<i class="icon ion-ios-location"></i>
				</div></a>
				<p>服务机构地图</p>
			</div>
			<div class="col col-50">
				<a href="<%=request.getContextPath()%>/wechat/other.jsp"><div class="circle cld">
					<i class="icon ion-link"></i>
				</div></a>
				<p>相关链接</p>
			</div>
		</div>
	</ion-content>
</body>
<script src="<%=request.getContextPath()%>/wechat/js/ionic.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/wechat/controller/app.js"></script>
</html>