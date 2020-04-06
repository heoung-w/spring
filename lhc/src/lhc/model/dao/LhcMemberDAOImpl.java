package lhc.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import lhc.model.vo.LhcMemberVO;



public class LhcMemberDAOImpl implements LhcMemberDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession = null;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		// 원래 jsp에서 DAO에서는 각 DAO 메서드 안에서 try(){}catch(){}finally(){}해서 예외처리 해줬었는데, 
		// throws Exception => 나를 부른 애가 예외처리하게 하는 것이고, 
		// MemberBean에서 이 메서드를 불렀으니까...MemberBean에서  예외처리 하도록 던져줌. 
		// memberSQL에 있는 sql태그 호출해서 DB에 데이터 저장시키기. 
		
		this.sqlSession = sqlSession;
	}

	@Override
	public void insertMember(LhcMemberVO vo) throws Exception {
		
		sqlSession.insert("lhcMember.insertMember", vo);
		
	}

	@Override
	public int idPwCheck(LhcMemberVO vo) throws Exception {
		
		int check = (Integer)sqlSession.selectOne("lhcMember.idPwCheck", vo);
		
		return check;
	}
	@Override
	public String idPwCheck2(String id) throws Exception {
		
		
		return sqlSession.selectOne("lhcMember.idPwCheck2", id);
	}

	@Override
	public List selectAll() throws Exception {
		List list = null;
		return list;
	}

	@Override
	public LhcMemberVO selectMember(String id) throws Exception {
		
		
		LhcMemberVO vo = sqlSession.selectOne("lhcMember.selectMember", id);
		
		
		return vo;
	}

	@Override
	public void updateMember(LhcMemberVO vo) throws Exception {
		
		sqlSession.update("lhcMember.updateMember", vo);
		
	}

	@Override
	public void deleteMember(String id, String pw) throws Exception {
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("pw", pw);
		sqlSession.delete("lhcMember.deleteMember", map);
	}
	

	@Override
	public int idAvailCheck(String id) throws Exception {
		return 0;
	}

	@Override
	public int getArticleMember() throws Exception {
		int count = (Integer)sqlSession.selectOne("lhcmember.getArticleMember");
		
		return count;
	}



	
	
}
