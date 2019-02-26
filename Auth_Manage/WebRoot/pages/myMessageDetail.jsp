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

<title>站内信管理 - 我的站内信详情</title>

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
			<h1 class="page-header">我的站内信详情</h1>
			<div class="row placeholders">
		<a href="${pageContext.request.contextPath}/message/findMyMessageParent.action?
		userMsgId=${message.userMessage.userMsgId}&sendType=1&toUserNames=${toUserNames }
		&parentId=${message.userMessage.parentId}">
		<button type="button" class="btn btn-primary reply-message" value="">回复</button>
		</a>
		<a href="${pageContext.request.contextPath}/message/findMyMessageParent.action?
		userMsgId=${message.userMessage.userMsgId}&sendType=2&toUserNames=${toUserNames }
		&parentId=${message.userMessage.parentId}">
		<c:if test="${message.sendType!=1}">
		<button type="button" class="btn btn-primary reply-allMessage" value="">全部回复</button>
		</c:if></a><br><br><br>	
		<b>发件人:</b>${message.userMessage.fromName}<br>
		<b>创建时间:</b>${message.createTime}<br>
		<b>收件人:</b>${toUserNames }<br>
		<b>标题:</b>${message.msgTitle}<br>
		<br>
		${message.msgContext}<br>
		<br><br><br>
		<c:if test="${message.fileUrl!=null && message.fileUrl!=''}">
		<b>附件:点击下载</b><br>
		<c:forEach items="${files}" var="file">
		<a href="${pageContext.request.contextPath}/message/download.action?fileName=${file}">
		${file}<br></a>
		</c:forEach>
		</c:if>
		<br><br><br>
	    <c:if test="${message.userMessage.parentId!=0}">
	    <c:forEach items="${list}" var="list">
		---------------原始邮件---------------
		<br>
		<b>发件人:</b>${list.userMessage.fromName}<br>
		<b>创建时间:</b>${list.createTime}<br>
		<b>标题:</b>${list.msgTitle}<br>
		<b>收件人:</b>${list.userMessage.parentToUserNames}<br><br>
		${list.msgContext}
		<br><br><br><br>
		</c:forEach>
		</c:if>
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
