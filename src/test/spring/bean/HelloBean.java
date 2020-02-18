package test.spring.bean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 컨트롤러 어노테이션 : 컨트롤러에서 이 클래스를 호출할 수 있는 빈이라고 반드시 표시

@Controller
@RequestMapping("/test/*")
public class HelloBean {
		// @RequestMapping({"hello.do", "hi.do", "hye.do"}) 여러개도 가능함
		// @RequestMapping("?.do")	? 로 하면 글자수만큼 넣어주면댐 한글자당 ? 하나씩!!
	 	// * 이있으면 앞에 뭐가입력되든 전부 이걸 실행시킨다.
	
		// @GetMapping("hello.do") get방식 요청받는
		// @PostMapping("hello.do") Post방식 요청받는
		@RequestMapping("hello.do")
		public String hello() {
			
			// 로직 처리.....
			return "/WEB-INF/views/spring01/hello.jsp";
		}
}
