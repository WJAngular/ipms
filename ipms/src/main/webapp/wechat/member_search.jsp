<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" ng-app="mtd">
<head>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>党员查询</title>
	<link href="<%=request.getContextPath()%>/wechat/css/ionic.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/wechat/css/mystyle.css" rel="stylesheet">
	<style>
		html{ background: #FFFFFF;}
		.bar{ height:auto; background:#E9E9E9;}
		#searchBtn{ width:20%; color:#11c1f3; font-size:14px;}
		.bar.item-input-inset .item-input-wrapper input{ width:80%;}
	</style>
</head>
<body id="msearch">
	<form action="<%=request.getContextPath()%>/user/wechat/getPersonSearch.do" method="post">
		<ion-header-bar class="item-input-inset">
			<label class="item-input-wrapper">
				<i class="icon ion-ios-search placeholder-icon"></i> 
				<input type="search" name="search" value="${search }" placeholder="输入姓名进行检索">
				<button class="button button-clear" id="searchBtn">搜索</button>
			</label> 
		</ion-header-bar>
		<ion-content>
			<div class="list">
				<c:if test="${!empty userList }">
					<c:forEach items="${userList }" var="t" varStatus="d">
						<a class="item" href="<%=request.getContextPath()%>/user/wechat/showUserDetail.do?id=${t.id}"> ${t.name }
						<span class="item-note"> 查看 <i class="icon ion-ios-arrow-right"></i></span>
						</a>
					</c:forEach>
				</c:if>
			</div>
		</ion-content>
	</form>
</body>
<script src="<%=request.getContextPath()%>/wechat/js/ionic.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/wechat/controller/app.js"></script>
</html>