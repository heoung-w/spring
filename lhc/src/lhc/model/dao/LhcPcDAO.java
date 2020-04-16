package lhc.model.dao;
import java.util.List;
import lhc.model.vo.LhcPcVO;

public interface LhcPcDAO {
	
	public void insertPc(LhcPcVO vo) throws Exception;
	// 관리자가 보는 등록이 필요한 pc정보 리스트
	public int getBeforePcCount(String state) throws Exception;
	// 회원, 업주, 비회원 등록된 pc정보 리스트
	public int getResPcCount(String state) throws Exception;
	// 
	public List getPcs(int start, int end, String state) throws Exception;
	// 
	public LhcPcVO getPc(int num) throws Exception;
	// 
	public LhcPcVO getPcForUpdate(int num) throws Exception;
	// 
	public void updatePc(LhcPcVO vo) throws Exception;
	// 관리자가 pc 등록하기 
	public void statePc(int num) throws Exception;
	// 업주 회원 탈퇴시 피시 삭제
	public void deletePc(String id) throws Exception;
	// pc 정보만 삭제
	public void pcDelete(int num) throws Exception;
	//검색된 글 개수
	public int getSearchPcCount(String sel,String search,String state) throws Exception;
	//검색된 글 정보 가져오기 
	public List getSearchPcs(int start, int end, String sel, String search, String state) throws Exception;
	// 예약 내역 확인을 위해 업주 pc방 num 찾기
	public int selectOnePcChar(String id) throws Exception;
	
	public int getLikeCount(int count) throws Exception;
	
}
