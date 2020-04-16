package lhc.model.dao;
import java.util.HashMap;
import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import lhc.model.vo.LhcPcSpecVO;

public class LhcPcSpecDAOImpl implements LhcPcSpecDAO{

	@Autowired
	private SqlSessionTemplate sqlSession=null;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession=sqlSession;
	}
	@Override
	public void insertPcSpec(LhcPcSpecVO vo) throws Exception {
		
		sqlSession.insert("lhcPcSpec.insertPcSpec", vo);
		
	}
	@Override
	public int getPcSpecCount() throws Exception {
		int count = sqlSession.selectOne("lhcPcSpec.countAll");
		return count;
	}
	@Override
	public List getPcSpecs(int start, int end) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public LhcPcSpecVO getPcSpec(String lhc_name) throws Exception {
		LhcPcSpecVO spec = sqlSession.selectOne("lhcPcSpec.selectOnePcSpec", lhc_name);
		return spec;
	}
	@Override
	public LhcPcSpecVO getPcSpecForUpdate(int num) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updatePcSpec(LhcPcSpecVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deletePcSpec(int num) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
