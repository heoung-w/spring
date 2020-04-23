<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href='https://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'/>
	
	<style>
		@import url(//fonts.googleapis.com/earlyaccess/jejuhallasan.css);
		@import url(http://fonts.googleapis.com/earlyaccess/hanna.css);
			.empty{
				backgroud-color: white;
				width: 50px;
				height: 50px;
				margin: 10px;
				padding: 30px;
				float: left;
			}
			
			.red{
				background-color: red;
				width: 50px;
				height: 50px;
				margin: 10px;
				padding: 30px;
				float: left;
			}
		<c:forEach var="i" begin="1" end="10" step="1" >	
			.box${i}{
	            background-color: yellow;
	            width: 50px;
	            height: 50px;
	            margin: 10px;
	            padding: 30px;
	            float: left
	        }
		</c:forEach>
		
		<c:forEach var="j" begin="11" end="12" step="1" >
			.box${j}{
	            background-color: yellow;
	            width: 50px;
	            height: 50px;
	            margin: 10px;
	            padding: 30px;
	            clear: both;
	        }
		</c:forEach>	
		
		
		<c:forEach var="j" begin="14" end="23" step="1" >
			.box${j}{
	            background-color: yellow;
	            width: 50px;
	            height: 50px;
	            margin: 10px;
	            padding: 30px;
	            float: left;
	        }
		</c:forEach>
		
			
			.box14{
	            background-color: yellow;
	            width: 50px;
	            height: 50px;
	            margin: 10px;
	            padding: 30px;
	            float: left
	        }
		
		
		
		/*특별 13번*/
			.box13{
				background-color: yellow;
				width: 50px;
				height: 50px;
				margin: 10px;
				padding: 30px;
				clear:left;
			}
		
			
			/*작은 빨강색 박스*/
			.lred{
				background-color: red;
				width: 1px;
				height: 1px;
				margin: 5px;
				padding: 10px;
				position: relative;
				top: 25px;
				left: 20px
			}
			
			/*작은 연두색 박스*/
			.llime{
				background-color: lime;
				width: 1px;
				height: 1px;
				margin: 5px;
				padding: 10px;
				position: relative;
				top: -27px;
				left: 130px;
				
			}
			
			/*작은 empty 박스*/
			.lempty{
				background-color: white;
				width: 1px;
				height: 1px;
				margin: 0px;
				padding: 10px;
				clear: both;
			}
			
			/*작은 노랑색 박스*/
			.lyellow{
				background-color: yellow;
				width: 1px;
				height: 1px;
				margin: 5px;
				padding: 10px;
				position: relative;
				left: 240px;
				top: -81px
			}
			
			
			
			.text1{
				background-color: black;
				color: white;
				width: 11px;
				height: 1px;
				margin: 6px;
				padding: 10px;
				position: relative;
				left: 40px;
				top: -3px;
			}
			
			.text2{
				background-color: black;
				color: white;
				width: 11px;
				height: 1px;
				margin: 6px;
				padding: 10px;
				position: relative;
				left: 150px;
				top: -55px;
			}
			
			.text3{
				background-color: black;
				color: white;
				width: 11px;
				height: 1px;
				margin: 6px;
				padding: 10px;
				position: relative;
				left: 260px;
				top: -108px;
			
			}
			
			.whole{
				background-color: black;
				width: 100%;
				height: 1500px;
				margin: 0 auto;
				padding: 0px;
				float: left;
			
			}
			
			/*작은 counter그리과 키오스크 박스*/
			.counter{
				background-color: skyblue;
				width: 100px;
				height: 50px;
				margin: 10px;
				padding: 30px;
				float: left;
			}
			
			/*출입문*/
			.door{
				background-color: gray;
				width: 50px;
				height: 300px;
				margin: 10px;
				padding: 30px;
				position: absolute;
				top: 430px;
				left: 1600px;
			}
			
			h1{
				font-size: 250%;
			}
			
			h3{
				font-size: 150%;
			}
			
	</style>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script>
			$(document).ready(function(){
				
				var a = ${intReservedList};
				
				if(a != null){
					var a = ${intReservedList};
				}else{
					var a = 0;
				}
				
				
				for(var i = 0; i < a.length; i++){
					var name = a[i];
				
					console.log(name[i]);
					$(".check"+name).parent().attr("class", "red");
					$(".check"+name).attr("disabled", true);
				}
				
				//$("#bgBlock").attr("class", "red");
				for(var i = 1; i <= 23; i++){
					$(".check"+i).click(function(){
						if($(this).is(":checked")){
							$(".box"+$(this).val()).css("background-color", "lime")
						}else{
							$(".box"+$(this).val()).css("background-color", "yellow")
						}
					});
				}
				
			});
	
			
			// 예약하기 유효성 검사
			function checkIt(){
				var inputs = document.inputForm;
				var checkedItems = 0;
				for(i = 0; i < inputs.elements.length; i++){
					if((inputs.elements[i].checked) && (inputs.elements[i].name == "lhc_reserved")){
						checkedItems++;
					}
				}
				
				if(checkedItems == 0){
					alert("좌석을 선택해주세요!");
					return false;
				}
				return true;
			}
			
				
					
			
	</script>
	
	
</head>

<c:if test="${sessionScope.memId != null}"> 
<body>

	<section class="whole">
		<form action="/lhc/lhcMyChar/lhcMyCharSeatsPro.lhc" method="post" name="inputForm" onsubmit="return checkIt()">
			<h1 align="center" style="color: white; font-family: 'Hanna', serif;">${pc.lhc_name} 의 PC방 좌석도 </h1>
			<h3 style="color: white; font-family: 'Jeju Hallasan', serif;">${sessionScope.memId}님 좌석번호를 선택후 예약하세오 <input type="submit" value="예약하기" /> </h3> 
				 <!-- 네모로 색깔 보여주기 -->
				 <div class="lred"></div><b class="text1">사용중</b>	
				 <div class="llime"></div><b class="text2">선택됨</b>
				 <div class="lyellow"></div><b class="text3">사용가능</b> 
				 <br />
				 
				
				<%--추가로 보내야할 데이터들 --%>
				<input type="hidden" name="lhc_num" value="${pc.lhc_num}" />
				
				<div class="empty"></div>
			
				
			
		 <%--1번 ~ 10번 세팅 --%>
			<c:forEach var="i" begin="1" end="10" step="1" >			
				<div id="bgBlock" class="box${i}"><input class="check${i}" type="checkbox" name="lhc_reserved" value="${i}" /><h2>${i}</h2></div>		
			</c:forEach>
			
			<%--카운터 --%>
				<div class="counter"><h2 align="center">카운터</h2></div>
				
			<div class="empty"></div>
			
			
			<%--11번 ~ 12번 세팅 --%>	
			<c:forEach var="j" begin="11" end="12" step="1" >			
				<div id="bgBlock" class="box${j}"><input class="check${j}" type="checkbox" name="lhc_reserved" value="${j}" /><h2>${j}</h2></div>		
			</c:forEach>
			
			<div class="door"><h1 align="center" style="writing-mode: vertical-rl; margin-left: 1px;margin-top: 70px;">출입문</h1></div>
			
			<%--13번 --%>	
				<div id="bgBlock" class="box13"><input class="check13" type="checkbox" name="lhc_reserved" value="13" /><h2>13</h2></div>
				
				
				<div class="empty"></div>
			
			<%--14번 ~ 23번 세팅 --%>	
			<c:forEach var="k" begin="14" end="23" step="1" >			
				<div id="bgBlock" class="box${k}"><input class="check${k}" type="checkbox" name="lhc_reserved" value="${k}" /><h2>${k}</h2></div>		
			</c:forEach>
			
			
			<%--키오스크 --%>
				<div class="counter"><h2 align="center">키오스크</h2></div>
			
		</form>
	</section>
	
</body>
</c:if>

<c:if test="${sessionScope.memId == null && pageNumber == 0 && reservedList == null && intReservedList == null && lhc_num == 0}">
	<h2>잘못된 경로입니다.</h2>
	<input type="button" value="메인으로 가기" onclick="window.location='/lhc/lhcMember/lhcMain.lhc'" />
</c:if>
<footer id="test"></footer>	
</html>