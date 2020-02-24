package test.mybatis.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import test.spring.model.TestMybatisDTO;

@Controller
public class Mybatis {
	
	@Autowired
	private SqlSessionTemplate sqlSession = null;
	
	@RequestMapping("selectIf.do")
	public String selectIf(Model model) {
		
		
		TestMybatisDTO dto = new TestMybatisDTO();
		dto.setId("admin");
		dto.setPw("1234");
		
		int count = (Integer)sqlSession.selectOne("myba.loginCheck", dto);
		System.out.println(count);
		
		model.addAttribute("count", count);
		return "spring/spring0224/select";
	}

	@RequestMapping("selectTrim.do")
	public String selectTrim(Model model) {
		
		
		TestMybatisDTO dto = new TestMybatisDTO();
		dto.setId("admin");
		dto.setPw("1234");
		dto.setAge(20);
		int count = (Integer)sqlSession.selectOne("myba.selectTrim", dto);
		System.out.println(count);
		
		model.addAttribute("count", count);
		return "spring/spring0224/select";
	}
	
	
	@RequestMapping("selectIn.do")
	public String selectIn(Model model) {
		
		List list = new ArrayList();
		list.add("admin");
		list.add("script");
		list.add("spring");
		

		
		int count = (Integer)sqlSession.selectOne("myba.selectIn", list);
		System.out.println(count);
		
		model.addAttribute("count", count);
		return "spring/spring0224/select";
	}
	
	
	@RequestMapping("selectLike.do")
	public String selectLike(Model model) {
 
		HashMap map = new HashMap();
		map.put("sel", "id");			// 검색할 카테코리 : DB 컬럼명
		map.put("keyword", "s");	// 검색할 키워드   : 사용자가 입력한 값
		
		int count = (Integer)sqlSession.selectOne("myba.selectLike", map);
		System.out.println(count);
		
		model.addAttribute("count", count);
		return "spring/spring0224/select";
	}
	
	
	
	
	
	
}
