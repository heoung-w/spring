package spring.aop.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// @RequestMapping("/aopMember/")
public class AopBean {
//di 결합도가 aop 응집도 높아
    @Autowired
    private Tv tv = null;

    // 핵심 기능 구현 메서드
    @RequestMapping("main.do")
    public String main() {
        System.out.println("하윙");
        tv.power();
        tv.channel();
        return "aop/testAop";
    }

    @RequestMapping("main2.do")
    public String main2(String id) {
        System.out.println("main2 -->"+id);
        return "aop/testAop";
    }
    
    @RequestMapping("main3.do")
    public String main3() {
        System.out.println("main3 !!!!!!!!!");
        return "aop/testAop";
    }
    
    public void main100() {  
    	System.out.println(" == main 100 == ");
    }

}