package lhc.model.dao;
import java.util.List;
import lhc.model.vo.LhcPcSpecVO;


public interface LhcPcSpecDAO {
	public void insertPcSpec(LhcPcSpecVO vo) throws Exception;
	// 
	public int getPcSpecCount() throws Exception;
	// 
	public LhcPcSpecVO getPcSpec(String lhc_name) throws Exception; 
}
