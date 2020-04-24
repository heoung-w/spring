<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
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
<c:if test="${sessionScope.memId==null}">
<nav class="navbar navbar-inverse" style="margin-top: -30px;">
	<div class="container" style="height: 120px; background-color:white; width:1140px; background-image: url('/lhc/lhcResources/lhcImg/4.jpg');">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/lhc/lhcMember/lhcMain.lhc"><img src="/lhc/lhcResources/lhcImg/logo.JPG" style="width: 100px; height: 80px;"></a>
        </div>
      	<div id="navbar" class="navbar-collapse collapse" style="margin-top: 15px;">      	
          <form class="navbar-form navbar-right" style="padding-right:20px" action="/lhc/lhcMember/lhcLoginPro.lhc">
            <div class="form-group">
              <input type="text" placeholder="id" class="form-control" name="lhc_id" style="width:130px; height:29px;">
            </div>
            <button type="submit" class="btn btn-default" style="width:68px; height:29px ">로그인</button><br/>
            <div class="form-group" style="margin-top:10px;">
              <input type="password" placeholder="Password" name="lhc_pw"  class="form-control" style="width:130px; height:29px">
            </div>
            <button type="submit" class="btn btn-default" style="width:68px; height:29px; margin-top:10px;" >회원가입</button><br/>
          </form>
        
        <div class="bbb" style="text-align: center; align-self:center; margin-left:200px; margin-top:18px; ">
	<form class="form-inline" action = "/lhc/lhcPc/lhcPcListAll.lhc" method ="post">
		<div class="form-group">
			<div class="input-group">
				<div class="input-group-addon">
					<select name = "sel" style=" height: 30px;" class="form-control">
						<option value ="lhc_name">PC방 명</option><%--value => DB에 있는 이름으로 써야 함.--%>
						<option value ="lhc_addr">주소</option>					
					</select>
					<input type="text" name ="search" style=" margin-top:0px; width: 200px;  height: 30px;" class="form-control" >
				</div>
				<div class="input-group-addon">
					<input type="submit" value ="검색" style="height: 30px;"class="btn btn-default">
				</div>
			</div>
		</div>
	</form>
	</div>
	</div>
</nav>  
    
    <nav class="navbar navbar-default" style="margin-top: -20px;">      
        <div class="navbar-header">
          <a class="navbar-brand" href="#">카테고리</a>
        </div>
        
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="/lhc/lhcPc/lhcPcListAll.lhc">PC방 전체보기</a></li>
            <li><a href="/lhc/lhcPc/lhcPcListCat.lhc">카테고리 보기</a></li>
            <li><a href="/lhc/lhcBoard/lhcList.lhc">공지사항</a></li>
          </ul>
         </div>
    </nav>
	<!-- 헤더 -->	
</c:if>
<c:if test="${sessionScope.memId!=null}">
	<nav class="navbar navbar-inverse" style="margin-top: -30px;">
      <div class="container" style="height: 120px; background-color:white; width:1140px; background-image: url('/lhc/lhcResources/lhcImg/4.jpg');">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/lhc/lhcMember/lhcMain.lhc"><img src="/lhc/lhcResources/lhcImg/logo.JPG" style="width: 100px; height: 80px;"></a>
        </div>
        
        <div id="navbar" class="navbar-form navbar-right" style="margin-bottom: 15px;">
       		<td "WebContent/WEB-INF"d style="padding-right: 532px; border: hidden;">
			</td>
			<td style=" position:absolute; margin-right:50px;">
				<h5 style="margin-top:30px; color: white;">${sessionScope.memId}님 환영합니다.<br /></h5>
				<button onclick="window.location.href='/lhc/lhcMember/lhcLogout.lhc'" style="width:100px; height:30px; margin-top: 0px;" class="btn btn-default">로그아웃</button>
				<button onclick="window.location.href='/lhc/lhcMember/lhcMyPage.lhc'" style="width:100px; height:30px; margin-right: 25px; margin-top: 0px;" class="btn btn-default" >마이페이지</button>
			</td>
      </div>
      
    <div class="bbb" style="text-align: center; align-self:center; margin-left:200px; margin-top:33px; ">
	<form class="form-inline" action = "/lhc/lhcPc/lhcPcListAll.lhc" method ="post">
		<div class="form-group">
			<div class="input-group">
				<div class="input-group-addon">
					<select name = "sel" style=" height: 30px;" class="form-control">
						<option value ="lhc_name">PC방 명</option><%--value => DB에 있는 이름으로 써야 함.--%>
						<option value ="lhc_addr">주소</option>					
					</select>
					<input type="text" name ="search" style=" margin-top:0px; width: 200px;  height: 30px;" class="form-control" >
				</div>
				<div class="input-group-addon">
					<input type="submit" value ="검색" style="height: 30px;"class="btn btn-default">
				</div>
			</div>
		</div>
	</form>
	</div>
    </nav>
      
    <nav class="navbar navbar-default" style="margin-top: -20px;">      
        <div class="navbar-header">
          <a class="navbar-brand" href="#">카테고리</a>
        </div>
        
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="/lhc/lhcPc/lhcPcListAll.lhc">PC방 전체보기</a></li>
            <li><a href="/lhc/lhcPc/lhcPcListCat.lhc">카테고리 보기</a></li>
            <li><a href="/lhc/lhcBoard/lhcList.lhc">공지사항</a></li>
          </ul>
         </div>
    </nav>
</c:if>
