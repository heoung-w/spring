<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
</script>
<body>
<br/>
<h2 align="center">관리자 마이페이지</h2>
<table>
	<tr>
		<td>
			<button onclick="window.location.href='/lhc/lhcMyChar/lhcAllChar.lhc'" >매출 내역</button>
			<button onclick="window.location.href='/lhc/lhcPc/lhcStatePcList.lhc'" >등록 리스트</button>
			<button onclick="window.location.href='/lhc/lhcMember/lhcMemberList.lhc?sep=c'" >일반 회원</button>
			<button onclick="window.location.href='/lhc/lhcMember/lhcMemberList.lhc?sep=v'" >업주 회원</button>
			<button onclick="window.location.href='/lhc/lhcMember/lhcMain.lhc'" >메인</button>
		</td>
	</tr>
</table>
</body>
</html>