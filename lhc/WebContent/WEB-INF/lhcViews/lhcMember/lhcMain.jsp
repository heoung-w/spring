<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Member Main</title>
	<style>
		.map_wrap, .map_wrap * {margin:0; padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
		.map_wrap {position:relative;width:100%;height:350px;}
		#category {position:absolute;top:0px;left:1320px;border-radius: 5px; border:1px solid #909090;box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);background: #fff;overflow: hidden;z-index: 2;}
		#category li {float:left;list-style: none;width:50px;px;border-right:1px solid #acacac;padding:3px 10;text-align: center; cursor: pointer;}
		#category li.on {background: #eee;}
		#category li:hover {background: #ffe6e6;border-left:1px solid #acacac;margin-left: -1px;}
		#category li:last-child{margin-right:0;border-right:0;}
		#category li img {display: block;margin:80 auto 3px;width:27px;height: 28px; margin-left: 10px;}
		#category li.on .category_bg {background-position-x:-46px;}
	</style>
	<link href="/lhc/lhcResources/style.css" rel="stylesheet" type="text/css">
</head>
<body style="background-color: aliceblue">
<div class="container">
<!-- 헤더 -->
<jsp:include page="../lhcHeader.jsp"/>

<!-- 메인 -->
<div id="map" style="width:100%;height:350px;"></div>
<!-- <button class="btn btn-primary" onclick="window.location.href='/lhc/lhcPc/lhcPcRegisterForm.lhc'">pc등록</button> -->
<!-- <button  class="btn btn-danger" onclick="window.location.href='/lhc/lhcPc/lhcPcListAll.lhc'">pc방리스트보기</button> -->
</div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9ec698eb01fcb8a6e180a95425f45904&libraries=services,clusterer,drawing"></script>
<c:if test="${sessionScope.memId==null}">
	<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		        level: 5 // 지도의 확대 레벨
		    };  
		
		// 지도를 생성   
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		// 마커가 표시될 위치
		var markerPosition  = new kakao.maps.LatLng(33.450701, 126.570667); 

		// 마커를 생성
		var marker = new kakao.maps.Marker({
		    position: markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정
		marker.setMap(map);

        // 인포윈도우로 장소에 대한 설명을 표시
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">로그인 후 이용하세요</div>'
        });      
        infowindow.open(map, marker);
		
	</script>
</c:if>
<c:if test="${sessionScope.memId!=null}">
<div class="map_wrap">
    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
    <ul id="category">
        <li id="BK9" data-order="0"> 
            <img src="/lhc/lhcResources/lhcImg/greenn.JPG"></img>
            여유
        </li>       
        <li id="MT1" data-order="1"> 
            <img src="/lhc/lhcResources/lhcImg/yelloww.JPG"></img>
            보통
        </li>  
        <li id="PM9" data-order="2"> 
            <img src="/lhc/lhcResources/lhcImg/redd.JPG"></img>
            부족
        </li>  
        <li id="OL7" data-order="3"> 
           <img src="/lhc/lhcResources/lhcImg/blackk.JPG"></img>
            만석
        </li>   
    </ul>
