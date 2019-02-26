<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
           

            <a class="navbar-brand" >欢迎
            <c:if test="${sessionScope.role=='admin'}">管理员</c:if>
            <c:if test="${sessionScope.role=='project_manager'}">经理</c:if>
            <c:if test="${sessionScope.role=='project_leader'}">组长</c:if>
            <c:if test="${sessionScope.role=='project_member'}">成员</c:if>
		            ：${sessionScope.userInfo.userName}</a>      
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
          	<li  style="display:inline-block;background-color:#101010;border: none;width:120px;">
				<span class="label label-default label-pill pull-right" >${unread}</span> 
				<a href="${pageContext.request.contextPath}/message/findMyMessage.action?readState=0">未读消息</a>
			</li>
			<c:if test="${!fn:contains(Rolecodes,'admin')}"> 
			<li  style="display:inline-block;background-color:#101010;border: none;width:120px;">
				<span class="label label-default label-pill pull-right" >${bugCount}</span> <a  href="${pageContext.request.contextPath}/bug/buginfo.action?turn=myBug">待解决的BUG</a>
			</li>
			</c:if> 
		<c:if test="${!fn:contains(Rolecodes,'admin')}"> 
			<li  style="display:inline-block;background-color:#101010;border: none;width:120px;">
				<span class="label label-default label-pill pull-right" ><c:if test="${tasksNum==0}">${tasksNum}</c:if>${tasksNum}</span> 
				<c:if test="${tasksNum!='0'}">
				<a href="${pageContext.request.contextPath}/proj/list.action?flag=0">快过期任务</a></c:if>
				<c:if test="${tasksNum=='0'}"><a onclick="tips()">快过期任务</a></c:if>
			</li>
			</c:if> 
            <li><a href="#" onclick="javascript:history.back(-1);">返回</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/index.jsp">首页</a></li>
            <li><a href="${pageContext.request.contextPath}/user/tologin.action">退出</a></li>
          </ul>
          <!--<form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>-->
        </div>
      </div>
    </nav>
</body>
<script type="text/javascript">
	function tips(){
		alert("暂无快过期任务！")
	}
</script>
</html>