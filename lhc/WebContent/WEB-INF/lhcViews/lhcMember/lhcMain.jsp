<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Main</title>
	<link href="/lhc/lhcResources/style.css" rel="stylesheet" type="text/css">
</head>

<%-- 세션x, 쿠키x --%>
<c:if test="${sessionScope.memId == null}">
<body>
	<br />
	<h1 align="center"> 메인페이지 </h1>
	<table>
		<tr>
			<td> 로그인 원하시면 버튼을 누르세요 <br />
				<button onclick = "window.location.href='/lhc/lhcMember/lchLoginForm.lhc'" > 로그인 </button>
			</td>
		</tr>
		<tr>
			<td>
				<button onclick = "window.location.href='/lhc/lhcMember/lhcSignupForm.lhc'" > 회원가입 </button>
			</td>
		</tr>
	</table>
	<br /><br /><br /><br />

</body>
</c:if>	

<%-- 세션o --%>
<c:if test="${sessionScope.memId != null}">
<body>
	<br />
	<h1 align="center"> 메인페이지 </h1>
	<table>
		<tr>
			<td>${sessionScope.memId}님 환영합니다.<br />
				<button onclick="window.location.href='/lhc/lhcMember/logout.lhc'" >로그아웃</button>
				<button onclick="window.location.href='/lhc/lhcMember/mypage.lhc'" >마이페이지</button>
			</td>
		</tr>
	</table>
	<br /><br /><br /><br />
</body>
</c:if>

</html>