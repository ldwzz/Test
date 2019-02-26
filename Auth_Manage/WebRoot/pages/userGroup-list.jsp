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
			<h1 class="page-header">用户组列表</h1>
			<div class="row placeholders">
				<div class="row placeholders">
					<form
						action="${pageContext.request.contextPath}/user/grouplist.action?pageNum=${page.pageNum}"
						method="post">
						<div class="pull-right form-inline">
							<input type="text" name="groupName"
								class="form-control form-inline" placeholder="用户组名"
								id="groupName" value="${param.groupName}" > <select
								class="form-control form-inline" name="groupState" id="groupState">
								<option value="">用户组状态</option>
								<option value="0"
									${param.groupState =='0' ?"selected='selected'":""}>禁用</option>
								<option value="1"
									${param.groupState =='1' ?"selected='selected'":""}>启用</option>
							</select>
							<button type="submit" class="btn btn-primary form-inline">确定查询</button>
						</div>
					</form>
					<br />
					<div>
						<div>
							<c:if test="${fn:contains(authCodes,'group-addUserGroup')}">
							<button type="button" class="btn btn-default show-add-form"
								data-toggle="modal" data-target="#role-form-div">添加用户组</button></c:if>

							<!--添加用户组表单-->
							<div class="modal fade " id="role-form-div" tabindex="-1"
								role="dialog" aria-labelledby="mySmallModalLabel">
								<div class="modal-dialog modal-md" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="role-form-title"></h4>
										</div>
										<div class="modal-body">
											<form class="role-form">
												<div class="form-group">
													<label for="roleNameInput">名称</label> <input type="text"
														name="roleName" class="form-control" id="userGroupNameInput"
														placeholder="用户组名" maxlength="20">
												</div>
												<div class="form-group">
													<label for="descInput">描述</label> <input type="text"
														name="roleDesc" class="form-control" id="userGroupdescInput"
														placeholder="用户组描述" maxlength="20">
												</div>
												<div class="form-group">
													<label for="codeInput">代码</label> <input type="text"
														name="roleCode" class="form-control" id="userGroupcodeInput"
														placeholder="用户组代码" maxlength="20"> <label id="groupCodeError"></label>
												</div>
											</form>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
											<button type="button"
												class="btn btn-primary group-submit addgroup">确定</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="space-div"></div>
						<table class="table table-hover table-bordered role-list"
							style="text-align: center;">
							<tr>
								<td>ID</td>
								<td>名称</td>
								<td>描述</td>
								<td>Code</td>
								<td>状态</td>
								<td>操作</td>
							</tr>
							<c:forEach items="${page.resultList}" var="usergroup">
								<tr>
									<td class="groupId">${usergroup.groupId }</td>
									<td class="groupName">${usergroup.groupName}</td>
									<td class="groupDesc">${usergroup.groupDesc}</td>
									<td class="groupCode">${usergroup.groupCode}</td>
									<td><c:if test="${usergroup.groupState==0}">
											<span style="color: red;">禁用</span>
										</c:if> <c:if test="${usergroup.groupState==1}">
											<span style="color: green;">启用</span>
										</c:if></td>
									<td><c:if test="${usergroup.groupState==0}">
									<c:if test="${fn:contains(authCodes,'group-startUserGroup')}">
											<button type="button" class="btn btn-warning start-using">启用</button></c:if>
										</c:if> <c:if test="${usergroup.groupState==1}">
											<c:if test="${fn:contains(authCodes,'group-forbiddenUserGroup')}">
											<button type="button" class="btn btn-danger forbidden"  id="forbidden">禁用</button></c:if>
												<c:if test="${fn:contains(authCodes,'group-assignRole')}">
											<button type="button" class="btn btn-primary assign-role" 
											data-toggle="modal"  data-target="#assign-role-form">分配角色</button></c:if>										
										</c:if></td>
								</tr>
							</c:forEach>
						</table>
						<jsp:include page="standard.jsp" />
					</div>
				</div>
			</div>
		</div>
		<!--分配角色-->
		<div class="modal fade " id="assign-role-form" tabindex="-1"
			role="dialog" aria-labelledby="mySmallModalLabel">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">分配角色</h4>
					</div>
					<div class="modal-body">
						<form class="user-form">
							<div class="form-group add-user">
								<h4>用户组名：</h4>
								<span class="form-control" id="assignGroupName"></span> <input
									type="hidden" id="assignGroupId" />
								<div class="checkbox">
									<h4 style="margin-top: 30px;">可选角色：</h4>
									<c:forEach items="${rolesList}" var="role">
										<label><input type="checkbox" class="form-inline"
											value="${role.roleId}" name="userRole" />${role.roleName}</label>
									</c:forEach>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary asign-role-submit">确定
						</button>
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
			//查询用户组角色
			$(function(){
				$(".assign-role").click(function(){
					var groupId = $(this).parents("tr").find(".groupId").text();
					var groupName = $(this).parents("tr").find(".groupName").text();
					$("#assignGroupName").text(groupName);
					$("#assignGroupId").val(groupId);
					$("input[name='userRole']").each(function(){
						$(this).prop("checked",false);
					})
					$.ajax({
						type:"POST",
						dataType:"json",
						url:"${pageContext.request.contextPath}/role/getGroupRoles.action",
						data:{
							groupId:groupId
						},
						success:function(json){
							var groupIds= json.groupIds.split(",");
							$("input[name='userRole']").each(function(){
								for(i=0;i<groupIds.length;i++){
									if(groupIds[i]==$(this).val()){
										$(this).prop("checked",true);
									}
								}
							})	
						},
						error:function(){
							alert("系统错误");
						}
				});
			})
			})
		//用户组角色绑定
		$(".asign-role-submit").click(function(){
			var roleIds="";
			var groupId=$("#assignGroupId").val();
			var length= $("input[name='userRole']:checked").length;
			$("input[name='userRole']:checked").each(function(i){
  				if(length==1 || i==length-1){
 					roleIds+=$(this).val();	
  				}else{
  					roleIds+=$(this).val()+",";	
  				}
			})
			if(length>0){
				$.ajax({
				type:"POST",
				dataType:"json",
				url:"${pageContext.request.contextPath}/role/assignUserGroupRoles.action",
				data:{
					groupId:groupId,
					roleIds:roleIds
				},
				success:function(data){
					if(data.msg==1){
						$("#assign-role-form").modal("hide");
						alert("分配成功！");
					}else{
						showTips("修改失败！");
					}
				},
				error:function(){
					showTips("系统错误！");
				}
			})
			}else{
				alert("未勾选任何权限")
			}
		})
		var flag=false;
		//添加用户组code唯一性校验
		$(function(){
			$("#userGroupcodeInput").keyup(function(){
				var groupCode=$("#userGroupcodeInput").val();//获取用户code
				if(!!groupCode){
					$.ajax({
						type:"POST",
						dataType:"json",
						url:"${pageContext.request.contextPath}/user/addGroupVerify.action",
						data:{
							groupCode:groupCode
						},
						success:function(json){						
							if(json.msg==1){
								flag=false;
								$("#groupCodeError").addClass("red").html("用户组code重复");						
							}else{
								flag=true;
								$("#groupCodeError").removeClass("red").html("");	
							}
						},
						error:function(){
							showTips("系统错误");
						}
					})
				}
			})
		})
		
		//添加用户组
		$(".group-submit").click(function() {
		var GroupName=$("#userGroupNameInput").val();//获取用户组名
		var Groupdesc=$("#userGroupdescInput").val();//获取昵称
		var Groupcode=$("#userGroupcodeInput").val();//获取部门
			//请求添加新用户			 
			if(flag){
				if(!!GroupName && !!Groupdesc){
					$.ajax({						
					type : "POST",
					dataType : "json",
					url :"${pageContext.request.contextPath}/user/addGroupInfo.action",
					data : {
					groupName :GroupName,
					groupDesc:Groupdesc,
					groupCode:Groupcode			
					},	
					success : function(data) {
					$("#add-user-form").modal("hide");
					alert("添加成功！");
					location.reload();
					},
					error:function(){
						alert("系统错误");
					}
				})		
				}else{
					alert("不能为空");
				}
			}else {
				alert("用户code不能重复");
			}
		});
		
		//用户组禁用
		$(function(){
			$(".forbidden").click(function(){
			var userTr = $(this).parents("tr");
			var groupId = userTr.find(".groupId").html();	
				if (confirm('确定要禁用？')) {
				$.ajax({
					type : "POST",
					dataType:"json",
					url : "${pageContext.request.contextPath}/user/groupForbidden.action",
					data : {
						groupState:0,
						groupId:groupId
					},					
					success : function(json) {
						if(json.msg==1){
							location.reload();					
						}else{
							showTips("禁用失败");
						}				
					},
					error:function(){
						showTips("系统错误！");
					}
				});			
			}		
			})
		})
		//用户组启用
		$(function(){
			$(".start-using").click(function(){
				var userTr = $(this).parents("tr");
			var groupId = userTr.find(".groupId").html();	
				if (confirm('确定要启用？')) {
				$.ajax({
					type : "POST",
					dataType:"json",
					url : "${pageContext.request.contextPath}/user/groupForbidden.action",
					data : {
						groupState:1,
						groupId:groupId
					},					
					success : function(json) {
						if(json.msg==1){
							location.reload();					
						}else{
							showTips("启用失败");
						}				
					},
					error:function(){
						showTips("系统错误！");
					}
				});			
			}		
			})
		})
		</script>
</body>
</html>
