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
	public List selectAll() throws Exception;
	//전체 회원 수 조회
	public int getArticleMember() throws Exception;
	//회원 한명 데이터 조회
	public LhcMemberVO selectMember(String id) throws Exception;
	//회원 데이터 수정
	public void updateMember(LhcMemberVO vo) throws Exception;
	//회원 데이터 삭제
	public void deleteMember(String id, String pw) throws Exception;
	//아이디 사용가능여부 조회
	public int idAvailCheck(String id) throws Exception;
	
	public String idPwCheck2(String id) throws Exception;
	

}
