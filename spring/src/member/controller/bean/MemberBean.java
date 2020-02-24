package member.controller.bean;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import member.model.dao.MemberDAOImpl;
import member.model.vo.MemberVO;

@Controller
@RequestMapping("/member/")
public class MemberBean {
	
	@Autowired
	private MemberDAOImpl memberDAO = null;

	@RequestMapping("main.do")
	public String main() {
		
		return "member/main";
	}
	
	@RequestMapping("signupForm.do")
	public String signupForm() {
		
		return "member/signupForm";
	}

	@RequestMapping("signupPro.do")
	public String signupPro(MemberVO vo) throws Exception {
		// 회원 가입 처리
		memberDAO.insertMember(vo);
		
		// 메인으로 이동
		
		return "member/main";
	}
	
	@RequestMapping("loginForm.do")
	public String loginForm() {
		
		return "member/loginForm";
	}
	
	@RequestMapping("loginPro.do")
	public String loginPro(HttpSession session, MemberVO vo, String auto, String id, Model model) throws Exception {
		// 로그인 처리
		int check = memberDAO.idPwCheck(vo);
		if(check == 1) {
			session.setAttribute("memId", vo.getId());
		}
		model.addAttribute("check", check);
		
		return "member/loginPro";
	}
	
	@RequestMapping("logout.do")
	public String logOutMember(HttpSession session) {
		session.invalidate();
		return "member/logout";
	}
	
	@RequestMapping("modifyForm.do")
	public String updateForm(HttpSession session, Model model, MemberVO vo) throws Exception{
		
		String id = (String)session.getAttribute("memId");
		if(id != null) {
			vo = memberDAO.selectMember(id);
		}
		model.addAttribute("vo", vo);
		
		return "member/modifyForm";
	}
	
	
	@RequestMapping("modifyPro.do")
	public String updatePro(MemberVO vo, Model model) throws Exception {
		
		memberDAO.updateMember(vo);
		
		return "member/modifyPro";
	}

}
