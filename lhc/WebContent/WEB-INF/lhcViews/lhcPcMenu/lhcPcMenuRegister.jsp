<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>lhcPcRegisterForm</title>
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
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  		crossorigin="anonymous">
	</script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">	
<style>
	body {
	  padding-top: 50px;
	}
	.starter-template {
	  padding: 40px 15px;
	  text-align: center;
	}
table.type09 {
    border-collapse: collapse;
    text-align: left;
    line-height: 1.5;

}
table.type09 thead th {
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #369;
    border-bottom: 3px solid #036;
}
table.type09 tbody th {
    width: 150px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #f3f6f7;
}
table.type09 td {
    width: 350px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}
</style>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<div class = "container">
<jsp:include page="/WEB-INF/lhcViews/lhcHeader.jsp"></jsp:include>
</div>

<c:if test="${sessionScope.memId==null}">
	<script>
		alert("로그인 후 이용해 주세요");
		window.location='/lhc/lhcMember/lhcMain.lhc';
	</script>
</c:if>

<c:if test="${sessionScope.memId!=null}">
<body style="background-color: aliceblue">
<div class="container">
	<br/>
	<h1 align="center">음식 메뉴 등록</h1>
	<form action="/lhc/lhcPcMenu/lhcPcMenuRegisterPro.lhc" method="post" name="menuRegister" onsubmit="return PCmenucheck()" enctype="multipart/form-data">
		<%--숨겨서 글 속성에 관련된 데이터 pro에 전송 --%>
		<input type="hidden" name="lhc_menum" value="${lhc_menum}"/>
		<input type="hidden" name="lhc_num" value="${vo.lhc_num}"/>
		<table class="type09" align="center">
			<thead>
				<tr scope="cols">
					<th>음식 사진</th>
					<td align="left"><input type="file" name="lhc_img"/></td>
				</tr>
				<tr >
					<th>음식명</th>
					<td align="left"><input type="text" name="lhc_name"/></td>
				</tr>
				<tr>
					<th>음식 가격</th>
					<td align="left"><input type="text" name="lhc_price"/></td>
				</tr>			
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="메뉴 등록"/>
						<input type="reset" value="재작성"/>
						<input type="button" value="PC방 정보" onclick="window.location='/lhc/lhcPc/lhcPcListAll.lhc'"/>
					</td>
				</tr>
		</table>
	</form>
</div>					
</body>
</c:if>
</html>