<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/spring/resource/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<br/>
<h2 align="center">content</h2>
<br/>
<table>
	<tr>
		<td>작성자</td>
		<td colspan="3">${vo.writer}</td>
	</tr>
	<tr>
		<td>제목</td>
		<td colspan="3">${vo.subject}</td>
	</tr>
	<tr>
		<td>이메일</td>
		<td colspan="3">${vo.email}</td>
	</tr>
	<tr>
		<td>내용</td>
		<td colspan="3">${vo.content}</td>
	</tr>
	<tr>
		<td>작성시간</td>
		<td>${vo.reg}</td>
		<td>조회수</td>
		<td>${vo.readcount}</td>
	</tr>
	<tr>
		<td colspan="4">
			<button onclick = "window.location.href='/spring/board/modifyForm.do?num=${vo.num}&pageNum=${pageNum }'">수정</button>
			<button onclick = "window.location.href='/spring/board/deleteForm.do?num=${vo.num}&pageNum=${pageNum }'">삭제</button>
			<button onclick = "window.location.href='/spring/board/list.do'">리스트</button>
		</td>
	</tr>

</table>

</body>
</html>