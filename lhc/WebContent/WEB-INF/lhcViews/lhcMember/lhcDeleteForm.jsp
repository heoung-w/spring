<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>delete Form</title>
	<link href="/lhc/lhcResources/style.css" rel="stylesheet" type="text/css">
<script>	
		function validate(){
			
			var deleteForm=document.deleteForm;
			
			if(deleteForm.pw.value!=deleteForm.pwCh.value){
				alert("pw가 일치하지 않습니다.");
				return false;
			}
		}
	</script>	
</head>
<jsp:include page="/WEB-INF/lhcViews/lhcHeader.jsp"></jsp:include>
	<body>
		<br />
		<h2 align="center"> 회원 탈퇴 </h2>
		<form action="/lhc/lhcMember/lhcDeletePro.lhc" method="post" name="deleteForm" onsubmit="return validate();">
			<input type="hidden" name="lhc_id" value="${memId}"/>
			<table> 
				<tr>
					<td colspan="2">
						<input type="password" name="lhc_pw"/>
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="회원 탈퇴" /> </td>
					<td><input type="button" value="취소" onclick="window.location.href='/lhc/lhcMember/lhcMain.lhc'" /> </td>
				</tr>
			
			</table>
		</form>
	
	</body>
</html>