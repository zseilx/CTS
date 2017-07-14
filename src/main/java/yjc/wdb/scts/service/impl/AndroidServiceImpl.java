package yjc.wdb.scts.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import yjc.wdb.scts.bean.BillVO;
import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.bean.Coupon_holdVO;
import yjc.wdb.scts.bean.GoodsVO;
import yjc.wdb.scts.bean.UserVO;
import yjc.wdb.scts.dao.AndroidDAO;
import yjc.wdb.scts.service.AndroidService;

@Service
public class AndroidServiceImpl implements AndroidService{
	
	@Inject
	private AndroidDAO dao;
	
	@Override
	public List<BillVO> billList(String user_id, int day) throws Exception {
		
		return dao.billList(user_id, day);
	}

	@Override
	public List<HashMap> billOne(int bill_code) throws Exception {
		
		return dao.billOne(bill_code);
	}

	@Override
	public List<HashMap> settleInfo(String user_id, int bill_code) throws Exception {
		
		return dao.settleInfo(user_id, bill_code);
	}

	@Override
	public List<HashMap> recommandProduct(String user_id) throws Exception {
		
		return dao.recommandProduct(user_id);
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
	@Transactional
	public CouponVO selectSendAndroidCoupon(Map<String, String> vo) throws Exception {
		// 고객이 감지한 타일에서 받을 수 있는 쿠폰들을 검색
		List<HashMap<String, Integer>> coupon_code_Array = dao.TileCoupon_code(vo);
		
		// 해당 쿠폰들을 저장
		Map<String, Object> map = new HashMap<String, Object>();

		if(coupon_code_Array.isEmpty())
			return null;
		
		map.put("user_id", vo.get("user_id"));
		map.put("coupon_code_Array", coupon_code_Array);
		
		// 저장된 쿠폰들 중 받은 것이 있는지 검색
		int coupon = dao.scanCoupon_hold(map);
		
		System.out.println("여기서 어떻게 되나 coupon 값 = " + coupon);
		// 받은게 있을 경우 쿠폰을 주지 않음
		if(coupon > 0)
			return null;
		
		// 받은 게 없으면 쿠폰을 검색해서 랜덤으로 한개 줌
		return dao.selectSendAndroidCoupon(map);
	}

	@Override
	public List<GoodsVO> productSearch(String productName) throws Exception {
		
		return dao.productSearch(productName);
	}

	@Override
	public List<HashMap> eventList() throws Exception {
		// TODO Auto-generated method stub
		return dao.eventList();
	}

	@Override
	public List<HashMap> eventOne(int bbsctt) throws Exception {
		// TODO Auto-generated method stub
		return dao.eventOne(bbsctt);
	}

	@Override
	public void insertCoupon_hold(String user_id, int coupon_code) throws Exception {
		dao.insertCoupon_hold(user_id, coupon_code);
		
	}

	@Override
	public void updateToken(UserVO user) throws Exception {
		
		dao.updateToken(user);
				
	}

	@Override
	public int androidLoginUser(UserVO user) throws Exception {
		
		return dao.androidLoginUser(user);
	}
	
	
	@Override
	public int checkUser(String id) throws Exception {
		
		return dao.checkUser(id);
	}

	@Override
	public int point(String user_id) throws Exception {
		
		return dao.point(user_id);
	}

	@Override
	public String userToken(String user_id) throws Exception {
		
		return dao.userToken(user_id);
	}

	@Override
	public List<HashMap> fcmCoupon(String user_id) throws Exception {
		
		return dao.fcmCoupon(user_id);
	}
	


}
