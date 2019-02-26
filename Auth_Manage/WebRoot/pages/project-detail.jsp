<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<style type="text/css">
	th,td{
		text-align: center;
	}
	.tablehead{
		text-align: center;
	}
</style>
<title>项目管理 - 项目列表</title>
<!-- Bootstrap core CSS -->
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
			<h1 class="page-header">项目详情</h1>
			
			<table class="table table-hover" style="text-align: center;width: 80%;" align="center">
			<tr>
			<th >项目Id:</th>
			<th class="projectId">${projectDetail.projId} </th>
			<th >项目名称:</th>
			<th >${projectDetail.projName}
			<input type="hidden" id="projName" value="${projectDetail.projName}">
			</th>			
			</tr>
			<tr>
			<th >项目编号: </th>
			<th >${projectDetail.projNum} </th>
			<th >创建时间:</th>
			<th >${projectDetail.createTime} </th>
			</tr>
			<tr>
			<th >创建人: </th>
			<th >${projectDetail.createBy} </th>
			<th >总工时:</th>
			<th >${projectDetail.ableDay}</th>
			</tr>
			<tr>
			<th >可用工时: </th>
			<th >${projectDetail.ableDay-consTime  } </th>
			<th >已消耗时间:</th>
			<th >${consTime }</th>
			</tr>
			<tr>
			<th >项目状态:</th>
			<th > 
			<select disabled="disabled" id="projState" >
			<option value="0" <c:if test="${projectDetail.projState==0}">selected="selected" </c:if>>未开始</option>
			<option value="1" <c:if test="${projectDetail.projState==1}">selected="selected" </c:if> >进行中</option>
			<option value="2" <c:if test="${projectDetail.projState==2}">selected="selected" </c:if>>已暂停</option>
			<option value="3" <c:if test="${projectDetail.projState==3}">selected="selected" </c:if>>已完成</option>
			<option value="4" <c:if test="${projectDetail.projState==4}">selected="selected" </c:if>>已关闭</option>
			</select>
		    </th>
			<th >进度:</th>
			
				<td class="c-progress">
	<div class="progress progress-text-left">
          				 	 <div class="progress-bar progress-bar-success" role="progressbar"  aria-valuemin="0" aria-valuemax="100" style="width:${((consTime)/projectDetail.ableDay)*100}%" >
            					<span class="progress-text"><fmt:formatNumber value="${((consTime)/projectDetail.ableDay)*100}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber>%</span> 
           				 	</div>
         					</div>


							</td>
			
			
			
			</tr>
			<tr>
			<th >项目负责人: </th>
			<th >${projectDetail.projNames} </th>
			<th >系统分类:</th>
			<th >${projectDetail.classId}</th>
			</tr>
			<tr>
			<th >项目描述: </th>
			<th >${projectDetail.projDesc} </th>
			<th >访问类型:</th>
			<th >
			<c:if test="${projectDetail.vistorType==0}">默认公开</c:if>
			<c:if test="${projectDetail.vistorType==1}">项目组内部</c:if>
			<c:if test="${projectDetail.vistorType==2}">自定义白名单</c:if>
			</th>
			</tr>
			<tr>
			<th >起始时间 :</th>
			<th > 
			<%-- <input type="date" disabled="disabled" id="startTime" value="${projectDetail.startTime}">  --%>
			<fmt:parseDate value="${projectDetail.startTime}" pattern="yyyy-MM-dd" var="receiveDate1"></fmt:parseDate>
						<input type="date" name="startTime" id="startTime" class="form-control form-inline" id="startTime"  value="<fmt:formatDate value="${receiveDate1}" pattern="yyyy-MM-dd" ></fmt:formatDate>"  style="width:170px;margin-left:10px;" disabled="disabled"/>
			</th>
			<th >结束时间:</th>
			<th >  
			<%-- <input type="date" disabled="disabled" id="endTime"  value="${projectDetail.endTime}">--%> 
			<fmt:parseDate value="${projectDetail.endTime}" pattern="yyyy-MM-dd" var="receiveDate1"></fmt:parseDate>
						<input type="date" name="endTime" id="endTime" class="form-control form-inline" id="endTime"  value="<fmt:formatDate value="${receiveDate1}" pattern="yyyy-MM-dd" ></fmt:formatDate>"  style="width:170px;margin-left:10px;" disabled="disabled"/>
			
			</th> 
			</tr>
			<tr colspan="4">
			<th >备注: </th>
			
			</tr>

			</table>
			
			<center>
			<c:if test="${fn:contains(authCodes,'project-updateDetail')}">	
			  <button type="button" class="btn btn-primary update-projectDetail" style="font-size: 10px">修改</button>
			  </c:if>
			  &nbsp;&nbsp;&nbsp;&nbsp;
			  <c:if test="${fn:contains(authCodes,'project-saveDetail')}">	
			  <button type="button" class="btn btn-primary save-projectDetail" style="font-size: 10px">保存</button>
			 </c:if>
			 </center>
			
			 <br><br><br><br>
			 
			 <center>
			 <h3 class="page-header" style="display :inline;">最新项目动态</h3></center>
			<button type="button" class="btn btn-primary insert-projectDynamic"
				data-toggle="modal"  data-target="#post-message-form" style="margin-left: 870px;">发表动态</button><hr>
			 <c:forEach  items="${lists}" var="lists">
				<div class="card">
					 	<div class="card-header">
						    <p style="color:dodgerblue;font-size: 20px;">${lists.userName}</p>
						</div>
						  <div class="card-block"> 
						    <input type="hidden" class="card-id" value="${lists.dynamicId}">
						    <h4 class="card-title">${lists.dynamicDesc}</h4>
						    <p class="card-text" style="display :inline;">${lists.createTime}</p>
						    <c:if test="${userInfo.userName==lists.userName }">
						    <a class="deleteDynamicMessage" style="cursor:pointer;">删除</a>
						    </c:if>
						     <input type="hidden" class="dynamicId" value="${lists.dynamicId}"/>
						    <button type="button" class="btn btn-primary post-comment"
				data-toggle="modal"  data-target="#post-comment-form" style="margin-left: 875px;">&nbsp;&nbsp;&nbsp;评论&nbsp;&nbsp;&nbsp;</button><hr/>
					  	</div>
					  	
					  	 
					  	  <c:forEach  items="${lists.comment}" var="list" >
					  	   		
					  		<div class="list-group">							
								    <h4 class="list-group-item-heading" style="display:inline;">${list.userName}&nbsp;回复${list.userNames}：${list.dynamicDesc}</h4>
							  		--------${list.createTime}
							  		 <input type="hidden" class="dynamicIds" value="${list.dynamicId}"/>
							  		<c:if test="${userInfo.userName==list.userName }"><a class="deleteDynamicMessage1" style="cursor:pointer;display:inline;">删除</a></c:if>
							        <c:if test="${userInfo.userName!=list.userName }"><button type="button" class="retuenDynamicMessage"
				data-toggle="modal"  data-target="#post-comment-form" >回复</button></c:if>
							</div>
						
							</c:forEach>
						
				</div><hr>
			</c:forEach>
			<c:if test="${fn:contains(authCodes,'project-seeDynamic')}">
			<a href="${pageContext.request.contextPath}/project/AllDynamicMessage.action?projId=${projectDetail.projId}">查看更多</a>
		</c:if>
		</div>
		<!--发表动态-->
					<div class="modal fade " id="post-message-form" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
										<h4 class="modal-title">发表动态</h4>
								</div>
								<div class="modal-body">
									<form class="user-form">
										<div class="form-group add-user">											
											<textarea cols="35" rows="10" placeholder="在这里输入内容..." id="Input-post-message"></textarea>										
											<input type="hidden" value="${projectDetail.projId}" id="projectDetail_projId"/>
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary post-message">发表
									</button>
								</div>
							</div>
						</div>
					</div>
					<!--评论-->
					<div class="modal fade " id="post-comment-form" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
										<h4 class="modal-title">评论</h4>
								</div>
								<div class="modal-body">
									<form class="user-form">
										<div class="form-group add-user">											
											<textarea cols="35" rows="10" placeholder="在这里输入内容..." id="Input-post-comment"></textarea>										
											<input type="hidden" value="" id="projectProjId"/>
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary assign-comment">发表
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
</body>
<script type="text/javascript">
 //发表动态
	$(function(){
		$(".post-message").click(function(){
			var dynamicDesc=$("#Input-post-message").val().trim();
			var projId=$("#projectDetail_projId").val();
			if(!!dynamicDesc){
				$.ajax({
						type : "POST",
						dataType:"json",
						url : "${pageContext.request.contextPath}/project/insertDynamicMessage.action",
						data : {
							dynamicDesc:dynamicDesc,
							projId:projId,
							userName:'${sessionScope.userInfo.userName}'
						},
						success : function(data) {
							if(data.msg==1){
								$("#post-message-form").modal("hide");
								showTips("发表成功");
								location.reload();
							}else{
								showTips("发表失败");
							}
						},
						error:function(){
							showTips("系统错误");
						}
				})
			}
		})
	})
	$(".post-comment").click(function(){
		var dynamicId=$(this).parents(".card-block").find(".dynamicId").val();	
		$("#projectProjId").val(dynamicId);
	})

	//评论
	$(function(){
		$(".assign-comment").click(function(){
			var dynamicDesc=$("#Input-post-comment").val().trim();
			var projId=${projectDetail.projId};
			var dynamicId=$("#projectProjId").val();		
			if(!!dynamicDesc){
				$.ajax({
						type : "POST",
						dataType:"json",
						url : "${pageContext.request.contextPath}/project/insertDynamicMessages.action",
						data : {
							dynamicDesc:dynamicDesc,
							projId:projId,
							userName:'${sessionScope.userInfo.userName}',
							parentId:dynamicId
						},
						success : function(data) {
							if(data.msg==1){
								$("#post-message-form").modal("hide");
								showTips("评论成功");
								location.reload();
							}else{
								showTips("评论失败");
							}
						},
						error:function(){
							showTips("系统错误");
						}
				})
			}
		})
	})
	
	$(".retuenDynamicMessage").click(function(){
		var dynamicId=$(this).parents(".list-group").find(".dynamicIds").val();	
		$("#projectProjId").val(dynamicId);
	})
	
	//回复
	$(function(){
		$(".retuenDynamicMessage").click(function(){
			var dynamicDesc=$("#Input-post-comment").val().trim();
			var projId=${projectDetail.projId};
			var dynamicId=$("#projectProjId").val();		
			if(!!dynamicDesc){
				$.ajax({
						type : "POST",
						dataType:"json",
						url : "${pageContext.request.contextPath}/project/insertDynamicMessages.action",
						data : {
							dynamicDesc:dynamicDesc,
							projId:projId,
							userName:'${sessionScope.userInfo.userName}',
							parentId:dynamicId
						},
						success : function(data) {
							if(data.msg==1){
								$("#post-message-form").modal("hide");
								showTips("评论成功");
								location.reload();
							}else{
								showTips("评论失败");
							}
						},
						error:function(){
							showTips("系统错误");
						}
				})
			}
		})
	})
	
