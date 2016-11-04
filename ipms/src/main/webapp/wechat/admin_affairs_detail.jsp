<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>详情</title>
<link href="<%=request.getContextPath()%>/wechat/css/ionic.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/wechat/css/mystyle.css" rel="stylesheet">
<style type="text/css">
html, body {
	background: #fff;
}

.row {
	flex-direction: column;
}
</style>
</head>
<body>
	<ion-content>
	<div class="row responsive-sm">
		<div class="col detail_title">
			<h3>${cms.title}</h3>
		</div>
		<div class="col">
			<span class="info_time"><fmt:formatDate value="${cms.addtime}" pattern="yyyy-MM-dd HH:mm" /></span> <span class="info_resource"><a href="javascript:void()">${cms.author}</a></span> <span
				class="info_like">${cms.status} <i class="icon ion-ios-heart"></i></span>
		</div>
		<div class="col picPreview">
			<img src="${cms.picUrl}">
		</div>
		<div class="col abstract">${cms.abstracts}</div>
		<div class="col detail_content">${cms.contents}</div>
		<div class="col">
			<div class="item tabs tabs-icon-left">
				<div class="tab-item" ng-click="userClick($event)">
					<i class="icon {{userlike}}"></i> 点赞
				</div>
				<div class="tab-item share">
					<i class="icon ion-share"></i> 分享
				</div>
			</div>
		</div>
	</div>
	</ion-content>
</body>
<script src="<%=request.getContextPath()%>/wechat/js/ionic.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/wechat/js/angular/angular-sanitize.min.js"></script>
</html>