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

import yjc.wdb.scts.bean.Branch_officeVO;

import yjc.wdb.scts.service.Branch_officeService;

@Controller
public class HeadOfficeController {
	
	@Inject
	private Branch_officeService branch_officeService;
		
	private static final Logger logger = LoggerFactory.getLogger(HeadOfficeController.class);
	
	@RequestMapping(value="headOfficeMain", method=RequestMethod.GET)
	public String headOfficeMain(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		
		String ContentPage = "headOffice";
		model.addAttribute("main_content", ContentPage);
		
		List<Branch_officeVO> gradeList = branch_officeService.selectGrade();
		model.addAttribute("gradeList", gradeList);
		
		return "mainPage";
	}
	
	@RequestMapping(value="bhfSearching", method=RequestMethod.GET,produces = "text/plain; charset=UTF-8")
	public @ResponseBody String searching(String bhf_nm) throws Exception{
		
		System.out.println(bhf_nm);
		
		List<Branch_officeVO> branchSearch = branch_officeService.searchBranchList(bhf_nm);
		JSONObject branchJson;
		JSONArray branchArray= new JSONArray();
		
		for(int i=0; i < branchSearch.size(); i++){
			
			branchJson = new JSONObject();
			
			branchJson.put("bhf_code", branchSearch.get(i).getBhf_code());
			branchJson.put("bhf_nm", branchSearch.get(i).getBhf_nm());
			branchJson.put("bhf_telno", branchSearch.get(i).getBhf_telno());
			
			branchArray.add(branchJson);

		}
		 
		JSONObject json = new JSONObject();
		json.put("result", branchArray);
		
		logger.info("branch: " + json.toString());
		return json.toString();
	}
	
	@RequestMapping(value="branchGrade", method=RequestMethod.GET,produces = "text/plain; charset=UTF-8")
	public @ResponseBody String grade() throws Exception{

		List<Branch_officeVO> branchGrade = branch_officeService.selectGrade();
		JSONObject gradeJson;
		JSONArray gradeArray= new JSONArray();
		
		for(int i=0; i < branchGrade.size(); i++){
			
			gradeJson = new JSONObject();
			
			gradeJson.put("bhf_code", branchGrade.get(i).getBhf_code());
			gradeJson.put("bhf_nm", branchGrade.get(i).getBhf_nm());
			gradeJson.put("totalPrice", branchGrade.get(i).getTotalPrice());
			
			gradeArray.add(gradeJson);

		}
		 
		JSONObject json = new JSONObject();
		json.put("result", gradeArray);
		
		logger.info("grade: " + json.toString());
		return json.toString();
	}
}
