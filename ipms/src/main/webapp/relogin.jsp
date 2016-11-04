<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" class="uk-height-1-1">
<head>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="UTF-8">
	<title>党建后台管理系统</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/uikit.gradient.min.css">
	<style>
		body{ 
			font:14px/18px "微软雅黑";
			background: rgba(0,0,0,.5);
		}
		.relogin{
			padding: 30px;
			box-shadow:3px 3px 8px rgb(50,50,50); 
			background: #ffffff;
		}
		.relogin legend{ font-weight: bold; color:#555; padding-bottom: 25px;}
		.relogin .uk-form-help-block{ padding-left:10px;}
	</style>
</head>
<body class="uk-height-1-1 uk-flex uk-flex-center uk-flex-middle">
	<form action="" class="uk-form relogin">
		<fieldset data-uk-margin>
			<legend><i class="uk-icon-retweet"></i> 登录超时，请重新登陆</legend>
			<div class="uk-grid">
				<div class="uk-width-2-5">
					<div class="uk-form-icon">
						<i class="uk-icon-user"></i>
						<input type="text" placeholder="用户名">
					</div>
					<c:if test="${ cms}">
						<p class="uk-form-help-block uk-text-danger">
							<i class="uk-icon-user-times"></i> 用户名不存在
						</p>
					</c:if>>
				</div>
				<div class="uk-width-2-5">
					<div class="uk-form-icon">
						<i class="uk-icon-shield"></i>
						<input type="password" placeholder="密码">
					</div>
					<c:if test="${ cms}">
						<p class="uk-form-help-block uk-text-danger">
							<i class="uk-icon-lock"></i> 密码错误
						</p>
					</c:if>
				</div>
				<div class="uk-width-1-5">
					<button class="uk-button uk-width-1-1 uk-button-success">登录</button>
				</div>
			</div>	
		</fieldset>
	</form>
</body>
</html>