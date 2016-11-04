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
			<li>菜单管理</li>
			<li class="uk-active">修改菜单</li>
		</ul>
		<hr>
		<form class="uk-form uk-form-horizontal myform" id="myform" name="myform" method="post">
			<input type="hidden" name="id" value="${menu.id }">
			<input type="hidden" name="type" value="F"/>
			<div class="uk-grid">
				<div class="uk-width-1-2">
					<div class="uk-form-row">
						<label class="uk-form-label">菜单名称</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="name" value="${menu.name}">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">访问路径</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="url" value="${menu.url}">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">上级菜单</label>
						<div class="uk-form-controls">
							<input name="pid" size="30" id="pid" value="${menu.pid}" readonly="readonly">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">排序</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="sort" value="${menu.sort}">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">描述</label>
						<div class="uk-form-controls">
							<input type="text" size="60" name="description" value="${menu.description}">
						</div>
					</div>
				</div>
				<div class="uk-width-1-1 uk-margin-top">
					<button type="submit" class="uk-button uk-button-success uk-width-1-1">确认</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script src="<%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/uikit.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/validation/jquery.validate.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/validation/messages_zh.js"></script>
<script src="<%=request.getContextPath()%>/frame/validation/additional-methods.js"></script>
<script>
	$(function(){
		$('.myform').validate({
			errorClass:'error',
			success:'valid',
			rules: {
				name:{ required:true },
				url:{ required:true },
				pid:{ required:true },
				sort:{ required:true,digits:true }
			},
			submitHandler:function(){
				var d = jQuery("#myform").serialize();
				$.post("<%=request.getContextPath()%>/permission/updatePerm.do", d, function(data) {
					if (0 == data.code) {
						alert("修改权限成功");
						window.location.href = "<%=request.getContextPath()%>/permission/menuList.do";
					} else {
						alert("修改权限失败:"+data.msg);
					}
				});
			}
		});
	})
</script>
</html>