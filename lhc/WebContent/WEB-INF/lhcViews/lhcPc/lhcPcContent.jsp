<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>PC방 정보</title>
</head>
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
</head>
<div class = "container">
<script src= "https://code.jquery.com/jquery-3.4.1.min.js"></script>
<%-- <jsp:include page="/WEB-INF/lhcViews/lhcHeader.jsp"></jsp:include> --%>
</div>
	
<script>
    function menu() {
		window.open("/lhc/lhcPcMenu/lhcPcMenuContent.lhc?lhc_num=${pc.lhc_num}","","width=500,height=500,left=900,top=200");
	}	    
    function spec() {
		window.open("/lhc/lhcPcSpec/lhcPcSpecContent.lhc?lhc_name=${pc.lhc_name}","","width=500,height=500,left=900,top=200");
	}	    
		var number = "${pc.lhc_num}";
		function getLike(){
			$.ajax({
				type : "post",
				url : "../lhcMember/lhcLikeCheck.lhc",	// 처리할 곳으로 보내주고
				data : {num : number},
				success : function(data){	// 리턴값을 여기서 받는다 yes or no 
					console.log(data);
					/* $("#likeCount").text(data); */
				}
			})
		}
		/*
		$(document).ready(function(){
			setInterval(getLike,1000);
		})
 		$("#getLike").click(function(){
			alert("ㅋㅋㅋ");

		}); */
	</script>
<body>
<h3 align="center">PC방 정보</h3>
	<table>
		<tr>
			<td>
				<c:if test ="${pc.lhc_img== null}">
					<img src="/lhc/lhcResources/lhcImg/Desert.jpg" width="100"/>
				</c:if>
				<c:if test ="${pc.lhc_img != null}">
					<img src="/lhc/lhcSave/${pc.lhc_img}" width="100"/>
				</c:if>
			</td>
			<td colspan="2"><h2>${pc.lhc_name}</h2></td>
		</tr>
		<tr>
			<td>소개글</td>
			<td>${pc.lhc_info}</td>
		</tr>
		<tr>
			<td><a href="#" onclick="return menu()"><button type="button" >음식 메뉴</button></a></td>
			<td><a href="#" onclick="return spec()"><button type="button" >pc 사양</button></a></td>
		</tr>
		<tr>
			<td>자주가는 PC방등록</td>
			<td><button onclick="getLike()">좋아요</button>${pc.lhc_like }</td>
		</tr> 
		<tr>
			<td colspan="3">
				<button onclick="window.location.href='history.go(-1)'">닫기</button>			
			</td>
		</tr>
	</table>
	
	<h2 align="center">지도 들어가는 곳</h2>
	<div id="map" style="width:30%; height:200px; margin-left: 655px"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9ec698eb01fcb8a6e180a95425f45904&libraries=services,clusterer,drawing"></script>
	<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  
		
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		//주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		
		//주소로 좌표를 검색합니다
		geocoder.addressSearch('${pc.lhc_addr}', function(result, status) {
		
		 // 정상적으로 검색이 완료됐으면 
		  if (status === kakao.maps.services.Status.OK) {
		
		     var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		
		     // 결과값으로 받은 위치를 마커로 표시합니다
		     var marker = new kakao.maps.Marker({
		         map: map,
		         position: coords
		     });
		
		     // 인포윈도우로 장소에 대한 설명을 표시합니다
		     var infowindow = new kakao.maps.InfoWindow({
		         content: '<div style="width:150px;text-align:center;padding:6px 0;">여기</div>'
		     });
		     infowindow.open(map, marker);
		
		     // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		     map.setCenter(coords);
		 } 
		}); 
	</script>
	
	<form align="center" action="/lhc/lhcSeats/lhcSeats.lhc" method="post">
		<input type="hidden" name="pageNum" value="${pageNum}" />
		<input type="hidden" name="lhc_num" value="${pc.lhc_num}" />
		<input type="submit" value="예약">
	</form>
</body>
</html>