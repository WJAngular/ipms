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
				<%-- <div class="item item-divider">单选测试</div>
				<label class="item item-radio"><input type="radio" name="a" value="A">
					<div class="radio-content">
						<div class="item-content"><span>A、喜欢</span></div>
						<i class="radio-icon icon ion-checkmark"></i>
					</div>
				</label>
				<label class="item item-radio"><input type="radio" name="a" value="B">
					<div class="radio-content">
						<div class="item-content"><span>B、一般</span></div>
						<i class="radio-icon icon ion-checkmark"></i>
					</div>
				</label>
				<label class="item item-radio"><input type="radio" name="a" value="C">
					<div class="radio-content">
						<div class="item-content"><span>C、不喜欢</span></div>
						<i class="radio-icon icon ion-checkmark"></i>
					</div>
				</label> --%>

				<%-- 1 --%>
				<div class="item item-divider">多选测试111</div>
				<li class="item item-checkbox">
					<label class="checkbox"><input name="b" value="A" data-group="m1" type="checkbox"></label>第一题111
				</li>
				<li class="item item-checkbox">
					<label class="checkbox"><input name="b" value="B" data-group="m1" type="checkbox"></label>第二题11
				</li>
				<li class="item item-checkbox">
					<label class="checkbox"><input name="b" value="C" data-group="m1" type="checkbox"></label>第三题1
				</li>

				<%-- 2 --%>
				<div class="item item-divider" id="m2">多选测试222</div>
				<li class="item item-checkbox">
					<label class="checkbox"><input name="c" value="A" data-group="m2" type="checkbox"></label>第一题2
				</li>
				<li class="item item-checkbox">
					<label class="checkbox"><input name="c" value="B" data-group="m2" type="checkbox"></label>第二题22
				</li>
				<li class="item item-checkbox">
					<label class="checkbox"><input name="c" value="C" data-group="m2" type="checkbox"></label>第三题222
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
		var result = {};

		$('#vote').click(function(){
			var surResult = $('#vote_survey').serializeArray();

			$.each(surResult,function(){
				if(result[this.name]){	 // 判断json中是否已有键名
					result[this.name] += this.value || '';
				}else{
					result[this.name] = this.value || '';
				}
			});

			console.log(result);		// 结果格式是 {b: "BC", c: "C"}

			$.post("<%=request.getContextPath()%>/cms_suvery_choice/wechat/add.do",{answer:surResult,surveyid:'${survey.id}',weixin:'${weixin}'},function(data){
				if ("success" == data.result) {
					alert("提交成功");
					location.href = '<%=request.getContextPath()%>/cms_survey/wechat/getWechatChoiceDetail.do?id=${survey.id}';
				} else {
					alert("提交失败");
				}
			});

			$(this).attr('disabled', 'disabled');
		});

		var testArr = [2,1];
		$('input[type="checkbox"]').on('click',function(){
			var self = $(this);
			for (var i = 1; i < 3; i++) {
				if( $('input[data-group="m'+i+'"]:checked').length > testArr[i-1] ){
					self.attr('checked',false);
				}
			}
		})

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
	})(jQuery)
</script>
</html>
