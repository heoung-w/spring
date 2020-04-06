<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업주 마이페이지</title>
<link href="/lhc/lhcResources/style.css" rel="stylesheet" type="text/css">
</head>
<script>
</script>
<body>
<br/>
<h2 align="center">업주 마이페이지</h2>
<table>
	<tr>
		<td>
			<button onclick="window.location.href='/lhc/lhcMember/'" >예약 내역</button>
			<button onclick="window.location.href='/lhc/lhcMember/'" >찜 회원 리스트</button>
			<button onclick="window.location.href='/lhc/lhcMember/'" >메뉴 등록</button>
			<button onclick="window.location.href='/lhc/lhcMember/'" >PC 정보</button>
			<button onclick="window.location.href='/lhc/lhcMember/lhcModifyForm.lhc'" >회원정보수정</button>
			<button onclick="window.location.href='/lhc/lhcMember/lhcMain.lhc'" >메인</button>
		</td>
	</tr>
</table>
</body>
</html>