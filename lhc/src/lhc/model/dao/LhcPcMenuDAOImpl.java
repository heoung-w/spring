package lhc.model.dao;
import java.util.HashMap;
import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import lhc.model.vo.LhcPcMenuVO;


public class LhcPcMenuDAOImpl implements LhcPcMenuDAO{

	@Autowired
	private SqlSessionTemplate sqlSession=null;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession=sqlSession;
	}
	
	@Override
	public void insertPcMenu(LhcPcMenuVO vo) throws Exception {
		
		sqlSession.insert("lhcPcMenu.insertPcMenu", vo);
		
	}

	@Override
	public int getPcMenuCount() throws Exception {
		int count = sqlSession.selectOne("lhcPcMenu.countAll");
		return count;
	}

	@Override
	public List getPcMenus(int start, int end) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LhcPcMenuVO getPcMenu(int lhc_num) throws Exception {
		LhcPcMenuVO menu = sqlSession.selectOne("lhcPcMenu.selectOnePcMenu",lhc_num);
		return menu;
	}

	@Override
	public LhcPcMenuVO getPcMenuForUpdate(int num) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePcMenu(LhcPcMenuVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePcMenu(int num) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
