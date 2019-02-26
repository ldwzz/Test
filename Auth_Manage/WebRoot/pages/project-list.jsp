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
			<h1 class="page-header">项目列表</h1>
			<div class="row placeholders">
				<form action="${pageContext.request.contextPath}/project/list.action?pageNum=${page.pageNum}" method="post" name="search" autocomplete="off">
					<div class="pull-right form-inline">
						<input type="text" name="projNum"
							class="form-control form-inline" placeholder="项目编号" id="projNum" value="${param.projNum}" style="width: 150px;left:238px;position: absolute;"> 
						<input type="text" name="projName"
							class="form-control form-inline" placeholder="项目名称" id="projName" value="${param.projName}" style="width: 150px;position: absolute; left:390px;"> 
						
						<input type="text" name="projNames"
							class="form-control form-inline" placeholder="负责人" id="projNames" value="${param.projNames}" style="width: 100px;position: absolute; left:546px;"> 
							<select class="form-control form-inline" name="projState" id="projState">
							<option value="" >项目状态</option>
							<option value="0" ${param.projState =='0' ?"selected='selected'":""}>未开始</option>
							<option value="1" ${param.projState =='1' ?"selected='selected'":""}>进行中</option>
							<option value="2" ${param.projState =='2' ?"selected='selected'":""}>已暂停</option>
							<option value="3" ${param.projState =='3' ?"selected='selected'":""}>已完成</option>
							<option value="4" ${param.projState =='4' ?"selected='selected'":""}>已关闭</option>
							</select>				
						<input type="date" name="startTime" class="form-control form-inline" id="startTime" value="${param.startTime}"/>
						至<input type="date" name="endTime" class="form-control form-inline" id="endTime" value="${param.endTime}"/>
						<button type="button" class="btn btn-primary form-inline" id="searchInput">确定查询</button>
					</div>
				</form>
				<br />
				<div>
				<div id="context2" style="background-color: white;border: 1px solid gray;width: 150px;position: absolute;top:122px; left:238px;display: none"> </div>	
				<div id="context1" style="background-color: white;border: 1px solid gray;width: 150px;position: absolute;top:122px; left:390px;display: none"> </div>
				<div id="context3" style="background-color: white;border: 1px solid gray;width: 100px;position: absolute;top:122px; left:546px;display: none"> </div>
				<br><br>
					
					<c:if test="${fn:contains(authCodes,'project-addProject')}">
					<button type="button" class="btn btn-primary show-user-form"
						data-toggle="modal" data-target="#add-project-form">添加项目</button>
					</c:if>
					
				<div class="space-div"></div>
				<table class="table table-hover table-bordered user-list" style="text-align: center;">
					<tr>
						<td>ID</td>
						<td>项目名</td>
						<td>项目编号</td>
						<td>创建时间</td>
						<td>创建人</td>
						<td>总工时</td>
						<td>访问类型</td>
						<td>项目状态</td>
						<td>项目负责人</td>
						<td>操作</td>
					</tr>					
					<c:forEach items="${page.resultList}" var="project">
						<tr>
							<td class="userid">${project.projId}</td>
							<td class="username">${project.projName}</td>
							<td class="nickname">${project.projNum}</td>					
							<td class="userstate">${project.createTime}</td>
							<td class="groupName">${project.projNames}</td>
							<td class="createtime">${project.ableDay}</td>
						    <td class="createtype">
						    <c:if test="${project.vistorType==0}">默认公开</c:if>
						     <c:if test="${project.vistorType==1}">项目组内部</c:if>
						     <c:if test="${project.vistorType==2}">自定义白名单</c:if>
						    </td>
							<td class="createtime">
							<c:if test="${project.projState==0}">未开始</c:if>
							<c:if test="${project.projState==1}">进行中</c:if>
							<c:if test="${project.projState==2}">已暂停</c:if>
							<c:if test="${project.projState==3}">已完成</c:if>
							<c:if test="${project.projState==4}">已关闭</c:if>
							</td>
							<td class="createtime">${project.projNames}</td>
							<td width="35%">													
								<c:if test="${fn:contains(authCodes,'project-allotTeam')}">	
									<button type="button" class="btn btn-primary allot-team" style="font-size: 10px"
									
									data-toggle="modal"  data-target="#assign-allotProjectTeam-form">分配项目组</button></c:if>
									<c:if test="${fn:contains(authCodes,'project-updateWhite')}">		
									<button type="button" class="btn btn-primary assign-updateWhite" style="font-size: 10px"
									data-toggle="modal"  data-target="#assign-updateWhite-form">修改白名单</button></c:if>
 					               <c:if test="${fn:contains(authCodes,'peoject-detail')}">	
									<a href="${pageContext.request.contextPath}/project/projectDetail.action?projId=${project.projId}">
									<button type="button" class="btn btn-primary update-auth" style="font-size: 10px">详情展示</button>
									</a></c:if>
								<c:if test="${fn:contains(authCodes,'project-addWhite')}">	
									<button type="button" class="btn btn-primary assign-white-group" style="font-size: 10px"
									data-toggle="modal"  data-target="#assign-white-form">添加白名单</button>
								</c:if>
								<c:if test="${fn:contains(authCodes,'project-task')}">	
									<a href="${pageContext.request.contextPath}/proj/list.action?projId=${project.projId}"><button type="button" class="btn btn-primary assign-role" style="font-size: 10px"
									data-toggle="modal"  data-target="">项目任务</button></a>
								</c:if>
								 <c:if test="${fn:contains(authCodes,'project-version')}">	
									<a href="${pageContext.request.contextPath}/project/projectVession.action?projId=${project.projId}">
									<button type="button" class="btn btn-primary version-auth" style="font-size: 10px">版本信息</button>
									</a></c:if>
							</td>
						</tr>
					</c:forEach>
				</table>				
				<jsp:include page="standard.jsp" />
				
					<!--添加 白名单-->
					<div class="modal fade " id="assign-white-form" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
										<h4 class="modal-title">分配白名单</h4>
								</div>
								<div class="modal-body">
									<form class="user-form" autocomplete="off">
										<div class="form-group add-user">
											<h4>项目名：</h4>
											<input class="form-control" id="projectName" readonly="readonly">
											<input type="hidden" id="projectId"/>												
											<h4 >白名单：</h4>
											<input class="form-control" id="projectWhite" >
											<br>
											<h5 >自动搜索：</h5>
											<input class="form-control" id="searchProjectWhite" >
																						
										</div>
									</form>
									<div id="contextWhite"  style="background-color: white;border: 1px solid gray;width: 265px;position: absolute;top:250px; left:20px;display: none;"> </div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary asign-white-submit">确定
									</button>
								</div>
							</div>
						</div>
					</div>
				
				
					<!--修改白名单-->
					<div class="modal fade " id="assign-updateWhite-form" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-md" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
										<h4 class="modal-title">分配白名单</h4>
										
								</div>
								<div class="modal-body">
									<form class="user-form">
										<div class="form-group add-user">
											<h4>用户名：</h4>
											<input class="form-control" id="projectNames" readonly="readonly">
											<input type="hidden" id="projectIds"/>	
												<div class="checkbox">
											<h4 style="margin-top: 30px;">白名单用户：</h4>
											<div id="allProjectWhite" style="margin-left: 20px"></div>
											<br><br><br>
											</div>												
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default-update"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary asign-updateWhite-submit">确定
									</button>
								</div>
							</div>
						</div>
					</div>

		
	               <!--分配项目组-->
					<div class="modal fade " id="assign-allotProjectTeam-form" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-md" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
										<h4 class="modal-title">分配项目组</h4>
										
								</div>
								<div class="modal-body">
									<form class="user-form">
										<div class="form-group add-user">
											<h4>项目名称：</h4>
											<input class="form-control" id="projectNames1" readonly="readonly">
											<input type="hidden" id="projectIds1"/>	
												<div class="checkbox">
											<h4 style="margin-top: 30px;">项目组：</h4>
											<%-- <c:forEach items="${allProjectTeam}" var="team">
												<label><input type="checkbox" class="form-inline" value="${team.teamId}" name="projectTeam"/>${team.teamName}</label>
											</c:forEach> --%>
											<div id="allprojectTeam" style="margin-left: 20px"></div>
											</div>												
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default-update"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary allotProjectTeam-submit">确定
									</button>
								</div>
							</div>
						</div>
					</div>
	
			
				<!--添加新项目表单-->

					<div class="modal fade " id="add-project-form" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
										<h4 class="modal-title">添加新项目</h4>
								</div>
								<div class="modal-body">
									<form class="user-form">
										<div class="form-group add-user">
										   <b>项目名称：</b> 
										   <br>
											<!-- <input type="text" name="projName" class="form-control " id="addprojNameInput">	 -->
											<select id="allprojectName" name="selectName">
			                                  <!--  <option value="">请选择</option> -->
			                                  
			                                  </select>
											<br>
											<b>项目描述：</b> 
											<input type="text"	name="projDesc" class="form-control" id="addprojDescInput"	>
											<b>访问类型：</b> 										
										<select name="vistorType" class="form-control" id="vistorType" style="margin: 15px 18px 15px 3px;">
											<option value="">请选择</option>
											<option value="0">默认公开</option>
											<option value="1">项目组内部</option>
											<option value="2">自定义白名单</option>
										</select>
										<b>开始时间：</b>
										<input type="date"	name="startTime" class="form-control" id="addstartTimeInput">				
										<b>结束时间：</b>
										<input type="date"	name="endTime" class="form-control" id="addendTimeInput">
										<b>项目负责人：</b>
										<input type="text" name="projChief" class="form-control " id="addprojChiefInput1" readonly="readonly" value="${userInfo.userName }">	
										<input type="hidden" name="projChief" class="form-control " id="addprojChiefInput" readonly="readonly" value="${userInfo.userId }">
										<b>项目备注：</b>
										<textarea rows="2" cols="2" name="remark" class="form-control " id="remark"></textarea>
										</div>
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
	<input type="hidden" id="pathone" value="${pageContext.request.contextPath}">
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
//新建项目按钮
     $(".show-user-form").click(function(){
             $.ajax({
					url:"${pageContext.request.contextPath}/project/findAllProjectName.action",					
					type:"POST",
					dataType:"json",
					success:function(data){
					var html="";	
					var check1=data.shu1;	
					var check2=data.shu2;	
					for ( i = 0; i < check2.length; i++) {
             if(check2[i]!=""){                      
             html+="<option value='"+check1[i]+"'>"+check2[i]+"</option>"
				}
			}
			//往div里面写入多选框
			$("#allprojectName").html(html);
     }
     });
     });
