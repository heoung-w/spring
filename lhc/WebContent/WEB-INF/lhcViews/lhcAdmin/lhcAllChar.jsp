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
	<h1 align="center" style="color: skyblue;">관리자</h1>	
	<%--예약 정보글 없을때 --%>
	<c:if test="${count == 0}">
	<table>
		<tr>
			<td>예약 정보가 없습니다.</td>
		</tr>
	</table>
	</c:if>
	
	<%--예약 정보 있을때 --%>
	<c:if test="${count > 0}">
	<table>
		<tr>
			<td>No.</td>
			<td>예약번호</td>
			<td>피시방이름</td>
			<td>예약 회원</td>
			<td>가격</td>
			<td align="center">예약시간</td>
			<td align="center">종료시간</td>
		</tr>
		<!-- for문 반복문 돌아가기  -->
	<c:forEach var="charList" items="${charList}">
		<tr style="color: white;">
			<td>${number}
				<c:set var="number" value="${number-1}" />
			</td>
			<td>${charList.lhc_count}</td>
			<td><a style="color: skyblue;" href="/lhc/lhcPc/lhcPcContent.lhc?lhc_num=${charList.lhc_num}&pageNum=${currentPage}">${charList.lhc_name}</a></td>
			<td>${charList.lhc_id}</td>
			<td>${charList.lhc_price}원</td>
			<td>${charList.lhc_reg}</td>
			<td>${charList.lhc_end}</td>
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
			<a href="/lhc/lhcPc/lhcPcListAll.lhc?pageNum=${startPage-pageBlock}" > &lt;</a>
		</c:if>
		
		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
			<a href="/lhc/lhcPc/lhcPcListAll.lhc?pageNum=${i}" class="nums"> &nbsp; ${i} &nbsp;</a>
		</c:forEach>
		
		<c:if test="${endPage < pageCount}">
			<a href="/lhc/lhcPc/lhcPcListAll.lhc?pageNum=${startPage+pageBlock}"> &gt;</a>
		</c:if>
	</c:if>
	</div>
</body>
</html>