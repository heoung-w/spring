package board.controller.bean;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import board.model.dao.BoardDAOImpl;
import board.model.vo.BoardVO;

@Controller
@RequestMapping("/board/")
public class BoardBean {
	// 실제 작업은 이렇게 하는데 우리는 service빼고 !!
	// Controller -> XxxService(i) -> XxxServiceImpl -> XxxDAO -> XxxDAOImpl -> SQL.xml -> DB

	@Autowired
	private BoardDAOImpl boardDAO = null;
	
	// 리스트 페이지 처리
	@RequestMapping("list.do")
	public String list(String pageNum, Model model) throws Exception{
		
		if(pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage -1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		
		List articleList = null;
		count = boardDAO.getArticleCount();
		if(count > 0) {
			articleList = boardDAO.getArticles(startRow, endRow);
		}else {
			articleList = Collections.EMPTY_LIST;	// null 대신 처리
		}
		int number = count - (currentPage-1) * pageSize;
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("count", count);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("articleList", articleList);
		model.addAttribute("number", number);
		
		return"board/list";
	}
	
	
	@RequestMapping("writeForm.do")
	public String writeForm(HttpServletRequest request, BoardVO vo) {
		int num = vo.getNum();
		int readcount = 0;
		int ref = 1;
		int re_step = 0;
		int re_level = 0;
		
		if(request.getAttribute("num") != null) {
			readcount = (Integer)request.getAttribute("readcount");
			ref = (Integer)request.getAttribute("ref");
			re_step = (Integer)request.getAttribute("re_step");
			re_level = (Integer)request.getAttribute("re_level");
		}
		
		request.setAttribute("num", num);
		request.setAttribute("readcount", readcount);
		request.setAttribute("ref", ref);
		request.setAttribute("re_step", re_step);
		request.setAttribute("re_level", re_level);
		   
		return "board/writeForm";
	}
	
	@RequestMapping("writePro.do")
	public String writePro(BoardVO vo, HttpServletResponse res) throws Exception{
		
		String path = "board/list";
		int num = vo.getNum();
		int check = (Integer)boardDAO.insertArticle(vo);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out=res.getWriter();
		if(check == 1) {
			out.println("<script>");
			out.println("alert('글이 작성되었습니다');");
			out.println("window.location.href='/spring/board/list.do'");
			out.println("</script>");
			path = "board/content";
		}

		return path;
	}
	
	@RequestMapping("content.do")
	public String content(int num, int pageNum, Model model) throws Exception {
		BoardVO vo = null;

		if(num >= 0) {
			vo = boardDAO.getArticle(num);
		}
		model.addAttribute("vo", vo);
		model.addAttribute("pageNum", pageNum);
		
		return "board/content";
	}
	
	@RequestMapping("modifyForm.do")
	public String modifyForm(int num, Model model, String pageNum) throws Exception {
		BoardVO vo = null;
		if(num >= 0) {
			vo = boardDAO.getArticleForUpdate(num);
		}
		model.addAttribute("num", num);
		model.addAttribute("vo", vo);
		model.addAttribute("pageNum", Integer.parseInt(pageNum));
		
		return "board/modifyForm";
	}
	
	@RequestMapping("modifyPro.do")
	public String modifyPro(int num, Model model, BoardVO vo, String pageNum) throws Exception {
		int check = 0;
		if(num >= 0) {
			check = boardDAO.updateArticle(vo);
		}
		model.addAttribute("num", num);
		model.addAttribute("check", check);
		model.addAttribute("pageNum", Integer.parseInt(pageNum));
		
		return "board/modifyPro";
	}
	
	@RequestMapping("deleteForm.do")
	public String deleteForm(int num, String pageNum, Model model) {
		
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
		return "board/deleteForm";
	}
	
	@RequestMapping("deletePro.do")
	public String deletePro(int num, String pw, String pageNum, Model model) throws Exception {
		int check = boardDAO.deleteArticle(num, pw);
		model.addAttribute("check", check);
		model.addAttribute("pageNum", pageNum);
		
		return "board/deletePro";
	}
	
	
	
}