//新建项目
		$(".add-project-submit").click(function() {
		var projName=$("#allprojectName option:selected").text();//获取项目名
		var classId=$("#allprojectName option:selected").val();//获取系统分类id
		var projDesc=$("#addprojDescInput").val();//获取项目描述
		var vistorType=$("#vistorType").val();//获取访问类型
		var startTime=$("#addstartTimeInput").val();//获取开始时间
		var endTime=$("#addendTimeInput").val();//获取结束时间	
		var projChief=$("#addprojChiefInput").val();//获取项目责任人	
		var remark=$("#remark").val();//获取项目备注		
		if(!!projName && !!classId && !!projDesc && !!vistorType && !!startTime
		&& !!endTime && !!projChief && !!remark && endTime>startTime
		){
			
			//请求添加新项目			
				$.ajax({						
						type : "POST",
						dataType : "json",
						url :"${pageContext.request.contextPath}/project/insertprojectInfo.action",
						data : {
							projName :projName,
							classId :classId,
							projDesc :projDesc,
							vistorType :vistorType,
							startTime :startTime,
							endTime :endTime,
							projChief :projChief,
							remark:remark											
						},	
						success : function(data) {
							$("#add-user-form").modal("hide");
							if(data.msg==1){
							showTips("添加成功！");
							location.reload();
							}
						},
						error:function(){
							showTips("系统错误");
						}
				})	
				}else{
				showTips("请正确填写完成，起始时间不能大于结束时间");
				}			
			
		});
