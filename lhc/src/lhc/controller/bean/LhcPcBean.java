package lhc.controller.bean;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lhc.model.dao.LhcMemberDAOImpl;
import lhc.model.dao.LhcMyCharDAOImpl;
import lhc.model.dao.LhcPcDAOImpl;
import lhc.model.vo.LhcMemberVO;
import lhc.model.vo.LhcPcVO;

@Controller
@RequestMapping("/lhcPc/")

public class LhcPcBean {
	
	@Autowired
	private LhcPcDAOImpl lhcPcDAO=null;
	public HttpServletRequest request;
	public HttpServletResponse response;
	public HttpSession session;
	public Model model;
	@Autowired
	private LhcMemberDAOImpl lhcMemberDAO=null;
	@Autowired
	private LhcMyCharDAOImpl lhcMyCharDAO=null;
	
	@ModelAttribute
	public void info(HttpServletRequest request,HttpServletResponse response,Model model,HttpSession session){
		this.request=request;
		this.model=model;
		this.session=session;
		this.response=response;
	}
	
	@RequestMapping("lhcPcRegisterForm.lhc")
	public String lhcPcRegisterForm() {
		
		return "lhcPc/lhcPcRegisterForm";
	}
	
	// pc 등록
	@RequestMapping("lhcPcRegisterPro.lhc")
	public String lhcPcRegisterPro(HttpServletResponse response, MultipartHttpServletRequest request1, String lhc_state,Model model) throws Exception{
		
		String id = (String)session.getAttribute("memId");
		LhcPcVO vo = new LhcPcVO();
		String newName= null;
		MultipartFile mf = null;
		mf= request1.getFile("lhc_img");
		String orgName=mf.getOriginalFilename();
		String imgName=orgName.substring(0, orgName.indexOf('.'));
		String ext=orgName.substring(orgName.indexOf('.'));
		long date=System.currentTimeMillis();
		newName=imgName+date+ext;

		String path=request1.getRealPath("lhcSave");
		String imgPath=path+"\\"+ newName;
		File copyFile=new File(imgPath);
		mf.transferTo(copyFile);
		
		vo.setLhc_name(request.getParameter("lhc_name"));
		vo.setLhc_img(newName);
		vo.setLhc_phone(request.getParameter("lhc_phone"));		
		vo.setLhc_addr(request.getParameter("lhc_addr")); 		
		vo.setLhc_info(request.getParameter("lhc_info"));
		vo.setLhc_vendor(id);
		vo.setLhc_seats(Integer.parseInt(request.getParameter("lhc_seats")));
		//System.out.println(request.getParameter("lhc_lat")+" =====> lhc_lat");
		vo.setLhc_lat(request.getParameter("lhc_lat"));
		vo.setLhc_lng(request.getParameter("lhc_lng"));
		vo.setLhc_state(lhc_state);
	

		//근데 여기부터는 Spring에서 알아서 예외처리를 해 줌. 따라서 내가 할 예외처리는 없음. 
		lhcPcDAO.insertPc(vo);
		return "lhcPc/lhcPcListAll";
	}
	
	// 사이트에 등록된 pc정보 가져오기
	@RequestMapping("lhcPcListAll.lhc")
	public String lhcPcListAll(String pageNum)throws Exception{
		
		if(pageNum == null) {
			pageNum ="1";
		}
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		String state = "a";
		LhcPcVO vo = new LhcPcVO();
		List pcList = null;
		count = lhcPcDAO.getResPcCount(state);
		number = count - (currentPage-1) * pageSize;
		//#검색 목록
		String sel = request.getParameter("sel");
		String search = request.getParameter("search");
		
		if(sel != null && search != null) {
			 count = lhcPcDAO.getSearchPcCount(sel,search,state);	//  검색된 글 수
			 if(count > 0){
				 pcList = lhcPcDAO.getSearchPcs(startRow, endRow, sel, search, state); //  검색된 글 리스트받기
			 }
		}else{
			// # 일반 전체 목록 
			count = lhcPcDAO.getResPcCount(state);		
			if(count > 0) {						
				pcList = lhcPcDAO.getPcs(startRow, endRow, state);	
			}else{
				pcList = Collections.EMPTY_LIST;	// null 대신 처리
			}
		}		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("count", count);
		model.addAttribute("pcList", pcList);
		model.addAttribute("number", number);
		
		return "lhcPc/lhcPcListAll";
	}
	  
