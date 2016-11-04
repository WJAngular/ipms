<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<li>账号管理</li>
			<li class="uk-active">修改账号</li>
		</ul>
		<hr>
		<form class="uk-form uk-form-horizontal myform" name="myform" method="post">
		<input type="hidden" name="id" value="${user.id}"/>
			<div class="uk-grid">
				<div class="uk-width-1-2">
					<div class="uk-form-row">
						<label class="uk-form-label" for="">姓名</label>
						<div class="uk-form-controls">
							<input type="text" size="25" name="name" value="${user.name }" >
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label" for="">账号</label>
						<div class="uk-form-controls">
							<input type="text" size="25" name="username" value="${user.username }">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label" for="">身份证号</label>
						<div class="uk-form-controls">
							<input type="text" size="25" name="idCard" id="idCard" value="${user.idCard }">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">电子邮箱</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="email" value= "${user.email}">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">短信条数</label>
						<div class="uk-form-controls">
							<input type="text" size="10" name="smsCount" value= "${user.smsCount}">
						</div>
					</div>
				</div>
				<!-- 右边 -->
				<div class="uk-width-1-2">
				<div class="uk-form-row">
						<label class="uk-form-label" for="">联系电话</label>
						<div class="uk-form-controls">
							<input type="text" size="25" name="tel" value="${user.tel }">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label" for="">密码</label>
						<div class="uk-form-controls">
							<input type="password" size="25" name="password" id="password" value="${user.password }">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">所在党委<span style="color:red"> *</span></label>
						<div class="uk-form-controls">
							<select name="organization" id="organization" onchange="proChange(this.value)">
							<option value="">请选择党委</option>
							</select>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">所在党支部<span style="color:red"> *</span></label>
						<div class="uk-form-controls">
							<select name="branch" id="branch">
								<option value="">请选择支部</option>
							</select>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">有效？</label>
						<div class="uk-form-controls">
							<select name="formal" id="formal">
								<option value="Y" ${user.isvaild=="Y"?"seleted":""}>是</option>
								<option value="N" ${user.isvaild=="N"?"seleted":""}>否</option>
							</select>
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
<script src="<%=request.getContextPath()%>/js/IdCard.js"></script>
<script>
	$.ajax({
		type : "post",
		url : "<%=request.getContextPath()%>/sysrole/getRole.do",
		dataType : "json",
		success : function(data) {
			$.each(data, function(key, value) {
				$('#type').append($("<option/>", {
					value : key,
					text : value
				}));
			});
			$("#type>option[value=${user.type}]").attr("selected",true);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
	
	$.ajax({
		type : "post",
		url : "<%=request.getContextPath()%>/sysdictionary/getOrgId.do",
		dataType : "json",
		success : function(data) {
			$.each(data, function(i,data) {
				$('#organization').append($("<option/>", {
					value : data,
					text : data
				}));
			});
			$("#organization>option[value=${user.organization}]").attr("selected",true);
			proChange('${user.organization}') ;
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
	
	function proChange(objVal) {
		document.getElementById("branch").innerHTML = "";
		$.ajax({
			type : "post",
			url : "<%=request.getContextPath()%>/sysdictionary/getBranch.do?org="
					+ encodeURI(encodeURI(objVal)),
			dataType : "json",
			success : function(data) {
				$.each(data, function(i,data) {
				 $('#branch').append($("<option/>", {
					value : data,
					text : data
				}));
			  });
				$("#branch>option[value=${user.branch}]").attr("selected",true);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	}
	$(function(){
		$('.myform').validate({
			errorClass:'error',
			success:'valid',
			rules: {
				name:{
					required:true,
					chiness:true
				},
				idCard:{ required:true,	idcard:true },
				password:{
					required:true,
					minlength:6
				},
				tel:{
					required:true,
					telephone:true
				},
				organization:{required:true},
				branch:{ required:true },
				smsCount:{ required:true,number:true  }
			},
			submitHandler:function(){
				document.myform.action = "<%=request.getContextPath()%>/user/updateAdminUser.do";
				document.myform.submit();
			}
		});
	})
</script>
</html>