<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>站内信</title>

<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/static/css/layout.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/new_file.css" />
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="header.jsp" />

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<jsp:include page="navibar.jsp" />
			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="container">
					<h1 class="page-header">发件箱</h1>
					<table width="800px" height="650px"
						style="background-color:lightsteelblue;">
						<tr>
							<td>
								<button type="button" class="btn btn-primary send">发送</button>&nbsp;&nbsp;











								<form id="uploadForm">
									<input type="file" name="files" /> <input type="file"
										name="files" /> <input type="file" name="files" /> <input
										type="button" value="上传" onclick="doUpload()">
									<!-- <button type="submit">提交</button> -->
								</form> <input hidden="hidden" id="filePath">








							</td>


						</tr>
						<tr>
							<th>收件人&nbsp;<input type="text" name="UserNames"
								id="UserNames" style="width: 600px;height: 30px;"
								autocomplete="off" value="${replyUserNames}"> <input
								hidden="hidden" id="fromUser" name="fromUser"
								value="${userInfo.userId}"> <input hidden="hidden"
								id="toUserIds" name="toUserIds" value="${replyUserIds }">
								<input hidden="hidden" id="parentId" name="parentId"
								value="${message.userMessage.userMsgId }">
								<div id="context1"
									style="width: 120px;
								position: absolute;display:none;margin-left: 55px; background-color:gray;"></div>
							</th>
						</tr>
						<tr>
							<th>主题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
								name="msgTitle" id="msgTitle" style="width: 600px;height: 30px;"
								value=""></th>
						</tr>
						<tr>
							<th id="sa">正文&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
							<th id="top"><textarea name="msgContext" id="msgContext"
									style="border:2px solid #6CABE7;background-color:lavenderblush;
				height: 200px; width: 600px;"></textarea><br>
								<textarea name="" id=""
									style="border:2px solid #6CABE7;background-color:lavenderblush;
				height: 200px; width: 600px;">
---------------原始邮件--------------- 
发件人:${message.userMessage.fromName}
创建时间:${message.createTime}
收件人:${toUserNames }
标题:${message.msgTitle}

${message.msgContext}

<c:if test="${message.userMessage.parentId!=0}">
<c:forEach items="${list}" var="list">
---------------原始邮件---------------
发件人:${list.userMessage.fromName}
创建时间:${list.createTime}
收件人:${list.userMessage.parentToUserNames}
标题:${list.msgTitle}

${list.msgContext}


</c:forEach>
</c:if>
</textarea></th>

						</tr>
					</table>
				</div>
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
	<script type="text/javascript">
		//发送站内信
		$(".send")
				.click(
						function() {

							$
									.ajax({
										type : "POST",
										dataType : "json",
										url : "${pageContext.request.contextPath}/message/insertReplyMessage.action",
										data : {
											fromUser : $("#fromUser").val(),
											msgTitle : $("#msgTitle").val(),
											msgContext : $("#msgContext").val(),
											toUserIds : $("#toUserIds").val(),
											parentId : $("#parentId").val(),
											fileUrl : $("#filePath").val(),
										},
										success : function(data) {
											if (data.res > 0) {
												alert("发送成功！");
												window.history.back();
											} else {
												alert("发送失败！");
											}
										},
										error : function() {
											alert("系统错误！");
										}
									})

						})

		function doUpload() {
			var formData = new FormData($("#uploadForm")[0],
					$("#uploadForm")[1], $("#uploadForm")[2]);
			$
					.ajax({
						url : "${pageContext.request.contextPath}/message/upload.action",
						type : 'POST',
						dataType : "json",
						data : formData,
						async : false,
						cache : false,
						contentType : false,
						processData : false,
						success : function(data) {
							if (data.filePath==0) {
							alert("没有选择文件！")
							}else {
				 	$("#filePath").val(data.filePath)
                			alert("成功了！")
							} 
						},
						error : function(data) {
							alert("失败了！");
						}
					});
		}
	</script>
</body>
</html>