//自动补全(项目名称搜索)
        $(function(){
        $("#projName").keyup(function(){       
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
            url:"${pageContext.request.contextPath}/project/findAutoComplete.action",
            data:{projName:content,time:time},
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
        $("#projName").val(div.innerText);
        $("#context1").css("display","none");
    }
//分配用户组按钮
    $(function(){
    $(".btn.btn-primary.allot-team").click(function(){     
    var projName=$(this).parents("tr").find(".username").html();
    var projId=$(this).parents("tr").find(".userid").html();      
    $("#projectNames1").val(projName); 
    $("#projectIds1").val(projId);
			   $.ajax({
					url:"${pageContext.request.contextPath}/project/findAllProjectTeamByProId.action",
					data:{projId:projId},
					type:"POST",
					dataType:"json",
					success:function(data){
					var html="";
					var check=data.shu;	
					var check1=data.shu1;	
					var check2=data.shu2;	
					for ( i = 0; i < check2.length; i++) {
             if(check2[i]!=""){            
             html+="<div style='width: 25px;float: left;'><input type='checkbox' name='projectTeam' value='"+check1[i]+"'  width='50px'></div> <div style='width: 80px;float: left;'>"+check2[i]+"</div>"
				}
			}
			//往div里面写入多选框
			$("#allprojectTeam").html(html);				
						 if(check.length>0){						 
						}else{
						alert("项目没有绑定项目组");
						} 
						//循环遍历比较和查出来项目组id相同的选中
						$("input[name='projectTeam']").each(function(){
						for (var i = 0; i < check.length; i++) {
							if(check[i]==$(this).val()){
							 $(this).prop("checked",true);
							}
						}
						
						});
					}
				}); 
    
    });
    });
//分配用户组

		$(".btn.btn-primary.allotProjectTeam-submit").click(function(){ 
          var teamId="";
		  var projId= $("#projectIds1").val();
		  var length=$("input[name='projectTeam']:checked").length;
         //拼接所有的选中的项目组id
	      $("input[name='projectTeam']:checked").each(function(i){
	         if(length==1||length==i+1){
	         teamId+=$(this).val();	         
	         }else{
	         teamId+=$(this).val()+",";
	         }	         
	           }); 
	          if(length==0){
		 alert("至少绑定一个项目组");
		 }else{          
	         $.ajax({
					url:"${pageContext.request.contextPath}/project/insertProjectTeamToPro.action",
					data:{
					teamId:teamId,
					projId:projId					
					},
					type:"POST",
					dataType:"json",
					success:function(data){
					if(data.res==2){
					alert("分配成功");	
					location.reload();
					}					
					}	
		});	
		}
		});  
//添加白名单
    $(function(){
    $(".btn.btn-primary.assign-white-group").click(function(){   
    var projName=$(this).parents("tr").find(".username").html();
    var projId=$(this).parents("tr").find(".userid").html();    
    $("#projectName").val(projName); 
    $("#projectId").val(projId);
    });
    });
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
             html+="<div style='width: 25px;float: left;'><input type='checkbox' name='onlyProjectWhite' value='"+resId[i]+"' checked='checked' width='80px'></div> <div style='width: 100px;float: left;'>"+resName[i]+"</div>"
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
      }else{
           $.ajax({
            type:"post",      
            url:"${pageContext.request.contextPath}/project/declearAutoCompleteWhiteUpdate.action",
            data:{
            projId:projId,
            
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
