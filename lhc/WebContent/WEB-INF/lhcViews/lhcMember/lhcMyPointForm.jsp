<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>lhcMyPointForm</title>
</head>
<jsp:include page="/WEB-INF/lhcViews/lhcHeader.jsp"></jsp:include>
<script>
    function pointup() {
		window.open("/lhc/lhcMember/lhcMyPointChargeForm.lhc","","width=500,height=500,left=900,top=200");
	}	    
</script>
<body>
	<h1><p align="center">포인트 조회 Page//예약 내역 리스트</p></h1>	
	<h2>${member.lhc_id}님</h2>
	<h2>보유 포인트 : ${member.lhc_money}원</h2>
	<a href="#" onclick="return pointup()"><button type="button" >충전</button></a>
	<button onclick = "history.go(-1)" >취소</button>				
</body>
</html>