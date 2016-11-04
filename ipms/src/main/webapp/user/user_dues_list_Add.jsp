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
<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/components/form-file.almost-flat.min.css">
</head>
<body class="uk-height-1-1">
	<div class="uk-panel uk-panel-box">
		<ul class="uk-breadcrumb">
			<li><i class="uk-icon-home"></i> <a href="<%=request.getContextPath()%>/index.jsp">首页</a></li>
			<li>用户管理</li>
			<li>党费管理</li>
			<li class="uk-active">新增党费</li>
		</ul>
		<hr>
		<form class="uk-form uk-form-horizontal myform" name="myform" method="post">
			<input type="hidden" name="addUser" value="${sysuser.username }"> <input type="hidden" name="updUser" value="${sysuser.username }">
			<div class="uk-grid">
				<div class="uk-width-1-2">
					<div class="uk-form-row">
						<label class="uk-form-label" for="">姓名</label>
						<div class="uk-form-controls">
							<input type="text" name="username" id="username" placeholder="姓名" readonly  required > <input type="button" value="选择" onclick="showUserlist()" />
						    <input type="hidden" name="userid" id="userid"/>
						</div>
					</div>

					<div class="uk-form-row">
						<label class="uk-form-label" for="">党费截止日期</label>
						<div class="uk-form-controls">
								<input type="text" name="vailddate" id="vailddate"  placeholder="系统生成" readonly>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label" for="">缴纳月份</label>
						<div class="uk-form-controls">
							<div class="uk-form-icon uk-form-icon-flip">
								<select name="month" id="month" style="width:165px;">
							
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<!-- 右边 -->
				<div class="uk-width-1-2">
					<!-- 头像 upload -->
					<div class="uk-form-row">
						<label class="uk-form-label" for="">缴费方式</label>
						<div class="uk-form-controls">
							<input type="text" name="payType" placeholder="缴费方式" value="在线缴费" readonly />
						</div>
					</div>

					<div class="uk-form-row">
						
						<label class="uk-form-label" for="">党费等级</label>
						<div class="uk-form-controls">
							<input type="text" name="userdues" id="userdues" placeholder="系统生成" readonly>
						</div>
					</div>

					<div class="uk-form-row">
						<label class="uk-form-label" for="">总费用</label>
						<div class="uk-form-controls">
							<input type="text" name="allDues" id="allDues" readonly>
						</div>
					</div>
				</div>
				<div class="uk-width-1-1 uk-margin-top">
					<button type="button" onclick="add()" class="uk-button uk-button-success uk-width-1-1">确定</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script src="<%=request.getContextPath()%>/js/admin.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=request.getContextPath()%>/js/IdCard.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/uikit.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function add() {
		document.myform.action = "<%=request.getContextPath()%>/dues/addDues.do";
		document.myform.submit();
	}
	
	function showUserlist() {
		window.open(
			"<%=request.getContextPath()%>/user/getAllUserDuesList.do",
			"newwindow",
			"height=400, width=850, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,left=280,top=100"
		);
	}

	$(function(){
		var $allDues = $('#allDues');

		$('#month').change(function(){
			var total = $(this).val() * $('#userdues').val();
			$allDues.val(total);
		});
	})

</script>
</html>