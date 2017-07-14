package yjc.wdb.scts.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.bean.Coupon_holdVO;
import yjc.wdb.scts.bean.GoodsVO;
import yjc.wdb.scts.dao.CouponDAO;


@Repository
public class CouponDAOImpl implements CouponDAO{
	
	private static final String NAMESPACE = "yjc.wdb.mapper.CouponMapper";
	
	@Inject
	private SqlSession sql;


	@Override
	public void insertCoupon(CouponVO couponVO) throws Exception {
		// TODO Auto-generated method stub
		sql.insert(NAMESPACE+".insertCoupon", couponVO);
	}
	
	@Override
	public List<CouponVO> couponBasket(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".couponList", user_id);
	}

	@Override
	public void delCouponBasket(Coupon_holdVO coupon_holdVO) throws Exception {
		
		sql.delete(NAMESPACE+".delCouponBasket", coupon_holdVO);
		
	}

	@Override
	public List<CouponVO> selectCouponList(int bhf_code) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".selectCouponList", bhf_code);
	}

	@Override
	public CouponVO selectSendAndroidCoupon() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(NAMESPACE+".selectSendAndroidCoupon");
	}
	
	@Override
	public void updateCoupon(CouponVO couponVO) throws Exception {
		// TODO Auto-generated method stub
		sql.update(NAMESPACE+".updateCoupon" , couponVO);
	}

	@Override
	public void deleteCoupon(int coupon_code) throws Exception {
		// TODO Auto-generated method stub
		sql.delete(NAMESPACE+".deleteCoupon", coupon_code);
	}

	@Override
	public CouponVO selectCouponOne(int coupon_code) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(NAMESPACE+".selectCouponOne", coupon_code);
	}

	@Override
	public void applyCoupon(int coupon_code, int goods_code, int coupon_co) throws Exception {
		Map<String,Integer> map = new HashMap();
		map.put("coupon_code", coupon_code);
		map.put("goods_code", goods_code);
		map.put("coupon_code", coupon_co);
		sql.insert(NAMESPACE+".applyCoupon", map);
	}

	@Override
	public int selectCode() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(NAMESPACE+".selectCode");
	}

}
