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
<title>�����û��б�</title>  
</head>  
<body>  
<table width="500" border="1" align="center">  
  <tr>  
    <td colspan="6" align="center">�û��б�</td>  
  </tr>  
  <tr>  
    <td width="15%">ID</td>  
    <td width="15%">�û��ǳ�</td>  
    <td width="15%">�û�����</td>  
    <td width="15%">�û�״̬</td>
    <td width="15%">������</td> 
    <td width="20%">����ʱ��</td>   
  </tr> 
<c:forEach items="${page.resultList}" var="user"> 
  <tr>  
    <td>${user.userId}</td>  
    <td>${user.nickName}</td>  
    <td>
    <c:if test="${user.userType==1}">��������Ա</c:if>
    <c:if test="${user.userType==2}">����Ա</c:if>
    <c:if test="${user.userType==3}">��ͨ�û�</c:if>
    </td>  
    <td>
	<c:if test="${user.userState==0}">����</c:if>
	<c:if test="${user.userState==1}">����</c:if>
	</td> 
    <td>
    	<c:if test="${user.createBy==1}">��������Ա</c:if>
    	<c:if test="${user.createBy==2}">����Ա</c:if>
    </td>  
    <td>${user.createTime}</td>   
  </tr> 
 </c:forEach>  
</table>  
</body>  
</html>  
