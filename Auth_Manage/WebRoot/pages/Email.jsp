<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>站内信</title>

<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/static/css/layout.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/new_file.css" />


</head>

<body>

	<!-- 头部 -->
	<jsp:include page="header.jsp" />

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<jsp:include page="navibar.jsp" />
			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="container">
					<h1 class="page-header">发件箱</h1>
					<table width="910px" height="600px"
						style="background-color:lightsteelblue;">
						<tr>
							<td><button type="button" class="btn btn-primary goBack">返回</button>&nbsp;&nbsp;
								<b><input type="radio" name="sendType" id="sendUser"
									value="1" checked="checked" />用户</b>&nbsp;&nbsp; <b> <input
									type="radio" name="sendType" id="sendProj" value="2" />项目
							</b>&nbsp;&nbsp; <b> <input type="radio" name="sendType"
									id="sendDep" value="3" />部门
							</b>&nbsp;&nbsp;
								<button type="button" class="btn btn-primary send">发送</button>&nbsp;&nbsp;
								<button type="button" class="btn btn-primary sendAll">发送所有人</button>&nbsp;&nbsp;
								
								
								
								
								
								
								
								
								
								
								
								
	<form id="uploadForm" >
        <input type="file" name="files" />
        <input type="file" name="files" />
        <input type="file" name="files" />
        <input type="button" value="上传" onclick="doUpload()"> 
        <!-- <button type="submit">提交</button> -->
    </form>

	<input hidden="hidden" id="filePath">						
							
							
							
							
							
							
							
							
							
							<td rowspan="4">
								<table>
									<h2 align="center">通讯录</h2>
									<input type="text" name="userNameSel" id="userNameSel" value=""
										style=" margin-top:15px; width: 170px; height:30px;" />
									<button type="button" class="btn btn-primary selectBut">搜索</button>
									<div id="context2"
										style="width: 120px;
								position: absolute;margin-top:-6px; background-color:gray;display: none"></div>
									<br />
									<br />
									<tr>
										<th>

											<div
												style="width:250px;height:385px;background-color:lavenderblush;border:2px solid #6CABE7;">
												<div class="userDiv" style="display: block; overflow: scroll;height: 400px;">
													<center>
														<b>用户</b>
													</center>
										
													<c:forEach items="${userLIst}" var="user">
														<input type="checkbox" name="user" id="user"
															value="${user.userId}">${user.userName}
													</input>
														<br>
													</c:forEach>
													
												</div>
												<div class="groupDiv" style="display: none;">
													<center>
														<b>项目</b>
													</center>
													<select class="form-control form-inline" name="projId"
														id="projId">
														<c:forEach items="${projects}" var="pro">
															<option value="${pro.projId}">${pro.projName}</option>
														</c:forEach>
													</select>
													<center>
														<b>项目组</b>
													</center>
													<select class="form-control form-inline" name="teamId"
														id="teamId">
														<c:forEach items="${projectTeams}" var="team">
															<option value="${team.teamId}">${team.teamName}</option>
														</c:forEach>
													</select>
												</div>
												<div class="departDiv" style="display: none;">
													<center>
														<b>部门</b>
													</center>
													<c:forEach items="${userGroup}" var="group">
														<input type="radio" name="depart" id="depart"
															value="${group.groupId}">${group.groupName}</input>
														<br>
													</c:forEach>
												</div>
											</div>
										</th>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th>收件人&nbsp;<input type="text" name="" id="userNames"
								style="width: 600px;height: 30px;" autocomplete="off"> <input
								hidden="hidden" id="fromUser" name="fromUser"
								value="${userInfo.userId}"> <input hidden="hidden"
								id="toUserIds" name="toUserIds"> <input hidden="hidden"
								id="toDepartId" name="toDepartId"> <input
								hidden="hidden" id="toProjId" name="toProjId"> <input
								hidden="hidden" id="toTeamId" name="toTeamId">
								<div id="context1"
									style="width: 120px;
								position: absolute;display:none;margin-left: 55px; background-color:gray;"></div>
							</th>
						</tr>
						<tr>
							<th>主题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
								name="msgTitle" id="msgTitle" style="width: 600px;height: 30px;"></th>
						</tr>
						<tr>
							<th id="sa">正文&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
							<th id="top"><textarea name="msgContext" id="msgContext"
									style="border:2px solid #6CABE7;background-color:lavenderblush;
				height: 340px; width: 600px;"></textarea></th>
						</tr>
					</table>
				</div>
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
	<script type="text/javascript">
		//通讯录控制显示
		$("input:radio[name='sendType']").click(
				function() {
					$("#userNames").val(null);
					$("#toDepartId").val(null);
					var type = $("input:radio[name='sendType']:checked").val();
					if (type == 1) {
						$(".userDiv").show();
						$(".groupDiv").hide();
						$(".departDiv").hide();
						$("input:radio[name='depart']:checked").prop('checked',
								false);

					} else if (type == 2) {
						$(".userDiv").hide();
						$("input:checkbox[name='user']:checked").prop(
								'checked', false);
						$(".groupDiv").show();
						$(".departDiv").hide();
						$("input:radio[name='depart']:checked").prop('checked',
								false);
					} else if (type == 3) {
						$(".userDiv").hide();
						$("input:checkbox[name='user']:checked").prop(
								'checked', false);
						$(".groupDiv").hide();
						$(".departDiv").show();
					}
				});
		//用户通讯录
		$(".userDiv")

				.click(
						function() {
							var toUserIds = "";
							var length = $("input[name='user']:checked").length;
							$("input[name='user']:checked").each(function(i) {
								if (length == 1 || i == length - 1) {
									toUserIds += $(this).val();
								} else {
									toUserIds += $(this).val() + ",";
								}
							});

							if (toUserIds.length > 0) {

								$("#toUserIds").val(toUserIds);
								$
										.ajax({
											type : "POST",
											dataType : "json",
											url : "${pageContext.request.contextPath}/message/findUserNames.action",
											data : {
												userIds : toUserIds,
											},
											success : function(data) {
												if (!!data.userNames) {
													var str = data.userNames;
													str = str.substring(0,
															str.length - 1);

													$("#userNames").val(str);
												} else {
													$("#userNames").val(null);
												}
											},
											error : function() {
												alert("系统错误！");
											}
										})
							} else {
								$("#userNames").val(null);
							}

						});

		//部门通讯录
		$(".departDiv")

				.click(
						function() {
							var toDepartId = $(
									"input:radio[name='depart']:checked").val()

							if (!!toDepartId) {

								$("#toDepartId").val(toDepartId);
								$
										.ajax({
											type : "POST",
											dataType : "json",
											url : "${pageContext.request.contextPath}/message/findGroupName.action",
											data : {
												groupId : toDepartId,
											},
											success : function(data) {
												if (!!data.groupName) {
													var str = data.groupName;
													$("#userNames").val(str);
												} else {
													$("#userNames").val(null);
												}
											},
											error : function() {
												alert("系统错误！");
											}
										})
							} else {
								$("#userNames").val(null);
							}

						});
		//发送站内信
		$(".send")
				.click(
						function() {
							var type = $("input:radio[name='sendType']:checked")
									.val();
							if (!!$("#userNames").val().trim()) {
								//单发，多发
								if (type == 1) {

									$
											.ajax({
												type : "POST",
												dataType : "json",
												url : "${pageContext.request.contextPath}/message/insertMessage.action",
												data : {
													fromUser : $("#fromUser")
															.val(),
													msgTitle : $("#msgTitle")
															.val(),
													msgContext : $(
															"#msgContext")
															.val(),
													toUserIds : $("#toUserIds")
															.val(),
													fileUrl : $("#filePath")
															.val(),
												},
												success : function(data) {
													if (data.res > 0) {
														alert("发送成功！");
														location.reload();
													} else {
														alert("发送失败！");
													}
												},
												error : function() {
													alert("系统错误！");
												}
											})
									//项目、项目组
								} else if (type == 2) {
									var teamId = $("#toTeamId").val();
									var projId = $("#toProjId").val();
									if (!!teamId) {
										$
												.ajax({
													type : "POST",
													dataType : "json",
													url : "${pageContext.request.contextPath}/message/insertProjMessage.action",
													data : {
														fromUser : $(
																"#fromUser")
																.val(),
														msgTitle : $(
																"#msgTitle")
																.val(),
														msgContext : $(
																"#msgContext")
																.val(),
														teamId : teamId,
														fileUrl : $("#filePath")
															.val(),

													},
													success : function(data) {
														if (data.res > 0) {
															alert("发送成功！");
															location.reload();
														} else if (data.res == 0) {
															alert("发送失败！");
														} else if (data.res == "-1") {
															alert("项目组无人！");
														}
													},
													error : function() {
														alert("系统错误！");
													}
												})
									} else {
										$
												.ajax({
													type : "POST",
													dataType : "json",
													url : "${pageContext.request.contextPath}/message/insertProjMessage.action",
													data : {
														fromUser : $(
																"#fromUser")
																.val(),
														msgTitle : $(
																"#msgTitle")
																.val(),
														msgContext : $(
																"#msgContext")
																.val(),

														projId : projId,
														fileUrl : $("#filePath")
															.val(),
													},
													success : function(data) {
														if (data.res > 0) {
															alert("发送成功！");
															location.reload();
														} else if (data.res == 0) {
															alert("发送失败！");
														} else if (data.res == "-1") {
															alert("项目无人！");
														}
													},
													error : function() {
														alert("系统错误！");
													}
												})
									}
									//部门群发
								} else if (type == 3) {

									$
											.ajax({
												type : "POST",
												dataType : "json",
												url : "${pageContext.request.contextPath}/message/insertGroupMessage.action",
												data : {
													fromUser : $("#fromUser")
															.val(),
													msgTitle : $("#msgTitle")
															.val(),
													msgContext : $(
															"#msgContext")
															.val(),
													toGroupId : $("#toDepartId")
															.val(),
															fileUrl : $("#filePath")
															.val(),
												},
												success : function(data) {
													if (data.res > 0) {
														alert("发送成功！");
														location.reload();
													} else if (data.res == 0) {
														alert("发送失败！");
													} else if (data.res == "-1") {
														alert("部门无人！");
													}
												},
												error : function() {
													alert("系统错误！");
												}
											})
								}
							} else {

								alert("收件人不能为空!");
							}

						});
				
	//全部发送站内信
		$(".sendAll")
				.click(
						function() {

							$
									.ajax({
										type : "POST",
										dataType : "json",
										url : "${pageContext.request.contextPath}/message/insertAllMessage.action",
										data : {
											fromUser : $("#fromUser").val(),
											msgTitle : $("#msgTitle").val(),
											msgContext : $("#msgContext").val(),
											sendType : "4",
											fileUrl : $("#filePath").val(),
										},
										success : function(data) {
											if (data.res > 0) {
												alert("发送成功！");
												location.reload();
											} else {
												alert("发送失败！");
											}
										},
										error : function() {
											alert("系统错误！");
										}
									})

						});
		//单发自动补全											
		$(function() {
			$("#userNames")
					.keyup(
							function() {
								var content = $(this).val();
								//当前搜索内容为空，无需查询
								if (content == "") {
									$("#context1").css("display", "none");
									return;
								}

								//由于浏览器的缓存机制 所以我们每次传入一个时间
								var time = new Date().getTime();
								$
										.ajax({
											type : "post",
											url : "${pageContext.request.contextPath}/message/findUserNameByName.action",
											data : {
												userName : content,
												time : time
											},
											dataType : "json",
											success : function(data) {
												var names = data.msg;
												//拼接html
												var res = names.split(",");
												var html = "";
												for (var i = 0; i < res.length; i++) {
													//每一个div还有鼠标移出、移入点击事件
													html += "<div onclick='setSearch_onclick(this)' onmouseout='changeBackColor_out(this)' onmouseover='changeBackColor_over(this)'>"
															+ res[i] + "</div>";
												}
												$("#context1").html(html);
												//显示为块级元素
												$("#context1").css("display",
														"block");
											}

										});
							});
		});
		//鼠标移动到内容上
		function changeBackColor_over(div) {
			$(div).css("background-color", "#CCCCCC");
		}
		//鼠标离开内容
		function changeBackColor_out(div) {
			$(div).css("background-color", "");
		}
		//将点击的内容放到搜索框
		function setSearch_onclick(div) {
			$("#userNames").val(div.innerText);
			$("#context1").css("display", "none");

			$
					.ajax({
						type : "post",
						url : "${pageContext.request.contextPath}/message/findUserIdByName.action",
						data : {
							userName : $("#userNames").val(),
						},
						dataType : "json",
						success : function(data) {
							var userId = data.msg;
							$("#toUserIds").val(userId);
						}

					});
		}

		//搜索自动补全											
		$(function() {
			$("#userNameSel")
					.keyup(
							function() {
								var content = $(this).val();
								//当前搜索内容为空，无需查询
								if (content == "") {
									$("#context2").css("display", "none");
									return;
								}

								//由于浏览器的缓存机制 所以我们每次传入一个时间
								var time = new Date().getTime();
								$
										.ajax({
											type : "post",
											url : "${pageContext.request.contextPath}/message/findUserNameByName.action",
											data : {
												userName : content,
												time : time
											},
											dataType : "json",
											success : function(data) {
												var names = data.msg;
												//拼接html
												var res = names.split(",");
												var html = "";
												for (var i = 0; i < res.length; i++) {
													//每一个div还有鼠标移出、移入点击事件
													html += "<div onclick='setSearch_onclick(this)' onmouseout='changeBackColor_out(this)' onmouseover='changeBackColor_over(this)'>"
															+ res[i] + "</div>";
												}
												$("#context2").html(html);
												//显示为块级元素
												$("#context2").css("display",
														"block");
											}

										});
							});
		});
		//鼠标移动到内容上
		function changeBackColor_over(div) {
			$(div).css("background-color", "#CCCCCC");
		}
		//鼠标离开内容
		function changeBackColor_out(div) {
			$(div).css("background-color", "");
		}
		//将点击的内容放到搜索框
		function setSearch_onclick(div) {
			$("#userNames").val(div.innerText);
			$("#userNameSel").val(div.innerText);
			$("#context2").css("display", "none");

			$
					.ajax({
						type : "post",
						url : "${pageContext.request.contextPath}/message/findUserIdByName.action",
						data : {
							userName : $("#userNames").val(),
						},
						dataType : "json",
						success : function(data) {
							var userId = data.msg;
							$("#toUserIds").val(userId);
						}

					});
		}

		
		//查询项目名显示到收件人
		$("#projId")
				.click(
						function() {
							$("#toTeamId").val(null);
							$("#toProjId").val($(this).val());
							$
									.ajax({
										type : "POST",
										dataType : "json",
										url : "${pageContext.request.contextPath}/message/findProjNameById.action",
										data : {
											projId : $(this).val(),
										},
										success : function(data) {
											if (!!data.projName) {
												var str = data.projName;
												$("#userNames").val(str);
											} else {
												$("#userNames").val(null);
											}
										},
										error : function() {
											alert("系统错误！");
										}
									})
							//项目、项目组二级联动
							$
									.ajax({
										type : "post",
										url : "${pageContext.request.contextPath}/message/findProjectTeamByProjId.action",
										data : {
											projId : $(this).val(),
										},
										dataType : "json",
										success : function(data) {
											$("#teamId").empty();
											for (var int = 0; int < data.res.length; int++) {
												var teamId = data.res[int].teamId;
												var teamName = data.res[int].teamName;
												var option = $("<option>").val(
														teamId).text(teamName);
												$("#teamId").append(option);
											}

										}

									});
						})
		//查询项目组名显示到收件人
		$("#teamId")
				.click(
						function() {
							$("#toProjId").val(null);
							$("#toTeamId").val($(this).val());

							$
									.ajax({
										type : "POST",
										dataType : "json",
										url : "${pageContext.request.contextPath}/message/findTeamNameById.action",
										data : {
											teamId : $(this).val(),
										},
										success : function(data) {
											if (!!data.teamName) {
												var str = data.teamName;
												$("#userNames").val(str);
											} else {
												$("#userNames").val(null);
											}
										},
										error : function() {
											alert("系统错误！");
										}
									})
						})
		$(".goBack").click(function() {
			window.history.back();
		})
		
		function doUpload(){
        var formData = new FormData($("#uploadForm")[0],$("#uploadForm")[1],$("#uploadForm")[2]);
        $.ajax({  
            url : "${pageContext.request.contextPath}/message/upload.action",  
            type: 'POST',
            dataType : "json",  
            data: formData,  
            async: false,  
            cache: false,  
            contentType: false,  
            processData: false, 
            success : function(data) {
            if (data.filePath==0) {
				alert("没有选择文件！")
			}else {
				 $("#filePath").val(data.filePath)
				 
                alert("成功了！")
			}  
               
            },  
            error : function(data) {  
                alert("失败了！");
            }
        }); 
    }
	</script>
</body>
</html>



