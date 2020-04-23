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
	public LhcPcSpecVO getPcSpec(String lhc_name) throws Exception {
		LhcPcSpecVO spec = sqlSession.selectOne("lhcPcSpec.selectOnePcSpec", lhc_name);
		return spec;
	}	
}
