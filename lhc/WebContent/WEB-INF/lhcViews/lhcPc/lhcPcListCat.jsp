<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  		crossorigin="anonymous">
	</script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<style>
	body {
	  padding-top: 50px;
	  background-image: url('/lhc/lhcResources/lhcImg/4.jpg');
	  background-repeat : no-repeat;
	  background-size : cover;
	}
	.starter-template {
	  padding: 40px 15px;
	  text-align: center;
	}
</style>
<style>
ul{
   list-style:none;
   }
</style>
</head>
<div class = "container">
<jsp:include page="/WEB-INF/lhcViews/lhcHeader.jsp"/>
</div>
<body style="background-color: aliceblue">
<h2 align="center" style="font-size: large; color: white;">카테고리 보기</h2>
<div class="container" >
	<ul align="center">
		<li>
			<button onclick="window.location.href='lhcPcCatList.lhc'"  class="btn btn-default" >매출순</button>
		</li>
		<li>
			<button onclick="window.location.href='lhcPcFavList.lhc'"  class="btn btn-default">인기순</button>
		</li>
		<li>
			<button onclick="window.location.href='lhcPcCharList.lhc'"  class="btn btn-default">예약순</button>
		</li>
	</ul>
</div>
</body>
</html>