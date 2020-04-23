<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>내 예약 목록 보기</title>
	<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  		crossorigin="anonymous">
	</script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">	
<style>
	body {
	  padding-top: 50px;
	}
	.starter-template {
	  padding: 40px 15px;
	  text-align: center;
	}
table.type09 {
    border-collapse: collapse;
    text-align: left;
    line-height: 1.5;

}
table.type09 thead th {
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #369;
    border-bottom: 3px solid #036;
}
table.type09 tbody th {
    width: 150px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #f3f6f7;
}
table.type09 td {
    width: 350px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}
</style>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script>
	
	</script>
</head>
<div class = "container">
<jsp:include page="/WEB-INF/lhcViews/lhcHeader.jsp"></jsp:include>
</div>
<body style="background-color: aliceblue">
<div class="container">
	<br />
	<h1 align="center" style="font-size: x-large;">예약목록</h1>
	<%--예약목록이 없을때 --%>
	<c:if test="${count == 0}">
		<script>
			alert("예약목록이 없습니다.");
		</script>
		<c:redirect url="/lhcMember/lhcMyPage.lhc" />
	</c:if>
	<c:if test="${count > 0}">
		<div id="result"></div>
		<table class="type09">
			<thead>
				<tr scope="cols">
					<th>업체명</th>
					<th>좌석번호</th>
					<th>예약가격</th>
					<th>시작시간</th>
					<th>끝낸시간</th>
					<th>결제를 원하는것 선택</th>
				</tr>
			</thead>		
		<c:forEach var="resList" items="${resList}">	
			<tr>
				<th scope="row">${resList.lhc_name}</th>
				<td>${resList.lhc_seats}</td>
				<td>${resList.lhc_price}</td>
				<td>${resList.lhc_reg}</td>
				<%--조건문--%>
				<c:if test="${resList.lhc_reg == resList.lhc_end}">
					<td><h4>사용중</h4></td>
				</c:if>
				<c:if test="${resList.lhc_reg != resList.lhc_end}">
					<td>${resList.lhc_end}</td>
				</c:if>
				
				<td><c:if test="${resList.lhc_reg == resList.lhc_end}">
						<form id="input_form"  name="inputForm" method="post" action="/lhc/lhcMyChar/lhcMyCharPro.lhc" >
							<input type="hidden" id="lhcSeats" name="lhc_seats" value="${resList.lhc_seats}" />
							<input type="hidden" id="lhcNum" name="lhc_num" value="${resList.lhc_num}" />
							<input type="submit" id="input_form" value="결제하기" style="color: black;" class="btn btn-default"/>
						</form>
					</c:if>
					<c:if test="${resList.lhc_reg != resList.lhc_end}">
						<h3>결제완료</h3>
					</c:if>
				</td>
			</tr>

		</c:forEach>
			<tr align="center">
				<td colspan="6"><button onclick = "history.go(-1)" style="color: black;" class="btn btn-default">마이페이지</button></td>
			</tr>
			<%--action="/lhc/lhcMember/lhcMyCharPro.lhc" method="post" --%>
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
			<a href="/lhc/lhcMyChar/lhcMyCharForm.lhc?pageNum=${startPage-pageBlock}" > &lt;</a>
		</c:if>
		
		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
			<a href="/lhc/lhcMyChar/lhcMyCharForm.lhc?pageNum=${i}" class="nums"> &nbsp; ${i} &nbsp;</a>
		</c:forEach>
		
		<c:if test="${endPage < pageCount}">
			<a href="/lhc/lhcMyChar/lhcMyCharForm.lhc?pageNum=${startPage+pageBlock}"> &gt;</a>
		</c:if>
	</c:if>
	</div>
</div>
</body>
</html>