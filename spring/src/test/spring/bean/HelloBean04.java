package test.spring.bean;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloBean04 {

	@RequestMapping("pikachu.do")
	public String hello10(String name, Model model) {
		System.out.println(name);
		model.addAttribute("name", name);
		
		return "spring03/pikachu";
	}
	
	
	@RequestMapping("download.do")
	public ModelAndView down() {
		
		// 개발자가 원하는 다운받게 할 파일을 연결
		File f = new File("D:\\save\\radio.jpg");
		
		// FileDown : xml에 만든 다운로드 비지니스로직 처리 클래스의 bean태그 id속성값
		// 			  페이지 이동이 아니라 DownloadView를 실행하겠다해서 빈으로 연결.
		// downloadFile : 파라미터명 지정
		// f : 다운로드 시킬 데이터(파일 객체)
		ModelAndView mv = new ModelAndView("fileDown", "downloadFile", f);
		
		return mv;
	}
}
 