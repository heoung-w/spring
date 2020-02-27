package member.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import member.model.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSessionTemplate sqlSession = null;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		
		this.sqlSession = sqlSession;
	}
	
	
	@Override
	public void insertMember(MemberVO vo) throws Exception {
		
		sqlSession.insert("member.insertMember", vo);
	}

	@Override
	public int idPwCheck(MemberVO vo) throws Exception {
		int check = (Integer)sqlSession.selectOne("member.idPwCheck", vo);
		
		return check;
	}

	@Override
	public List selectAll() throws Exception {
		return null;
	}

	@Override
	public MemberVO selectMember(String id) throws Exception {
		
		MemberVO vo = sqlSession.selectOne("member.selectMember", id);
		
		return vo;
	}

	@Override
	public void updateMember(MemberVO vo) throws Exception {
		
		sqlSession.update("member.updateMember", vo);
	}

	@Override
	public void deleteMember(String id, String pw) throws Exception {
		
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("pw", pw);
		sqlSession.delete("member.deleteMember", map);
	}

	@Override
	public int idAvailCheck(String id) throws Exception {
		int check = (Integer)sqlSession.selectOne("member.idAvailCheck", id);
		// check = 0 : db에 존재하지 않다 -> 사용가능
		// check = 1 : db에 존재함 -> 사용 불가능한 id
		
		return check;
	}

}
