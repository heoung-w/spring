package test.spring.bean;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class UploadBean {

	@RequestMapping("uploadForm.do")
	public String uploadForm() {
		System.out.println("test");
		
		return "upload/uploadForm";
	}
	
	// 사진이나, 이미지를 다운 하려면 그냥 Http가 아니라 MultipartHttp 써야함
	@RequestMapping("uploadPro.do")
	public String uploadPro(String writer, MultipartHttpServletRequest request, Model model) {
		System.out.println("writer" + writer);
		MultipartFile nf = null;
		String newName =null;
		try {
			// #1. D드라이브에 저장
			// 파일 정보담기
			// nf = request.getFile("img");
			// 파일 저장할 경로 + 파일명 -> 파일 객체 생성
			// File copyfile = new File("d://save//"+nf.getOriginalFilename());
			// 파일을 위치 + 파일명으로 저장
			// nf.transferTo(copyfile);
			
			// #2. 서버에 저장
//			nf = request.getFile("img");
//			String path = request.getRealPath("save"); // 서버상의 save 폴더 경로 가져오기
//			String imgPath = path + "\\" + nf.getOriginalFilename();
//			File copyfile = new File(imgPath);
//			nf.transferTo(copyfile);
			
			// 파일이름 중복처리 : 새로운 파일명 + 확장자 만들기
			// 오리지널파일명 + 날짜
			nf = request.getFile("img");
			String orgName = nf.getOriginalFilename();	// 오리지널 파일명
			String imgName = orgName.substring(0, orgName.lastIndexOf('.')); // 파일이름만
			String ext = orgName.substring(orgName.lastIndexOf('.')); // 확장자만 추출
			long date = System.currentTimeMillis();
			newName = imgName+date+ext;
			System.out.println(newName);
			
			String path = request.getRealPath("save");
			String imgPath = path+"\\" +newName;
			File copyfile = new File(imgPath);
			nf.transferTo(copyfile);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("newName", newName);
		
		return "upload/uploadPro";
	}
}
