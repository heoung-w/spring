package lhc.model.dao;

import java.util.List;

import lhc.model.vo.LhcMyCharVO;

public interface LhcMyCharDAO {
	// 예약내역 등록
	public void insertMyChar(LhcMyCharVO vo) throws Exception;
	// 회원 개인 예약 내역, 카운트 수
	public List selectMyChar(int startRow, int endRow, String id) throws Exception;
	public int selectMyCount(String id) throws Exception;
	// 관리자 전체 예약 내역, 카운트 수
	public List selectAllChar(int startRow, int endRow) throws Exception; 
	public int selectAllCount() throws Exception;
	// 업주 자기 pc방 예약 내역, 카운트 수
	public int selectVendorCount(int num) throws Exception;
	public List selectVendorChar(int startRow, int endRow, int num) throws Exception;
}
