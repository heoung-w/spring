package lhc.controller.bean;

import java.io.IOError;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.List;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpServerErrorException;

import lhc.model.dao.LhcMemberDAOImpl;
import lhc.model.vo.LhcMemberVO;


@Controller
@RequestMapping("/lhcMember/")
public class LhcMemberBean {
	static PrintWriter out=null;
	
	@ModelAttribute
	public void Writer(HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		out=response.getWriter();
	}
	
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
		System.out.println(uri);
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

	// 회원가입
	@RequestMapping("lhcSignupForm.lhc")
	public String signup(HttpServletRequest request, HttpServletResponse response, LhcMemberVO vo, String lhc_addr, String lhc_addr2) throws Exception {
		System.out.println(request.getMethod());
		if (request.getMethod().equals("POST")) {
			 vo.setLhc_addr(lhc_addr+" "+lhc_addr2); 
			 lhcMemberDAO.insertMember(vo);
			// 다른폴더로 갈때는 ../lhcBoard/lhcMain.lhc
			//response.sendRedirect("/lhc/lhcMember/lhcMain.lhc");
			return "lhcMember/lhcMain";
		}else {
		
			//response.sendRedirect("/lhc/lhcMember/lhcMain.lhc");
			return "lhcMember/lhcSignupForm";
		}
	}

	// 로그인
	@RequestMapping("lhcLoginPro.lhc")
	public String login(HttpServletResponse response, HttpSession session, LhcMemberVO vo, String auto, Model model) throws Exception {

		int check = lhcMemberDAO.idPwCheck(vo);
		System.out.println(check);
		if (check == 1) {

			session.setAttribute("memId", vo.getLhc_id());
			out.write("<script>");
			out.write("alert('"+vo.getLhc_id()+"님 환영합니다');");
			out.write("window.location.href='lhcMain.lhc'");
			out.write("</script>");
			return null;
		} else {
			out.write("<script>");
			out.write("alert('아이디 또는 비밀번호가 일치하지 않습니다');");
			out.write("window.location.href='lhcMain.lhc'");
			out.write("</script>");
			return null;
		}
	}
	
	// 로그 아웃
	@RequestMapping("lhcLogout.lhc")
	public String logoutForm(HttpSession session) throws Exception {
		String id = (String)session.getAttribute("memId");
		if(id != null) {
			session.invalidate();
		}
		return "lhcMember/lhcMain";
	}
	
	// 회원 정보 수정
	@RequestMapping("lhcModifyForm.lhc")
	public String modifyForm(HttpSession session,Model model, LhcMemberVO vo) throws Exception {			
	
		String id = (String)session.getAttribute("memId");
		vo = lhcMemberDAO.selectMember(id);

		model.addAttribute("vo", vo);
		return "lhcMember/lhcModifyForm";
		
	}

	// 회원 수정
	@RequestMapping("lhcModifyPro.lhc")
	public String modifyPro(HttpSession session, LhcMemberVO vo, Model model, HttpServletResponse response, String addr, String addr2) throws Exception{
		vo.setLhc_addr(addr+""+addr2);
		lhcMemberDAO.updateMember(vo);
		
		return "lhcMember/lhcMyPage";
	}
	
	
	// 아이디 삭제
	@RequestMapping("lhcDeleteForm.lhc")
	public String deleteForm(HttpSession session,String pw, Model model) throws Exception {
		if(pw==null) {
			out.write("<script>");
			out.write("var userInput = prompt(\"당신의 패스워드는 무엇입니까?\"+\"\");");
			out.write("window.location.href='lhcDeleteForm.lhc?pw='+userInput");
			out.write("</script>");
			pw="0";
		}
		String id = (String)session.getAttribute("memId");
		String pw2=lhcMemberDAO.idPwCheck2((String)session.getAttribute("memId"));
		System.out.println(pw);
		if(pw.equals(pw2)) {
			lhcMemberDAO.deleteMember(id, pw);
			session.invalidate();
			out.write("<script>");
			out.write("alert('삭제 성공');");
			out.write("window.location.href='lhcMain.lhc'");
			out.write("</script>");
			return null;
		}else{
			out.write("<script>");
			out.write("alert('비밀번호가 일치하지 않습니다.');");
			out.write("history.go(-1)");
			out.write("</script>");
		}
			
		return null;
	}
	
	
	// 회원 , 업주, 관리자 페이지 각각 분리
	@RequestMapping("lhcMyPage.lhc")
	public String mypage(HttpSession session, LhcMemberVO vo) throws Exception{
		
		String id = (String)session.getAttribute("memId");
		vo = lhcMemberDAO.selectMember(id);
		System.out.println(vo.getLhc_sep());
		System.out.println(vo.getLhc_id());
		System.out.println(vo.getLhc_pw());
		System.out.println(vo.getLhc_age());
		System.out.println(vo.getLhc_addr());
		if(id.equals("admin")) {
			System.out.println("어드민입니다");
			return "lhcAdmin/lhcMyPage";
			
		}else if(!id.equals("admin")) {
			System.out.println("어드민이 아닙니다");
			if(vo.getLhc_sep().equals("v")) {
				System.out.println("업주입니다");
				return "lhcVender/lhcMyPage";
			}else {
				System.out.println("고객입니다");
			}
		}		
		return "lhcMember/lhcMyPage";
	}
	
	// 회원 리스트 ( 관리자 , 업주 ) 분리
	@RequestMapping("lhcMemberList.lhc")
	public String memberList(HttpSession session, Model model, String pageNum)throws Exception {
		String id = (String)session.getAttribute("memId");
		if(pageNum == null) {pageNum ="1";}
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		List articleList = null;
		count = lhcMemberDAO.getArticleMember();
		
		
		number = count - (currentPage-1) * pageSize;
		
		// 블랙박스 테스트
		System.out.println("LhcBoardBean.java에서 count는? " + count);
		System.out.println("LhcBoardBean.java에서 number는? " + number);
		System.out.println("LhcBoardBean.java에서 currentPage는? " + currentPage);
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("count", count);
		model.addAttribute("articleList", articleList);
		model.addAttribute("number", number);
		if(id.equals("admin")) {
			System.out.println("어드민 입니다.");
			System.out.println(count);
			
			if(count > 0) {
				// articleList = lhcMemberDAO.selectAll(startRow, endRow);
			}else {
				// articleList = Collections.EMPTY_LIST;
			}
			
		}
		
		return "lhcAdmin/lhcMemberList";
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
