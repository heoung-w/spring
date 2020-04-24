<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
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
</style>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<div class = "container">
<jsp:include page="/WEB-INF/lhcViews/lhcHeader.jsp"></jsp:include>
</div>
<body style="background-color: aliceblue">
<div class="container">
		<h1 align="center" style="color: white;">PC방 전체 리스트</h1>	
		<%--Pc방 정보글 없을때 --%>
		<c:if test="${count == 0}">
			<table>
				<tr>
					<td>
						<button onclick="window.location='/lhc/lhcPc/lhcPcRegister.lhc'">pc방 등록</button>
						<button onclick="window.location.href='/lhc/lhcMember/lhcMain.lhc'">메인</button>
					</td>
				</tr>
				<tr>
					<td>pc방 정보가 없습니다.</td>
				</tr>
			</table>
		</c:if>
	</div>
	
	<%--PC방 정보 있을때 --%>
	<div class = "container">
		<c:if test="${count > 0}">
			<table class="type09">
			<thead>
				<tr scope="cols">
					<th style="color: white;">No.</th>
					<th style="color: white;">PC방 명</th>
					<th style="color: white;">사진</th>
					<th style="color: white;">전화번호</th>
					<th style="color: white;">주소</th>
					<th style="color: white;">정보</th>
					<th style="color: white;">좌석 수</th>
				</tr>
			</thead>
				<!-- for문 반복문 돌아가기  -->
			<c:forEach var="pc" items="${pcList}">
				<tr style="color: white;">
					<th scope="row" style="color: black; background-color: gray;">${number}
						<c:set var="number" value="${number-1}" />
					</th>
					<td><a style="color: skyblue;" href="/lhc/lhcPc/lhcPcContent.lhc?lhc_num=${pc.lhc_num}&pageNum=${currentPage}&state=${pc.lhc_state}">${pc.lhc_name}</a></td>
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
	<table align="center">
		<tr >
			<td colspan="6"><button onclick = "history.go(-1)" style="color: black;" class="btn btn-default">뒤로</button></td>
		</tr>
	</table>
</body>
</html>