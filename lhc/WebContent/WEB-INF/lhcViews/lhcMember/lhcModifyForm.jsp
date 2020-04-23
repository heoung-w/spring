<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function check(){
		var inputs = document.inputForm;
		if(!input.pw.value){
			alert("비밀번호를 입력하세요");
			inputs.pw.focus();
			return false;
		}
		if(!input.pwCh.value){
			alert("비밀번호 확인란을 입력하세요")
			return false;
		}
		if(input.pw.value != input.pwCh.value){
			alert("비밀번호를 동일하게 입력하세요");
			return false;
		}
		if(!input.phone.value){
			alert("전화번호를 입력하세요")
			return false;
		}
		if(!input.addr.value){
			alert("주소를 입력하세요");
			return false;
		}
		if(!input.addr2.value){
			alert("상세주소를 입력하세요");
			return false;
		}
	
	}
</script>
<script src= "https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function addr_set(){
	    new daum.Postcode({
	        oncomplete: function(data) {
	        	console.log(data);
	        	$("#add").val(data["address"]);
	        }
	    }).open();
	};
</script>

</head>
<jsp:include page="/WEB-INF/lhcViews/lhcHeader.jsp"/>
<c:if test="${sessionScope.memId == null}">
	<script>
		alert("로그인 후 회원수정 해주세요.");
		window.location.href = "/lhc/lhcMember/lhcMain.lhc";
	</script>
</c:if>
<c:if test="${sessionScope.memId != null}">
<body>
<br />
	<h1 align="center">회원수정</h1>
	<form action="/lhc/lhcMember/lhcModifyPro.lhc" method="post" name="inputForm" > 
	<input type ="hidden" name="lhc_id" value="${vo.lhc_id }"/>
	<input type ="hidden" name="lhc_name" value="${vo.lhc_name }"/>
	<input type ="hidden" name="lhc_age" value="${vo.lhc_age }"/>
	
	<table>
		<tr>
			<td>아이디 * </td>
			<td colspan="2">${vo.lhc_id}</td>
		</tr>
		<tr>
			<td>비밀번호 * </td>
			<td colspan="2"><input type="password" name="lhc_pw" style="text-align:center; width:200px"/></td>
		</tr>
		<tr>
			<td>비밀번호 확인 * </td>
			<td colspan="2"><input type="password" name="lhc_pwCh" style="text-align:center; width:200px"/></td>
		</tr>
		<tr>
			<td>이름 * </td>
			<td colspan="2">${vo.lhc_name}</td>
		</tr>
		<tr>
			<td>나이 * </td>
			<td colspan="2">${vo.lhc_age}</td>
		</tr>
		<tr>
			<td>전화번호 * </td>
			<td colspan="2"><input type="text" name="lhc_phone" style="text-align:center; width:200px"/></td>
		</tr>
		<tr>
			<td>주소 * </td>
			<td>
				<input type="text" id="add" name="lhc_addr" style="text-align:center; width:180px"/> 
				<input type="button" value = "주소검색" onclick="addr_set()"/>
			</td>
		</tr>
		<tr>
			<td>상세 주소 *</td>
			<td colspan="2"><input type="text" name="lhc_addr2" style="text-align:center; width:200px"> 
			</td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				<input type="submit" value="수정" />
				<input type="button" value="취소" onclick="window.location.href='/lhc/lhcMember/lhcMyPage.lhc'" /> 
			</td>
		</tr> 
	</table>
	</form>
</body>
</c:if>
</html>