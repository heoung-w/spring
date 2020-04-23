package lhc.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import lhc.model.vo.LhcMyCharVO;


public class LhcMyCharDAOImpl implements LhcMyCharDAO {

	private SqlSessionTemplate sqlSession = null;
	public void setSqlSession(SqlSessionTemplate sqlSession) {this.sqlSession = sqlSession;}
	
	@Override
	public List getMychar(int lhc_num) throws Exception {
		HashMap map = new HashMap();
		map.put("lhc_num", lhc_num);
		List seats = sqlSession.selectList("lhcMyChar.selectSeats", map);
		return seats;
	}

	@Override
	public void updateReservation(String checkboxes, int lhc_num, int lhc_rescount) throws Exception {
		HashMap map = new HashMap();
		map.put("lhc_rescount", lhc_rescount);
		map.put("checkboxes", checkboxes);
		map.put("lhc_num",lhc_num);
		sqlSession.update("lhcMyChar.updateReservation", map);
	}

	@Override
	public void insertMychar(int lhc_num, String checkboxes, String id, String lhc_name) throws Exception {
		HashMap map = new HashMap();
		
		map.put("id", id);
		map.put("checkboxes", checkboxes);
		map.put("lhc_num", lhc_num);
		map.put("lhc_name", lhc_name);
		sqlSession.insert("lhcMyChar.insertMychar", map);
	}

	@Override
	public int countReservation(int lhc_num) throws Exception {
		int countReserved = sqlSession.selectOne("lhcMyChar.countReserved", lhc_num);
		return countReserved;
	}
	
	//  회원 예약 내역 개수 세기
	@Override
	public int getReservedCount(String id) throws Exception {
		int count = sqlSession.selectOne("lhcMember.getReservedCount", id);
		return count;
	}
	
	// 업주 예약 내역 개수 세기
	@Override
	public int getReservedCount1(String lhc_name) throws Exception {
		int count = sqlSession.selectOne("lhcMember.getReservedCount1", lhc_name);
		return count;
	}

	@Override
	public List getReservedList(String id) throws Exception {
		List list = sqlSession.selectList("lhcMember.getReservedList", id);
		return list;
	}
	
	@Override
	public List getReservedList1(String lhc_name) throws Exception {
		List list = sqlSession.selectList("lhcMember.getReservedList1", lhc_name);
		return list;
	}

	@Override
	public LhcMyCharVO getSeats(String lhc_seats, int lhc_num) throws Exception {
		HashMap map = new HashMap();
		map.put("lhc_seats", lhc_seats);
		map.put("lhc_num", lhc_num);
		LhcMyCharVO seats = sqlSession.selectOne("lhcMyChar.getSeats", map);
		return seats;
	}

	@Override
	public void updateLhc_end(int netRev, int lhc_num, String lhc_seats) throws Exception {
		HashMap map = new HashMap();
		map.put("netRev", netRev);
		map.put("lhc_num", lhc_num);
		map.put("lhc_seats", lhc_seats);
		
		sqlSession.update("lhcMember.updateLhc_end", map);
		
	}

	@Override
	public int getAllCharCount() throws Exception {
		int count = sqlSession.selectOne("lhcMyChar.getAllCharCount");
		
		return count;
	}

	@Override
	public List getReservedAllList() throws Exception {
		List list = sqlSession.selectList("lhcMember.getReservedAllList");
		return list;
	}

	@Override // 2020.04.20 인터페이스 구현(PC방 정보 테이블에서 누적 예약 건수 올리기)
	public void updateTotalRes(int lhc_num) throws Exception {
		sqlSession.update("lhcMyChar.updateTotalRes", lhc_num);
		
	}

	@Override // 2020.04.20 인터페이스 구현(PC방 정보 테이블에서 누적 매출 올리기)
	public void updateTotalPrice(int lhc_num) throws Exception {
		sqlSession.update("lhcMyChar.updateTotalPrice", lhc_num);
		
	}

}
