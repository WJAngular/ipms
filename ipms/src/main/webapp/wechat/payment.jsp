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
	<title>党费缴纳</title>
	<link href="css/ionic.css" rel="stylesheet">
	<link href="css/mystyle.css" rel="stylesheet">
</head>
<body ng-controller="picLoader">
	<div class="pic">
		<img ng-src="{{photo}}" width="80" height="80"> 
		<p>${user.name}</p>
	</div>
	<div class="row justify">
		<div class="col col-33 pay_level text-center">
			党费：<span>{{ paylevel }}元</span>
		</div>
	</div>
	<div class="pay_record">
		<div class="list">
			<label class="item item-input">
				<strong class="input-label">党费缴纳日期</strong>
				<input type="text" value="2016-01" readonly>
			</label>
			<label class="item item-input">
				<strong class="input-label">党费截止日期</strong>
				<input type="text" value="2016-01" readonly>
			</label>
			<label class="item item-input">
				<strong class="input-label">缴纳月数</strong>
				<input type="text" ng-model="paymonth" ng-init="paymonth='1'">
			</label>
			<label class="item item-input">
				<strong class="input-label">费用总计</strong>
				<input type="text" disabled value="{{ paymonth * paylevel }}元">
			</label>
		</div>
		<div class="pad-lr-16">
			<button class="button button-block button-balanced">确认</button>
		</div>
	</div>
</body>
<script src="js/ionic.bundle.js"></script>
<script src="controller/app.js"></script>
</html>