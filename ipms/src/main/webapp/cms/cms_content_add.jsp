<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/components/datepicker.almost-flat.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">
</head>
<body>
	<div class="uk-panel uk-panel-box">
		<ul class="uk-breadcrumb">
			<li><i class="uk-icon-home"></i> <a href="<%=request.getContextPath()%>/index.jsp">首页</a></li>
			<li>内容管理</li>
			<li class="uk-active">内容发布</li>
		</ul>
		<hr>
		<form class="uk-form uk-form-stacked myform" name="myform" method="post" enctype="multipart/form-data">
			<div class="uk-grid">
				<!-- 左边 -->
				<div class="uk-width-1-3">
					<div class="uk-overlay">
						<img Style="width:400px;height:266px"  id="preview" src="<%=request.getContextPath()%>/img/placeholder_600x400.svg" />
						<div class="uk-overlay-caption">图片建议尺寸为200像素*200像素</div>
					</div>
					<!-- <div class="uk-margin-top">
						<button  type="button" class="uk-button uk-width-1-1 uk-button-large"><i class="uk-icon-plus"></i></button>
					</div> -->
					<div id="upload-drop" class="uk-placeholder uk-text-center">
						<i class="uk-icon-cloud-upload uk-icon-medium uk-text-muted uk-margin-small-right"></i> <a class="uk-form-file">选择图片作为封面<input type="file" name="file" id="doc"
							onchange="javascript:setImagePreview();" />
						</a>
					</div>
					<div id="progressbar" class="uk-progress uk-progress-success uk-progress-striped uk-active uk-hidden">
						<div class="uk-progress-bar" style="width: 0%;">0%</div>
					</div>
				</div>
				<!-- 右边 -->
				<div class="uk-width-2-3">
					<div class="uk-form-row">
						<label class="uk-form-label" for="">栏目<span style="color:red"> *</span></label> <select name="topic" id="topic" style="width:250px">
						</select>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label" for="">标题<span style="color:red"> *</span></label>
						<div class="uk-form-controls">
							<input type="text" size="64" name="title" placeholder="文章标题" required>
						</div>
					</div>
					<!-- <div class="uk-form-row">
								<label class="uk-form-label" for="">参加日期（选填）</label>
								<div class="uk-form-controls uk-form-controls-text">
									<input type="text" name="meettime" readonly onClick="WdatePicker()" >&nbsp;&nbsp;&nbsp;
								</div>	
					</div> -->
					<div class="uk-form-row">
						<label class="uk-form-label" >摘要</label>
						<div class="uk-form-controls">
							<textarea rows="3" cols="67" name="abstracts" id="abstracts" placeholder="选填,如果不填写会默认捉取正文前54个字" style="resize: none"></textarea>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label" for="">内容<span style="color:red"> *</span></label>
						<!-- UEditor -->
						<div class="uk-form-controls">
							<script id="contents" name="contents" type="text/plain"></script>
							<!-- 配置文件 -->
							<script type="text/javascript" src="<%=request.getContextPath()%>/frame/UEditor/ueditor.config.js"></script>
							<!-- 编辑器源码文件 -->
							<script type="text/javascript" src="<%=request.getContextPath()%>/frame/UEditor/ueditor.all.min.js"></script>
							<!-- 语言选择 -->
							<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/frame/UEditor/lang/zh-cn/zh-cn.js"></script>
							<!-- 实例化编辑器 -->
							<script type="text/javascript">
								var ue = UE.getEditor('contents');
							</script>
						</div>
					</div>
					<div class="uk-form-row">
						<button class="uk-button uk-button-success" type="submit">保存</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
<script src="<%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/uikit.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/components/upload.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/DatePicker/WdatePicker.js"></script>
<script src="<%=request.getContextPath()%>/frame/validation/jquery.validate.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/validation/messages_zh.js"></script>
<script src="<%=request.getContextPath()%>/frame/validation/additional-methods.js"></script>
<script type="text/javascript">
		$.ajax({
				type : "post",
				url : "<%=request.getContextPath()%>/sysdictionary/getTopic.do",
				dataType : "json",
				success : function(data) {
					$.each(data, function(i, value) {
						$('#topic').append($("<option/>", {
							value : value,
							text : value
						}));
					});
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});



	function setImagePreview() {
		var docObj = document.getElementById("doc");
		var imgObjPreview = document.getElementById("preview");
		if (docObj.files && docObj.files[0]) {
			//火狐下，直接设img属性 
			imgObjPreview.style.display = 'block';
			imgObjPreview.style.width = '400px';
			imgObjPreview.style.height = '266px';
			//imgObjPreview.src = docObj.files[0].getAsDataURL(); 
			//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式 
			imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
		} else {
			//IE下，使用滤镜 
			docObj.select();
			var imgSrc = document.selection.createRange().text;
			var localImagId = document.getElementById("localImag");
			//必须设置初始大小 
			localImagId.style.width = "400px";
			localImagId.style.height = "266px";
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
				title:{
					required:true,
				}
			},
			submitHandler:function(){
				if($("#abstracts").val()=="")
				{
				    var abstr = UE.getEditor('contents').getContentTxt();
				    if(abstr.length>54){
					abstr = abstr.substring(0,54);
				    }else
					{
					abstr = abstr.substring(0,abstr.length);
					}
				  $("#abstracts").val(abstr);
				}
				document.myform.action = "<%=request.getContextPath()%>/cms_content/addContent.do";
				document.myform.submit();
			}
		});
	});

	 
</script>
</html>