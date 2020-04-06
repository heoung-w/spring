package lhc.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import lhc.model.vo.LhcPcVO;

public class LhcPcDAOImpl implements LhcPcDAO{

	@Autowired
	private SqlSessionTemplate sqlSession=null;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession=sqlSession;
	}

	@Override
	public void insertPc(LhcPcVO vo) throws Exception {
		//System.out.println("LhcPcDAOImpl에서 lhc_img"+vo.getLhc_img());
		sqlSession.insert("lhcPc.insertPc", vo);
		//System.out.println("LhcPcDAOImpl에서 lhc_img2"+vo.getLhc_img());
		
	}

	@Override
	public int getPcCount() throws Exception {
		int count = sqlSession.selectOne("lhcPc.countAll");
		return count;
	}

	@Override
	public List getPcs(int start, int end) throws Exception {
		
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List list = sqlSession.selectList("lhcPc.selectAll", map);
		
		return list;
	}

	@Override
	public LhcPcVO getPc(int num) throws Exception {
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
	public void deletePc(int num) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getSearchPcCount(String sel,String search) throws Exception {
		
		HashMap map = new HashMap();
		map.put("sel", sel);
		map.put("search", search);
		int count = sqlSession.selectOne("lhcPc.getSearchPcCount", map);

		return count;
	}
	
	public List getSearchPcs(int start, int end, String sel, String search) throws Exception {
		
		HashMap map = new HashMap();
		
		map.put("start", start);
		map.put("end", end);
		map.put("sel", sel);
		map.put("search", search);
		
		List pcList=sqlSession.selectList("lhcPc.getSearchPCs", map);
		
		//System.out.println("LhcPcDAOImpl에서 "+pcList);
		
		
		return pcList;
	}	
	
	
}
