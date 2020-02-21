<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 
		Bean 클래스(helloBean에서 view로 전달되는 커멘드객체 (Model 계층에 삽입되는 객체)를 출력
		@ModelAttribute 어노테이션에 별칭을 지정하지 않으면,
		전달하는 객체의 클래스 타입의 앞글자를 소문자로 바꿔서 속성명으로 전달되므로
		TvDTo => tvDTO 라는 이름으로 객체에 접근 가능함.
		
		@ModelAttribute 어노테이션이 붙은 메서드는 해당 클래스의 하위 RequestMapping 어노테이션이
		붙은 메서드보다 선행되어 실행된다
		따라가 RequestMapping으로 호출된 메서드는 @ModelAttibute 메서드를 반드시 선행 실행한다.
		==> 이미 리턴되는 객체가(tv)가 Model 속성으로 저장되어 있어서 페이지 실행시 꺼내 쓸수 있다.
		==> 맨앞 TvDTO.??? ==> tvDTO.??? 맨앞 대문자는 소문자로 바꿔서 사용!!
	 -->
	<h2>---tvDTO-----</h2>
	<h2>hello tv</h2>
	<h2>${tvDTO.power}</h2>
	<h2>${tvDTO.ch}</h2>
	<h2>${tvDTO.col}</h2>
	<br/><br/>
	<h2>---tvInfo-----</h2>
	<h2>hello tv</h2>
	<h2>${tvInfo.power}</h2>
	<h2>${tvInfo.ch}</h2>
	<h2>${tvInfo.col}</h2>

</body>
</html>