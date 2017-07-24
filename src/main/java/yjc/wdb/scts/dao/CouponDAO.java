package yjc.wdb.scts.dao;

import java.util.HashMap;
import java.util.List;

import yjc.wdb.scts.bean.Branch_officeVO;
import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.bean.Coupon_holdVO;

public interface CouponDAO { 
	public List<CouponVO> selectCouponList(int bhf_code) throws Exception;
	public void insertCoupon(CouponVO couponVO) throws Exception;
	public void applyCoupon(CouponVO couponVO) throws Exception;
	public void applyDetailCoupon(CouponVO couponVO) throws Exception;
	public void updateCoupon(CouponVO couponVO) throws Exception;
	public List<CouponVO> couponBasket(String user_id) throws Exception ;
	public void deleteCoupon(int coupon_code) throws Exception;
	public void delCouponBasket(Coupon_holdVO coupon_holdVO) throws Exception;
	public CouponVO selectSendAndroidCoupon() throws Exception;
	public CouponVO selectCouponOne(int coupon_code) throws Exception;
	public int selectCode() throws Exception;
	public List<Branch_officeVO> selectAllbranchOffice() throws Exception;
	public List<Branch_officeVO> searchingBranchOffice(String bhf_nm) throws Exception;
	public List<HashMap> selectAllCategory() throws Exception;
	public List<CouponVO> couponAdList() throws Exception;
}
