<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공지게시글 수정</title>
	<link href="/lhc/lhcResources/style.css" rel="stylesheet" type="text/css" />
	<script>
	
	</script>
</head>
<body>
	<br />
	<h1 align="center">공지 게시글 수정</h1>
	<form action="/lhc/lhcBoard/lhcModifyPro.lhc?pageNum=${pageNum}" method="post">
		<%--숨겨서 글 속성에 관련된 데이터 전송하기 --%>
		<input type="hidden" name="lhc_num" value="${article.lhc_num}" />
		<table>
			<tr>
				<td>작성자</td>
				<td align="left">
					${article.lhc_writer}
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td align="left"><input type="text" name="lhc_subject" value="${article.lhc_subject}" /></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="20" cols="70" name="lhc_content">${article.lhc_content}</textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="저장" />
					<input type="reset" value="재작성" />
					<input type="button" value="공지사항게시판가기" onclick="window.location='/lhc/lhcBoard/lhcList.lhc?pageNum=${pageNum}'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>