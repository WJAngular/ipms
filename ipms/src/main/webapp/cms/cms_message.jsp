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
	<link rel="stylesheet" href="<%=request.getContextPath() %>/frame/uikit/css/uikit.almost-flat.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/frame/icheck/skins/all.css" >
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frame/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frame/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frame/easyui/demo/demo.css">
	<style>
		dl{ border-bottom:1px solid #E9E9E9; padding-bottom: 20px;}
		dd{ line-height:28px; overflow: hidden;}
		.mescount{ line-height: 30px;}
		.active{ border:none!important;}
		.uk-description-list-horizontal>dt{ width:180px;}
		.uk-description-list-horizontal>dd{ margin-left: 200px;}
		.iradio_square-green { margin-right: 8px;}
	</style>
	<script src="<%=request.getContextPath() %>/js/jquery-2.1.4.min.js"></script>
</head>
<body class="uk-height-1-1">
	<div class="uk-panel uk-panel-box">
		<ul class="uk-breadcrumb">
		<li><i class="uk-icon-home"></i> <a href="<%=request.getContextPath() %>/index.jsp">首页</a></li>
			<li class="uk-active">图文管理</li>
		</ul>
		<hr>
		<div class="uk-margin-bottom uk-clearfix">
			<div class="uk-float-left uk-text-muted mescount">
				图文消息共 ${VO.totalCount } 条
			</div>
			<div class="uk-float-right">
				<button type="button" class="uk-button uk-button-danger" id="actiBtn"><i class="uk-icon-mail-forward"></i> 推送活跃人群</button>
				<button type="button" class="uk-button uk-button-danger" id="allpull"><i class="uk-icon-mail-forward"></i> 推送党支部</button>
				<button type="button" class="uk-button uk-button-danger" id="allBtn"><i class="uk-icon-mail-forward"></i> 推送全体人员</button>
				<a href="<%=request.getContextPath() %>/media/toNewsPage.do" class="uk-button uk-button-success"><i class="uk-icon-plus"></i> 新建图文消息</a>
			</div>
		</div>
		<div class="uk-panel uk-panel-box">
			<c:forEach items="${VO.items }" var="item" varStatus="vs">
				<dl class="uk-description-list-horizontal">
					<dt>
						<input type="radio" name="myradio" value="${item.mediaId}">
						<img src="<%=request.getContextPath() %>/img/test1.jpg" id="img_${vs.count}" width="150" height="150">
					</dt>
					<c:forEach items="${item.news_item }" var="i" varStatus="status">
						<dd><a href="${i.url }" target="_blank">${status.count }.${i.title }</a>
							<a href="<%=request.getContextPath() %>/media/${item.mediaId}/${status.count-1 }/${i.thumbMediaId}/editNews.do" class="uk-button uk-button-primary uk-button-small uk-float-right">编辑</a>
							<c:if test="${status.first }">
							<button type="button" class="preview uk-button uk-button-small uk-float-right uk-margin-small-right">预览</button>
							<span class="uk-float-right uk-display-inline-block uk-margin-large-right uk-text-muted">${item.update_time }</span>
							<script type="text/javascript">
								$('#img_${vs.count}').attr("src",'${se_imag_map[i.thumbMediaId]}');
							</script>
							</c:if>
						</dd>
					</c:forEach>
				</dl>
			</c:forEach>
			
			<!-- 分页 -->
			<div style="float: right; margin-right: 60px">
				<span class="disabled">第${VO.page}页/共${VO.total}页</span> <a href="<%=request.getContextPath() %>/media/AllNewsList/1/get.do">首页</a>
				<c:if test="${VO.page>1}">
					<a href="<%=request.getContextPath() %>/media/AllNewsList/${VO.page-1 }/get.do">上一页</a>
				</c:if>
				<c:if test="${VO.page<VO.total}">
					<a href="<%=request.getContextPath() %>/media/AllNewsList/${VO.page+1 }/get.do">下一页</a>
				</c:if>
				<a href="<%=request.getContextPath() %>/media/AllNewsList/${VO.total }/get.do">尾页</a>
			</div>
		</div>
	</div>
	<!-- preview modal -->
	<div id="my-id" class="uk-modal">
		<div class="uk-modal-dialog">
			<div class="uk-modal-header">选择党支部发送图文消息进行预览</div>
			<!--表单-->
			<form class="uk-form">
				<div class="uk-grid">
					<div class="uk-width-1-3 uk-push-1-3">
						<select class="uk-width-1-1" id="chooseAdmin">
							<c:forEach items="${adminList }" var="admin">
								<option value="${admin['weixin'] }">${admin['name'] }</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</form>
			<!--//-->
			<div class="uk-modal-footer uk-text-center">
				<div class="uk-button-group">
					<button type="button" class="uk-button" id="cancel">取消</button>
					<button type="button" class="uk-button uk-button-success" id="sure">确定</button>
				</div>
			</div>
		</div>
	</div>

	<!-- pull modal -->
	<div id="pullmodal" class="uk-modal">
		<div class="uk-modal-dialog">
			<div class="uk-modal-header">选择推送的党支部</div>
			<!--表单-->
			<form class="uk-form">
				<div class="uk-grid">
					<div class="uk-width-1-1 uk-push-1-1">
						<select class="uk-width-1-1" id="chooseGroup">
							<option value="${admin['weixin'] }">请选择分组</option>
							<!-- <option value="1">第一分组</option>
							<option value="2">第二分组</option> -->
							<option value="香蜜社区综合党委">香蜜社区综合党委</option>
							<option value="侨香社区联合党总支">侨香社区联合党总支</option>
							<option value="竹园社区综合党委">竹园社区综合党委</option>
							<option value="农园社区综合党委">农园社区综合党委</option>
							<option value="机关党总支">机关党总支</option>
							<option value="香梅社区综合党委">香梅社区综合党委</option>
							<option value="香安社区综合党委">香安社区综合党委</option>
							<option value="竹林社区综合党委">竹林社区综合党委</option>
							<option value="香岭社区综合党委">香岭社区综合党委</option>
						</select>
						<!-- <select id="selector" name="meetingperson" class="easyui-combotree" multiple style="width: 500px;"></select> -->
					</div>
				</div>
			</form>
			<!--//-->
			<div class="uk-modal-footer uk-text-center">
				<div class="uk-button-group">
					<button type="button" class="uk-button" id="pullcancel">取消</button>
					<button type="button" class="uk-button uk-button-success" id="pullsure">确定</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="<%=request.getContextPath() %>/frame/uikit/js/uikit.min.js"></script>
<script src="<%=request.getContextPath() %>/frame/icheck/icheck.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/frame/easyui/jquery.easyui.min.js"></script>
<script>
	jQuery(function(){
		
		var modal = UIkit.modal("#my-id",{center:true}); 			// 实例弹窗
		var pullModal = UIkit.modal("#pullmodal",{center:true});	// 推送弹窗
		var curMediaId;
		var sendMes;
		
		$('.preview').click(function(){
			modal.show();
			curMediaId = $(this).val(); // 取点击的那个按钮的那整个图文的id
			console.log(curMediaId);
		});
		
		/** 预览确定按钮 */
		$('#sure').on('click',function(){
			$.post('<%=request.getContextPath() %>/media/preview.do',
					{'media':curMediaId,'user':$('#chooseAdmin').val()},
					function(data){
						if(data=='ok'){
							alert('发送成功');
						}else{
							alert('发送失败');
						}
					});
			// 发送完关闭弹窗
			 modal.hide();
		});
		
		/** 取消按钮 */
		$('#cancel').on('click',function(){
			modal.hide();
		});

		/** icheck初始化 */
		$('input').iCheck({
			radioClass: 'iradio_square-green'
		}).on('ifChecked',function(event){
			sendMes = $(this).val();
		})

		/** 推送按钮 */
		$('#allpull').on('click',function(){
			if(sendMes!=null) pullModal.show(); 
			else alert('您未选择任何图文');
		});
		
		/**全员推送*/
		$('#allBtn').on('click',function(){
			if(sendMes!=null) {
				 $.post('<%=request.getContextPath()%>/message/all.do',
					{'mediaId':sendMes},
					function(data){
						if(data=='ok'){
						 	alert('发送成功');
						 }else{
						 	alert('发送失败');
						 }
							});
				}else alert('您未选择任何图文');
		});
		
		/**活跃人群推送*/
		$('#actiBtn').on('click',function(){
			if(sendMes!=null) {
			 $.post('<%=request.getContextPath()%>/message/actiUser.do',
				{'mediaId':sendMes},
				function(data){
					if(data=='ok'){
					 	alert('发送成功');
					 }else{
					 	alert('发送失败');
					 }
						});
			}else alert('您未选择任何图文');
		});
		
		/** 推送取消按钮 */
		$('#pullcancel').on('click', function(event) {
			pullModal.hide();
		});

		/** 推送按钮 */
		$('#pullsure').on('click', function(event) {
			console.log(sendMes);
			console.log($('#chooseGroup').val());
			//console.log($('#chooseAdmin').val());
			 $.post('<%=request.getContextPath()%>/message/organization.do',
					{'mediaId':sendMes,'organization':$('#chooseGroup').val()},
			 		function(data){
						if(data=='ok'){
			 				alert('发送成功');
			 			}else{
			 				alert('发送失败');
			 			}
				});

			// 发送完关闭弹窗
			 //modal.hide();
			 pullModal.hide();
		});
		
		// $('#selector').combotree({
		// 	url : '<%=request.getContextPath()%>/user/wechat/getWechatUserJson.do',
		// 	onlyLeafCheck : true
		// });
		// $("#selector").combotree('tree').tree('check', node.target);

	})
</script>
</html>