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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/components/accordion.almost-flat.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">
	<style>
		.uk-table { font-size: 14px; border-top: 1px solid #B8CFD3; border-collapse: separate; border-spacing: 1px; }
		.uk-table td{ vertical-align: middle;}
		.uk-table tr:nth-child(even){ background:#EEEEEE;}
		.darkth{ background: #DFEBED;}
		.text-center{ text-align: center!important;}
		.uk-panel { border-radius: 0;}
		.uk-panel-title,.uk-accordion-title { font-size: 14px;}
		.uk-accordion-title{ margin-bottom:8px;}
		.uk-panel-box .uk-panel-title{ color:#555;}
		.uk-grid,.uk-width-2-10,.uk-width-8-10,.uk-width-1-1,.uk-width-1-2{ margin:0;padding:0;}
		.sec_l_nav{ margin-left:-1px;}
		.uk-list li{ padding-left:20px; cursor: pointer; color:#ddd;}
		.uk-list .openup{ color:#000;}
		.color-1{ color: #E84545}
		.color-2{ color: #2D4059}
		.color-3{ color: #4AA0D5}
		.color-4{ color: #EB586F}
		.color-5{ color: #F1C40F}
		.color-6{ color: #9365DB}
		.color-7{ color: #1A936F}
	</style>
</head>
<body>
	<div class="uk-grid">
		<!--表格面板开始-->
		<div class="uk-width-8-10">
			<div class="uk-panel uk-panel-box">
				<ul class="uk-breadcrumb">
					<li><i class="uk-icon-home"></i> <a href="<%=request.getContextPath()%>/index.jsp">首页</a></li>
					<li>系统管理</li>
					<li class="uk-active">菜单管理</li>
				</ul>
				<hr>
				<ul class="uk-subnav uk-subnav-line">
					<li>
						<a href="<%=request.getContextPath()%>/admin/admin_menu_add.jsp" class="uk-button uk-button-success">新增</a>
					</li>
				</ul>
				<table class="uk-table uk-table-hover uk-table-condensed">
					<thead>
						<tr class="darkth">
							<th width="30">编号</th>
							<th>菜单名称</th>
							<th>资源路径</th>
							<th>排序</th>
							<th>描述</th>
							<th class="uk-text-center">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty list }">
							<c:forEach items="${list }" var="t" varStatus="d">
							<tr>
								<td>${d.count }</td>
								<td>${t.name }</td>
								<td>${t.url }</td>
								<td>${t.sort }</td>
								<td>${t.description }</td>
								<td class="uk-text-center">
									<button class="uk-button uk-button-small" onclick="javascript:edit('${t.id}')">编辑</button>
									<button class="uk-button uk-button-small uk-button-danger" onclick="javascript:del('${t.id }')">删除</button>
									<button class="uk-button uk-button-small uk-button-primary" onclick="javascript:per('${t.id }')">权限</button>
								</td>
							</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</div><!--表格面板结束-->
		<!--右边权限列表-->
		<div class="uk-width-2-10">
			<div class="uk-panel uk-panel-box uk-panel-header sec_l_nav">
				<h5 class="uk-panel-title"><i class="uk-icon-cog"></i> 权限列表</h5>
				<div class="uk-accordion" data-uk-accordion>
					<div class="uk-accordion-title"><i class="color-2 uk-icon-justify uk-icon-newspaper-o"></i> 角色管理</div>
					<div class="uk-accordion-content">
						<ul class="uk-list uk-list-line">
							<li>添加 : 角色添加</li>
							<li>删除 : 角色删除</li>
							<li>修改 : 角色修改</li>
							<li>查看 : 角色查看</li>
						</ul>
					</div>
					<div class="uk-accordion-title"><i class="color-1 uk-icon-justify uk-icon-user"></i> 用户管理</div>
					<div class="uk-accordion-content">
						<ul class="uk-list uk-list-line">
							<li>添加 : 用户添加</li>
							<li>删除 : 用户删除</li>
							<li>修改 : 用户修改</li>
							<li>查看 : 用户查看</li>
						</ul>
					</div>
					<div class="uk-accordion-title"><i class="color-3 uk-icon-justify uk-icon-navicon"></i> 菜单管理</div>
					<div class="uk-accordion-content">
						<ul class="uk-list uk-list-line">
							<li>添加 : 菜单添加</li>
							<li>删除 : 菜单删除</li>
							<li>修改 : 菜单修改</li>
							<li>查看 : 菜单查看</li>
						</ul>
					</div>
					<div class="uk-accordion-title"><i class="color-4 uk-icon-justify uk-icon-key"></i> 权限管理</div>
					<div class="uk-accordion-content">
						<ul class="uk-list uk-list-line">
							<li>添加 : 权限添加</li>
							<li>删除 : 权限删除</li>
							<li>修改 : 权限修改</li>
							<li>查看 : 权限查看</li>
						</ul>
					</div>
					<div class="uk-accordion-title"><i class="color-5 uk-icon-justify uk-icon-book"></i> 字典管理</div>
					<div class="uk-accordion-content">
						<ul class="uk-list uk-list-line">
							<li>添加 : 字典添加</li>
							<li>删除 : 字典删除</li>
							<li>修改 : 字典修改</li>
							<li>查看 : 字典查看</li>
						</ul>
					</div>
				</div>
			</div>
		</div> <!-- // 右边权限列表-->
	</div>
</body>
<script src="<%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/uikit.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/components/accordion.min.js"></script>
<script src="<%=request.getContextPath()%>/js/role.js"></script>
<script>
	function edit(id){
		window.location.href="<%=request.getContextPath()%>/permission/getMenu.do?id=" + id;
	}

	function del(id){
		if(confirm("请确认是否删除该记录？")){
			$.get("<%=request.getContextPath()%>/permission/delete.do?id=" + id, function(data) {
				if (0 == data.code) {
					alert("删除成功");
					window.location.reload();
				} else {
					alert("删除失败:"+data.msg);
				}
			});
		}
	}

	function per(id){
		window.location.href="<%=request.getContextPath()%>/permission/getList.do?id=" + id;
	}
</script>
</html>