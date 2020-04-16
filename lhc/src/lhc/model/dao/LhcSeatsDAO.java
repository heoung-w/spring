package lhc.model.dao;

import lhc.model.vo.LhcSeatsVO;

public interface LhcSeatsDAO {

	public void printsomething();
	
	public LhcSeatsVO getMychar(int lhc_num) throws Exception;
}
