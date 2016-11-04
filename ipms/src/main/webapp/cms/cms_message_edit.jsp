<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<li><i class="uk-icon-home"></i> <a
				href="<%=request.getContextPath()%>/index.jsp">首页</a></li>
			<li>内容管理</li>
			<li class="uk-active">图文修改</li>
		</ul>
		<hr>
		<div class="uk-grid">
			<!-- 左边 图片列表区域 -->
			<div class="uk-width-1-3">
				<figure class="uk-position-relative uk-overlay uk-overlay-hover">
					<img width="600" height="400" class="item-main"
						 src="${se_imag_map[thumbMediaId]}" />
					<div class="uk-position-cover uk-flex uk-flex-bottom">
						<div class="main_title">标题</div>
					</div>

				</figure>
			</div>
			<!-- 右边 表单区域-->
			<div class="uk-width-2-3">
				<div id="formChange">
					<form class="uk-form uk-form-stacked myform" name="item-main">
						<div class="uk-form-row">
							<label class="uk-form-label" for="title">标题</label>
							<div class="uk-form-controls">
								<div class="uk-form-icon uk-form-icon-flip">
									<input type="text" size="64" name="title" placeholder="文章标题" value="${news.title }"
										required>
								</div>
							</div>
						</div>
						<div class="uk-form-row">
							<label class="uk-form-label" for="author">作者(选填)</label>
							<div class="uk-form-controls">
								<div class="uk-form-icon uk-form-icon-flip">
									<input type="text" name="author" size="64"
										placeholder="文章作者/发布人" value="${news.author }">
								</div>
							</div>
						</div>
						<div class="uk-form-row">
							<label class="uk-form-label" for="covers">封面</label>
							<div class="uk-form-controls">
								<div class="uk-form-icon uk-form-icon-flip fileupload">
									<button type="button" data-uk-modal="{target:'#my-id'}">选择图片</button>
									<input type="text" name="picId" class="picurl" value="${thumbMediaId}" readonly>
								</div>
							</div>
						</div>
						<div class="uk-form-row">
							<label class="uk-form-label" for="abstract">摘要</label>
							<div class="uk-form-controls">
								<textarea rows="3" cols="67" name="abstracts"
									style="resize: none" value="${news.digest }"></textarea>
							</div>
						</div>
						<div class="uk-form-row">
							<label class="uk-form-label" for="content">内容</label>
							<div class="uk-form-controls">
								<script id="item-main" name="contents" type="text/plain"></script>
							</div>
						</div>
						<div class="uk-form-row">
							<label class="uk-form-label" for="title">原文链接</label>
							<div class="uk-form-controls">
								<div class="uk-form-icon uk-form-icon-flip">
									<input type="text" size="64" name="url" placeholder="原文链接">
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="uk-margin">
					<button type="button" id="save" class="uk-button uk-button-primary">保存</button>
					<button type="button" class="uk-button uk-button-success"
						id="sendBtn" onclick="send()">推送到微信</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态对话框 -->
	<div id="my-id" class="uk-modal">
		<div class="uk-modal-dialog">
			<div class="uk-modal-header">从图片库中选择</div>
			<ul class="uk-thumbnav uk-grid-width-1-4 uk-text-center">
				<c:forEach items="${imgList }" var="img">
					<li><img class="uk-thumbnail" src="${img.url }" alt="${img.mediaID }" width="120" height="80"></li>
				</c:forEach>
			</ul>
			<hr>
			<span class="uk-text-muted">上传图片</span> <input type="file" />
			<div class="uk-modal-footer uk-text-center">
				<button class="uk-button uk-modal-close">取消</button>
				<button class="uk-button uk-button-success" type="button" id="choseAlbum">确定</button>
			</div>
		</div>
	</div>
</body>
<script src="<%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/uikit.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/components/upload.min.js"></script>
<!-- 加载ue配置 -->
<script src="<%=request.getContextPath()%>/frame/UEditor/ueditor.msg.config.js"></script>
<script src="<%=request.getContextPath()%>/frame/UEditor/ueditor.all.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/UEditor/lang/zh-cn/zh-cn.js"></script>
<script src="<%=request.getContextPath()%>/frame/UEditor/addCustomizeDialog.js"></script>
<!-- 页面脚本 -->
<script src="<%=request.getContextPath()%>/js/cms_message.js"></script>
<script>
	var str = "${news.content}";
	var ue = UE.getEditor('item-main');
	ue.ready(function() {
		ue.setContent(str);
	});
</script>
</html>