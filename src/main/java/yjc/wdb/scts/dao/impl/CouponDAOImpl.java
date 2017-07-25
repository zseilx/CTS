package yjc.wdb.scts.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.bean.Branch_officeVO;
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
	public void applyCoupon(CouponVO couponVO) throws Exception {

		sql.insert(NAMESPACE+".applyCoupon", couponVO);
	}

	@Override
	public int selectCode() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(NAMESPACE+".selectCode");
	}

	@Override
	public List<Branch_officeVO> selectAllbranchOffice() throws Exception {
		
		return sql.selectList(NAMESPACE+".selectAllbranchOffice");
	}

	@Override
	public List<Branch_officeVO> searchingBranchOffice(String bhf_nm) throws Exception {
		
		return sql.selectList(NAMESPACE+".searchingBranchOffice", bhf_nm);
	}

	@Override
	public List<HashMap> selectAllCategory() throws Exception {
		return sql.selectList(NAMESPACE+".selectAllCategory");
	}

	@Override
	public void applyDetailCoupon(CouponVO couponVO) throws Exception {
	
		sql.insert(NAMESPACE+".applyDetailCoupon", couponVO);
	}

	@Override
	public List<CouponVO> couponAdList() throws Exception {
		
		return sql.selectList(NAMESPACE+".couponAdList");
	}
}
