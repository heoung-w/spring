
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

   <form action="updatePro.do" method="post">
      id: ${dto.id}<br/>
      pw:  <input type="password" name="pw" value="${dto.pw}"/><br/>
      age: <input type="text" name="age" value="${dto.age}"><br/>
           <input type="submit" value="수정"/>
   </form>

</body>
</html>
