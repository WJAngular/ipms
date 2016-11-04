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
<title>社区行政事务</title>
<link href="<%=request.getContextPath()%>/wechat/css/ionic.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/wechat/css/mystyle.css" rel="stylesheet">
</head>
<body id="interact">
	<ion-content>
	<div>
		<img src="<%=request.getContextPath()%>/wechat/img/bannar2.jpg" width="100%" height="auto">
	</div>
	<div class="card">
		<div class="item item-text-wrap meshow">社区自治，坚持“问需于民、问计于民、问政于民”的原则，以社区党组织为核心，以解决问题为目的，更好地服务社区党员群众。欢迎广大社区党员群众查阅评论社区议题，文明发言。</div>
		<c:if test="${!empty cmslist }">
			<c:forEach items="${cmslist }" var="cms" varStatus="d">
				<div class="item">
					<h2><a href="<%=request.getContextPath()%>/cms_content/${userid }/wechat/getWechatXZSW.do?id=${cms.id}">${cms.title}</a></h2>
					<p><fmt:formatDate value="${cms.addtime}" pattern="yyyy-MM-dd HH:mm"/> </p>
				</div>
			</c:forEach>
		</c:if>
	</div>
	</ion-content>
</body>
<script src="<%=request.getContextPath()%>/wechat/js/ionic.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/wechat/controller/app.js"></script>
</html>