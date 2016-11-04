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
	<script src="http://map.qq.com/api/js?v=2.exp&key=PCMBZ-S3Z3W-JQ4RU-R4QZ2-W6ZWQ-XHFF5&libraries=drawing,geometry,autocomplete,convertor"></script>
	<style>
		html,body,#container{ padding:0; margin:0; width: 100%; height: 100%; overflow: hidden;}
	</style>
</head>
<body>
	<div id="container"></div>
</body>
<script>
	var center = new qq.maps.LatLng(22.566680,114.063900);
	var map = new qq.maps.Map(document.getElementById("container"),{
		mapTypeControl:false,
		panControl:false,
		scaleControl:false,
		zoomControl:false,
		center: center,
		zoom:18
	});

	var marker = new qq.maps.Marker({
		position: center,
		map: map,
		animation: qq.maps.MarkerAnimation.BOUNCE
	});

    function CustomOverlay(position, index) {
	    this.index = index;
	    this.position = position;
	}

	

	CustomOverlay.prototype = new qq.maps.Overlay();
	//定义construct,实现这个接口来初始化自定义的Dom元素

	CustomOverlay.prototype.construct = function() {
	    var div = this.div = document.createElement("div");
	    var divStyle = this.div.style;
	    divStyle.position = "absolute";
	    divStyle.webkitTransform= "translate(-50%,-100%)";
	    divStyle.transform= "translate(-50%,-150%)" ;
	    divStyle.fontSize = "12px";
	    divStyle.fontFamily = "微软雅黑";
	    divStyle.color = "#ffffff";
	    divStyle.paddingTop = "5px";
	    divStyle.paddingBottom = "5px";
	    divStyle.paddingLeft = "12px";
	    divStyle.paddingRight = "12px";
	    divStyle.backgroundColor = "rgba(0,0,0,.5)";
	    divStyle.border = "1px solid #ddd";
	    divStyle.whiteSpace = "nowrap"
	    divStyle.lineHeight = "24px";
	    divStyle.borderRadius = "4px";
	    this.div.innerHTML = this.index;
	    
	    //将dom添加到覆盖物层
	    var panes = this.getPanes();
	    
	    //设置panes的层级，overlayMouseTarget可接收点击事件
	    panes.overlayMouseTarget.appendChild(div);
	 
	    var self = this;
	    this.div.onclick = function() {
	        // alert(self.index);
	    }
	}

	//实现draw接口来绘制和更新自定义的dom元素
	CustomOverlay.prototype.draw = function() {
	    var overlayProjection = this.getProjection();
	    //返回覆盖物容器的相对像素坐标
	    var pixel = overlayProjection.fromLatLngToDivPixel(this.position);
	    var divStyle = this.div.style;
	    divStyle.left = pixel.x - 12 + "px";
	    divStyle.top = pixel.y - 12 + "px";
	}

	//实现destroy接口来删除自定义的Dom元素，此方法会在setMap(null)后被调用
	CustomOverlay.prototype.destroy = function() {
	    this.div.onclick = null;
	    this.div.parentNode.removeChild(this.div);
	    this.div = null
	}
	
	var content = '梅亭社区工作站<br>深圳市福田区上梅林凯丰路凯丰花园综合楼南319C';
	var overlay = new CustomOverlay(center, content);
	overlay.setMap(map);
</script>
</html>