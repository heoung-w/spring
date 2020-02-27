<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>글 삭제</title>
	<link href="/spring/resource/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<br />
	<h1 align="center"> delete article </h1>
	<form action="/spring/board/deletePro.do?pageNum=${pageNum}" method="post">
		<%-- 글 고유 번호 숨겨서 넘기기 --%>
		<input type="hidden" name="num" value="${num}" />
		<table>
			<tr>
				<td>삭제를 원하시면 비밀번호를 입력하세요.</td>
			</tr>
			<tr>
				<td><input type="password" name="pw" /></td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="삭제" />
					<input type="button" value="취소" onclick="window.location='/spring/board/list.do?pageNum=${pageNum}'"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>