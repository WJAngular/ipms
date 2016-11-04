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
	<title>三会一课</title>
	<link href="<%=request.getContextPath()%>/wechat/css/ionic.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/wechat/css/mystyle.css" rel="stylesheet">
	<style>
		.item-complex .item-content{ line-height: 24px }
	</style>
</head>
<body id="testlist">
	<ion-header-bar class="bar-light">
		<div class="btn-group">
			<a class="tab-btn" id="meet" href="#/meeting">会议</a>
			<a class="tab-btn" id="course" href="#/course">党课</a>
		</div>
	</ion-header-bar>
	<ion-nav-view name="courseview"></ion-nav-view>
	
	<script id="templates/meeting.html" type="text/ng-template">
		<ion-content>
			<ion-refresher pulling-text="刷新" 
						   pulling-icon="ion-ios-arrow-up" 
						   spinner="lines" 
						   on-refresh="doRefresh()">
			</ion-refresher>
			<ion-list>
				<ion-item ng-repeat="item in items" href="<%=request.getContextPath()%>/cms_meeting/wechat/getWechatMeeting.do?id={{item.id}}">
					{{ item.title }}
					<span class="item-note">
						{{ item.addtimeString }}
					</span>
				</ion-item>
			</ion-list>
		</ion-content>
	</script>
	
	<script id="templates/course.html" type="text/ng-template">
		<ion-content>
			<ion-list>
				<ion-item ng-repeat="item in items" href="<%=request.getContextPath()%>/cms_course/wechat/getWechatCourse.do?id={{item.id}}">
					{{ item.title }}
					<span class="item-note">
						{{ item.addtimeString }}
					</span>
				</ion-item>
			</ion-list>
		</ion-content>
	</script>
</body>
<script src="<%=request.getContextPath()%>/wechat/js/jquery-1.9.1.min.js"></script>
<script src="<%=request.getContextPath()%>/wechat/js/ionic.bundle.js"></script>
<script src="<%=request.getContextPath()%>/wechat/controller/meet_course.js"></script>
</html>