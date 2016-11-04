<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" class="uk-height-1-1">
<head>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="UTF-8">
	<title>社区党建后台管理系统</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/uikit.almost-flat.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">
</head>
<body class="uk-height-1-1">
	<div class="uk-panel uk-panel-box">
		<ul class="uk-breadcrumb">
			<li><i class="uk-icon-home"></i> <a href="<%=request.getContextPath()%>/index.jsp">首页</a></li>
			<li>系统管理</li>
			<li>权限管理</li>
			<li class="uk-active">修改权限</li>
		</ul>
		<hr>
		<form class="uk-form uk-form-horizontal myform" name="myform" method="post">
			<input type="hidden" name="id" value="${perm.id }">
			<input type="hidden" name="type" value="${perm.type }">
			<div class="uk-grid">
				<div class="uk-width-1-2">
					<div class="uk-form-row">
						<label class="uk-form-label" for="">权限名称</label>
						<div class="uk-form-controls">
							<input type="text" name="name" value="${perm.name}">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label" for="">权限编码</label>
						<div class="uk-form-controls">
							<input type="text" name="permCode" value="${perm.permCode}">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label" for="">访问路径</label>
						<div class="uk-form-controls">
							<input type="text" name="url" value="${perm.url}">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label" for="">上级菜单</label>
						<div class="uk-form-controls">
							<input type="text" name="pid" value="${perm.pid}">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label" for="">描述</label>
						<div class="uk-form-controls">
							<input type="text" name="description" value="${perm.description}">
						</div>
					</div>
				</div>
				<div class="uk-width-1-1 uk-margin-top">
					<button class="uk-button uk-button-success uk-width-1-1"
					onclick="updUser()">确认</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script src="<%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/uikit.min.js"></script>
<script>
	function updUser() {
		document.myform.action = "<%=request.getContextPath()%>/permission/updatePerm.do";
		document.myform.submit();
	}
</script>
</html>