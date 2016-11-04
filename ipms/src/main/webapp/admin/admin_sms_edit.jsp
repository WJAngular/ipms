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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/easyui/themes/default/easyui.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/easyui/themes/icon.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/easyui/demo/demo.css">
</head>
<body class="uk-height-1-1">
	<div class="uk-panel uk-panel-box">
		<ul class="uk-breadcrumb">
			<li><i class="uk-icon-home"></i> <a href="<%=request.getContextPath()%>/index.jsp">首页</a></li>
			<li>短信平台</li>
			<li class="uk-active">查看短信</li>
		</ul>
		<hr>
		<form class="uk-form uk-form-horizontal myform" name="myform" method="post">
			<div class="uk-grid">
				<div class="uk-width-1-2">
					<div class="uk-form-row">
						<label class="uk-form-label" for="">收信人</label>
						<div class="uk-form-controls">
							<input value="${sms.recevier }"  id="cc" name="recevier" class="easyui-combotree" multiple style="width: 350px;"/>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label" for="">内容</label>
						<div class="uk-form-controls">
							<textarea rows="5" cols="67" name="contents" style="resize: none">${sms.contents } </textarea>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
<script src="<%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/uikit.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/easyui/jquery.easyui.min.js"></script>
<script>
	$(document).ready(function() {
		$('#cc').combotree({
			url : '<%=request.getContextPath()%>/user/getUserJson.do',
			onlyLeafCheck : true
		});
		    var t = $("#cc").combotree('tree');
			var n=true;
			t.tree({
				onClick:function(node){
					if(n){
						t.tree('check',node.target);
						n=false;
					}else{
						t.tree('uncheck',node.target);
						n= true;
					}
					console.log(n);
				}
	       });
	});
</script>
</html>