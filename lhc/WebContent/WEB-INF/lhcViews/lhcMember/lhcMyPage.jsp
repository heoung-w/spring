<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	}
	.starter-template {
	  padding: 40px 15px;
	  text-align: center;
	}
</style>
<style>
ul{
   list-style:none;
   }
</style>
<div class="container">
	<jsp:include page="../lhcHeader.jsp"/>
</div>
</head>
<c:if test="${sessionScope.memId ==null}">
	<script>
		alert("로그인 후 이용해주세요")!!
		window.location.href="/lhc/lhcMember/lhcMain.lhc";  
	</script>
</c:if>
<c:if test="${sessionScope.memId != null }">
<body style="background-color: aliceblue">
<div class="container" >
<br/>
<h2 align="center" style="font-size: large;">회원 마이페이지</h2>
<div class="form-group">
	<ul align="center">
		<li colspan="6">${sessionScope.memId} 님</li>
	</ul>
	<ul align="center">
		<li>
			<button onclick="window.location.href='/lhc/lhcMember/lhcMyFavoriteListForm.lhc'" class="btn btn-default">pc방 찜목록</button>
		</li>
		<li>
			<button onclick="window.location.href='/lhc/lhcMyChar/lhcMyCharForm.lhc'" class="btn btn-default">예약 내역</button>
		</li>
		<li>
			<button onclick="window.location.href='/lhc/lhcMember/lhcMyPointForm.lhc'" class="btn btn-default">포인트 충전</button>
		</li>
		<li>
			<button onclick="window.location.href='/lhc/lhcMember/lhcModifyForm.lhc'" class="btn btn-default">회원정보수정</button>
		</li>
		<li>
			<button onclick="window.location.href='/lhc/lhcMember/lhcDeleteForm.lhc'" class="btn btn-default">회원정보삭제</button>
		</li>
		<li>
			<button onclick="window.location.href='/lhc/lhcMember/lhcMain.lhc'" class="btn btn-default">메인</button>
		</li>
	</ul>
</div>
</div>
<div class="container">
<jsp:include page="../mFooter.jsp"/>
</div>
</body>                  
</c:if>


</html>