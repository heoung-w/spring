<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공지게시판</title>
</head>
<body>
	<br />
	<h1 align="center">게시판</h1>
	
	<%--게시글 없을때 --%>
	<c:if test="${count == 0}">
	<table>
		<tr>
			<td>
				<button onclick="window.location='/lhc/lhcBoard/lhcWriteForm.lhc'">글쓰기</button>
			</td>
		</tr>
		<tr>
			<td>공지사항이 없습니다.</td>
		</tr>
	</table>
	</c:if>
	
	<%--공지사항이 있을때 --%>
	<c:if test="${count > 0}">
	<table>
		<tr>
			<td colspan="6" align="right">
				<%--  <c:if test="${"관리자 == true"}" 즉 관리자일 경우만 글쓰기 보여주기 임시로 만들어놓은것 --%>
				<button onclick="window.location='/lhc/lhcBoard/lhcWriteForm.lhc'">글쓰기</button>
				<%-- </c:if> --%>
			</td>
		</tr>
		<tr>
			<td>No.</td>
			<td>제 목</td>
			<td>작성자</td>
			<td>게시일</td>
			<td>조회수</td>
		</tr>
		<!-- for문 반복문 돌아가기 -->
	<c:forEach var="article" items="${articleList}">
		<tr>
			<td>${number}
				<c:set var="number" value="${number-1}" />
			</td>
			<td><a href="/lhc/lhcBoard/lhcContent.lhc?lhc_num=${article.lhc_num}&pageNum=${currentPage}">${article.lhc_subject}</a></td>
			<td>${article.lhc_writer}</td>
			<td>${article.lhc_reg}</td>
			<td>${article.lhc_readcount}</td>
		</tr>
	</c:forEach>
	</table>
	</c:if>
	<br />
	<%--목록의 페이지 번호 뷰어 설정 --%>
	<div align="center">
	<c:if test="${count > 0}">
		<fmt:parseNumber var="res" value="${count/pageSize}" integerOnly="true" />
		<c:set var="pageCount" value="${res + (count % pageSize == 0 ? 0 : 1)}" />
		<c:set var="pageBlock" value="10" />
		<fmt:parseNumber var="result" value="${currentPage/pageBlock}" integerOnly="true" />
		<fmt:parseNumber var="startPage" value="${result * pageBlock + 1}" />
		<fmt:parseNumber var="endPage" value="${startPage + pageBlock - 1}" />
		<c:if test="${endPage > pageCount}" >
			<c:set var="endPage" value="${pageCount}" />
		</c:if>
		<c:if test="${startPage > pageBlock}">
			<a href="/lhc/lhcBoard/lhcList.lhc?pageNum=${startPage-pageBlock}" > &lt;</a>
		</c:if>
		
		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
			<a href="/lhc/lhcBoard/lhcList.lhc?pageNum=${i}" class="nums"> &nbsp; ${i} &nbsp;</a>
		</c:forEach>
		
		<c:if test="${endPage < pageCount}">
			<a href="/lhc/lhcBoard/lhcList.lhc?pageNum=${startPage+pageBlock}"> &gt;</a>
		</c:if>
	</c:if>
	</div>
</body>
</html>