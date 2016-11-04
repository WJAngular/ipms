<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" ng-app="mtd">
<head>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>我是党员</title>
	<link href="<%=request.getContextPath()%>/wechat/css/ionic.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/wechat/css/mystyle.css" rel="stylesheet">
	<style>
		.item-avatar { padding-left: 80px;}
		.item-avatar > img:first-child{ top: 12px; max-width: 55px; max-height: 55px; border-radius: 5px; }
	</style>
</head>
<body id="me" ng-controller="meCtrl">
	<ion-content>
		<div class="list margin">
			<div class="item item-avatar">
				<c:if test="${!empty imgPath}">
					<img src="${imgPath}">
				</c:if>
				<h2>${user.name} <i class="icon ion-male sexy"></i></h2>
				<p><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/>　　党员</p>
			</div>
		</div>

		<div class="list">
			<a class="item item-icon-left item-icon-right" href="<%=request.getContextPath()%>/user/wechat/showUserinfo.do?id=${user.id}">
				<i class="icon ion-ribbon-b info"></i> 个人信息
				<i class="icon ion-ios-arrow-right aright"></i>
			</a> 
			<c:if test="${is_dw}">
				<a class="item item-icon-left item-icon-right" href="<%=request.getContextPath()%>/wechat/member_search.jsp">
					<i class="icon ion-ios-search-strong search"></i> 党员查询
					<i class="icon ion-ios-arrow-right aright"></i>
				</a>
			</c:if> 
			<%-- 
				<a class="item item-icon-left item-icon-right" href="<%=request.getContextPath()%>/wechat/payment.jsp">
					<i class="icon ion-social-yen pay"></i> 党费缴纳
					<i class="icon ion-ios-arrow-right aright"></i>
				</a>
			--%>
			<a class="item item-icon-left item-icon-right" href="<%=request.getContextPath()%>/wechat/sign.do?user=${user.id}">
				<i class="icon ion-ios-location map"></i> 党员签到
				<i class="icon ion-ios-arrow-right aright"></i>
			</a>
			<a class="item item-icon-left item-icon-right" href="" ng-click="logOut()">
				<i class="icon ion-log-out personal"></i> 退出登录
				<i class="icon ion-ios-arrow-right aright"></i>
			</a>
		</div>
	</ion-content>
</body>
<script src="<%=request.getContextPath()%>/wechat/js/ionic.bundle.js"></script>
<script src="<%=request.getContextPath()%>/wechat/controller/app.js"></script>
<script>
	mtd.controller('meCtrl',['$scope','$ionicPopup',function($scope,$ionicPopup){
		$scope.logOut = function(){
			var confirmPopup = $ionicPopup.confirm({
				title: '是否要退出登录',
				buttons: [
					{ text: '否'},
					{ 
						text  : '是',
						type  : 'button-balanced',
						onTap : function(){
							window.location.href = '<%=request.getContextPath()%>/user/wechat/UnUserinfo.do?id=${user.id}';
						}
					}
				]
			});
		}
		
	}])
</script>
</html>