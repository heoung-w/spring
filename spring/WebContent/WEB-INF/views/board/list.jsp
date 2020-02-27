<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판</title>   
	<link href="/spring/resource/style.css" rel="stylesheet" type="text/css">
</head>

<body>
	<br />
	<h1 align="center"> 게시판 </h1>
	<%-- 게시글 없을때 --%>
	<c:if test="${count == 0}">
			<h2 align="center"> 게시글이 없습니다. </h2>
	</c:if>
	<br/>
	<%-- 게시글 있을때 --%>
	<c:if test="${count > 0}">
	<table>
		<tr>
			<td colspan="6" align="right">
				<button onclick="window.location.href='/spring/board/writeForm.do'" > 글쓰기 </button>
			</td>
		</tr>
		<tr>
			<td>No.</td>
			<td>작성자</td>
			<td>제  목</td>
			<td>시  간</td>
			<td>조회수</td>
		</tr>
		<c:forEach var="article" items="${articleList}">
			<tr>
				<td>${number}
					<c:set var="number" value="${number-1}" />
				</td>
				<td align="left">
					<c:set var="wid" value="0" />
					<c:if test="${article.re_level > 0}">
						<img src="/spring/resource/img/tabImg.PNG" width="${wid}" /> <%--배경색으로 들여쓰기 효과 --%>
						<img src="/spring/resource/img/replyImg.png" width="10" />		
					</c:if>
					<a href="mailto:${article.email}">${article.writer}</a>
				</td>
				<td><a href="/spring/board/content.do?num=${article.num}&pageNum=${currentPage}">${article.subject}</a></td>
				<td>${article.reg}</td>
				<td>${article.readcount}</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	<br />
	<%-- 목록의 페이지 번호 뷰어 설정 --%>
	<div align="center">
	<c:if test="${count > 0}">
		<fmt:parseNumber var="res" value="${count/pageSize}" integerOnly="true"  />
		<c:set var="pageCount" value="${res + (count % pageSize == 0 ? 0 : 1)}" />
		<c:set var="pageBlock" value="10" />
		<fmt:parseNumber var="result" value="${currentPage/pageBlock}" integerOnly="true" />
		<fmt:parseNumber var="startPage" value="${result * pageBlock +1}" />
		<fmt:parseNumber var="endPage" value="${startPage + pageBlock - 1}" />
		<c:if test="${endPage > pageCount}" >
			<c:set var="endPage" value="${pageCount}" />
		</c:if>
		
		<c:if test="${startPage > pageBlock}">
			<a href="/spring/board/list.do?pageNum=${startPage-pageBlock}" > &lt; </a>		
		</c:if>
		
		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
			<a href="/spring/board/list.do?pageNum=${i}" class="nums"> &nbsp; ${i} &nbsp; </a>
		</c:forEach>
		
		
		<c:if test="${endPage < pageCount}">
			<a href="/spring/board/list.do?pageNum=${startPage+pageBlock}" > &gt; </a>		
		</c:if>
	</c:if>
	</div>

</body>
</html>









