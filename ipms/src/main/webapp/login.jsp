<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="UTF-8">
<title>登录——党建后台管理系统</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/uikit.almost-flat.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/components/form-password.almost-flat.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/components/notify.almost-flat.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/img/favicon.ico" type="image/x-icon" />
<style>
	body{ padding-top:10%; overflow: hidden;}
	.uk-form{ width: 215px; margin: 0 auto;}
</style>
<!--[if IE 9]>
		<style>
			body{ overflow:hidden;}
			.uk-height-1-1{ display:table; width:100%;}
			.uk-panel{ vertical-align: middle; display: table-cell;}
		</style>
	<![endif]-->
</head>
<body>
	<div class="uk-height-1-1 uk-flex uk-flex-column" id="mainbox">
		<div class="logo uk-container-center"></div>
		<form class="uk-form" action="<%=request.getContextPath()%>/sysusers/sysLogin.do" method="post" autocomplete="on">
			<div class="uk-form-row">
				<div class="uk-form-icon uk-width-1-1">
					<i class="uk-icon-user"></i> <input type="text" id="name" class="uk-width-1-1" name="username" value="${username }" placeholder="输入用户名" autofocus="autofocus" required>
				</div>
			</div>
			<div class="uk-form-row">
				<div class="uk-form-icon uk-width-1-1">
					<i class="uk-icon-lock"></i> <input type="password" class="uk-width-1-1" name="password" value="${password }" placeholder="请输入密码" required>
				</div>
			</div>
			<div class="uk-form-row">
				<input type="text" name="code" size="7" placeholder="验证码" required>
				<img id="imgObj" alt="验证码" src="<%=request.getContextPath()%>/code/getcode.do" onclick="changeImg()"/>
			</div>
			<div class="uk-form-row uk-text-center">
				<button class="uk-button uk-width-1-1 uk-button-primary">登录</button>
			</div>
		</form>
		<input type="hidden" id="nouser" value="">
	</div>
</body>
<script src="<%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/uikit.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/components/notify.min.js"></script>
<script>
	$(function() {
		if ("${message_login}" != "") {
			UIkit.notify("${message_login}", {
				pos : 'bottom-center'
			});
		}
	})
	function changeImg() {
		var imgSrc = $("#imgObj");
		var src = imgSrc.attr("src");
		imgSrc.attr("src", chgUrl(src));
	}
	//时间戳
	//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
	function chgUrl(url) {
		var timestamp = (new Date()).valueOf();
		if (url.indexOf("?timestamp=") > 0) {
			url = url.substring(0, url.indexOf("?timestamp="));
		}
		url = url + "?timestamp=" + timestamp;
		console.log(url);
		return url;
	}
</script>
</html>
