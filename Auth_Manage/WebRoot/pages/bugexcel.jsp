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
<table class="table table-hover table-bordered role-list"
			style="text-align: center;">
			<tr><td >��ĿId</td>
				<td >��Ŀ����</td>
				<td >ģ��</td>
				<td >BUG���</td>
				<td>BUG����</td>
				<td>BUG״̬</td>
				<td >BUG����</td>
				<td>ִ����</td>
				
			</tr>
			<c:forEach items="${page.resultList}" var="bug">
				<tr>
				<td class="projId">${bug.projId}</td>
					<td class="projName">${bug.projName}</td>
					<td class="className">${bug.className}</td>
					<td class="bugNum">${bug.bugNum}</td>
					<td class="bugTitle">${bug.bugTitle}</td>
					<td class="bugState">${bug.bugState==0?'�½�':(bug.bugState==1?'�ѷ���':(bug.bugState==2?'�ѽ��':'�ѹر�'))}</td>
					<td class="bugLevel">${bug.bugLevel==0?'��ͨ':(bug.bugLevel==1?'����':'ȱ��')}</td>
					<td class="executor">${bug.executor}</td>
					
				</tr>
			</c:forEach>
		</table>
</body>  
</html>  
