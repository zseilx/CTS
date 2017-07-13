package yjc.wdb.scts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HeadOfficeController {
//	private static final Logger logger = LoggerFactory.getLogger(HeadOfficeController.class);
	
	@RequestMapping(value="headOfficeMain", method=RequestMethod.GET)
	public String tile_RegisterForm(HttpServletRequest request, HttpSession session, Model model) {
		
		String ContentPage = "headOffice";
		model.addAttribute("main_content", ContentPage);

		return "mainPage";
	}
}
