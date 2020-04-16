<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
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
<div class = "container">
<jsp:include page="/WEB-INF/lhcViews/lhcHeader.jsp"/>
</div>
<body>
	<br />
	<div class = "container">
	<h1 align="center">PC방 전체 리스트</h1>	
	<%--Pc방 정보글 없을때 --%>
	<c:if test="${count == 0}">
	<table>
		<tr>
			<td>
				<button onclick="window.location='/lhc/lhcPc/lhcPcRegister.lhc'">pc방 등록</button>
			</td>
		</tr>
		<tr>
			<td>pc방 정보가 없습니다.</td>
		</tr>
	</table>
	</c:if>
	</div>
	<%--PC방 정보 있을때 --%>
	<div class = "container">
	<c:if test="${count > 0}">
	<table>
		<tr>
			<td>No.</td>
			<td>PC방 명</td>
			<td>사진</td>
			<td>전화번호</td>
			<td>주소</td>
			<td>정보</td>
			<td>좌석 수</td>
		</tr>
		<!-- for문 반복문 돌아가기  -->
	<c:forEach var="pc" items="${pcList}">
		<tr>
			<td>${number}
				<c:set var="number" value="${number-1}" />
			</td>
			<td><a href="/lhc/lhcPc/lhcPcContent.lhc?lhc_num=${pc.lhc_num}&pageNum=${currentPage}&state=${pc.lhc_state}">${pc.lhc_name}</a></td>
			<td>
				<c:if test="${pc.lhc_img==null}">
					<img src="/lhc/lhcResources/lhcImg/Desert.jpg" width="100"/>
				</c:if>
				<c:if test="${pc.lhc_img!=null }">
					<img src="/lhc/lhcSave/${pc.lhc_img}" width="100"/>
				</c:if>
			</td>
			<td>${pc.lhc_phone}</td>
			<td>${pc.lhc_addr}</td>
			<td>${pc.lhc_info}</td>
			<td>${pc.lhc_seats}</td>
		</tr>
	</c:forEach>
	</table>
	</c:if>
	</div>
	<br />
	
	<%--목록의 페이지 번호 뷰어 설정 --%>
	<div align="center">
	<c:if test="${count > 0}">
		<fmt:parseNumber var="res" value="${count/pageSize}" integerOnly="true" />
		<c:set var="pageCount" value="${res + (count % pageSize == 0 ? 0 : 1)}" />
		<c:set var="pageBlock" value="10" />
		<fmt:parseNumber var="result" value="${currentPage/pageBlock}" integerOnly="true" />
		<fmt:parseNumber var="startPage" value="${result * pageBlock + 1}" />
		<fmt:parseNumber var="endPage" value="${startPage + pageBlock - 1}" />
		<c:if test="${endPage > pageCount}" >
			<c:set var="endPage" value="${pageCount}" />
		</c:if>
		<c:if test="${startPage > pageBlock}">
			<a href="/lhc/lhcPc/lhcPcListAll.lhc?pageNum=${startPage-pageBlock}" > &lt;</a>
		</c:if>
		
		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
			<a href="/lhc/lhcPc/lhcPcListAll.lhc?pageNum=${i}" class="nums"> &nbsp; ${i} &nbsp;</a>
		</c:forEach>
		
		<c:if test="${endPage < pageCount}">
			<a href="/lhc/lhcPc/lhcPcListAll.lhc?pageNum=${startPage+pageBlock}"> &gt;</a>
		</c:if>
	</c:if>
	</div>
</body>
</html>