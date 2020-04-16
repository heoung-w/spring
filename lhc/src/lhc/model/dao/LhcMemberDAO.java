package lhc.model.dao;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lhc.model.vo.*;


public interface LhcMemberDAO {
	
	// CRUD에 맞게 작성
	//회원 가입
	public void insertMember(LhcMemberVO vo) throws Exception;
	//아이디 비번 확인
	public int idPwCheck(LhcMemberVO vo) throws Exception;
	//전체 회원 데이터 조회
	public List selectAll(int startRow, int endRow, String sep) throws Exception;
	//전체 회원 수 조회
	public int getCountMember(String sep) throws Exception;
	//회원 한명 데이터 조회
	public LhcMemberVO selectMember(String id) throws Exception;
	//회원 데이터 수정
	public void updateMember(LhcMemberVO vo) throws Exception;
	//회원 데이터 삭제
	public void deleteMember(String id, String pw) throws Exception;
	//아이디 사용가능여부 조회
	public int idAvailCheck(String id) throws Exception;
	
	//포인트 충전
	public int updatePoint(String lhc_id, int point) throws Exception;
	//비밀번호 체크(회원삭제)
	public String idPwCheck2(String id) throws Exception;
	
	// 좋아요 안에 번호 다 빼오기
	public String likeCheck(String id) throws Exception;
	// 좋아요 올려주기
	public void updateLikeNum(String id, String number)throws Exception;

}
