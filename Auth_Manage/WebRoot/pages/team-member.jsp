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

<title>项目管理 - 项目组列表</title>
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
			<h1 class="page-header">项目组成员列表</h1>
			<div class="row placeholders">
				<form id="chengyuan" action="${pageContext.request.contextPath}/projectTeam/findTeamMembers.action?pageNum=${page.pageNum} " method="post" name="search" autocomplete="off">
					<div class="pull-right form-inline">
						
						<input type="text" name="userName"
							class="form-control form-inline" placeholder="项目组成员名称" id="userName" value="${param.userName}" style="width: 150px"> 
						
						
						
						<button type="button" class="btn btn-primary form-inline" id="searchInput">确定查询</button>
					</div>
				</form>
				<br />
				<div>
				
				<br><br>
					
					<c:if test="${fn:contains(authCodes,'member-addMember')}">	
					<button type="button" class="btn btn-primary show-teamMember-form"
						data-toggle="modal" data-target="#add-project-form">添加项目组成员</button>
					</c:if>
					
				<div class="space-div"></div>
				<table class="table table-hover table-bordered user-list" style="text-align: center;">
					<tr>
						<td>ID</td>
						<td>项目组成员名称</td>
						<td>所属项目组</td>				
						<td>操作</td>
					</tr>					
					<c:forEach items="${page.resultList}" var="projectTeam">
						<tr>
							<td class="userid">${projectTeam.memberId}</td>
							<td class="username">${projectTeam.userName}</td>
							<td class="nickname" >${projectTeam.teamName}
							</td>
												
							<td width="35%">	
							<c:if test="${fn:contains(authCodes,'member-allotMember')}">												
							<button type="button" class="btn btn-primary allot-teamMember" style="font-size: 10px"
							data-toggle="modal"  data-target="#assign-allotPeople-form">人员调配</button>
							</c:if>	
							</td>
						</tr>
						
					</c:forEach>
				</table>
				<input type="hidden" value="${teamName}" id="projectTeamteamName">	
				<c:forEach items="${page.resultList}" var="projectTeam">
				<input type="hidden" value="${projectTeam.teamId }" id="projectTeamteamId">
				
				</c:forEach>			
				<jsp:include page="standard.jsp" />
				
					<!--人员调配-->
					<div class="modal fade " id="assign-allotPeople-form" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-md" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
										<h4 class="modal-title">人员调配</h4>
								</div>
								<div class="modal-body">
									<form class="user-form" autocomplete="off">
										<div class="form-group add-user">
											<h4>项目组名：</h4>
											<div id="allprojectTeam" style="margin-left: 20px"></div>
											<br><br><br>										
										</div>
									</form>
									<div id="contextWhite"  style="background-color: white;border: 1px solid gray;width: 265px;position: absolute;top:250px; left:20px;display: none;"> </div>
								</div>
								<input type="hidden" id="yincang"> 
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary asign-allotPeople-submit">确定
									</button>
								</div>
							</div>
						</div>
					</div>
				
				
				<!--添加新项目组成员表单-->

					<div class="modal fade " id="add-project-form" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
										<h4 class="modal-title">添加新项目组成员</h4>
								</div>
								<div class="modal-body">
									<form class="user-form" autocomplete="off">
										<div class="form-group add-user">
										   <b>项目组名称：</b> 
											<input type="text" name="projName" class="form-control " id="addprojectTeamName" readonly="readonly">	
											<b>项目组成员：</b>         
											<input type="text"	name="projDesc" class="form-control" id="addprojectuserName"	>
											<div id="context" style="background-color: white;border: 1px solid gray;width: 260px;position: absolute;top:122px; left:20px;display: none;"> </div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary add-project-submit">添加
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
//查询
		$(function(){
			$("#searchInput").click(function(){
			var teamId=$("#projectTeamteamId").val();
			
			$("#chengyuan").prop("action","${pageContext.request.contextPath}/projectTeam/findTeamMembers.action?pageNum=${page.pageNum}&teamId="+teamId)
					window.search.submit();
			
			})
		})
//提示框
		function showTips(content) {
			$("#op-tips-content").html(content);
			$("#op-tips-dialog").modal("show");
		}
//新建项目组成员按钮
		$(".show-teamMember-form").click(function() {
		  
			//请求添加新项目
			var teamNames=$("#projectTeamteamName").val();
			
			$("#addprojectTeamName").val(teamNames);
			
			
		});
//新建项目组成员 
		$(".add-project-submit").click(function() {
		var teamName=$("#addprojectTeamName").val();
		var userName=$("#addprojectuserName").val();
			
			if(!!teamName && !!userName){
			//请求添加新项目
	    		
				$.ajax({						
						type : "POST",
						dataType : "json",
						url :"${pageContext.request.contextPath}/projectTeam/insertProjectTeamMember.action",
						data : {
							teamName :teamName,							
							userName :userName,							
						},	
						success : function(data) {
							$("#add-user-form").modal("hide");
							if(data.msg=="2"){
							showTips("添加成功！");
							location.reload();
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
//自动补全(项目组成员名称搜索)
        $(function(){
        $("#addprojectuserName").keyup(function(){       
        var userName=$(this).val();
        var teamName=$("#addprojectTeamName").val();
        //当前搜索内容为空，无需查询
        if(userName==""){
        $("#context").css("display","none");
        return ;
        }
        //alert(userName+teamName);
        //由于浏览器的缓存机制 所以我们每次传入一个时间
        var time=new Date().getTime();
        $.ajax({
            type:"post",      
            url:"${pageContext.request.contextPath}/projectTeam/findAutoCompleteProjTeamUserName.action",
            data:{
            userName:userName,
            teamName:teamName,
            time:time},
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
                $("#context").html(html);
                //显示为块级元素
                $("#context").css("display","block");
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
        $("#addprojectuserName").val(div.innerText);
        $("#context").css("display","none");
    }
//人员调配按钮 
    $(".allot-teamMember").click(function() {
		var teamId=$("#projectTeamteamId").val();
		var userName=$(this).parents("tr").find(".username").text().trim();
	   $("#yincang").val(userName);
	   var flag=true;
	   
	   $.ajax({
	        async:false,
            type:"post",      
            url:"${pageContext.request.contextPath}/projectTeam/allotPeoples.action",
            data:{
            teamId:teamId,
            userName:userName,
            },
            dataType:"json",
            success:function(data){
            
            if(data.msg==0){
            var html="";
            var check1=data.shu1;	
			var check2=data.shu2;	
			for( i = 0; i < check2.length; i++) {
             if(check2[i]!=""){            
             html+="<div style='width: 25px;float: left;'><input type='radio' name='projectTeam' value='"+check1[i]+"'  width='50px'></div> <div style='width: 80px;float: left;'>"+check2[i]+"</div>"
				}
			}
			//往div里面写入多选框
			$("#allprojectTeam").html(html);
			 }else{
			 flag=false;
			 //alert(data.msg);
             alert("请先对任务进行维护")
            }	
            }
           
            });
            return flag;
	  
     }); 
//确认调配

 $(".asign-allotPeople-submit").click(function() {
  var userName=$("#yincang").val();
    var teamId=0;
 $("input[name='projectTeam']:checked").each(function(){
  teamId=$(this).val();	
 });
    $.ajax({
	        
            type:"post",      
            url:"${pageContext.request.contextPath}/projectTeam/updateallotPeoples.action",
            data:{
            teamId:teamId,
            userName:userName,
            },
            dataType:"json",
            success:function(data){
            if(data.msg==2){
            showTips("分配成功！");
            location.reload();
            }
            }
 
 });
});
</script>
</html>
