package lhc.model.dao;

import java.util.List;

import lhc.model.vo.LhcBoardVO;

public interface LhcBoardDAO {

	// 
	public void insertArticle(LhcBoardVO vo) throws Exception;
	// 
	public int getArticleCount() throws Exception;
	// 
	public List getArticles(int start, int end) throws Exception;
	// 
	public LhcBoardVO getArticle(int num) throws Exception;
	// 
	public LhcBoardVO getArticleForUpdate(int num) throws Exception;
	// 
	public void updateArticle(LhcBoardVO vo) throws Exception;
	// 
	public void deleteArticle(int num) throws Exception;
	

	
	//
}
