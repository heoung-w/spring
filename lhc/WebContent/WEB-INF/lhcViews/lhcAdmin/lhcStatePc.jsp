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
	<h1 align="center">PC방 전체 리스트</h1>	
	<%--Pc방 정보글 없을때 --%>
	<c:if test="${count == 0}">
	<table>
		<tr>
			<td>pc방 정보가 없습니다.</td>
		</tr>
	</table>
	</c:if>
	
	<%--PC방 정보 있을때 --%>
	<c:if test="${count > 0}">
	<table>
		<tr>
			<td>No.</td>
			<td>PC방 명</td>
			<td>사진</td>
			<td>전화번호</td>
			<td>주소</td>
			<td>정보</td>
			<td>좌석 수</td>
		</tr>
		<!-- for문 반복문 돌아가기  -->
	<c:forEach var="pc" items="${pcList}">
		<tr>
			<td>${number}
				<c:set var="number" value="${number-1}" />
			</td>
			<td><a href="/lhc/lhcPc/lhcPcContent.lhc?lhc_num=${pc.lhc_num}&pageNum=${currentPage}&state=${pc.lhc_state}">${pc.lhc_name}</a></td>
			<td>
				<c:if test="${pc.lhc_img==null}">
					<img src="/lhc/lhcResources/lhcImg/Desert.jpg" width="100"/>
				</c:if>
				<c:if test="${pc.lhc_img!=null }">
					<img src="/lhc/lhcSave/${pc.lhc_img}" width="100"/>
				</c:if>
			</td>
			<td>${pc.lhc_phone}</td>
			<td>${pc.lhc_addr}</td>
			<td>${pc.lhc_info}</td>
			<td>${pc.lhc_seats}</td>
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