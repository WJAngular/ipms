<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
  <meta charset="UTF-8">
  <title>党建后台管理系统</title>
  <style>
    html,body{ height: 100%; overflow: hidden;}
    body{
      font-family: "微软雅黑" ;
      background: #f1f1f1;
      display: table;
    }
    .content,.cartoon{
      vertical-align: middle;
      display: table-cell;
    }
    img{ vertical-align: middle;}
    .error{
      font-size:36px;
      border-bottom: 1px solid #ccc;
      color:#99460a;
      padding-bottom: 15px;
      margin-left:200px;
    }
    .try{ margin-left:200px; }
    .try h4{ color:#343738;}
    .try p{ padding-left:28px; color:#0277D0;}
  </style>
</head>
<body>
<div class="content">
  <div class="error">
    <img src="<%=request.getContextPath()%>/img/error.png" height="48" ondragstart="return false;"> 信息提示
  </div>
  <div class="try">
    <article>
      <p>${msg}</p>
    </article>
    <article>
      <p><a href="javascript:window.history.back();">返回</a></p>
    </article>
  </div>
</div>
<div class="cartoon">
  <img src="<%=request.getContextPath()%>/img/cartoon.png" ondragstart="return false;">
</div>
</body>
</html>