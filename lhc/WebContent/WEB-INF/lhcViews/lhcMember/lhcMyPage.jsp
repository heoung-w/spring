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
<script></script>
<c:if test="${sessionScope.memId ==null}">
	<script>
		alert("로그인 후 이용해주세요")!!
		window.location.href="/lhc/lhcMember/lhcMain.lhc";
	</script>
</c:if>
<c:if test="${sessionScope.memId != null }">
<body>
<br/>
<h2 align="center">회원 마이페이지</h2>
<table>
	<tr>
		<td colspan="2">${sessionScope.memId} 님</td>
	</tr>
	<tr>
		<td>
			<button onclick="window.location.href='/lhc/lhcMember/lhcMyFavoriteListForm.lhc'" >pc방 찜목록</button>
			<button onclick="window.location.href='/lhc/lhcMember/lhcMyCharForm.lhc'" >예약 내역</button>
			<button onclick="window.location.href='/lhc/lhcMember/lhcMyPointForm.lhc'" >포인트 충전</button>
			<button onclick="window.location.href='/lhc/lhcMember/lhcModifyForm.lhc'" >회원정보수정</button>
			<button onclick="window.location.href='/lhc/lhcMember/lhcDeleteForm.lhc'" >회원정보삭제</button>
			<button onclick="window.location.href='/lhc/lhcMember/lhcMain.lhc'" >메인</button>
		</td>
	</tr>
</table>
</body>                  
</c:if>


</html>