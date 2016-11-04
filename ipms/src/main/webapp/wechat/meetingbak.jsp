<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" ng-app="meetingMod">
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>会议活动通知</title>
<link href="<%=request.getContextPath()%>/wechat/css/ionic.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/wechat/css/mystyle.css" rel="stylesheet">
</head>
<body id="meeting" ng-cloak>
	<ion-nav-view name="meetingDetail"></ion-nav-view>
	<script id="templates/meetlist.html" type="text/ng-template">
		<ion-view view-title="会议活动通知">
			<ion-content>
				<div class="list meeting">					
					<div class="card" ng-repeat="meet in meetData">
						<div class="item item-avatar" style="padding-left:60px">
							<kind valid="{{ meet.topic }}"></kind>
							<type valid="{{ meet.topic }}"></type>
							<h2>{{ meet.title }}</h2>
						</div>
                        <div class="item">
							<a href="#/detail/{{ $index }}" class="seedetail"><i class="icon ion-forward"></i> 阅读详细</a>
						</div>
					</div>
				</div>
			</ion-content>
		</ion-view>
	</script>
	<script id="templates/detail.html" type="text/ng-template">
		<ion-view view-title="详细">
			<ion-content id="eventPlaceholder">
				<div class="list list-inset">
					<div class="item">
						<h2>{{ detail.title }}</h2>
					</div>
					<div class="item">
						负责人<span class="item-note">{{ detail.author }}</span>
					</div>
				</div>
				<div class="item item-divider positive"><i class="icon ion-ios-paper"></i> 详细内容</div>	
				<div class="col det_content" ng-bind-html="detail.contents|to_trusted"></div>
			</ion-content>
		</ion-view>
	</script>
	<!-- <ion-footer-bar class="bar-assertive">
				<button class="button button-clear" ng-click="showConfirm()">参与</button>
			</ion-footer-bar> -->
</body>
<script src="<%=request.getContextPath()%>/wechat/js/jquery-1.9.1.min.js"></script>
<script src="<%=request.getContextPath()%>/wechat/js/ionic.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/wechat/js/angular/angular-sanitize.min.js"></script>
<script src="<%=request.getContextPath()%>/wechat/controller/meeting.js"></script>
</html>