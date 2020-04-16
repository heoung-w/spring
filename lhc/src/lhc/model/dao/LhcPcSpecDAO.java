package lhc.model.dao;
import java.util.List;
import lhc.model.vo.LhcPcSpecVO;


public interface LhcPcSpecDAO {
	
	public void insertPcSpec(LhcPcSpecVO vo) throws Exception;
	// 
	public int getPcSpecCount() throws Exception;
	// 
	public List getPcSpecs(int start, int end) throws Exception;
	// 
	public LhcPcSpecVO getPcSpec(String lhc_name) throws Exception;
	// 
	public LhcPcSpecVO getPcSpecForUpdate(int num) throws Exception;
	// 
	public void updatePcSpec(LhcPcSpecVO vo) throws Exception;
	// 
	public void deletePcSpec(int num) throws Exception;

}
