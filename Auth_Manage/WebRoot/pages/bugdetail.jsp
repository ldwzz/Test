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
<style type="text/css">
th,td {
	text-align: center;
}
.tablehead {
	text-align: center;
}
</style>
<title>项目管理 - 项目动态</title>
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/static/css/bug.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/static/css/layout.css"
	rel="stylesheet">
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
			<h1 class="page-header">BUG详情</h1>
			<div class="row placeholders">
				<div class="detsal">项目名：${detail.projName}</div>
				<div class="detsal">模块名：${detail.className}</div>
				<div class="detsal">BUG名称：${detail.bugTitle}</div>
				<div class="detsal">BUG编号：${detail.bugNum}</div>
				<div class="detsal">BUG描述：${detail.bugDesc}</div>
				<div class="detsal">BUG等级：${detail.bugLevel==0?'普通':(detail.bugLevel==1?'严重':'缺陷')}</div>
				<div class="detsal">BUG状态：${detail.bugState==0?'新建':(detail.bugState==1?'已分配':(detail.bugState==2?'已解决':'已关闭'))}</div>
				<div class="detsal">BUG创建时间：${detail.createTime}</div>
				<div class="detsal">BUG创建者：${detail.creator}</div>
				<div class="detsal">BUG执行者：${detail.executor}</div>
				<div>
					<c:if test="${detail.fileUrl != null && detail.fileUrl != ''}">
						附件：<br/><img src="${pageContext.request.contextPath}/${detail.fileUrl}" width="320px" height="150px" />
					</c:if>
				</div>
				<br />
				<div>
					<button type="button" class="btn btn-secondary" data-toggle="modal"
						data-target="#addcomment">回复</button>
					<hr>
					<div class="card">
						<c:forEach items="${bugmake}" var="comment">
							<div class="list-group">
								<h4 class="list-group-item-heading" style="display:inline;">
								${comment.nickName}
								<c:if test="${comment.fatherName != null}">
									&nbsp;回复&nbsp;${comment.fatherName}
								</c:if>
								：&nbsp;&nbsp;${comment.replyContent}</h4>
								${comment.createTime}&nbsp;&nbsp;&nbsp;&nbsp; 
								<input type="hidden" class="replyid" value="${comment.replyId}" />
								<button type="button" class="delete_fuson">删除</button>
								<button type="button" class="add_fuson" data-toggle="modal"
									data-target="#addcomment">回复</button>
							</div>
						</c:forEach>
					</div>
				</div>
				
				<!--评论-->
				<div class="modal fade " id="addcomment" tabindex="-1" role="dialog"
					aria-labelledby="mySmallModalLabel">
					<div class="modal-dialog modal-sm" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title">回复</h4>
							</div>
							<div class="modal-body">
								<form class="user-form">
									<div class="form-group add-user">
										<textarea cols="35" rows="10" placeholder="在这里输入内容..."
											id="commentcontent"></textarea>
										<input type="hidden" value="0" id="parentId" /> 
										<input type="hidden" value="${userInfo.userId}" id="bugpinglun" />
										<input type="hidden" value="${detail.bugId}" id="bugpinglunids" />
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
								<button type="button" class="add_bugremake">发表</button>
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
</body>

<script type="text/javascript">
	$().ready(function() {
		$(".add_bugremake").click(function() {
			var creatBy = $("#bugpinglun").val();
			var bugids = $("#bugpinglunids").val();
			var content = $("#commentcontent").val();
			var pid = 0 + $("#parentId").val();
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${pageContext.request.contextPath}/bug/addremake.action",
				data : {
					replyContent : content,
					createBy : creatBy,
					bugId : bugids,
					parentId : pid
				},
				success : function(data) {
					if (data.ok > 0) {
						alert("添加成功");
						location.reload();
					}
				},
				error : function() {
					alert("系统错误");
				}
			});
		})
	})
	
	$().ready(function() {
		$(".add_fuson").click(function() {
			var parentid = $(this).parent("div").find(".replyid").val();
			$("#parentId").val(parentid);
		})
	})
	
	$().ready(function() {
		$(".deletepinglun").click(function() {
			var replyId = $(this).parent("div").find(".replyid").val();
			var bugids = $("#bugpinglunids").val();
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${pageContext.request.contextPath}/bug/deletemake.action",
				data : {
					bugId : bugids,
					replyId : replyId
				},
				success : function(data) {
					location.reload();
				},
				error : function() {
					alert("系统错误");
				}
			});
		})

		$(".delete_fuson").click(function() {
			var replyId = $(this).parent("div").find(".replyid").val();
			var bugids = $("#bugpinglunids").val();
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${pageContext.request.contextPath}/bug/deletemake.action",
				data : {
					bugId : bugids,
					replyId : replyId
				},
				success : function(data) {
					location.reload();
				},
				error : function() {
					alert("系统错误");
				}
			});
		})
	})
</script>
</html>
