<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>用户管理 - 角色列表</title>

<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/static/css/layout.css"
	rel="stylesheet">
<style type="text/css">
.red {
	color: red
}
</style>
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="header.jsp" />

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<jsp:include page="navibar.jsp" />
			</div>
		</div>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h1 class="page-header">日志列表</h1>
			<div class="row placeholders">
				<div class="row placeholders">



					<!-- 搜索表单 -->
					<form
						action="${pageContext.request.contextPath}/log_info/logInfoList.action?pageNum=${page.pageNum}"
						method="post" name="search">
						<div class="pull-right form-inline">

							<select id="projId" name="projId" style="height:32px;width:150px">
								<option value="-1">其他</option>
								<c:if test="${findAllProList!=null}">

									<c:forEach items="${findAllProList}" var="proList">
										<option value="${proList.projId}"
											${param.projId==proList.projId?"selected='selected'":""}>${proList.projName}</option>
									</c:forEach>
								</c:if>
							</select> <input type="text" name="logInfo"
								class="form-control form-inline" placeholder="日志内容" id="logInfo"
								value="${param.logInfo}" maxlength="20"> <input
								type="text" name="ipAddress" class="form-control form-inline"
								placeholder="ip" id="ipAddress" value="${param.ipAddress}"
								maxlength="20"> <input type="text" name="userName"
								class="form-control form-inline" placeholder="操作人" id="userName"
								value="${param.userName}" maxlength="10"> <input
								type="date" name="createStartTime"
								class="form-control form-inline" id="startTime"
								value="${param.createStartTime}"> ---- <input
								type="date" name="createEndTime"
								class="form-control form-inline" id="endTime"
								value="${param.createEndTime}">
							<button type="button" class="btn btn-primary form-inline"
								id="searchInput">确定查询</button>
							<a
								href="${pageContext.request.contextPath}/log_info/logInfoList.action?${page.params}&flag=0">
								<button type="button" class="btn btn-success" id="exportexcel">导出表格</button>
							</a>
						</div>
					</form>
					</br>



					<div>
						<div class="space-div"></div>
						<table class="table table-hover table-bordered recycle-list"
							style="text-align: center;">
							<tr>
								<td>ID</td>
								<td>ip</td>
								<td>url</td>
								<td>内容</td>
								<td>异常信息</td>
								<td>创建时间</td>
								<td>操作人</td>
							</tr>

							<c:forEach items="${page.resultList}" var="logInfos">
								<tr>
									<td class="logId">${logInfos.logId}</td>
									<td class="ip">${logInfos.ipAddress }</td>
									<td class="url">${logInfos.url }</td>
									<td class="intro">${logInfos.logInfo }</td>
									<td class="exception">${logInfos.exception }</td>
									<td class="createTime">${logInfos.createTime }</td>
									<td class="userName">${logInfos.userName}</td>
								</tr>
							</c:forEach>
						</table>
						<jsp:include page="standard.jsp" />
					</div>
				</div>
			</div>
		</div>
		<!-- 提示框 -->
		<div class="modal fade" id="op-tips-dialog" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">提示信息</h4>
					</div>
					<div class="modal-body" id="op-tips-content"></div>
				</div>
			</div>
		</div>
		<!-- Bootstrap core JavaScript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script
			src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
		<script>
			//时间验证
			$(function() {
				$("#searchInput").click(function() {
					var startTime = $("#startTime").val();
					var endTime = $("#endTime").val();
					if (!!startTime && !!endTime) {
						if (startTime > endTime) {
							alert("起始时间不能大于结束时间！");
							return false;
						} else {
							window.search.submit();
						}
					} else {
						window.search.submit();
					}
				})
			})
			//导出数据表格
			/* 		$("#exportexcel").click(function(){	
			 alert(${listsize});
			 //对页面有无数据进行判断
			 if(${listsize}>0){
			 var projName=$("#projName").val();
			 var logInfo=$("#logInfo").val();
			 var ipAddress=$("#ipAddress").val();
			 var userName=$("#userName").val();
			 var createStartTime=$("#createStartTime").val();
			 var createEndTime=$("#createEndTime").val();
			 alert(logInfo);
			 if(confirm("确认导出吗？")){
			 //showTips("已成功导出");
			 window.location.href="${pageContext.request.contextPath}/log_info/logInfoList.action?flag=0&projName="+projName+"&logInfo="+logInfo+"&ipAddress="+ipAddress+"&userName="+userName+"&createStartTime="+createStartTime+"&createEndTime="+createEndTime+"";			
			 }	
			 }else{
			 showTips("无符合条件数据");
			 }
			 }) */

			function ceshi() {

				window.location.href = "${pageContext.request.contextPath}/log_info/logInfoList.action?flag=3"
			}

			/* 		$(document).ready(function(){
			 setInterval("ceshi()",5000);
			 }); */

			//选中项目框时触发的事件
			$("#projId")
					.change(
							function() {
								var projId = $("#projId option:selected").val();
								window.location.href = "${pageContext.request.contextPath}/log_info/logInfoList.action?projId="
										+ projId + ""
							})
		</script>
</body>
</html>
