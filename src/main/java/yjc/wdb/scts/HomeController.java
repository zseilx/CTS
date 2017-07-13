package yjc.wdb.scts;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import yjc.wdb.scts.bean.BeaconVO;
import yjc.wdb.scts.bean.GoodsVO;
import yjc.wdb.scts.bean.PageMaker;
import yjc.wdb.scts.bean.PageVO;
import yjc.wdb.scts.bean.TileVO;
import yjc.wdb.scts.bean.UserVO;
import yjc.wdb.scts.service.BBSService;
import yjc.wdb.scts.service.BeaconService;
import yjc.wdb.scts.service.CouponService;
import yjc.wdb.scts.service.CourseService;
import yjc.wdb.scts.service.Floor_informationService;
import yjc.wdb.scts.service.GoodsService;
import yjc.wdb.scts.service.TileService;
import yjc.wdb.scts.service.UserService;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Inject
	private UserService userService;

	@Inject
	private TileService tileService;
	
	@Inject
	private CourseService courseService;
	
	@Inject
	private GoodsService goodsService;
	
	@Inject
	private CouponService couponService;
	
	@Inject
	private BBSService bbsService;
	
	@Inject
	private BeaconService beaconService;
	
	@Inject
	private Floor_informationService floor_informationService;

	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


	/********************************* 로그인 관련 ***************************************/
	/********************************* 로그인 관련 ***************************************/
	// 처음 접속 시 표시해 주는 로그인 화면
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	// 회원가입
	@RequestMapping(value="/signUp", method=RequestMethod.GET)
	public String sineUp() {
		return "signUp";
	}

	// 로그인 요청 받는 부분
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public String loginPost(UserVO user, 
							HttpServletRequest request, HttpSession session) throws Exception {
		int chk = userService.loginUser(user);
		Map<String, String> branch = userService.knowUserBranch(user.getUser_id());

		Map<String, String> map = new HashMap<String, String>();

		System.out.println(chk + "    " + branch);
		if(chk == 0) {
			map.put("status", "error");
		}
		else if(chk == 1) {
			session.setAttribute("user_id", user.getUser_id());
			session.setAttribute("bhf_code", branch.get("BHF_CODE"));
			session.setAttribute("bhf_nm", branch.get("BHF_NM"));

			map.put("status", "success");
			map.put("location", "mainPage");
			if( branch.equals("1") ) {
				// 본사 페이지 이동시  밑에 있는 "본사페이지"에 해당하는 부분에다가 주소를 넣어주면됨
				// 맵핑 꼭 해놓고 해야지 됨.
				// 위에 있는 mainPage 처럼 맵핑 시켜놓고 해당부분에 해당 맵핑된 주소만 넣어주면 됨.
				map.put("location", "bonsaPage");
			}
		}
		else {
			map.put("status", "error");
		}

		String str = new Gson().toJson(map);
		
		return str;
	}
	
	// 초기 화면 표시용 메인 페이지
	@RequestMapping(value="mainPage", method=RequestMethod.GET)
	public String mainPage(HttpServletRequest request, HttpSession session, Model model) throws Exception{
		// 메인 콘텐츠에서 어떤 페이지를 보여 줄 것인지 저장할 변수.
		String ContentPage = "dashBoard";

		// 실제 뷰 페이지로 메인 콘텐츠 페이지 정보를 넘겨준다.
		model.addAttribute("main_content", ContentPage);

		/*int todayCount = courseService.selectTodayVisitCnt();
		model.addAttribute("todayCount", todayCount);*/
		
		List<HashMap<String, String>> tileList = tileService.selectTileList();
		model.addAttribute("tileList", tileList);

		// 매장에 등록되어 있는 도면 모델에 저장시켜서 넘김
		//int bhf_code = Integer.parseInt((String) session.getAttribute("bhf_code"));
		int bhf_code = (Integer) session.getAttribute("bhf_code");
		int countStory = floor_informationService.selectCountStory(bhf_code);
		model.addAttribute("countStory", countStory);
		
		return "mainPage";
	}

	/********************************* 매장 관리 부분 ***************************************/
	/********************************* 매장 관리 부분 ***************************************/
	// 매장 등록 페이지
	@RequestMapping(value="shop_Register", method=RequestMethod.GET)
	public String shopRegister(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		// 메인 콘텐츠에서 어떤 페이지를 보여 줄 것인지 저장할 변수.
		String ContentPage = "shop_Register";

		// 실제 뷰 페이지로 메인 콘텐츠 페이지 정보를 넘겨준다.
		model.addAttribute("main_content", ContentPage);
		
		// 현재 이부분 수정해야함.
		List<HashMap<String, String>> tileList = tileService.selectTileListUp();
		model.addAttribute("tileList", tileList);

		int bhf_code = (Integer) session.getAttribute("bhf_code");	// 임시로 테스트 위해서 여기서 만들어줌
		List<BeaconVO> beaconList = beaconService.selectAllBeaconList(bhf_code);
		model.addAttribute("beaconList", beaconList);
		
		// 매장에 등록되어 있는 도면 모델에 저장시켜서 넘김
		int countStory = floor_informationService.selectCountStory(bhf_code);
		model.addAttribute("countStory", countStory);
		
		return "mainPage";
	}
	
	@RequestMapping(value="tile_RegisterForm", method=RequestMethod.GET)
	public String tile_RegisterForm(HttpServletRequest request, HttpSession session, Model model) {
		// 메인 콘텐츠에서 어떤 페이지를 보여 줄 것인지 저장할 변수.
		String ContentPage = "tile_RegisterForm";

		// 실제 뷰 페이지로 메인 콘텐츠 페이지 정보를 넘겨준다.
		model.addAttribute("main_content", ContentPage);

		return "mainPage";
	}
	
	@RequestMapping(value="tile_RegisterForm", method=RequestMethod.POST)
	public String tile_RegisterPost(HttpServletRequest request, HttpSession session, TileVO vo) throws Exception {
		
		tileService.insertTile(vo);
		
		logger.debug("타일 정보가 db에 등록 되었음." + vo.getTile_nm() );
		
		return "redirect:mainPage";
	}

	
	// 매출관리
	@RequestMapping(value="sales_Management", method=RequestMethod.GET)
	public String salesManagement(HttpServletRequest request, HttpSession session, Model model) {
		String ContentPage = "sales_Management";

		model.addAttribute("main_content", ContentPage);

		return "mainPage";
	}
	
	//////////////////////////////////////// 관리자 //////////////////////////
	@RequestMapping(value="adSales_Management", method=RequestMethod.GET)
	public String adSales(HttpServletRequest request, HttpSession session, Model model) {
		String ContentPage = "adSales_Management";

		model.addAttribute("main_content", ContentPage);

		return "mainPage";
	}
	
	@RequestMapping(value="adCoupon_Management", method=RequestMethod.GET)
	public String adCoupon(HttpServletRequest request, HttpSession session, Model model) {
		String ContentPage = "adCoupon_Management";

		model.addAttribute("main_content", ContentPage);

		return "mainPage";
	}
	
	@RequestMapping(value="adHelp_List", method=RequestMethod.GET)
	public String adHelp(HttpServletRequest request, HttpSession session, Model model) {
		String ContentPage = "adHelp_List";

		model.addAttribute("main_content", ContentPage);

		return "mainPage";
	}
	
	/////////////////////////////////////////// 문의 사항 //////////////////////////
	
	/********************************* 이벤트 관리 부분 ***************************************/
	/********************************* 이벤트 관리 부분 ***************************************/
	@RequestMapping(value="event_Management", method=RequestMethod.GET)
	public String event(HttpServletRequest request, HttpSession session, Model model) {
		String ContentPage = "event_Management";

		model.addAttribute("main_content", ContentPage);

		return "mainPage";
	}

	
	/********************************* User Profile ***************************************/
	/********************************* User Profile ***************************************/
	@RequestMapping(value="myProfile", method=RequestMethod.GET)
	public String myProfile(HttpServletRequest request, HttpSession session, Model model) {
		
		String ContentPage = "myProfile";
		
		model.addAttribute("main_content", ContentPage);

		return "mainPage";
	}
	
	/***************************************
	 * 2017_05_09
	 * 테스트용 페이지들
	 * @param model
	 * @return
	 * @throws Exception
	 */
	/*
	@RequestMapping(value="avgStayTest")
	public String avgStayTest(Model model) throws Exception{
		List<HashMap<String, String>> list = positionService.avgStay();
		logger.debug(list.toString());
		model.addAttribute("list", list);
		return "test/avgStayTest";
	}

	@RequestMapping(value="visit_countTest")
	public String visit_countTest(Model model) throws Exception{
		List<HashMap<String, String>> list = positionService.visit_count();
		logger.debug(list.toString());
		model.addAttribute("list", list);
		return "test/visit_countTest";
	}

	@RequestMapping(value="probabilityTest")
	public String probabilityTest(Model model) throws Exception{
		List<HashMap<String, String>> list = positionService.probability();
		logger.debug(list.toString());
		model.addAttribute("list", list);
		return "test/probabilityTest";
	}
*/
	/****************************** 예지쓰 *************************************/
	/****************************** 예지쓰 *************************************/

	@RequestMapping(value="widgets")
	public String widgets() {
		return "NiceAdmin/widgets";
	}

	@RequestMapping(value="404")
	public String errorPage() {
		return "NiceAdmin/404";
	}

	@RequestMapping(value="register_shop")
	public String register_shop() {
		return "NiceAdmin/register_shop";
	}

	@RequestMapping(value="register_shopForm")
	public String register_shopForm() {
		return "NiceAdmin/register_shopForm";
	}

	@RequestMapping(value="form")
	public String form(){
		return "NiceAdmin/form";

	}

	@RequestMapping(value="register_tileInfo")
	public String register_tileInfo() {
		return "NiceAdmin/register_tileInfo";
	}

	@RequestMapping(value="register_product")
	public String register_product() {
		return "NiceAdmin/register_product";
	}

	@RequestMapping(value="list_product")
	public String list_product() {
		return "NiceAdmin/product_list";
	}

	@RequestMapping(value="info_product")
	public String info_product() {
		return "NiceAdmin/product_info";
	}

	

}
