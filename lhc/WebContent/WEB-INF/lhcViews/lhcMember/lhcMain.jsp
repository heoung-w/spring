<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Main</title>
	<!-- 합쳐지고 최소화된 최신 CSS -->
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
<body>
<div class="container">
<!-- 헤더 -->
<jsp:include page="../lhcHeader.jsp"/>

<!-- 메인 -->
<div id="map" style="width:100%;height:350px;"></div>
<button class="btn btn-primary" onclick="window.location.href='/lhc/lhcMember/lhcPcRegisterForm.lhc'">pc등록</button>
<button  class="btn btn-danger" onclick="window.location.href='/lhc/lhcPc/lhcPcListAll.lhc'">pc방리스트보기</button>
</div>




<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9ec698eb01fcb8a6e180a95425f45904&libraries=services,clusterer,drawing"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 2 // 지도의 확대 레벨
    };
var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
 
// 마커를 표시할 위치 객체 배열입니다 
var positions = [
    {
        latlng: new kakao.maps.LatLng(33.450705, 126.570677)
    },
    { 
        latlng: new kakao.maps.LatLng(33.450936, 126.569477)
    },
    {
        latlng: new kakao.maps.LatLng(33.450879, 126.569940)
    }
]; 
for (var i = 0; i < positions.length; i ++) {
    
	// 마커 이미지의 이미지 주소입니다
	var imageSrc1 = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";  
	var imageSrc2 = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png"; 
	var imageSrc3 = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/sign-info-48.png";  

	// 마커 이미지의 이미지 크기 입니다
    var imageSize = new kakao.maps.Size(24, 35); 
    
    // 마커 이미지를 생성합니다    
    var markerImage1 = new kakao.maps.MarkerImage(imageSrc1, imageSize)
    var markerImage2 = new kakao.maps.MarkerImage(imageSrc2, imageSize)
    var markerImage3 = new kakao.maps.MarkerImage(imageSrc3, imageSize)
    
 // 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    var content1 = '<div class="wrap">' + 
        '    <div class="info">' + 
        '        <div class="title">' + 
        '            star PC방' + 
        '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' + 
        '        </div>' + 
        '        <div class="body">' + 
        '            <div class="img">' +
        '                <img src="https://item.kakaocdn.net/do/beed7a12f59489d5e333dc0298126021f43ad912ad8dd55b04db6a64cddaf76d" width="73" height="70">' +
        '           </div>' + 
        '            <div class="desc">' + 
        '                <div class="ellipsis">제주특별자치도 제주시 첨단로 242</div>' + 
        '                <div class="jibun ellipsis">(우) 63309 (지번) 영평동 2181</div>' + 
        '                <div><a href="http://www.kakaocorp.com/main" target="_blank" class="link">홈페이지</a></div>' + 
        '            </div>' + 
        '        </div>' + 
        '    </div>' +    
        '</div>';
    var content2 = '<div class="wrap">' + 
        '    <div class="info">' + 
        '        <div class="title">' + 
        '            rainbow PC방' + 
        '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' + 
        '        </div>' + 
        '        <div class="body">' + 
        '            <div class="img">' +
        '                <img src="https://i.pinimg.com/originals/5e/4b/21/5e4b2163c7c3f3383c22ecf282e8ad93.gif" width="73" height="70">' +
        '           </div>' + 
        '            <div class="desc">' + 
        '                <div class="ellipsis">경기도 서울특별시 아무로 590</div>' + 
        '                <div class="jibun ellipsis">(우) 43312 (지번) 룰루동 19</div>' + 
        '                <div><a href="http://www.kakaocorp.com/main" target="_blank" class="link">홈페이지</a></div>' + 
        '            </div>' + 
        '        </div>' + 
        '    </div>' +    
        '</div>';
    var content3 = '<div class="wrap">' + 
        '    <div class="info">' + 
        '        <div class="title">' + 
        '            tree PC방' + 
        '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' + 
        '        </div>' + 
        '        <div class="body">' + 
        '            <div class="img">' +
        '                <img src="https://i.pinimg.com/originals/f5/06/cd/f506cd30132a31f366ea211098311510.gif" width="73" height="70">' +
        '           </div>' + 
        '            <div class="desc">' + 
        '                <div class="ellipsis">경기도 수원시 태장로 71</div>' + 
        '                <div class="jibun ellipsis">(우) 11111 (지번) 망포동 0099</div>' + 
        '                <div><a href="http://www.kakaocorp.com/main" target="_blank" class="link">홈페이지</a></div>' + 
        '            </div>' + 
        '        </div>' + 
        '    </div>' +    
        '</div>';
    // 마커와 커스텀 오버레이를 생성합니다
    var position = positions[i].latlng;
    if(position == positions[0].latlng){
    	var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치         
            image : markerImage3           
        });
        var overlay = new kakao.maps.CustomOverlay({
            map: map,
            position: marker.getPosition(),
            content: content1,
            yAnchor: 1 
        });
	}else if(position == positions[1].latlng){
		var marker = new kakao.maps.Marker({
            map: map, 
            position: positions[i].latlng,        
            image : markerImage1         
        });
		var overlay = new kakao.maps.CustomOverlay({
            map: map,
            position: marker.getPosition(),
            content: content2,
            yAnchor: 1 
        });
	}else if(position == positions[2].latlng){
		var marker = new kakao.maps.Marker({
            map: map,
            position: positions[i].latlng,           
            image : markerImage2         
        });
		var overlay = new kakao.maps.CustomOverlay({
            map: map,
            position: marker.getPosition(),
            content: content3,
            yAnchor: 1 
        });
	}
	 // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
	 kakao.maps.event.addListener(marker, 'click', function() {
	     overlay.setMap(map);
	 });
	
	 // 커스텀 오버레이를 닫기 위해 호출되는 함수입니다 
	 function closeOverlay() {
	     overlay.setMap(null);     
	 }
	 // 마커를 클릭했을 때, 커스텀 오버레이 표시하는 함수랑, 닫는 함수를 호출하는건데..
}
</script>

</body>
</html>