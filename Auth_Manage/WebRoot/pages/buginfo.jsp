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
<title>用户管理 - 角色列表</title>
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
			<h1 class="page-header">BUG列表</h1>
			<div class="row placeholders">
				<form action="${pageContext.request.contextPath}/bug/buginfo.action"
					method="post" name="form">
					<div class="pull-right form-inline">
						<div class="search">
							项目名称：<select name="projId" id="headpid" class="proid"
								onchange="window.form.submit()">
								<c:if test="${turn=='myBug' }">
									<option value="">全部</option>
								</c:if>
								<c:forEach items="${project}" var="proj">
									<option value="${proj.projId}"
										${proj.projId==param.projId?"selected='selected'":""}>${proj.projName}</option>
								</c:forEach>
								<c:if test="${turn=='allBug' }">
									<option value="">全部</option>
								</c:if>
							</select>
						</div>
						<div class="search">
							<input type="hidden" id="dumeihang" value="${param.classId}">
							模块名称：<select name="classId" id="classids"
								onchange="window.form.submit()">
							</select>
						</div>
						<div class="search">
							BUG名称：<input type="text" value="${param.bugTitle}" class=""
								name="bugTitle" />
						</div>
						<div class="search">
							BUG编号：<input type="text" value="${param.bugNum}" class=""
								name="bugNum" />
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
						</div>
						<div class="search">
							BUG状态：<select name="bugState">
								<option value="${param.bugState}">${param.bugState=='0'?'新建':(param.bugState=='1'?'已分配':(param.bugState=='2'?'已解决':(param.bugState=='3'?'已关闭':'---')))}
								</option>
								<option value="0">新建</option>
								<option value="1">已分配</option>
								<option value="2">已解决</option>
								<option value="3">已关闭</option>
								<option value="">全部</option>
							</select>
						</div>
						<div class="search">
							BUG等级：<select name="bugLevel">
								<option value="${param.bugLevel}">${param.bugLevel=='0'?'普通':(param.bugLevel=='1'?'严重':(param.bugLevel=='2'?'缺陷':'---'))}
								</option>
								<option value="0">普通</option>
								<option value="1">严重</option>
								<option value="2">缺陷</option>
								<option value="">全部</option>
							</select>
						</div>
						<button type="submit" class="btn form-inline" style="margin-top: -2px;">确定查询</button>
					</div>
				</form>
				<br />

				<div>
					<div>
						<c:if test="${fn:contains(authCodes,'bug-outputBug-bug')}">
							<button type="button" class="success btn" id="exportroleexcel"
								onclick="location.href='${pageContext.request.contextPath}/${page.url}?${page.params}&pageNum=9999&currNo=1'">导出表格</button>
						</c:if>
						<c:if test="${fn:contains(authCodes,'bug-add-bug')}">
							<button type="button" class="show-add-form btn"
								data-toggle="modal" data-target="#bug-form-div">添加BUG</button>
						</c:if>

						<!--添加bug表单-->
						<div class="modal fade " id="bug-form-div" tabindex="-1"
							role="dialog" aria-labelledby="mySmallModalLabel">
							<div class="modal-dialog modal-md">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="role-form-title">添加Bug</h4>
									</div>
									<div class="modal-body">
										<form method="post" name="addbugs"
											action="${pageContext.request.contextPath}/bug/insertbug.action"
											enctype="multipart/form-data">
											<div class="form-group">
												<label for="bug_title">BUG名称:</label> <input type="text"
													name="bugTitle" class="bug_title_add" placeholder="BUG名称"
													maxlength="20">
											</div>
											<div class="form-group">
												<input type="hidden" name="createBy" class="creator"
													value="${userInfo.userId}" />
											</div>
											<div class="form-group">
												<label for="bug_title">BUG编号:</label> <input type="text"
													name="bugNum" class="bug_num_add" placeholder="BUG编号"
													maxlength="20">
											</div>
											<div class="form-group">
												<label for="descInput">项目名称：</label> <select name="projId"
													class="addproid" id="addprojIds">
													<option value="">---</option>
													<c:forEach items="${project}" var="proj">
														<option value="${proj.projId}">${proj.projName}</option>
													</c:forEach>
												</select>
											</div>
											<div class="form-group">
												<label for="codeInput">模块名称:</label> <select name="classId"
													class="modelclassid">
												</select>
											</div>
											<div class="form-group">
												<label for="">BUG描述:</label><br/>
												<textarea rows="5" cols="39" name="bugDesc"></textarea>
											</div>
											<div class="form-group">
												<label for="codeInput">上传附件:</label> <input type="file"
													name="pictureFile" value="请选择文件" />
											</div>
											<div class="form-group">
												<label for="codeInput">BUG等级:</label> <select
													name="bugLevel" class="bugLevel_add">
													<option value="">---</option>
													<option value="0">普通</option>
													<option value="1">严重</option>
													<option value="2">缺陷</option>
												</select>
											</div>
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
						<td>项目Id</td>
						<td>项目名称</td>
						<td>模块</td>
						<td>BUG编号</td>
						<td>BUG名称</td>
						<td>BUG状态</td>
						<td>BUG级别</td>
						<td>创建时间：</td>
						<td>执行人</td>
						<td>操作</td>
					</tr>
					<c:forEach items="${page.resultList}" var="bug">
						<tr>
							<input type="hidden" class="bugid" value="${bug.bugId}" />
							<input type="hidden" class="bugDesc" value="${bug.bugDesc}" />
							<input type="hidden" class="fileUrl" value="${bug.fileUrl}" />
							<input type="hidden" class="bugLevelNum" value="${bug.bugLevel}" />
							<input type="hidden" class="classId" value="${bug.classId}" />
							<td class="projId">${bug.projId}</td>
							<td class="projName">${bug.projName}</td>
							<td class="className">${bug.className}</td>
							<td class="bugNum">${bug.bugNum}</td>
							<td class="bugTitle">${bug.bugTitle}</td>
							<td class="bugState">${bug.bugState==0?'新建':(bug.bugState==1?'已分配':(bug.bugState==2?'已解决':'已关闭'))}</td>
							<td class="bugLevel">${bug.bugLevel==0?'普通':(bug.bugLevel==1?'严重':'缺陷')}</td>
							<td class="starttime">${bug.createTime}</td>
							<td class="executor">${bug.executor}</td>
							<td  style="text-align: left">
								<c:if test="${fn:contains(authCodes,'bug-updateBug-bug')}">
									<button type="button" data-toggle="modal" style="margin: 2px;"
										class="updatebugbutton btn" data-target="#bug-updateBUg-div">修改BUG</button>
								</c:if> <c:if test="${fn:contains(authCodes,'bug-bugDetail-bug')}">
									<button type="button" class="xiangqing btn" style="margin: 2px;">BUG详情</button>
								</c:if> <c:if test="${fn:contains(authCodes,'bug-assignBug-bug')}">
									<button type="button" data-toggle="modal" style="margin: 2px;"
										data-target="#fenpeiBUg-div" class="fenpeiBUgdiv btn">分配BUG</button>
								</c:if> 
								<c:if test="${fn:contains(authCodes,'bug-updateState-bug')}">
									<button type="button" data-toggle="modal" style="margin: 2px;"
										data-target="#bugstate" class="upbugstate btn">修改BUG状态</button>
								</c:if>
								<c:if test="${fn:contains(authCodes,'bug-deleteBug-bug')}">
									<button type="button" data-toggle="modal" style="margin: 2px;"
										class="deletebugbutton btn">删除BUG</button>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
				<jsp:include page="standard.jsp" />
			</div>

			<!-- 修改BUG -->
			<form method="post" action="${pageContext.request.contextPath}/bug/updatebug.action" enctype="multipart/form-data">
			<div class="modal fade " id="bug-updateBUg-div" tabindex="-1"
				role="dialog" aria-labelledby="mySmallModalLabel">
				<div class="modal-dialog modal-md">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="role-form-title">修改BUG</h4>
						</div>
						<input type="hidden" class="bug_id_update" name="bugId">
						<div class="modal-body">
							<div class="form-group">
								<label for="bug_title">BUG编号:</label> <input readonly="readonly" type="text"
									name="bugNum" class="bug_num_update" placeholder="BUG编号" maxlength="20">
							</div>
							<div class="form-group">
								<label for="bug_title">BUG名称:</label> <input type="text"
									name="bugTitle" class="bug_title_update" placeholder="Bug名称" maxlength="20">
							</div>
							<div class="form-group">
								<label for="bug_title">BUG描述:</label><br/>
									<textarea rows="5" cols="39" name="bugDesc" class="bug_desc_update"></textarea>
							</div>
							<div class="form-group">
								<label for="codeInput">BUG级别:</label> <select
									name="bugLevel" class="bugLevelupdate">
									<option value="">---</option>
									<option value="0">普通</option>
									<option value="1">严重</option>
									<option value="2">缺陷</option>
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


			<!-- 分配BUG -->
			<div class="modal fade " id="fenpeiBUg-div" tabindex="-1"
				role="dialog" aria-labelledby="mySmallModalLabel">
				<div class="modal-dialog modal-md">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="role-form-title">分配BUG</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="bug_title">BUG名称:</label> <input readonly="readonly" type="text"
									class="fen_bug_title" placeholder="Bug名称" maxlength="20">
							</div>
							<div class="form-group">
								<label for="bug_num">BUG编号:</label> <input readonly="readonly" type="text"
									class="fen_bug_num" placeholder="BUG编号" maxlength="20">
							</div>
							<div class="form-group">
								<label for="codeInput">执行人:</label> <select name="updateman"
									class="manidm">
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


			<!-- 修改BUG状态 -->
			<div class="modal fade " id="bugstate" tabindex="-1" role="dialog"
				aria-labelledby="mySmallModalLabel">
				<div class="modal-dialog modal-md">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="role-form-title">修改BUG状态</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="bug_title">BUG名称:</label> <input readonly="readonly" type="text"
									class="bug_title_upstate" placeholder="Bug名称" maxlength="20">
							</div>
							<div class="form-group">
								<label for="bug_num">BUG编号:</label> <input readonly="readonly" type="text"
									class="bug_num_upstate" placeholder="BUG编号" maxlength="20">
							</div>
							<div class="form-group">
								<label for="codeInput">BUG等级:</label> <select
									name="bug_state_upstate">
									<c:if
										test="${fn:contains(rolecode,'testleader') || fn:contains(rolecode,'testmember')}">
										<option value="1">已分配</option>
									</c:if>
									<option value="2">已解决</option>
									<c:if
										test="${fn:contains(rolecode,'testleader') || fn:contains(rolecode,'testmember')}">
										<option value="3">已关闭</option>
									</c:if>
								</select>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="button" class="updatebugstate">确定</button>
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
			var bugTitle = $(".bug_title_add").val();
			var bugNum = $(".bug_num_add").val();
			var projId = $(".addproid").val();
			var classId = $(".modelclassid").val();
			if (!bugTitle || !bugNum || !projId || !classId) {
				alert("缺少必选项");
			} else {
				$.ajax({
					type : "POST",
					dataType : "json",
					url : "${pageContext.request.contextPath}/bug/checkednum.action",
					data : {
						bugNum : bugNum
					},
					success : function(data) {
						if (data.ok > 0) {
							alert("BUG编号已有,添加失败");
						} else {
							window.addbugs.submit();
						}
					}
				})
			}
		})
	})

	$(".updatebugbutton").click(function() {
		var parent = $(this).parents("tr");
		var id = parent.find(".bugid").val();
		var num = parent.find(".bugNum").html();
		var title = parent.find(".bugTitle").html();
		var desc = parent.find(".bugDesc").val();
		var level = parent.find(".bugLevelNum").val();
		//var url = parent.find(".fileUrl").val();
		$(".bug_id_update").val(id);
		$(".bug_num_update").val(title);
		$(".bug_title_update").val(num);
		$(".bug_desc_update").html(desc);
		$(".bugLevelupdate").val(level);
	});
	
	$().ready(function() {
		$(".upbugstate").click(
			function() {
				var parent = $(this).parents("tr");
				var num = parent.find(".bugNum").html();
				var title = parent.find(".bugTitle").html();
				$(".bug_title_upstate").val(title);
				$(".bug_num_upstate").val(num);
			});
		$(".updatebugstate").click(function() {
			$("#bugstate").modal("hide");
			var state = $("[name=bug_state_upstate]").val();
			var num = $(".bug_num_upstate").val();
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${pageContext.request.contextPath}/bug/updatebugstate.action",
				data : {
					bugNum : num,
					bugState : state
				},
				success : function(data) {
					if (data.update > 0) {
						alert("修改成功");
						location.reload();
					} else {
						alert("修改失败");
					}
				}
			})
		})
	})
	
	$().ready(function() {
		$(".fenpeiBUgdiv").click(function() {
			var parent = $(this).parents("tr");
			var num = parent.find(".bugNum").html();
			var title = parent.find(".bugTitle").html();
			var proid = parent.find(".projId").html();
			$(".fen_bug_title").val(title);
			$(".fen_bug_num").val(num);
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${pageContext.request.contextPath}/bug/doitman.action",
				data : {
					projId : proid
				},
				success : function(data) {
					var id = [];
					var name = [];
					id = data.userid.split(",");
					name = data.username.split(",");
					var dmh = "<option value=''>" + "---" + "</option>";
					for (var i = 0; i < id.length - 1; i++) {
						dmh += "<option value='"+id[i]+"'>" + name[i] + "</option>";
					}
					$(".manidm").html(dmh);
				}
			})
		})
		
		$(".fen_bug").click(function() {
			$("#fenpeiBUg-div").modal("hide");
			bugnum = $(".fen_bug_num").val();
			updateby = $("[name=updateman]").val();
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${pageContext.request.contextPath}/bug/assignBug.action",
				data : {
					bugNum : bugnum,
					updateBy : updateby
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
				url : "${pageContext.request.contextPath}/bug/deleteBug.action",
				data : {
					intro : "项目ID:"+parent.find(".projId").html()+",项目名称:"+parent.find(".projName").html()+
							",模块名称:"+parent.find(".className").html()+",BUG名称:"+parent.find(".bugTitle").html(),
					classId : parent.find(".classId").val(),
					keyValue : parent.find(".bugid").val(),
					createBy : ${userInfo.userId},
					dbUser : "autho_manage",
					tableName : "bug_info",
					keyName : "bug_id"
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
