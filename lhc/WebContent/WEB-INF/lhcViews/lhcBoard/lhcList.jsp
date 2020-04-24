<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공지게시판</title>
	
	<!-- bootstrap 적용하기-->
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
    background: #F3F6F7;
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
		<h1 align="center" style="color: skyblue;"> 공지 게시판</h1>
		
		<%--게시글 없을때 --%>
		<c:if test="${count == 0}">
		<table>
			<tr>
				<td>
					<button onclick="window.location='/lhc/lhcBoard/lhcWriteForm.lhc'" style="color: skyblue;">글쓰기</button>
				</td>
			</tr>
			<tr>
				<td style="color: skyblue;">공지사항이 없습니다.</td>
			</tr>
		</table>
		</c:if>
	</div>
	<%--공지사항이 있을때 --%>
	<div class = "container">
		<c:if test="${count > 0}">
		<table class="type09">
			<tr>
				<td colspan="6" align="right">
					<%--  <c:if test="${"관리자 == true"}" 즉 관리자일 경우만 글쓰기 보여주기 임시로 만들어놓은것 --%>
					<button onclick="window.location='/lhc/lhcBoard/lhcWriteForm.lhc'">글쓰기</button>
					<%-- </c:if> --%>
				</td>
			</tr>
			<thread>
				<tr scope="cols" style="color:skyblue;">
					<td>No.</td>
					<td>제 목</td>
					<td>작성자</td>
					<td>게시일</td>
					<td>조회수</td>
				</tr>
			</thread>
			<!-- for문 반복문 돌아가기 -->
		<c:forEach var="article" items="${articleList}">
			<tr style="color: white;">
				<td>${number}
					<c:set var="number" value="${number-1}" />
				</td>
				<td><a style="color: skyblue;" href="/lhc/lhcBoard/lhcContent.lhc?lhc_num=${article.lhc_num}&pageNum=${currentPage}">${article.lhc_subject}</a></td>
				<td>${article.lhc_writer}</td>
				<td>${article.lhc_reg}</td>
				<td>${article.lhc_readcount}</td>
			</tr>
		</c:forEach>
		</table>
		</c:if>
	</div>
	
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