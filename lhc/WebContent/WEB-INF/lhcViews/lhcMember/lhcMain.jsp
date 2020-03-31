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
	<form action = "/lhc/lhcMember/lhcLoginPro.lhc" method="post">
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id" /></td>
			<td rowspan="2">
				<input type="checkbox" name="auto" value="1" /> 자동로그인 
			</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="pw" /></td>
		</tr>
		<tr>
			<td colspan="3" align="right">
				<input type="submit" value="로그인" />
				<input type="button" value="회원가입" onclick="window.location.href='/lhc/lhcMember/lhcSignupForm.lhc'" />
			</td>
		</tr>
	</table>
	</form>
	<br /><br /><br /><br />
	<div align="center">
		<img src="/spring/resource/img/beach.jpg" width="700" />
	</div>
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
				<button onclick="window.location.href='/lhc/lhcMember/lhcLogout.lhc'" >로그아웃</button>
				<button onclick="window.location.href='/lhc/lhcMember/lhcMypage.lhc'" >마이페이지</button>
			</td>
		</tr>
	</table>
	<br /><br /><br /><br />
	<div align="center">
		<img src="/spring/resource/img/beach.jpg"  width="700" />
	</div>
</body>
</c:if>

</html>