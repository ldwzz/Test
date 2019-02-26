<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="application/msexcel" %> 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%-- 
Word只需要把contentType="application/msexcel"改为contentType="application/msword" 
--%>
<%   
  //独立打开excel软件   
  //response.setHeader("Content-disposition","attachment; filename=MyExcel.xls");   
  
//嵌套在ie里打开excel   
  
//response.setHeader("Content-disposition","inline; filename=MyExcel.doc");   
  
response.setHeader("Content-disposition","inline; filename=MyExcel.xls");   
%>  
<html>  
<head>  
<title>测试导出Excel和Word</title>  
</head>  
<body>  
	<table width="500" border="1" align="center">
					<tr>
						<td>ID</td>
						<td>ip</td>
						<td>url</td>
						<td>内容</td>
						<td>异常信息</td>
						<td>创建时间</td>	
						<td>操作人</td>	
					</tr>
				
					<c:forEach items="${page.resultList}" var="log">
						<tr>
							<td >${log.logId}</td>
							<td >${log.ipAddress}</td>
							<td >${log.url}</td>
							
							<td >${log.logInfo}</td>
							<td >${log.exception} </td>
							<td >${log.createTime}</td>
							<td >${log.userName}</td>						
						</tr>
						</c:forEach>
				</table>
</body>  
</html>  