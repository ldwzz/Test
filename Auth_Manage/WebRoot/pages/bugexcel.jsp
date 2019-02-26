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
<table class="table table-hover table-bordered role-list"
			style="text-align: center;">
			<tr><td >项目Id</td>
				<td >项目名称</td>
				<td >模块</td>
				<td >BUG编号</td>
				<td>BUG名称</td>
				<td>BUG状态</td>
				<td >BUG级别</td>
				<td>执行人</td>
				
			</tr>
			<c:forEach items="${page.resultList}" var="bug">
				<tr>
				<td class="projId">${bug.projId}</td>
					<td class="projName">${bug.projName}</td>
					<td class="className">${bug.className}</td>
					<td class="bugNum">${bug.bugNum}</td>
					<td class="bugTitle">${bug.bugTitle}</td>
					<td class="bugState">${bug.bugState==0?'新建':(bug.bugState==1?'已分配':(bug.bugState==2?'已解决':'已关闭'))}</td>
					<td class="bugLevel">${bug.bugLevel==0?'普通':(bug.bugLevel==1?'严重':'缺陷')}</td>
					<td class="executor">${bug.executor}</td>
					
				</tr>
			</c:forEach>
		</table>
</body>  
</html>  
