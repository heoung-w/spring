package lhc.model.dao;

import java.util.List;

import lhc.model.vo.LhcMyCharVO;

public interface LhcMyCharDAO {
		// 업체번호를 통해서 업체번호를 통해서 리스트 뽑아오기
		public List getMychar(int lhc_num) throws Exception;
		
		// lhcpcinfo 테이블에 예약한 좌석번호들을 넣고, 예약한 것들마다 rescount 컬럼에 숫자 증가 
		public void updateReservation(String checkboxes, int lhc_num, int lhc_rescount) throws Exception;
		
		// lhcmychar에 전체예약 테이블에 좌석번호랑 ID랑 업체명 넣기
		public void insertMychar(int lhc_num, String checkboxes, String id, String lhc_name) throws Exception;
		
		// 예약 건수가 있는지 없는지 확인후에 int(숫자)로 리턴
		public int countReservation(int lhc_num) throws Exception;
		
		// 예약테이블에서 회원 아이디를 통해서 한개의 예약 시간 조회 2020.04.14 추가 인터페이스
		public LhcMyCharVO getSeats(String lhc_seats, int lhc_num) throws Exception;
		
		// 예약테이블에서 회원 아이디를 통해서 예약 개수 꺼내기 2020.04.14 추가 인터페이스
		public int getReservedCount(String id) throws Exception;

		// 예약테이블에서 pc방 name을 통해서 예약 개수 꺼내기
		public int getReservedCount1(String lhc_name) throws Exception;
			
		// 예약테이블에서 회원 아이디를 통해서 예약내역들을 꺼내기 2020.04.14 추가 인터페이스
		public List getReservedList(String id) throws Exception;
	
		// 예약테이블에서 pc방 명을 통해서 예약내역들을 꺼내기
		public List getReservedList1(String lhc_name) throws Exception;
		
		// 전체 예약 테이블에서 결제하기 누르면 매출 올리는 기능 추가 2020.04.16 추가 인터페이스 
		public void updateLhc_end(int netRev, int lhc_num, String lhc_seats) throws Exception;
		
		// PC방 정보 테이블에서 누적 예약 건수 올리기 (2020.04.20일 추가 인터페이스)
		public void updateTotalRes(int lhc_num) throws Exception;
		
		// PC방 정보 테이블에서 누적 매출  올리기 (2020.04.20일 추가 인터페이스)
		public void updateTotalPrice(int lhc_num) throws Exception;
		
		//전체 예약 건수 
		public int getAllCharCount() throws Exception;
		
		//전체 예약 리스트
		public List getReservedAllList() throws Exception;
}
