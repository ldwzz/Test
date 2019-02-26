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
	th,td{
		text-align: center;
	}
	.tablehead{
		text-align: center;
	}
</style>
<title>项目管理 - 项目动态</title>
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
			<h1 class="page-header">项目动态</h1>
			<div class="row placeholders">
				<form action="${pageContext.request.contextPath}/project/AllDynamicMessage.action?pageNum=${page.pageNum}" method="post" name="search"autocomplete="off">
					<div class="pull-right form-inline">
						<input type="text" name="dynamicDesc"
							class="form-control form-inline" placeholder="动态名称" id="dynamicDesc" value="${param.dynamicDesc}" style="width: 150px"> 
						<input type="text" name="userName"
							class="form-control form-inline" placeholder="创建人" id="userName" value="${param.userName}" style="width: 100px"> 
						<input type="hidden" value="${param.projId}" name="projId" id="InputprojId"/>
						<input type="text" name="userNames"
							class="form-control form-inline" placeholder="回复人" id="userNames" value="${param.userNames}" style="width: 100px"> 
						<input type="date" name="startTime" class="form-control form-inline" id="startTime" value="${param.startTime}"/>
						至<input type="date" name="endTime" class="form-control form-inline" id="endTime" value="${param.endTime}"/>
						<button type="button" class="btn btn-primary form-inline" id="searchInput">确定查询</button>
					</div>
				</form>
				
				<div>
				<div id="context2" style="background-color: white;border: 1px solid gray;width: 150px;position: absolute;top:122px; left:277px;display: none;"> </div>	
				<div id="context1" style="background-color: white;border: 1px solid gray;width: 100px;position: absolute;top:122px; left:430px;display: none;"> </div>
				<div id="context3" style="background-color: white;border: 1px solid gray;width: 100px;position: absolute;top:122px; left:535px;display: none;"> </div>				
		<br /><br /><br /><br />
			<button type="button" class="btn btn-primary insert-projectDynamic"
				
				data-toggle="modal"  data-target="#post-message-form" style="margin-left: 870px;">发表动态</button><hr>
			 <c:forEach  items="${page.resultList}" var="lists">
				<div class="card">
					 	<div class="card-header">
						    <p style="color:dodgerblue;font-size: 20px;">${lists.userName}</p>
						</div>
						  <div class="card-block">
						  <input type="hidden" class="card-id" value="${lists.dynamicId}">
						    <h4 class="card-title">${lists.dynamicDesc}</h4>
						    <input type="hidden" class ="projectIds" value="${lists.projId}"  >
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
			<jsp:include page="standard.jsp" />
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
											<input type="hidden" value="${projectDynamic.projId }" id="projectDetail_projId"/>
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

	//提示框
		function showTips(content) {
			$("#op-tips-content").html(content);
			$("#op-tips-dialog").modal("show");
		}
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
			var projId=${projectDynamic.projId };
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
			var projId=${projectDynamic.projId };
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
//自动补全(动态创建人搜索)
        $(function(){
        $("#userName").keyup(function(){       
        var content=$(this).val();
        //当前搜索内容为空，无需查询
        if(content==""){
        $("#context1").css("display","none");
        return ;
        }
        
        //由于浏览器的缓存机制 所以我们每次传入一个时间
        var time=new Date().getTime();
        $.ajax({
            type:"post",      
            url:"${pageContext.request.contextPath}/project/findAutoCompleteProjectDynamic.action",
            data:{userName:content,time:time},
            dataType:"json",
            success:function(data){
                var names=data.msg;              
                //拼接html
                var res=names.split(",");
                var html="";
                for(var i=0;i<res.length;i++){
                    //每一个div还有鼠标移出、移入点击事件
                    html+="<div onclick='setSearch_onclick(this)' onmouseout='changeBackColor_out(this)' onmouseover='changeBackColor_over(this)'>"+res[i]+"</div>";
                }
                $("#context1").html(html);
                //显示为块级元素
                $("#context1").css("display","block");
            }
            
        });
    });
        });
    //鼠标移动到内容上
    function changeBackColor_over(div){
        $(div).css("background-color","#CCCCCC");
    }
    //鼠标离开内容
    function changeBackColor_out(div){
        $(div).css("background-color","");
    }
    //将点击的内容放到搜索框
    function setSearch_onclick(div){
        $("#userName").val(div.innerText);
        $("#context1").css("display","none");
    }
//自动补全(动态描述搜索)
        $(function(){
        $("#dynamicDesc").keyup(function(){       
        var content=$(this).val();
        //当前搜索内容为空，无需查询
        if(content==""){
        $("#context2").css("display","none");
        return ;
        }
        
        //由于浏览器的缓存机制 所以我们每次传入一个时间
        var time=new Date().getTime();
        $.ajax({
            type:"post",      
            url:"${pageContext.request.contextPath}/project/findAutoCompleteProjectDynamic.action",
            data:{dynamicDesc:content,time:time},
            dataType:"json",
            success:function(data){
                var names=data.msg;              
                //拼接html
                var res=names.split(",");
                var html="";
                for(var i=0;i<res.length;i++){
                    //每一个div还有鼠标移出、移入点击事件
                    html+="<div onclick='setSearch1_onclick(this)' onmouseout='changeBackColor_out(this)' onmouseover='changeBackColor_over(this)'>"+res[i]+"</div>";
                }
                $("#context2").html(html);
                //显示为块级元素
                $("#context2").css("display","block");
            }
            
        });
    });
        });

    //将点击的内容放到搜索框
    function setSearch1_onclick(div){
        $("#dynamicDesc").val(div.innerText);
        $("#context2").css("display","none");
    }
//自动补全(动态回复人搜索)
        $(function(){
        $("#userNames").keyup(function(){       
        var content=$(this).val();
        //当前搜索内容为空，无需查询
        if(content==""){
        $("#context3").css("display","none");
        return ;
        }
        
        //由于浏览器的缓存机制 所以我们每次传入一个时间
        var time=new Date().getTime();
        $.ajax({
            type:"post",      
            url:"${pageContext.request.contextPath}/project/findAutoCompleteProjectDynamic.action",
            data:{userNames:content,time:time},
            dataType:"json",
            success:function(data){
                var names=data.msg;              
                //拼接html
                var res=names.split(",");
                var html="";
                for(var i=0;i<res.length;i++){
                    //每一个div还有鼠标移出、移入点击事件
                    html+="<div onclick='setSearch2_onclick(this)' onmouseout='changeBackColor_out(this)' onmouseover='changeBackColor_over(this)'>"+res[i]+"</div>";
                }
                $("#context3").html(html);
                //显示为块级元素
                $("#context3").css("display","block");
            }
            
        });
    });
        });

    //将点击的内容放到搜索框
    function setSearch2_onclick(div){
        $("#userNames").val(div.innerText);
        $("#context3").css("display","none");
    }  
 //删除动态
 $(".deleteDynamicMessage").click(function(){

   var projId=$(".projectIds").val();
   var dynamicDesc=$(this).parents(".card-block").find(".card-title").text();
   var dynamicId=$(this).parents(".card-block").find(".card-id").val();
     //alert(projId+"aa"+dynamicDesc+"aa"+dynamicId)
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

   var projId=$(".projectIds").val();
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
