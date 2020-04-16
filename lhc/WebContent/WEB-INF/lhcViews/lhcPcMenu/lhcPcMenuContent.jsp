<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>lhcPcMenuContent</title>
</head>

<body>
<c:if test="${menu.lhc_img==null}">
	<img src="/lhc/lhcResources/lhcImg/default.jpg" width="100"/>
</c:if>
<c:if test="${menu.lhc_img!=null }">
	<img src="/lhc/lhcSave/${menu.lhc_img}" width="100"/>
</c:if>

<h1>메뉴 명 : ${menu.lhc_name}</h1>
<h1>가격 : ${menu.lhc_price}</h1>

</body>
</html>