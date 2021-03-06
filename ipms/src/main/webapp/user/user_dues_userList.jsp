<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" class="uk-height-1-1">
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="UTF-8">
<title>党员列表</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/uikit.almost-flat.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">
</head>
<body class="uk-height-1-1">
<form name="myform" method="post" action="">
	<div class="uk-panel uk-panel-box">
		<div class="uk-margin-bottom uk-clearfix">
			<div class="uk-form uk-float-right" >
				<fieldset data-uk-margin="">
				入党日期:<input type="text" name="startDate" value="${startDate}" readonly onClick="WdatePicker()">
					至:<input type="text" name="endDate" value="${endDate}" readonly onClick="WdatePicker()">
					<input type="text" placeholder="输入关键字搜索" name="search" value="${search }">
					<button class="uk-button uk-button-primary uk-margin-small-top" type="button" onclick="dosearch()">搜索</button>
				</fieldset>
			</div>
		</div>
		<div class="uk-panel uk-panel-box">
			<table class="uk-table mytable">
				<thead>
					<tr>
						<th>序号</th>
						<th>姓名</th>
						<th>党支部</th>
						<th>党费等级</th>
						<th>入党日期</th>
						<th>党费截止日期</th>
						<th class="uk-text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty userList }">
						<c:forEach items="${userList }" var="user" varStatus="d">
							<tr>
								<td>${d.count}</td>
								<td>${user.name }</td>
								<td>${user.branch }</td>
								<td>${user.dues }</td>
								<td><fmt:formatDate value="${user.inDate}" /></td>
								<td><fmt:formatDate value="${user.duesvaliddate}" /></td>
								<td class="uk-text-center">
									<button class="uk-button uk-button-small uk-button-danger" onclick="foo('${user.id }','${user.name}','${user.dues}','<fmt:formatDate value="${user.duesvaliddate}" />')">选择</button>
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
<script src="<%=request.getContextPath()%>/js/admin.js"></script>
<script src="<%=request.getContextPath()%>/frame/DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function dosearch()
{
	document.myform.action = "<%=request.getContextPath()%>/user/getAllUserDuesList.do";   
	document.myform.submit();
}
function thumbPage(val)
{
	document.myform.action = "<%=request.getContextPath()%>/user/getAllUserDuesList.do?&page="+val;
	document.myform.submit();
}
	function foo(id,name,dues,vailddate) {
		window.close();
		window.opener.document.getElementById("vailddate").value = vailddate;
		window.opener.document.getElementById("userdues").value = dues;
		window.opener.document.getElementById("username").value = name;
		window.opener.document.getElementById("userid").value = id;
		var m=window.opener.document.getElementById("month").value ;
		window.opener.document.getElementById("allDues").value = m*dues;
		
	}
</script>
</html>