package yjc.wdb.scts.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.bean.Coupon_holdVO;
import yjc.wdb.scts.dao.CouponDAO;
import yjc.wdb.scts.service.CouponService;


@Service
public class CouponServiceImpl implements CouponService {
	
	@Inject
	private CouponDAO dao;

	@Override
	public void insertCoupon(CouponVO couponVO) throws Exception {
		// TODO Auto-generated method stub
		dao.insertCoupon(couponVO);
	}

	@Override
	public List<CouponVO> couponBasket(String user_id) throws Exception {
		
		return dao.couponBasket(user_id);
	}

	@Override
	public void delCouponBasket(Coupon_holdVO coupon_holdVO) throws Exception {
		
		dao.delCouponBasket(coupon_holdVO);
		
	}

	@Override
	public List<CouponVO> selectCouponList(int bhf_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectCouponList(bhf_code);
	}

	@Override
	public CouponVO selectSendAndroidCoupon() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectSendAndroidCoupon();
	}
	
	@Override
	public void updateCoupon(CouponVO couponVO) throws Exception {
		// TODO Auto-generated method stub
		dao.updateCoupon(couponVO);
	}

	@Override
	public void deleteCoupon(int coupon_code) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteCoupon(coupon_code);
	}

	@Override
	public CouponVO selectCouponOne(int coupon_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectCouponOne(coupon_code);
	}

	@Override
	public void applyCoupon(int coupon_code, int goods_code, int coupon_co) throws Exception {
		// TODO Auto-generated method stub
		dao.applyCoupon(coupon_code,goods_code,coupon_co);
	}

	@Override
	public int selectCode() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectCode();
	}

}
