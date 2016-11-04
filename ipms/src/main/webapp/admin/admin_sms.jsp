<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" class="uk-height-1-1">
<head>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="UTF-8">
	<title>党建后台管理系统</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/uikit.almost-flat.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">
</head>
<body class="uk-height-1-1">
	<div class="uk-panel uk-panel-box">
		<ul class="uk-breadcrumb">
			<li><i class="uk-icon-home"></i> <a href="<%=request.getContextPath()%>/index.jsp">首页</a></li>
			<li class="uk-active">短信平台</li>
		</ul>
		<hr>
		<div class="uk-margin-bottom uk-clearfix">
			<div class="uk-float-left">
				<a href="<%=request.getContextPath()%>/admin/admin_sms_add.jsp" class="uk-button uk-button-success">新增</a>
			</div>
			<form class="uk-form uk-float-right" name="myform" method="post" action="<%=request.getContextPath()%>/sms/getAllList.do">
				<fieldset data-uk-margin="">
					<input type="text" placeholder="输入内容关键字搜索" name="search" value="${search }">
					<button class="uk-button uk-button-primary uk-margin-small-top">搜索</button>
				</fieldset>
			</form>
		</div>
		<div class="uk-panel uk-panel-box">
			<table class="uk-table mytable">
				<thead>
					<tr>
						<th width="30">编号</th>
						<th class="uk-text-left">内容</th>
						<th width="160">发送日期</th>
						<th width="90">发送状态</th>
						<th width="80">类型</th>
						<th width="160">是否存在失败号码</th>
						<th class="uk-text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty list }">
						<c:forEach items="${list }" var="sms" varStatus="d">
							<tr>
								<td>${d.count }</td>
								<td class="uk-text-left">${sms.contents }</td>
								<td><fmt:formatDate value="${sms.addtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${sms.status=='Y'?'成功':'失败' }</td>
								<td>${sms.type=='sms'?'短信平台':'会议' }</td>
							    <td>${sms.error=='Y'?'是':'否' }</td>
								<td class="uk-text-center">
									<button class="uk-button uk-button-small" onclick="edit('${sms.id }')">查看</button>
									<button class="uk-button uk-button-small uk-button-primary" onclick="find('${sms.id }')">查看详细状态</button>
									<button class="uk-button uk-button-small uk-button-danger" onclick="javascript:del('${sms.id }')">删除</button>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<!-- 分页 -->
			<div style="float: right; margin-right: 60px">
				<span class="disabled">第${pageModel.page}页/共${pageModel.pageCount}页</span> <a href="${pageuri}&page=1">首页</a>
				<c:if test="${pageModel.page>1}">
					<a href="${pageuri}&page=${pageModel.prev}">上一页</a>
				</c:if>

				<c:forEach var="pre" items="${pageModel.prevPages }">
					<a href="${pageuri}&page=${pre}">${pre}</a>
				</c:forEach>
				<span class="current">${pageModel.page }</span>
				<c:forEach var="next" items="${pageModel.nextPages }">
					<a href="${pageuri}&page=${next}">${next}</a>
				</c:forEach>

				<c:if test="${pageModel.page<pageModel.last}">
					<a href="${pageuri}&page=${pageModel.next}">下一页</a>
				</c:if>
				<a href="${pageuri}&page=${pageModel.last}">尾页</a>
			</div>
		</div>
	</div>
</body>
<script src="<%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/uikit.min.js"></script>
<script>
	function del(id) {
		$.get("<%=request.getContextPath()%>/sms/delSms.do?id=" + id, function(data) {
			if ("success" == data.result) {
				alert("删除成功");
				window.location.reload();
			} else {
				alert("删除失败");
			}
		});
	}
	function edit(id) {
		window.location.href = "<%=request.getContextPath()%>/sms/getSms.do?id="+ id;
	}
	function find(id) {
		window.location.href = "<%=request.getContextPath()%>/sms/showSms.do?id="+ id;
	}
</script>
</html>