<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" ng-app="mtd">
<head>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>其他链接</title>
	<link href="<%=request.getContextPath()%>/wechat/css/ionic.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/wechat/css/mystyle.css" rel="stylesheet">
	<style>
		.row{ flex-wrap:wrap;}
	</style>
</head>
<body id="otherlink" ng-cloak>
	<ion-content>
		<div class="row" ng-controller="linkCtrl">
			<div class="col col-33" ng-repeat="othlink in othlinks">
				<a href="{{othlink.linkUrl}}">
					<img ng-src="{{othlink.iconurl}}" width="50" height="50">
					<p ng-bind="othlink.linkname"></p>
				</a>
			</div>
		</div>
	</ion-content>
</body>
<script src="<%=request.getContextPath()%>/wechat/js/ionic.bundle.js"></script>
<script src="<%=request.getContextPath()%>/wechat/controller/app.js"></script>
</html>