</div>
	<script>
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	        level: 5 // 지도의 확대 레벨
	    };  
	
	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	
	//### 1. 내 위치 띄우기 (로그인한 회원의 위치 띄우기)
	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();
	
	// 주소로 좌표를 검색합니다
	geocoder.addressSearch('${member.lhc_addr}', function(result, status) {
	
	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {
	
	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	
	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
	        });
	
	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	        /* var infowindow = new kakao.maps.InfoWindow({
	            content: '<div style="width:150px;text-align:center;padding:6px 0;">내 위치</div>'
	        });
	        infowindow.open(map, marker); */
	
	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	    } 
	});   
	
	
	//### 2. 내 주변 pc방들 띄우기
		var pcL = new Array(); //위도, 경도 담을 배열
		var pcL1 = new Array(); //좌석 담을 배열
		var pcL2 = new Array(); //좌석 예약 건 수 담을 배열
		var pcL3 = new Array(); //PC방 명 담을 배열
		var pcL4 = new Array(); //PC방 고유번호 담을 배열
		
		<c:forEach var="pc" items="${pcList}">
			pcL.push({lat:"${pc.lhc_lat}", lng:"${pc.lhc_lng}"});
		</c:forEach>
		
		//console.log(pcL[0].lat+"은 lat이고, "+pcL[0].lng+"은 lng임");
		
		<c:forEach var="pc1" items="${pcList}">
			pcL1.push("${pc1.lhc_seats}");
		</c:forEach>
		
		<c:forEach var="pc2" items="${pcList}">
			pcL2.push("${pc2.lhc_rescount}");
		</c:forEach>
		
		<c:forEach var="pc3" items="${pcList}">
			pcL3.push("${pc3.lhc_name}");
		</c:forEach>
		
		<c:forEach var="pc4" items="${pcList}">
			pcL4.push("${pc4.lhc_num}");
		</c:forEach>
		
		for (var i = 0; i < pcL1.length; i ++) {
		    
			var latlng =  new kakao.maps.LatLng(pcL[i].lat, pcL[i].lng)		
			console.log(pcL.length+"   pcL.length");  
			//마커 이미지의 이미지 주소입니다
			var imageSrc1 = "/lhc/lhcResources/lhcImg/greenn.JPG";  
			var imageSrc2 = "/lhc/lhcResources/lhcImg/yelloww.JPG"; 
			var imageSrc3 = "/lhc/lhcResources/lhcImg/redd.JPG";  
			var imageSrc4 = "/lhc/lhcResources/lhcImg/blackk.JPG";  
		
			// 마커 이미지의 이미지 크기 입니다
		    var imageSize = new kakao.maps.Size(20,20); 
		    
		    // 마커 이미지를 생성합니다    
		    var markerImage1 = new kakao.maps.MarkerImage(imageSrc1, imageSize)
		    var markerImage2 = new kakao.maps.MarkerImage(imageSrc2, imageSize)
		    var markerImage3 = new kakao.maps.MarkerImage(imageSrc3, imageSize)
		    var markerImage4 = new kakao.maps.MarkerImage(imageSrc4, imageSize)
		    
		    var seats = pcL1[i];
		    //console.log(seats+" seats");
		    var rescounts = pcL2[i];
		    
		    var pcnames = pcL3[i];//pc방 명
		    
		    var pcnums = pcL4[i];//pc방 고유번호
		    
		    /* 
		    	좌석 10개. 
		    	rescounts  ==> 0~3 : 초록색 
		    				   4~7 : 노란색
		    				   8~9 : 빨간색
		    				   10  : 매진
		    
		    */
		    
		    iwRemoveable = true; // removeable 속성을 true 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됨
		    
		    if(rescounts>=0 && rescounts <= 3){ 
		    	var marker = new kakao.maps.Marker({
		            map: map, // 마커를 표시할 지도
		            position: latlng, // 마커를 표시할 위치         
		            image : markerImage1,
		            clickable: true
		        });
		    	var infowindow = new kakao.maps.InfoWindow({
		    	 	map: map, 		     
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+
		                     '<a href="/lhc/lhcPc/lhcPcContent.lhc?lhc_num='+pcnums+'&pageNum=${pageNum}&state=${state}">'+
		                       pcnames+"("+(seats-rescounts)+"석 남음)"+
		                     '</a>'+'</div>',
		            removable : iwRemoveable
			    });
		    	//infowindow.open(map, marker);
		    	 
			}else if(rescounts>=4 && rescounts <= 7){
				var marker = new kakao.maps.Marker({
		            map: map, 
		            position: latlng,        
		            image : markerImage2,
		            clickable: true
		        });
				 var infowindow = new kakao.maps.InfoWindow({
					 map: map, 
			         content: '<div style="width:150px;text-align:center;padding:6px 0;">'+
			         		  '<a href="/lhc/lhcPc/lhcPcContent.lhc?lhc_num='+pcnums+'&pageNum=${pageNum}&state=${state}">'+
			         			pcnames+"("+(seats-rescounts)+"석 남음)"+
			         		  '</a>'+'</div>',
			         removable : iwRemoveable
			       
		         });
				// infowindow.open(map, marker);
			}else if(rescounts>=8 && rescounts <= 9){
				var marker = new kakao.maps.Marker({
		            map: map,
		            position: latlng,           
		            image : markerImage3,
		            clickable: true
		        });
				 var infowindow = new kakao.maps.InfoWindow({
					map: map, 
			        content: '<div style="width:150px;text-align:center;padding:6px 0;">'+
			        		 '<a href="/lhc/lhcPc/lhcPcContent.lhc?lhc_num='+pcnums+'&pageNum=${pageNum}&state=${state}">'+
			        		   pcnames+"("+(seats-rescounts)+"석 남음)"+
			        		 '</a>'+'</div>',
			        removable : iwRemoveable
		        });
				// infowindow.open(map, marker);
			}else if(rescounts == 10){
				var marker = new kakao.maps.Marker({
		            map: map,
		            position: latlng,           
		            image : markerImage4,
		            clickable: true
		        });
				 var infowindow = new kakao.maps.InfoWindow({
					map: map, 
			        content: '<div style="width:150px;text-align:center;padding:6px 0;">'+
			        		 '<a href="/lhc/lhcPc/lhcPcContent.lhc?lhc_num='+pcnums+'&pageNum=${pageNum}&state=${state}">'+
			        		   pcnames+'(만석)'+
			        		 '</a>'+'</div>',
			        removable : iwRemoveable
		        });
				// infowindow.open(map, marker);
			}
		    kakao.maps.event.addListener(marker, 'click', makeClickListener(map, marker, infowindow)); // 마커 클릭하면, infowidow 뜨게 하는 거
		    function makeClickListener(map, marker, infowindow) {
		    	return function() {
		    		infowindow.open(map, marker);
		    	};
		    }
		}
	</script>
</c:if>
</body>
<div class="container">
<jsp:include page="../mFooter.jsp"/>
</div>
</html>