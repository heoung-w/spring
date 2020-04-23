<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>예약완료</title>
	<link href="/lhc/lhcResources/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table>
		<tr>
			<td colspan="3">예약확인내역</td>
		</tr>
		<tr>
			<td>ID</td>
			<td>PC방 명</td>
			<td>좌석번호</td>
		</tr>
	
		
		<tr>
			<td>${sessionScope.memId}</td>
			<td>${lhc_name}</td>
			<td>${checkboxes}</td>
		</tr>
	</table>
	
	<h5 align="center"><button onclick="window.location='/lhc/lhcMember/lhcMain.lhc'">메인으로 가기</button></h5>
</body>
</html>