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
			<li class="uk-active">日志管理</li>
		</ul>
		<hr>
		<div class="uk-margin-bottom uk-clearfix">
			<form class="uk-form uk-float-right" name="myform" method="post" action="<%=request.getContextPath()%>/syslog/getAllList.do">
				<fieldset data-uk-margin="">
					<input type="text" placeholder="输入关键字搜索" name="search" value="${search }">
					<button class="uk-button uk-button-primary uk-margin-small-top">搜索</button>
				</fieldset>
			</form>
		</div>
		<div class="uk-panel uk-panel-box">
			<table class="uk-table mytable">
				<thead>
					<tr>
						<th width="30">编号</th>
						<th width="90" class="uk-text-left">账号</th>
						<th width="120">日期</th>
						<th width="90">IP</th>
						<th width="120" class="uk-text-left">信息</th>
						<th width="50">类型</th>
						<th width="500" class="uk-text-left">备注</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty list }">
						<c:forEach items="${list }" var="t" varStatus="d">
							<tr>
								<td>${d.count }</td>
								<td class="uk-text-left">${t.username }</td>
								<td><fmt:formatDate value="${t.addtime}" pattern="yyyy-MM-dd HH:mm" /></td>
								<td>${t.ip}</td>
								<td class="uk-text-left">${t.status}</td>
								<td>${t.type}</td>
								<td class="uk-text-left">${t.rmk}</td>
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