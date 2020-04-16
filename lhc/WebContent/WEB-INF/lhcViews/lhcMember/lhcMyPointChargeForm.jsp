<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>lhcMyPointChargeForm</title>
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
</html>