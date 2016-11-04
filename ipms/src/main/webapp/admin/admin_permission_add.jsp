<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" class="uk-height-1-1">
<head>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="UTF-8">
	<title>党建后台管理系统</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/uikit.almost-flat.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">
</head>
<body class="uk-height-1-1">
	<div class="uk-panel uk-panel-box">
		<ul class="uk-breadcrumb">
			<li><i class="uk-icon-home"></i> <a href="<%=request.getContextPath()%>/index.jsp">首页</a></li>
			<li>系统管理</li>
			<li>权限管理</li>
			<li class="uk-active">新增权限</li>
		</ul>
		<hr>
		<form class="uk-form uk-form-horizontal myform" name="myform" method="post">
			<input type="hidden" name="type" value="O"/>
			<div class="uk-grid">
				<div class="uk-width-1-2">
					<div class="uk-form-row">
						<label class="uk-form-label" for="">权限名称</label>
						<div class="uk-form-controls">
							<input type="text" name="name">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label" for="">权限编码</label>
						<div class="uk-form-controls">
							<input type="text" name="permCode">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label" for="">上级菜单</label>
						<div class="uk-form-controls">
							<input name="pid" id="pid" value="${menu.id}" readonly="readonly">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label" for="">描述</label>
						<div class="uk-form-controls">
							<input type="text" name="description">
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
	$.ajax({
		type : "post",
		url : "<%=request.getContextPath()%>/permission/getData.do",
		dataType : "json",
		success : function(data) {
		$.each(data, function(i,data) {
			$('#pid').append($("<option/>", {
				value : data.id,
				text : data.name
				}));
			});
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
			}
	});

	$(function(){
		$('.myform').validate({
		errorClass:'error',
		success:'valid',
		rules: {
			name:{ required:true },
			permCode:{ required:true },
			description:{ required:true }
		},
		submitHandler:function(){
			document.myform.action = "<%=request.getContextPath()%>/permission/addPerm.do";
			document.myform.submit();
		}
		});
	})
</script>
</html>