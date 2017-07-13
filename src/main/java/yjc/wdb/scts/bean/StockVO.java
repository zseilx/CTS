package yjc.wdb.scts.bean;

import java.sql.Date;
import java.util.Map;

public class StockVO {
	private String user_id;
	private int goods_code;
	private String goods_nm;
	private Date wrhousng_de;
	private Date distb_de;
	private int invntry_qy;
	private int lclasctgry_code;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getWrhousng_de() {
		return wrhousng_de;
	}
	public void setWrhousng_de(Date wrhousng_de) {
		this.wrhousng_de = wrhousng_de;
	}
	public Date getDistb_de() {
		return distb_de;
	}
	public void setDistb_de(Date distb_de) {
		this.distb_de = distb_de;
	}
	public int getInvntry_qy() {
		return invntry_qy;
	}
	public void setInvntry_qy(int invntry_qy) {
		this.invntry_qy = invntry_qy;
	}
	public int getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(int goods_code) {
		this.goods_code = goods_code;
	}
	public String getGoods_nm() {
		return goods_nm;
	}
	public void setGoods_nm(String goods_nm) {
		this.goods_nm = goods_nm;
	}
	public int getLclasctgry_code() {
		return lclasctgry_code;
	}
	public void setLclasctgry_code(int lclasctgry_code) {
		this.lclasctgry_code = lclasctgry_code;
	}
	
}
