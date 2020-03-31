package lhcMember.controller.bean;

import java.io.IOError;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpServerErrorException;

import lhcMember.model.dao.LhcMemberDAO;
import lhcMember.model.dao.LhcMemberDAOImpl;
import lhcMember.model.vo.LhcMemberVO;

@Controller
@RequestMapping("/lhcMember/")
public class LhcMemberBean {

	@Autowired
	private LhcMemberDAOImpl lhcMemberDAO = null;
	// DI,IOC,AOP

	// RequestMapping을 **로 받고 return null로 해주면
	// jsp 파일 이름과 요청한 uri 이름이 같은 곳을 알아서 찾아 가줌.

	/*
	 * @RequestMapping("lhcMain.lhc") public String main(HttpServletResponse
	 * response) throws IOException {
	 * 
	 * PrintWriter out = response.getWriter(); out.write("<script>");
	 * out.write("window.location.href='/lhc/lhcMember/lhcMain.lhc'");
	 * out.write("</script>");
	 * 
	 * return "lhcMember/lhcMain"; }
	 */
	
	// RequestMapping을 **로 받고 return null로 해주면
	// jsp 파일 이름과 요청한 uri 이름이 같은 곳을 알아서 찾아 가줌.
	@RequestMapping("**")
	public String all(HttpServletRequest request) {
		String uri = request.getRequestURI();
		System.out.println();
		uri = uri.substring(5, uri.length() - 4);
		return uri;
	}

	// 아이디 중복 체크 하는 메서드
	@RequestMapping("lhcAjaxIdAvail.lhc")
	@ResponseBody
	public ResponseEntity<String> ajaxIdAvail(String id) throws Exception {

		String result = "";
		int check = lhcMemberDAO.idAvailCheck(id);
		if (check == 1) {
			result = "이미 사용중인 아이디 입니다.";
		} else if (check == 0) {
			result = "사용 가능한 아이디 입니다.";
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");

		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping("lhcSignupForm.lhc")
	public String signup(HttpServletRequest request, HttpServletResponse response, LhcMemberVO vo, String addr, String addr2) throws Exception {
		System.out.println(request.getMethod());
		if (request.getMethod().equals("POST")) {
			 vo.setAddr(addr+" "+addr2); 
			 lhcMemberDAO.insertMember(vo);
			// 다른폴더로 갈때는 ../lhcBoard/lhcMain.lhc
			response.sendRedirect("/lhc/lhcMember/lhcMain.lhc");
			return null;
		}

		return "lhcMember/lhcSignupForm";
	}

	@RequestMapping("lhcLoginPro.lhc")
	public String login(HttpServletResponse response, HttpSession session, LhcMemberVO vo, String auto, Model model) throws Exception {

		int check = lhcMemberDAO.idPwCheck(vo);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter(); 
		System.out.println(check);
		if (check == 1) {
			session.setAttribute("memId", vo.getId());
			
			out.write("<script>");
			out.write("alert('"+vo.getId()+"님 환영합니다');");
			out.write("window.location.href='lhcMain.lhc'");
			out.write("</script>");
			return null;
		} else {
			out.write("<script>");
			out.write("alert('로그인 실패');");
			out.write("</script>");
			return null;
		}
	}

	@RequestMapping("lhcDeleteForm.lhc")
	public String deleteForm(HttpSession session, LhcMemberVO vo, Model model) throws Exception {

		return "lhcMember/lhcDeleteForm";
	}
	@RequestMapping("lhcLogout.lhc")
	public String logoutForm(HttpSession session) throws Exception {
		session.invalidate();
		return null;
	}

	@RequestMapping("lhcDeletePro.lhc")
	public String deletePro(HttpSession session, LhcMemberVO vo, Model model) throws Exception {
		String id = (String) session.getAttribute("memId");
		if (id != null) {

		}

		return "lhcMember/lhcDeletePro";
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
