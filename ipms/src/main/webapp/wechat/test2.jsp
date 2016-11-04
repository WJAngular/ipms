<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPEhtml>
<html lang="zh-cmn-Hans" ng-app="mtd">
<head>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>在线调查 </title>
	<link href="<%=request.getContextPath()%>/wechat/css/ionic.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/wechat/css/mystyle.css" rel="stylesheet">
	<style>
		html,body{ min-height:100%;}
		#mask{
			width:100%;
			height:100%;
			position:absolute;
			z-index:999;
			display:-webkit-flex;
			display:-ms-flexbox;
			display:flex;
			-webkit-justify-content:center;
			-ms-flex-pack:center;
			justify-content:center;
			-webkit-align-items:center;
			-ms-flex-align:center;
			align-items:center;
			background:rgba(0,0,0,.5);
		}
		.item,.item-content{
			white-space: normal!important;
		}
		.item-checkbox{
			font-size: 14px;
		}
		.confirmbox{
			width: 85%;
			padding:15px;
			border-radius: 5px;
			background:#EFEFEF;
		}
		hr{ 
			margin:15px 0;
			border:none;
			height:1px;
			background:#ddd;
		}
		.confirmmes{
			font-size:16px;
			line-height: 32px;
			color:#333;
			text-align: center;
		}
		.iconfont{
			font-size:70px;
			text-align: center;
		}
	</style>
</head>
<body ng-cloak>
	<ion-content> 
		<form id="vote_survey">
			<div class="list">
				<div class="item item-divider">多选测试</div>
				<li class="item item-checkbox">
					<input id="check1" value="2" type="hidden">
					<label class="checkbox"><input name="a1" value="A" type="checkbox" data-group="a1"></label>第一题第一题第一题第一题第一题第一题第一题第一题第一题第一题第一题第一题第一题第一题
				</li>
				<li class="item item-checkbox">
					<label class="checkbox"><input name="a1" value="B" type="checkbox" data-group="a1"></label>第二题
				</li>
				<li class="item item-checkbox">
					<label class="checkbox"><input name="a1" value="C" type="checkbox" data-group="a1"></label>第三题
				</li>
				<div class="item item-divider">多选测试1</div>
				<li class="item item-checkbox">
				<input id="check2" value="1" type="hidden">
					<label class="checkbox"><input name="a2" value="A" type="checkbox" data-group="a2"></label>
					第一题
				</li>
				<li class="item item-checkbox">
					<label class="checkbox"><input name="a2" value="B" type="checkbox" data-group="a2"></label>第二题
				</li>
				<li class="item item-checkbox">
					<label class="checkbox"><input name="a2" value="C" type="checkbox" data-group="a2"></label>第三题
				</li>
			</div>
			<div class="padding">
				<button id="vote" type="button" class="button button-block button-positive">确定</button>
			</div>
		</form>
	</ion-content>
</body>
<script src="<%=request.getContextPath()%>/wechat/js/jquery-1.9.1.min.js"></script>
<script src="<%=request.getContextPath()%>/wechat/js/ionic.bundle.js"></script>
<script src="<%=request.getContextPath()%>/wechat/controller/app.js"></script>
<script>
	(function($){
		'use strict';
		$('#vote').click(function(){
			var surResult = $('#vote_survey').serializeObject();
			alert(JSON.stringify(surResult));
		});
		
		/**
		 * 将表单对象重组
		 */
		$.fn.serializeObject = function(){
			var o = {};
			var a = this.serializeArray();
			$.each(a,function(){							// 遍历表单的序列化对象
				if ( o[this.name] ) {					// 判断新建对象里面是否已有键,有该键插入对应的值
					if ( !o[this.name].push ) {		// 判断对象的值是不是数组,不是的话转为数组,并插入初始的值
						o[this.name] = [o[this.name]];	
					}
					o[this.name].push(this.value || '');	// 转化为数组后插入新的值
				}else{
					o[this.name] = this.value || '';		// 判断新建对象里面是否已有键,没有便新建该键
				}
			});
			return o;
		};

		 $('input[type=checkbox]').click(function() {
        	for(var i=1;i<=15;i++){
            $("input[data-group='a"+i+"']").attr('disabled', true);
            if ($("input[data-group='a"+i+"']:checked").length >= $("#check"+i+"").val()) {
                $("input[data-group='a"+i+"']:checked").attr('disabled', false);
            } else {
                $("input[data-group='a"+i+"']").attr('disabled', false);
            }
        }
        });
	})(jQuery)
</script>
</html>