<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<title>登录页面</title>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/static/css/login.css"
	rel="stylesheet">
<style type="text/css">
.red {
	color: red
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/jquery.cookie.js"></script>
<script type="text/javascript">

	var flag=false;
	
	$(function(){
		var userName="${cookie.userName.value}";
		if(!!userName){
			$("input[name='rememberMe']").each(function(){
				$(this).prop("checked",true);
			})
		}
	})
	//验证码
	$(document).ready(function(){		
		$("#inputCode").keyup(function(){
		var vCode=$("#inputCode").val();
			if(!!vCode){
				$.ajax({
					type:"POST",
					url:"${pageContext.request.contextPath}/user/valiCode.action",
					dataType:"json",
					data:{
						vCode:vCode
					},
					success:function(json){
						if(json.msg==1){
						$("#error_html").removeClass("red").html("");					
						flag= true;
						}else{
						$("#error_html").addClass("red").html("验证码不正确");
						flag=false;
						}
					},
					error:function(){
						alert("系统错误");
						flag=false;
					}
				})
			}else{
				alert("验证码不能为空");
				flag=false;
			}
		})
	})
	//登录
	function login(){
		var username=$("#inputEmail").val();
		var pwd=$("#inputPassword").val();
		var vCode=$("#inputCode").val();
		if(!/^\w{4,16}$/.test(username)){		
			$("#userNameError").addClass("red").html("用户名不合法，数字、字母、下划线、4-16位");
		}else if(pwd.length<6 || pwd.length>16){
			$("#passwordError").addClass("red").html("密码不合法，6-16位");
		}else if(!flag){
			alert("验证码不正确！");
		}else{
			$.ajax({
				type:"POST",
				dataType:"json",
				url:"${pageContext.request.contextPath}/user/login.action",
				data:{
					userName:username,
					userPwd:pwd,
					vCode:vCode
				},
				success:function(data){
					if(data.msg==1){
						//登录成功
		                  var chc=$("input[name='rememberMe']:checked"); 
		                  //判断checkbox是否被选中
		                  if(!!chc[0]){		             
		                  //expires:7 --------时效7天
		                    $.cookie("userName", username,{expires:7});
		                    $.cookie("userPwd", pwd,{expires:7});
		                    //token加密标识
		                  	//$.cookie("token", token);
		                  }else{
		                  	//清空
		                  	$.cookie("userName", null);
		                   }
						window.location.href="${pageContext.request.contextPath}/user/index.action";
					}else if(data.msg==-1){
						alert("登录失败");
						return false;
					}else if(data.msg==2){
						alert("未审核");
						return false;
					}else{
						alert("验证码错误");
						return false;
					}				
				},
				error:function(){
					alert("系统错误");
					return false;
				}
			})
		} 
	}		
		 $(document).keypress(function (event) {
                // 回车键事件                  
                if (event.keyCode == 13) {
                    $("#submit").click(); //调用登录按钮的登录事件
                }
            });
		
</script>
</head>
<body>
	<div class="panel panel-default">
		<h3 style="text-align: center;">请登录</h3>
		<form class="form-signin" method="post" action="##">
			<label for="inputEmail" class="sr-only">用户名</label>
			<div style="height: 32px;">
				<span class="glyphicon glyphicon-user"></span> <input
					type="text" id="inputEmail" class="form-control class123456"
					placeholder="用户名" name="username" maxlength="20" value="${cookie.userName.value}">
			</div>
			<label id="userNameError"></label> <label for="inputPassword"
				class="sr-only">密码</label>
			<div style="height: 32px;">
				<span class="glyphicon glyphicon-lock"></span> <input
					type="password" id="inputPassword" class="form-control"
					placeholder="密码" name="password" maxlength="10" value="${cookie.userPwd.value}">
			</div>
			<label id="passwordError"></label><br>
			<div style="margin-left: 20px;">
				<label for="inputEmail" class="sr-only">验证码</label> <input
					type="text" id="inputCode" placeholder="验证码" name="code"					
					style="width:80px;text-transform:uppercase;ime-mode:disabled"
					maxlength="4"> <img id="vdimgck"
					src="${pageContext.request.contextPath}/pages/image.jsp?d=" +new
					Date()+"" alt="看不清？点击更换" align="absmiddle" width="75"
					style="cursor:pointer" onclick="this.src=this.src+'?'" /> <label
					id="error_html" style="font-size:18px;"></label>
				<div class="checkbox" id="checkboxid" >
					<label> <input type="checkbox" name="rememberMe">
						记住我
					</label>
				</div>
				<p class="bg-warning">${error}</p>
				<button class="btn btn-lg btn-primary btn-block" type="button"
					onclick="return login()" id="submit">登录</button>
			</div>
		</form>
	</div>
</body>
</html>
