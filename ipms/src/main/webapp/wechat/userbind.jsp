<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" ng-app="mtd">
<head>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>党建社区</title>
	<link href="<%=request.getContextPath()%>/wechat/css/ionic.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/wechat/css/mystyle.css" rel="stylesheet">
	<style>
		.icon{ display: none;}
	</style>
</head>
<body>
	<ion-content>
		<div class="card" style="box-shadow:none;">
			<div class="item item-text-wrap bind_warn text-center">
				党内保密信息需要用户完成认证<br>【请认证绑定】
			</div>
		</div>
		<div class="card">
			<div class="item item-text-wrap user_mention">
					<p>欢迎关注香蜜党建：</p>
					<p>未匹配认证信息。如果您是香蜜湖街道所辖党员，请联系您所在社区党委，或街道组织部门（联系电话：0755-83702233）。 </p>
				</div>
		</div>
		<div class="list list-inset">
			<form method="post" action="" name="myform" id="myform">
				<input id="wechat"  value="${wechat }" type="hidden"/>
				<label class="item item-input" >
					<span class="input-label">姓名</span>
					<input type="text" id='username' name="username">	
					<i class="icon assertive ion-close-round"></i>
			  	</label>
			  	<label class="item item-input">
			  		<span class="input-label">身份证号码</span>
					<input type="text" id='identify' name="identify" >
					<i class="icon assertive ion-close-round"></i>
			  	</label>		  	
			  	<label class="item item-input">
			  		<span class="input-label">手机</span>
					<input type="text" id='telphone' name="telphone" >
					<i class="icon assertive ion-close-round"></i>
			  	</label>
	        </form>
		</div>
		<div style="padding:0 10px">	
			<button	id="register" type="button" class="button button-block button-balanced" >注册</button>
	    </div>
	</ion-content>
</body>
<script src="<%=request.getContextPath()%>/wechat/js/jquery-1.9.1.min.js"></script>
<script src="<%=request.getContextPath()%>/wechat/js/ionic.bundle.js"></script>
<script src="<%=request.getContextPath()%>/wechat/controller/app.js"></script>
<script>
	$(function(){
		var name 	= $('#username'),
			idCard  = $('#identify'),
			tel		= $('#telphone');
		
		function checkName(){
			if( name.val() == '' || !name.val().match(/[\u4e00-\u9fa5]/g) || name.val().length <2 ){
				name.next().show();
			}else{
				return true;
			}
		}
		
		function checkId(){
			if( idCard.val() == '' || !idCard.val().match(/(\d{15})|(\d{18})|(\d{17}(\d|X|x))/g)){
				idCard.next().show();		
			}else{
				return true;
			}
		}
		
		function checkTel(){
			if( tel.val() == '' || !tel.val().match(/1[3458][0-9]{9}/g)){
				tel.next().show();
			}else{
				return true;
			}
		}
		
		$('#myform').find('input').on('focus',function(){
			$('.icon').hide();
		})
		
		$('#register').on('click',function(){
			if( checkName() && checkId() && checkTel() ){
				$.post('<%=request.getContextPath()%>/user/wechat/bindUserinfo.do', 
				{
					'username': name.val(),
					'identify': idCard.val(),
					'telphone': tel.val(),
					'wechat': $('#wechat').val(),
				}, 
				function(data) {
					if (data=='success') {
						window.location.href='<%=request.getContextPath()%>/user/wechat/getUserinfo.do?wechat='+$('#wechat').val();
						alert('个人信息绑定成功，点击跳转');
					}else{
						alert('个人信息填写有误，请核实');
					};
				},'text');
			}
		})
	})
</script>
</html>