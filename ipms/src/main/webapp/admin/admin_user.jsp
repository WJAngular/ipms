<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" class="uk-height-1-1">
<head>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="UTF-8">
	<title>社区党建后台管理系统</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/uikit.almost-flat.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">
</head>
<body class="uk-height-1-1">
<form id="myform" name="myform" method="post" >
	<div class="uk-panel uk-panel-box">
		<ul class="uk-breadcrumb">
			<li><i class="uk-icon-home"></i> <a href="<%=request.getContextPath()%>/index.jsp">首页</a></li>
			<li class="uk-active">系统管理</li>
			<li class="uk-active">账号管理</li>
		</ul>
		<hr>
		<div class="uk-margin-bottom uk-clearfix">
		<div class="uk-float-left">
				<a href="<%=request.getContextPath()%>/admin/admin_user_add.jsp" class="uk-button uk-button-success">新增</a>
			</div>
			<div class="uk-form uk-float-right">
				<fieldset data-uk-margin="">
					<input type="text" placeholder="输入关键字搜索" name="search" value="${search }">
					<button class="uk-button uk-button-primary uk-margin-small-top" type="button" onclick="dosearch()">搜索</button>
				</fieldset>
			</div>
		</div>
		<div class="uk-panel uk-panel-box">
			<table class="uk-table mytable">
				<thead>
					<tr>
						<th width="30">序号</th>
						<th width="50">姓名</th>
						<th width="90">账号</th>
						<th>类型</th>
						<th>IP</th>
						<th>新增日期</th>
						<th class="uk-text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty userList }">
						<c:forEach items="${userList }" var="user" varStatus="d">
							<tr>
								<td>${d.count }</td>
								<td>${user.name }</td>
								<td>${user.username }</td>
								<td>${user.type }</td>
								<td>${user.ip }</td>
								<td><fmt:formatDate value="${user.addtime }" pattern="yyyy-MM-dd HH:mm"/></td>
								<td class="uk-text-center">
									<button class="uk-button uk-button-small" type="button" onclick="javascript:edit('${user.id}')">编辑</button>
									<button class="uk-button uk-button-small uk-button-danger" type="button" onclick="javascript:del('${user.id }')">删除</button>
									<button class="uk-button uk-button-small uk-button-primary" type="button" onclick="javascript:per('${user.id }')">角色</button>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<!-- 分页 -->
			<div style="float: right;margin-right: 60px">
				<span class="disabled">第${pageModel.page}页/共${pageModel.pageCount}页</span>
				<a href="${pageuri}&page=1">首页</a>
				<c:if test="${pageModel.page>1}">
					<a href="javascript:thumbPage('${pageModel.prev}')">上一页</a>
				</c:if>
				<c:forEach var="pre" items="${pageModel.prevPages }">
					<a href="javascript:thumbPage('${pre}')">${pre}</a>
				</c:forEach>
				<span class="current">${pageModel.page }</span>
				<c:forEach var="next" items="${pageModel.nextPages }">
					<a href="javascript:thumbPage('${next}')">${next}</a>
				</c:forEach>
				<c:if test="${pageModel.page<pageModel.last}">
					<a href="javascript:thumbPage('${pageModel.next}')">下一页</a>
				</c:if>
				<a href="javascript:thumbPage('${pageModel.last}')">尾页</a>
			</div>
		</div>
	</div>
	</form>
</body>
<script src="<%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/uikit.min.js"></script>
<script>

function dosearch()
{
	document.myform.action="<%=request.getContextPath()%>/sysusers/getAllList.do";   
	document.myform.submit();
}

function thumbPage(val)
{
	document.myform.action = "<%=request.getContextPath()%>/sysusers/getAllList.do?&page="+val;
	document.myform.submit();
}
 	function del(id) {
		if(confirm("确定删除此记录吗？")){
			$.get("<%=request.getContextPath()%>/sysusers/delAdminUser.do?id=" + id, function(data) {
				if ("success" == data.result) {
					window.location.reload();
				} else {
					alert("删除失败");
				}
			});
		}
	} 

	function edit(id){
		window.location.href="<%=request.getContextPath()%>/sysusers/getAdminUser.do?id=" + id;
	}

	function per(id){
		window.location.href="<%=request.getContextPath()%>/sysusers/getRoles.do?id="+ id;
	}
</script>
</html>