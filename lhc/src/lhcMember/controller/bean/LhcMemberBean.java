package lhcMember.controller.bean;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lhcMember.model.dao.LhcMemberDAO;

@Controller
@RequestMapping("/lhcMember/")
public class LhcMemberBean {

	@RequestMapping("lhcMain.lhc")
	public String main() {
		
		return "lhcMember/lhcMain";
	}
	
	@RequestMapping("lhcLoginForm.lhc")
	public String loginForm() {
		
		return "lhcMember/lhcLoginForm";
	}
	
	@RequestMapping("lhcLoginPro.lhc")
	public String loginPro() {
		
		
		
		return "lhcMember/lhcMain";
	}
	
	@RequestMapping("lhcSignupForm.lhc")
	public String signupform() {
		
		return "lhcMember/lhcSignupForm";
	}
	
	
	// 아이디 중복 검사 메서드 (ajax 이용)
//	@RequestMapping("lhcAjaxIdAvail.lhc")
//	@ResponseBody // 문자열로 리턴해주고 싶을때 쓰는 어노테이션
//	// public String ajaxIdAvail(String id) throws Exception{
//	public ResponseEntity<String> ajaxIdAvail(String id) throws Exception{
//		String result = "";
//		int check = LhcMemberDAO.idAvailCheck(id);
//		if(check == 1) {
//			result = "Unavailable";
//		}else if(check == 0) {
//			result = "Available";
//		}
//		// 한글을 넘겨주기 위해 HttpHeaders 객체를 만들어주고
//		// utf-8을 넣어줘야 함 (헤더 정보 추가)
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
//		
//		// 마지막 statue는 상태를 말함. 대부분 HttpStatus.CREATED 어떠한 생성 작업을 요청받았으며 생성작업을 성공하였다.
//		// 또는 HttpStatus.OK 를 넣어준다
//		
//										   // body결과, 헤더정보, 상태정보(CREATED:201)
//		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.CREATED);
//	}
			
			
			
			
			
			
			
			
			
			
			
}
