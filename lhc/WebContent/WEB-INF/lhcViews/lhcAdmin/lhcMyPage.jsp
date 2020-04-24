<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	    .wrap {position: absolute;left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;line-height: 1.5;}
	    .wrap * {padding: 0;margin: 0;}
	    .wrap .info {width: 286px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;}
	    .wrap .info:nth-child(1) {border: 0;box-shadow: 0px 1px 2px #888;}
	    .info .title {padding: 5px 0 0 10px;height: 30px;background: #eee;border-bottom:1px solid #ddd;font-size: 18px;font-weight: bold;}
	    .info .close {position: absolute;top: 10px;right: 10px;color: #888;width: 17px;height: 17px;background: url('http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');}
	    .info .close:hover {cursor: pointer;}
	    .info .body {position: relative;overflow: hidden;}
	    .info .desc {position: relative;margin: 13px 0 0 90px;height: 75px;}
	    .desc .ellipsis {overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
	    .desc .jibun {font-size: 11px;color: #888;margin-top: -2px;}
	    .info .img {position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;}
	    .info:after {content: '';position: absolute;margin-left: -12px;left: 50%;bottom: 0;width: 22px;height: 12px;background: url('http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
	    .info .link {color: #5085BB;}
</style>
<style>	   
	body {
	  padding-top: 50px;
	  background-image: url('/lhc/lhcResources/lhcImg/4.jpg');
	  background-repeat : no-repeat;
	  background-size : cover;
	}
</style>
</head>
<script src= "https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

<script>
</script>
<body>
<div class = "container">
<jsp:include page="/WEB-INF/lhcViews/lhcHeader.jsp"></jsp:include>
<br/>
<h2 align="center" style="color:white;font-size: x-large;">관리자 마이페이지</h2>
<div>
	<ul align="center">
		<li>
			<button onclick="window.location.href='/lhc/lhcMyChar/lhcAllChar.lhc'" class="btn btn-default">매출 내역</button>
		</li>
		<li>
			<button onclick="window.location.href='/lhc/lhcPc/lhcStatePcList.lhc'" class="btn btn-default">등록 리스트</button>
		</li>
		<li>s
			<button onclick="window.location.href='/lhc/lhcMember/lhcMemberList.lhc?sep=c'" class="btn btn-default">일반 회원</button>
		</li>
		<li>
			<button onclick="window.location.href='/lhc/lhcMember/lhcMemberList.lhc?sep=v'" class="btn btn-default">업주 회원</button>
		</li>
		<li>
			<button onclick="window.location.href='/lhc/lhcMember/lhcMain.lhc'" class="btn btn-default">메인</button>
		</li>
	</ul>
</div>
</div>
</body>
</html>