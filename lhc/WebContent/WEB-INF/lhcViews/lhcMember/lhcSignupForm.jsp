<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<link href="/lhc/lhcResources/style.css" rel="stylesheet" type="text/css">
	<script>
		// 유효성 검사
		function check(){
			var inputs = document.inputForm;
			if(!inputs.id.value){
				alert("아이디를 입력하세요.");
				inputs.id.focus();	// 아이디입력란에 커서 깜빡이게 focus주기 
				return false;
			}
			if(!inputs.pw.value){
				alert("비밀번호를 입력하세요.");
				return false;
			}
			if(!inputs.pwCh.value){
				alert("비밀번호 확인란을 입력하세요.");
				return false;
			}
			if(inputs.pw.value != inputs.pwCh.value){
				alert("비밀번호를 동일하게 입력하세요");
				return false;
			}
			if(!inputs.name.value){
				alert("이름을 입력하세요.");
				return false;
			}
			if(!inputs.age.value){
				alert("나이를 입력하세요.");
				return false;
			}
			if(!inputs.phone.value){
				alert("전화번호를 입력하세요.");
				return false;
			}
			if(!inputs.addr.value){
				alert("주소를 입력하세요.");
				return false;
			}
		}
		// 아이디 중복 검사 함수
		// inputForm 매개변수에 form 전체를 받음.(this.form)
		/* function confirmIdPopup(inputForm){	
			// 아이디 기입 재확인.
			if(inputForm.id.value == ""){
				alert("아이디를 입력하세요.");
				return;			// 기입 안했으면 현재 메서드 종료
			}
			
			// 팝업 띄워서 아이디만 DB에 존재하는지 확인한후 돌아오게
			var url = "/spring/member/idAvail.do?id="+inputForm.id.value;
			// 화면의 띄워줄 페이지 이름과 id=값(name속성값이 id인 input 태그에 기입한 값)
			
			open(url,"confirm", "toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizalbe=no, width=300, height=200");
			
		} */
	</script>
	<script src= "https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script>
		$(document).ready(function(){
			$("#id").change(function(){
				id = $("#id").val();
				$.ajax({
					type : "post",
					url : "/lhc/lhcMember/lhcAjaxIdAvail.lhc",	// 처리할 곳으로 보내주고
					data : {id : id},
					success : function(data){	// 리턴값을 여기서 받는다 yes or no 
						console.log(data);
						$("#idCheckRes").val(data);
					}
				});
			});
		});
	</script>
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script>
			function addr_set(){
			    new daum.Postcode({
			        oncomplete: function(data) {
			        	console.log(data);
			        	$("#add").val(data["address"]);
			            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
			            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
			        }
			    }).open();
			};
		</script>
	

</head>
<c:if test="${sessionScope.memId != null}">
	<script>
		alert("로그아웃 후 회원가입 해주세요.");
		window.location = "/lhc/lhcMember/lhcMain.lhc";
	</script>
</c:if>
<c:if test="${sessionScope.memId == null}">
<body>
	<br />
	<h1 align="center">회원가입</h1>
	<form action="/lhc/lhcMember/lhcSignupPro.lhc" method="post" name="inputForm" >
	<table>
		<tr>
			<td>아이디 * </td>
			<td colspan="2"><input type="text" name="id" id="id" style="text-align:center; width:200px"/></td>
		</tr>
		<tr>
			<td>아이디 사용 가능 여부</td>
			<td colspan="2"><input type="text" id="idCheckRes" disabled style="text-align:center; width:200px"/></td>
		</tr>
		<!--  
		<tr>
			<td>아이디 중복검사</td>
			<td><input type="button" value="중복검사" onclick="confirmIdPopup(this.form)" /></td>
		</tr>
		 -->
		<tr>
			<td>비밀번호 * </td>
			<td colspan="2"><input type="password" name="pw" style="text-align:center; width:200px"/></td>
		</tr>
		<tr>
			<td>비밀번호 확인 * </td>
			<td colspan="2"><input type="password" name="pwCh" style="text-align:center; width:200px"/></td>
		</tr>
		<tr>
			<td>이름 * </td>
			<td colspan="2"><input type="text" name="name" style="text-align:center; width:200px"/></td>
		</tr>
		<tr>
			<td>나이 * </td>
			<td colspan="2"><input type="text" name="age" style="text-align:center; width:200px"/></td>
		</tr>
		<tr>
			<td>전화번호 * </td>
			<td colspan="2"><input type="text" name="phone" style="text-align:center; width:200px"/></td>
		</tr>
		<tr>
			<td>주소 * </td>
			<td>
				<input type="text" id="add" name="addr" style="text-align:center; width:180px"/> 
				<input type="button" value = "주소검색" onclick="addr_set()"/>
			</td>
		</tr>
		<tr>
			<td>상세 주소 *</td>
			<td colspan="2"><input type="text" name="addr2" style="text-align:center; width:200px"> 
			</td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				<input type="submit" value="가입" />
				<input type="button" value="취소" onclick="window.location.href='/lhc/lhcMember/lhcMain.lhc'" /> 
			</td>
		</tr> 
	</table>
	</form>

</body>
</c:if>
</html>







