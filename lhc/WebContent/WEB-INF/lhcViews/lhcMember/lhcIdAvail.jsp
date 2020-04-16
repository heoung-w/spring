<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="/lhc/lhcResources/style.css" rel="stylesheet" type="text/css">
</head>
<body>		
	<c:if test="${idcheck==1}">
			<table>
				<tr>
					<td>${vo.id} 이미 사용중인 아이디입니다.</td>
				</tr>
			</table>
			<form action="/lhc/lhcMember/lhcIdAvail.lhc" method="post" name="checkForm">
				<table>
					<tr>
						<td>다른 아이디를 선택하세요. <br />
							<input type="text" name="id"  />
							<input type="submit" value="ID중복확인"  />
						</td>
					</tr>
				</table>
			</form>
	</c:if>
	<c:if test="${idcheck!=1}">
			<table>
				<tr>
					<td>입력하신 ${vo.id}는 사용하실수 있는 아이디입니다. <br />
						<input type="button" value="닫기" onclick="setId()" />
					</td>
				</tr>
			</table>
	</c:if>
</body>
	<script>	
		function setId() {
			opener.document.inputForm.id.value = "${vo.id}"; 
			self.close();	// 창닫기
		}
	</script>
</html>