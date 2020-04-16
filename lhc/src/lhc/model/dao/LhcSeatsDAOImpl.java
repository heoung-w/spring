package lhc.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import lhc.model.vo.LhcPcVO;
import lhc.model.vo.LhcSeatsVO;

public class LhcSeatsDAOImpl implements LhcSeatsDAO, LhcPcDAO {

	@Autowired
	private SqlSessionTemplate sqlSession = null;
	public void setSqlSession(SqlSessionTemplate sqlSession) {this.sqlSession = sqlSession;}
	
	@Override
	public void printsomething() {
	}

	@Override
	public LhcPcVO getPc(int num) throws Exception {
		LhcPcVO pc = sqlSession.selectOne("lhcPc.selectOnePc", num);
		return pc;
	}

	@Override
	public void insertPc(LhcPcVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getBeforePcCount(String state) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getResPcCount(String state) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List getPcs(int start, int end, String state) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LhcPcVO getPcForUpdate(int num) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePc(LhcPcVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePc(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSearchPcCount(String sel, String search, String state) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List getSearchPcs(int start, int end, String sel, String search, String state) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LhcSeatsVO getMychar(int lhc_num) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void statePc(int num) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int selectOnePcChar(String id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void pcDelete(int num) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLikeCount(int count) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}




}
