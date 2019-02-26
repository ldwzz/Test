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
       var setting = {          
        	   check:{
               enable:true,
               chkStyle: "radio",  //单选框
               radioType: "all",  //对所有节点设置单选
               chkDisaledinherit:true,
            },
            view:{
            	fontCss:setFontCss,
            	nameIsHTML:true
            },
           
            data: {
                simpleData:{//是否使用简单数据模式
                    enable:true
                }
            },
            callback:{//回调函数
                onCheck:onCheck//onCheck是否选中，后面名字随便起
            }           
        };
         //id 标识 ，pId 父id，name 名称，open 是否展开节点， checked  是否选中      ,grade 级别  
        var zNodes = ${jsonArr};
		
		
		function getFont(treeid,node){
			return node.font?node.font:{};
		};
		
		//根据后端传到前端的状态给大树的节点赋颜色
		function setFontCss(treeId, treeNode) {
	          return treeNode.authState == 0 ? {color:"red"} : {};
                                 };
 
            
//选中复选框后触发事件
            function onCheck(e,treeId,treeNode){//e是事件，treeId是当前大树的节点id，treeNode是节点
            	var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),//获取当前大树的节点
            	nodes=treeObj.getCheckedNodes(true),//获取当前选中的节点，返回一个数组
            	v="";
            	w="";
            	x="";
            	//获取选中的复选框的值
            	for(var i=0;i<nodes.length;i++){
            		v+=nodes[i].id;
            		w+=nodes[i].grade;//拿到选中的分类的级别
            		x+=nodes[i].sysType;//拿到选中的分类的类型
            	}
           	 	//alert(v+"+"+w+"+"+x);
            }       
		
		
		
		
		
		
		
		
		
		
///* 添加权限的时候要判断，看你是如何添加，添加什么样的权限，1.不用选中，直接添加权限，此时添加的权限应该是一级权限（最外面的，默认设置好的，且用户无法修改）；
//2.如果二级权限选中，点击添加，此时要判断，选中的节点对应的权限类型authGrade，在进行添加；3.三级权限的添加 ,同理，目前我这里限制只可添加一级权限*/
        $(document).ready(function(){
        //添加权限
        $(".show-add-form").click(function(){
        
        var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            	nodes=treeObj.getCheckedNodes(true),
            	v="";
            	w=""     
 			for(var i=0;i<nodes.length;i++){
            		v+=nodes[i].id;
            		w+=nodes[i].grade;
            	}
        
        //控制按钮和input框是否显示
  
		   if(w==""){
		    $("#userByAuth").find("option[value='1']").prop("selected",true);
		   $("#addlist").attr("disabled","disabled");
		   $("#addbutton").attr("disabled","disabled");
		   }else if(w=="1"){
			   $("#userByAuth").find("option[value='2']").prop("selected",true);
			    $("#addmodel").attr("disabled",true);
			    $("#addlist").attr("disabled",false);
		   $("#addbutton").attr("disabled",true);
		   }else if(w=="2") {
				$("#userByAuth").find("option[value='3']").prop("selected",true);
			      $("#addmodel").attr("disabled",true);
			    $("#addlist").attr("disabled",true);
		   $("#addbutton").attr("disabled",false);
			}else{
				alert("无法添加子权限");
				return false;
				}
				 $("#role-form-div").modal("show");
		})

            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        
        });
        
       
//请求添加权限(输入完成后点击确定按钮触发)
	 function addAuth1(){
	 		
	 		var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            	nodes=treeObj.getCheckedNodes(true),
            	v="";
            	w=""; 
            	x="";
 			for(var i=0;i<nodes.length;i++){
            		v+=nodes[i].id;//分类ID
            		w+=nodes[i].grade;//分类级别
            	    x+=nodes[i].sysType;//分类类型
            	}
			var an=$("#classNameInput1").val().trim();//分类名
      		var ad=$("#descInput1").val().trim();//分类描述
      	    var ac=$("#codeInput1").val().trim();//分类code
      		var at=$("#userByAuth").val().trim();	//模块，按钮，列表的值
      		var af=v;
      		if(!af){ 
      			af="0";
      		}
      		
      	//判断是否非空
      		if(w==""){
      		w=1;
      		x="1";
      		if(an=="" || ac==""){
      		
      		alert("分类名或code不能为空");
      		return false;
      		}
      		}else if(w=="1"){
      		w=2;
      		x="2";
      		if(an=="" || ac==""){
      		
      		alert("分类名或code不能为空");
      		return false;
      		}
      		}else if(w=="2"){
      		w=3;
      		x="3";
      		if(an=="" || ac==""){
      		alert("分类名或code不能为空");
      		return false;
      		}
      		}
      		
      		
      		
      	if(!!an&&!!ad&&!!af){
				$.ajax({
					url:"${pageContext.request.contextPath}/sysClass/addSysClass.action",
					dataType:"json",
					type:"POST",
					data:{
					className:an,
					classDesc:ad,
					classCode:ac,
					parentId:af,
					sysType:x,
                     grade:w
					},
					success:function(data){
						if(data.msg==1){
						alert("添加成功！");
					   location.reload();						
						}else if(data.msg=='3'){
						alert("此分类名或分类code已经存在，请重新输入！");
						return;
						window.location.href="#";
						}else{
						alert("添加失败！");
					   window.location.href="#";						
						}
					},
					error:function(data){ 
					alert("执行异常");
						window.location.href="#";
                		},
					});
				}else{ 
				alert("请正确填写!!");
				}
			}
			 
	
	
	
	
	
