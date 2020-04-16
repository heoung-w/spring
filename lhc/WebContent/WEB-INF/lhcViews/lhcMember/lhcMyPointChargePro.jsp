<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>lhcMyPointChargePro</title>
</head>
<script>  
    function closeWindow() {  
            setTimeout(function() {  
        window.close();  
            }, 1000);  
        }  
    // 창이 열린 후 1초후에 닫기  
    window.onload = closeWindow();  
</script>  

<body>
<c:if test="${myPoint==null}">
	<script>
		alert("충전 금액 선택하세요");
	</script>
</c:if>
<c:if test="${result==1}">
	<script>
		alert("충전 성공");
	</script>
</c:if>

<c:if test="${result!=1}">
	<script>
		alert("충전 실패");
	</script>
</c:if>
<!-- onclick 이벤트에 직접 입력 -->  
<a href="#" onClick="self.close();"></a>   
<a href="#" onClick="javascript:closeWindow();"></a>   
</body>
</html>