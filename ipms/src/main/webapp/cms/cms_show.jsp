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
<title>内容详情</title>
<link href="<%=request.getContextPath()%>/wechat/css/normalize.css" rel="stylesheet">
<style>
html,body,h1,h2,h3,h4,h5,h6,img,table{  margin:0; padding:0; }
		html, body { width:100%; min-height:100%; background: #fff;}
		table{width:100%!important;}
		img,table{ max-width:100%;}
		.container{ padding:15px;}
		.info{ margin-top:12px;}
		.info_time,.info_resource{display:inline-block; margin-right:15px;}
		.info_time{ color:#aaa;}
		.info_resource a{ text-decoration: none; color:#3498DB;}

		.detail_content section{ max-width: 100%; }

		.getlike, .readnum { display: inline-block; }
		.getlike { margin-left: 25px; color: #F06161; text-decoration: none; }
		.readnum { color: #888; }
		
		.art_foot { margin-top:30px; font-size:16px;}
</style>
</head>
<body>
	<div class="container">
		<h3>${cms.title}</h3>
		<div class="info">
			<span class="info_time"><fmt:formatDate value="${cms.addtime}" pattern="yyyy-MM-dd" /></span> <span class="info_resource"><a href="javascript:void()">${cms.author}</a></span>
		</div>
		<div class="detail_content">${cms.contents}</div>
	</div>
</body>
</html>