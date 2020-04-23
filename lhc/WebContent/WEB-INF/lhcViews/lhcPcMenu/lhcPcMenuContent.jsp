<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>lhcPcMenuContent</title>
</head>
<script>	    
    function javascript(){
        self.close();   //자기자신창을 닫습니다.
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
<body style="background-color: aliceblue">
<div class = "container">
	<table class="type09">
		<thead>
			<tr scope="cols">
				<th>사진</th>
				<th>음식명</th>
				<th>가격</th>
			</tr>
		</thead>
		<tr>
			<td>
				<c:if test="${menu.lhc_img==null}">
					<img src="/lhc/lhcResources/lhcImg/default.jpg" width="100"/>
				</c:if>
				<c:if test="${menu.lhc_img!=null }">
					<img src="/lhc/lhcSave/${menu.lhc_img}" width="100"/>
				</c:if></td>
			<td>${menu.lhc_name}</td>
			<td>${menu.lhc_price}</td>
		</tr>
	</table>
	<table align="center">
		<tr >
			<td colspan="6"><button onclick = 'javascript()' value='닫기' style="color: black;" class="btn btn-default">닫기</button></td>
		</tr>
	</table>
</body>
</html>