	// 등록전 pc 정보 가져오기(관리자)
	@RequestMapping("lhcStatePcList.lhc")
	public String lhcPcState(String pageNum, Model model)throws Exception{
		
		if(pageNum == null) {
			pageNum ="1";
		}
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		List pcList = null;
		LhcPcVO vo = new LhcPcVO();
		String state = "b";
		count = lhcPcDAO.getBeforePcCount(state);
		number = count - (currentPage-1) * pageSize;
		// # 등록 전 전체 목록 
		count = lhcPcDAO.getBeforePcCount(state);		
		if(count > 0) {						
			pcList = lhcPcDAO.getPcs(startRow, endRow, state);	
		}else{
			pcList = Collections.EMPTY_LIST;	// null 대신 처리
		}
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("count", count);
		model.addAttribute("pcList", pcList);
		model.addAttribute("number", number);
		
		return "lhcAdmin/lhcStatePcList";
	}
	
	// 관리자와, 업주, 회원 보는페이지 분리
	@RequestMapping("lhcPcContent.lhc")
	public String lhcPcContent(int lhc_num, String pageNum, String state) throws Exception {
		
		if(pageNum == null) {pageNum ="1";}
		LhcPcVO pc = lhcPcDAO.getPc(lhc_num);
		
		if(session.getAttribute("memId")==null) {
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("pc", pc);
		    return "lhcPc/lhcPcContent";
		}else{
			String id = (String)session.getAttribute("memId");
			String vendor = pc.getLhc_vendor();
			//System.out.println(pc);
			LhcMemberVO vo = new LhcMemberVO();
			vo = (LhcMemberVO)lhcMemberDAO.selectMember(id);
			//System.out.println(vo.getLhc_sep() + "    sep");			
			if(id.equals("admin") || id.equals(vendor)) {
				model.addAttribute("state", state);
				model.addAttribute("pageNum", pageNum);
				model.addAttribute("pc", pc);
				return "lhcAdmin/lhcStateContent";
			}else {
				String likeNum = lhcMemberDAO.getLikeNum(id);
				boolean check = false;
				if(likeNum != null) {
					String[] likeNumber = null;
					String lhcnum = Integer.toString(lhc_num);
					likeNumber = likeNum.split(",");
					for(int i = 0; i < likeNumber.length; i++) {
						if(likeNumber[i].equals(lhcnum)) {
							check = true;
							break;
						}
					}
				}
				model.addAttribute("pageNum", pageNum);
				model.addAttribute("pc", pc);
				model.addAttribute("lhc_id", id);				
				model.addAttribute("vo", vo);
				model.addAttribute("check", check);
				return "lhcPc/lhcPcContent";
				}
		}
	}
	
	/*
	 * // 관리자와, 업주, 회원 보는페이지 분리
	 * 
	 * @RequestMapping("lhcPcContent.lhc") public String lhcPcContent(int lhc_num,
	 * int pageNum,String state) throws Exception { LhcPcVO pc =
	 * lhcPcDAO.getPc(lhc_num); String id = (String)session.getAttribute("memId");
	 * String vendor = pc.getLhc_vendor(); System.out.println(pc);
	 * if(id.equals("admin") || id.equals(vendor)) { model.addAttribute("state",
	 * state); model.addAttribute("pageNum", pageNum); model.addAttribute("pc", pc);
	 * 
	 * return "lhcAdmin/lhcStateContent"; } String likeNum =
	 * lhcMemberDAO.getLikeNum(id); String[] likeNumber = null; String lhcnum =
	 * Integer.toString(lhc_num); likeNumber = likeNum.split(","); boolean check =
	 * false; for(int i = 0; i < likeNumber.length; i++) {
	 * if(likeNumber[i].equals(lhcnum)) { check = true;
	 * System.out.println("for문~~~ >> "+check); break; } }
	 * System.out.println("하트 결과 "+check); model.addAttribute("pageNum", pageNum);
	 * model.addAttribute("pc", pc); model.addAttribute("check", check);
	 * 
	 * //세션으로 아이디체크한다음에, 좋아요리스트 뽑아서 //model.addAttribute("like", "1"); //0
	 * 
	 * return "lhcPc/lhcPcContent"; }
	 */
	
