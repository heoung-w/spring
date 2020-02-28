package spring.aop.advice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aopMember/")
public class AopMemberBean {

	@RequestMapping("testaop.do")
	public String testAop() {
		System.out.println("testAop!!!!!!!!");
		
		return "aopMember/main";
	}
}
