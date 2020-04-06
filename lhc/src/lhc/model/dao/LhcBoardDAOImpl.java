package lhc.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import lhc.model.vo.LhcBoardVO;


public class LhcBoardDAOImpl implements LhcBoardDAO {

	private SqlSessionTemplate sqlSession = null;
	public void setSqlSession(SqlSessionTemplate sqlSession) {this.sqlSession = sqlSession;}
	
	@Override
	public void insertArticle(LhcBoardVO vo) throws Exception {
		int num = 0;
		// maxNum 받기, 즉 새로 저장되는 글의  seq 번호를 유추(꺼내+1)
		String number = (String)sqlSession.selectOne("lhcBoard.maxNum");
		
		if(number != null) {
			num = Integer.parseInt(number) + 1;
		}else {
			num = 1;
		}
		
		sqlSession.insert("lhcBoard.insertArticle", vo);
		
	}

	@Override
	public int getArticleCount() throws Exception {
		int count = sqlSession.selectOne("lhcBoard.countAll");
		return count;
	}

	@Override
	public List getArticles(int start, int end) throws Exception {
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List list = sqlSession.selectList("lhcBoard.selectAll", map);
		
		return list;
	}

	@Override
	public LhcBoardVO getArticle(int lhc_num) throws Exception {
		// 조회수 올리기 위해서 readcount 올리기
		sqlSession.update("lhcBoard.updateReadCount", lhc_num);
		LhcBoardVO article = (LhcBoardVO)sqlSession.selectOne("lhcBoard.selectOne", lhc_num);
		return article;
	}

	@Override
	public LhcBoardVO getArticleForUpdate(int lhc_num) throws Exception {
		LhcBoardVO article = (LhcBoardVO)sqlSession.selectOne("lhcBoard.selectOne", lhc_num);
		return article;
	}

	@Override
	public void updateArticle(LhcBoardVO lhc_article) throws Exception {
		sqlSession.update("lhcBoard.updateArticle", lhc_article);
		
	}

	@Override
	public void deleteArticle(int lhc_num) throws Exception {
		sqlSession.delete("lhcBoard.deleteArticle", lhc_num);
		
	}

}
