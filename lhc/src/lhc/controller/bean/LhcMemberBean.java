package lhc.controller.bean;

import java.io.IOError;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
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
import lhc.model.dao.LhcPcDAO;
import lhc.model.dao.LhcPcDAOImpl;
import lhc.model.vo.LhcMemberVO;
import lhc.model.vo.LhcPcVO;


@Controller
@RequestMapping("/lhcMember/")
public class LhcMemberBean {
	
	@Autowired
	private LhcMemberDAOImpl lhcMemberDAO = null;
	// DI,IOC,AOP
	@Autowired
	private LhcPcDAOImpl lhcPcDAO=null;
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
		//System.out.println(uri);
		uri = uri.substring(5, uri.length() - 4);
		return uri;
	}
	
	@RequestMapping("lhcMain.lhc")
	public String lhcMain(HttpSession session, String pageNum, Model model) throws Exception{
		
		if(session.getAttribute("memId")!=null) {
		//회원 한 명의 모든 정보 꺼내오기(내 주변 pc방 지도 구현)
		String lhc_id = (String)session.getAttribute("memId");
		LhcMemberVO member = lhcMemberDAO.selectMember(lhc_id);			
		
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
		
		LhcPcVO pc = lhcPcDAO.getPc(lhc_id);

		if(lhc_id.equals("admin")) { //관리자 위치 띄우는 main	
			model.addAttribute("member", member);			
			return "lhcAdmin/lhcMain";
		}else if(!lhc_id.equals("admin")) {
			if(member.getLhc_sep().equals("v")) { // 업주의 pc방 위치 띄우는 main				
				state = "b";
				model.addAttribute("pc", pc);
				model.addAttribute("state", state);
				model.addAttribute("pageNum", pageNum);
				model.addAttribute("member", member);
				return "lhcVendor/lhcMain";		
			}	
		}
		model.addAttribute("member", member); // 내 주변 pc할라고 회원 정보 LhcMemberVO로 담아서 넘겨줌.
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("count", count);
		model.addAttribute("pcList", pcList);
		model.addAttribute("state", state);
		//model.addAttribute("number", number);
		}
		return "lhcMember/lhcMain";
		
	}
	

	// 아이디 중복 체크 하는 메서드
	@RequestMapping("lhcAjaxIdAvail.lhc") public ResponseEntity<String>
	lhcAjaxIdAvail(String lhc_id) throws Exception {//ResponseEntity=>응답해주는 객체들 타입 정할 수 있음 
		String result="";
	  
		int idcheck=(Integer)lhcMemberDAO.idAvailCheck(lhc_id);
		  
		if(idcheck==1) { 
			result="이미 존재하는 id"; 
		}else if(idcheck==0){
			result="사용 가능한 id"; 
		} 
		//System.out.println(result); 
		HttpHeaders responseHeader=new HttpHeaders();//헤더 객체를 만들어
		responseHeader.add("Content-Type", "text/html;charset=utf-8");//헤더 정보 추가
		return new ResponseEntity<String>(result, responseHeader, HttpStatus.CREATED); 
	}

	// 회원가입
	@RequestMapping("lhcSignupForm.lhc")
	public String signup(HttpServletRequest request, HttpServletResponse response, LhcMemberVO vo, String lhc_addr, String lhc_addr2) throws Exception {
		//System.out.println(request.getMethod());
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
		PrintWriter out = null;
		response.setContentType("text/html;charset=utf-8");
		out=response.getWriter();
		int check = lhcMemberDAO.idPwCheck(vo);
		//System.out.println(check);
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
	public String deleteForm(HttpSession session,String pw, Model model, HttpServletResponse response) throws Exception {
		PrintWriter out = null;
		response.setContentType("text/html;charset=utf-8");
		out=response.getWriter();
		if(pw==null) {
			out.write("<script>");
			out.write("var userInput = prompt(\"당신의 패스워드는 무엇입니까?\"+\"\");");
			out.write("window.location.href='lhcDeleteForm.lhc?pw='+userInput");
			out.write("</script>");
			pw="0";
		}
		String id = (String)session.getAttribute("memId");
		String pw2=lhcMemberDAO.idPwCheck2((String)session.getAttribute("memId"));
		//System.out.println(pw);
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
	public String mypage(HttpSession session, LhcMemberVO vo, Model model) throws Exception{
		
		String pageNum = null;
		if(pageNum == null) {pageNum ="1";}
		
		String id = (String)session.getAttribute("memId");
		vo = lhcMemberDAO.selectMember(id);
		String sep = vo.getLhc_sep();
		LhcPcVO pc = lhcPcDAO.getPc(id);
		String state = "a";
		if(id.equals("admin")) {
			return "lhcAdmin/lhcMyPage";
			
		}else if(!id.equals("admin")) {
			
			if(vo.getLhc_sep().equals("v")) {
				model.addAttribute("pageNum", pageNum);
				model.addAttribute("state", state);
				model.addAttribute("pc", pc);
				model.addAttribute("sep", sep);
				return "lhcVendor/lhcMyPage";
			}
		}		
		return "lhcMember/lhcMyPage";
	}
	
	
	// 회원 리스트 ( 관리자 , 업주 ) 분리
	@RequestMapping("lhcMemberList.lhc")
	public String memberList(HttpSession session, Model model, String pageNum, String sep)throws Exception {
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
			return "lhcAdmin/lhcVenderList";
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


	//# 1 , 2. 찜 하는 
	@RequestMapping("lhcMyFavorite.lhc")
	public String lhcMyFavorite(String state, String pageNum, String lhc_id, String lhc_num, Model model, HttpSession session)throws Exception{
		String id = (String)session.getAttribute("memId");
		//lhc_id ==> 찜 누른 회원 id // lhc_num ==> 찜 당한 pc방 num.
		
		// #1. 찜 리스트 select하는 sql 
		String fav = (String)lhcMemberDAO.selectFav(lhc_id);
		//System.out.println(fav+"   fav!");
		
		LhcMemberVO vo = new LhcMemberVO();
		String num = null;
		if(fav == null) {	//찜 컬럼에 아무것도 없으면,
			num = ","+ lhc_num + ","; //번호 앞에 ,를 넣어주고 시작		
			// #2. 찜 넣어주는 sql
			lhcMemberDAO.updateFav(num, lhc_id);
		}else{						
			String [] fav_list = fav.split(",");
			int already = 0;
			for(int i=1; i<fav_list.length; i++) {
				String favOne = fav_list[i];
				//System.out.println(favOne+"   => favOne");
				//System.out.println(lhc_num+"lhc_num.................");
				if(favOne.equals(lhc_num)) {
					//System.out.println(favOne+"  favOne은 ");
					//System.out.println(lhc_num+"  lhc_num이랑 같다?");
					already += 1;
				}
			}
			//System.out.println(already+"   already 개");
			if(already == 0) {
				num = fav + lhc_num + ","; 	
				// #2. 찜 넣어주는 sql
				lhcMemberDAO.updateFav(num, lhc_id);
			}
		}
		//System.out.println(num+"    lhcMyFavorite매핑에서 num");
		LhcPcVO pc = lhcPcDAO.getPc(Integer.parseInt(lhc_num));
		String likeNum = lhcMemberDAO.getLikeNum(id);
		String[] likeNumber = null;
		likeNumber = likeNum.split(",");
		boolean check = false;
		for(int i = 0; i < likeNumber.length; i++) {
			if(likeNumber[i].equals(lhc_num)) {
				check = true;
				break;
			}
		}
		
		model.addAttribute("check", check);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pc", pc);
		model.addAttribute("state", state);
		
		return "lhcPc/lhcPcContent";
	} 
	
	// #3. 찜 보여주는
	@SuppressWarnings("unchecked")
	@RequestMapping("lhcMyFavoriteListForm.lhc")
	public String lhcMyFavoriteForm(HttpSession session, Model model)throws Exception {
		
		String lhc_id = (String)session.getAttribute("memId");
		
		LhcMemberVO vo = new LhcMemberVO();
		String fav = lhcMemberDAO.selectFav(lhc_id);
		
		List<LhcPcVO> favList = new ArrayList<LhcPcVO>();

		String [] fav_list = fav.split(",");
		//System.out.println(fav_list+"  fav_list");
		//System.out.println(fav_list.length-1+"fav_list.length");
		//int count = fav_list.length-1;
		
		for(int i=1; i<fav_list.length;i++) {
			String favOne = fav_list[i];
			//System.out.println(favOne+" -----------favOne");
			favList.addAll((List<LhcPcVO>)lhcPcDAO.getPc1(favOne));			
		}
		
		//System.out.println(favList+"     favList");
		model.addAttribute("favList", favList);
		
		return "lhcMember/lhcMyFavoriteListForm";
	}
	
	//각 pc방 찜한 회원 찾는 페이지
	@SuppressWarnings("unchecked")
	@RequestMapping("lhcCustomerFavList.lhc")
	public String lhcCustomerFavList(HttpSession session, Model model)throws Exception{
			
		// #1. 각 업주의 pc방 고유 번호를 받음.
		String id = (String)session.getAttribute("memId");
		LhcPcVO pc = lhcPcDAO.getPc(id);
		int num = pc.getLhc_num(); // pc방 고유 번호

		// #2. 모든 회원들의 리스트를 뽑은 후에, 각 회원의 lhc_favorite 컬럼을 가져오고, 그 컬럼의 pc방 고유번호를 split해서 favOne에 담아		
		String pageNum = null;
		String sep="c";
		if(pageNum == null) {pageNum ="1";}
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		List memberList = null;
		LhcMemberVO vo = new LhcMemberVO();
		int count = 0;
		int number = 0;
		count = lhcMemberDAO.getCountMember(sep);
		number = count - (currentPage-1) * pageSize;
		
		//찜 회원 리스트 뿌리기
		List<LhcMemberVO> member = new ArrayList<LhcMemberVO>();
		//System.out.println(member+"    member 리스트");
		if(count > 0) {
			memberList = lhcMemberDAO.selectAll(startRow, endRow, sep);
			for(int i = 0; i < memberList.size(); i++) {
				vo = (LhcMemberVO)memberList.get(i);
				// 회원의 id로 lhc_favorite 컬럼을 뽑아서
				String lhc_id = vo.getLhc_id();
				//System.out.println(lhc_id + "   lhc_id ");
				String fav = (String)lhcMemberDAO.selectFav(lhc_id);
				//System.out.println(fav + "   fav");
				
				String [] fav_list = fav.split(","); 
				
				for(int j=1; j<fav_list.length; j++) {
					int favOne = Integer.parseInt(fav_list[j]);
					//System.out.println(favOne+"  favOne");
					
					// # 3. 업주의 pc방 고유 번호와 하나씩 비교해서
					// 	    pc방 고유번호와 같은 번호만 회원정보를 빼와서 리스트에 넣어줌  
					if(num == favOne) {
						//System.out.println("num은 ~~~~~ "+num);
						//System.out.println("if문 안에서 lhc_id는   "+lhc_id);
						member.addAll((List<LhcMemberVO>)lhcMemberDAO.selectMember1(lhc_id));
						model.addAttribute("member", member);
					}					
				}			
			 }
		}else{
				member = Collections.EMPTY_LIST;
		}
		
		return "lhcVendor/lhcCustomerFavList";
	}
	
	// 좋아요
		@RequestMapping("lhcLikeCheck.lhc")
		@ResponseBody
		public String lhcLike(HttpSession session,HttpServletResponse response, int num)throws Exception{
			JSONObject json=new JSONObject();
			String id = (String)session.getAttribute("memId");
			String likeNum = null;
			String [] likeNumber = null;
			String newLike = null;
			likeNum = lhcMemberDAO.getLikeNum(id);
			ArrayList<String> list = new ArrayList<String>();
			list.add(0, Integer.toString(num));
			if(likeNum == null) { // 없으면 추가 해라
				newLike = String.join(",", list);
				lhcMemberDAO.updateLikeNum(id, newLike);
				lhcPcDAO.updateLikeCount(num);
				json.put("check", true);
			}else {
				likeNumber = likeNum.split(",");
				lhcPcDAO.updateLikeCount(num);
				json.put("check", true);
				for(int i = 0; i < likeNumber.length; i++) {
					if(!likeNumber[i].equals(Integer.toString(num))) {
						list.add(likeNumber[i]);
					}else {
						json.put("check", false);
						list.remove(likeNumber[i]);
						lhcPcDAO.downLikeCount(num);
					}
				}
				newLike = String.join(",", list);
				lhcMemberDAO.updateLikeNum(id, newLike);
			}
			String volist=lhcMemberDAO.getLikeNum(id);
			System.out.println(list);
			String likeCountdo = Integer.toString(lhcPcDAO.getLikeCount(num));
			json.put("likeCountdo", likeCountdo);
			
//			response.getWriter().print(likeCountdo);
			return json.toString();
		}
}

