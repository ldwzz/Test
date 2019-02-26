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
<title>导出角色列表</title>  
</head>  
<body>  
<table width="500" border="1" align="center">  
  <tr>  
    <td colspan="5" align="center">角色列表</td>  
  </tr>  
  <tr>  
    <td width="15%">ID</td>  
    <td width="15%">名称</td>  
    <td width="15%">描述</td>  
    <td width="15%">状态</td>
    <td width="15%">code</td>   
  </tr> 
<c:forEach items="${page.resultList}" var="role"> 
  <tr>  
    <td>${role.roleId}</td>  
    <td>${role.roleName}</td>  
    <td>${role.roleDesc}</td>  
    <td>
	<c:if test="${role.roleState==0}">禁用</c:if>
	<c:if test="${role.roleState==1}">启用</c:if>
	</td> 
    <td>${role.roleCode}</td>   
  </tr> 
 </c:forEach>  
</table>  
</body>  
</html>  
