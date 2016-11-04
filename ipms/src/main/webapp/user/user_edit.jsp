<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/components/datepicker.almost-flat.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/frame/uikit/css/components/form-select.almost-flat.min.css">
</head>
<body class="uk-height-1-1">
	<div class="uk-panel uk-panel-box">
		<ul class="uk-breadcrumb">
			<li><i class="uk-icon-home"></i> <a href="<%=request.getContextPath()%>/index.jsp">首页</a></li>
			<li><a href="<%=request.getContextPath()%>/user/getAllUser.do">党员管理</a></li>
			<li class="uk-active">编辑党员</li>
		</ul>
		<hr>
		<form class="uk-form uk-form-horizontal myform" name="myform" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${user.id }"> 
			<div class="uk-grid uk-grid-divider">
				<div class="uk-width-1-2">
					<div class="uk-form-row">
						<label class="uk-form-label">姓名<span style="color:red"> *</span></label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="name" value= "${user.name}" placeholder="您的名称">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">曾用名</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="formerName" value= "${user.formerName}" placeholder="曾用名">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">性别</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="sex" value= "${user.sex}"  placeholder="性别自动根据身份证获取">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">民族</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="nation" value= "${user.nation}" id="nation">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">籍贯</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="natives"  value= "${user.natives}">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">出生地</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="placeOfBirth" value= "${user.placeOfBirth}">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">户口所在地址</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="registeredResidence" value= "${user.registeredResidence}">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">家庭地址</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="address" value= "${user.address}">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">手机号码<span style="color:red"> *</span></label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="tel" value= "${user.tel}">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">身份证号<span style="color:red"> *</span></label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="idCard" id="idCard" placeholder="身份证号" value= "${user.idCard}">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">个人身份</label>
						<div class="uk-form-controls">
							<select name="careerID" id="careerID">
								<option value="">请选择</option>
							</select> 
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">学历</label>
						<div class="uk-form-controls">
							<select name="edcation" id="edcation">
								<option value="">请选择</option>
							</select>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">毕业院校</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="postEdcation" value= "${user.postEdcation}">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">学位</label>
						<div class="uk-form-controls">
							<select name="degree" id="degree">
								<option value="">请选择</option>
							</select>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">专业</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="major" value= "${user.major}">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">参加工作时间</label>
						<div class="uk-form-controls">
							<div class="uk-form-icon">
								<i class="uk-icon-calendar"></i><input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="dateOfParticipatingInWork" placeholder="YYYY-MM-DD" value='<fmt:formatDate value="${user.dateOfParticipatingInWork }" pattern="yyyy-MM-dd"/>'   readonly/>
							</div>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">工作单位</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="job" value= "${user.job}">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">联系电话</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="otherTel" value= "${user.otherTel}">
						</div>
					</div>
					
					<div class="uk-form-row">
						<label class="uk-form-label">电子邮箱</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="email" value= "${user.email}">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">工作地址</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="workingLocation" value= "${user.workingLocation}">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">所属行业</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="industry" value= "${user.industry}">
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">单位属性</label>
						<div class="uk-form-controls">
							<select name="attribute" id="attribute">
								<option value="">请选择</option>
							</select>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">职务名称</label>
						<div class="uk-form-controls">
							<div class="uk-form-icon uk-form-icon-flip">
								<input type="text" size="30" name="position" value= "${user.position}">
							</div>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">技术职务</label>
						<div class="uk-form-controls">
							<div class="uk-form-icon uk-form-icon-flip">
								<input type="text" size="30"  name="technicalPosition" value= "${user.technicalPosition}">
							</div>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">备注</label>
						<div class="uk-form-controls">
							<input type="text" size="30" name="rmk" value= "${user.rmk}">
						</div>
					</div>
				</div>
				<!-- 右边 -->
				<div class="uk-width-1-2">
					<!-- 头像 upload -->
					<div class="uk-form-row">
						<label class="uk-form-label">头像</label>
						<div class="uk-form-controls">
							<div class="preload uk-margin-top">
							<c:choose>
							    <c:when test="${empty user.picUrl}">
							      <img src="<%=request.getContextPath()%>/img/preload.png" id="preview" width="160" height="80">
							    </c:when>
							    <c:otherwise>
							      <img src="${user.picUrl}" id="preview" width="160" height="80">
							    </c:otherwise>
							    </c:choose>
							</div>
							<div class="uk-form-file uk-margin-small-top">
								<button class="uk-button">上传头像</button>
								<input type="file" name="file" id="doc" onchange="javascript:setImagePreview();"> <small class="uk-form-help-inline uk-text-muted"> 头像尺寸建议为160px * 160px </small>
							</div>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">原党组织</label>
						<div class="uk-form-controls">
							<div class="uk-form-icon uk-form-icon-flip">
								<input type="text"  size="30" name="originalPartyOrg" value= "${user.originalPartyOrg}">
							</div>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">所在党委<span style="color:red"> *</span></label>
						<div class="uk-form-controls">
							<select name="organization" id="organization" onchange="proChange(this.value)">
	                           <option value="">请选择党委</option>
							</select>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">党员转入时间<span style="color:red"> *</span></label>
						<div class="uk-form-controls">
							<div class="uk-form-icon">
								<i class="uk-icon-calendar"></i><input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="turnInDate"  placeholder="YYYY-MM-DD"  value='<fmt:formatDate value="${user.turnInDate }" pattern="yyyy-MM-dd"/>'  />
							</div>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">所在党支部<span style="color:red"> *</span></label>
						<div class="uk-form-controls">
							<select name="branch" id="branch">
								<option value="">请选择支部</option>
							</select>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">入党时间<span style="color:red"> *</span></label>
						<div class="uk-form-controls">
							<div class="uk-form-icon">
								<i class="uk-icon-calendar"></i><input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="inDate" placeholder="YYYY-MM-DD"  value='<fmt:formatDate value="${user.inDate }" pattern="yyyy-MM-dd"/>'  required  />
							</div>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">党费<span style="color:red"> *</span></label>
						<div class="uk-form-controls">
							<input type="text" name="dues" value= "${user.dues}">元/月
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">党费截止日期<span style="color:red"> *</span></label>
						<div class="uk-form-controls">
							<div class="uk-form-icon">
								<i class="uk-icon-calendar"></i><input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="duesvaliddate" placeholder="YYYY-MM-DD"   value='<fmt:formatDate value="${user.duesvaliddate }" pattern="yyyy-MM-dd"/>' />
							</div>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">状态<span style="color:red"> *</span></label>
						<div class="uk-form-controls">
							<select name="status" id="status">
							</select>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">入党介绍人</label>
						<div class="uk-form-controls">
							<div class="uk-form-icon uk-form-icon-flip">
								<input type="text" size="30"  name="partyIntroducer" value= "${user.partyIntroducer}">
							</div>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">转正时间</label>
						<div class="uk-form-controls">
							<div class="uk-form-icon">
								<i class="uk-icon-calendar"></i><input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="qualifiedDate" placeholder="YYYY-MM-DD"   value='<fmt:formatDate value="${user.qualifiedDate }" pattern="yyyy-MM-dd"/>'  />
							</div>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">转正情况</label>
						<div class="uk-form-controls">
							<select name="qualifiedStatus" id="qualifiedStatus">
								<option value="">请选择</option>
							</select>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">发展类型</label>
						<div class="uk-form-controls">
							<select name="recruitType" id="recruitType">
								<option value="">请选择</option>
							</select>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">新阶层</label>
						<div class="uk-form-controls">
							<select name="newClass" id="newClass">
								<option value="">请选择</option>
							</select>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">一线情况</label>
						<div class="uk-form-controls">
							<select name="industryStatus" id="industryStatus">
								<option value="">请选择</option>
							</select>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">退休时间</label>
						<div class="uk-form-controls">
							<div class="uk-form-icon">
								<i class="uk-icon-calendar"></i><input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="retiredDate" placeholder="YYYY-MM-DD"  value='<fmt:formatDate value="${user.retiredDate }" pattern="yyyy-MM-dd"/>'   readonly/>
							</div>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">离职时间</label>
						<div class="uk-form-controls">
							<div class="uk-form-icon">
								<i class="uk-icon-calendar"></i><input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="leaveDate" placeholder="YYYY-MM-DD" value='<fmt:formatDate value="${user.leaveDate }" pattern="yyyy-MM-dd"/>'    readonly/>
							</div>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">离岗原因</label>
						<div class="uk-form-controls">
							<div class="uk-form-icon uk-form-icon-flip">
								<input type="text" size="30" name="leaveReason" value= "${user.leaveReason}">
							</div>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">支部书记</label>
						<div class="uk-form-controls">
							<div class="uk-form-icon uk-form-icon-flip">
								<input type="text" size="30" name="branchSecretary"  value= "${user.branchSecretary}">
							</div>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">支部书记电话</label>
						<div class="uk-form-controls">
							<div class="uk-form-icon uk-form-icon-flip">
								<input type="text" size="30" name="branchSecretaryTel" value= "${user.branchSecretaryTel}">
							</div>
						</div>
					</div>
					<div class="uk-form-row">
						<label class="uk-form-label">是否党代表</label>
						<div class="uk-form-controls">
							<select name="ddb" id="ddb">
							</select> 
						</div>
					</div>
				</div>
				<div class="uk-width-1-1 uk-margin-top">
					<button type="submit" class="uk-button uk-button-success uk-width-1-1">确定</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script src="<%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/uikit.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/validation/jquery.validate.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/validation/messages_zh.js"></script>
