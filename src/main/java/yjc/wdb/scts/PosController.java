package yjc.wdb.scts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import yjc.wdb.scts.bean.GoodsVO;
import yjc.wdb.scts.service.BillService;
import yjc.wdb.scts.service.Coupon_holdService;
import yjc.wdb.scts.service.GoodsService;

@Controller
public class PosController {

	private static final Logger logger = LoggerFactory.getLogger(PosController.class);

	@Inject
	GoodsService goodsService;

	@Inject
	Coupon_holdService coupon_holdService;

	@Inject
	BillService billService;

	private String str;


	/********************************* ���� �ý��� �κ� ***************************************/
	/********************************* ���� �ý��� �κ� 
	 * @throws Exception ***************************************/
	@RequestMapping(value="posSystem", method=RequestMethod.GET)
	public String posSystem(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		String ContentPage = "posSystem";

		model.addAttribute("main_content", ContentPage);


		List<HashMap> list = billService.getSettlement();
		
		model.addAttribute("list", list);

		return "mainPage";
	}

	@RequestMapping(value="payment", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> payment(@RequestBody JSONObject json, HttpSession session) throws Exception{
		System.out.println("���̸�Ʈ ������ ����");
		System.out.println(json);
		try {

			//List<HashMap<String, String>> purchase_goodsList = new ArrayList<HashMap<String,String>>();
			List<HashMap<String, String>> purchase_goodsList = null;


			Map map = (Map) new JSONParser().parse(json.toJSONString());

			List<HashMap<String, String>> goodsList = (List) map.get("goodsList");
			
			System.out.println(map.get("stprc"));
			
			JSONArray jArrayPrice = (JSONArray) new JSONParser().parse(json.get("stprc").toString());
					
			JSONArray jArraySettle = (JSONArray) new JSONParser().parse(json.get("setle_mth_code").toString());
			
			int[] stprc = new int[jArrayPrice.size()];
			int[] setle_mth_code = new int[jArraySettle.size()];
			
			for(int i = 0; i < jArrayPrice.size(); i++){
				
				stprc[i] = Integer.parseInt(jArrayPrice.get(i).toString());
				setle_mth_code[i] = Integer.parseInt(jArraySettle.get(i).toString());
				
			}
			
			
			String user_id = null;
			if(map.get("user_id") != null) {
				user_id = map.get("user_id").toString();	// ���� ���̵�
			}
					
			Map billMap = new HashMap<String, String>();
			int bhf_code = (int) session.getAttribute("bhf_code");
			billMap.put("user_id", user_id);
			billMap.put("bhf_code", bhf_code);

			billService.payment(billMap, stprc, setle_mth_code, goodsList);

			System.out.println("�������� ���� �Ϸ�");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	/* posSystem.js
	 * ���� ���������� ���ڵ� �Է� �� ��ǰ ����� ������ �� ��� ��ȸ�ؼ� ��ǰ�� ������
	 * ���������� �����ִ� ���۽� ó����
	 */
	@RequestMapping(value="getGoodsAjax", method=RequestMethod.POST,
			produces = "text/plain; charset=UTF-8")
	@ResponseBody 
	public ResponseEntity<String> getGoodsAjax(@RequestParam("goods_code") int goods_code) throws Exception {

		GoodsVO vo = goodsService.selectGoodsOne(goods_code);

		if(vo == null) {
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		String str = new Gson().toJson(vo);

		System.out.println(str);

		return new ResponseEntity<String>(str, HttpStatus.OK);
	}


	/* posSystem.js
	 * ���� ����Ʈ ��� â���� ������� ���̵� �Է��Ͽ��� ��
	 * ����ڰ� ���� ���Ÿ�Ͽ��� ����� �� �ִ� �������� ��� ��ȸ�ؼ�
	 * ���������� �����ִ� ������ �ϴ� ���۽� ó����
	 */
	@RequestMapping(value="getUserCoupon", method=RequestMethod.POST,
			produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<String> getUserCoupon(@RequestBody JSONObject json) throws Exception {
		String str;

		try {

			System.out.println(json.toString());

			// �ش� �ʿ��� ���� �ֹ���Ͽ� �÷��� �ִ� ��ǰ ��ȣ��, ���� ���̵� ��
			HashMap<String, Object> map = new ObjectMapper().readValue(json.toString(), HashMap.class);

			List<HashMap<String, String>> userCouponList = coupon_holdService.selectCouponHoldGoods(map);

			if(userCouponList == null) {
				return new ResponseEntity<String>(HttpStatus.OK);
			}
			str = new Gson().toJson(userCouponList);

			System.out.println(str);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}



	@RequestMapping(value="searchGoodsList", method=RequestMethod.GET,
			produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String searchGoodsList(String goods_nm) throws Exception {


		String str;


		List<GoodsVO> searchGoodsList = goodsService.searchGoodsList(goods_nm);

		str = new Gson().toJson(searchGoodsList);

		return str;
	}
	
	

}
