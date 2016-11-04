<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<li>数字管理</li>
			<li class="uk-active">编辑</li>
		</ul>
		<hr>
		<form class="uk-form uk-form-horizontal myform" name="myform" method="post">
			<input type="hidden" name="id" value="${dict.id }">
			<input type="hidden" name="adduser" value="${dict.adduser }">
			<input type="hidden" name="upduser" value="${sysuser.username} ">
			<div class="uk-grid">
				<div class="uk-width-1-2">
					<div class="uk-form-row">
						<label class="uk-form-label">字典名称</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="mcode" value="${dict.mcode }">
						</div>
					</div>

					<div class="uk-form-row">
						<label class="uk-form-label">上级</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="pcode" value="${dict.pcode }">
						</div>
					</div>

					<div class="uk-form-row">
						<label class="uk-form-label">排序</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="orderby" value="${dict.orderby }">
						</div>
					</div>

					<div class="uk-form-row">
						<label class="uk-form-label">备注</label>
						<div class="uk-form-controls">
							<textarea cols="30" name="remark">${dict.remark }</textarea>
						</div>
					</div>
				</div>
				<div class="uk-width-1-2">
					<div class="uk-form-row">
						<label class="uk-form-label">显示名称</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="name" value="${dict.name }">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">显示代码</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="code" value="${dict.code }">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">分组</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="ocode" value="${dict.ocode }">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">是否有效</label>
						<div class="uk-form-controls">
							<select name="isvalid" id="isvaild">
								<c:choose>
									<c:when test="${dict.isvalid=='Y'}">
										<option value="Y">是</option>
										<option value="N">否</option>
									</c:when>
									<c:when test="${dict.isvalid=='N'}">
									    <option value="N">否</option>
										<option value="Y">是</option>
									</c:when>
									<c:otherwise>
									    <option value="Y">是</option>
										<option value="N">否</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
				</div>
				<div class="uk-width-1-1 uk-margin-top">
					<button class="uk-button uk-button-success uk-width-1-1" onclick="adddict()">确认</button>
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
				mcode:{ required:true },
				pcode:{ required:true },
				orderby:{ required:true, digits:true },
				name:{ required:true },
				code:{ required:true },
				ocode:{ required:true }
			},
			submitHandler:function(){
				document.myform.action = "<%=request.getContextPath()%>/sysdictionary/updDict.do";
				document.myform.submit();
			}
		});
	})
</script>
</html>