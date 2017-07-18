package yjc.wdb.scts;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yjc.wdb.scts.bean.CouponVO;
import yjc.wdb.scts.bean.GoodsVO;
import yjc.wdb.scts.service.CouponService;
import yjc.wdb.scts.service.GoodsService;

@Controller
public class CouponController {
	private static final Logger logger = LoggerFactory.getLogger(CouponController.class);
	@Inject
	private CouponService couponService;
	
	@Inject
	private GoodsService goodsService;
	
	/********************************** 쿠폰 관리 부분***************************************/
	/********************************** 쿠폰 관리 부분***************************************/

	@RequestMapping(value = "insertCoupon", method = RequestMethod.POST, produces = "text/plain; charset=UTF-8")
	public String insertCoupon(CouponVO couponVO, Model model, HttpSession session, int coupon_code, int goods_code, int coupon_co, String yPersent) throws Exception {
		String ContentPage = "coupon_Management";
		
		int bhf_code = (int) session.getAttribute("bhf_code");
		
		couponService.insertCoupon(couponVO);
		
		couponService.applyCoupon(coupon_code, goods_code, coupon_co, bhf_code);
		/*logger.info("applyCoupon:"+coupon_code+goods_code+coupon_co);*/
		
		model.addAttribute("main_content", ContentPage);
		return "redirect:coupon_Management";
	}
	
	@RequestMapping(value = "deleteCoupon", method = RequestMethod.POST)
	public String remove(int coupon_code) throws Exception {

		System.out.println("넘어온 쿠폰 코드는 " + coupon_code);
		couponService.deleteCoupon(coupon_code);

		return "redirect:coupon_Management";
	}

	@RequestMapping(value = "coupon_Management", method = RequestMethod.GET)
	public String coupon(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		String ContentPage = "coupon_Management";
		model.addAttribute("main_content", ContentPage);

		int bhf = (int)session.getAttribute("bhf_code");
		
		
		List<CouponVO> Couponlist = couponService.selectCouponList(bhf);
		model.addAttribute("list", Couponlist);
		
		return "mainPage";
	}
	
	@RequestMapping(value="coupon_modify", method=RequestMethod.POST)
	public String couponModify(HttpServletRequest request, HttpSession session, Model model, int coupon_code) throws Exception{
		
		String ContentPage = "coupon_Modify";
		model.addAttribute("main_content", ContentPage);
		
		CouponVO coupon = couponService.selectCouponOne(coupon_code);
		model.addAttribute("coupon",coupon);
		
		List<GoodsVO> GoodsList = goodsService.selectGoodsList();
		model.addAttribute("GoodsList", GoodsList);
		
		return "mainPage";
		
	}
	
	@RequestMapping(value="modify", method=RequestMethod.GET)
	public String modify(HttpServletRequest request,  HttpSession session, Model model, CouponVO couponVO) throws Exception{
		
		String ContentPage = "coupon_Management";
		model.addAttribute("main_content", ContentPage);
		
		couponService.updateCoupon(couponVO);
		
		return "redirect:coupon_Management";
		
	}
	
	@RequestMapping(value = "reg_coupon", method = RequestMethod.GET)
	public String search(Model model) throws Exception {
		String ContentPage = "coupon_Register";
		model.addAttribute("main_content", ContentPage);
		
		List<GoodsVO> GoodsList = goodsService.selectGoodsList();
		model.addAttribute("GoodsList", GoodsList);

		int coupon_max_code = couponService.selectCode();
		model.addAttribute("max_code",coupon_max_code);
		
		logger.info("code:"+coupon_max_code);
		
		return "mainPage";
	}
	
	@RequestMapping(value="search", method=RequestMethod.GET,produces = "text/plain; charset=UTF-8")
	public @ResponseBody String searcing(String goodsName) throws Exception{
		
		List<GoodsVO> GoodsSearch = goodsService.searchGoodsList(goodsName);
		
		JSONObject goodJson;
		JSONArray goodsArray= new JSONArray();
		
		
		
		for(int i=0; i < GoodsSearch.size(); i++){
			
			goodJson = new JSONObject();
			
			goodJson.put("goods_code", GoodsSearch.get(i).getGoods_code());
			goodJson.put("goods_nm", GoodsSearch.get(i).getGoods_nm());
			goodJson.put("goods_pc", GoodsSearch.get(i).getGoods_pc());
			
			goodsArray.add(goodJson);

		}
		 
		JSONObject json = new JSONObject();
		json.put("result", goodsArray);
		
		logger.info("goods: " + json.toString());
		return json.toString();
	}
}
