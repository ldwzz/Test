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
			<h1 class="page-header">回收站列表</h1>
			<div class="row placeholders">
				<div class="row placeholders">



					<!-- 搜索表单 -->
					<form
						action="${pageContext.request.contextPath}/recycle/list.action?pageNum=${page.pageNum}"
						method="post" name="search">
						<div class="pull-right form-inline">

							<select id="projNames" name="classId"
								style="height:32px;width:150px">

								<c:if test="${findAllProList!=null}">

									<c:forEach items="${findAllProList}" var="proList">
										<option value="${proList.classId}"
											${param.classId==proList.classId?"selected='selected'":""}>${proList.projName}</option>
									</c:forEach>
								</c:if>
							</select> <select id="projModel" name="classIds"
								style="height:32px;width:150px">
								<option value="0">--请选择--</option>
								<c:if test="${findSysClassByPidList!=null}">
									<c:forEach items="${findSysClassByPidList}" var="ByPidList">
										<option value="${ByPidList.classId }"
											${param.classIds==ByPidList.classId?"selected='selected'":""}>${ByPidList.className}</option>
									</c:forEach>
								</c:if>
							</select> <input type="text" name="intro" class="form-control form-inline"
								placeholder="描述" id="intro" value="${param.intro}"> <input
								type="date" name="createStartTime"
								class="form-control form-inline" id="createStartTime"
								value="${param.createStartTime}"> ---- <input
								type="date" name="createEndTime"
								class="form-control form-inline" id="createEndTime"
								value="${param.createEndTime}">
							<button type="button" class="btn btn-primary form-inline"
								id="searchInput">确定查询</button>
						</div>
					</form>
					<br />
					<div>
						<div class="space-div"></div>
						<table class="table table-hover table-bordered recycle-list"
							style="text-align: center;">
							<tr>
								<td>ID</td>
								<td>名称/描述</td>
								<td>创建时间</td>
								<td>创建人</td>
								<td>操作</td>
							</tr>

							<c:forEach items="${page.resultList}" var="recycle">
								<tr>
									<td class="recycleId">${recycle.recyId}</td>
									<td class="intro">${recycle.intro}</td>
									<td class="createTime">${recycle.createTime }</td>
									<td class="userName">${recycle.userName}</td>
									<td>
										<button type="button"
											class="btn btn-warning recover-this-recy">恢复</button>


										<button type="button" class="btn btn-success delete-this-recy">删除</button>

									</td>
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
					var startTime = $("#createStartTime").val();
					var endTime = $("#createEndTime").val();
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
			//删除回收站里面的某一条信息
			$(".recycle-list")
					.on(
							"click",
							".delete-this-recy",
							function() {
								var userTr = $(this).parents("tr");
								var recycleId = userTr.find(".recycleId")
										.html();
								/* 	var uid=${sessionScope.userInfo.userId};
									if(userid==uid){
										showTips("不能删除自己");
									}else{ */
								if (confirm('确认删除该用户吗？此操作将无法恢复！')) {
									//请求删除该信息
									$
											.ajax({
												type : "POST",
												dataType : "json",
												url : "${pageContext.request.contextPath}/recycle/deleteRecycle.action",
												data : {
													recyId : recycleId
												},
												success : function(json) {
													if (json.re == 1) {

														userTr.remove();
														showTips("删除成功！");
														location.reload();
													} else {
														showTips("删除失败！");
													}
												},
												error : function() {
													showTips("系统错误！");
												}
											});
								}

							});
			//恢复某一个删除的
			$(".recycle-list")
					.on(
							"click",
							".recover-this-recy",
							function() {
								var userTr = $(this).parents("tr");
								var recycleId = userTr.find(".recycleId")
										.html();
								/* 	var uid=${sessionScope.userInfo.userId};
									if(userid==uid){
										showTips("不能删除自己");
									}else{ */

								//请求删除该信息
								$
										.ajax({
											type : "POST",
											dataType : "json",
											url : "${pageContext.request.contextPath}/recycle/recoverData.action",
											data : {
												recyId : recycleId
											},
											success : function(json) {
												if (json.re == 1) {

													userTr.remove();
													location.reload();
												} else {
													showTips("恢复失败！");
												}
											},
											error : function() {
												showTips("系统错误！");
											}
										});

							});
			/* 	if(${fleg==null}){
				$(document).ready(function(){
			  $("#projNames").find("option").eq(1).attr("selected","selected");
				        var  projId=$("#projNames option:selected").val();
				        alert(projId)
				window.location.href="${pageContext.request.contextPath}/recycle/list.action?projId="+projId+"&flag=5"
				
				})
				     
				}
			 */

			//选中项目框时触发的事件
			$("#projNames")
					.change(
							function() {
								var classId = $("#projNames option:selected")
										.val();
								window.location.href = "${pageContext.request.contextPath}/recycle/list.action?classId="
										+ classId + ""
							})
			//选中模块之后触发事件
			$("#projModel")
					.change(
							function() {
								var classIds = $("#projModel option:selected")
										.val();
								var classId = $("#projNames option:selected")
										.val();
								window.location.href = "${pageContext.request.contextPath}/recycle/list.action?classIds="
										+ classIds
										+ "&classId="
										+ classId
										+ "&n=2"
							})
		</script>
</body>
</html>
