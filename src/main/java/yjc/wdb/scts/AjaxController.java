package yjc.wdb.scts;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import yjc.wdb.scts.bean.BeaconVO;
import yjc.wdb.scts.bean.GoodsVO;
import yjc.wdb.scts.bean.TileVO;
import yjc.wdb.scts.dao.CategoryDAO;
import yjc.wdb.scts.service.BeaconService;
import yjc.wdb.scts.service.Branch_officeService;
import yjc.wdb.scts.service.CategoryService;
import yjc.wdb.scts.service.CourseService;
import yjc.wdb.scts.service.Floor_informationService;
import yjc.wdb.scts.service.TileService;

@Controller
public class AjaxController {

	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);

	@Inject
	TileService tileService;

	@Inject
	BeaconService beaconService;

	@Inject
	Branch_officeService branchService;

	@Inject
	CourseService courseService;

	@Inject
	CategoryService categoryService;

	@Inject
	Floor_informationService floor_informationService;

	/* shop_Register.js
	 * ������ ���������� �������� Ÿ���� Ŭ�������� �߻��ϴ� ���۽� ���
	 * �ش� Ÿ���� ������ ��񿡼� �����ͼ� �����ش�
	 */
	@RequestMapping(value="shopTileClick", method=RequestMethod.POST)
	@ResponseBody
	public String shopTileClick(@RequestParam("drw_code") int drw_code,
			@RequestParam("X_index") int X_index, @RequestParam("Y_index") int Y_index) throws Exception {

		logger.info("X = " + X_index + "  Y = " + Y_index);

		TileVO clickTile = new TileVO();
		clickTile.setDrw_code(drw_code);
		clickTile.setTile_crdnt_x(X_index);
		clickTile.setTile_crdnt_y(Y_index);

		HashMap<String, String> tile = tileService.selectTile_LocationOne(clickTile);

		String str = new Gson().toJson(tile);

		System.out.println(str);

		return str;
	}

	/* shop_Register.js
	 * ������ ���������� �������� Ÿ���� Ŭ�������� �߻��ϴ� ���۽� ���
	 * ���� ī�װ� ������
	 */
	@RequestMapping(value="shopDetailCategory", method=RequestMethod.POST, produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String shopDetailCategory(@RequestParam("lclasctgry_code") int lclasctgry_code) throws Exception {

		Map map = new HashMap();

		map.put("lclasctgry_code", lclasctgry_code);

		List<Map> detailCategoryList = categoryService.selectDetail_categoryList(map);

		String str = new Gson().toJson(detailCategoryList);

		System.out.println(str);

		return str;
	}

	/* shop_Register.js
	 * ������ ���������� ī�װ� ������� Ŭ����
	 * ��з� ī�װ� ������
	 */
	@RequestMapping(value="shopLargeCategory", method=RequestMethod.POST, produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String shopLargeCategory() throws Exception {

		List<Map> largeCategoryList = categoryService.selectLarge_categoryList();

		String str = new Gson().toJson(largeCategoryList);

		System.out.println(str);

		return str;
	}


	/* shop_Register.js
	 * ������ ���������� ī�װ� ������� Ŭ����
	 * ��з� ī�װ� ������
	 */
	@RequestMapping(value="categorySetAllZone", method=RequestMethod.POST, produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String categorySetAllZone(@RequestParam("detailctgry_code") int detailctgry_code, @RequestParam("drw_code") int drw_code) throws Exception {

		Map map = new HashMap();

		map.put("detailctgry_code", detailctgry_code);
		map.put("drw_code", drw_code);

		List<Map> largeCategoryList = categoryService.selectCategoryLocation(map);

		String str = new Gson().toJson(largeCategoryList);

		System.out.println(str);

		return str;
	}


	/* shop_Register.js
	 * ������ ���������� ī�װ� ������� Ŭ����
	 * ��з� ī�װ� ������
	 */
	@RequestMapping(value="setTileCategory", method=RequestMethod.POST,  produces = "text/plain; charset=UTF-8")
	/*, produces="application/json; charset=utf-8",
			headers = "content-type=application/x-www-form-urlencoded")*/
	@ResponseBody
	public String setTileCategory(@RequestBody JSONObject jObject) throws Exception {

		String str = null;
		try {

			Map map = new ObjectMapper().readValue(jObject.toString(), HashMap.class);

			System.out.println(jObject);

			System.out.println(map.get("tileList"));

			List<Map> list = categoryService.insertDetail_category_location(map);
			str = new Gson().toJson(list);

		} catch (Exception e) {
			e.printStackTrace();
		}


		return str;
	}
	
	

	/* shop_Register.js
	 * ������ ���������� Ÿ��Ŭ�� �� Ÿ�Ͽ� ������ ��ϵǾ� ���� ���� ��
	 * ���ܵ�� ��ư�� ���� �� ��񿡼� ��밡���� ������ �������� �ҷ��ͼ� ���۽��� ���� 
	 */
	@RequestMapping(value="getBeaconList", method=RequestMethod.POST)
	@ResponseBody
	public String getBeacon(HttpSession session) throws Exception {

		int bhf_code = (int) session.getAttribute("bhf_code");
		List<BeaconVO> beaconList = beaconService.selectSetBeaconList(bhf_code);

		String str = new Gson().toJson(beaconList);

		System.out.println(str);

		return str;
	}


	@RequestMapping(value="detailCategroyGoods", method=RequestMethod.GET, produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String detailCategroyGoods(int detailctgry_code) throws Exception {

		List<GoodsVO> list = categoryService.detailCategroyGoods(detailctgry_code);

		String str = new Gson().toJson(list);

		System.out.println(str);

		return str;
	}


	@RequestMapping(value="goods_locationList", method=RequestMethod.GET, produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String goods_locationList(int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception {

		List<GoodsVO> list = categoryService.goods_locationList(drw_code, tile_crdnt_x, tile_crdnt_y);

		String str = new Gson().toJson(list);

		System.out.println(str);

		return str;
	}
	
	@RequestMapping(value="getGoods_locationList", method=RequestMethod.GET, produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String getGoods_locationList(int tile_code) throws Exception {

		List<GoodsVO> list = tileService.goods_locationList(tile_code);

		String str = new Gson().toJson(list);

		System.out.println(str);

		return str;
	}



	@RequestMapping(value="insertGoods_location", method=RequestMethod.POST,  produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String insertGoods_location(@RequestBody JSONObject jObject) throws Exception {

		String str = null;
		try {

			Map map = new ObjectMapper().readValue(jObject.toString(), HashMap.class);

			System.out.println(jObject);

			System.out.println(map.get("goodsList"));
			
			int drw_code = Integer.parseInt(map.get("drw_code").toString());
			int tile_crdnt_x = Integer.parseInt(map.get("tile_crdnt_x").toString());
			int tile_crdnt_y = Integer.parseInt(map.get("tile_crdnt_y").toString());

			List<GoodsVO> list = categoryService.insertGoods_location(drw_code, tile_crdnt_x, tile_crdnt_y, map);
			str = new Gson().toJson(list);

		} catch (Exception e) {
			e.printStackTrace();
		}


		return str;
	}



	/* dashBoard.js
	 * �뽬���忡�� ������ Ÿ���� ������ ��� �߻��ϴ� ���۽� ���
	 * ���� �� �޼���� ���� ����� �� �����͸� �ܾ���� ���� �ƴ�
	 */
	@RequestMapping(value="dashBoardTile", method=RequestMethod.POST)
	@ResponseBody
	public String dashBoardTile(@RequestParam("drw_code") int drw_code,
			@RequestParam("X_index") int X_index, @RequestParam("Y_index") int Y_index) throws Exception {

		logger.info("X = " + X_index + "  Y = " + Y_index);

		TileVO clickTile = new TileVO();
		clickTile.setDrw_code(drw_code);
		clickTile.setTile_crdnt_x(X_index);
		clickTile.setTile_crdnt_y(Y_index);

		HashMap<String, String> tile = tileService.selectTile_LocationOne(clickTile);

		String str = new Gson().toJson(tile);

		System.out.println(str);

		return str;
	}

	/* shop_Register.js
	 * Ÿ�Ͽ� ���������� �����ϴ� ���۽�
	 */
	@RequestMapping(value="setTileBeacon", method=RequestMethod.POST)
	@ResponseBody
	public String setTileBeacon(@RequestBody JSONObject json) throws Exception {

		System.out.println( json.get("tile_code") + "  Ÿ��  " + json.get("beacon_mjr") + "  ���� ������  " + json.get("beacon_mnr") );

		HashMap<String, String> vo = new HashMap<String, String>();


		vo.put("tile_code", json.get("tile_code").toString());
		vo.put("beacon_mjr", json.get("beacon_mjr").toString());
		vo.put("beacon_mnr", json.get("beacon_mnr").toString());

		tileService.updateTileBeaconSet(vo);

		// ���� �̺κп� ���� ���������� Ÿ�Ϸ� ������.
		/*
		 *  ���� shop_Register.js �� �̺κа� �����Ǵ� ���۽��� ������
		 *  ����� �ڵ��� ���� ���� ���� �κ� �� �պ�����
		 */
		return "success";
	}

	@RequestMapping(value="getTile_goodsList", method=RequestMethod.GET, produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String getTile_goodsList(int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception {

		System.out.println("�����!!");
		JSONObject list = tileService.tile_goods(drw_code, tile_crdnt_x, tile_crdnt_y);

		System.out.println("list �Ӵ�" + list.toString());
		
		return list.toString();
	}

	/* shop_Register.js
	 * �� ������ Ÿ�� ���� ���ε�
	 */
	@RequestMapping(value="tileReload", method=RequestMethod.POST)
	@ResponseBody
	public String tileReload(@RequestParam("floor") int floor, HttpSession session) throws Exception {

		Map map = new HashMap();

		int bhf_code = (int) session.getAttribute("bhf_code");
		floor += 1;

		map.put("bhf_code", bhf_code);
		map.put("floor", floor);

		List<HashMap<String, String>> tileList = tileService.selectTileListUp(map);

		String str = new Gson().toJson(tileList);

		return str;

	}

	/* shop_Register.js
	 * ���� ��� ��ư Ŭ���� ���۽��� �����ϴ� �����, �������� db���� �˻��Ͽ� �Ѱ��ִ� ����
	 */

	@RequestMapping(value="shop_RegisterForm", method=RequestMethod.GET, produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String shop_RegisterForm(HttpSession session) throws Exception{


		int bhf_code =(int) session.getAttribute("bhf_code");
		List<Integer> knowFloor = branchService.knowFloor(bhf_code);

		String str = new Gson().toJson(knowFloor);

		System.out.println(str);

		return str;
	}



	@RequestMapping(value="loadDetailCategory", method=RequestMethod.GET, produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String loadDetailCategory(int floor, HttpSession session) throws Exception {


		int bhf_code = (int) session.getAttribute("bhf_code");

		HashMap map1 = floor_informationService.selectDrawingOne(bhf_code, floor);


		Map map = new HashMap();
		int drw_code = Integer.parseInt(map1.get("drw_code").toString());

		List<HashMap> categoryList = categoryService.loadDetailCategory(drw_code);

		map.put("categoryList", categoryList);

		String str = new Gson().toJson(map);

		return str;
	}
	
	@RequestMapping(value="setReTileCategory", method=RequestMethod.POST,  produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String setReTileCategory(@RequestBody JSONObject jObject) throws Exception {

		String str = null;
		try {

			Map map = new ObjectMapper().readValue(jObject.toString(), HashMap.class);

			System.out.println(jObject);

			System.out.println(map.get("tileList"));

			tileService.insertDetail_category_location(map);
			
			str = "success";
			


		} catch (Exception e) {
			e.printStackTrace();
			str = "failed";
		}


		return str;
	}

	
	@RequestMapping(value="setGoods_location", method=RequestMethod.POST,  produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String setGoodsLocation(@RequestBody JSONObject jObject) throws Exception {

		String str = null;
		try {

			Map map = new ObjectMapper().readValue(jObject.toString(), HashMap.class);

			tileService.insertGoods_location(map);
			
			str = "success";
			


		} catch (Exception e) {
			e.printStackTrace();
			str = "failed";
		}


		return str;
	}




	/* dashBoard.js
	 * ������ ���������� �������� Ÿ���� Ŭ�������� �߻��ϴ� ���۽� ���
	 * �ش� Ÿ�Ͽ��� �߻��� ������ ������ ���� �����͵��� ���� �з��� ������
	 * �뽬���忡�� ǥ���� �� �ֵ��� ������
	 */
	@RequestMapping(value="getTileData", method=RequestMethod.POST)
	@ResponseBody
	public String getTileData(int drw_code, int X_index, int Y_index) throws Exception {

		logger.info("X = " + X_index + "  Y = " + Y_index);

		TileVO vo = new TileVO();
		vo.setDrw_code(drw_code);
		vo.setTile_crdnt_x(X_index);
		vo.setTile_crdnt_y(Y_index);

		// HashMap<String, String> tile = tileService.selectTile_LocationOne(Map_XY);

		// �ʿ��� �����͵� ��񿡼� ����ͼ� ����
		HashMap<String, String> pro = courseService.tileProbability(vo);
		List<HashMap<String, String>> user = courseService.tileUserinfo(vo);

		// ������ ������ ��ü �ֱ� ���� jsonObject
		JSONObject json = new JSONObject();

		// db���� ������ �����͵� json���� ��ȯ
		JsonArray jsonUser = (JsonArray) new JSONParser().parse(new Gson().toJson(user));
		JSONObject jsonPro = (JSONObject) new JSONParser().parse(new Gson().toJson(pro));

		// jsonObject�� ��� �����͵��� ����
		json.put("jsonUser", jsonUser);
		json.put("jsonPro", jsonPro);
		json.put("status", "success");

		return json.toString();
	}

	// Ÿ�� �ϳ��� �� ��� �ӹ� �ð�
	@RequestMapping(value="oneTileAvgTime", method=RequestMethod.GET)
	@ResponseBody
	public JSONObject oneTileAvgTime(int day, int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception{
		List<HashMap> list = courseService.oneTileAvgTime(day, drw_code, tile_crdnt_x, tile_crdnt_y);

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("avgTime", list.get(0).get("avgTime"));
		jsonObj.put("tile_nm", list.get(0).get("tile_nm"));

		return jsonObj;
	}

	@RequestMapping(value="tileGender", method=RequestMethod.GET)
	@ResponseBody
	public String tileGender(int day, HttpSession session) throws Exception{
		
		int bhf_code = (int) session.getAttribute("bhf_code");
		
		List<HashMap> list = courseService.tileGender(day, bhf_code);

		JSONArray tileGenderArray = new JSONArray();
		for(int i = 0; i < list.size(); i++){

			JSONObject tileGenderObj = new JSONObject();

			tileGenderObj.put("probability", list.get(i).get("probability"));
			tileGenderObj.put("user_sexdstn", list.get(i).get("user_sexdstn"));

			tileGenderArray.add(tileGenderObj);			
		}

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("tileGender", tileGenderArray);

		return jsonObj.toJSONString();
	}





	@RequestMapping(value="tileAge", method=RequestMethod.GET)
	@ResponseBody
	public String tileAge(int day, HttpSession session) throws Exception{
		
		int bhf_code = (int) session.getAttribute("bhf_code");
		
		List<HashMap> list = courseService.tileAge(day, bhf_code);

		JSONArray tileAgeArray = new JSONArray();
		for(int i = 0; i < list.size(); i++){

			JSONObject tileAgeObj = new JSONObject();

			tileAgeObj.put("probability", list.get(i).get("probability"));
			tileAgeObj.put("agegroup", list.get(i).get("agegroup").toString());

			tileAgeArray.add(tileAgeObj);			
		}

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("tileAge", tileAgeArray);

		return jsonObj.toJSONString();
	}

	@RequestMapping(value="oneTileGender", method=RequestMethod.GET)
	@ResponseBody
	public JSONObject oneTileGender(int day, int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception{

		List<HashMap> list = courseService.oneTileGender(day, drw_code, tile_crdnt_x, tile_crdnt_y);

		System.out.println("list : " + list.toString());
		JSONArray tileGenderArray = new JSONArray();
		for(int i = 0; i < list.size(); i++){

			JSONObject tileGenderObj = new JSONObject();

			tileGenderObj.put("probability", list.get(i).get("probability"));
			tileGenderObj.put("user_sexdstn", list.get(i).get("user_sexdstn"));

			tileGenderArray.add(tileGenderObj);			
		}

		JSONObject jsonTileObj = new JSONObject();
		jsonTileObj.put("oneTileGender", tileGenderArray);

		return jsonTileObj;
	}


	@RequestMapping(value="oneTileAge", method=RequestMethod.GET)
	@ResponseBody
	public String oneTileAge(int day, int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception{
		List<HashMap> list = courseService.oneTileAge(day, drw_code, tile_crdnt_x, tile_crdnt_y);

		JSONArray tileAgeArray = new JSONArray();
		for(int i = 0; i < list.size(); i++){

			JSONObject tileAgeObj = new JSONObject();

			tileAgeObj.put("probability", list.get(i).get("probability"));
			tileAgeObj.put("agegroup", list.get(i).get("agegroup").toString());

			tileAgeArray.add(tileAgeObj);			
		}

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("tileAge", tileAgeArray);

		return jsonObj.toJSONString();
	}


	@RequestMapping(value="tileTotal", method=RequestMethod.GET)
	@ResponseBody
	public String tileTotal(int day, int drw_code, int tile_crdnt_x, int tile_crdnt_y) throws Exception{
		List<HashMap> list = courseService.tileTotal(day, drw_code, tile_crdnt_x, tile_crdnt_y);

		JSONArray tileTotalArray = new JSONArray();
		for(int i = 0; i < list.size(); i++){

			JSONObject tileTotalObj = new JSONObject();

			tileTotalObj.put("probability", list.get(i).get("probability"));
			tileTotalObj.put("cours_pasng_time", list.get(i).get("cours_pasng_time").toString());
			tileTotalObj.put("tile_visit", list.get(i).get("tile_visit"));

			tileTotalArray.add(tileTotalObj);			
		}

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("tileTotal", tileTotalArray);

		return jsonObj.toJSONString();
	}
}
