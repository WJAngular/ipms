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
<title>会议详情</title>
<link href="<%=request.getContextPath()%>/wechat/css/ionic.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/wechat/css/mystyle.css" rel="stylesheet">
</head>
<body id="meeting" style="background: #ffffff">
	<ion-content id="eventPlaceholder">
	<div class="list list-inset">
		<form method="post" action="" name="myform" id="myform">
		<input type="hidden" name="meetingid"  id ="meetingid" value="${cms.id}">
			<div class="item">
				<h2>${cms.title}</h2>
			</div>
			<div class="item">
				<small>负责人</small><span class="item-note">${cms.author}</span>
			</div>
			<div class="item">
				<small>时间</small><span class="item-note"><fmt:formatDate value="${cms.meetingtime}" pattern="yyyy-MM-dd HH:mm" /> </span>
			</div>
			<div class="item">
				<small>地点</small><span class="item-note">${cms.meetingplace}</span>
			</div>
			<div class="item">
				<small>是否参加会议</small> 
				<span class="item-note">
				<select name="join" id="join" style="width:60px;">
				    <option value="Y" ${status=="Y"?"selected":""}>是</option>
					<option value="N" ${status=="N"?"selected":""}>否</option>
				</select>
				</span>
			</div>
			<div style="padding: 0 10px">
				<button id="save" type="button"  class="button button-block button-balanced">确定</button>
			</div>
		</form>
	</div>
	<div class="item item-divider positive">
		<i class="icon ion-ios-paper"></i> 详细内容
	</div>
	<div class="col">${cms.contents}</div>
	<c:if test="${!empty cms.meetingsummary}">
		<div class="item item-divider assertive">
			<i class="icon ion-compose"></i> 会议纪要
		</div>
		<div class="col">${cms.meetingsummary}</div>
	</c:if> </ion-content>
</body>
<script src="<%=request.getContextPath()%>/wechat/js/jquery-1.9.1.min.js"></script>
<script src="<%=request.getContextPath()%>/wechat/js/ionic.bundle.js"></script>
<script type="text/javascript">
$('#save').on('click',function(){
		$.post('<%=request.getContextPath()%>/cms_meeting/wechat/joinMeeting.do', 
		{
			'meetingid':$('#meetingid').val(),
			'join':$('#join').val(),
		}, 
		function(data) {
			if (data=='success') {
				alert('参与成功');
				$('#save').attr('disabled',"true");
			}else{
				alert('参与失败');
			};
		},'text');
})
</script>
</html>