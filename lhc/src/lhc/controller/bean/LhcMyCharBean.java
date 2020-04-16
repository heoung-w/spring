package lhc.controller.bean;

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

import lhc.model.dao.LhcMemberDAOImpl;
import lhc.model.dao.LhcMyCharDAOImpl;
import lhc.model.dao.LhcPcDAOImpl;
import lhc.model.vo.LhcMemberVO;
import lhc.model.vo.LhcPcVO;

@Controller
@RequestMapping("/lhcMyChar/")
public class LhcMyCharBean {
	
	@Autowired
	private LhcMyCharDAOImpl lhcMyCharDAO = null;
	@Autowired
	private LhcMemberDAOImpl lhcMemberDAO = null;
	@Autowired
	private LhcPcDAOImpl lhcPcDAO = null;
	
	public HttpSession session;
	public HttpServletRequest request;
	public HttpServletResponse response;
	public Model model;
	
	@ModelAttribute
	public void info(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		this.session = session;
		this.request = request;
		this.response = response;
		this.model = model;
	}
	
	// 예약 내역 리스트
	@RequestMapping("lhcAllChar.lhc")
	public String lhcAllChar(String pageNum) throws Exception{
		String id = (String)session.getAttribute("memId");
		LhcMemberVO memberVO = lhcMemberDAO.selectMember(id);
		String sep = memberVO.getLhc_sep();
		 
		List charList = null;
		if(pageNum == null) {
			pageNum ="1";
		}
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		number = count - (currentPage-1) * pageSize;
		
		// 관리자
		if(sep.equals("관리자")) {
			System.out.println("관리자입니다");
			count = lhcMyCharDAO.selectAllCount();
			number = count - (currentPage-1) * pageSize;
			if(count > 0) {
				charList = lhcMyCharDAO.selectAllChar(startRow, endRow);
			}else {
				charList = Collections.EMPTY_LIST;
			}
			model.addAttribute("number", number);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("startRow", startRow);
			model.addAttribute("endRow", endRow);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("count", count);
			model.addAttribute("charList", charList);
			return "lhcAdmin/lhcAllChar";
			
		// 업주
		}else if(sep.equals("v")) {
			System.out.println("업주입니다");
			int num = lhcPcDAO.selectOnePcChar(id);
			System.out.println("num은" + num);
			count = lhcMyCharDAO.selectVendorCount(num);
			System.out.println("count는" + count);
			number = count - (currentPage-1) * pageSize;
			if(count > 0) {
				charList = lhcMyCharDAO.selectVendorChar(startRow,endRow,num);
			}else {
				charList = Collections.EMPTY_LIST;
			}
			model.addAttribute("number", number);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("startRow", startRow);
			model.addAttribute("endRow", endRow);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("count", count);
			model.addAttribute("charList", charList);
			return "lhcVendor/lhcVendorChar";
					
		// 회원, 비로그인
		}else {
			System.out.println("회원입니다");
			count = lhcMyCharDAO.selectMyCount(id);
			number = count - (currentPage-1) * pageSize;
			if(count > 0) {
				charList = lhcMyCharDAO.selectMyChar(startRow,endRow,id);
			}else {
				charList = Collections.EMPTY_LIST;
			}
		}
		
		System.out.println(charList);
		model.addAttribute("number", number);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("count", count);
		model.addAttribute("charList", charList);
		
		return "lhcMember/lhcMyChar";
	}
	
	
	

}
