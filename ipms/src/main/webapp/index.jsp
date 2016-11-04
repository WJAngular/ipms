<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" class="uk-height-1-1">
<head>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="UTF-8">
	<title>CMS管理系统</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/uikit.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">
	<!--[if IE 9]>
		<style>
			.fastlink .uk-text-center{ display:inline-block; width:30%;}
		</style>
	<![endif]-->
	<style type="text/css">
.STYLE1 {
	font-size: 12px;
	color: white;
}
</style>
<script type="text/javascript">
	function startclock() {
		document.getElementById('sys').innerHTML=detectOS();
		document.getElementById('browser').innerHTML=getBrowser();
		document.getElementById('dates').innerHTML=getDate();
		stopclock();
		showtime();
	}
</script>
</head>
<body class="uk-height-1-1" onload="startclock()">
	<div class="uk-panel uk-panel-box uk-height-1-1">
		<div class="uk-grid">
			<div class="uk-width-4-10">
				<div class="uk-panel uk-panel-box uk-panel-box-secondary uk-panel-header">
					<div class="uk-panel-badge uk-badge uk-badge-success"><span class="STYLE1" id="timeshow"></span></div>
					<h3 class="uk-panel-title uk-text-primary uk-text-bold">您好，${sysuser.name}</h3>
					欢迎进入社区党建后台管理系统
				</div>
			</div>
			<div class="uk-width-6-10">
				<div class="uk-panel uk-panel-box uk-panel-header sys_info">
					<table class="uk-table">
								<caption class="uk-panel-title uk-text-center">
									系统信息/system information</caption>
								<tbody>
									<tr>
										<td class="uk-width-2-10 uk-text-bold">操作系统：</td>
										<td class="uk-width-3-10"><span id="sys"></span></td>
										<td class="uk-width-2-10 uk-text-bold">浏览器：</td>
										<td class="uk-width-3-10"><span id="browser"></span></td>
									</tr>
									<tr>
										<td class="uk-width-2-10 uk-text-bold">登录IP：</td>
										<td class="uk-width-3-10"><span id="localIp">127.0.0.1</span></td>
										<td class="uk-width-2-10 uk-text-bold">登录时间</td>
										<td class="uk-width-3-10"><span id="dates">getDate</span></td>
									</tr>
								</tbody>
							</table>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="<%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/uikit.min.js"></script>
<script src="<%=request.getContextPath()%>/js/other.js"></script>
</html>