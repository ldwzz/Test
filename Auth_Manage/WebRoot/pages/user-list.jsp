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

<title>用户管理 - 用户列表</title>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/static/css/layout.css"
	rel="stylesheet">
 <style type="text/css">
   	.red{color:red}
   	.update-userrole-form input{margin: 15px 18px 15px 3px;}
   	.user-form input{
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
			<h1 class="page-header">用户列表</h1>
			<div class="row placeholders">
				<form action="${pageContext.request.contextPath}/user/list.action?pageNum=${page.pageNum}" method="post" name="search">
					<div class="pull-right form-inline">
						<input type="text" name="userName"
							class="form-control form-inline" placeholder="用户名" id="userName" value="${param.userName}"> 
							<select class="form-control form-inline" name="userType" id="userType">
							<option value="" >用户类型</option>
							<option value="1" ${param.userType =='1' ?"selected='selected'":""}>超级管理员</option>
							<option value="2" ${param.userType =='2' ?"selected='selected'":""}>管理员</option>
							<option value="3" ${param.userType =='3' ?"selected='selected'":""}>普通用户</option>
							</select> 
							<select class="form-control form-inline" name="userState" id="userState">
							<option value="" >用户状态</option>
							<option value="0" ${param.userState =='0' ?"selected='selected'":""}>禁用</option>
							<option value="1" ${param.userState =='1' ?"selected='selected'":""}>启用</option>
							</select>
							
						<input type="date" name="startTime" class="form-control form-inline" id="startTime" value="${param.startTime}"/>
						至<input type="date" name="endTime" class="form-control form-inline" id="endTime" value="${param.endTime}"/>
						<button type="button" class="btn btn-primary form-inline" id="searchInput">确定查询</button>
					</div>
				</form>
				<br />
				<div>	
					<c:if test="${fn:contains(authCodes,'exportexcel-user')}">		
					<button type="button" class="btn btn-success"  id="exportexcel">导出表格</button></c:if>
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
					<c:if test="${fn:contains(authCodes,'user-addUser')}">
					<button type="button" class="btn btn-primary show-user-form"
						data-toggle="modal" data-target="#add-user-form">添加新用户</button>
					</c:if>				
					<!--添加新用户表单-->

					<div class="modal fade " id="add-user-form" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
										<h4 class="modal-title">添加新用户</h4>
								</div>
								<div class="modal-body">
									<form class="user-form">
										<div class="form-group add-user">
											<input type="text" name="usernames" class="form-control addUserNameInput" id="addUserNameInput"
												placeholder="用户名" maxlength="20">	<label id="userNameError" ></label>
											<input type="password"	name="userPwd" class="form-control" id="addPasswordInput"	placeholder="密码" maxlength="10">
												<label id="passwordError"></label>
											<input type="text"	name="nickName" class="form-control" id="addNickNameInput"
												placeholder="昵称" maxlength="20">
										<select name="createBy" class="form-control" id="addCreateBy" style="margin: 15px 18px 15px 3px;">
											<option value="">添加人</option>
											<option value="${sessionScope.userInfo.userId}">${sessionScope.userInfo.userName}</option>
										</select>
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
											<h4>用户名：</h4>
											<span class="form-control" id="assignUserName"></span>
											<input type="hidden" id="assignUserId"/>
												<div class="checkbox">
											<h4 style="margin-top: 30px;">可选角色：</h4>
											<c:forEach items="${rolesList}" var="role">
												<label><input type="checkbox" class="form-inline" value="${role.roleId}" name="userRole"/>${role.roleName}</label>
											</c:forEach>
											</div>												
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary asign-role-submit">确定
									</button>
								</div>
							</div>
						</div>
					</div>
					<!--分配部门-->
					<div class="modal fade " id="assign-group-form" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
										<h4 class="modal-title">分配部门</h4>
								</div>
								<div class="modal-body">
									<form class="user-form">
										<div class="form-group add-user">
											<h4>用户名：</h4>
											<span class="form-control" id="assignGroupName"></span>
											<input type="hidden" id="assignGroupId" class="assignGroupId"/>
												<div class="checkbox">
											<h4 style="margin-top: 30px;">可选部门：</h4>
											<c:forEach items="${groupList}" var="group">
												<label><input type="radio" class="form-inline" value="${group.groupId}" name="InputgroupId"/>${group.groupName}</label>
											</c:forEach>
											</div>												
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary asign-group-submit">确定
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="space-div"></div>
				<table class="table table-hover table-bordered user-list" style="text-align: center;">
					<tr>
						<td>ID</td>
						<td>用户名</td>
						<td>用户昵称</td>
						<td>用户类型</td>
						<td>用户状态</td>
						<td>用户组</td>
						<td>创建时间</td>
						<td>操作</td>
					</tr>					
					<c:forEach items="${page.resultList}" var="user">
						<tr>
							<td class="userid">${user.userId }</td>
							<td class="username">${user.userName}</td>
							<td class="nickname">${user.nickName}</td>
							<td class="usertype">
							<c:if test="${user.userType==1}">超级管理员</c:if>
							<c:if test="${user.userType==2}">管理员</c:if>
							<c:if test="${user.userType==3}">普通用户</c:if></td>
							<td class="userstate">
							<c:if test="${user.userState==0}"><span style="color: red;">禁用</span></c:if>
							<c:if test="${user.userState==1}"><span style="color: green;">启用</span></c:if></td>
							<td class="groupName">${user.groupName}
							<input type="hidden" class="groupIdhidden" value="${user.groupId}"/>
							</td>
							<td class="createtime">${user.createTime}</td>
							<td width="40%">
							<c:if test="${fn:contains(authCodes,'startusing')}">
								<c:if test="${user.userState==0}">
									<button type="button" class="btn btn-warning start-using" >启用</button>
								</c:if>
								</c:if>
								<c:if test="${fn:contains(authCodes,'forbidden')}">
								<c:if test="${user.userState==1}">
									<button type="button" class="btn btn-danger forbidden" >禁用</button>
								</c:if>	
								</c:if>
							<c:if test="${user.userState==1}">	
							<c:if test="${fn:contains(authCodes,'user-updateUser')}">	
							<a class="glyphicon glyphicon-wrench show-userrole-form"
								aria-hidden="true" title="修改所有角色" href="javascript:void(0);"
								data-toggle="modal" data-target="#update-userrole-dialog"></a></c:if> 
							<c:if test="${fn:contains(authCodes,'user-deleteUser')}">		
								<a class="glyphicon glyphicon-remove delete-this-user"
								aria-hidden="true" title="删除用户" href="javascript:void(0);"></a></c:if>
								<c:if test="${fn:contains(authCodes,'user-resetpwd')}">	
									<button type="button" class="btn btn-primary reset-password" >重置密码</button></c:if>
								<c:if test="${fn:contains(authCodes,'user-assignRole')}">		
									<button type="button" class="btn btn-primary assign-role" 
									data-toggle="modal"  data-target="#assign-role-form">分配角色</button></c:if>	
								<c:if test="${fn:contains(authCodes,'user-assignAuth')}">	
									<a href="${pageContext.request.contextPath}/auth/userAuthShow.action?userId=${user.userId}">
									<button type="button" class="btn btn-primary update-auth" >分配权限</button>
									</a></c:if>
								<c:if test="${fn:contains(authCodes,'user-assigngroup')}">	
									<button type="button" class="btn btn-primary assign-group" 
									data-toggle="modal"  data-target="#assign-group-form">分配用户组</button>
								</c:if>
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
							<div class="modal-body" >
								<form class="update-userrole-form form-group" name="updateuserinfo" action="" method="post">
									<input name="userId" type="hidden" class="form-control" />
									用户名<input name="username" type="text" class="form-control" readonly="readonly"/>
									昵称<input name="nickName" type="text" class="form-control" maxlength="20"/>
									密码(如果密码为空，则不修改密码)<input name="userPwd" type="password" class="form-control" id="inputPassword" maxlength="10"/>
									<label id="inputPasswordError"></label><br>
									修改人<select name="updateBy" class="form-control">
											<option value="">请选择</option>
											<option value="${sessionScope.userInfo.userId}">${sessionScope.userInfo.userName}</option>
										</select>
									修改时间<input name="updateTime" type="date" class="form-control" />
									<div class="roles-div"></div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
								<button type="button"
									class="btn btn-primary update-userrole-submit" id="update-user-info">提交</button>
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
	var passflag=false;
	var userflag=false;
	//导出数据表格
		$("#exportexcel").click(function(){	
			//对页面有无数据进行判断
			if(${listsize}>0){
				var userName=$("#userName").val();
				var userType=$("#userType").val();
				var userState=$("#userState").val();
				var startTime=$("#startTime").val();
				var endTime=$("#endTime").val();
				if(confirm("确认导出吗？")){
					showTips("已成功导出");
					window.location.href="${pageContext.request.contextPath}/user/list.action?flag=0&userType="+userType+"&userState="+userState+"&startTime="+startTime+"&endTime="+endTime+"";			
				}	
			}else{
				showTips("无符合条件数据");
			}
		})
		//时间校验
		$(function(){
			$("#searchInput").click(function(){
				var startTime=$("#startTime").val();
			var endTime=$("#endTime").val();
			if(!!startTime && !!endTime){
				if(startTime>endTime){
					showTips("起始时间不能大于结束时间！");
					return false;
				}else{
					window.search.submit();
				}
			}else{
				window.search.submit();
			}	
			})
		})
		//查询用户角色
		$(".user-list").on("click", ".assign-role", function(){
			var userid = $(this).parents("tr").find(".userid").html();
			var username = $(this).parents("tr").find(".username").html();
			$("#assignUserName").html(username);
			$("#assignUserId").val(userid);	
			$("input[name='userRole']").each(function(){
				$(this).prop("checked",false);
			})
			$.ajax({
				type:"POST",
				dataType:"json",
				url:"${pageContext.request.contextPath}/role/getRoles.action",
				data:{
					userId:userid
				},
				success:function(json){
					var userids= json.userIds.split(",");
					$("input[name='userRole']").each(function(){
						for(i=0;i<userids.length;i++){
							if(userids[i]==$(this).val()){
								$(this).prop("checked",true);
							}
						}
					})	
				},
				error:function(){
					alert("系统错误");
				}
			})
				
		})
		//给用户分配角色
		$(".asign-role-submit").click(function(){
			var roleIds="";
			var userId=$("#assignUserId").val();
			var length= $("input[name='userRole']:checked").length;
			$("input[name='userRole']:checked").each(function(i){
  				if(length==1 || i==length-1){
 					roleIds+=$(this).val();	
  				}else{
  					roleIds+=$(this).val()+",";	
  				}
			})
			if(roleIds.length>0){
				$.ajax({
				type:"POST",
				dataType:"json",
				url:"${pageContext.request.contextPath}/role/assignRoles.action",
				data:{
					userId:userId,
					roleIds:roleIds
				},
				success:function(data){
					if(data.msg==1){
						$("#assign-role-form").modal("hide");
						showTips("分配成功！");
					}else{
						showTips("修改失败！");
					}
				},
				error:function(){
					showTips("系统错误！");
				}
			})
			}else{
				showTips("未选中角色！");
			}
			
		})
		$(function(){
		//用户名,用户组回显
		$(".assign-group").click(function(){
			var roleIds="";
			var userid = $(this).parents("tr").find(".userid").html();
			var username = $(this).parents("tr").find(".username").html();
			var groupId = $(this).parents("tr").find(".groupName .groupIdhidden").val();
			$("#assignGroupName").text(username);
			$("#assignGroupId").val(userid);
			$("input[name='InputgroupId']").each(function(){			
				if($(this).val()==groupId){
					$(this).prop("checked",true);
				}
			})
		})
		})
		//分配用户组
		$(document).ready(function(){
			$(".asign-group-submit").click(function(){
				var length= $("input[name='InputgroupId']:checked").length;
				if(length>0){
					var groupId= $("input[name='InputgroupId']:checked").val();
				}
				var userId=$("#assignGroupId").val();
					$.ajax({
						type:"POST",
						dataType:"json",
						url:"${pageContext.request.contextPath}/user/assignGroup.action",
						data:{
							userId:userId,
							groupId:groupId
						},
						success:function(data){
							if(data.msg==1){
								$("#assign-group-form").modal("hide");
						//		location.reload();
								showTips("分配成功！");
							}else{
								showTips("分配失败！");
							}
						},
						error:function(){
							showTips("系统错误！");
						}
					})
				
						
			})
		})
		//提示框
		function showTips(content) {
			$("#op-tips-content").html(content);
			$("#op-tips-dialog").modal("show");
		}
		$(".select-all-btn").change(
				function() {
					if ($(this).is(":checked")) {//$(this).prop("checked")
						$(".user-list input[type='checkbox']:gt(0)").prop("checked", true);
					} else {
						$(".user-list input[type='checkbox']:gt(0)").prop("checked", false);
					}
				});
		$(".show-user-form").click(function() {
			getAllRoles($(".user-form .roles-div"));
		});
		//添加用户唯一性校验
		$(function(){
			$(".addUserNameInput").keyup(function(){
				var userName=$("#addUserNameInput").val();//获取用户名
				if(!!userName){
					$.ajax({
						type:"POST",
						dataType:"json",
						url:"${pageContext.request.contextPath}/user/addVerify.action",
						data:{
							userName:userName
						},
						success:function(json){						
							if(json.msg==1){
								userflag=false;
								$("#userNameError").addClass("red").html("用户名已注册");						
							}else{
								userflag=true;
								$("#userNameError").removeClass("red").html("");	
							}
						},
						error:function(){
							userflag=false;
							showTips("系统错误");
						}
					})
				}
			})
		})
		//添加用户
		$(".add-user-submit").click(function() {
		var userName=$("#addUserNameInput").val();//获取用户名
		var psw=$("#addPasswordInput").val();//获取密码
		var nickName=$("#addNickNameInput").val();//获取昵称
		var createBy=$("#addCreateBy").val();//获取部门			
			//请求添加新用户
			if(!/^\w{4,16}$/.test(userName)){
				//alert("用户名不合法! 4-16位，字母，数字，下划线");
				$("#userNameError").addClass("red").html("用户名不合法! 4-16位，字母，数字，下划线");
			}else 
			if(psw.length>16 || psw.length<6){
				$("#passwordError").addClass("red").html("密码不合法! 6-16位，字母，数字");				
			}else if(!!nickName && !!createBy && userflag){
				$.ajax({						
						type : "POST",
						dataType : "json",
						url :"${pageContext.request.contextPath}/user/addUserInfo.action",
						data : {
							userName :userName,
							userPwd:psw,
							nickName:nickName,
							createBy:createBy				
						},	
						success : function(data) {
							$("#add-user-form").modal("hide");
							showTips("添加成功！");
							location.reload();
							
						},
						error:function(){
							showTips("系统错误");
						}
				})				
			}else{
					showTips("昵称、添加人不能为空、用户名不能重复！");
			}
		});
		//修改用户信息页面显示
		$(".user-list").on("click", ".show-userrole-form", function() {
			var userid = $(this).parents("tr").find(".userid").html();
			var username = $(this).parents("tr").find(".username").html();
			var nickname = $(this).parents("tr").find(".nickname").html();
			$(".update-userrole-form input[name='userId']").val(userid);
			$(".update-userrole-form input[name='username']").val(username);
			$(".update-userrole-form input[name='nickName']").val(nickname);
		});
		//对用户修改密码进行校验
		$(function(){		
			$("#inputPassword").keyup(function(){
				var psw=$("#inputPassword").val();
				if(!!psw){
					if(psw.length>16 || psw.length<6){
						$("#inputPasswordError").addClass("red").html("密码不合法! 6-16位，字母，数字");					
						passflag=false;
					}else{
						$("#inputPasswordError").addClass("red").html("");
						passflag=true;					
					}
				}		
			})
		})
		//修改用户信息
		$(function(){
			$("#update-user-info").click(function(){
			var psw=$("#inputPassword").val();
				if(!psw){	
					passflag=true;							
				}
				if(passflag){
					userid=$(".update-userrole-form input[name='userId']").val();
					nickname=$(".update-userrole-form input[name='nickName']").val();
					userpwd=$(".update-userrole-form input[name='userPwd']").val();
					updateby=$(".update-userrole-form select[name='updateBy']").val();
					updatetime=$(".update-userrole-form input[name='updateTime']").val();
					if(!!updateby && !!updatetime){
						$.ajax({
							type:"POST",
							dataType:"json",
							url:"${pageContext.request.contextPath}/user/updateUserInfo.action",
							data:{
								userId:userid,
								nickName:nickname,
								userPwd:userpwd,
								updateBy:updateby,
								updateTime:updatetime
							},
							success:function(json){
								location.reload();
							},error:function(){
								showTips("系统错误！");
							}
						})
					}else{
						showTips("修改人、修改时间不能为空！");
					}
					
					
				}
								
			})
		})
		//删除用户
		$(".user-list").on("click", ".delete-this-user", function() {
			var userTr = $(this).parents("tr");
			var userid = userTr.find(".userid").html();
			var uid=${sessionScope.userInfo.userId};
			if(userid==uid){
				showTips("不能删除自己");
			}else{
				if (confirm('确认删除该用户吗？')) {
				//请求删除该用户
				$.ajax({
					type : "POST",
					dataType:"json",
					url : "${pageContext.request.contextPath}/user/deleteUser.action",
					data : {
						userId : userid
					},					
					success : function(json) {
						if(json.msg==1){
						var currNo=	${page.currNo}
						var totalPage=${page.totalPage}							
							userTr.remove();
							showTips("删除成功！");
							location.reload();
						}else{
							showTips("删除失败！");
						}				
					},
					error:function(){
						showTips("系统错误！");
					}
				});
			}
			}		
		});
		//删除用户
			var isdelete=false;
			$(".delete-selected-confirm").click(function() {
			$("#delete-confirm-dialog").modal("hide");
			var cbs = $("input[name='userIds']:checked");
			var uid=${sessionScope.userInfo.userId};
			var userIds="";
			if (cbs.length == 0) {
				showTips("没有选中任意项！");
			} else {
				$.each(cbs, function(i, cb) {
					if(uid!=$(this).val()){
							if(cbs.length==1 || i==cbs.length-1){
 								userIds+=$(this).val();	
  							}else{
  								userIds+=$(this).val()+",";	
  							}
  						isdelete=true;			
					}else{
						showTips("不能删除自己");
						isdelete=false;					
					}													
				});
				//删除所选用户
				if(isdelete){
				$.ajax({
					type : "POST",
					dataType:"json",
					url : "${pageContext.request.contextPath}/user/deleteUsers.action",
					data : {
						userIds:userIds
					},
					success : function() {
						cbs.parent().parent().remove();
						location.reload();
					},
					error:function(){
						showTips("系统错误！");
					}
				});
				}
			}
		});
		//重置密码
		$(".user-list").on("click",".reset-password",function() {
			var userTr = $(this).parents("tr");
			var userid = userTr.find(".userid").html();			
				if (confirm('确定重置密码？')) {
				//请求删除该用户
				$.ajax({
					type : "POST",
					dataType:"json",
					url : "${pageContext.request.contextPath}/user/resetPassword.action",
					data : {
						userId : userid
					},					
					success : function(json) {
						if(json.msg==1){
							showTips("已重置密码为：123456！");
						}else{
							showTips("重置密码失败！");
						}				
					},
					error:function(){
						showTips("系统错误！");
					}
				});			
			}		
		});
		
		//用户禁用
		$(".user-list").on("click",".forbidden",function() {
			var userTr = $(this).parents("tr");
			var userid = userTr.find(".userid").html();			
				if (confirm('确定要禁用？')) {
				//请求删除该用户
				$.ajax({
					type : "POST",
					dataType:"json",
					url : "${pageContext.request.contextPath}/user/updateUserState.action",
					data : {
						userState:0,
						userId : userid
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
			//用户启用
		$(".user-list").on("click",".start-using",function() {
			var userTr = $(this).parents("tr");
			var userid = userTr.find(".userid").html();			
				if (confirm('确定要启用？')) {
				//请求删除该用户
				$.ajax({
					type : "POST",
					dataType:"json",
					url : "${pageContext.request.contextPath}/user/updateUserState.action",
					data : {
						userState:1,
						userId : userid
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

	</script>
</body>
</html>
