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
		function PCspeccheck(){
			var specs = document.specRegister;
			
			if(!specs.lhc_cpu.value){
				alert("CPU를 등록하세요.");
				return false;
			}
			if(!specs.lhc_graphic.value){
				alert("GRAPHIC을 입력하세요.");
				specs.lhc_graphic.focus(); 
				return false;
			}
			if(!specs.lhc_memory.value){
				alert("MEMORY를 입력하세요.");
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
	<h1 align="center">사양 등록</h1>
	<form action="/lhc/lhcPcSpec/lhcPcSpecRegisterPro.lhc" method="post" name="specRegister" onsubmit="return PCspeccheck()">
		<%--숨겨서 글 속성에 관련된 데이터 pro에 전송 --%>
		<%-- <input type="hidden" name="lhc_num" value="${lhc_num}"/> --%>
		<table>
			<tr>
				<td>PC방 명</td>
				<td align="left"><input type="text" name="lhc_name"/></td>
			</tr>
			<tr>
				<td>CPU</td>
				<td align="left"><input type="text" name="lhc_cpu"/></td>
			</tr>
			<tr>
				<td>GRAPHIC</td>
				<td align="left"><input type="text" name="lhc_graphic"/></td>
			</tr>
			<tr>
				<td>MEMORY</td>
				<td align="left"><input type="text" name="lhc_memory"/></td>
			</tr>			
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="사양 등록"/>
					<input type="reset" value="재작성"/>
					<input type="button" value="PC방 정보" onclick="window.location='/lhc/lhcPc/lhcPcContent.lhc'"/>
				</td>
			</tr>
			
		</table>
	</form>					
</body>
</c:if>
</html>