<script src="<%=request.getContextPath()%>/frame/validation/additional-methods.js"></script>
<script src="<%=request.getContextPath()%>/frame/uikit/js/components/form-select.min.js"></script>
<script src="<%=request.getContextPath()%>/frame/DatePicker/WdatePicker.js"></script>
<script src="<%=request.getContextPath()%>/js/IdCard.js"></script>
<script type="text/javascript">
$.ajax({
		type : "post",
		url : "<%=request.getContextPath()%>/sysdictionary/getOrgId.do",
		dataType : "json",
		success : function(data) {
			$.each(data, function(i,data) {
				$('#organization').append($("<option/>", {
					value : data,
					text : data
				}));
			});
			$("#organization>option[value=${user.organization}]").attr("selected",true);
			proChange('${user.organization}') ;
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
	
	function proChange(objVal) {
		document.getElementById("branch").innerHTML = "";
		$.ajax({
			type : "post",
			url : "<%=request.getContextPath()%>/sysdictionary/getBranchWithoutSys.do?org="
					+ encodeURI(encodeURI(objVal)),
			dataType : "json",
			success : function(data) {
				$.each(data, function(i,data) {
				 $('#branch').append($("<option/>", {
					value : data,
					text : data
				}));
			  });
				$("#branch>option[value=${user.branch}]").attr("selected",true);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	}
	
	$.ajax({
		type : "post",
		url : "<%=request.getContextPath()%>/sysdictionary/getUserStatus.do",
		dataType : "json",
		success : function(data) {
			$.each(data, function(i,data) {
				$('#status').append($("<option/>", {
					value : data,
					text : data
				}));
			});
			$("#status>option[value=${user.status}]").attr("selected",true);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
	
	$.ajax({
		type : "post",
		url : "<%=request.getContextPath()%>/sysdictionary/getEducation.do",
		dataType : "json",
		success : function(data) {
			$.each(data, function(i,data) {
				$('#edcation').append($("<option/>", {
					value : data,
					text : data
				}));
			});
			$("#edcation>option[value=${user.edcation}]").attr("selected",true);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
	$.ajax({
		type : "post",
		url : "<%=request.getContextPath()%>/sysdictionary/getCareerID.do",
		dataType : "json",
		success : function(data) {
			$.each(data, function(i,data) {
				$('#careerID').append($("<option/>", {
					value : data,
					text : data
				}));
			});
			$("#careerID>option[value=${user.careerID}]").attr("selected",true);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
$.ajax({
		type : "post",
		url : "<%=request.getContextPath()%>/sysdictionary/getNewClass.do",
		dataType : "json",
		success : function(data) {
			$.each(data, function(i,data) {
				$('#newClass').append($("<option/>", {
					value : data,
					text : data
				}));
			});
			$("#newClass>option[value=${user.newClass}]").attr("selected",true);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
$.ajax({
		type : "post",
		url : "<%=request.getContextPath()%>/sysdictionary/getQualifiedStatus.do",
		dataType : "json",
		success : function(data) {
			$.each(data, function(i,data) {
				$('#qualifiedStatus').append($("<option/>", {
					value : data,
					text : data
				}));
			});
			$("#qualifiedStatus>option[value=${user.qualifiedStatus}]").attr("selected",true);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
$.ajax({
		type : "post",
		url : "<%=request.getContextPath()%>/sysdictionary/getRecruitType.do",
		dataType : "json",
		success : function(data) {
			$.each(data, function(i,data) {
				$('#recruitType').append($("<option/>", {
					value : data,
					text : data
				}));
			});
			$("#recruitType>option[value=${user.recruitType}]").attr("selected",true);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
$.ajax({
		type : "post",
		url : "<%=request.getContextPath()%>/sysdictionary/getIndustryStatus.do",
		dataType : "json",
		success : function(data) {
			$.each(data, function(i,data) {
				$('#industryStatus').append($("<option/>", {
					value : data,
					text : data
				}));
			});
			$("#industryStatus>option[value=${user.industryStatus}]").attr("selected",true);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
$.ajax({
		type : "post",
		url : "<%=request.getContextPath()%>/sysdictionary/getAttribute.do",
		dataType : "json",
		success : function(data) {
			$.each(data, function(i,data) {
				$('#attribute').append($("<option/>", {
					value : data,
					text : data
				}));
			});
			$("#attribute>option[value=${user.attribute}]").attr("selected",true);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
$.ajax({
		type : "post",
		url : "<%=request.getContextPath()%>/sysdictionary/getDegree.do",
		dataType : "json",
		success : function(data) {
			$.each(data, function(i,data) {
				$('#degree').append($("<option/>", {
					value : data,
					text : data
				}));
			});
			$("#degree>option[value=${user.degree}]").attr("selected",true);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
	
$.ajax({
	type : "post",
	url : "<%=request.getContextPath()%>/sysdictionary/getDdb.do",
	dataType : "json",
	success : function(data) {
		$.each(data, function(i,data) {
			$('#ddb').append($("<option/>", {
				value : data,
				text : data
			}));
		});
		$("#ddb>option[value=${user.ddb}]").attr("selected",true);
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
			localImagId.style.width = "160px";
			localImagId.style.height = "160px";
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
</script>
<script>
	$(function(){
		$('.myform').validate({
			errorClass:'error',
			success:'valid',
			rules: {
				name:{ required:true, chiness:true },
				idCard:{ required:true,	idcard:true },
				tel:{ required:true,	telephone:true },
				organization:{required:true},
				branch:{ required:true },
				inDate:{ required:true },
				dues:{ required:true,number:true },
				status:{ required:true }
			},
			submitHandler:function(){
				document.myform.action = "<%=request.getContextPath()%>/user/updateUser.do";
				document.myform.submit();
			}
		});
	})
</script>
</html>