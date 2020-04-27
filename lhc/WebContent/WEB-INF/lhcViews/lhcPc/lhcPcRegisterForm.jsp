<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>lhcPcRegisterForm</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9ec698eb01fcb8a6e180a95425f45904&libraries=services,clusterer,drawing"></script>
<script>

	// 주소 -> 위도, 경도 값 구하기
	$(document).ready(function(){
		$("#lhc_addr1").click(function(){
			address = $("#lhc_addr").val();
			var x = 0;
			var y = 0;			
			var geocoder = new kakao.maps.services.Geocoder();
			geocoder.addressSearch(address, function(result, status) {
			    if (status === kakao.maps.services.Status.OK) {	
			        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);				
			        x = result[0].y;
			        y = result[0].x;			     				
					$("#lhc_lat").val(x);
					$("#lhc_lng").val(y);	
			    } 			
			});
		});
		
	});

	// 유효성 검사
	function PCcheck(){
		var registers = document.RegisterForm;
		
		if(!registers.lhc_name.value){
			alert("PC방 명을 입력하세요.");
			inputs.lhc_name.focus(); 
			return false;
		}
		if(!registers.lhc_img.value){
			alert("사진을 등록하세요.");
			return false;
		}
		if(!registers.lhc_addr.value){
			alert("주소를 입력하세요.");
			return false;
		}
			if(!registers.lhc_info.value){
			alert("정보를 입력하세요.");
			return false;
		}
		if(!registers.lhc_vendor.value){
			alert("정보를 입력하세요.");
			return false;
		}
		if(!registers.lhc_seats.value){
			alert("좌석 수를 입력하세요.");
			return false;
		}
	}	
/* 	
 	####### 값 제대로 나오는 지 확인하기 ##########
 	$(document).ready(function(){
		$("#lhc_addr1").click(function(){
			address = $("#lhc_addr").val();
			//console.log(address);
			var x = 0;
			var y = 0;			
			var geocoder = new kakao.maps.services.Geocoder();
			geocoder.addressSearch(address, function(result, status) {
				//console.log(address);
				//console.log(result);
			    if (status === kakao.maps.services.Status.OK) {	
			        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);				
			        	//console.log(result[0].y);
			        	//console.log(result[0].x);
			        x = result[0].y;
			        y = result[0].x;	
			        	//console.log(x);
			        	//console.log(y);
					$("#lhc_lat").val(x); 
					$("#lhc_lng").val(y);
						//testy = $("#lhc_lat").val();
						//testy = $("#lhc_lng").val();
						//console.log(testx);
						//console.log(testy);
			    } 			
			});
		});
		
	}); 
*/
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
	<h1 align="center" style="font-size: x-large; color:white;">PC방 등록</h1>
	<form action="/lhc/lhcPc/lhcPcRegisterPro.lhc" method="post" name="RegisterForm" id="RegisterForm1" onsubmit="return PCcheck()" enctype="multipart/form-data">
		<%--숨겨서 글 속성에 관련된 데이터 pro에 전송 --%>
		<input type="hidden" name="lhc_num" value="${lhc_num}"/>
		<input type="hidden" name="lhc_state" value="b"/>
		<input type="hidden" name="lhc_lat" id="lhc_lat" value=""/>
		<input type="hidden" name="lhc_lng" id="lhc_lng" value=""/> 
		<table class="type09" align="center"  >
			<tr scope="cols">
				<th>PC방 명</th>
				<td align="left"><input type="text" name="lhc_name"/></td>
			</tr>
			<tr scope="cols">
				<th>사진</th>
				<td align="left"><input type="file" name="lhc_img" /></td>
			</tr>
			<tr scope="cols">
				<th>전화번호</th>
				<td align="left"><input type="text" name="lhc_phone"/></td>
			</tr>
			<tr scope="cols">
				<th>주소</th>
				<td align="left">
					<input type="text" name="lhc_addr" id="lhc_addr"/>
					<input type="button" value="btn" id="lhc_addr1" class="btn btn-default"/>
				</td>
			</tr>
			<tr scope="cols">
				<th>정보</th>
				<td><textarea rows="20" cols="70" name="lhc_info"></textarea></td>
			</tr>
			<tr scope="cols">
				<th>좌석 수</th>
				<td align="left"><input type="text" name="lhc_seats"/></td>
			</tr>		
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="PC방 등록" class="btn btn-default"/>
					<input type="button" value="PC방 리스트" onclick="window.location='/lhc/lhcPc/lhcPcListAll.lhc'" class="btn btn-default"/>
				</td>
			</tr>			
		</table>
	</form>
	</div>					
</body>
</c:if>
</html>