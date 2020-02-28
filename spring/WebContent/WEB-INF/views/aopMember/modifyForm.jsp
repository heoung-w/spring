<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/spring/resource/style.css" rel="stylesheet" type="text/css">

</head>
<body>
<br />
	<h1 align="center">회원 정보 수정 </h1>
	<form action="/spring/aopMember/modifyPro.do" method="post" name="inputForm" >
	<table>
	<input type="hidden" name="id" value="${vo.id }"/>
	<input type="hidden" name="name" value="${vo.name }"/>
		<tr>
			<td>아이디 * </td>
			<td>${vo.id }</td>
		</tr>
		<tr>
			<td>비밀번호 * </td>
			<td><input type="password" name="pw" value="${vo.pw }"/></td>
		</tr>
		<tr>
			<td>비밀번호 확인 * </td>
			<td><input type="password" name="pwCh" ></td>
		</tr>
		<tr>
			<td>이름 * </td>
			<td>${vo.name }</td>
		</tr>
		<tr>
			<td>나이 * </td>
			<td><input type="text" name="age" value="${vo.pw }"/></td>
		</tr>
		<tr>
			<td>Email </td>
			<td><input type="text" name="email" value="${vo.email}" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="수정" />
				<input type="button" value="취소" onclick="window.location.href='/spring/aopMember/main.do'" /> 
			</td>
		</tr>
	</table>
	</form>

</body>
</html>