	@RequestMapping("lhcPcAdRegisterPro.lhc")
	public void lhcPcAdRegisterPro(int num, String state) throws Exception {
		if(state.equals("b")) {
			lhcPcDAO.statePc(num);
		}
		response.sendRedirect("lhcStatePcList.lhc");
		//return "lhcAdmin/lhcStatePcList";
	}
	
	// 관리자, 업주가 삭제 할경우
	@RequestMapping("lhcPcDelete.lhc")
	public void lhcPcDelete(int num) throws Exception{
		if(num != 0) {
			lhcPcDAO.pcDelete(num);
		}
		response.sendRedirect("lhcMember/lhcMain.lhc");
	}
	
	// 카테고리 페이지
	@RequestMapping("lhcPcListCat.lhc")
	public String lhcPclistCat() throws Exception{
		
		return "lhcPc/lhcPcListCat";
	}
	
	// 인기순
	@RequestMapping("lhcPcFavList.lhc")
	public String lhcPcFavList(String pageNum) throws Exception{
		if(pageNum == null) {
			pageNum ="1";
		}
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		int numnum = 1;
		String state = "a";
		count = lhcPcDAO.getResPcCount(state);
		number = count - (currentPage-1) * pageSize;
		List pcFavList = null;
		if(count > 0) {						
			pcFavList = lhcPcDAO.getFavPcs(startRow, endRow, state);	
		}else{
			pcFavList = Collections.EMPTY_LIST;	// null 대신 처리
		}		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("count", count);
		model.addAttribute("pcList", pcFavList);
		model.addAttribute("number", number);
		model.addAttribute("numnum", numnum);
		
		return "lhcPc/lhcPcFavList";
	}
	
	// 예약순
		@RequestMapping("lhcPcCharList.lhc")
		public String lhcPcCharList(String pageNum) throws Exception{
			if(pageNum == null) {
				pageNum ="1";
			}
			int pageSize = 10;
			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage - 1) * pageSize + 1;
			int endRow = currentPage * pageSize;
			int count = 0;
			int number = 0;
			int numnum = 1;
			String state = "a";
			count = lhcPcDAO.getResPcCount(state);
			number = count - (currentPage-1) * pageSize;
			List pcCharList = null;
			if(count > 0) {						
				pcCharList = lhcPcDAO.getCharPcs(startRow, endRow, state);	
			}else{
				pcCharList = Collections.EMPTY_LIST;	// null 대신 처리
			}
			
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("startRow", startRow);
			model.addAttribute("endRow", endRow);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("count", count);
			model.addAttribute("pcList", pcCharList);
			model.addAttribute("number", number);
			model.addAttribute("numnum", numnum);
			
			
			return "lhcPc/lhcPcCharList";
		}
		// 2020.04.20 추가 ( 매출 금액 토대로 list)
		@RequestMapping("lhcPcCatList.lhc")
		public String lhcPcCatList(HttpSession session, HttpServletRequest request,String pageNum, Model model) throws Exception {
			if (pageNum == null) {pageNum = "1";}
			int pageSize = 10;
			int currentPage = Integer.parseInt(pageNum);
			int startRow= (currentPage - 1) * pageSize+1;
			int endRow = currentPage * pageSize;
			int count = 0;
			int number = 0;
			
			List pcMoneyList = null;
			
			// #일반전체 목록 불러오기 
			count = lhcPcDAO.getPcCount();
			// 2020.04.20 PcDAO 인터페이스랑 implements에 추가
			if(count > 0) {pcMoneyList = lhcPcDAO.getPcsMoneyList(startRow, endRow);}
			
			// Data 보내기
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("startRow", startRow);
			model.addAttribute("endRow", endRow);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("count", count);
			model.addAttribute("pcMoneyList", pcMoneyList);
			model.addAttribute("number", number);
			
			return "lhcPc/lhcPcCatList";
		}
	
}
