package yjc.wdb.scts;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import yjc.wdb.scts.bean.HelpVO;
import yjc.wdb.scts.bean.PageMaker;
import yjc.wdb.scts.bean.PageVO;
import yjc.wdb.scts.service.HelpService;

@Controller
public class HelpController {
	@Inject
	private HelpService helpService;
	
	@RequestMapping(value="help_List", method=RequestMethod.GET)
	public String helpList(Model model, @ModelAttribute("cri") PageVO cri, HttpSession session) throws Exception{
		String ContentPage = "help_List";
		
		int bhf_code = (int) session.getAttribute("bhf_code");
		
		cri.setBhf_code(bhf_code);
		model.addAttribute("list", helpService.listSearch(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(helpService.countSearch(cri));
		System.out.println(cri.isMsg());
		model.addAttribute("msg", cri.isMsg());
		model.addAttribute("pageMaker", pageMaker);
		System.out.println(pageMaker.toString());
		model.addAttribute("main_content", ContentPage);		
		return "mainPage";
	}
	
	@RequestMapping(value="searchHelp", method=RequestMethod.GET)
	public String searchHelp(@ModelAttribute("cri") PageVO cri, Model model, RedirectAttributes rttr) throws Exception{
		String ContentPage = "help_List";
		
		model.addAttribute("list", helpService.listSearch(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(helpService.countSearch(cri));
		
		cri.setMsg(false);
		model.addAttribute("msg", cri.isMsg());
		model.addAttribute("pageMaker", pageMaker);
		
		model.addAttribute("main_content", ContentPage);
		return "mainPage";
	}
	
	@RequestMapping(value="readPage", method=RequestMethod.GET)
	public String readPage(@RequestParam("bbsctt_code") int bbsctt_code, @ModelAttribute("cri") PageVO cri, HelpVO vo, Model model)throws Exception{
		String ContentPage = "help_Read";
		
		System.out.println("여기는 리드 페이지 "+bbsctt_code);
		System.out.println("그리고 "+ vo.getBbsctt_code());
		
		model.addAttribute("msg", cri.isMsg());
		model.addAttribute(helpService.readHelp(bbsctt_code));
		model.addAttribute("main_content", ContentPage);
		return "mainPage";
	}
	
	
	@RequestMapping(value="insertHelp", method=RequestMethod.GET)
	public String insertHelpGET(@ModelAttribute("cri") PageVO cri, RedirectAttributes rttr, Model model)throws Exception{
		String ContentPage = "help_Register";
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		model.addAttribute("msg", cri.isMsg());
		model.addAttribute("main_content", ContentPage);
		
		return "mainPage";
	}
	
	@RequestMapping(value="insertHelp", method=RequestMethod.POST)
	public String insertHelpPOST(HelpVO vo, @ModelAttribute("cri") PageVO cri, RedirectAttributes rttr, Model model) throws Exception{
		
		helpService.createHelp(vo);
		
		model.addAttribute("msg", cri.isMsg());
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:help_List";
	}

	@RequestMapping(value="updateHelp", method=RequestMethod.GET)
	public String updateHelpGET(@RequestParam ("bbsctt_code") int bbsctt_code, @ModelAttribute("cri") PageVO cri, RedirectAttributes rttr, Model model) throws Exception{
		String ContentPage = "help_Update";
		System.out.println("업데이트헬프 "+bbsctt_code);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		model.addAttribute("msg", cri.isMsg());
		model.addAttribute(helpService.readHelp(bbsctt_code));
		model.addAttribute("main_content", ContentPage);
		
		return "mainPage";
	}
	
	@RequestMapping(value="updateHelp", method=RequestMethod.POST)
	public String updateHelpPOST(HelpVO vo, PageVO cri, RedirectAttributes rttr) throws Exception{
		
		helpService.updateHelp(vo);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:help_List";
	}
	
	@RequestMapping(value="deleteHelp", method=RequestMethod.GET)
	public String deleteHelpGet(@RequestParam("bbsctt_code") int bbsctt_code,@ModelAttribute("cri") PageVO cri , RedirectAttributes rttr, Model model) throws Exception{
		model.addAttribute("msg", cri.isMsg());
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:searchHelp";
	}
	
	@RequestMapping(value="deleteHelp", method=RequestMethod.POST)
	public String deleteHelpPost(@RequestParam("bbsctt_code") int bbsctt_code, @ModelAttribute("cri") PageVO cri , RedirectAttributes rttr, Model model) throws Exception{
		
		System.out.println("del help다");
		helpService.deleteHelp(bbsctt_code);
		
		model.addAttribute("msg", cri.isMsg());
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:help_List";
	}
	
}
