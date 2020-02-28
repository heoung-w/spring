<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${sessionScope ==null }">
	<script>
		window.location.href="/spring/aopMember/loginForm.do";
	</script>
</c:if>
<c:if test="${sessionScope != null }">
	<c:redirect url="/aopMember/main.do" />
</c:if>

</body>
</html>