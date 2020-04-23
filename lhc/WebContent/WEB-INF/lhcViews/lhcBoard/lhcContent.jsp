<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공지게시글보기</title>
	<link href="/lhc/lhcResources/style.css" rel="stylesheet" type="text/css">
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script>
		$(document).ready(function(){
			$("button").click(function(){
				$("#lhcDeleteForm").html("삭제를 정말로 원하시면 옆에 있는 버튼을 눌러주세요 <button onclick="+"window.location='/lhc/lhcBoard/lhcDeletePro.lhc?lhc_num=${article.lhc_num}&pageNum=${pageNum}'"+">삭제</button>")
			});
		});
	</script>
</head>
<body>
	<br />
	<h1 align="center">content</h1>
	<table>
		<tr>
			<td colspan="2"><b>${article.lhc_subject}</b></td>
		</tr>
		<tr>
			<td colspan="2"><textarea rows="20" cols="70" readonly>${article.lhc_content}</textarea></td>
		</tr>
		<tr>
			<td>작성자 {article.lhc_writer} at ${article.lhc_reg}</td>
			<td>${article.lhc_readcount} viewed</td>
		</tr>
		<tr>
			<td colspan="2">
				<button onclick="window.location='/lhc/lhcBoard/lhcModifyForm.lhc?lhc_num=${article.lhc_num}&pageNum=${pageNum}'">수 정</button>
				
				<!-- jQuery 및 ajax활용하기 -->
				<button>삭제</button>
				<div id="lhcDeleteForm"></div>
				
				<%--<button onclick="window.location='/lhc/lhcBoard/lhcDeleteForm.lhc?lhc_num=${article.lhc_num}&pageNum=${pageNum}'">삭 제</button> --%>
				<button onclick="window.location='/lhc/lhcBoard/lhcList.lhc?pageNum=${pageNum}'">게시판으로 이동</button>
			</td>
		</tr>
	</table>
</body>
</html>