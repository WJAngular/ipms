<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<body ng-cloak ng-controller="PopupCtrl">
	<ion-content>
		<div class="card" style="box-shadow: none;">
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
				<input id="weixin" name="weixin" value="${wechat }" type="hidden" /> 
				<label class="item item-input"> 
				<span class="input-label">姓名</span> 
				<input type="text" id='name' name="name"
					ng-model="name" ng-pattern="/[\u4e00-\u9fa5]/" required> 
					<i class="icon assertive ion-close-round" ng-show="myform.name.$invalid && !myform.name.$pristine"> </i> 
					<i class="icon balanced ion-checkmark-round" ng-show="myform.name.$valid && !myform.name.$pristine"></i>
				</label> 
				<label class="item item-input"> <span class="input-label">身份证号码</span> 
				<input type="text" id="idCard" name="idCard" ng-model="idCard" ng-pattern="/(\d{15})|(\d{18})|(\d{17}(\d|X|x))/"
					ng-minlength="15" ng-maxlength="18" required> 
					<i class="icon assertive ion-close-round" ng-show="myform.idCard.$invalid && !myform.idCard.$pristine"></i> 
					<i class="icon balanced balanced ion-checkmark-round" ng-show="myform.idCard.$valid && !myform.idCard.$pristine"></i>
				</label> 
				<label class="item item-input"> 
				<span class="input-label">手机</span> 
				<input type="text" id="tel" name="tel" ng-model="telphone" ng-minlength="11" ng-maxlength="11" ng-pattern="/1[358][0-9]{9}/" required> 
				<i class="icon assertive ion-close-round" ng-show="myform.tel.$invalid && !myform.tel.$pristine"></i> 
				<i class="icon balanced balanced ion-checkmark-round" ng-show="myform.tel.$valid && !myform.tel.$pristine"></i>
				</label>
				<!--  -->
			</form>
		</div>
		<div style="padding: 0 10px">
			<button ng-click="checkUser()" class="button button-block button-balanced" ng-disabled="myform.$invalid">注册</button>
		</div>
	</ion-content>
</body>
<script src="<%=request.getContextPath()%>/wechat/js/jquery-1.9.1.min.js"></script>
<script src="<%=request.getContextPath()%>/wechat/js/ionic.bundle.js"></script>
<script src="<%=request.getContextPath()%>/wechat/controller/app.js"></script>
<script>
	mtd.controller('PopupCtrl',['$scope','$ionicPopup',function($scope,$ionicPopup){
		$scope.checkUser = function() {
			$.post('<%=request.getContextPath()%>/user/wechat/updateWechatUser.do',
			{
				name:$('#name').val(),
				idCard:$('#idCard').val(),
				tel:$('#tel').val(),
				weixin:$('#weixin').val()
			},
			function(data) {
				if(data.result == 'error'){
					var alertPopup = $ionicPopup.alert({
						title: '提示',
						template: '<div>系统没有该账号存在或者信息有误，请通知管理员录入</div>',
						buttons: [{
							text: '确定',
							type: 'button-assertive'
						}]
					});
				}else{
					window.location.href='<%=request.getContextPath()%>/wechat/course.jsp';
				}
			});
		};
	}]);
</script>
</html>