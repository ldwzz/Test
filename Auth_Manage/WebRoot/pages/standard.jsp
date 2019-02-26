<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<ul class="pager">
<li><a>共 ${page.totalNum} 条&nbsp; </a></li>
<li><a>${page.currNo}/ ${page.totalPage}  &nbsp;</a></li>
<c:if test="${page.currNo==1}"><li><a>首页</a></li></c:if>
<c:if test="${page.currNo!=1}"><li><a href="${pageContext.request.contextPath}/${page.url}?${page.params}&currNo=1&pageNum=${page.pageNum}">首页</a></li></c:if>&nbsp;
<c:if test="${page.currNo==1}"><li><a>上一页</a></li></c:if>
<c:if test="${page.currNo!=1}"><li><a href="${pageContext.request.contextPath}/${page.url}?${page.params}&currNo=${page.currNo-1}&pageNum=${page.pageNum}">上一页</a></li></c:if>&nbsp;
<c:if test="${page.currNo==page.totalPage}"><li><a>下一页</a></li></c:if>
<c:if test="${page.currNo!=page.totalPage}"><li><a href="${pageContext.request.contextPath}/${page.url}?${page.params}&currNo=${page.currNo+1}&pageNum=${page.pageNum}">下一页</a></li></c:if>&nbsp;
<c:if test="${page.currNo==page.totalPage}"><li><a>尾页</a><li></c:if>
<c:if test="${page.currNo!=page.totalPage}"><li><a href="${pageContext.request.contextPath}/${page.url}?${page.params}&currNo=${page.totalPage}&pageNum=${page.pageNum}">尾页</a><li></c:if>&nbsp;
<li><a>第<select onchange="location.href='${pageContext.request.contextPath}/${page.url}?${page.params}&pageNum=${page.pageNum}&currNo='+this.value">
	<c:forEach var="selectvalue" begin="1" end="${page.totalPage}" step="1">
		<option value="${selectvalue}" ${page.currNo eq selectvalue ?"selected='selected'":""}>
			${selectvalue}   
		</option>
	</c:forEach>
</select>
页
</a></li>
<li><a>
每页
<select
	onchange="location.href='${pageContext.request.contextPath}/${page.url}?${page.params}&pageNum='+this.value">
	<option value="5" ${page.pageNum ==5 ?"selected='selected'":""}>5</option>
	<option value="10" ${page.pageNum ==10 ?"selected='selected'":""}>10</option>
	<option value="15" ${page.pageNum ==15 ?"selected='selected'":""}>15</option>
	<option value="20" ${page.pageNum ==20 ?"selected='selected'":""}>20</option>
</select>
条
</a></li>
</ul>

