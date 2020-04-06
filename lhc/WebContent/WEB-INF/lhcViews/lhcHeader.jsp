<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<script type="text/javascript" src="/erp/resources/script.js"></script>
	<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
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

</style>

    <nav class="navbar navbar-inverse" style="margin-top: -30px;">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </nav>
      
    <nav class="navbar navbar-default" style="margin-top: -20px;">      
        <div class="navbar-header">
          <a class="navbar-brand" href="#">카테고리</a>
        </div>
        
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
          </ul>
         </div>
    </nav>
	<!-- 헤더 -->
<c:if test="${sessionScope.memId==null}">
		<table style="border: hidden; width: 100%; align-self: center; height:30%; display:table-header-group;">
			<tr>
				<td style="padding-right: 532px; border: hidden;">
				</td>
				<td style="border: hidden; padding-right: 450px;">
					<form action="/lhc/lhcMember/.lhc" > <!-- 페이지명 정하기 -->
						<select name="sel" style="color: black; background-color: white">
							<option value="lhc_addr" >주소</option>
							<option value="lhc_name" style="color: black;">PC방 명</option>
						</select>
						<input type="text" name="search" />
						<input type="submit" value="검색" />
					</form>
						<button onclick="window.location.href='/lhc/lhcMember/lhcMain.lhc'">메인으로</button>	
				</td>
				<td style="border: hidden; padding-right: 100px;">
					<form action="/lhc/lhcMember/lhcLoginPro.lhc" method="post">
						<table>
							<tr style="border: hidden;">
								<td style="border: hidden;">아이디</td>
								<td><input type="text" name="lhc_id"/></td>
								<td rowspan="2" style="border: hidden;">
									<input type="checkbox" name="auto" value="1" /> 자동로그인 
								</td>
							</tr>
							<tr style="border: hidden;">
								<td style="border: hidden;">비밀번호</td>
								<td><input type="password" name="lhc_pw" /></td>
							</tr>
							<tr style="border: hidden;">
								<td colspan="3" align="right" style="border: hidden;">
									<input type="submit" value="로그인" />
									<input type="button" value="회원가입" onclick="window.location.href='/lhc/lhcMember/lhcSignupForm.lhc'" />
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
		<table style="border: hidden; width: 100%; height:30%;">
			<tr>
				<td style="border: hidden;">
					<button onclick="window.location.href='/lhc/lhcMember/.lhc'">pc방 전체 보기</button> <!-- 페이지명 정하기 -->
				</td>
				<td style="border: hidden;">
					<button onclick="window.location.href='/lhc/lhcMember/.lhc'">카테고리 보기</button> <!-- 페이지명 정하기 -->
				</td>
				<td style="border: hidden;">
					<button onclick="window.location.href='/lhc/lhcMember/.lhc'">공지사항</button> <!-- 페이지명 정하기 -->					
				</td>
			</tr>
		</table>
</c:if>

<c:if test="${sessionScope.memId!=null}">
	<table style="border: hidden; width: 100%; align-self: center; height:30%; display:table-header-group;">
			<tr>
				<td style="padding-right: 532px; border: hidden;">
				</td>
				<td style="border: hidden; padding-right: 600px;">
					<form action="/lhc/lhcMember/.lhc" > <!-- 페이지명 정하기 -->
						<select name="sel" style="color: black; background-color: white">
							<option value="lhc_addr" >주소</option>
							<option value="lhc_name" style="color: black;">PC방 명</option>
						</select>
						<input type="text" name="search" />
						<input type="submit" value="검색" />
					</form>
						<button onclick="window.location.href='/lhc/lhcMember/lhcMain.lhc'">메인으로</button>	
				</td>
				<td style="border: hidden; padding-right: 100px;">
					${sessionScope.memId}님 환영합니다.<br />
					<button onclick="window.location.href='/lhc/lhcMember/lhcLogout.lhc'" >로그아웃</button>
					<button onclick="window.location.href='/lhc/lhcMember/lhcMyPage.lhc'" >마이페이지</button>
				</td>
			</tr>
		</table>
		<table style="border: hidden; width: 100%; height:30%;">
			<tr>
				<td style="border: hidden;">
					<button onclick="window.location.href='/lhc/lhcMember/.lhc'">pc방 전체 보기</button> <!-- 페이지명 정하기 -->
				</td>
				<td style="border: hidden;">
					<button onclick="window.location.href='/lhc/lhcMember/.lhc'">카테고리 보기</button> <!-- 페이지명 정하기 -->
				</td>
				<td style="border: hidden;">
					<button onclick="window.location.href='/lhc/lhcMember/.lhc'">공지사항</button> <!-- 페이지명 정하기 -->					
				</td>
			</tr>
		</table>
</c:if>
