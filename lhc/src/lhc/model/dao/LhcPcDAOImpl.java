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
		sqlSession.insert("lhcPc.insertPc", vo);
		
	}

	@Override
	public int getBeforePcCount(String state) throws Exception {
		int count = sqlSession.selectOne("lhcPc.beforeCountAll", state);
		return count;
	}
	
	@Override
	public int getResPcCount(String state) throws Exception {
		int count = sqlSession.selectOne("lhcPc.resCountAll", state);
		return count;
	}	

	@Override
	public List getPcs(int start, int end, String state) throws Exception {
		
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("state", state);
		
		List list = sqlSession.selectList("lhcPc.selectResAll", map);
		
		return list;
	}
	@Override
	public List getPcs1(int start, int end, String lhc_id) throws Exception {
		
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("lhc_id",lhc_id);
		
		List list = sqlSession.selectList("lhcPc.selectAllVendor", map);
		//System.out.println(list+"daoimpl에서 list");
		return list;
	}

	@Override
	public int getPcCount() throws Exception {
		int count = sqlSession.selectOne("lhcPc.countAll");
		return count;
	}
	@Override
	public int getVendorPcCount(String lhc_id) throws Exception {
		int count1 = sqlSession.selectOne("lhcPc.countAllVendor", lhc_id);
		//System.out.println(count1+"  daoimpl에서 count1");
		return count1;
	}
	
	@Override
	public LhcPcVO getPc(int num) throws Exception {
		LhcPcVO pc = sqlSession.selectOne("lhcPc.selectOnePc", num);
		return pc;
	}
	@Override
	public List getPc1(String num) throws Exception {
		List list =null;
		list = sqlSession.selectList("lhcPc.selectOnePc1", num);
		return list;
	}
	
	@Override
	public LhcPcVO getPc(String lhc_id) throws Exception {
		//System.out.println(lhc_id+"   daoimpl에서 lhc_id");
		LhcPcVO pc = sqlSession.selectOne("lhcPc.selectOnePc2", lhc_id);
		//System.out.println(pc+"   daoimpl에서 pc");
		return pc;
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
	public void statePc(int num) throws Exception {
		sqlSession.update("lhcPc.statePc", num);
	}
	
	// 업주 회원 정보 삭제시 pc정보 같이 삭제
	@Override
	public void deletePc(String id) throws Exception {
		sqlSession.delete("lhcPc.deletePc", id);
	}
	
	// pc정보만 삭제
	@Override
	public void pcDelete(int num) throws Exception {
		sqlSession.delete("lhcPc.pcDelete", num);
	}
	
	@Override
	public int getSearchPcCount(String sel,String search,String state) throws Exception {
		
		HashMap map = new HashMap();
		map.put("sel", sel);
		map.put("search", search);
		map.put("state", state);
		int count = sqlSession.selectOne("lhcPc.getSearchPcCount", map);

		return count;
	}
	
	public List getSearchPcs(int start, int end, String sel, String search, String state) throws Exception {
		
		HashMap map = new HashMap();
		
		map.put("start", start);
		map.put("end", end);
		map.put("sel", sel);
		map.put("search", search);
		map.put("state", state);
		
		List pcList=sqlSession.selectList("lhcPc.getSearchPcs", map);
		return pcList;
	}

	

	@Override
	public int selectOnePcChar(String id) throws Exception {
		int num = sqlSession.selectOne("lhcPc.selectOnePcChar",id);
		return num;
	}
	
	@Override
	public int getLikeCount(int num) throws Exception {
		int count =sqlSession.selectOne("lhcPc.getLikeCount",num);
		return count;
	}

	@Override
	public void updateLikeCount(int num) throws Exception {
		sqlSession.update("lhcPc.updateLikeCount", num);
	}
	
	@Override
	public void downLikeCount(int num) throws Exception {
		sqlSession.update("lhcPc.downLikeCount", num);
	}

	@Override
	public List getFavPcs(int startRow, int endRow, String state) throws Exception {
		List pcFavList = null;
		HashMap map = new HashMap();
		map.put("start", startRow);
		map.put("end", endRow);
		map.put("state", state);
		pcFavList = sqlSession.selectList("lhcPc.getFavPcs", map);
		return pcFavList;
	}
	
	@Override
	public List getCharPcs(int startRow, int endRow, String state) throws Exception {
		List pcFavList = null;
		HashMap map = new HashMap();
		map.put("start", startRow);
		map.put("end", endRow);
		map.put("state", state);
		pcFavList = sqlSession.selectList("lhcPc.getCharPcs", map);
		return pcFavList;
	}
	
	// 매출로 정렬해서 메서드 추가
	@Override	
	public List getPcsMoneyList(int start, int end) throws Exception {
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		List list = sqlSession.selectList("lhcPc.selectAllbyMoney", map);
		
		return list;
	}

}
