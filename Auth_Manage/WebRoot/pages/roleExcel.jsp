<%@ page language="java" pageEncoding="gbk"%>
<%@ page contentType="application/msexcel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%-- 
Wordֻ��Ҫ��contentType="application/msexcel"��ΪcontentType="application/msword" 
--%>
<%   
  //������excel���   
  response.setHeader("Content-disposition","attachment; filename=MyExcel.xls");   
  
//Ƕ����ie���excel   
  
//response.setHeader("Content-disposition","inline; filename=MyExcel.xls");   
  
//response.setHeader("Content-disposition","inline; filename=wwww.doc");   
%>  
<html>  
<head>  
<title>������ɫ�б�</title>  
</head>  
<body>  
<table width="500" border="1" align="center">  
  <tr>  
    <td colspan="5" align="center">��ɫ�б�</td>  
  </tr>  
  <tr>  
    <td width="15%">ID</td>  
    <td width="15%">����</td>  
    <td width="15%">����</td>  
    <td width="15%">״̬</td>
    <td width="15%">code</td>   
  </tr> 
<c:forEach items="${page.resultList}" var="role"> 
  <tr>  
    <td>${role.roleId}</td>  
    <td>${role.roleName}</td>  
    <td>${role.roleDesc}</td>  
    <td>
	<c:if test="${role.roleState==0}">����</c:if>
	<c:if test="${role.roleState==1}">����</c:if>
	</td> 
    <td>${role.roleCode}</td>   
  </tr> 
 </c:forEach>  
</table>  
</body>  
</html>  
