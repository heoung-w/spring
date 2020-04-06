<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/lhc/lhcResources/style.css" rel="stylesheet" type="text/css">
</head>
<script>
</script>
<body>
<br/>
<h2 align="center">관리자 마이페이지</h2>
<table>
	<tr>
		<td>
			<button onclick="window.location.href='/lhc/lhcMember/'" >매출 내역</button>
			<button onclick="window.location.href='/lhc/lhcMember/lhcMemberList.lhc'" >일반 회원</button>
			<button onclick="window.location.href='/lhc/lhcMember/'" >업주 회원</button>
			<button onclick="window.location.href='/lhc/lhcMember/'" >신규 pc 등록</button>
			<button onclick="window.location.href='/lhc/lhcMember/lhcMain.lhc'" >메인</button>
		</td>
	</tr>
</table>
</body>
</html>