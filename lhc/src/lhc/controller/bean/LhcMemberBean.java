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
import lhc.model.dao.LhcPcDAOImpl;
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
	
	public Model model;
	
	@ModelAttribute
	public void info(Model model) {
		this.model = model;
	}
	
	
	@Autowired
	private LhcMemberDAOImpl lhcMemberDAO = null;
	@Autowired
	private LhcPcDAOImpl lhcPcDAO = null;
	
	// DI,IOC,AOP

	// RequestMapping을 **로 받고 return null로 해주면
	// jsp 파일 이름과 요청한 uri 이름이 같은 곳을 알아서 찾아 가줌.

	/*
	 * @RequestMapping("l cMain.lhc") public String main(HttpServletResponse
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
	
	@RequestMapping("lhcMain.lhc")
	public String lhcMain(HttpSession session, String pageNum) throws Exception{
		
		if(session.getAttribute("memId")!=null) {
			//회원 한 명의 모든 정보 꺼내오기(내 주변 pc방 지도 구현)
			String lhc_id = (String)session.getAttribute("memId");
			LhcMemberVO member = lhcMemberDAO.selectMember(lhc_id);			
			model.addAttribute("member", member); // 내 주변 pc할라고 회원 정보 LhcMemberVO로 담아서 넘겨줌.
		}
		// pc방 리스트 뽑기(pc방 여러개 지도 구현)
		if(pageNum == null) {
			pageNum ="1";
		}
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		String state = "a";
		List pcList = null;
		
		count = lhcPcDAO.getResPcCount(state);		
		if(count > 0) {						
			pcList = lhcPcDAO.getPcs(startRow, endRow, state);	
		}else{
			pcList = Collections.EMPTY_LIST;	// null 대신 처리
		}
		//System.out.println(pcList+"pcList");
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("count", count);
		model.addAttribute("pcList", pcList);
		//model.addAttribute("number", number);
		
		return "lhcMember/lhcMain";
	}
	
	// 아이디 중복 체크 하는 메서드
	@RequestMapping("lhcAjaxIdAvail.lhc")
	public ResponseEntity<String> lhcAjaxIdAvail(String lhc_id) throws Exception {//ResponseEntity=>응답해주는 객체들 타입 정할 수 있음 
		String result="";
	  
		int idcheck=(Integer)lhcMemberDAO.idAvailCheck(lhc_id);
		  
		if(idcheck==1) { 
			result="이미 존재하는 id"; 
		}else if(idcheck==0){
			result="사용 가능한 id"; 
		} 
		HttpHeaders responseHeader=new HttpHeaders();//헤더 객체를 만들어
		responseHeader.add("Content-Type", "text/html;charset=utf-8");//헤더 정보 추가
		return new ResponseEntity<String>(result, responseHeader, HttpStatus.CREATED); 
	}

	// 회원가입
	@RequestMapping("lhcSignupForm.lhc")
	public String signup(HttpServletRequest request, HttpServletResponse response, LhcMemberVO vo, String lhc_addr, String lhc_addr2) throws Exception {
		System.out.println(request.getMethod());
		if (request.getMethod().equals("POST")) {
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
	public String deleteForm(HttpSession session,String pw, String sep) throws Exception {
		System.out.println(sep + "?? 업주냐 회원이냐");
		if(pw==null) {
			out.write("<script>");
			out.write("var userInput = prompt(\"당신의 패스워드는 무엇입니까?\"+\"\");");
			out.write("window.location.href='lhcDeleteForm.lhc?pw='+userInput");
			out.write("</script>");
			pw="0";
		}
		String id = (String)session.getAttribute("memId");
		String pw2=lhcMemberDAO.idPwCheck2((String)session.getAttribute("memId"));
		if(sep.equals("v")) {
			lhcPcDAO.deletePc(id);
		}
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
		String sep = vo.getLhc_sep();
		if(id.equals("admin")){
			return "lhcAdmin/lhcMyPage";
			
		}else if(!id.equals("admin")) {
			
			if(sep.equals("v")) {
				model.addAttribute("sep", sep);
				return "lhcVendor/lhcMyPage";
			}
		}		
		return "lhcMember/lhcMyPage";
	}
	
	
	// 회원 리스트 ( 관리자 , 업주 ) 분리
	@RequestMapping("lhcMemberList.lhc")
	public String memberList(HttpSession session, String pageNum, String sep)throws Exception {
		String id = (String)session.getAttribute("memId");
		if(pageNum == null) {pageNum ="1";}
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		List articleList = null;
		LhcMemberVO vo = new LhcMemberVO();
		int count = 0;
		int number = 0;
			
		count = lhcMemberDAO.getCountMember(sep);
		number = count - (currentPage-1) * pageSize;
		if(count > 0) {
			articleList = lhcMemberDAO.selectAll(startRow, endRow, sep);
			for(int i = 0; i < articleList.size(); i++) {
				vo = (LhcMemberVO)articleList.get(i);
			}
		}else{
			articleList = Collections.EMPTY_LIST;
		}
		model.addAttribute("articleList", articleList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("count", count);
		model.addAttribute("number", number);
		if(sep.equals("v")) {
			return "lhcAdmin/lhcVendorList";
		}else {
			return "lhcAdmin/lhcMemberList";
		}
	}
	
	//포인트 충전 Form
	@RequestMapping("lhcMyPointForm.lhc")
	public String lhcMyPointView(LhcMemberVO vo, HttpSession session, Model model) throws Exception{
		
		String lhc_id = (String)session.getAttribute("memId");	
		LhcMemberVO member = lhcMemberDAO.selectMember(lhc_id);	
		model.addAttribute("member", member);
		
		return "lhcMember/lhcMyPointForm";
	}
	
	//포인트 충전 팝업 창 Form 
	@RequestMapping("lhcMyPointChargeForm.lhc")
	public String lhcMyPointChargeForm(LhcMemberVO vo, HttpSession session, Model model)throws Exception {
		
		String lhc_id = (String)session.getAttribute("memId");	
		LhcMemberVO member = lhcMemberDAO.selectMember(lhc_id);		
		model.addAttribute("member", member);
		
		return "lhcMember/lhcMyPointChargeForm";
	}
	
	//포인트 충전 팝업 창 Pro
	@RequestMapping("lhcMyPointChargePro.lhc")
	public String lhcMyPointChargePro(HttpServletRequest request, LhcMemberVO vo, HttpSession session, Model model)throws Exception {
		
		String myPoint = request.getParameter("point");
		model.addAttribute("myPoint", myPoint);			
		
		String lhc_id = (String)session.getAttribute("memId");
		int lhc_money = Integer.parseInt(request.getParameter("point"));		
		int result = lhcMemberDAO.updatePoint(lhc_id, lhc_money);
		model.addAttribute("result", result);

		return "lhcMember/lhcMyPointChargePro";
	}
	
	// 좋아요
	@RequestMapping("lhcLikeCheck.lhc")
	@ResponseBody
	public void lhcLike(HttpSession session,HttpServletResponse response, int num)throws Exception{
		String id = (String)session.getAttribute("memId");
		String likeNum = null;
		String number = ","+ num;
		likeNum = lhcMemberDAO.likeCheck(id);
		System.out.println(likeNum + "좋아요 누른 pc번호");
		lhcMemberDAO.updateLikeNum(id, number);
		
		String aa=Integer.toString(lhcPcDAO.getLikeCount(num));
		response.getWriter().print(aa.toString());
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
