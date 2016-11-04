<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" ng-app="mtd">
<head>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>党员签到</title>
	<link href="<%=request.getContextPath()%>/wechat/css/ionic.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/wechat/css/mystyle.css" rel="stylesheet">
	<style>
		html, body { 
			font-family:"微软雅黑";
			width: 100%;
			height: 100%;
			margin:0;
			padding: 0;
			overflow: hidden;
			background: #fff;
		}
		.row { -webkit-flex-direction: column; -ms-flex-direction: column; flex-direction: column;	padding:15px 10px; }
		.getlike, .readnum { display: inline-block; }
		.readnum { color: #888; }
		.getlike { margin-left: 30px; color: #F06161; text-decoration: none; }
		#allmap{ width: 100%; height: 40%; }
		#main{ padding:0 15px;}
		.sign_success{ margin-top: 12px; text-align: center; padding:10px 0; border-bottom: 1px solid #ddd;}
		.sign_success i { font-size: 48px; color:#FF5A5A; }
		.sign_success h4{ font-size: 16px; color:#333333; }
		.s_info{ margin-top: 12px;}
		.s_address,.s_time{ margin: 5px;}
		.s_address strong,.s_time strong{ display: block; font-size: 16px; line-height: 24px;}
	</style>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=dAnjEsTeiNYmHz9yBUWGKF3a"></script>
</head>
<body>
	<div id="allmap"></div>
	<div id="main">
		<div class="sign_success">
			<i class="icon ion-flag"></i>
			<h4>您已成功签到！</h4>
		</div>
		<div class="s_info">
			<div class="s_address">
				<strong>签到地点</strong>
				${sign.sign_address }
			</div>
			<div class="s_time">
				<strong>签到时间</strong>
				<span id="date">${sign.sign_date }</span> <span id="time">${sign.sign_time }</span>
			</div>
		</div>
	</div>
</body>
<script src="<%=request.getContextPath()%>/wechat/js/jquery-1.9.1.min.js"></script>
<script src="<%=request.getContextPath()%>/wechat/js/ionic.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/wechat/controller/app.js"></script>
<script>
	$(function(){
		// 百度地图API功能
		var map = new BMap.Map("allmap");    // 创建Map实例
		var p=new BMap.Point(${sign.sign_longtitude}, ${sign.sign_latitude});
		map.centerAndZoom(p, 18); 
		var marker = new BMap.Marker(p);
		map.addOverlay(marker);
		var label = new BMap.Label("签到点",{offset:new BMap.Size(20,-10)}); 
		marker.setLabel(label); //添加百度label	 
		wx.config({
			debug : false,
			appId : '${appid}', // 必填
			timestamp : '${signature.timestamp}', // 必填
			nonceStr : '${signature.noncestr}', // 必填
			signature : '${signature.signature}',// 必填
			jsApiList : ['openLocation','getLocation']
		});

		wx.error(function(res) {
		});

		wx.ready(function() {
			
		});
		
	});
</script>
</html>