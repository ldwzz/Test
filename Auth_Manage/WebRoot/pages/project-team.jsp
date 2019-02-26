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
			<h1 class="page-header">项目组列表</h1>
			<div class="row placeholders">
				<form action="${pageContext.request.contextPath}/projectTeam/list.action?pageNum=${page.pageNum}" method="post" name="search" autocomplete="off">
					<div class="pull-right form-inline">
						
						<input type="text" name="teamName"
							class="form-control form-inline" placeholder="项目组名称" id="team_name" value="${param.teamName}" style="width: 150px"> 
						
						<input type="text" name="projName"
							class="form-control form-inline" placeholder="项目名称" id="projName" value="${param.projName}" style="width: 100px"> 
										
						<input type="text" name="userName" class="form-control form-inline" placeholder="项目负责人" id="userName" value="${param.userName}"/>
						
						<button type="button" class="btn btn-primary form-inline" id="searchInput">确定查询</button>
					</div>
				</form>
				<br />
				<div>
				<!-- <div id="context2" style="background-color: white;border: 1px solid gray;width: 150px;position: absolute;top:122px; left:118px;display: none"> </div>	
				<div id="context1" style="background-color: white;border: 1px solid gray;width: 150px;position: absolute;top:122px; left:273px;display: none"> </div>
				<div id="context3" style="background-color: white;border: 1px solid gray;width: 100px;position: absolute;top:122px; left:426px;display: none"> </div> -->
				<br><br>
					
					<c:if test="${fn:contains(authCodes,'team-addTeam')}">	
					<button type="button" class="btn btn-primary show-user-form"
						data-toggle="modal" data-target="#add-project-form">添加项目组</button>
					</c:if>
					
				<div class="space-div"></div>
				<table class="table table-hover table-bordered user-list" style="text-align: center;">
					<tr>
						<td>ID</td>
						<td>项目组名</td>
						<td>项目组编号</td>
						<td>项目组描述</td>
						<td>项目组负责人</td>
						<td>所属项目</td>
						<td>操作</td>
					</tr>					
					<c:forEach items="${page.resultList}" var="projectTeam">
						<tr>
							<td class="userid">${projectTeam.teamId}</td>
							<td class="username">${projectTeam.teamName}</td>
							<td class="nickname">${projectTeam.teamCode}</td>					
							<td class="userstate">${projectTeam.teamDesc}</td>
							<td class="groupName">${projectTeam.userName}</td>
							<td class="createtime">${projectTeam.projName}</td>
							
							<td width="35%">	
							<c:if test="${fn:contains(authCodes,'team-seeMember')}">												
							<a href="${pageContext.request.contextPath}/projectTeam/findTeamMembers.action?teamId=${projectTeam.teamId}&teamName=${projectTeam.teamName}"><button type="button" class="btn btn-primary reset-password" style="font-size: 10px">查看项目组成员</button></a>
							</c:if>		
							</td>
						</tr>
					</c:forEach>
				</table>				
				<jsp:include page="standard.jsp" />

				
				<!--添加新项目组-->

					<div class="modal fade " id="add-project-form" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
										<h4 class="modal-title">添加新项目组</h4>
								</div>
								<div class="modal-body">
									<form class="user-form" autocomplete="off">
										<div class="form-group add-user">
										   <b>项目组名称：</b> 
											<input type="text" name="projName" class="form-control " id="addteam_name">	
											<b>项目组编码：</b>                      
											<input type="text"	name="projDesc" class="form-control" id="addteam_code"	>
										
										<b>项目组负责人：</b>
										<input type="text" name="projChief" class="form-control " id="addproj_chief"  >	
										 <%-- <b>所属项目：</b> 
											<select class="form-control " id="addproj_id">
											<option value="">请选择</option>
											<c:forEach items="${allproject}" var="project">
											<option value="${project.projId}">${project.projName} </option>
											</c:forEach>
											</select>  --%>
										
										<b>项目组描述：</b>
										<textarea rows="2" cols="2" name="remark" class="form-control " id="addteam_desc"></textarea>
										</div>
										<div id="context" style="background-color: white;border: 1px solid gray;width: 265px;position: absolute;top:176px; left:20px;display: none;"> </div>
										
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
//时间验证
		$(function(){
			$("#searchInput").click(function(){
			
					window.search.submit();
			
			})
		})
//提示框
		function showTips(content) {
			$("#op-tips-content").html(content);
			$("#op-tips-dialog").modal("show");
		}
