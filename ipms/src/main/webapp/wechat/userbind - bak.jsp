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
</head>
<body ng-cloak>
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
			<form method="post" action="" name="myform" id="myform" novalidate>
				<input id="wechat"  value="${wechat }" type="hidden"/>
				<label class="item item-input" >
					<span class="input-label">姓名</span>
					<input type="text" id='username' name="username" 
							ng-model="username" 
							ng-pattern="/[\u4e00-\u9fa5]/" 
							required
							>	
					<i class="icon assertive ion-close-round" ng-show="myform.username.$invalid && !myform.username.$pristine" >
					</i>
					<i class="icon balanced ion-checkmark-round" ng-show="myform.username.$valid && !myform.username.$pristine"></i>
			  	</label>
			  	<label class="item item-input">
			  		<span class="input-label">身份证号码</span>
					<input type="text" id='identify' name="identify" 
							ng-model="identify" 
							ng-pattern="/(\d{15})|(\d{18})|(\d{17}(\d|X|x))/" 
							ng-minlength="15" 
							ng-maxlength="18" 
							required 
							>
					<i class="icon assertive ion-close-round" ng-show="myform.identify.$invalid && !myform.identify.$pristine"></i>
					<i class="icon balanced balanced ion-checkmark-round" ng-show="myform.identify.$valid && !myform.identify.$pristine"></i>
			  	</label>		  	
			  	<label class="item item-input">
			  		<span class="input-label">手机</span>
					<input type="text" id='telphone' name="telphone" 
							ng-model="telphone"
							ng-minlength="11" 
							ng-maxlength="11" 
							ng-pattern="/1[358][0-9]{9}/" 
							required 
							>
					<i class="icon assertive ion-close-round" ng-show="myform.telphone.$invalid && !myform.telphone.$pristine"></i>
					<i class="icon balanced balanced ion-checkmark-round" ng-show="myform.telphone.$valid && !myform.telphone.$pristine"></i>
			  	</label>
		        <!--  -->
	        </form>
		</div>
		<div style="padding:0 10px">	
			<button	onclick="test()"
	        		class="button button-block button-balanced" 
	        		ng-disabled="myform.$invalid" >
	        		注册
	        </button>
	    </div>
	</ion-content>
</body>
<script src="<%=request.getContextPath()%>/wechat/js/jquery-1.9.1.min.js"></script>
<script src="<%=request.getContextPath()%>/wechat/js/ionic.bundle.js"></script>
<script src="<%=request.getContextPath()%>/wechat/controller/app.js"></script>
<script type="text/javascript">
	function test()
	{
		$.post('<%=request.getContextPath()%>/user/wechat/bindUserinfo.do', 
				{
					'username': $('#username').val(),
					'identify': $('#identify').val(),
					'telphone': $('#telphone').val(),
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
</script>
</html>