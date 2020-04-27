<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업주 마이페이지</title>
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
</script>
</head>
<div class="container">
	<jsp:include page="../lhcHeader.jsp"/>
</div>
<body style="background-color: aliceblue">
	<div class="container" >
		<br/>
		<h2 align="center" style="font-size: large;">업주 마이페이지</h2>
			<div class="form-group">
				<ul align="center">
					<li>
						<button onclick="window.location.href='/lhc/lhcMyChar/lhcVendorChar.lhc'"  class="btn btn-default">예약 내역</button>
					</li>
					<br/>
					<li>
						<button onclick="window.location.href='/lhc/lhcMember/lhcCustomerFavList.lhc'" class="btn btn-default">찜 회원 리스트</button>
					</li>
					<br/>
					<li>
						<button onclick="window.location.href='/lhc/lhcPcMenu/lhcPcMenuRegister.lhc'"  class="btn btn-default">메뉴 등록</button>
					</li>
					<br/>
					<li>
						<button onclick="window.location.href='/lhc/lhcPcSpec/lhcPcSpecRegister.lhc'"  class="btn btn-default">pc사양 등록</button>
					</li>
					<br/>
					<li>
						<button onclick="window.location.href='/lhc/lhcPc/lhcPcRegisterForm.lhc'"  class="btn btn-default">신규 pc 등록 요청</button>
					</li>
					<br/>
					<li>
						<button onclick="window.location.href='/lhc/lhcPc/lhcPcContent.lhc?lhc_num=${pc.lhc_num}&pageNum=${pageNum}&state=${state}'"  class="btn btn-default">PC 정보</button>
					</li>
					<br/>
					<li>
						<button onclick="window.location.href='/lhc/lhcMember/lhcModifyForm.lhc'"  class="btn btn-default">회원정보수정</button>
					</li>
					<br/>
					<li>
						<button onclick="window.location.href='/lhc/lhcMember/lhcDeleteForm.lhc?sep=${sep}'"  class="btn btn-default">회원정보삭제</button>
					</li>
					<br/>
					<li>
						<button onclick="window.location.href='/lhc/lhcMember/lhcMain.lhc'"  class="btn btn-default">메인</button>
					</li>
				</ul>
			</div>
	</div>
</body>
</html>