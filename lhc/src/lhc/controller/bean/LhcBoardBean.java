package lhc.controller.bean;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lhc.model.dao.LhcBoardDAOImpl;
import lhc.model.vo.LhcBoardVO;


@Controller
@RequestMapping("/lhcBoard/")
public class LhcBoardBean {
	
	@Autowired
	private LhcBoardDAOImpl lhcBoardDAO = null;
	
	@RequestMapping("lhcList.lhc")
	public String lhcList(String pageNum, Model model) throws Exception {
		
		if(pageNum == null) {pageNum ="1";}
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		List articleList = null;
		count = lhcBoardDAO.getArticleCount();
		
		if(count > 0) {
			articleList = lhcBoardDAO.getArticles(startRow, endRow);
		}else {
			articleList = Collections.EMPTY_LIST;
		}
		
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
		
		return "lhcBoard/lhcList";
	}
	
	@RequestMapping("lhcWriteForm.lhc")
	public String lhcWriteForm(@ModelAttribute("lhc") LhcBoardVO vo) {
		
		return "lhcBoard/lhcWriteForm";
	}
	
	@RequestMapping("lhcWritePro.lhc")
	public String lhcWritePro(LhcBoardVO vo) throws Exception {
		lhcBoardDAO.insertArticle(vo); 
		return "lhcBoard/lhcWritePro";
	}
	
	@RequestMapping("lhcContent.lhc")
	public String lhcContent(int lhc_num, int pageNum, Model model) throws Exception {
		LhcBoardVO article = lhcBoardDAO.getArticle(lhc_num);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("article", article);
		
		return "lhcBoard/lhcContent";
	}
	
	@RequestMapping("lhcModifyForm.lhc")
	public String lhcModifyForm(int lhc_num, int pageNum, Model model) throws Exception {
		LhcBoardVO article = lhcBoardDAO.getArticleForUpdate(lhc_num);
		model.addAttribute("article", article);
		model.addAttribute("pageNum", pageNum);
		return "lhcBoard/lhcModifyForm";
	}
	
	@RequestMapping("lhcModifyPro.lhc")
	public String lhcModifyPro(LhcBoardVO lhc_article) throws Exception {
		lhcBoardDAO.updateArticle(lhc_article);
		
		return "lhcBoard/lhcModifyPro";
	}
	
	
	@RequestMapping("lhcDeletePro.lhc")
	public String lhcDeletePro(int lhc_num, String pageNum, Model model) throws Exception {
		System.out.println("lhcDeletePro.lhc에서 int인 lhc_num은 뭐냐? " + lhc_num);
		lhcBoardDAO.deleteArticle(lhc_num);
		
		return "lhcBoard/lhcDeletePro";
	}
}
