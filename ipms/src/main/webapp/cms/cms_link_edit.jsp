<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" class="uk-height-1-1">
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="UTF-8">
<title>党建后台管理系统</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/uikit.almost-flat.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/components/upload.almost-flat.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/components/placeholder.almost-flat.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/components/form-file.almost-flat.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/components/progress.almost-flat.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">
</head>
<body>
	<div class="uk-panel uk-panel-box">
		<ul class="uk-breadcrumb">
			<li><i class="uk-icon-home"></i> <a href="<%=request.getContextPath()%>/index.jsp">首页</a></li>
			<li>其他管理</li>
			<li class="uk-active">编辑链接</li>
		</ul>
		<hr>
		<form class="uk-form uk-form-stacked myform" name="myform" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${cms.id }"/>
			<div class="uk-grid">
				<!-- 左边 -->
				<div class="uk-width-1-4">
					<div class="uk-position-relative uk-text-center">
						<img id="preview" src="${cms.iconurl}" alt="找不到图片"/>
					</div>
					<div id="upload-drop" class="uk-placeholder uk-text-center">
						<a class="uk-form-file">图标上传<span style="color:red"> *</span><input type="file" name="file" id="doc"
							onchange="javascript:setImagePreview();" />
						</a>
					</div>
					<div id="progressbar" class="uk-progress uk-progress-success uk-progress-striped uk-active uk-hidden">
						<div class="uk-progress-bar" style="width: 0%;">0%</div>
					</div>
				</div>
				<!-- 中间人 -->
				<div class="uk-width-2-4">
					<div class="uk-form-row">
						<label class="uk-form-label" for="">链接名称<span style="color:red"> *</span></label>
						<div class="uk-form-controls">
							<input type="text" size="10" name="linkname" value="${cms.linkname }" required />
							<span class="uk-form-help-inline uk-text-muted">链接名称建议5个字以内</span>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label" for="">链接<span style="color:red"> *</span></label>
						<div class="uk-form-controls">
							<input type="text" name="linkUrl" size="50" value="${cms.linkUrl }" required />
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label" for="">排序<span style="color:red"> *</span></label>
						<div class="uk-form-controls">
							<input type="text" name="orderby" size="20" value="${cms.orderby }" placeholder="排序" required />
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label" for="">备注</label>
						<div class="uk-form-controls">
							<textarea rows="3" cols="67" name="remark" style="resize: none" >${cms.remark }</textarea>
						</div>
					</div>
					<div class="uk-form-row">
						<button class="uk-button uk-button-success" type="submit">确认并保存</button>
					</div>
				</div>
				<!-- 右边 -->
				<div class="uk-width-1-4">
					<p class="uk-text-muted illustration">
						新增链接模块在其他链接页面显示<br>
						=======================<br>
						链接图标尺寸为：80*80<br>
						=======================<br>
						为方便管理可对每个链接添加备注，备注信息不会在页面中显示
					</p>
				</div>
			</div>
		</form>
	</div>
</body>
<script src="<%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/uikit.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/components/upload.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/validation/jquery.validate.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/validation/messages_zh.js"></script>
<script src="<%=request.getContextPath()%>/frame/validation/additional-methods.js"></script>
<script src="<%=request.getContextPath()%>/js/IdCard.js"></script>
<script type="text/javascript">
	function setImagePreview() {
		var docObj = document.getElementById("doc");
		var imgObjPreview = document.getElementById("preview");
		if (docObj.files && docObj.files[0]) {
			//火狐下，直接设img属性 
			imgObjPreview.style.display = 'block';
			imgObjPreview.style.width = '160px';
			imgObjPreview.style.height = '160px';
			//imgObjPreview.src = docObj.files[0].getAsDataURL(); 
			//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式 
			imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
		} else {
			//IE下，使用滤镜 
			docObj.select();
			var imgSrc = document.selection.createRange().text;
			var localImagId = document.getElementById("localImag");
			//必须设置初始大小 
			localImagId.style.width = "80px";
			localImagId.style.height = "80px";
			//图片异常的捕捉，防止用户修改后缀来伪造图片 
			try {
				localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
				localImagId.filters
						.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
			} catch (e) {
				alert("您上传的图片格式不正确，请重新选择!");
				return false;
			}
			imgObjPreview.style.display = 'none';
			document.selection.empty();
		}
		return true;
	}
	$(function(){
		$('.myform').validate({
			errorClass:'error',
			success:'valid',
			rules: {
				linkname:{
					required:true,
				},
				linkUrl:{
					required:true,
				},
				orderby:{
					required:true,
					number:true,
				}
			},
			submitHandler:function(){
				document.myform.action = "<%=request.getContextPath()%>/cms_link/updateLink.do";
				document.myform.submit();
			}
		});
	})
</script>
</html>