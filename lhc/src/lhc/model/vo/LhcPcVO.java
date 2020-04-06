package lhc.model.vo;

public class LhcPcVO {

	private int lhc_num;
	private String lhc_name;
	private String lhc_img;
	private String lhc_phone;
	private String lhc_addr;
	private String lhc_info;
	private String lhc_vendor;
	private int lhc_seats;
	
	public int getLhc_num() {
		return lhc_num;
	}
	public void setLhc_num(int lhc_num) {
		this.lhc_num = lhc_num;
	}
	public String getLhc_name() {
		return lhc_name;
	}
	public void setLhc_name(String lhc_name) {
		this.lhc_name = lhc_name;
	}
	public String getLhc_img() {
		return lhc_img;
	}
	public void setLhc_img(String lhc_img) {
		this.lhc_img = lhc_img;
		System.out.println("LhcPcVO에서 lhc_img"+this.lhc_img);
	}
	public String getLhc_phone() {
		return lhc_phone;
	}
	public void setLhc_phone(String lhc_phone) {
		this.lhc_phone = lhc_phone;
	}
	public String getLhc_addr() {
		return lhc_addr;
	}
	public void setLhc_addr(String lhc_addr) {
		this.lhc_addr = lhc_addr;
	}
	public String getLhc_info() {
		return lhc_info;
	}
	public void setLhc_info(String lhc_info) {
		this.lhc_info = lhc_info;
	}
	public String getLhc_vendor() {
		return lhc_vendor;
	}
	public void setLhc_vendor(String lhc_vendor) {
		this.lhc_vendor = lhc_vendor;
	}
	public int getLhc_seats() {
		return lhc_seats;
	}
	public void setLhc_seats(int lhc_seats) {
		this.lhc_seats = lhc_seats;
	}
	
	
	
}
