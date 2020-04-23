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

import lhc.model.dao.LhcPcDAOImpl;
import lhc.model.dao.LhcPcMenuDAOImpl;
import lhc.model.vo.LhcPcMenuVO;
import lhc.model.vo.LhcPcVO;

@Controller
@RequestMapping("/lhcPcMenu/")

public class LhcPcMenuBean {

	@Autowired
	private LhcPcMenuDAOImpl lhcPcMenuDAO=null;
	@Autowired
	private LhcPcDAOImpl lhcPcDAO=null;
	//메뉴 등록
	@RequestMapping("lhcPcMenuRegister.lhc")
	public String lhcPcMenuRegister(HttpSession session, Model model)throws Exception {
		
		String id = (String)session.getAttribute("memId");
		LhcPcVO vo = lhcPcDAO.getPc(id);
		model.addAttribute("vo", vo);
		
		return "lhcPcMenu/lhcPcMenuRegister";
	}
	
	//메뉴 등록 pro
	@RequestMapping("lhcPcMenuRegisterPro.lhc")
	public String lhcPcMenuRegisterPro(HttpServletRequest request, MultipartHttpServletRequest request1) throws Exception{
		
		String newName=null;
		MultipartFile mf=null;
		
		mf=request1.getFile("lhc_img");
		String orgName=mf.getOriginalFilename();
		String imgName=orgName.substring(0, orgName.indexOf('.'));
		String ext=orgName.substring(orgName.indexOf('.'));
		long date=System.currentTimeMillis();
		newName=imgName+date+ext;
		
		String path=request1.getRealPath("lhcSave");
		String imgPath=path+"\\"+newName;
		File copyFile=new File(imgPath);
		mf.transferTo(copyFile);
		
		LhcPcMenuVO vo=new LhcPcMenuVO();
		
		//System.out.println(vo.getLhc_menum()+"@@@vo.setLhc_menum");
		
		vo.setLhc_num(Integer.parseInt(request.getParameter("lhc_num")));
		vo.setLhc_img(newName);		
		vo.setLhc_name(request.getParameter("lhc_name"));
		vo.setLhc_price(Integer.parseInt(request.getParameter("lhc_price")));
		
		lhcPcMenuDAO.insertPcMenu(vo);
		
		return "lhcPc/lhcPcListAll"; 
	}
	
	//메뉴 content
	@RequestMapping("lhcPcMenuContent.lhc")
	public String lhcPcContent(int lhc_num, Model model) throws Exception {
		
		//System.out.println(lhc_num+"pcMenuContent의 lhc_num");
		
		LhcPcMenuVO menu = lhcPcMenuDAO.getPcMenu(lhc_num);
		model.addAttribute("menu", menu);
		
		return "lhcPcMenu/lhcPcMenuContent";
	}
}
