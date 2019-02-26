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

<title>项目任务表</title>
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

.update-userrole-form input {
	margin: 15px 18px 15px 3px;
}

.user-form input {
	margin: 0 18px 0 3px;
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
			<h1 class="page-header">项目任务列表</h1>
			<div class="row placeholders">
				<form
					action="${pageContext.request.contextPath}/proj/list.action?projId=${sessionScope.projId}"
					method="post" name="search">
					<div class="pull-right form-inline">

						<input type="text" name="taskName"
							class="form-control form-inline" placeholder="任务名称" id="1"
							value="${param.taskName}"> <input type="text"
							name="excutorName" class="form-control form-inline"
							placeholder="执行人" id="2" value="${param.excutorName}"> <input
							type="text" name="classIdName" class="form-control form-inline"
							placeholder="模块" id="3" value="${param.classIdName}">
						<button type="submit" class="btn btn-primary form-inline"
							id="searchInput">确定查询</button>
					</div>
				</form>
				<br />
				<div>

					<!--  删除所选对话框 -->
					<div class="modal fade " id="delete-confirm-dialog" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">警告</h4>
								</div>
								<div class="modal-body">确认删除所选用户吗</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button"
										class="btn btn-primary delete-selected-confirm">确认</button>
								</div>
							</div>
						</div>
					</div>

					<!--任务修改表单-->

					<div class="modal fade " id="update-proj-form" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">任务修改</h4>
								</div>
								<div class="modal-body">

									<h4>任务名称：</h4>
									<input type="hidden" id="stateid"> <input
										readonly="readonly" class="form-control" id="assignUserName" />
									<form class="user-form">
										<div class="form-group add-user">
											<div class="form-group">
												<label for="descInput">任务状态</label> <select name="createBy"
													class="form-control" id="upState"
													style="margin: 15px 18px 15px 3px;">

													<option value="0">未开始</option>
													<option value="1">进行中</option>
													<option value="2">已暂停</option>
													<option value="3">已完成</option>
													<option value="4">已关闭</option>
												</select> <label for="InputStartTime">开始时间</label> <input type="date"
													name="startTime" class="form-control form-inline"
													id="upStartTime" value="${projTask.startTime}"
													style="margin-bottom:15px;" /> <label for="InputEndTime">结束时间</label>
												<input type="date" name="endTime"
													class="form-control form-inline" id="upEndTime"
													value="${projTask.endTime}" style="margin-bottom:15px;" />
												<label for="InputStartTime">计划时间</label> <input type="text"
													name="startTime" class="form-control form-inline"
													id="upPlanTime" value="${projTask.planTime}"
													style="margin-bottom:15px;" /> <label for="InputEndTime">实际消耗时间</label>
												<input type="text" name="endTime"
													class="form-control form-inline" id="upFinishTime"
													value="${projTask.finishTime}" style="margin-bottom:15px;" />
												<label for="InputEndTime">实际进度</label> <input type="number"
													max="100" id="upProcess" value="${projTask.process}">

											</div>
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary up-user-submit">确定
									</button>
								</div>
							</div>
						</div>
					</div>

					<!--任务添加表单-->

					<div class="modal fade " id="add-proj-form" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">任务添加</h4>
								</div>
								<div class="modal-body">
									<form class="user-form">
										<div class="form-group add-user">
											<div class="form-group">
												<label for="descInput">任务名称</label> <input type="text"
													name="usernames" class="form-control addUserNameInput"
													id="addTaskNameInput" placeholder="任务名称" maxlength="20">
											</div>
											<div class="form-group">
												<label for="descInput">任务描述</label> <input type="text"
													name="usernames" class="form-control addUserNameInput"
													id="addTaskDescInput" placeholder="任务描述" maxlength="20">
											</div>

											<div class="form-group">
												<label for="descInput">所属模块</label> <select name="createBy"
													class="form-control" id="addClass"
													style="margin: 15px 18px 15px 3px;">
													<option value="">所属模块</option>
													<c:forEach items="${listClass}" var="cl">
														<option value="${cl.classId}">${cl.className}</option>
													</c:forEach>
												</select>

											</div>
											<div class="form-group">
												<label for="descInput">执行人</label> <select name="createBy"
													class="form-control" id="addCreateBy1"
													style="margin: 15px 18px 15px 3px;">
													<option value="">执行人</option>

													<option value="${sessionScope.userInfo.userId}"></option>

												</select>
											</div>
											<div class="form-group">
												<label for="descInput">添加人</label> <select name="createBy"
													class="form-control" id="addCreateBy"
													style="margin: 15px 18px 15px 3px;">
													<option value="">添加人</option>
													<option value="${sessionScope.userInfo.userId}">${sessionScope.userInfo.userName}</option>
												</select>
											</div>
											<div class="form-group">
												<label for="descInput">备注</label> <input type="text"
													name="usernames" class="form-control addUserNameInput"
													id="addRemarkInput" placeholder="备注" maxlength="20">
											</div>
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary add-user-submit">添加
									</button>
								</div>
							</div>
						</div>
					</div>



				</div>
				<div class="space-div"></div>

				<c:if
					test="${!fn:contains(Rolecodes,'project_member')}">
					<button type="button"
						class="btn btn-primary show-user-form  findState"
						data-toggle="modal" data-target="#add-proj-form" id="findState">任务添加</button>
				</c:if>
				<table class="table table-hover table-bordered user-list"
					style="text-align: center;">
					<tr>

						<td>任务id</td>
						<td>任务名称</td>
						<td>任务描述</td>
						<td>执行人</td>
						<td>任务状态</td>
						<td>创建时间</td>
						<td>创建人</td>
						<td>所属模块</td>
						<td>工时</td>
						<td>开始时间</td>
						<td>结束时间</td>
						<td>计划时间</td>
						<td>实际消耗时间</td>
						<td>实际进度</td>
						<td>备注</td>
						<td>操作</td>
					</tr>
					<c:forEach items="${page.resultList}" var="projtask">
						<tr>
							<td class="taskId">${projtask.taskId }</td>
							<td class="taskName">${projtask.taskName}</td>
							<td class="taskDesc">${projtask.taskDesc}</td>
							<td class="excutor">${projtask.excutorName}</td>
							<td class="taskState"><c:if test="${projtask.taskState==0}">未开始</c:if>
								<c:if test="${projtask.taskState==1}">进行中</c:if> <c:if
									test="${projtask.taskState==2}">已暂停</c:if> <c:if
									test="${projtask.taskState==3}">已完成</c:if> <c:if
									test="${projtask.taskState==4}">已关闭</c:if></td>
							<input type="hidden" id="classhidden" value="${projtask.classId}" />
							<td class="createTime">${projtask.createTime}</td>
							<td class="createBy">${projtask.createByName}</td>
							<td class="classId">${projtask.classIdName}</td>
							<td class="ableDay">${projtask.ableDay}</td>
							<td class="startTime">${projtask.startTime}</td>
							<td class="endTime">${projtask.endTime}</td>
							<td class="planTime">${projtask.planTime}</td>
							<td class="finishTime">${projtask.finishTime}</td>
							<td class="process">${projtask.process}%</td>
							<td class="remark">${projtask.remark}</td>
							<td>

								<button type="button" class="btn btn-primary show-user-form"
									data-toggle="modal" data-target="#update-proj-form" id="tasids"
									value="${projtask.taskId}">任务修改</button> <c:if
									test="${rolecode=='admin'||rolecode=='projectLeader'||rolecode=='projectManager'}">

									<button type="button"
										class="btn btn-primary delete-selected-confirm" id="delProj">任务删除</button>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
				<jsp:include page="standard.jsp" />
				<div class="modal fade " id="update-userrole-dialog" tabindex="-1"
					role="dialog" aria-labelledby="mySmallModalLabel">
					<div class="modal-dialog modal-sm" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title">修改用户信息</h4>
							</div>
							<div class="modal-body">
								<form class="update-userrole-form form-group"
									name="updateuserinfo" action="" method="post">
									<input name="userId" type="hidden" class="form-control" /> 用户名<input
										name="username" type="text" class="form-control"
										readonly="readonly" /> 昵称<input name="nickName" type="text"
										class="form-control" maxlength="20" /> 密码(如果密码为空，则不修改密码)<input
										name="userPwd" type="password" class="form-control"
										id="inputPassword" maxlength="10" /> <label
										id="inputPasswordError"></label><br> 修改人<select
										name="updateBy" class="form-control">
										<option value="">请选择</option>
										<option value="${sessionScope.userInfo.userId}">${sessionScope.userInfo.userName}</option>
									</select> 修改时间<input name="updateTime" type="date" class="form-control" />
									<div class="roles-div"></div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
								<button type="button"
									class="btn btn-primary update-userrole-submit"
									id="update-user-info">提交</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 提示框 -->
	<div class="modal fade" id="op-tips-dialog" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
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
		var passflag = false;
		var userflag = false;

		//添加用户
		$(".add-user-submit")
				.click(
						function() {
							//${sessionScope.projId}a${projId}a${"projId"}
							var projId = ${projId};//项目ID
							var taskName = $("#addTaskNameInput").val();//任务名称
							var taskDesc = $("#addTaskDescInput").val();//任务描述
							var classId = $("#addClass").val();//所属模块
							var createBy = $("#addCreateBy").val(); //创建人remark
							var remark = $("#addRemarkInput").val();//备注	
							var excutor = $("#addCreateBy1").val();
							//请求添加新用户
							if (!!createBy && !!taskDesc && !!classId) {

								$
										.ajax({
											type : "POST",
											dataType : "json",
											url : "${pageContext.request.contextPath}/proj/insetProjTask.action",
											data : {
												projId : projId,
												taskName : taskName,
												taskDesc : taskDesc,
												classId : classId,
												remark : remark,
												excutor : excutor,
												createBy : createBy
											},
											success : function(data) {

												showTips("添加成功！");
												location.reload();

											},
											error : function() {
												showTips("系统错误");
											}
										})
							}
						});

		//修改项目任务状态

		$(function() {
			$(".up-user-submit")
					.click(
							function() {
								var startTime = $("#upStartTime").val();
								var endTime = $("#upEndTime").val();
								var taskState = $("#upState").val();
								var stateId = $("#stateid").val();
								var planTime = $("#upPlanTime").val();
								var finishTime = $("#upFinishTime").val();
								var process = $("#upProcess").val();
								$("#update-proj-form").modal("hide");

								$
										.ajax({
											type : "POST",
											dataType : "json",
											url : "${pageContext.request.contextPath}/proj/updateProjState.action",
											data : {
												planTime : planTime,
												finishTime : finishTime,
												process : process,
												startTime : startTime,
												endTime : endTime,
												taskState : taskState,
												taskId : stateId
											},
											success : function(data) {
												alert("修改成功！");

												window.location.href = "${pageContext.request.contextPath}/proj/list.action?projId="
														+ ${param.projId};

											},
											error : function() {
												showTips("系统错误！");
											}
										})
							})
		})
		//修改div弹框name显示
		$(function() {
			$(".show-user-form").click(
					function() {

						var taskName = $(this).parents("tr").find(".taskName")
								.html();
						$("#assignUserName").val(taskName);
						var stateId = $(this).parents("tr").find(".taskId")
								.html();
						$("#stateid").val(stateId);
						var taskState = $(this).parents("tr")
								.find(".taskState").html();
						$("#upState").val(taskState);
						var startTime = $(this).parents("tr")
								.find(".startTime").html();
						$("#upStartTime").val(startTime);
						var endTime = $(this).parents("tr").find(".endTime")
								.html();
						$("#upEndTime").val(endTime);
						var planTime = $(this).parents("tr").find(".planTime")
								.html();
						$("#upPlanTime").val(planTime);
						var finishTime = $(this).parents("tr").find(
								".finishTime").html();
						$("#upFinishTime").val(finishTime);
						var Process = $(this).parents("tr").find(".process")
								.html();
						$("#upProcess").val(Process);

					})
		})

		function showTips(content) {
			$("#op-tips-content").html(content);
			$("#op-tips-dialog").modal("show");
		}

		//查询执行人
		$("#findState")
				.on(
						"click",
						function() {

							$
									.ajax({
										type : "POST",
										dataType : "json",
										url : "${pageContext.request.contextPath}/proj/findProjState.action",

										success : function(json) {
											$("#addCreateBy1").html(json.names);

										},
										error : function() {
											showTips("系统错误！");
										}
									});
						});

		//删除项目任务 delProj
		$("#delProj")
				.on(
						"click",
						function() {
							var keyValue = $(this).parents("tr")
									.find(".taskId").html();
							var taskDesc = $(this).parents("tr").find(
									".taskDesc").html();

							var classId = $("#classhidden").val();

							$
									.ajax({
										type : "POST",
										dataType : "json",
										url : "${pageContext.request.contextPath}/proj/delProjState.action",
										data : {
											dbUser : "autho_manage",
											classId : classId,
											tableName : "proj_task",
											keyName : "task_id",
											intro : taskDesc,
											keyValue : keyValue
										},
										success : function(json) {

											$("#addCreateBy1").html(json.names);
											location.reload();
										},
										error : function() {
											showTips("系统错误！");
										}
									});
						});

		/* $(".user-list").on("click",".show-user-form",
						function(obj) { 
						var id = $(this).parents("tr").find(".taskId").html();
					
							$.ajax({
										type : "POST",
										dataType : "json",
										url : "${pageContext.request.contextPath}/proj/projTaskById.action",
		                               data:{
		                                id:id
		                                 },
										success : function(json) {
											$("#InputProcess1").html(json.ss);
											
		                         
										},
										error : function() {
											showTips("系统错误！");
										}
									});
						}); */
	</script>
</body>
</html>
