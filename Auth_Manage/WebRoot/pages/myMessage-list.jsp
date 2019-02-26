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

<title>站内信管理 - 我的站内信</title>

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
			<h1 class="page-header">我的站内信</h1>
			<div class="row placeholders">
				<div class="row placeholders">
					<form
						action="${pageContext.request.contextPath}/message/findMyMessage.action"
						method="post">
						<div class="pull-right form-inline">
							<input type="text" name="msgTitle"
								class="form-control form-inline" placeholder="邮件标题"
								id="msgTitle" value="${param.msgTitle}"> <select
								class="form-control form-inline" name="sendType" id="sendType">
								<option value="">站内信类型</option>
								<option value="1"
									${param.sendType =='1' ?"selected='selected'":""}>单发</option>
								<option value="2"
									${param.sendType =='2' ?"selected='selected'":""}>多发</option>
								<option value="3"
									${param.sendType =='3' ?"selected='selected'":""}>群发</option>
								<option value="4"
									${param.sendType =='4' ?"selected='selected'":""}>全发</option>
							</select> <select class="form-control form-inline" name="readState"
								id="readState">
								<option value="">站内信状态</option>
								<option value="0"
									${param.readState =='0' ?"selected='selected'":""}>未读</option>
								<option value="1"
									${param.readState =='1' ?"selected='selected'":""}>已读</option>
							</select> <input type="date" name="startTime"
								class="form-control form-inline" id="startTime"
								value="${param.startTime}" /> 至<input type="date"
								name="endTime" class="form-control form-inline" id="endTime"
								value="${param.endTime}" />
							<button type="submit" class="btn btn-primary form-inline">确定查询</button>
						</div>
					</form>
					<br />
					<div>
						<div>
							<a href="${pageContext.request.contextPath}/message/updateMyMessageState.action"><button type="button" class="btn btn-default"
								data-toggle="modal" >全部标为已读</button></a>


							<!--回复邮件表单-->
							<div class="modal fade " id="replyMess-form-div" tabindex="-1"
								role="dialog" aria-labelledby="mySmallModalLabel">
								<div class="modal-dialog modal-md" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="message-form-title"></h4>
										</div>
										<div class="modal-body">
											<form class="message-form">
												<div class="form-group">
													<label for="toUserInput">收件人</label> <input type="text"
														name="toUser" class="form-control" id="toUserInput"
														placeholder="收件人" maxlength="20">
												</div>
												<div class="form-group">
													<label for="titleInput">标题</label> <input type="text"
														name="msgTitle" class="form-control" id="titleInput"
														placeholder="标题" maxlength="20">
												</div>
												<div class="form-group">
													<label for="contextInput">内容</label><br>
													<textarea name="msgContext" id="contextInput"
														style="border:2px solid #6CABE7;background-color:lavenderblush;
															height: 250px; width: 570px;"></textarea>
												</div>
												<div class="form-group">
													<label for="fileInput">文件</label> <input type="file"
														name="fileUrl" id="fileInput"
										       		 maxlength="20"> 
												</div>
											</form>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
											<button type="button"
												class="btn btn-primary message-submit replyMess">确定</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="space-div"></div>
						<table class="table table-hover table-bordered message-list"
							style="text-align: center;">
							<tr>
								<td>ID</td>
								<td>标题</td>
								<td>发送类型</td>
								<td>状态</td>
								<td>创建时间</td>
								<td>发件人</td>
								<td>操作</td>
							</tr>
							<c:forEach items="${myMessages}" var="message">
								<tr>
									<td class="msgId">${message.msgId }</td>
									<td class="msgTitle">${message.msgTitle}</td>
									<td class="sendType"><c:if test="${message.sendType==1}">单发</c:if>
									<c:if test="${message.sendType==2}">多发</c:if>
									<c:if test="${message.sendType==3}">群发</c:if>
									<c:if test="${message.sendType==4}">全发</c:if>
									</td>

									<td><c:if test="${message.userMessage.readState==0}">
											<span style="color: red;">未读</span>
										</c:if> <c:if test="${message.userMessage.readState==1}">
											<span style="color: green;">已读</span>
										</c:if></td>

									<td class="createTime">${message.createTime}</td>
									<td>${message.userMessage.fromName}<input type="hidden"
										value="${message.userMessage.fromUser}">
									</td>
									<input type="hidden" value="${message.userMessage.toUser}">
									<input type="hidden" value="${message.userMessage.parentId}">
									<td>
									<a href="${pageContext.request.contextPath}/message/findMyMessageDetail.action?
									msgId=${message.msgId}&userMsgId=${message.userMessage.userMsgId}&parentId=${message.userMessage.parentId}">
										<button type="button" class="btn btn-primary see-message">查看详情</button>
										</a>
										<c:if test="${message.userMessage.readState==0}">
										<a href="${pageContext.request.contextPath}/message/updateMyMessageState.action?msgId=${message.msgId}">
										<button type="button" class="btn btn-primary update-messageState" value="">标为已读</button></a>
										</c:if>
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

		</script>
</body>
</html>
