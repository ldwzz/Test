<%@ page language="java" pageEncoding="gbk"%>
<%@ page contentType="application/msexcel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%-- 
Word只需要把contentType="application/msexcel"改为contentType="application/msword" 
--%>
<%   
  //独立打开excel软件   
  response.setHeader("Content-disposition","attachment; filename=MyExcel.xls");   
  
//嵌套在ie里打开excel   
  
//response.setHeader("Content-disposition","inline; filename=MyExcel.xls");   
  
//response.setHeader("Content-disposition","inline; filename=wwww.doc");   
%>  
<html>  
<head>  
<title>导出用户列表</title>  
</head>  
<body>  
<table width="500" border="1" align="center">  
  <tr>  
    <td colspan="6" align="center">用户列表</td>  
  </tr>  
  <tr>  
    <td width="15%">ID</td>  
    <td width="15%">用户昵称</td>  
    <td width="15%">用户类型</td>  
    <td width="15%">用户状态</td>
    <td width="15%">创建人</td> 
    <td width="20%">创建时间</td>   
  </tr> 
<c:forEach items="${page.resultList}" var="user"> 
  <tr>  
    <td>${user.userId}</td>  
    <td>${user.nickName}</td>  
    <td>
    <c:if test="${user.userType==1}">超级管理员</c:if>
    <c:if test="${user.userType==2}">管理员</c:if>
    <c:if test="${user.userType==3}">普通用户</c:if>
    </td>  
    <td>
	<c:if test="${user.userState==0}">禁用</c:if>
	<c:if test="${user.userState==1}">启用</c:if>
	</td> 
    <td>
    	<c:if test="${user.createBy==1}">超级管理员</c:if>
    	<c:if test="${user.createBy==2}">管理员</c:if>
    </td>  
    <td>${user.createTime}</td>   
  </tr> 
 </c:forEach>  
</table>  
</body>  
</html>  
