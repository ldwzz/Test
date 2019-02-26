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
	.red{color:red}
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
			<h1 class="page-header">角色列表</h1>
			<div class="row placeholders">
				<div class="row placeholders">
					<form
						action="${pageContext.request.contextPath}/role/list.action?pageNum=${page.pageNum}"
						method="post">
						<div class="pull-right form-inline">
							<input type="text" name="roleName"
								class="form-control form-inline" placeholder="角色名" id="roleName"
								value="${param.roleName}"> 
								<select class="form-control form-inline" name="roleState"
								id="roleState">
								<option value="">角色状态</option>
								<option value="0"
									${param.roleState =='0' ?"selected='selected'":""}>禁用</option>
								<option value="1"
									${param.roleState =='1' ?"selected='selected'":""}>启用</option>
							</select> 
							<button type="submit" class="btn btn-primary form-inline">确定查询</button>
						</div>
					</form>
					<br />
					<div>
						<div>
						
						<c:if test="${fn:contains(authCodes,'role-exportExcel')}">
							<button type="button" class="btn btn-success" id="exportroleexcel">导出表格</button></c:if>
							<c:if test="${fn:contains(authCodes,'role-addRole')}">
							<button type="button" class="btn btn-default show-add-form"
								data-toggle="modal" data-target="#role-form-div">添加新角色</button></c:if>
							
							<!--添加新角色表单-->
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
														name="roleName" class="form-control" id="roleNameInput"
														placeholder="角色名" maxlength="20">
												</div>
												<div class="form-group">
													<label for="descInput">描述</label> <input type="text"
														name="roleDesc" class="form-control" id="descInput"
														placeholder="角色描述" maxlength="20">
												</div>
												<div class="form-group">
													<label for="codeInput">代码</label> <input type="text"
														name="roleCode" class="form-control" id="codeInput"
														placeholder="角色代码" maxlength="20">
													<label id="RoleCodeError" ></label>
												</div>
											</form>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
											<button type="button" class="btn btn-primary role-submit addrole">确定</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="space-div"></div>
						<table class="table table-hover table-bordered role-list" style="text-align: center;">
							<tr>
								<td>ID</td>
								<td>名称</td>
								<td>描述</td>
								<td>Code</td>
								<td>状态</td>
								<td>操作</td>
							</tr>				
							<c:forEach items="${rolesList}" var="role">
								<tr>
									<td class="roleid">${role.roleId }</td>
									<td class="roleName">${role.roleName}</td>
									<td class="roleDesc">${role.roleDesc}</td>
									<td class="roleCode">${role.roleCode}</td>
									<td>
										<c:if test="${role.roleState==0}">
										<span style="color: red;">禁用</span></c:if>
										<c:if test="${role.roleState==1}"><span style="color: green;">启用</span></c:if>
									</td>
									<td>
										<c:if test="${role.roleState==0}">
										<c:if test="${fn:contains(authCodes,'RoleStart')}">
											<button type="button" class="btn btn-warning start-using" >启用</button></c:if>
										</c:if>
										<c:if test="${role.roleState==1}">
										<c:if test="${fn:contains(authCodes,'Role-forbidden')}">
											<button type="button" class="btn btn-danger forbidden" >禁用</button></c:if>
										<c:if test="${fn:contains(authCodes,'role-assignAuth')}">
											<a href="${pageContext.request.contextPath}/auth/assignAuth.action?roleId=${role.roleId}">
											<button type="button" class="btn btn-success authority" >分配权限</button>
											</a>	
										</c:if>
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
		var passflag=false;
	//导出数据表格
		$("#exportroleexcel").click(function(){	
			//对页面有无数据进行判断
			if(${listsize}>0){
				var roleName=$("#roleName").val();
				var roleState=$("#roleState").val();
				if(confirm("确认导出吗？")){
					window.location.href="${pageContext.request.contextPath}/role/list.action?flag=0&roleState="+roleState+"&roleName="+roleName+"";			
				}	
			}else{
				showTips("无符合条件数据");
			}
		})
		
		//添加角色唯一性校验
		$(function(){
			$("#codeInput").keyup(function(){
				var codeInput=$("#codeInput").val();//获取用户名
				if(!!codeInput){
					$.ajax({
						type:"POST",
						dataType:"json",
						url:"${pageContext.request.contextPath}/role/addRoleVerify.action",
						data:{
							roleCode:codeInput
						},
						success:function(json){						
							if(json.msg==1){
								$("#RoleCodeError").addClass("red").html("角色code重复");						
							}else{
								$("#RoleCodeError").removeClass("red").html("");	
							}
						},
						error:function(){
							showTips("系统错误");
						}
					})
				}
			})
		})
		
		//添加角色
		$(".role-submit.addrole").click(function() {
		var roleName=$("#roleNameInput").val();//获取角色名
		var desc=$("#descInput").val();//获取角色详情
		var code=$("#codeInput").val();//获取角色code			
			//添加角色
			if(!!roleName && !!desc && !!code){
				$.ajax({						
						type : "POST",
						dataType : "json",
						url :"${pageContext.request.contextPath}/role/addRole.action",
						data : {
							roleName :roleName,
							roleDesc:desc,
							roleCode:code,									
						},	
						success : function(data) {
							$("#add-user-form").modal("hide");
							location.reload();							
						},
						error:function(){
							showTips("系统错误");
						}
				})				
			}else{
					alert("角色名称、角色详情、角色code不能为空！");
			}
		});

	//删除角色
	$(".role-list").on("click", ".delete-this-role", function() {
				var roleTr = $(this).parents("tr");
				var roleId = roleTr.find(".roleid").html();
				if (confirm('确认删除该角色吗？')) {
					//请求删除该用户
					$.ajax({
						url : "${pageContext.request.contextPath}/role/deleteRole.action",
						data : {
							roleId : roleId
						},
						dataType:"json",
						type : "POST",
						success : function() {
							roleTr.remove();
							showTips("删除成功！");
						}
					});
				}
			});
			
			//角色禁用
		$(".role-list").on("click",".forbidden",function() {
			var roleId = $(this).parents("tr").find(".roleid").html();
				if (confirm('确定要禁用？')) {
				//请求删除该用户
				$.ajax({
					type : "POST",
					dataType:"json",
					url : "${pageContext.request.contextPath}/role/updateRoleState.action",
					data : {
						roleState:0,
						roleId : roleId
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
		});
			//角色启用
		$(".role-list").on("click",".start-using",function() {
			var roleId = $(this).parents("tr").find(".roleid").html();	
				if (confirm('确定要启用？')) {
				//请求删除该用户
				$.ajax({
					type : "POST",
					dataType:"json",
					url : "${pageContext.request.contextPath}/role/updateRoleState.action",
					data : {
						roleState:1,
						roleId : roleId
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
		});
		
		//显示修改角色信息
		$(".role-list").on("click",".show-roleinfo-form",function() {						
						var roleId = $(this).parents("tr").find(".roleid").html();	
						var roleName = $(this).parents("tr").find(".roleName").html();	
						var roleDesc = $(this).parents("tr").find(".roleDesc").html();
						var roleCode = $(this).parents("tr").find(".roleCode").html();	
						$("input[name='roleId']").val(roleId);
						$("input[name='roleName']").val(roleName);
						$("input[name='roleDesc']").val(roleDesc);
						$("input[name='roleCode']").val(roleCode);							
		});
		$(".modal-footer").on("click",".update-role-submit",function(){
		})
		</script>
</body>
</html>
