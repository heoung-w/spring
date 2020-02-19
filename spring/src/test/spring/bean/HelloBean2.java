package test.spring.bean;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import test.spring.model.TestDTO;

@Controller
public class HelloBean2 {
	
//	# 1. Date 객체 생성해서 하는 방법 (기본방법)
//	private Date day = null;
//	@RequestMapping("hello2.do")
//	public String hello() {
//		day = new Date();
//		System.out.println(day);
//		return "/WEB-INF/views/spring01/hello.jsp";
//	}
	
	
	// 의존성 자동 주입
	// # 2. Autowired 자동 주입 방법 (위에 처럼 new 객체생성 안하고 Autowired로 다 받아쓰기)
	/*
	 *  같은 타입의 Bean이 두개가 있으면 변수이름에 맞게 주입됨
	 *  자동 의존성 주입될떄, 기본적으로 "클래스 타입으로 구분" 해서 가져오는데
	 *  (bean의 id 속성값과 이곳의 변수이름이 달라도 상관없음) 하지만
	 *  만약 같은 타입의 bean 객체가 여러개가 있다면 bean id의 속성값과 변수명이 동일한 것을 연결해줌!!!!
	 *  또 같은 타입의 bean 객체가 여러개 있는데 bean id와 변수명이 다르면 에러 발생함!!
	 *  spring servlet bean에 id 속성값과 변수명을 맞춰줘라!!!!!!!!!
	 */
	@Autowired
	private TestDTO dto = null;
	
	@Autowired
	private TestDTO dto2 = null;
	
	@Autowired
	private Date day = null;
	
	@RequestMapping("hello2.do")
	public String hello() {
		
		System.out.println(dto.getId());
		System.out.println(dto.getPw());
		System.out.println("reg ==> " + dto.getReg());
		
		System.out.println(dto2.getId());
		System.out.println(dto2.getPw());
		System.out.println("reg ==> " + dto2.getReg());
		
		return "/WEB-INF/views/spring01/hello.jsp";
	}
	
	
	// 총 3가지 방법이 있음 request, Model, ModelAndView
	
	// # 1. dto 로 값 받아오기
	@RequestMapping("pro.do")
	public String pro(TestDTO dto) {
		
		System.out.println(dto.getId());
		System.out.println(dto.getPw());
//		request.setAttribute("id", dto.getId());	
//		request.setAttribute("pw", dto.getPw());	
		return "/WEB-INF/views/spring02/pro.jsp";
	}
	
	// # 2. Model 객체에 저장해서 model에 저장한 값 넘겨주기
//	@RequestMapping("pro.do")
//	public String pro(TestDTO dto, Model model) {
//		// DTO로 받기
//		// model로 값 넘겨주기
//		model.addAttribute("id", dto.getId());
//		model.addAttribute("pw", dto.getPw());
//		
//		return "/WEB-INF/views/spring02/pro.jsp";
//	}
	
	
//	// # 3. ModelAndView 로 넘겨주는 방법
//	@RequestMapping("pro.do")
//	public ModelAndView pro(TestDTO dto) {
//		
//		// ModelAndView 사용하여 값 넘겨주기
//		ModelAndView mv = new ModelAndView();
//		
//		mv.addObject("id", dto.getId());
//		mv.addObject("pw", dto.getPw());
//		
//		mv.setViewName("/WEB-INF/views/spring02/pro.jsp");
//		
//		return mv;
//		//return "/WEB-INF/views/spring02/pro.jsp";
//	}
	
}
