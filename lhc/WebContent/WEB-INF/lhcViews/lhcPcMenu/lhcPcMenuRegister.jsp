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
		function PCmenucheck(){
			var menus = document.menuRegister;
			
			if(!menus.lhc_img.value){
				alert("사진을 등록하세요.");
				return false;
			}
			if(!menus.lhc_name.value){
				alert("메뉴 명을 입력하세요.");
				menus.lhc_name.focus(); 
				return false;
			}
			if(!menus.lhc_price.value){
				alert("가격을 입력하세요.");
				return false;
			}
		}	
	</script>
</head>
<jsp:include page="/WEB-INF/lhcViews/lhcHeader.jsp"></jsp:include>

<c:if test="${sessionScope.memId==null}">
	<script>
		alert("로그인 후 이용해 주세요");
		window.location='/lhc/lhcMember/lhcMain.lhc';
	</script>
</c:if>

<c:if test="${sessionScope.memId!=null}">
<body>
	<br/>
	<h1 align="center">음식 메뉴 등록</h1>
	<form action="/lhc/lhcPcMenu/lhcPcMenuRegisterPro.lhc" method="post" name="menuRegister" onsubmit="return PCmenucheck()" enctype="multipart/form-data">
		<%--숨겨서 글 속성에 관련된 데이터 pro에 전송 --%>
		<input type="hidden" name="lhc_menum" value="${lhc_menum}"/>
		<table>
			<tr>
				<td>pcNum</td>
				<td align="left"><input type="text" name="lhc_num"/></td>
			</tr>
			<tr>
				<td>음식 사진</td>
				<td align="left"><input type="file" name="lhc_img"/></td>
			</tr>
			<tr>
				<td>음식명</td>
				<td align="left"><input type="text" name="lhc_name"/></td>
			</tr>
			<tr>
				<td>음식 가격</td>
				<td align="left"><input type="text" name="lhc_price"/></td>
			</tr>			
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="메뉴 등록"/>
					<input type="reset" value="재작성"/>
					<input type="button" value="PC방 정보" onclick="window.location='/lhc/lhcPc/lhcPcListAll.lhc'"/>
				</td>
			</tr>
			
		</table>
	</form>					
</body>
</c:if>
</html>