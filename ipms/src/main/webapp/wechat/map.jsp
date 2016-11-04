<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>地理位置</title>
<style type="text/css">
html, body {
	height: 100%;
	padding: 0;
	margin: 0;
}

#container {
	width: 100%;
	height: 100%;
}

p {
	padding: 0;
	margin: 0;
}

.outside {
	position: relative;
}

.info {
	padding: 8px 12px;
	font: 16px "微软雅黑";
	color: #fff;
	border-radius: 4px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	background: rgba(0, 0, 0, .5);
}

.info p {
	font-size: 12px;
	margin-top: 5px;
}

.triangle-down {
	position: absolute;
	bottom: 0;
	left: 50%;
	transform: translate(-50%, 100%);
	width: 0;
	height: 0;
	border-left: 5px solid transparent;
	border-right: 5px solid transparent;
	border-top: 10px solid rgba(0, 0, 0, .5);
}
</style>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=fa8cdb942e92657be3aaacbcb36f106d"></script>
</head>
<body>
	<div id="container"></div>
</body>
<script type="text/javascript">
	// map.setZoom(10);					// 设置缩放的方法
	// map.setCenter([116.39,39.9]);	// 设置中心点的方法
	// normal（默认样式）、dark（深色样式）、light（浅色样式）、fresh(osm样式)
	// 地图
	var map = new AMap.Map('container', {
		zoom : 16,
		center : [ 114.015309, 22.534885 ],
		mapStyle : 'normal'
	});

	// 标记
	var marker = new AMap.Marker({
		position : [ 114.015309, 22.534885 ],
	});
	// var circle = new AMap.Circle({
	//        center: [114.015309, 22.534885],
	//        radius: 250,
	//        fillOpacity:0.2,
	//        fillColor:'#09f',
	//        strokeOpacity:0
	//    })
	marker.setMap(map);

	// circle.setMap(map);

	var info = '<div class="outside"><div class="info">深圳市香蜜湖街道办<p>广东省深圳市福田区香蜜园街道竹子林农林路1号</p></div><div class="triangle-down"></div></div>';
	var infoWindow = new AMap.InfoWindow({
		isCustom : true, //使用自定义窗体
		content : info,
		offset : new AMap.Pixel(1, -50)
	});
	marker.on('click', function() {
		infoWindow.open(map, [ 114.015309, 22.534885 ]);
	});

	map.on('click', function() {
		infoWindow.close();
	})
</script>
</html>