<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" ng-app="course">
<head>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>会议</title>
	<link href="<%=request.getContextPath()%>/wechat/css/ionic.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/wechat/css/mystyle.css" rel="stylesheet">
	<style>
		html,body{overflow-y:auto;}
		.item-complex .item-content{ line-height: 24px }
		.c_title{
			width: 100%;
			height: 50px;
			background:url('<%=request.getContextPath()%>/wechat/img/hy.jpg') #EF5B5B no-repeat;
			background-size: auto 100%;
		}
	</style>
</head>
<body id="testlist" ng-controller="meetingCtrl" ng-cloak>
	<div class="c_title"></div>
	<ion-content>
		<ion-list>
			<ion-item ng-repeat="item in items" href="<%=request.getContextPath()%>/cms_meeting/wechat/getWechatMeeting.do?id={{item.id}}">
				{{ item.title }}
				<span class="item-note">
					{{ item.addtimeString }}
				</span>
			</ion-item>
		</ion-list>
	</ion-content>
</body>
<script src="<%=request.getContextPath()%>/wechat/js/jquery-1.9.1.min.js"></script>
<script src="<%=request.getContextPath()%>/wechat/js/ionic.bundle.js"></script>
<script src="<%=request.getContextPath()%>/wechat/controller/meet.js"></script>
</html>