package spring.aop.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import member.model.dao.MemberDAOImpl;
import member.model.vo.MemberVO;

@Controller
@RequestMapping("/aop/")
public class AopMemberBean {
	
	@RequestMapping("main.do")
	public String main() {
		return "aopMember/main";
	}
	
	@Autowired
	private MemberDAOImpl memberDAO = null;

	@RequestMapping("mypage.do")
	public String mypage() {
		
		return "aopMember/mypage";
	}
	
	@RequestMapping("signupForm.do")
	public String signupForm() {
		
		return "aopMember/signupForm";
	}

	@RequestMapping("signupPro.do")
	public String signupPro(MemberVO vo) throws Exception {
		// 회원 가입 처리
		memberDAO.insertMember(vo);
		
		// 메인으로 이동
		
		return "aopMember/main";
	}
	
	@RequestMapping("loginForm.do")
	public String loginForm() {
		
		return "aopMember/loginForm";
	}
	
	// 회원 로그인 처리 메서드
	@RequestMapping("loginPro.do")
	public String loginPro(HttpSession session, MemberVO vo, String auto, String id, Model model) throws Exception {
		// 로그인 처리
		int check = memberDAO.idPwCheck(vo);
		if(check == 1) {
			session.setAttribute("memId", vo.getId());
		}
		model.addAttribute("check", check);
		
		return "aopMember/loginPro";
	}
	
	
	// 아이디 중복 검사 메서드 (ajax 이용)
		@RequestMapping("ajaxIdAvail.do")
		@ResponseBody // 문자열로 리턴해주고 싶을때 쓰는 어노테이션
		// public String ajaxIdAvail(String id) throws Exception{
		public ResponseEntity<String> ajaxIdAvail(String id) throws Exception{
			String result = "";
			int check = memberDAO.idAvailCheck(id);
			if(check == 1) {
				result = "Unavailable";
			}else if(check == 0) {
				result = "Available";
			}
			// 한글을 넘겨주기 위해 HttpHeaders 객체를 만들어주고
			// utf-8을 넣어줘야 함 (헤더 정보 추가)
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html;charset=utf-8");
			
			// 마지막 statue는 상태를 말함. 대부분 HttpStatus.CREATED 어떠한 생성 작업을 요청받았으며 생성작업을 성공하였다.
			// 또는 HttpStatus.OK 를 넣어준다
			
											   // body결과, 헤더정보, 상태정보(CREATED:201)
			return new ResponseEntity<String>(result, responseHeaders, HttpStatus.CREATED);
		}
	
		
	// 로그아웃 메서드
	@RequestMapping("logout.do")
	public String testLogOut(HttpSession session) {
		session.invalidate();
		return "redirect:main.do";
	}
	
	
	// 회원 정보 받아오기
	@RequestMapping("modifyForm.do")
	public String testupdateForm(HttpSession session, Model model, MemberVO vo, HttpServletRequest request) throws Exception{
		
		String id = (String)session.getAttribute("memId");
		if(id != null) {
			vo = memberDAO.selectMember(id);
		}
		model.addAttribute("vo", vo);
		
		return "aopMember/modifyForm";
	}
	
	
	// 회원 정보 수정 
	@RequestMapping("modifyPro.do")
	public String testupdatePro(MemberVO vo, Model model) throws Exception {
		
		memberDAO.updateMember(vo);
		
		return "aopMember/modifyPro";
	}
	
	
	// 회원 정보 탈퇴
	@RequestMapping("deleteForm.do")
	public String testdeleteForm(HttpSession session, MemberVO vo, Model model) throws Exception {
		String id = (String)session.getAttribute("memId");
		if(id != null) {
			vo = memberDAO.selectMember(id);
		}
		model.addAttribute("vo", vo);
		
		return "aopMember/deleteForm";
	}
	
	
	@RequestMapping("deletePro.do")
	public String testdeletePro(HttpSession session, String id, String pw) throws Exception {
		memberDAO.deleteMember(id, pw);
		session.invalidate();
		
		return "aopMember/delete.Pro";
	}
}
