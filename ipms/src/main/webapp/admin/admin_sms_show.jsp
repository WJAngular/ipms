<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" class="uk-height-1-1">
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="UTF-8">
<title>党建后台管理系统</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/uikit.almost-flat.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/easyui/themes/default/easyui.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/easyui/themes/icon.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/easyui/demo/demo.css">
</head>
<body class="uk-height-1-1">
	<div class="uk-panel uk-panel-box">
		<ul class="uk-breadcrumb">
			<li><i class="uk-icon-home"></i> <a href="<%=request.getContextPath()%>/index.jsp">首页</a></li>
			<li>短信平台</li>
			<li class="uk-active">查看发送失败的短信</li>
		</ul>
		<hr>
		<form class="uk-form uk-form-horizontal myform" name="myform" method="post">
		<input type="hidden" name="id" value="${sms.id }"/>
			<div class="uk-grid">
				<div class="uk-width-1-2">
					<div class="uk-form-row">
						<label class="uk-form-label" for="">收信人</label>
						<div class="uk-form-controls">
							<input value="${person}" id="cc" name="person" class="easyui-combotree" multiple style="width: 350px;" />
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label" for="">发送失败的号码</label>
						<div class="uk-form-controls">
							<textarea rows="5" cols="67" name="numbers" readonly style="resize: none">${sms.errorNumber } </textarea>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label" for="">返回信息</label>
						<div class="uk-form-controls">
							<textarea rows="5" cols="67" name="info" style="resize: none;" >${sms.info } </textarea>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label" for="">内容</label>
						<div class="uk-form-controls">
							<textarea rows="5" cols="67" name="contents" style="resize: none;" id="sms_content">${sms.contents } </textarea>
							<span class="uk-form-help-inline uk-text-muted uk-text-small"> 已输入 <label id="word_num">0</label> 个字符
							</span>
							<p class="uk-form-help-block uk-text-warning" id="warning">每条短信可发送70个汉字(160个字符),超过的汉字将会拆分多条短信</p>
						</div>
					</div>
				</div>
				<div class="uk-width-1-1 uk-margin-top">
					<button class="uk-button uk-button-large uk-button-success uk-width-1-1" onclick="upd()">重发</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script src="<%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/uikit.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/easyui/jquery.easyui.min.js"></script>
<script>
	$(document).ready(function() {
		$('#cc').combotree({
			url : '<%=request.getContextPath()%>/user/getUserJson.do',
			onlyLeafCheck : true
		});
		    var t = $("#cc").combotree('tree');
			var n=true;
			t.tree({
				onClick:function(node){
					if(n){
						t.tree('check',node.target);
						n=false;
					}else{
						t.tree('uncheck',node.target);
						n= true;
					}
					console.log(n);
				}
	       });
		
		var $smsContent = $('#sms_content'),
			$warning	= $('#warning'),
			str,abcnum,total = 0;

		$smsContent.keyup(function(event) {
			var content   = $smsContent.val(),
				wordCount = changeNum(content);
			$('#word_num').html(wordCount);
			if ( wordCount > 70 ) {
				$warning.text('该内容会被拆分为 '+ Math.ceil(wordCount/70) +' 条短信');
			}else{
				$warning.text('每条短信可发送70个汉字(160个字符),超过的汉字将会拆分多条短信');
			}
		});

		function changeNum(param){
			//汉字的个数
			str = param.replace(/\w/g,"").length;
			//非汉字的个数
			abcnum = param.length-str;
			total = str*2+abcnum;
			return total;
		};

	});

	function upd() {
		document.myform.action = "<%=request.getContextPath()%>/sms/updateSms.do";
		document.myform.submit();
	}
</script>
</html>