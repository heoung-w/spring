package lhc.model.dao;
import java.util.List;
import lhc.model.vo.LhcPcMenuVO;

public interface LhcPcMenuDAO {
	
	// 메뉴 입력
	public void insertPcMenu(LhcPcMenuVO vo) throws Exception;
	
	// 
	public int getPcMenuCount() throws Exception;
	// 
	public List getPcMenus(int start, int end) throws Exception;
	// 
	public LhcPcMenuVO getPcMenu(int lhc_menum) throws Exception;
	// 
	public LhcPcMenuVO getPcMenuForUpdate(int num) throws Exception;
	// 
	public void updatePcMenu(LhcPcMenuVO vo) throws Exception;
	// 
	public void deletePcMenu(int num) throws Exception;

}