//新建项目组
		$(".add-project-submit").click(function() {
		var teamName=$("#addteam_name").val();
		var teamCode=$("#addteam_code").val();
		var userName=$("#addproj_chief").val();
		//var projId=$("#addproj_id").val();
		var teamDesc=$("#addteam_desc").val();	
		//alert(projId+","+teamName+","+teamCode+","+userName+","+teamDesc);
			if(!!teamName && !!teamCode && !!userName && !!teamDesc){
			//请求添加新项目
	    		
				$.ajax({						
						type : "POST",
						dataType : "json",
						url :"${pageContext.request.contextPath}/projectTeam/insertprojectTeam.action",
						data : {
							teamName :teamName,
							teamCode :teamCode,
							userName :userName,
							//projId :projId,
							teamDesc :teamDesc,
											
						},	
						success : function(data) {
							$("#add-user-form").modal("hide");
							if(data.msg=="2"){
							showTips("添加成功！");
							location.reload();
							}else{
							  showTips("项目组code已存在！");
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
//自动补全(项目组负责人搜索)
        $(function(){
        $("#addproj_chief").keyup(function(){       
        var content=$(this).val();
        //当前搜索内容为空，无需查询
        if(content==""){
        $("#context").css("display","none");
        return ;
        }
        
        //由于浏览器的缓存机制 所以我们每次传入一个时间
        var time=new Date().getTime();
        $.ajax({
            type:"post",      
            url:"${pageContext.request.contextPath}/projectTeam/findAutoCompleteProjTeamChief.action",
            data:{Name:content,time:time},
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
        $("#addproj_chief").val(div.innerText);
        $("#context").css("display","none");
    }

//自动补全(白名单搜索)
        $(function(){
        $("#searchProjectWhite").keyup(function(){
        var projId = $("#projectId").val();
        var content=$(this).val();
        //当前搜索内容为空，无需查询
        if(content==""){
        $("#contextWhite").css("display","none");
        return ;
        }
        
        //由于浏览器的缓存机制 所以我们每次传入一个时间
        var time=new Date().getTime();
        $.ajax({
            type:"post",      
            url:"${pageContext.request.contextPath}/project/findAutoCompleteWhite.action",
            data:{
            projId:projId,
            userName:content,
            time:time},
            dataType:"json",
            success:function(data){
                var names=data.msg;              
                //拼接html
                var res=names.split(",");
                var html="";
                for(var i=0;i<res.length;i++){
                    //每一个div还有鼠标移出、移入点击事件
                    html+="<div onclick='setSearch3_onclick(this)' onmouseout='changeBackColor_out(this)' onmouseover='changeBackColor_over(this)'>"+res[i]+"</div>";
                }
                $("#contextWhite").html(html);
                //显示为块级元素
                $("#contextWhite").css("display","block");
            }
            
        });
    });
        }); 
    //将点击的内容放到搜索框
    function setSearch3_onclick(div){
    var nameList=$("#projectWhite").val();
         if(nameList.indexOf(div.innerText)!=-1){
            showTips("已经添加过，请选择其他用户");
         }else{         
        $("#projectWhite").val(nameList+div.innerText+";");
        $("#contextWhite").css("display","none");
        }
    } 
 //提交白名单 
    $(".asign-white-submit").click(function() {
     var nameList=$("#projectWhite").val();
     var projId = $("#projectId").val();
      $.ajax({
            type:"post",      
            url:"${pageContext.request.contextPath}/project/submitAutoCompleteWhite.action",
            data:{
            projId:projId,
            nameListe:nameList,
            },
            dataType:"json",
            success:function(data){
            
              if(data.msg=="0"){
              showTips("添加成功");
              location.reload();
              }  
              }      
      });
    });
//修改白名单
    $(function(){
    $(".btn.btn-primary.assign-updateWhite").click(function(){   
    var projName=$(this).parents("tr").find(".username").html();
    var projId=$(this).parents("tr").find(".userid").html();    
    $("#projectNames").val(projName); 
    $("#projectIds").val(projId);   
    $.ajax({
            type:"post",      
            url:"${pageContext.request.contextPath}/project/findProjectWhiteListByProjectId.action",
            
            data:{
            projId:projId,           
            },
            dataType:"json",
            success:function(data){
            var html="";
             var userNames= data.msg1;
             var userIds= data.msg2;
             var resName=userNames.split(",");
             var resId=userIds.split(",");
             for ( i = 0; i < resName.length; i++) {
             if(resName[i]!=""){            
             html+="<div style='width: 25px;float: left;'><input type='checkbox' name='onlyProjectWhite' value='"+resId[i]+"' checked='checked' width='50px'></div> <div style='width: 80px;float: left;'>"+resName[i]+"</div>"
				}
			}
			//往div里面写入多选框
			$("#allProjectWhite").html(html);
            }
    
    });
    });
      });
//提交修改后的白名单 
    $(".asign-updateWhite-submit").click(function() {     
     var projId = $("#projectIds").val();
     var userIds="";
     //如果多选框选中，将用户id拼接成字符串
     var length= $("input[name='onlyProjectWhite']:checked").length;
			$("input[name='onlyProjectWhite']:checked").each(function(i){
  				if(length==1 || i==length-1){
 					userIds+=$(this).val();	
  				}else{
  					userIds+=$(this).val()+",";	
  				}
			})		
	 if(userIds.length>0){		
      $.ajax({
            type:"post",      
            url:"${pageContext.request.contextPath}/project/submitAutoCompleteWhiteUpdate.action",
            data:{
            projId:projId,
            userIds:userIds,
            },
            dataType:"json",
            success:function(data){
            
              if(data.msg=="0"){
              showTips("修改成功");
              location.reload();
              }  
              }      
      });
      }
    });
//自动补全(项目编号搜索)
        $(function(){
        $("#projNum").keyup(function(){       
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
            url:"${pageContext.request.contextPath}/project/findAutoComplete.action",
            data:{projNum:content,time:time},
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
        $("#projNum").val(div.innerText);
        $("#context2").css("display","none");
    }
//自动补全(项目负责人搜索)
        $(function(){
        $("#projNames").keyup(function(){       
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
            url:"${pageContext.request.contextPath}/project/findAutoComplete.action",
            data:{projNames:content,time:time},
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
        $("#projNames").val(div.innerText);
        $("#context3").css("display","none");
    }
</script>
</html>
