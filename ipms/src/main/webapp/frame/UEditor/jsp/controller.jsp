<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
 <%@page import="java.lang.System" %>
<%

    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	String path = application.getRealPath( "/" );
	System.out.print("path:"+path);
	out.write( new ActionEnter( request, path ).exec() );
	
%>