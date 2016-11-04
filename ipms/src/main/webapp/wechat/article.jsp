<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>${news.title}</title>
	<link href="<%=request.getContextPath()%>/wechat/css/normalize.css" rel="stylesheet">
	<style>
		* { -webkit-box-sizing: border-box; -moz-box-sizing: border-box; box-sizing: border-box; }
		html,body,h1,h2,h3,h4,h5,h6,img,table,ul,li{  margin:0; padding:0; }
		html,body { width:100%; min-height:100%; background: #fff; }
		table{ width:100%!important; }
		img{ max-width:100%;}

		.container{ padding:15px 8px;}
		.info{ margin-top:12px;}
		.info_time,.info_resource{display:inline-block; margin-right:15px;}
		.info_time{ color:#aaa;}
		.info_resource a{ text-decoration: none; color:#3498DB;}

		.detail_content{ margin-top:25px;}
		.detail_content *{ max-width: 100%;}

		.getlike, .readnum { float: left; }
		.getlike { margin-left: 30px; color: #F06161; text-decoration: none; }
		.readnum { color: #888; }
		
		.art_foot { margin-top:30px; font-size:16px; overflow: hidden;}
	</style>
	<script src="<%=request.getContextPath()%>/wechat/js/jweixin-1.0.0.js"></script>
	<script>
		wx.config({
			debug : false,
			appId : '${appid}', // 必填
			timestamp : '${sign.timestamp}', // 必填
			nonceStr : '${sign.noncestr}', // 必填
			signature : '${sign.signature}',// 必填
			jsApiList : [ 'onMenuShareTimeline', 'onMenuShareAppMessage',
					'onMenuShareQQ', 'onMenuShareWeibo', 'onMenuShareQZone' ]
		// 必填
		});

		wx.error(function(res) {
		});

		wx.ready(function() {
			/**
			 * 朋友圈分享
			 */
			wx.onMenuShareTimeline({
				title : '${news.title}', // 分享标题
				link : '${sign.url}', // 分享链接
				imgUrl : '${news.picMediaUrl}', // 分享图标
			});

			/**
			 * 好友分享
			 */
			wx.onMenuShareAppMessage({
				title : '${news.title}', // 分享标题
				desc : '${news.abstracts}', // 分享描述
				link : '${sign.url}', // 分享链接
				imgUrl : '${news.picMediaUrl}', // 分享图标
			});
		});
	</script>
</head>
<body>
	<div class="container">
		<h3>${news.title}</h3>
		<div class="info">
			<span class="info_time"><fmt:formatDate value="${news.addtime}" pattern="yyyy-MM-dd"/></span>
			<span class="info_resource"><a href="javascript:void()">${news.author}</a></span>
		</div>
		<div class="detail_content">		
        	${news.contents}
		</div>
		<div class="art_foot">
			<div class="readnum">阅读 ${news.readCount}</div>
			<div class="getlike">
				<c:if test="${like.isLike==null}">
					<i class="icon ion-ios-heart-outline"></i> 
				</c:if>
				<c:if test="${like.isLike!=null}">
					<i class="icon ion-ios-heart"></i> 
				</c:if>
				喜欢 <span id="likecount">${like.likeCount}</span>
			</div>
		</div>
	</div>
</body>
<script src="<%=request.getContextPath()%>/wechat/js/jquery-1.9.1.min.js"></script>
<script>
	$(function(){

		var isLiked = '${like.isLike!=null}'; 
		var	likeCount = $('#likecount');

		$('.getlike').on('click',function(){
			// console.log(likeCount.html());
			var n = parseInt(likeCount.html());
			if(!isLiked){
				n+=1;
				$(this).children('i').attr('class', 'ion-ios-heart'); // 改变样式
				effectChange(n);
			}else{
				n-=1;
				$(this).children('i').attr('class', 'ion-ios-heart-outline'); // 改变样式
				effectChange(n);
			}	
		})

		function effectChange (number) {
			// console.log('sending data...');
			likeCount.html(number); // 改变页面中的数值
			isLiked = !isLiked;
			$.post('<%=request.getContextPath()%>/cms/likes/wechat/update.do', {'type': 'content','renfo':'${news.refno}','userId':'${userid}'},
					function(data){
				console.log(data);
			});
		}
	})
</script>
</html>