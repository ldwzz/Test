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

<title>权限管理 - 权限列表</title>
<!-- Bootstrap core CSS -->
<style type="text/css">
.red {
	color: red
}
</style>
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/static/css/layout.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/zTree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/zTree/css/demo.css"
	type="text/css">
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/zTree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript">
  	var flag=false;
  	
   var setting = {    
        
           check:{
               enable:true,
               chkStyle: "radio",  //单选框
               radioType: "all"   //对所有节点设置单选
            }, 
             view:{
            	fontCss:getFont,
            	nameIsHTML:true
            },
            
//                check:{//使用复选框
//                 enable:true
//             },
 
            data: {
                simpleData:{//是否使用简单数据模式
                    enable:true
                }
            },
            callback:{
                onCheck:onCheck
            }           
        };
         //id 标识 ，pId 父id，name 名称，open 是否展开节点， checked  是否选中        
        var zNodes =${jsonArray};

        $(document).ready(function(){
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });
            //选中复选框后触发事件
            function onCheck(e,treeId,treeNode){
            	var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            	nodes=treeObj.getCheckedNodes(true),
            	parentId="";
            	authType="";
            	authName="";
            	authDesc="";
            	authUrl="";
            	authState="";
            	//获取选中的复选框的值
            	for(var i=0;i<nodes.length;i++){
            		parentId+=nodes[i].id;
            		authType+=nodes[i].authType;
            		authName+=nodes[i].authName;
            		authDesc+=nodes[i].authDesc;
            		authUrl+=nodes[i].authUrl;
            		authState+=nodes[i].authState;
            	}
           	 	$("#ParentIdHidden").val(parentId);
           	 	$("#AuthTypeHidden").val(authType); 
           	 	$("#AuthNameHidden").val(authName);
           	 	$("#AuthDescHidden").val(authDesc);
           	 	$("#AuthUrlHidden").val(authUrl);
           	 	$("#authStateHidden").val(authState);          	 	
            }            
        function getFont(treeid,node){
			return node.font?node.font:{};
		};
		
	
        //权限code唯一校验
		$(function(){
			$("#codeInput1").keyup(function(){
				var codeInput=$("#codeInput1").val();//获取用户名
				if(!!codeInput){
					$.ajax({
						type:"POST",
						dataType:"json",
						url:"${pageContext.request.contextPath}/auth/authCodeVerify.action",
						data:{
							authCode:codeInput
						},
						success:function(json){						
							if(json.msg==1){
								$("#authCodeError").addClass("red").html("权限code重复");
								flag=false;						
							}else{
								$("#authCodeError").removeClass("red").html("");
								flag=true;	
							}
						},
						error:function(){
							showTips("系统错误");
							flag=flase;
						}
					})
				}
			})
		})
       $(document).ready(function(){
        //添加权限   
        $(".show-add-form").click(function(){
		var authId=$("#ParentIdHidden").val();
        var authType=$("#AuthTypeHidden").val();
        var authState=$("#authStateHidden").val(); 
        //父id为空，只能添加模块
        if(authState==1 || !authState){
        	if(!authId){
            $("#userByAuth").find("option").eq(1).attr("selected","selected");
	        $("#userByAuth").find("option:gt(1)").attr("disabled","disabled");
	        $("#codeInput1").hide();
	        $("#urlInput1").hide();      
        }else if(!!authId && authType=='1'){//添加列表， 父id不为空,authType=1
	        $("#userByAuth").find("option").eq(1).attr("disabled","disabled");
	        $("#userByAuth").find("option").eq(3).attr("disabled","disabled");
	        $("#userByAuth").find("option").eq(2).attr("selected","selected");
	   		$("#codeInput1").hide();
	   		$("#urlInput1").show(); 
	    }else if(!!authId && authType=='2'){//父id不为空
         	$("#userByAuth").find("option").eq(3).attr("selected","selected");
	        $("#userByAuth").find("option:lt(3)").attr("disabled","disabled");
	        $("#codeInput1").show();
	  		$("#urlInput1").show();
	    }else{
		    alert("对不起,按钮下无法添加子权限");
		    return false;
        }
        }else{
        	alert("禁用中，无法添加子权限");
        	return false;
        }
        
		})
		});	 
	//	添加权限        
	 function addAuth1(){ 
	 	var code=false;
	 	var	authName=$("#authNameInput1").val().trim();
	 	var	authCode=$("#codeInput1").val().trim();
	 	var	authDesc=$("#descInput1").val().trim();
	 	var	authUrl=$("#urlInput1").val().trim();
	 	var	authType=$("#AuthTypeHidden").val().trim();      
	 	var parentId=$("#ParentIdHidden").val().trim();		
	 	if(!parentId && !!authName && !!authDesc){
	 			code=true;
	 	}else if(!!parentId && authType=='1' && !!authName && !!authDesc && !!authUrl){			
	 			code=true;
	 	}else if(!!parentId && authType=='2' && !!authName && !!authDesc &&  !!authCode  && !!authUrl   && flag){
	 			code=true;
	 	}else{
	 		code=false;
	 	}
     	if(code){   	
			$.ajax({
				type:"POST",
				dataType:"json",
				url:"${pageContext.request.contextPath}/auth/insertAuth.action",
				data:{
					authName:authName,
					authCode:authCode,
					authDesc:authDesc,
					authUrl:authUrl,
					authType:authType,
					parentId:parentId
				},
				success:function(data){
					if(data.msg>0){
						window.location.href="${pageContext.request.contextPath}/auth/updateList.action?userId=${userInfo.userId}&groupId=${userInfo.groupId}";
					}else{
						alert("添加失败！");
					}
				},
				error:function(){
					alert("系统错误！");
				}
			})	
		}else{ 
			alert("请正确填写，不能为空！");
			}
		}
		//修改页面回显
		$(function(){
			$(".show-update-form").click(function(){
			var AuthId=$("#ParentIdHidden").val();
			var AuthName=$("#AuthNameHidden").val();
      		var AuthDesc=$("#AuthDescHidden").val();     		
      		var AuthUrl=$("#AuthUrlHidden").val();
      		var	authType=$("#AuthTypeHidden").val();  	  		
      		if(!AuthId){
      			alert("未选中权限");
      			return false;
      		}else if(authType=='1'){      			
      			$("#updateUrl").hide();
      		}else{
      			$("#updateUrl").show();
      			$("#authUrlInput").val(AuthUrl);
      		}     		
      		$("#authdescInput").val(AuthDesc);
      		$("#authNameInput").val(AuthName);  	
		})
		})	 
		//修改权限信息
		function updateAuth(){
          	var AuthId=$("#ParentIdHidden").val();
            var	authDesc=$("#authdescInput").val();
      		var	authName=$("#authNameInput").val();  
          	var authUrl=$("#authUrlInput").val();
      		if(!!AuthId){
				$.ajax({
					url:"${pageContext.request.contextPath}/auth/updateAuth.action",
					type:"POST",
					dataType:"json",
					data:{
						authId:AuthId,
						authDesc:authDesc,
						authName:authName,	
						authUrl,authUrl
					},					
					success:function(){
						window.location.href="${pageContext.request.contextPath}/auth/updateList.action?userId=${userInfo.userId}&groupId=${userInfo.groupId}";
					},
					error:function(){ 
					alert("执行异常");						
                	},
					});
		    }else{
		    	alert("请选择要修改的权限");
		    } 
		    }
		
		//删除权限信息
		 function deleteAuth(){
   			var authId=$("#ParentIdHidden").val();
   			var authState=$("#authStateHidden").val();
   				if(!!authId){
   					if(authState==1){
					if(confirm("确定删除此权限吗？")){
						$.ajax({
					url:"${pageContext.request.contextPath}/auth/deleteAuth.action",
					type:"POST",
					dataType:"json",
					data:{
						authId:authId,
					},
					success:function(data){
						if(data.msg==0){
							alert("此权限使用中，无法删除！");
						}else{
						alert("删除成功")
						window.location.href="${pageContext.request.contextPath}/auth/updateList.action?userId=${userInfo.userId}&groupId=${userInfo.groupId}";
						}
					},
					error:function(){ 
					alert("执行异常");				
                		},
					});
					}
		    }else{
		    	alert("已禁用,无需重复禁用");
		    }
   			}else{
   				alert("未选中权限");
   			}   			
      		
  		}
