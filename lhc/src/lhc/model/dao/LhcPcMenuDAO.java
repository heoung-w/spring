package lhc.model.dao;
import java.util.List;
import lhc.model.vo.LhcPcMenuVO;

public interface LhcPcMenuDAO {	
	// 메뉴 입력
	public void insertPcMenu(LhcPcMenuVO vo) throws Exception;	
	// 
	public int getPcMenuCount() throws Exception;
	// 
	public LhcPcMenuVO getPcMenu(int lhc_menum) throws Exception;
}
