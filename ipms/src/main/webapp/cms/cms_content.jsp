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
				<li class="uk-active">内容管理</li>
			</ul>
			<hr>
			<div class="uk-margin-bottom uk-clearfix">
			<div class="uk-float-left">
				<a href="<%=request.getContextPath()%>/cms/cms_content_add.jsp" class="uk-button uk-button-success">新增民主评议</a>
			</div>
			<form class="uk-form uk-float-right" name="myform" method="post" action="<%=request.getContextPath()%>/cms_survey/getAllList.do">
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
							<th width="30">序号</th>
							<th class="uk-text-left">标题</th>
							<th width="80">栏目</th>
							<th width="70">作者</th>
							<th width="90">发布日期</th>
							<th class="uk-text-center">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty cmslist }">
							<c:forEach items="${cmslist }" var="cms" varStatus="d">
								<tr>
									<td>${d.count}</td>
									<td class="uk-text-left">${cms.title }</td>
									<td>${cms.topic }</td>
									<td>${cms.author}</td>
									<td><fmt:formatDate value="${cms.addtime}" /></td>
									<td class="uk-text-center">
										<button class="uk-button uk-button-small" type="button" onclick="javascript:show('${cms.id}')">预览</button>
										<button class="uk-button uk-button-small" type="button" onclick="javascript:edit('${cms.id}')">编辑</button> <c:if test="${cms.status=='N'}">
											<button class="uk-button uk-button-small uk-button-danger" type="button" onclick="javascript:del('${cms.id }')">删除</button>
											<c:if test="${sysuser.type=='admin' || sysuser.type=='dw'}">
												<button class="uk-button uk-button-small uk-button-primary" type="button" onclick="javascript:approve('${cms.id }')">未审核</button>
											</c:if>
											<c:if test="${sysuser.type!='admin' && sysuser.type!='dw'}">
												<button class="uk-button uk-button-small" type="button" disabled>未审核</button>
											</c:if>
										</c:if> <c:if test="${cms.status=='Y'}">

											<c:if test="${sysuser.type=='admin' || sysuser.type=='dw'}">
												<button class="uk-button uk-button-small uk-button-danger" type="button" onclick="javascript:invalid('${cms.id }')">删除</button>
											</c:if>
											<c:if test="${sysuser.type!='admin' && sysuser.type!='dw'}">
												<button class="uk-button uk-button-small uk-button-danger" type="button" disabled>删除</button>
											</c:if>
											<button class="uk-button uk-button-small uk-button-success" type="button">已审核</button>
										</c:if>
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
</body>
<script src="<%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/uikit.min.js"></script>
<script type="text/javascript">
function dosearch()
{
	document.myform.action="<%=request.getContextPath()%>/cms_content/getAllList.do";   
	document.myform.submit();
}

function thumbPage(val)
{
	document.myform.action = "<%=request.getContextPath()%>/cms_content/getAllList.do?&page="+val;
	document.myform.submit();
}

	function invalid(id) {
		if(confirm("确定删除该记录吗？")){
		  $.get("<%=request.getContextPath()%>/cms_content/getInvalid.do?id=" + id, function(data) {
			if ("success" == data.result) {
				alert("删除成功");
				window.location.reload();
			} else {
				alert("删除失败");
			}
		});
		}
	}
	function del(id) {
		if(confirm("确定删除此记录吗？")){
		  $.get("<%=request.getContextPath()%>/cms_content/delContent.do?id=" + id, function(data) {
			if ("success" == data.result) {
				alert("删除成功");
				window.location.reload();
			} else {
				alert("删除失败");
			}
		});
		}
	}
	function edit(id) {
		window.location.href = "<%=request.getContextPath()%>/cms_content/getContent.do?id=" + id;
	}
	function approve(id) {
		if(confirm("确定审核此记录吗？")){
			window.location.href = "<%=request.getContextPath()%>/cms_content/approve.do?id=" + id;
		}
	}
	
	function show(id) {
	   window.open("<%=request.getContextPath()%>/cms_content/show.do?id="+ id,
		'newwindow','height=627, width=375, top=150, left=380, toolbar=no, menubar=no, scrollbars=1, resizable=no, location=no, status=no')
	}
</script>
</html>