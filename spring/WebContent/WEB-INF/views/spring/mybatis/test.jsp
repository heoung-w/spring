<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>count : ${count }</h2>
	<h2>maxAge : ${maxAge }</h2>
	<c:forEach var="user" varStatus="status" items="${userList}">
		<h2>${status.count } : ${user.id} : ${user.pw } : ${user.age} :${user.reg }</h2>
	</c:forEach>
	<h2>${dto.id} / ${dto.pw} / ${dto.reg}</h2>
	<h2> reg : ${reg}</h2>
</body>
</html>