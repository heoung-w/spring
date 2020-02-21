package test.mybatis.bean;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import test.spring.model.TestMybatisDTO;

@Controller
public class TestMybatis {

	@Autowired
	private SqlSessionTemplate sqlSession = null;
	
	@RequestMapping("mybatis.do")
	public String helloMybatis() {
		
		System.out.println(sqlSession);
		
		return "spring/spring01/hello";
	}
	
	@RequestMapping("testTable.do")
	public String testTable(Model model) {
		// sqlSession -> DB -> SQL 쿼리문 호출 데이터 -> 데이터 가져오기
		int count = (Integer)sqlSession.selectOne("test.userCount");
		
		// age가 가장 높은 수 가져오기
		int maxAge = (Integer)sqlSession.selectOne("test.maxAge");
		
		// test테이블 전체 레코드 가져오기
		List<TestMybatisDTO> userList = sqlSession.selectList("test.selectAll");
		
		// id를 주고 해당 id에 일치하는 레코드 가져오기
		// 파라미터는 한개만 보낼 수 있다
		// 여러개의 파라미터를 보내고 싶으면 Map 만들어 데이터 넣고 보내야함
		String id = "java";
		TestMybatisDTO dto = (TestMybatisDTO)sqlSession.selectOne("test.getUser", id);
		
		// id주고 reg 결과 하나 가져오기
		Timestamp reg =  (Timestamp)sqlSession.selectOne("test.getReg", id);
		
		
		
		model.addAttribute("count", count);
		model.addAttribute("maxAge", maxAge);
		model.addAttribute("userList", userList);
		model.addAttribute("dto", dto);
		model.addAttribute("reg", reg);
		
		return "spring/mybatis/test";
	}
	
	
	
	
	@RequestMapping("insertForm.do")
	public String insertForm() {
		return "spring/spring0221/insertForm";
	}
	

	@RequestMapping("insertPro.do")
	public String insertPro(TestMybatisDTO dto, Model model) {
		
		// DB에 insert
		sqlSession.insert("test.insertUser", dto);
		model.addAttribute("dto", dto);
		
		return "spring/spring0221/insertPro";
	}
	
	
	@RequestMapping("updateForm.do")
	public String updateForm(Model model) {
		
		// DB에 insert
		TestMybatisDTO dto = (TestMybatisDTO)sqlSession.selectOne("test.getUser", "java");
		model.addAttribute("dto", dto);
		
		return "spring/spring0221/updateForm";
	}
	
	@RequestMapping("updatePro.do")
	public String updatePro(String pw, int age) {
		
		HashMap map = new HashMap();
		map.put("mapId", "java");
		map.put("mapPw", pw);
		map.put("mapAge", age);
		sqlSession.update("test.updateUser", map);
		
		return "spring/spring0221/updatePro";
	}
	
	@RequestMapping("deleteUser.do")
	public String deleteUser(Model model) {
		
		sqlSession.delete("test.deleteUser", "java");
		model.addAttribute("java");
		
		return "spring/spring0221/delete";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
