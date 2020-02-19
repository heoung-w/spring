package test.spring.model;

import java.util.Date;

public class TestDTO {

	private String id;
	private String pw;
	private Date reg;
	
	
	public TestDTO() {System.out.println("기본생성자");}

	public TestDTO(String id) {
		this.id = id;
		System.out.println("생성자1");
	}
	public TestDTO(String id, Date reg) {
		this.id = id; this.reg = reg;
		System.out.println("생성자2");
	}
	public TestDTO(String id, String pw, Date reg) {
		this.id = id; this.pw = pw; this.reg = reg;
		System.out.println("생성자3");
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public Date getReg() {
		return reg;
	}
	public void setReg(Date reg) {
		this.reg = reg;
	}
	

	
}
