package lhc.controller.bean;

import java.io.File;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lhc.model.dao.LhcPcDAO;
import lhc.model.dao.LhcPcDAOImpl;
import lhc.model.dao.LhcPcSpecDAOImpl;
import lhc.model.vo.LhcPcSpecVO;
import lhc.model.vo.LhcPcVO;


@Controller
@RequestMapping("/lhcPcSpec/")

public class LhcPcSpecBean {

	@Autowired
	private LhcPcSpecDAOImpl lhcPcSpecDAO=null;
	@Autowired
	private LhcPcDAOImpl lhcPcDAO=null;
	
	//사양 등록
	@RequestMapping("lhcPcSpecRegister.lhc")
	public String lhcPcSpecRegister(HttpSession session, Model model) throws Exception{
		String id = (String)session.getAttribute("memId");
		LhcPcVO vo = lhcPcDAO.getPc(id);
		model.addAttribute("vo", vo);
		return "lhcPcSpec/lhcPcSpecRegister";
	}
	
	//사양 등록 pro
	@RequestMapping("lhcPcSpecRegisterPro.lhc")
	public String lhcPcSpecRegisterPro(LhcPcSpecVO vo) throws Exception{
		
		//System.out.println(vo.getLhc_num()+"vo.getLhc_num()");
		
		lhcPcSpecDAO.insertPcSpec(vo);
		
		return "lhcPc/lhcPcListAll"; 
	}
	
	//사양 content
	@RequestMapping("lhcPcSpecContent.lhc")
	public String lhcPcSpecContent(LhcPcSpecVO vo, Model model) throws Exception {
		
		String lhc_name = vo.getLhc_name();
		//System.out.println(lhc_name+"lhc_name은 pc방 이름인데...");
		LhcPcSpecVO spec = lhcPcSpecDAO.getPcSpec(lhc_name);
		model.addAttribute("spec", spec);
		
		return "lhcPcSpec/lhcPcSpecContent";
	}
}
