<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="/lhc/lhcResources/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<c:if test="${lhc_money > netRev}">
		<c:redirect url="/lhcMyChar/lhcMyCharForm.lhc" />
	</c:if>
	
	<c:if test="${lhc_money < netRev}">
			<h3>돈이 부족합니다. 충전 후 사용 부탁드립니다.</h3>
			<button onclick="window.location='/lhc/lhcMember/lhcMyPointForm.lhc'">내 충전하기로 이동</button>
			<%--<c:redirect url="/lhcMember/lhcMyPointForm.lhc" /> --%>
	</c:if>
	
</body>
</html>