<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>lhcMyPointChargeForm</title>
	<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  		crossorigin="anonymous">
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
	<script>
	
	</script>
</head>
<script>	    
    function pointup() {
		window.open("")
    }
    
    function javascript(){
        self.close();   //자기자신창을 닫습니다.
    }
</script>
<body>
<div class="container">
	<p align ="center">보유 포인트 :${member.lhc_money}원</p>
	
	<form action="/lhc/lhcMember/lhcMyPointChargePro.lhc">
		<input type="radio" name="point" value="5000">5000원<br/>
		<input type="radio" name="point" value="10000">10000원<br/>
		<input type="radio" name="point" value="15000">15000원<br/>
		<input type="radio" name="point" value="20000">20000원<br/>
		<input type="submit" value="충전" />
		<input type='button' onclick='javascript()' value='취소'/>
	</form>
</body>
</div>
</html>