<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script type="text/javascript">
	if ('${sysuser.name}' == "") {
		alert("用户已超时，请重新登陆");
		window.top.location.href="<%=request.getContextPath()%>/login.jsp";
	} 
</script>
</head>