//修改项目信息
        
        $(".btn.btn-primary.update-projectDetail").click(function(){
         $("#projState").prop("disabled",false);
         $("#startTime").prop("disabled",false);
         $("#endTime").prop("disabled",false); 
        });
//确认修改
      $(".btn.btn-primary.save-projectDetail").click(function(){
         var projState=$("#projState").val();
         var startTime=$("#startTime").val();
         var endTime=$("#endTime").val();
         var projName=$("#projName").val();
         var projId=${projectDetail.projId};
         if(startTime==null || startTime==""){
          alert("参数不能为空");
         }else{
     
        $.ajax({
						type : "POST",
						dataType:"json",
						url : "${pageContext.request.contextPath}/project/updateProjectInfo.action",
						data : {
							projState:projState,
							projId:projId,
							startTime:startTime,
							endTime:endTime,
							projName:projName,
						},
						success : function(data) {
							if(data.msg==1){
								$("#post-message-form").modal("hide");
								showTips("修改成功");
								location.reload();
							}else{
								showTips("修改失败");
							}
						},
						error:function(){
							showTips("系统错误");
						}
				})
        }
        });
	//提示框
		function showTips(content) {
			$("#op-tips-content").html(content);
			$("#op-tips-dialog").modal("show");
		}
//删除动态
 $(".deleteDynamicMessage").click(function(){

   var projId=$(".projectId").text().trim();
   var dynamicDesc=$(this).parents(".card-block").find(".card-title").text();
   var dynamicId=$(this).parents(".card-block").find(".card-id").val();
     
        $.ajax({
						type : "POST",
						dataType:"json",
						url : "${pageContext.request.contextPath}/project/deleteProjectDynamic.action",
						data : {
							projId: projId,
							dynamicDesc: dynamicDesc,
							dynamicId: dynamicId,
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
 //删除评论
 $(".deleteDynamicMessage1").click(function(){

   var projId=$(".projectId").text().trim();
   var dynamicDesc=$(this).parents(".list-group").find(".list-group-item-heading").text();
   var dynamicId=$(this).parents(".list-group").find(".dynamicIds").val();
     
        $.ajax({
						type : "POST",
						dataType:"json",
						url : "${pageContext.request.contextPath}/project/deleteProjectDynamic.action",
						data : {
							projId: projId,
							dynamicDesc: dynamicDesc,
							dynamicId: dynamicId,
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
