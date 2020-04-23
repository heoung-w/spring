<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>lhcMyFavoriteListForm</title>
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
</head>
<div class = "container">
<jsp:include page="/WEB-INF/lhcViews/lhcHeader.jsp"></jsp:include>
</div>
<body style="background-color: aliceblue">
<div class="container">
	<table class="type09">
		<thead>
			<tr scope="cols">
				<th>No.</th>
				<th>PC방 명</th>
				<th>사진</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>좌석 수</th>
			</tr>
		</thead>
		<!-- for문 반복문 돌아가기  -->
	<c:forEach var="favlist" items="${favList}">
		<tr>
			<th scope="row">${numnum+1}번
				<c:set var="numnum" value="${numnum+1}" />
			</th>
			<td><a href="/lhc/lhcPc/lhcPcContent.lhc?lhc_num=${favlist.lhc_num}&pageNum=${currentPage}&state=${favlist.lhc_state}">${favlist.lhc_name}</a></td>
			<td>
				<c:if test="${favlist.lhc_img==null}">
					<img src="/lhc/lhcResources/lhcImg/Desert.jpg" width="100"/>
				</c:if>
				<c:if test="${favlist.lhc_img!=null }">
					<img src="/lhc/lhcSave/${favlist.lhc_img}" width="100"/>
				</c:if>
			</td>
			<td>${favlist.lhc_phone}</td>
			<td>${favlist.lhc_addr}</td>
			<td>${favlist.lhc_seats}</td>
		</tr>
	</c:forEach>
	</table>
	<table align="center">
		<tr >
			<td colspan="6"><button onclick = "history.go(-1)" style="color: black;" class="btn btn-default">카테고리 보기</button></td>
		</tr>
	</table>
</div>
</body>
</html>