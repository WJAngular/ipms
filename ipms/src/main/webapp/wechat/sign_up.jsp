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
		#main{ padding:0 10px;}
		.card{ font-size: 14px; margin-top:35px;}
		.card i { display: inline-block; width: 20px; text-align: center; font-size: 20px; line-height: 20px; }
	</style>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=dAnjEsTeiNYmHz9yBUWGKF3a"></script>
</head>
<body>
	<div id="allmap"></div>
	<div class="card">
		<div class="item item-divider">
			<i class="icon ion-ios-location balanced"></i> 当前位置
		</div>
		<div class="item item-text-wrap">
			<span id="result"></span>
		</div>
		<div class="item item-divider">
			<i class="icon ion-ios-clock assertive"></i> 当前时间
		</div>
		<div class="item item-text-wrap">
			<span id="date"></span> <span id="time"></span>
		</div>
	</div>
	<div id="main">
		<input type="hidden" id="gps"/>
		<input type="hidden" id="user" value="${user}"/>
		<button type="button" class="button button-block button-positive" id="sign_btn">签到</button>
	</div>
</body>
<script src="<%=request.getContextPath()%>/wechat/js/jquery-1.9.1.min.js"></script>
<script src="<%=request.getContextPath()%>/wechat/js/ionic.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/wechat/controller/app.js"></script>
<script>
	$(function(){
		// 百度地图API功能
		var map = new BMap.Map("allmap");    // 创建Map实例
		map.centerAndZoom("惠州", 18);  // 初始化地图,设置中心点坐标和地图级别

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
			wx.hideOptionMenu();
			wx.getLocation({
    			type: 'wgs84', 
    			success: function (res) {
        			var latitude0 = res.latitude; // 纬度，浮点数，范围为90 ~ -90
        			var longitude0 = res.longitude; // 经度，浮点数，范围为180 ~ -180。
        			var speed = res.speed; // 速度，以米/每秒计
        			var accuracy = res.accuracy; // 位置精度
        			showPoint(latitude0,longitude0);
    			}
			});
		});

		$('#sign_btn').click(function(){
			
			var user=$("#user").val();
		
			var gps=$("#gps").val();
	
			var date=$("#date").html();
			var time=$("#time").html();
			var address=$("#result").html();
			$.post('/Mtd/wechat/sign/sign_up.do', 
				{'user': user,'gps':gps,'date':date,'time':time,'address':address}, 
				function(data) {
					if(data=='ok'){
						window.location="/Mtd/wechat/sign.do?user="+user;
						//wx.closeWindow();
				}	
			});
		});
		
		function showPoint(la,lo){
			var gg=new BMap.Point(lo,la);
  			var convertor = new BMap.Convertor();
  			var pointArr = [];
        	pointArr.push(gg);	
        	convertor.translate(pointArr, 1, 5, translateCallback);
		}

		translateCallback=function(data){
			if(data.status === 0) {
				var geoc = new BMap.Geocoder();  
				geoc.getLocation(data.points[0], function(rs){
					var addComp = rs.addressComponents;
					var str=addComp.province+addComp.city+addComp.district+addComp.street+addComp.streetNumber;
					$('#result').html(str);
					showTime();
				});
				var marker = new BMap.Marker(data.points[0]);
		        map.addOverlay(marker);
		        var label = new BMap.Label("当前位置",{offset:new BMap.Size(20,-10)}); 
		        marker.setLabel(label); //添加百度label	      
		        map.setCenter(data.points[0]);
		        $('#gps').val(data.points[0].lat+","+data.points[0].lng);
      		}
		}
		
		function showTime(){
			var d = new Date();
			var vYear = d.getFullYear();
			var vMon = d.getMonth() + 1;
			var vDay = d.getDate();
			var h = d.getHours(); 
			var m = d.getMinutes(); 
			var se = d.getSeconds(); 
			var date=vYear+"/"+(vMon<10 ? "0" + vMon : vMon)+"/"+(vDay<10 ? "0"+ vDay : vDay);
			var time=(h<10 ? "0"+ h : h)+":"+(m<10 ? "0" + m : m)+":"+(se<10 ? "0" +se : se);
			$("#date").html(date);
			$("#time").html(time);
		}
	})
</script>
</html>