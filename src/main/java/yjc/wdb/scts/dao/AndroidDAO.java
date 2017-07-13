package yjc.wdb.scts.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yjc.wdb.scts.bean.BillVO;
import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.bean.Coupon_holdVO;
import yjc.wdb.scts.bean.GoodsVO;

public interface AndroidDAO {
	
	public List<BillVO> billList(String user_id, int day) throws Exception;
	public List<HashMap> billOne(int bill_code) throws Exception;
	public List<HashMap> settleInfo(String user_id, int bill_code) throws Exception;
	public List<HashMap> recommandProduct(String user_id) throws Exception;
	public List<CouponVO> couponBasket(String user_id) throws Exception;
	public void delCouponBasket(Coupon_holdVO coupon_holdVO) throws Exception;
	public CouponVO selectSendAndroidCoupon(Map map) throws Exception;
	public List<GoodsVO> productSearch(String productName) throws Exception;

	public List<HashMap> eventList() throws Exception;
	public List<HashMap> eventOne(int bbsctt) throws Exception;

	public void insertCoupon_hold(String user_id, int coupon_code) throws Exception;



	public int scanCoupon_hold(Map map) throws Exception;
	public List<HashMap<String, Integer>> TileCoupon_code(Map map) throws Exception;
}
