package test.spring.bean;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import test.spring.model.TestDTO;
import test.spring.model.TvDTO;

@Controller
public class HelloBean3 {

// 	일반 메서드에
// 	ModelAttribute 어노테이션을 추가해 놓으면 -- >
// 	모든 view에서 가져다 쓸수있다.
//	@ModelAttribute("tvInfo")
//	public TvDTO getTV(String col) {
//		System.out.println("getTv호출");
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
	
	
	@RequestMapping("hello5.do")
	@ResponseBody
	public String hello5() {
		return "helloooooooooooo 5!!!!";
	}  
	
	// RequestMapping 옵션들
	// value = 주소, method=전송방식, params=파라미터
	// @RequestMapping(value="hello6.do", params="id=java") id 파라미터는 java값이여야 한다는 조건 제시 (제약을 걸어두는거임)
	// @RequestMapping(value="hello6.do", params= {"id=java","pw=1234"}) // id = 자바 pw =1234 여야만 넘어감. 일치하지 않을시 에러뜸
	// @RequestMapping(value="hello.do", params= {"id=admin","pw"}) 이런식으로 pw만 적으면 pw를 적긴해야하지만 값은 정해두지 않는거임!!
	// @RequestMapping(value="hello6.do", params= {"id=admin","pw", "!age"}) !age란 , age값이 넘어 오면 안된다 라는 뜻임!!!!
	@RequestMapping(value="hello6.do", params= {"id=admin","pw", "!age"})
	public String hello6() {
		
		return "/WEB-INF/views/spring01/hello.jsp";
	}
  
	
	
	      
	 
	
	
	// @Requestparam 
	// value : 파라미터명 , required : 파라미터 필수 여부 , defaultValue : 기본값
	// ------------ 파라미터(넘어오는 값)이 있어야 매핑되는 방법 (required=true)가 기본 설정----------------------------
	// public String hell07(@RequestParam String msg){ 웹 사이트 요청에서 msg=??? 이렇게 요청을 받아야함
	// public String hell07(@RequestParam("id") String msg){ ("id")가 붙으면 웹 사이트 요청에서 id=??? 이렇게 요청을 받아야함
	
	// ------------ 파라미터(넘어오는 값)이 생략해도 매핑되는 방법 ----------------------------
	// public String hell07(@RequestParam(value="msg", defaultValue="hello") String msg){ 이렇게 지정해 놓으면 기본값이 hello
	// public String hell07(@RequestParam(value="msg", required=false) String msg){
	@RequestMapping("hello7.do")
	public String hell07(
		@RequestParam(value="id", required=true) String id,
		@RequestParam(value="pw", required=true) String pw,
		@RequestParam(value="auto", required=false, defaultValue="0") String auto){
		
		System.out.println(id);
		System.out.println(pw);
		System.out.println(auto);
		
		return "/WEB-INF/views/spring01/hello.jsp";
	}
	
	
	
	
	
}
