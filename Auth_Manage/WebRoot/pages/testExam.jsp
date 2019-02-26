<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>测试管理</title>
<link href="${pageContext.request.contextPath}/static/css/bug.css"
	rel="stylesheet">
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/static/css/layout.css"
	rel="stylesheet">
<style type="text/css">
.red {color: red}
#table-style td {vertical-align: middle;}
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
			<h1 class="page-header">测试列表</h1>
			<div class="row placeholders">
				<form action="${pageContext.request.contextPath}/test/testlist.action"
					method="post" name="form">
					<div class="pull-right form-inline">
						<div class="search">
							项目名称：<select name="projId" id="headpid" class="proid"
								onchange="window.form.submit()">
								<option value="">---</option>
								<c:forEach items="${proId}" var="proj">
									<option value="${proj.projId}"
										${proj.projId==param.projId?"selected='selected'":""}>${proj.projName}</option>
								</c:forEach>
							</select>
						</div>
						<%-- <div class="search" >
							用例名称：<input type="text" value="${param.testTitle}" class=""
								name="testTitle" />
						</div>
						<div class="search">
							执行人：<input type="text" value="${param.executor}" class=""
								name="executor" />
						</div>
						<div class="search">
							开始时间：<input type="date" value="${param.startTime}" class=""
								name="startTime" />
						</div>
						<div class="search">
							结束时间：<input type="date" value="${param.endTime}" class=""
								name="endTime" />
						</div> --%>
						<div class="search">
							用例状态：<select name="testState">
								<option value="${param.testState}">${param.testState=='0'?'新建':(param.testState=='1'?'已分配':(param.testState=='2'?'未完成':(param.testState=='3'?'已完成':'---')))}
								</option>
								<option value="0">新建</option>
								<option value="1">已分配</option>
								<option value="2">未完成</option>
								<option value="3">已完成</option>
								<option value="">全部</option>
							</select>
						</div>
						<div class="search">
							用例类型：<select name="testType">
								<option value="${param.testType}">${param.testType=='1'?'功能测试':(param.testType=='2'?'性能测试':(param.testType=='3'?'安全测试':(param.testState=='4'?'其他':'---')))}
								</option>
								<option value="1">功能测试</option>
								<option value="2">性能测试</option>
								<option value="3">安全测试</option>
								<option value="4">其他</option>
								<option value="">全部</option>
							</select>
						</div>
						<br/>
						<button type="submit" class="btn form-inline" style="margin-top: -2px;">确定查询</button>
						<c:if test="${fn:contains(authCodes,'test-add-test')}">
							<button type="button" class="show-add-form btn"
								data-toggle="modal" data-target="#bug-form-div" style="margin-top: -2px;">添加用例</button>
						</c:if>
					</div>
				</form>
				<br />

				<div>
					<div>
						<%-- <c:if test="${fn:contains(authCodes,'test-add-test')}">
							<button type="button" class="success btn" id="exportroleexcel"
								onclick="location.href='${pageContext.request.contextPath}/${page.url}?${page.params}&pageNum=9999&currNo=1'">导出表格</button>
						</c:if> --%>
						<!--添加用例表单-->
						<div class="modal fade " id="bug-form-div" tabindex="-1"
							role="dialog" aria-labelledby="mySmallModalLabel">
							<div class="modal-dialog modal-md">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="role-form-title">添加用例</h4>
									</div>
									<div class="modal-body">
										<form method="post" name="addbugs"
											action="${pageContext.request.contextPath}/test/insertlist.action"
											enctype="multipart/form-data">
											<input type="hidden" name="createBy" class="creatora"
												value="${userInfo.userId}" />
											<div class="form-group">
												<label for="bug_title">用例名称：</label> <input type="text"
													name="testTitle" class="testTitle" placeholder="用例名称"
													maxlength="20">
											</div>
											<div class="form-group">
												<input type="hidden" name="createBy" class="creator"
													value="${userInfo.userId}" />
											</div>
											<div class="form-group">
												<label for="descInput">项目名称：</label> <select name="projId"
													class="addproid" id="addprojIds">
													<option value="">---</option>
													<c:forEach items="${proId}" var="proj">
														<option value="${proj.projId}">${proj.projName}</option>
													</c:forEach>
												</select>
											</div>
											<div class="form-group">
												<label for="">用例描述：</label><br/>
												<textarea rows="5" cols="39" name="testDesc"></textarea>
											</div>
											<div class="form-group">
												<label for="codeInput">测试类型：</label> <select
													name="testType" class="testType">
													<option value="">---</option>
													<option value="1">功能测试</option>
													<option value="2">性能测试</option>
													<option value="3">安全测试</option>
													<option value="4">其他</option>
												</select>
											</div>
											<!-- <div class="form-group">
												<label for="codeInput">上传附件:</label> <input type="file"
													name="pictureFile" value="请选择文件" />
											</div> -->
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>
										<button type="button" class="addbug">确定</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div>
				<div class="space-div"></div>
				<table id="table-style" class="table table-hover table-bordered role-list"
					style="text-align: center;">
					<tr>
						<td>测试用例Id</td>
						<td>项目ID</td>
						<td>测试类型</td>
						<td>用例名称</td>
						<td width="100px" style="word-wrap: break-word;word-break: break-all;">用例描述</td>
						<td>测试结果</td>
						<td>执行人</td>
						<td>创建时间：</td>
						<td>创建人：</td>
						<td>开始时间：</td>
						<td>结束时间：</td>
						<td>状态：</td>
						<td>附件：</td>
						<td>操作：</td>
					</tr>
					<c:forEach items="${page.resultList}" var="test">
						<tr>
							<input type="hidden" class="projId" value="${test.projId}" />
							<td class="testId">${test.testId}</td>
							<td class="projName">${test.projName}</td>
							<td class="testType">${test.testType==1?'功能测试':(test.testType==2?'性能测试':(test.testType==3?'安全测试':'其他'))}</td>
							<td class="testtitleshow">${test.testTitle}</td>
							<td width="100px" style="word-wrap: break-word;word-break: break-all;" class="testDesc">${test.testDesc}</td>
							<td class="result">${test.result==0?'未通过':(test.result==1?'已通过':'')}</td>
							<td class="executor">${test.executor}</td>
							<td class="createTime">${test.createTime}</td>
							<td class="createBy">${test.createName}</td>
							<td class="startTime">${test.startTime}</td>
							<td class="endTime">${test.endTime}</td>
							<td class="testState">${test.testState==0?'新建':(test.testState==1?'已分配':(test.testState==2?'未完成':'已完成'))}</td>
							<td class="fileUrl">
								<img src="${pageContext.request.contextPath}/${test.fileUrl}" alt="" width="70" height="50" />
							</td>
							<td style="text-align: left">
								<c:if test="${fn:contains(authCodes,'test-updateTest-test')}">
									<button type="button" data-toggle="modal" style="margin: 2px;"
										class="updatebugbutton btn" data-target="#bug-updateBUg-div">修改用例</button>
								</c:if>  
								<c:if test="${fn:contains(authCodes,'test-assign-test')}">
									<button type="button" data-toggle="modal" style="margin: 2px;"
										data-target="#fenpeiBUg-div" class="fenpeiBUgdiv btn">分配用例</button>
								</c:if> 
								<c:if test="${fn:contains(authCodes,'test-deleteTest-test')}">
									<button type="button" data-toggle="modal" style="margin: 2px;"
										class="deletebugbutton btn">删除用例</button>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
				<jsp:include page="standard.jsp" />
			</div>

			<!-- 修改BUG -->
			<form method="post" action="${pageContext.request.contextPath}/test/updatexam.action" enctype="multipart/form-data">
			<div class="modal fade " id="bug-updateBUg-div" tabindex="-1"
				role="dialog" aria-labelledby="mySmallModalLabel">
				<div class="modal-dialog modal-md">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="role-form-title">修改用例</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="bug_title">测试用例ID:</label>
								<input type="" name="testId" id="test_ids" readonly="readonly">
							</div>
							<div class="form-group">
								<label for="codeInput">用例状态:</label> <select
									name="testState" class="bugLevelupdate">
									<option value="">---</option>
									<option value="0">新建</option>
									<option value="1">已分配</option>
									<option value="2">未完成</option>
									<option value="3">已完成</option>
								</select>
							</div>
							<div class="form-group">
								<label for="bug_title">结束时间:</label> <input type="date"
									name="endTime" class="endtime" maxlength="20">
							</div>
							<div class="form-group">
								<label for="codeInput">测试结果:</label> <select
									name="result" class="bugLevelupdate">
									<option value="">---</option>
									<option value="0">未通过</option>
									<option value="1">已通过</option>
								</select>
							</div>
							<div class="form-group">
								<label for="codeInput">上传附件:</label> <input type="file"
									name="pictureFileUpdate" class="pictureFile_update" value="请选择文件" />
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="submit" class="updatebug">确定</button>
						</div>
					</div>
				</div>
			</div>
			</form>


			<!-- 分配用例 -->
			<div class="modal fade " id="fenpeiBUg-div" tabindex="-1"
				role="dialog" aria-labelledby="mySmallModalLabel">
				<div class="modal-dialog modal-md">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="role-form-title">分配用例</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="bug_num">用例ID:</label> 
								<input readonly="readonly" type="text"
									class="updateslistid" maxlength="20">
							</div>
							<div class="form-group">
								<label for="bug_title">用例名称:</label> <input readonly="readonly" type="text"
									class="testzhu_ti" placeholder="用例名称" maxlength="20">
							</div>
							<div class="form-group">
								<label for="codeInput">执行人:</label> <select name="executor"
									class="whichdoman">
									<option value="">---</option>
									<c:forEach items="${user}" var="use">
											<option value="${use.userName}">${use.userName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="button" class="fen_bug">确定</button>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>

			<!-- Bootstrap core JavaScript
    ================================================== -->
			<!-- Placed at the end of the document so the pages load faster -->
<script
	src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<script>
	$().ready(function() {
		var mid = $("#dumeihang").val();
		var pid = $("#headpid").val();
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "${pageContext.request.contextPath}/bug/searchmodel.action",
			data : {
				projId : pid
			},
			success : function(data) {
				if (data.sb == 0) {
					var sname = [];
					var sid = [];
					sname = data.syname.split(",");
					sid = data.syid.split(",");
					var amk = "<option value=''>" + "全部" + "</option>";
					for (var i = 0; i < sname.length - 1; i++) {
						if (mid == sid[i]) {
							amk += "<option value='"+sid[i]+"' selected='selected'>" + sname[i] + "</option>";
						} else {
							amk += "<option value='"+sid[i]+"'>" + sname[i] + "</option>";
						}
					}
					$("#classids").html(amk);
				} else {
					$("#classids").html("<option value=''>" + "全部" + "</option>");
				}
			},
			error : function() {
				alert("系统错误");
			}
		});
	})

	$().ready(function() {
		$(".addproid").change(function() {
			var pid = $("#addprojIds").val();
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${pageContext.request.contextPath}/bug/searchmodel.action",
				data : {
					projId : pid
				},
				success : function(data) {
					if (data.sb == 0) {
						var sname = [];
						var sid = [];
						sname = data.syname.split(",");
						sid = data.syid.split(",");
						var amk = "";
						for (var i = 0; i < sname.length - 1; i++) {
							amk += "<option value='"+sid[i]+"'>" + sname[i] + "</option>";
						}
						$(".modelclassid").html(amk);
					} else {
						$(".modelclassid").html("<option value=''>" + "---" + "</option>");
					}
				},
				error : function() {
					alert("系统错误");
				}
			});
		})
	})
	
	$().ready(function() {
		$(".addbug").click(function() {
			var createBy = $(".creatora").val();
			var testType = $(".testType").val();
			var testTitle = $(".testTitle").val();
			var projId = $(".addproid").val();
			if (!testType || !testTitle || !projId) {
				alert("缺少必选项");
			} else {window.addbugs.submit();
			}
		})
	})

	$(".updatebugbutton").click(function() {
		var parent = $(this).parents("tr");
		var id = parent.find(".testId").html();
		$("#test_ids").val(id);
	});
	
	$().ready(function() {
		$(".fenpeiBUgdiv").click(function() {
			var parent = $(this).parents("tr");
			var testId = parent.find(".testId").html();
			var testTitle = parent.find(".testtitleshow").html();
			$(".updateslistid").val(testId);
			$(".testzhu_ti").val(testTitle);
		})
		$(".fen_bug").click(function() {
			$("#fenpeiBUg-div").modal("hide");
			var testId = $(".updateslistid").val();
			var executor = $(".whichdoman").val();
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${pageContext.request.contextPath}/test/updateAssign.action",
				data : {
					testId : testId,
					executor : executor
				},
				success : function(data) {
					if (data.ok > 0) {
						alert("分配成功");
						location.reload();
					} else {
						alert("分配失败");
					}
				}
			})
		})
	})
	
	//描述、模块id、数据库用户、表名称、主键key、主键value
	$().ready(function() {
		$(".deletebugbutton").click(function() {
			var parent = $(this).parents("tr");
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${pageContext.request.contextPath}/test/deleteTest.action",
				data : {
						intro : "项目ID:"+parent.find(".projId").val()+",项目名称:"+parent.find(".projName").html()+
							",用例名称:"+parent.find(".testtitleshow").html(),
						keyValue : parent.find(".testId").html(),
						createBy : ${userInfo.userId},
						dbUser : "autho_manage",
						tableName : "test_examp",
						keyName : "test_id"
				},
				success : function(data) {
					if (data.res > 0) {
						alert("删除成功");
						location.reload();
					} else {
						alert("删除失败");
					}
				}
			})
		})
	})
	
	$(".xiangqing").click(
		function() {
			var bugid = $(this).parents("tr").find(".bugid").val();
			window.location.href = "${pageContext.request.contextPath}/bug/detail.action?bugId=" + bugid;
		})
</script>
</html>
