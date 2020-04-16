<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
			.empty{
				backgroud-color: white;
				width: 50px;
				height: 50px;
				margin: 10px;
				padding: 30px;
				float: left;
			}
		<c:forEach var="i" begin="0" end="10" step="1" >
			.box${i}{
	            background-color: yellow;
	            width: 50px;
	            height: 50px;
	            margin: 10px;
	            padding: 30px;
	            float: left
	        } 
		</c:forEach>
		
		<c:forEach var="i" begin="21" end="22" step="1" >
			.box${i}{
				background-color: yellow;
				width: 50px;
				height: 50px;
				margin: 10px;
				padding: 30px;
				clear:both
			}
		</c:forEach>
		
		<c:forEach var="i" begin="24" end="26" step="1" >
			.box${i}{
				background-color: yellow;
				width: 50px;
				height: 50px;
				margin: 10px;
				padding: 30px;
				clear:both
			}
		</c:forEach>
		
		<c:forEach var="i" begin="28" end="30" step="1" >
			.box${i}{
				background-color: yellow;
				width: 50px;
				height: 50px;
				margin: 10px;
				padding: 30px;
				clear:both
			}
		</c:forEach>
		
		/*특별 23번*/
			.box23{
				background-color: yellow;
				width: 50px;
				height: 50px;
				margin: 10px;
				padding: 30px;
				float:left
			}
		/*특별 27번*/
			.box27{
				background-color: yellow;
				width: 50px;
				height: 50px;
				margin: 10px;
				padding: 30px;
				float:left
			}
		
			
		<c:forEach var="i" begin="11" end="20" step="1" >
			.box${i}{
	            background-color: yellow;
	            width: 50px;
	            height: 50px;
	            margin: 10px;
	            padding: 30px;
	            float: left
	        } 
		</c:forEach>	
			
		<c:forEach var="j" begin="31" end="38" step="1" >
			.box${j}{
	            background-color: yellow;
	            width: 50px;
	            height: 50px;
	            margin: 10px;
	            padding: 30px;
	            float: left
	        } 
		</c:forEach>
		
		<c:forEach var="i" begin="39" end="46" step="1" >
			.box${i}{
	            background-color: yellow;
	            width: 50px;
	            height: 50px;
	            margin: 10px;
	            padding: 30px;
	            float: left
	        } 
		</c:forEach>	
	</style>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script>
			$(document).ready(function(){
				$(".check").click(function(){
					if($(this).is(":checked")){
						$(".box"+$(this).val()).css("background-color", "red")
					
						
					}else{
						$(".box"+$(this).val()).css("background-color", "yellow")
					}
				});
			});
	</script>
	
	<%--$(".check").click(function(){
					$(".box"+$(this).val()).fadeOut(1000)
				}); --%>
</head>
<body>
	<form action="/lhc/lhcSeats/lhcReserveForm.lhc" method="post">
		<h1 align="center">${pc.lhc_name} 의 PC방 좌석도 </h1>
		<h3>좌석번호를 선택후 예약하세오 <input type="submit" value="예약하기" /></h3>
			 
			 <div id="lhcReserveForm"></div>
			
			<%--추가로 보내야할 데이터들 --%>
			<input type="hidden" name="lhc_num" value="${pc.lhc_num}" />
			
			<div class="empty">empty</div>
		<c:forEach var="i" begin="1" end="10" step="1" >
			<div class="box${i}"><input class="check" type="checkbox" name="checkbox" value="${i}" /><h2>${i}</h2></div>
		</c:forEach>
		
		
		
		<c:forEach var="i" begin="21" end="22" step="1">
			<div class="box${i}"><input type="checkbox" class="check" name="checkbox" value="${i}" /><h2>${i}</h2></div>
		</c:forEach>
			<div class="box23"><input class="check" type="checkbox" name="checkbox" value="23" /><h2>23</h2></div>
			<div class="empty">empty</div>
			<div class="empty">empty</div>
			
			<c:forEach var="i" begin="31" end="38" step="1">
				<div class="box${i}"><input type="checkbox" class="check" name="checkbox" value="${i}" /><h2>${i}</h2></div>
			</c:forEach>
				
			<c:forEach var="i" begin="24" end="26" step="1">
				<div class="box${i}"><input type="checkbox" class="check" name="checkbox" value="${i}" /><h2>${i}</h2></div>
			</c:forEach>
			
			<div class="box27"><input type="checkbox" class="check" name="checkbox" value="27" /><h2>27</h2></div>
			<div class="empty">empty</div>
			<div class="empty">empty</div>
		
			<c:forEach var="i" begin="39" end="46" step="1">
				<div class="box${i}"><input type="checkbox" class="check" name="checkbox" value="${i}" /><h2>${i}</h2></div>
			</c:forEach>
			
			<c:forEach var="i" begin="28" end="30" step="1">
				<div class="box${i}"><input type="checkbox" class="check" name="checkbox" value="${i}" /><h2>${i}</h2></div>
			</c:forEach>
			
			<div class="empty">empty</div>
			
		<c:forEach var="i" begin="11" end="20" step="1" >
			<div class="box${i}"><input type="checkbox" class="check" name="checkbox" value="${i}" /><h2>${i}</h2></div>
		</c:forEach>
	</form>
</body>
</html>