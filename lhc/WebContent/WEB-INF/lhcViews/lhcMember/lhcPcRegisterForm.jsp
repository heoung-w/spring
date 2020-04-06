<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>lhcPcRegisterForm</title>
<link href="/lhc/lhcResources/style.css" rel="stylesheet" type="text/css" >
	<script>
		// 유효성 검사
		function PCcheck(){
			var registers = document.RegisterForm;
			
			if(!registers.lhc_name.value){
				alert("PC방 명을 입력하세요.");
				inputs.lhc_name.focus(); 
				return false;
			}
			if(!registers.lhc_img.value){
				alert("사진을 등록하세요.");
				return false;
			}
			if(!registers.lhc_addr.value){
				alert("주소를 입력하세요.");
				return false;
			}
			if(!registers.lhc_info.value){
				alert("정보를 입력하세요.");
				return false;
			}
			if(!registers.lhc_vendor.value){
				alert("정보를 입력하세요.");
				return false;
			}
			if(!registers.lhc_seats.value){
				alert("좌석 수를 입력하세요.");
				return false;
			}
		}	
	</script>
</head>
<c:if test="${sessionScope.memId==null}">
	<script>
		alert("로그인 후 이용해 주세요");
		window.location='/lhc/lhcMember/lhcMain.lhc';
	</script>
</c:if>

<c:if test="${sessionScope.memId!=null}">
<body>
	<br/>
	<h1 align="center">PC방 등록</h1>
	<form action="/lhc/lhcPc/lhcPcRegisterPro.lhc" method="post" name="RegisterForm" onsubmit="return PCcheck()" enctype="multipart/form-data">
		<%--숨겨서 글 속성에 관련된 데이터 pro에 전송 --%>
		<input type="hidden" name="lhc_num" value="${lhc_num}"/>
		
		<table>
			<tr>
				<td>PC방 명</td>
				<td align="left"><input type="text" name="lhc_name"/></td>
			</tr>
			<tr>
				<td>사진</td>
				<td align="left"><input type="file" name="lhc_img" multiple="multiple"/></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td align="left"><input type="text" name="lhc_phone"/></td>
			</tr>
			<tr>
				<td>주소</td>
				<td align="left"><input type="text" name="lhc_addr"/></td>
			</tr>
			
			<tr>
				<td>정보</td>
				<td><textarea rows="20" cols="70" name="lhc_info"></textarea></td>
			</tr>
			<tr>
				<td>업주 아이디</td>
				<td align="left"><input type="text" name="lhc_vendor" id="lhc_vendor"/></td>
			</tr>
			<tr>
				<td>좌석 수</td>
				<td align="left"><input type="text" name="lhc_seats"/></td>
			</tr>			
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="PC방 등록"/>
					<input type="reset" value="재작성"/>
					<input type="button" value="PC방 리스트" onclick="window.location='/lhc/lhcMember/lhcPcListAll.lhc'"/>
				</td>
			</tr>
			
		</table>
	</form>					
</body>
</c:if>
</html>