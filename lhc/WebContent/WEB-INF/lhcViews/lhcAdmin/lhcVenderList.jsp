<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="/lhc/lhcResources/style.css" rel="stylesheet" type="text/css">
</head>
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  		crossorigin="anonymous">
	</script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">	
<style>
	body {
	  padding-top: 50px;
	background-image: url('/lhc/lhcResources/lhcImg/4.jpg');
	background-repeat : no-repeat;
	background-size : cover;
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
span{
display:inline-block; width:100px; white-space:nowrap; overflow:hidden; text-overflow:ellipsis; 
}

</style>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<div class = "container">
<jsp:include page="/WEB-INF/lhcViews/lhcHeader.jsp"></jsp:include>
</div>
<body style="background-color: aliceblue">
<body>
	<br />
	<h1 align="center" style="color:white">업주 회원 리스트</h1>	
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
		<tr style="color: skyblue;">
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
			<tr style="color: white;">
				<td>${number}
					<c:set var="number" value="${number-1}" />
				</td>
				<td><a style="color: skyblue;" href="/lhc/lhcPc/lhcPcContent.lhc?lhc_num=${pc.lhc_num}&pageNum=${currentPage}">${article.lhc_id}</a></td>
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