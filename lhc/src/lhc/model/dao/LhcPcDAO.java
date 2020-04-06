package lhc.model.dao;
import java.util.List;
import lhc.model.vo.LhcPcVO;

public interface LhcPcDAO {
	
	public void insertPc(LhcPcVO vo) throws Exception;
	// 
	public int getPcCount() throws Exception;
	// 
	public List getPcs(int start, int end) throws Exception;
	// 
	public LhcPcVO getPc(int num) throws Exception;
	// 
	public LhcPcVO getPcForUpdate(int num) throws Exception;
	// 
	public void updatePc(LhcPcVO vo) throws Exception;
	// 
	public void deletePc(int num) throws Exception;
	//검색된 글 개수
	public int getSearchPcCount(String sel,String search) throws Exception;
	
	//검색된 글 정보 가져오기 
	public List getSearchPcs(int start, int end, String sel, String search) throws Exception;
}
