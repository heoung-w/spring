package lhc.controller.bean;

import java.io.File;
import java.util.ArrayList;
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
import lhc.model.vo.LhcPcVO;

@Controller
@RequestMapping("/lhcPc/")

public class LhcPcBean {

	@Autowired
	private LhcPcDAOImpl lhcPcDAO=null;
	
	@RequestMapping("lhcPcRegisterForm.lhc")
	public String lhcPcRegisterForm() {
		
		return "lhcMember/lhcPcRegisterForm";
	}
	
	@RequestMapping("lhcPcRegisterPro.lhc")
	public String lhcPcRegisterPro(HttpServletRequest request, MultipartHttpServletRequest request1) throws Exception{
		
		String newName= ",";
		List<MultipartFile> mf = request1.getFiles("lhc_img");
		LhcPcVO vo=new LhcPcVO(); 
		for(int i=0; i<mf.size();i++) {
			String orgName=mf.get(i).getOriginalFilename();
			String imgName=orgName.substring(0, orgName.indexOf('.'));
			String ext=orgName.substring(orgName.indexOf('.'));
			System.out.println(orgName);
			System.out.println(imgName);
			System.out.println(ext);
			if(i==0) {
				imgName = "main_" + imgName;
				newName += imgName+ext+",";
			}else {
				long date=System.currentTimeMillis();
				newName += imgName+date+ext+",";
			}
			
		}
		vo.setLhc_img(newName);
		String newImgName = null;
		File copyFile = null;
		
		for(int i=0; i<mf.size();i++) {
			String orgName=mf.get(i).getOriginalFilename();
			String imgName=orgName.substring(0, orgName.indexOf('.'));
			String ext=orgName.substring(orgName.indexOf('.'));
			if(i==0) {
				imgName = "main_"+imgName;
				newImgName = imgName+ext;
			}else {
				long date=System.currentTimeMillis();
				newImgName += imgName+date+ext;
			}
			String path=request1.getRealPath("lhcSave");
			String imgPath=path+"\\"+newImgName;
			copyFile=new File(imgPath);
			System.out.println("copyFile은"+copyFile);
			mf.get(i).transferTo(copyFile);
		}
		
		vo.setLhc_name(request.getParameter("lhc_name"));
		vo.setLhc_phone(request.getParameter("lhc_phone"));
		vo.setLhc_addr(request.getParameter("lhc_addr")); 
		vo.setLhc_info(request.getParameter("lhc_info"));
		vo.setLhc_vendor(request.getParameter("lhc_vendor"));
		vo.setLhc_seats(Integer.parseInt(request.getParameter("lhc_seats")));

		//근데 여기부터는 Spring에서 알아서 예외처리를 해 줌. 따라서 내가 할 예외처리는 없음. //회원 가입 처리
		lhcPcDAO.insertPc(vo);
		 
		return "lhcMember/lhcMain";
	}
	
	@RequestMapping("lhcPcListAll.lhc")
	public String lhcPcListAll(HttpSession session, HttpServletRequest request,String pageNum, Model model)throws Exception{
		
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
		count = lhcPcDAO.getPcCount();
		
		number = count - (currentPage-1) * pageSize;
		
		//#검색 목록
		String sel = request.getParameter("sel");
		String search = request.getParameter("search");
		
		//#메인 이미지 따로 빼기
		LhcPcVO vo = new LhcPcVO();
		String imgMains = null;
		String [] imgMain = null;
		String newImgMain = null;
		if(sel != null && search != null) {
			
			count = lhcPcDAO.getSearchPcCount(sel,search);	//  검색된 글 수
			//System.out.println("count는 "+count);
			if(count > 0){
				pcList = lhcPcDAO.getSearchPcs(startRow, endRow, sel, search); //  검색된 글 리스트받기
			//System.out.println("pcList는  "+pcList);
			}
			//System.out.println(count);
		}else{
			
			// # 일반 전체 목록 
			count = lhcPcDAO.getPcCount();		
			if(count > 0) {						
				pcList = lhcPcDAO.getPcs(startRow, endRow);	
			}else{
				pcList = Collections.EMPTY_LIST;	// null 대신 처리
			}
			
			
			for(int i = 0; i < pcList.size(); i++) {
				//list.get(1)
				vo = (LhcPcVO)pcList.get(i);
				imgMains = vo.getLhc_img();
				imgMain = imgMains.split(",");
				newImgMain = imgMain[1];
				vo.setLhc_img(newImgMain);
				System.out.println(i+"번째는 newImgMain : "+ newImgMain);
			}
		}
		System.out.println("전체 newImgMain : "+ newImgMain);
		
		model.addAttribute("mainImg", newImgMain);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("count", count);
		model.addAttribute("pcList", pcList);
		model.addAttribute("number", number);
		
		return "lhcPc/lhcPcListAll";
	}
	
	
	
	
	
	
}