//   	权限状态校验
	$(function(){
		$("#recoverAuth").click(function(){
		var authId=$("#ParentIdHidden").val();
		var authState=$("#authStateHidden").val(); 
		if(!!authId){
			if(authState==0){				
				return true;
			}else{
				alert("已是启用状态,无需恢复");
				return false;
			}
		}else{
			alert("未选中权限");
			return false;
		}
		
	})
	})
	//恢复权限
  	function reinAuth(){
      	var authId=$("#ParentIdHidden").val();
      	var authState=$("#authStateHidden").val(); 
      		if(!!authId){
			$.ajax({
				url:"${pageContext.request.contextPath}/auth/recoverAuth.action",
				type:"POST",
				dataType:"json",
				data:{
					authId:authId,
				},
				success:function(data){
					if(data.msg==1){
						alert("恢复成功");
						window.location.href="${pageContext.request.contextPath}/auth/updateList.action?userId=${userInfo.userId}&groupId=${userInfo.groupId}";		
					}else if(data.msg==-1){
						alert("恢复失败");
					}else{
						alert("父权限未恢复");
					}
				},
					});
		    }else{
		    	alert("请选择要删除的权限");
		    }     	
  		}
  		
    </script>
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="header.jsp" />

	<div class="container-fluid" style="margin-top: 30px;">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<jsp:include page="navibar.jsp" />
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">权限列表</h1>
				<input type="hidden" id="ParentIdHidden" /> <input type="hidden"
					id="AuthTypeHidden" /> <input type="hidden" id="AuthNameHidden" />
				<input type="hidden" id="AuthDescHidden" /> <input type="hidden"
					id="AuthUrlHidden" /><input type="hidden" id="authStateHidden" />
				<div class="row placeholders">
					<!--添加权限表单 start-->
					<div>
						<c:if test="${fn:contains(authCodes,'auth-updateAuth')}">
							<button type="button" class="btn btn-default show-update-form"
								style="width:100px;" data-toggle="modal"
								data-target="#update-auth-form-div">修改权限</button>
						</c:if>
						<c:if test="${fn:contains(authCodes,'auth-recoverAuth')}">
							<button type="button" style="width:100px;"
								class="btn btn-primary" data-toggle="modal"
								data-target="#rein-auth-form-div" id="recoverAuth">恢复权限</button>
						</c:if>
						<div class="modal fade " id="update-auth-form-div" tabindex="-1"
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
												<label for="authNameInput1">名称</label> <input type="text"
													name="authName" class="form-control  authNameInput"
													id="authNameInput" placeholder="要修改的权限名称 " maxlength="20">
											</div>
											<div class="form-group">
												<label for="descInput">权限描述</label> <input type="text"
													name="authGrade" class="form-control" id="authdescInput"
													placeholder="要修改的权限描述" maxlength="20">
											</div>
											<div class="form-group" id="updateUrl">
												<label for="descInput">Url</label> <input type="text"
													name="authGrade" class="form-control" id="authUrlInput"
													placeholder="要修改的URl" maxlength="20">
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal" id="dismissSelected">取消</button>
										<button type="button" class="btn btn-primary role-submit"
											id="checkon" onclick="updateAuth()">提交</button>
									</div>
								</div>
							</div>
						</div>
						<div class="modal fade " id="rein-auth-form-div" tabindex="-1"
							role="dialog" aria-labelledby="mySmallModalLabel">
							<div class="modal-dialog modal-md" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="role-form-title">恢复权限</h4>

									</div>
									<div class="modal-body">
										<c:forEach items="${auth0}" var="auth0">
											<div>
												<input type="checkbox" name="authId" value="${auth0.authId}" />${auth0.authName}</div>
										</c:forEach>
										<button type="button" style="width:100px;"
											class="btn btn-primary" data-dismiss="modal"
											class="btn btn-default">取消</button>
										<button type="button" style="width:100px;"
											class="btn btn-primary" onclick="reinAuth()">提交</button>
									</div>
								</div>
							</div>
						</div>
					</div>
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

										<!-- <input type="text" name="authId" class="form-control" id="authIdInput"> -->
										<div class="form-group">
											<input type="text" name="authName" class="form-control"
												id="authNameInput1" placeholder="权限名称" maxlength="20">
										</div>
										<div class="form-group">
											<input type="text" name="authCode" class="form-control"
												id="codeInput1" placeholder="权限代码(只能输入英文)" maxlength="20"> <label
												id="authCodeError"></label>
										</div>
										<div class="form-group">
											<input type="text" name="authDesc" class="form-control"
												id="descInput1" placeholder="权限描述" maxlength="20">
										</div>
										<div class="form-group">
											<input type="text" name="authurl" class="form-control"
												id="urlInput1" placeholder="权限url(只能输入英文)" maxlength="30">
										</div>
										权限类型
										<div class="form-group" id="userByAuth"
											style="margin: 15px 18px 15px 3px;">
											<select id="selectAuthType">
												<option value="">--请选择--</option>
												<option value="1">模块</option>
												<option value="2">列表</option>
												<option value="3">按钮</option>
											</select>
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary role-submit"
										id="checkon" onclick="addAuth1()">提交</button>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!--添加权限表单 end-->
				<div class="space-div"></div>

				<div class="zTreeDemoBackground left">
					<ul id="treeDemo" class="ztree" style="width:1024px;"></ul>
				</div>
				<div class="space-div"></div>
				<div>
					<c:if test="${fn:contains(authCodes,'auth-addAuth')}">
						<button type="button" class="btn btn-default show-add-form"
							data-toggle="modal" data-target="#role-form-div"
							style="width: 100px;">添加权限</button>
					</c:if>
					<c:if test="${fn:contains(authCodes,'auth-deleteAuth')}">
						<button type="button" style="width:100px;" class="btn btn-primary"
							onclick="deleteAuth()">删除权限</button>
					</c:if>
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
		src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>

</body>
</html>
