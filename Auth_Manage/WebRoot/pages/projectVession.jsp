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

<title>项目管理 - 项目列表</title>
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
			<h1 class="page-header">项目版本列表</h1>
			<input type="hidden" id="yincang1" value="${projId}">
			<div class="row placeholders">
				
				<br />
				<div>
				<div id="context2" style="background-color: white;border: 1px solid gray;width: 150px;position: absolute;top:122px; left:118px;display: none"> </div>	
				<div id="context1" style="background-color: white;border: 1px solid gray;width: 150px;position: absolute;top:122px; left:273px;display: none"> </div>
				<div id="context3" style="background-color: white;border: 1px solid gray;width: 100px;position: absolute;top:122px; left:426px;display: none"> </div>
				<br><br>
					
					
					<button type="button" class="btn btn-primary show-user-form"
						data-toggle="modal" data-target="#add-project-form">添加版本</button>
					
					
				<div class="space-div"></div>
				<table class="table table-hover table-bordered user-list" style="text-align: center;">
					<tr>
						<td>ID</td>
						<td>版本号</td>
						<td>版本说明</td>
						<td>创建时间</td>
						<td>创建人</td>					
						<td>操作</td>
					</tr>					
					<c:forEach items="${page.resultList}" var="projectVersion">
						<tr>
							<td class="userid">${projectVersion.versionId}</td>
							<td class="username">${projectVersion.versionNum}</td>
							<td class="nickname">${projectVersion.versionDesc}</td>					
							<td class="userstate">${projectVersion.createTime}</td>
							<td class="groupName">${projectVersion.userName}
							
							</td>
							<td width="35%">	
							<c:if test="${fn:contains(authCodes,'team-seeMember')}">												
							<button type="button" class="btn btn-primary update-version" style="font-size: 10px"
							data-toggle="modal"  data-target="#update-version-form">修改</button>
							</c:if>	
							<c:if test="${fn:contains(authCodes,'team-seeMember')}">												
							<button type="button" class="btn btn-primary delete-version" style="font-size: 10px"
							 >删除</button>
							</c:if>	
							</td>
						</tr>
					</c:forEach>
				</table>			
				<jsp:include page="standard.jsp" />
				
					<!--修改项目版本信息-->
					<div class="modal fade " id="update-version-form" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
										<h4 class="modal-title">修改版本信息</h4>
								</div>
								<div class="modal-body">
									<form class="user-form" autocomplete="off">
										<div class="form-group add-user">
											<b>项目名称：</b> 
											<input type="text" name="projName" class="form-control " id="addteam_projName" readonly="readonly">	
											<b>项目版本说明：</b>                      
											<input type="text"	name="projDesc" class="form-control" id="addteam_versionDesc"	>
	                                        <input type="hidden"	name="vesionid" class="form-control" id="addteam_versionId"	>
																						
										</div>
									</form>
									<div id="contextWhite"  style="background-color: white;border: 1px solid gray;width: 265px;position: absolute;top:250px; left:20px;display: none;"> </div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary update-version-submit">确定
									</button>
								</div>
							</div>
						</div>
					</div>
				
				
				<!-- 添加项目版本信息 -->

					<div class="modal fade " id="add-project-form" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
										<h4 class="modal-title">添加版本</h4>
								</div>
								<div class="modal-body">
									<form class="user-form">
										<div class="form-group add-user">
										    <b>版本号：</b> 
											<input type="text" name="projName" class="form-control " id="version_number">	
											<b>版本说明：</b>                      
											<input type="text"	name="projDesc" class="form-control" id="version_code"	>
										
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary add-version-submit">添加
									</button>
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
</body>
<script type="text/javascript">
//提示框
		function showTips(content) {
			$("#op-tips-content").html(content);
			$("#op-tips-dialog").modal("show");
		}
	
//新建版本   
		$(".add-version-submit").click(function() {
		var projId=$("#yincang1").val();
		var versionNum=$("#version_number").val();
		var versionDesc=$("#version_code").val();
       if(!!projId && !!versionNum && !!versionDesc ){
       
			//请求添加新项目
				$.ajax({						
						type : "POST",
						dataType : "json",
						url :"${pageContext.request.contextPath}/project/insertVersion.action",
						data : {
							projId :projId,
							versionNum :versionNum,
							versionDesc :versionDesc,
											
						},	
						success : function(data) {
							$("#add-version-form").modal("hide");
							if(data.res=="0"){
							showTips("添加成功！");
							location.reload();
							}else{
							  showTips("版本号必须大于原来的版本号");
							}
						},
						error:function(){
							showTips("系统错误");
						}
				})	
				}else{
       showTips("请填写完整");
       }			
			
		});
//修改版本信息按钮     
     $(".update-version").click(function(){
     var projName=$(this).parents("tr").find(".groupName").html();
     var versionDesc=$(this).parents("tr").find(".nickname").html();
     var versionId=$(this).parents("tr").find(".userid").html();
     $("#addteam_projName").val(projName);
     $("#addteam_versionDesc").val(versionDesc);
     $("#addteam_versionId").val(versionId);
     });
//修改版本信息
     $(".update-version-submit").click(function(){
       var versionDesc=$("#addteam_versionDesc").val();
       var versionId=$("#addteam_versionId").val();
       $.ajax({						
						type : "POST",
						dataType : "json",
						url :"${pageContext.request.contextPath}/project/updateVersion.action",
						data : {
                            versionDesc:versionDesc,
							versionId:versionId,				
						},	
						success : function(data) {
						if(data.res==2){
						   showTips("修改成功");
						   location.reload();
						}
						}
						});
     });
 //删除动态
 $(".delete-version").click(function(){

   var projId=$("#yincang1").val();
   var versionId=$(this).parents("tr").find(".userid").html();
   var versionDesc=$(this).parents("tr").find(".nickname").html();
     
        $.ajax({
						type : "POST",
						dataType:"json",
						url : "${pageContext.request.contextPath}/project/deleteVersion.action",
						data : {
							projId: projId,
							versionDesc: versionDesc,
							versionId: versionId,
						},
						success : function(data) {
						 if( data.msg==0){
						  showTips("该项目没有删除动态模块请添加");
						 }else{
						 showTips("删除成功");
						 location.reload();
						 }
						}
						});
 });    
</script>
</html>