//系统修改
		 $(document).ready(function(){
		     $(".show-update-form").click(function(){
		         var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            	   nodes=treeObj.getCheckedNodes(true),
            	   v="";
            	   w="";   
            	   classCode="";
            	   className="";
            	   classDesc="";
 			   for(var i=0;i<nodes.length;i++){
            		v+=nodes[i].id;
            		w+=nodes[i].grade;
            		classCode+=nodes[i].classCode;
            		classDesc+=nodes[i].classDesc;  
            	    className+=nodes[i].name;
            	}
        //控制按钮和input框是否显示
		   if(w=="1"){
		   $("#classNameInput2").val(className);//分类名称回显
		   $("#descInput2").val(classDesc);//分类描述回显
		   $("#codeInput2").val(classCode).attr("readonly","readonly");//分类code
		   }else if(w=="2"){
		   $("#classNameInput2").val(className);//分类名称回显
		   $("#descInput2").val(classDesc);//分类描述回显
		    $("#codeInput2").val(classCode).attr("readonly","readonly");//分类code
		   }else if(w=="3") {
		   $("#classNameInput2").val(className);//分类名称回显
		   $("#descInput2").val(classDesc);//分类描述回显
		   $("#codeInput2").val(classCode).attr("readonly","readonly");
			}else{
				alert("请选择要修改的权限");
				return false;
				}
				 $("#update-auth-form-div").modal("show");
		})
		 })	
	
	 
//修改权限之提交
		function updateAuth(){
      		var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            	nodes=treeObj.getCheckedNodes(true),
            	v="";      
            	w="";  
 			for(var i=0;i<nodes.length;i++){
            		v+=nodes[i].id;
            		w+=nodes[i].grade;//权限级别
            	}
         var an=$("#classNameInput2").val().trim();//分类名称回显
		 var ad=$("#descInput2").val().trim();//分类描述回显 	
            	
	
		
      		
            //判断是否非空
      		if(w=="1"){
      		if(an==""||ad==""){
      		alert("分类名或者描述不能为空");
      		return false;
      		}
      		}else if(w=="2"){
      		if(an=="" || ad==""){
      		alert("分类名或者描述不能为空");
      		return false;
      		}
      		}else if(w=="3"){
      		if(an=="" || ad==""){
      		alert("分类名或者描述不能为空");
      		return false;
      		}
      		}
      		
      		
      		var classId =v;
      		if(!!classId){
				$.ajax({
					url:"${pageContext.request.contextPath}/sysClass/updateSysClass.action",
					type:"POST",
					dataType:"json",
					data:{
					className:an,
					classDesc:ad,
					classId:classId,
					},					
					success:function(data){
						if(data.msg==1){
						alert("修改成功！");
					   location.reload();					
						}else{
							alert("修改失败！");
					   window.location.href="#";						
						}
					},
					error:function(){ 
					alert("执行异常");
						window.location.href="#"; 
                		},
					});
		    }else{
		    	alert("请选择要修改的权限");
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
				<h1 class="page-header">分类列表</h1>
				<div class="row placeholders">
					
					
					<!--添加系统分类表单 start-->
					<div>
<button type="button" class="btn btn-default btn btn-primary show-update-form" style="width:100px;" data-toggle="modal">修改系统分类</button>
						
						

						
<button type="button" class="btn btn-primary btn btn-default show-add-form" data-toggle="modal" style="width: 100px;">添加系统分类</button>
						

						
						
						<!--修改系统分类-->
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

				                            <div class="form-group" id="sys-class-nick">
												<label for="classNameInput2">分类名称</label> <input type="text"
													name="className" class="form-control" id="classNameInput2" maxlength="20"
													placeholder="分类名称" maxlength="10">
											</div>
											
											<div class="form-group" id="sys-class-desc">
												<label for="descInput2">分类描述</label> <textarea type="text" maxlength="20"
													name="classDesc" class="form-control" id="descInput2"
													placeholder="分类描述" maxlength="20"></textarea>
											</div>
											
											<div class="form-group" id="sys-class-code">
												<label for="codeInput2">分类classCode</label> <input type="text" maxlength="28"
													name="classCode" class="form-control" id="codeInput2"  
													placeholder="classCode" maxlength="20">
											</div>
											
												

										
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>
										<button type="button" class="btn btn-primary role-submit"
											id="checkon" onclick="updateAuth()">提交</button>
									</div>
								</div>
							</div>
						</div>
						
						
					
				<!--新增系统分类-->
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

											<div class="form-group" id="sys-class-nick">
												<label for="classNameInput1">分类名称</label> <input type="text"
													name="className" class="form-control" id="classNameInput1" maxlength="20"
													placeholder="分类名称" maxlength="10">
											</div>
											
											<div class="form-group" id="sys-class-desc">
												<label for="descInput1">分类描述</label> <textarea type="text" maxlength="20"
													name="classDesc" class="form-control" id="descInput1"
													placeholder="分类描述" maxlength="20"></textarea>
											</div>
												
											
											
											<div class="form-group" id="sys-class-code">
												<label for="codeInput1">分类classCode</label> <input type="text" maxlength="28"
													name="classCode" class="form-control" id="codeInput1"  
													placeholder="classCode" maxlength="20">
											</div>
										

											<select  id="userByAuth" name="userState" style="height:28px;">
									         	
									         	<option id="addmodel" value="1">平台</option>
									         	<option id="addlist" value="2">项目</option>
									         	<option id="addbutton" value="3">模块</option>
									         </select> &nbsp;
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

					<!--添加系统分类表单 end-->
					<div class="space-div"></div>

					<div class="zTreeDemoBackground left">
						<ul id="treeDemo" class="ztree" style="width:1024px;"></ul>
					</div>
					
					<div class="space-div"></div> 
															
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
