package board.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import board.model.vo.BoardVO;

public class BoardDAOImpl implements BoardDAO{
	
	private SqlSessionTemplate sqlSession = null;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		
		this.sqlSession = sqlSession;
	}

	@Override
	public int insertArticle(BoardVO vo) throws Exception {
		
		int check = (Integer)sqlSession.insert("board.insertArticle", vo);
		
		return check;
	}

	@Override
	public int getArticleCount() throws Exception {
		
		int count = (Integer)sqlSession.selectOne("board.countAll");
		
		return count;
	}

	@Override
	public List getArticles(int start, int end) throws Exception {
		
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List list = sqlSession.selectList("board.selectAll", map);
		
		return list;
	}

	@Override
	public BoardVO getArticle(int num) throws Exception {
		
		BoardVO vo = sqlSession.selectOne("board.getArticle", num);
		sqlSession.update("board.updateReadCount", num);
		
		return vo;
	}

	@Override
	public BoardVO getArticleForUpdate(int num) throws Exception {
		BoardVO vo = null;
		vo = sqlSession.selectOne("board.getArticle", num);
		
		return vo;
	}

	@Override
	public int updateArticle(BoardVO vo) throws Exception {
		int x = -1;
		String str = null;
		str = sqlSession.selectOne("board.selectPw", vo.getNum());
		if(str != null) {
			if(str.equals(vo.getPw())) {
				sqlSession.update("board.updateArticle", vo);
				x = 1;
			}else {
				x = 0; // 비밀번호 오류
			}
		}
		
		return x;
	}

	@Override
	public int deleteArticle(int num, String passwd) throws Exception {
			int check = -1;
			String pass = sqlSession.selectOne("board.selectPw", num);
			System.out.println("pass" + pass);
			System.out.println("passwd" + passwd);
			if(pass != null) {
				if(pass.equals(passwd)) {
					System.out.println(num);
					sqlSession.delete("deleteArticle", num);
					check = 1;
				}else {
					check = 0;
				}
			}
		
		return check;
	}

}
