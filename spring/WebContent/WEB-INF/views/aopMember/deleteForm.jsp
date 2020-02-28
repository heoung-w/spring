<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/spring/resource/style.css" rel="stylesheet" type="text/css"/>
</head>
<c:if test="${sessionScope.memId ==null}">
	<script>
		alert("로그인후 이용해주세요!!");
		window.location.href="/spring/aopMember/loginForm.do";
	</script>
</c:if>
<c:if test="${sessionScope.memId != null}">
<body>
<br/>
<h2 align="center">회원 정보 삭제</h2>
<form action ="/spring/aopMember/deletePro.do" method="post">
<table>
	<input type="hidden" name="id" value="${vo.id }"/>
	<tr>
		<td>${vo.id}님 </td>
	</tr>
	<tr>
		<td><input type="password" name="pw" placeholder="비밀번호를 입력하세요."/></td>
	</tr>
	<tr>
		<td>
			<input type="submit" value="삭제">
			<input type="button" value="취소">
		</td>
	</tr>
</table>
</form>
</body>
</c:if>
</html>