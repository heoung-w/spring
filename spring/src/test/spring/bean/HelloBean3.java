package test.spring.bean;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import test.spring.model.TestDTO;
import test.spring.model.TvDTO;

@Controller
public class HelloBean3 {

	// 일반 메서드에
	// ModelAttribute 어노테이션을 추가해 놓으면 -- >
	// 모든 view에서 가져다 쓸수있다.
//	@ModelAttribute("tvInfo")
//	public TvDTO getTV(String col) {
//		System.out.println("getTv호출");
//		
//		TvDTO dto = new TvDTO();
//		dto.setPower(true);
//		dto.setCh(10);
//		dto.setCol(col);
//		
//		return dto;
//	}
//	
//	// 일반 메서드
//	@ModelAttribute
//	public void gTV(String col, Model model) {
//		TvDTO dto = new TvDTO();
//		
//		model.addAttribute("tv0", dto);
//	}
//	
//	// 매핑 메서드
//	@RequestMapping("hello4.do")
//	public String hello4(){
//		
//		System.out.println("hello 매핑 메서드 호출");
//		
//		return "/WEB-INF/views/spring02/tv.jsp";
//	}
	
	
	@RequestMapping("form2.do")
	public String sendMsg() {
		return "/WEB-INF/views/spring02/form.jsp";
	}
	
	@RequestMapping("pro2.do")
	public String viewMsg(@ModelAttribute("dto") TestDTO dto) {
		
		// 매개변수 TestDTo dto 라고 지정하면 set메서드로 자동 바인딩됨.
		// @ModelAttribute 어노테이션 효과로 dto라는 별칭으로 model에 저장
		// view에서 (pro.jsp) 에서 ${dto.id} , ${dto.pw} 로 바로 쓸 수 있음.  
		System.out.println(dto.getId());
		System.out.println(dto.getPw());
		
		return "/WEB-INF/views/spring02/pro.jsp";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
