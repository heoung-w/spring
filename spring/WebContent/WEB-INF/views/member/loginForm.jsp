<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
	<link href="/spring/resource/style.css" rel="stylesheet" type="text/css" >
</head>
<c:if test ="${sessionScope.memId !=null}">
	<c:redirect url="/spring/member/main.do"></c:redirect>
</c:if>
<c:if test ="${sessionScope.memId ==null}">
<body>
	<br />
	<h2 align="center" > 로그인 </h2>
	<form action="/spring/member/loginPro.do" method="post">
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
					<input type="button" value="메인" onclick="window.location.href='/spring/member/main.do'" />
					<input type="submit" value="로그인" />
					<input type="button" value="회원가입" onclick="window.location.href='/spring/member/signupForm.do'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</c:if>
</html>






