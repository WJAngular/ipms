<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="check.jsp"></jsp:include>
<%-- <%@ page import="com.mtd.entity.*" %>
<%
  	Users user = (Users)request.getSession().getAttribute("sysuser");
  	if(user==null)
  	{
  	  out.print("<script>alert('登录超时，请重新登录！');top.window.location.href='login.jsp';</script>");
	  out.close();
  	  return;
  	}
%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" class="uk-height-1-1">
<head>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="UTF-8">
	<title>党建后台管理系统</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/uikit.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">
	<style>
		#mask{
			position: fixed;
			top: 0;
			left: 0;
			width: 100%;
			height: 100%;
			display: none;
			background: rgba(0,0,0,.5);
		}
		#mask .settingbox{
			position:absolute;
			top:25%;
			left:50%;
			width:280px;
			margin-left:-140px;
			border:1px solid #EEE;
			border-radius: 8px;
			padding:25px;
			background:#FAFAFA;
		}
		.mylabel{ display: inline-block; font-weight: bold; line-height: 30px; width: 35%;}
		.myinput{ display: inline-block; width: 65%; }
		.modalbtn{ width:120px;}
		#admin_upd label.error{ padding-left:35%;}
	</style>
</head>
<body class="uk-height-1-1" style="overflow:hidden;">
	<!-- topnav include -->
	<header>
		<nav class="uk-navbar uk-width-1-1">
			<a class="uk-navbar-brand" href="main.jsp">
				<img src="img/admin-logo.png">
			</a>
			<div class="uk-navbar-content uk-navbar-flip uk-hidden-small">
				<div class="uk-button-group">
					<button class="uk-button" id="setting">
						<i class="uk-icon-male"></i> 个人设置
					</button>
					<a class="uk-button exit" href="<%=request.getContextPath()%>/sysusers/logout.do"><i class="uk-icon-sign-out"></i> 退出</a>
				</div>
			</div>
			<!-- 2015年11月10日09:48:23 -->
			<div class="uk-navbar-content uk-navbar-flip uk-hidden-small">
				<a class="uk-button uk-button-danger mybtn" href="javascript:window.parent.location.reload();"> 首页</a>
				<a class="uk-button uk-button-danger mybtn" href="javascript:history.go(-1)"> 后退</a>
				<a class="uk-button uk-button-danger mybtn" href="javascript:history.go(1)"> 前进</a>
				<a class="uk-button uk-button-danger mybtn" href="javascript:window.parent.location.reload();"> 刷新</a>
			</div>
		</nav>
	</header>
	<!-- body  -->
	<div class="uk-grid uk-grid-small mainbox">
		<!-- leftnav -->
		<div class="uk-height-1-1" style="width:13%">
			<div class="uk-panel uk-panel-box uk-height-1-1 left_nav">
				<h3 class="uk-panel-title">个人工作台</h3>
				<ul class="uk-nav uk-nav-side uk-nav-parent-icon" data-uk-nav>	
					<li class="uk-nav-divider"></li>
					<shiro:hasAnyRoles name="admin,other">
					<li class="uk-parent"><a href="#"><i class="uk-icon-edit uk-icon-justify"></i>　内容管理</a>
						<ul class="uk-nav-sub">
							<li><a href="<%=request.getContextPath()%>/cms_content/getAllList.do" target="datarea">内容发布</a></li>
							<li><a href="<%=request.getContextPath()%>/media/AllNewsList/1/get.do" target="datarea">图文管理</a></li>
							<li><a href="<%=request.getContextPath()%>/auto_reply/get/list.do" target="datarea">自动回复设置</a></li>
							<li><a href="<%=request.getContextPath()%>/cms_topic/getAllList.do" target="datarea">话题管理</a></li>
							<li><a href="<%=request.getContextPath()%>/cms_survey/getAllList.do" target="datarea">社区调查</a></li>
							<li><a href="<%=request.getContextPath()%>/cms_link/getAllList.do" target="datarea">相关链接</a></li>
						</ul>
					</li>
					</shiro:hasAnyRoles>
					<shiro:hasAnyRoles name="admin">
					<li class="uk-parent"><a href="<%=request.getContextPath()%>/sms/getAllList.do" target="datarea"><i class="uk-icon-envelope uk-icon-justify"></i>　短信平台</a></li>
					<li class="uk-parent"><a href="#"><i class="uk-icon-gears uk-icon-justify"></i>　系统管理</a>
						<ul class="uk-nav-sub">
							<li><a href="<%=request.getContextPath()%>/sysusers/getAllList.do" target="datarea">后台用户管理</a></li>
							<li><a href="<%=request.getContextPath()%>/sysdictionary/getOrganizationList.do" target="datarea">组织管理</a></li>
							<li><a href="<%=request.getContextPath()%>/role/getList.do" target="datarea">角色管理</a></li>
							<%-- <li><a href="<%=request.getContextPath()%>/permission/menuList.do" target="datarea">菜单和权限管理</a></li> --%>
							<li><a href="<%=request.getContextPath()%>/sysdictionary/getAllList.do" target="datarea">数字字典</a></li>
							<li><a href="<%=request.getContextPath()%>/syslog/getAllList.do" target="datarea">日志管理</a></li>
				<%-- 			<li><a href="<%=request.getContextPath()%>/message/actiUser.do" target="datarea">客服测试</a></li>
							<li><a href="<%=request.getContextPath()%>/message/branch.do" target="datarea">推送测试</a></li> --%>
						</ul>
					</li>
					</shiro:hasAnyRoles>
					<li class="uk-nav-header">其他相关</li>
					<li><a href="#"><i class="uk-icon-question-circle uk-icon-justify"></i>　帮助文档</a></li>
				</ul>
			</div>
		</div>
		<!-- datarea -->
		<div class="uk-height-1-1" style="width:87%">
			<iframe name="datarea" src="<%=request.getContextPath()%>/index.jsp" class="uk-width-1-1 uk-height-1-1" style="padding-right:10px"></iframe>
		</div>
	</div>
	<!-- foot : no setting -->
	<!-- 个人设置 -->
	<div id="mask" class="uk-animation-slide-top">
		<div class="settingbox">
			<h4>个人信息修改</h4>
			<hr>
			<form class="uk-form" id="admin_upd">
				<div class="uk-clearfix uk-margin-top uk-margin-bottom">
					<label class="mylabel uk-float-left">账号信息</label>
					<input class="myinput uk-float-right" type="text"  value="${sysuser.username }" disabled>
				</div>
				<div class="uk-clearfix uk-margin-top uk-margin-bottom">
					<label class="mylabel uk-float-left">姓名</label>
					<input class="myinput uk-float-right" type="text" value="${sysuser.name}" disabled>
				</div>
				<div class="uk-clearfix uk-margin-top uk-margin-bottom">
					<label class="mylabel uk-float-left">密码</label>
					<input class="myinput uk-float-right" type="password" name="password" id="password" >
				</div>
				<div class="uk-button-group uk-width-1-1 uk-text-center">
					<button type="button" id="cancel" class="modalbtn uk-button">取消</button>
					<button type="submit" id="confir" class="modalbtn uk-button uk-button-primary">确认</button>
				</div>
			</form>
		</div>
	</div>
</body>
<script src="<%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/uikit.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/validation/jquery.validate.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/validation/messages_zh.js"></script>
<script src="<%=request.getContextPath()%>/frame/validation/additional-methods.js"></script>
<script>
	$(function(){

		$('#setting').on('click', function(event){
			$('#mask').show();
		});

		$('#cancel').on('click',function(){
			$('#mask').hide();
		})

		// 密码验证
		$('#admin_upd').validate({
			errorClass:'error',
			success:'valid',
			rules: {
				password:{
					required:true,
					minlength:6
				}
			},
			submitHandler:function(){
				$.post('<%=request.getContextPath()%>/user/updatePass.do', 
				{
			        'id': '${sysuser.id}',
					'password': $('#password').val(),
				}, 
				function(data) {
					if (data=='success') {
						alert('修改成功');
						window.location.reload();
					}else{
						alert('修改失败');
					};
				},'text');
			}
		});
	})
</script>
</html>
