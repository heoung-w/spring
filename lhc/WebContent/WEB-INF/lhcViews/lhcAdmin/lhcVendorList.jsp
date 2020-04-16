<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<!--<jsp:include page="/WEB-INF/lhcViews/lhcHeader.jsp"></jsp:include> -->
<body>
	<br />
	<h1 align="center">회원 전체 리스트</h1>	
	<%--회원 정보글 없을때 --%>
	<c:if test="${count == 0}">
	<table>
		<tr>
			<td>회원 정보가 없습니다.</td>
		</tr>
		<tr>
			<td>
				<button onclick = "window.location.href='/lhc/lhcMember/lhcMain.lhc'">메인</button>
			</td>
		</tr>
	</table>
	</c:if>
	
	<%--PC방 정보 있을때 --%>
	<c:if test="${count > 0}">
	<table>
		<tr>
			<td>No.</td>
			<td>회원아이디</td>
			<td>회원명</td>
			<td>전화번호</td>
			<td>찜 pc방</td>
			<td>보유금액</td>
		</tr>
		<!-- for문 반복문 돌아가기 -->
	<c:forEach var="article" items="${articleList}">
		<c:if test ="${article.lhc_sep == 'v'}">
			<tr>
				<td>${number}
					<c:set var="number" value="${number-1}" />
				</td>
				<td><a href="/lhc/lhcPc/lhcPcContent.lhc?lhc_num=${pc.lhc_num}&pageNum=${currentPage}">${article.lhc_id}</a></td>
				<td>${article.lhc_name}</td>
				<td>${article.lhc_phone}</td>
				<td>${article.lhc_favorite}</td>
				<td>${article.lhc_money}</td>
			</tr>
		</c:if>
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
			<a href="/lhc/lhcAdmin/lhcMemberList.lhc?pageNum=${startPage-pageBlock}" > &lt;</a>
		</c:if>
		
		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
			<a href="/lhc/lhcAdmin/lhcMemberList.lhc?pageNum=${i}" class="nums"> &nbsp; ${i} &nbsp;</a>
		</c:forEach>
		
		<c:if test="${endPage < pageCount}">
			<a href="/lhc/lhcAdmin/lhcMemberList.lhc?pageNum=${startPage+pageBlock}"> &gt;</a>
		</c:if>
	</c:if>
	</div>
</body>
</html>