<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/spring/resource/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<br/>
<h2 align="center">게시글 수정</h2>
<br/>
<form action="/spring/board/modifyPro.do" method="post">
<input type="hidden" name="num" value="${vo.num }"/>
<input type="hidden" name="pageNum" value="${pageNum }"/>
<input type="hidden" name="writer" value="${vo.writer}"/>
	<table>
		<tr>
			<td>작성자 * </td>
			<td>${vo.writer}</td>
		</tr>
		<tr>
			<td>제목 * </td>
			<td><input type="text" name="subject" placeholder="제목 입력."/></td>
		</tr>
		<tr>
			<td>이메일 * </td>
			<td><input type="text" name="email" placeholder="이메일 입력."/></td>
		</tr>
		<tr>
			<td>내용 * </td>
			<td><input type="text" name="content" placeholder="내용 입력."/></td>
			<!-- <textarea rows="20" cols="70" name="content" placeholder="내용 입력."></textarea>-->
		</tr>
		<tr>
			<td>패스워드 * </td>
			<td><input type="password" name="pw" placeholder="패스워스 입력."/></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="수정"/>
				<input type="button" value="취소" onclick="history.go(-1)"/>
			</td>
		</tr>
	</table>
</form>

</body>
</html>