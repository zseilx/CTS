package yjc.wdb.scts.bean;

import java.sql.Date;

public class BillVO {
	private int		bill_code;
	private String	user_id;
	private Date	bill_issu_de;
	private int		bill_totamt;
	
	public int getBill_code() {
		return bill_code;
	}
	public void setBill_code(int bill_code) {
		this.bill_code = bill_code;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getBill_issu_de() {
		return bill_issu_de;
	}
	public void setBill_issu_de(Date bill_issu_de) {
		this.bill_issu_de = bill_issu_de;
	}
	public int getBill_totamt() {
		return bill_totamt;
	}
	public void setBill_totamt(int bill_totamt) {
		this.bill_totamt = bill_totamt;
	}
}
