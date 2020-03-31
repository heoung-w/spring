package lhcMember.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import lhcMember.model.vo.LhcMemberVO;


public class LhcMemberDAOImpl implements LhcMemberDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession = null;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		
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
	public List selectAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LhcMemberVO selectMember(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateMember(LhcMemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMember(String id, String pw) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int idAvailCheck(String id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
