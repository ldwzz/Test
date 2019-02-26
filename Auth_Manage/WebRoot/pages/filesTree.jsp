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

<title>文件管理 - 文件列表</title>
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
		check : {
			enable : true,
			chkStyle : "radio", //单选框
			radioType : "all" //对所有节点设置单选
		},
		view : {
			fontCss : getFont,
			nameIsHTML : true
		},

		/*     check:{//使用复选框
		     enable:true
		 },   */
		data : {
			simpleData : {//是否使用简单数据模式
				enable : true
			}
		},
		callback : {
			onCheck : onCheck
		}
	};
	//id 标识 ，pId 父id，name 名称，open 是否展开节点， checked  是否选中        
	var zNodes = ${jsonArray};
	function getFont(treeid, node) {
		return node.font ? node.font : {};
	};

	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
	//选中复选框后触发事件
	function onCheck(e, treeId, treeNode) {
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo"), nodes = treeObj
				.getCheckedNodes(true), v = "";
		//获取选中的复选框的值
		for (var i = 0; i < nodes.length; i++) {
			v += nodes[i].id;//节点id
			p = nodes[i].pId;//文件父id
			c = nodes[i].name;//文件编码
			g = nodes[i].grade; //文件等级
			//	tn = nodes[i].typeName;//文件类型名称	
			url = nodes[i].url;
			code = nodes[i].typeCode;
			fn = nodes[i].fileName;//file中的name
			fu = nodes[i].fileUrl;//file中的url
			fp = nodes[i].filePswd;//file中的密码
		}
		if (!v) {
			p = 0
			$("#yinfileid").val(null);//节点id
			$("#yinfilecode").val(null);//文件名称
			$("#yingrade").val(null);//文件等级
			//    $("#yintypename").val(tn);//文件类型名称	
			$("#yinparentid").val(p);//父id
			$("#yinurl").val(null);//url
			$("#yintypeCode").val(null);//code 
			$("#fileUrlInput1").val(null);//div中的url
			$("#yinfileName").val(null)//file中的name
			$("#yinfileUrl").val(null)//file中的url
			$("#yinfilePswd").val(null)//file中的密码	        
		} else if (!!v && !p) {
			p = 0
			$("#yinfileid").val(v);//节点id
			$("#yinfilecode").val(c);//文件名称
			$("#yingrade").val(g);//文件等级
			//  $("#yintypename").val(tn);//文件类型名称	
			$("#yinparentid").val(p);//父id
			$("#yinurl").val(code);//url
			$("#yintypeCode").val(code);//code	
			$("#fileUrlInput1").val(code);//div中的url
			$("#yinfileName").val(fn)//file中的name
			$("#yinfileUrl").val(fu)//file中的url
			$("#yinfilePswd").val(fp)//file中的密码	     
		} else if (!!v && !!p) {
			$("#yinfileid").val(v);//节点id
			$("#yinfilecode").val(c);//文件名称
			$("#yingrade").val(g);//文件等级
			//  $("#yintypename").val(tn);//文件类型名称	
			$("#yinparentid").val(p);//父id
			$("#yinurl").val(code);//url
			$("#yintypeCode").val(code);//code	
			$("#fileUrlInput1").val(code);//div中的url
			$("#yinfileName").val(fn)//file中的name
			$("#yinfileUrl").val(fu)//file中的url
			$("#yinfilePswd").val(fp)//file中的密码	   
		}

	}
	//新建文件夹
	$(document)
			.ready(
					function() {
						$("#addfileid")
								.click(
										function() {
											var fileTypeId = $("#yinfileid")
													.val();
											var grade = $("#yingrade").val();
											var typeCode = $("#yintypeCode")
													.val();
											//	alert(typeCode);
											var parentId = $("#yinparentid")
													.val();
											var newUrl = $("#yinnewurl").val();
											//alert(fileTypeId);
											var id = fileTypeId.slice(0, 1);
											//alert(id); 
											if (id == "f") {
												alert("当前为文件无法新建文件夹");
												return false;
											}
											//alert(typeCode+";"+parentId);
											//  alert(typeCode+"999");
											if (parentId == 0 && !!fileTypeId) {
												$("#fileUrlInput1").val(
														typeCode);
											}

											else if (parentId != 0) {
												$
														.ajax({
															url : "${pageContext.request.contextPath}/files/findfileTypeByPid.action",
															dataType : "json",
															type : "POST",
															data : {
																parentId : parentId,
															},
															success : function(
																	data) {
																if (!!data.res) {
																	$(
																			"#fileUrlInput1")
																			.val(
																					data.res
																							+ typeCode);
																	//alert(data.res+typeCode);			      
																}
															}
														});
											} else if (!fileTypeId && !grade) {
												$("#userByAuth").find("option")
														.eq(1).attr("selected",
																"selected");
												$("#userByAuth").find(
														"option:gt(1)").attr(
														"disabled", "disabled");
												$(".form-group.authcode")
														.hide();
												$(".form-group.authurl").hide();
											} else if (!!fileTypeId && !!grade) {//添加列表， 父id不为空,authType=1
												$("#userByAuth").find("option")
														.eq(1).attr("selected",
																"selected");
												$("#userByAuth").find("option")
														.eq(3).attr("disabled",
																"disabled");
												$("#userByAuth").find("option")
														.eq(2).attr("selected",
																"selected");
												$(".form-group.authcode")
														.hide();
												$(".form-group.authurl").show();
											} else if (!!fileTypeId
													&& grade == '2') {//父id不为空
												/* alert("请选择上传文件"); */
												return true;
											} else {
												return true;
											}
										});
					});
	//新建文件夹        
	function addFileType() {

		var fileId = $("#yinfileid").val();
		var typeName = $("#typeNameInput1").val();
		var typeCode = $("#fileCodeInput1").val();
		var parentId = $("#yinparentid").val();
		var grade = $("#yingrade").val();
		//pid为0节点不为0
		if (parentId == 0 && !!fileId) {
			var urls = $("#fileUrlInput1").val();
			//	     alert(urls);	      
			// 	    $("#fileUrlInput1").val(urls+typeCode);
			$("#fileUrlInput1").val(urls + "/" + typeCode);
			var url = $("#fileUrlInput1").val();
			//	      alert(url);
		}
		// 	    else if(parentId==0 && !fileId){
		// 	       var urls=$("#fileUrlInput1").val();
		// 	      }

		if (!fileId) {
			fileId = "0";
			grade = "1";
		} else {
			grade = (grade * 1) + 1;
		}
		// alert(typeCode+";"+typeName+";"+fileId+";"+grade+";"+urls); 
		//	alert(fileId+"--"+typeName+"--"+typeCode+"--"+parentId+"--"+grade);
		var flag = false;
		if (!!typeName && !!typeCode && fileId == "0") {
			flag = true;
		} else if (!!typeName && !!typeCode && !!grade && !!fileId) {
			flag = true;
		} else if (!typeName || !typeCode) {
			alert("请完善信息");
		} else if (!!typeName && !!typeCode && parentId == 0) {
			flag = true;
		}

		//	 alert(typeCode); 
		if (flag) {
			$
					.ajax({
						url : "${pageContext.request.contextPath}/files/insertFileType.action",
						dataType : "json",
						type : "POST",
						data : {
							typeName : typeName,
							typeCode : typeCode,
							parentId : fileId,
							grade : grade,
							url : url,
							fileTypeId : fileId,
						},

						success : function(data) {
							//   alert(data.res);
							if (data.res == "1") {
								alert("文件夹已经存在，请重新输入");
								//window.location.href="${pageContext.request.contextPath}/files/list.action"; 
							}

							else if (data.res == "20") {
								alert("新建成功 ");
								window.location.href = "${pageContext.request.contextPath}/files/list.action";
							} else {
								alert("新建失败");
							}
						},
						error : function() {
							alert("请重新输入");
						}
					});
		}

	}
	//删除文件		
	function deleteFile() {
		var fileId = $("#yinfileid").val();
		//alert(fileId);
		//判断是否为文件
		var id = fileId.slice(0, 1);
		//alert(id); 
		if (id != "f" && !!fileId) {
			alert("当前为文件夹无法执行此操作");
			return false;
		}
		//判断是否选中文件 	
		if (!fileId) {
			alert("请选择要删除的文件");
		} else {
			//获取字符串中的最后一位
			var fId = fileId.substring(1);
			//alert(fId);
			$
					.ajax({
						url : "${pageContext.request.contextPath}/upload/updateFilesState.action",
						Type : "POST",
						dataType : "json",
						data : {
							fileId : fId,
						},
						success : function(data) {
							//alert(1111);
							if (data.res == "1") {
								alert("删除成功")
							}
							window.location.href = "${pageContext.request.contextPath}/files/list.action";
						},
						error : function() {
							alert("执行异常");
						},
					});
		}
	}
	//上传文件
	$(document)
			.ready(
					function() {
						$("#fileUpload")
								.click(
										function() {
											var fileId = $("#yinfileid").val();
											//alert(fileId);

											if (fileId >= 0) {
												//节点的id作为file表的type_file_id
												// var fileId= $("#yinfileid").val();
												$("#hiddenFileId").val(fileId);
												var parentId = $("#yinparentid")
														.val();
												//当前节点的路径
												var typeCode = $("#yintypeCode")
														.val();
												//alert(parentId+"+"+typeCode);
												$
														.ajax({
															url : "${pageContext.request.contextPath}/files/findUploadUrl.action",
															data : {
																parentId : parentId,
																fileTypeId : fileId,
															},
															dataType : "json",
															type : "POST",
															success : function(
																	data) {
																// alert("kaishi");	
																//alert(data.resUrl); 
																// alert(data.urls+"....");
																if (!!data.resUrl) {
																	//alert(555555555555555);
																	$(
																			"#hiddenUrl")
																			.val(
																					data.resUrl
																							+ typeCode);
																	var upload = $(
																			"#hiddenUrl")
																			.val();
																	// alert(upload);	  
																} else if (!!data.urls) {
																	//   alert(6666666)   
																	$(
																			"#hiddenUrl")
																			.val(
																					data.urls);
																	var u = $(
																			"#hiddenUrl")
																			.val();
																	// alert(111111);
																	// alert(u);	
																}
															}
														});
											} else if (!fileId) {
												alert("请选择文件上传的地址");
												return false;
											} else {
												// 	    var id=fileId.slice(0,1);
												// 				alert(id); 
												//		if(id=="f"){
												alert("当前为文件无法执行此操作");
												return false;
												//			}

											}
										});
					});
	//文件加密
	$(document).ready(function() {
		$("#addpswd").click(function() {
			var filepswd = $("#addfilepswdInput").val();
			$("#hiddenpswd").val(filepswd);
			var pswd = $("#hiddenpswd").val();

		});
	})
	//文件下载
	$(document)
			.ready(
					function() {
						$("#uploadFile")
								.click(
										function() {
											var fileId = $("#yinfileid").val();
											var filetypeid = $("#yinparentid")
													.val();
											if (fileId >= 0) {
												alert("此为文件夹无法直接下载");
												return false;
											} else {
												var id = fileId.substring(1);
												var fname = $("#yinfilecode")
														.val();
												$
														.ajax({
															url : "${pageContext.request.contextPath}/upload/findfilepswd.action",
															Type : "POST",
															dataType : "json",
															data : {
																fileId : id,
															},
															success : function(
																	data) {
																if (data.res == "10") {
																	//	$("#uploadFile").prop("data-target","#input-filepswd");
																	//有密码	
																	alert("此文件有密码是否下载?")
																	var pswd = prompt("请输入密码");
																	$
																			.ajax({
																				url : "${pageContext.request.contextPath}/upload/getpswd.action",
																				Type : "POST",
																				dataType : "json",
																				data : {
																					fileTypeId : filetypeid,
																					filePswd : pswd,
																					fileId : id,
																				},
																				success : function(
																						data) {
																					if (data.res == "0") {//有密码
																						alert("密码错误");
																						location
																								.reload();
																					} else if (data.res == "1") {
																						alert("密码正确")
																						window.location.href = "${pageContext.request.contextPath}/upload/downloadTwo.action?fileId="
																								+ id
																								+ "&filePswd="
																								+ pswd
																								+ "&fileTypeId="
																								+ filetypeid
																								+ "";
																					}
																				},
																			});
																} else if (data.res == "2") {
																	alert("是否确定下载");
																	var fname = $(
																			"#yinfilecode")
																			.val();
																	window.location.href = "${pageContext.request.contextPath}/upload/download.action?fileTypeId="
																			+ filetypeid
																			+ "&fileIds="
																			+ fileId
																			+ "&fName="
																			+ fname
																			+ "";
																}
															},
														});
											}
										});
					});
	//文件压缩下载
	function zipUpload() {
		var fileid = $("#yinfileid").val();
		//  alert(fileid);
		if (fileid >= 0) {
			var filetypid = $("#yinparentid").val();
			$.ajax({
						url : "${pageContext.request.contextPath}/upload/zipdownload.action",
						Type : "POST",
						dataType : "json",
						data : {
							fileTypeId : fileid,
						},
						success : function(data) {
							if (data.res == "1") {//有密码
								alert("有密码无法压缩");
								location.reload();
							} else if (data.res == "2") {
								alert("执行压缩");
								$
										.ajax({
											url : "${pageContext.request.contextPath}/upload/zip.action",
											Type : "POST",
											dataType : "json",
											data : {
												fileTypeId : fileid,
											},
											success : function(data) {
												if (data.res == '1') {
													//压缩成功,将zip压缩包的路径返回
													var zip = data.zip;
													window.location.href = "${pageContext.request.contextPath}/"
															+ zip + "";
												} else {
													alert("失败，出现异常");
												}
											}
										});
							}
						}
					});
		}
		else {
			alert("无法执行操作");
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
				<h1 class="page-header">文件列表</h1>
				<div class="row placeholders">
					<!--添加文件表单 start-->
					<div style="border: 2px;border-color: black;">

						<button type="button" class="btn btn-primary show-add-form"
							id="addfileid" data-toggle="modal" data-target="#file-form-div"
							style="width: 100px;">新建文件夹</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						 <button type="button" style="width:100px;" class="btn btn-primary"
							onclick="shangchuan()" id="fileUpload" data-toggle="modal"
							data-target="#file-upload-div">上传文件</button> 
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" style="width:100px;" class="btn btn-primary"
							onclick="deleteFile()">删除文件</button>
					
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" style="width:100px;" class="btn btn-primary"
							id="uploadFile" data-toggle="modal" data-target="">下载文件</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" style="width:100px;" class="btn btn-primary"
							id="zipupload" data-toggle="modal" data-target=""
							onclick="zipUpload()">压缩下载</button>
						
						<input type="hidden" id="yinfileid">
						<!-- 文件名称 -->
						<input type="hidden" id="yinfilecode"> <input
							type="hidden" id="yingrade">
						<!-- <input type="text" id="yintypename">  -->
						<input type="hidden" id="yinparentid">
						<!--拼接的code -->
						<input type="hidden" id="yinnewurl">
						<!-- code作为url  -->
						<input type="hidden" id="yintypeCode">

					</div>
					<!--上传文件-->
					 <div class="modal fade " id="file-upload-div" tabindex="-1"
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
									<form
										action="${pageContext.request.contextPath}/upload/fileUpload.action"
										method="post" enctype="multipart/form-data" name="myform">
										<input type="file" name="file" style="width:200px;"
											id="fileUpload"> <input type="hidden" id="hiddenUrl"
											name="fileurl"> <input type="hidden" id="hiddenpswd"
											name="filePswd"> <input type="hidden" name="fileid"
											id="hiddenFileId">
										<!-- <input type="button" id="fileUpload" value="提交" onclick="fileUploads()"> -->
										 <input type="submit" value="提交" name="upload"> 
										
									</form>
									<div>
										<input type="button" data-toggle="modal"
											data-target="#add-md5-file" value="文件加密"></input>
									</div>
								</div>

							<!-- 	<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary role-submit"
										id="checkon" onclick="addFileType()">提交</button>
								</div> -->
							</div>
						</div>
					</div> 

					<!--文件加密-->
					<div class="modal fade " id="add-md5-file" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">设置密码</h4>
								</div>
								<div class="modal-body">
									<form class="user-form">
										<div class="form-group add-user">
											<input type="text" name="filePswd"
												class="form-control addUserNameInput" id="addfilepswdInput"
												placeholder="文件密码" maxlength="20">

										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" id="addpswd"
										class="btn btn-primary add-user-submit" data-dismiss="modal">确定
									</button>
								</div>
							</div>
						</div>
					</div>
					<!-- 输入密码 -->
					<div class="modal fade" id="input-filepswd" tabindex="-1"
						role="dialog" aria-labelledby="mySmallModalLabel"
						style="display: none">
						<div class="modal-dialog modal-sm" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title">请输入密码</h4>
								</div>
								<div class="modal-body">
									<form class="user-form">
										<div class="form-group add-user">
											<input type="text" name="filepswd"
												class="form-control filepswdInput" id="addfilepswdInput"
												placeholder="文件密码" maxlength="20">
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" id="inputpswd"
										class="btn btn-primary add-user-submit" data-dismiss="modal">确定
									</button>
								</div>
							</div>
						</div>
					</div>
					<!--新建文件界面 -->
					<div class="modal fade " id="file-form-div" tabindex="-1"
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
											<label for="typeNameInput1">文件类型名称</label> <input type="text"
												name="typeName" class="form-control" id="typeNameInput1"
												placeholder="文件类型名称">
										</div>
										<div class="form-group-typecode">
											<label for="codeInput1">文件编码code</label> <input type="text"
												name="typeCode" class="form-control" id="fileCodeInput1"
												placeholder="文件编码code">
										</div>

										<input type="hidden" name="url" class="form-control"
											id="fileUrlInput1" placeholder="文件编码code">

										<div class="form-group-grade">
											<select id="userByAuth" name="userType" style="height:28px;">
												<option value="">文件夹</option>


											</select>
										</div>
									</form>
								</div>

								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary role-submit"
										id="checkon" onclick="addFileType()">提交</button>
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



