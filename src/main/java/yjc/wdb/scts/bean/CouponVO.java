package yjc.wdb.scts.bean;

import java.sql.Date;

public class CouponVO {
	private int		coupon_code;
	private String	coupon_nm;
	private String	coupon_cntnts;
	private String	coupon_dscnt;
	private Date	coupon_begin_de;
	private Date	coupon_end_de;
	
	public int getCoupon_code() {
		return coupon_code;
	}
	public void setCoupon_code(int coupon_code) {
		this.coupon_code = coupon_code;
	}
	public String getCoupon_nm() {
		return coupon_nm;
	}
	public void setCoupon_nm(String coupon_nm) {
		this.coupon_nm = coupon_nm;
	}
	public String getCoupon_cntnts() {
		return coupon_cntnts;
	}
	public void setCoupon_cntnts(String coupon_cntnts) {
		this.coupon_cntnts = coupon_cntnts;
	}
	public String getCoupon_dscnt() {
		return coupon_dscnt;
	}
	public void setCoupon_dscnt(String coupon_dscnt) {
		this.coupon_dscnt = coupon_dscnt;
	}
	public Date getCoupon_begin_de() {
		return coupon_begin_de;
	}
	public void setCoupon_begin_de(Date coupon_begin_de) {
		this.coupon_begin_de = coupon_begin_de;
	}
	public Date getCoupon_end_de() {
		return coupon_end_de;
	}
	public void setCoupon_end_de(Date coupon_end_de) {
		this.coupon_end_de = coupon_end_de;
	}
}
