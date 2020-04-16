package lhc.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import lhc.model.vo.LhcMyCharVO;

public class LhcMyCharDAOImpl implements LhcMyCharDAO{

	@Autowired
	private SqlSessionTemplate sqlSession = null;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void insertMyChar(LhcMyCharVO vo) {
		sqlSession.insert("lhcMyChar.inserMyChar", vo);
		
	}

	@Override
	public List selectMyChar(int startRow, int endRow, String id) throws Exception {
		System.out.println("아이디는" + id);
		HashMap map = new HashMap();
		map.put("start", startRow);
		map.put("end", endRow);
		map.put("id", id);
		List list = sqlSession.selectList("lhcMyChar.selectMyChar", map); 
		System.out.println(list +"list 입니다");
		return list;
	}

	@Override
	public List selectAllChar(int startRow, int endRow) throws Exception {
		HashMap map = new HashMap();
		map.put("start", startRow);
		map.put("end", endRow);
		List list = sqlSession.selectList("lhcMyChar.selectAllChar", map);
		return list;
	}

	@Override
	public List selectVendorChar(int startRow, int endRow, int num) throws Exception {
		HashMap map = new HashMap();
		map.put("start", startRow);
		map.put("end", endRow);
		map.put("num", num);
		List list = sqlSession.selectList("lhcMyChar.selectVendorChar", map);
		return list;
	}

	@Override
	public int selectMyCount(String id) throws Exception {
		int count = sqlSession.selectOne("lhcMyChar.selectMyCount", id);
		return count;
	}

	@Override
	public int selectAllCount() throws Exception {
		int count = sqlSession.selectOne("lhcMyChar.selectAllCount");
		return count;
	}

	@Override
	public int selectVendorCount(int num) throws Exception {
		int count = sqlSession.selectOne("lhcMyChar.selectVendorCount", num);
		return count;
	}

}
