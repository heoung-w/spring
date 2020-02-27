package spring.aop.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aop/")
public class AopBean {

    @Autowired
    private Tv tv = null;

    // 핵심 기능 구현 메서드
    @RequestMapping("main.do")
    public String main() {
        System.out.println("하윙");
        tv.power();
        tv.channel();
        return "aop/testaop";
    }

    @RequestMapping("main2.do")
    public String main2(String id) {
        System.out.println("main2 -->"+id);
        return "aop/testaop";
    }

}