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
	public List selectAll(int startRow, int endRow, String sep) throws Exception {
		HashMap map = new HashMap();
		map.put("sep", sep);
		map.put("start", startRow);
		map.put("end", endRow);
		List list = sqlSession.selectList("lhcMember.selectAll", map);
		return list;
	}

	@Override
	public LhcMemberVO selectMember(String lhc_id) throws Exception {
		
		
		LhcMemberVO vo = sqlSession.selectOne("lhcMember.selectMember", lhc_id);
		
		return vo;
	}
	
	//각 pc방 찜한 회원들 리스트 담아서, 찜회원 목록에 보여주기
	@Override
	public List selectMember1(String lhc_id) throws Exception {
		 
		List list = null;
		list = sqlSession.selectList("lhcMember.selectMember", lhc_id);
		
		return list;
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
	public int idAvailCheck(String lhc_id) throws Exception {
		int idcheck=(Integer)sqlSession.selectOne("lhcMember.idAvailCheck", lhc_id);
		// idcheck=0 : db에 존재하지 않다=>사용가능한 id,
		// idcheck=1 : db에 존재함. ->사용불가능한 id
		return idcheck;
	}

	@Override
	public int getCountMember(String sep) throws Exception {
		int count = sqlSession.selectOne("lhcMember.getCountMember", sep);
		
		return count;
	}


	@Override
	public int updatePoint(String lhc_id, int lhc_money) throws Exception {
		
		//System.out.println(lhc_money+"daoimpl에서 lhc_money");
		
		HashMap map = new HashMap();
		map.put("lhc_id", lhc_id);
		map.put("lhc_money", lhc_money);
		
		sqlSession.update("lhcMember.updatePoint", map);
		
		//System.out.println(map+"map이다");
		
		int result = 1;
		
		return result;
	}

	@Override
	public String selectFav(String lhc_id) throws Exception {
		
		String fav = (String)sqlSession.selectOne("lhcMember.selectFav", lhc_id);
		//System.out.println(fav+"memberdaoimpl에서 fav");
		return fav;
	}

	@Override
	public void updateFav(String num, String lhc_id) throws Exception {
		
		HashMap map = new HashMap();
		map.put("num", num);
		map.put("lhc_id", lhc_id);
		
		sqlSession.update("lhcMember.updateFav", map);
		
	}


	@Override
	public String getLikeNum(String id) throws Exception {
		String likeNum = null;
		likeNum = sqlSession.selectOne("lhcMember.selectLikeNum", id);
	
		return likeNum;
	}

	
	@Override
	public void updateLikeNum(String id, String newLike) throws Exception {
		System.out.println(newLike+"뉴라이크");
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("newLike", newLike);
		sqlSession.update("lhcMember.updateLikeNum", map);
		
	}
}
