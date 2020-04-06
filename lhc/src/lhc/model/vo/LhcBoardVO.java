package lhc.model.vo;

import java.sql.Timestamp;

public class LhcBoardVO {

	private int lhc_num;
	private String lhc_writer;
	private String lhc_subject;
	private String lhc_content;
	private Timestamp lhc_reg;
	private int lhc_readcount;
	
	public int getLhc_num() {
		return lhc_num;
	}
	public void setLhc_num(int lhc_num) {
		this.lhc_num = lhc_num;
	}
	public String getLhc_writer() {
		return lhc_writer;
	}
	public void setLhc_writer(String lhc_writer) {
		this.lhc_writer = lhc_writer;
	}
	public String getLhc_subject() {
		return lhc_subject;
	}
	public void setLhc_subject(String lhc_subject) {
		this.lhc_subject = lhc_subject;
	}
	public String getLhc_content() {
		return lhc_content;
	}
	public void setLhc_content(String lhc_content) {
		this.lhc_content = lhc_content;
	}
	public Timestamp getLhc_reg() {
		return lhc_reg;
	}
	public void setLhc_reg(Timestamp lhc_reg) {
		this.lhc_reg = lhc_reg;
	}
	public int getLhc_readcount() {
		return lhc_readcount;
	}
	public void setLhc_readcount(int lhc_readcount) {
		this.lhc_readcount = lhc_readcount;
	}
	
	

	
}
