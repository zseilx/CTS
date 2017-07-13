package yjc.wdb.scts.dao;

import java.util.List;

import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.bean.Coupon_holdVO;

public interface CouponDAO { 
	public List<CouponVO> selectCouponList() throws Exception;
	public void insertCoupon(CouponVO couponVO) throws Exception;
	public void applyCoupon(int coupon_code, int goods_code, int coupon_co) throws Exception;
	public void updateCoupon(CouponVO couponVO) throws Exception;
	public List<CouponVO> couponBasket(String user_id) throws Exception ;
	public void deleteCoupon(int coupon_code) throws Exception;
	public void delCouponBasket(Coupon_holdVO coupon_holdVO) throws Exception;
	public CouponVO selectSendAndroidCoupon() throws Exception;
	public CouponVO selectCouponOne(int coupon_code) throws Exception;
	public int selectCode() throws Exception;
	
}
