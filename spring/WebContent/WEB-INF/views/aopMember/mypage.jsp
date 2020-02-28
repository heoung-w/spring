<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/spring/resource/style.css" rel="stylesheet" type="text/css">
</head>
<c:if test="${sessionScope.memId ==null }">
	<script>
		alert("로그인 후 이용해주세요")!!
		window.location.href="/spring/aopMember/loginForm.do";
	</script>
</c:if>
<c:if test="${sessionScope.memId != null }">
<body>
<br/>
<h2 align="center">마이페이지</h2>
<table>
	<tr>
		<td colspan="2">${sessionScope.memId} 님</td>
	</tr>
	<tr>
		<td>
			<button onclick="window.location.href='/spring/aopMember/modifyForm.do'" >회원정보변경</button>
			<button onclick="window.location.href='/spring/aopMember/deleteForm.do'" >회원정보삭제</button>
		</td>
	</tr>
</table>
</body>
</c:if>
</html>