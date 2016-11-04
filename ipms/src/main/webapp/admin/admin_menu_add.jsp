<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<li class="uk-active">新增菜单</li>
		</ul>
		<hr>
		<form class="uk-form uk-form-horizontal myform" id="myform" name="myform" method="post">
			<input type="hidden" name="type" value="F"/>
			<div class="uk-grid">
				<div class="uk-width-1-2">
					<div class="uk-form-row">
						<label class="uk-form-label">菜单名称</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="name">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">访问路径</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="url">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">上级菜单</label>
						<div class="uk-form-controls">
							<select name="pid" id="pid">
							</select>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">排序</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="sort">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">描述</label>
						<div class="uk-form-controls">
							<input type="text" size="60" name="description">
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
		type : "get",
		url : "<%=request.getContextPath()%>/permission/menuData.do",
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
				url:{ required:true },
				pid:{ required:true },
				sort:{ required:true,digits:true }
			},
			submitHandler:function(){
				var d = jQuery("#myform").serialize();
				$.post("<%=request.getContextPath()%>/permission/addPerm.do", d, function(data) {
					if (0 == data.code) {
						alert("添加菜单成功");
						window.location.href = "<%=request.getContextPath()%>/permission/menuList.do";
					} else {
						alert("添加菜单失败:"+data.msg);
					}
				});
			}
		});
	})
</script>
</html>