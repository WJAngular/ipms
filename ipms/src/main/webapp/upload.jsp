<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>UpLoad</title>
</head>
<body>

<form action="<%=request.getContextPath()%>/upload/upload_image.do" enctype="multipart/form-data" method="post">
文件：<input name="file" type="file" /><input  type="submit" value="上传"/>
</form>
</body>
</html>