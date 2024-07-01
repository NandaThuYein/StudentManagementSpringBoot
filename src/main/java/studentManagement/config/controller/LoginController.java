package studentManagement.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import studentManagement.config.model.UserBean;
import studentManagement.config.persistant.dao.UserService;
import studentManagement.config.persistant.dto.UserRequestDTO;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public ModelAndView login(ModelMap model) {
		return new ModelAndView("UserLogin","bean",new UserBean());
	}
	
	@PostMapping("/UserLoginServlet")
	public String userLogin(@ModelAttribute("bean") UserBean bean, ModelMap model, HttpSession session) {
		if(bean.getUserName().equals("") || bean.getUserPassword().equals("")) {
			model.addAttribute("error","Field cannot be blank !");
		}else {
			
				UserRequestDTO res = userService.userLoginDao(bean);
				
			if(res == null) {
				model.addAttribute("error","Please check your data again.");
			}else {
				session.setAttribute("res",res);
				return "redirect:/welcomePage";
			}
		}
		return "UserLogin";
	}
	
	@RequestMapping(value = "/welcomePage",method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		return new ModelAndView("TopMenu");
	}
	
	@RequestMapping(value = "/CreateUser",method = RequestMethod.GET)
	public ModelAndView createUser() {
		UserBean bean = new UserBean();
		bean.setUserRole("User");
		return new ModelAndView("CreateUser","bean",bean);
	}
	
	@RequestMapping(value = "/CreateUserServlet",method = RequestMethod.POST)
	public String createAccount(@ModelAttribute("bean") @Validated UserBean bean,BindingResult bs,ModelMap model,RedirectAttributes ra) {
		
		if(bs.hasErrors()) {
			
			model.addAttribute("error","UserBean must not be empty !");
			
		}else if(!bean.getUserPassword().equals(bean.getConfPassword())){
			
			model.addAttribute("error","Please check your confirm password again.");
			
		}else {
			
			UserRequestDTO dto = new UserRequestDTO();
			dto.setUserId(bean.getUserId());
			dto.setUserName(bean.getUserName());
			dto.setUserEmail(bean.getUserEmail());
			dto.setUserPassword(bean.getUserPassword());
			dto.setUserRole(bean.getUserRole());
			
			 userService.insertUser(dto);
			
				ra.addFlashAttribute("succ","Create Account Successfully !");
				return "redirect:/";
		}
		
		return "CreateUser";
	}
	
	@RequestMapping(value = "/UserLogoutServlet",method = RequestMethod.GET)
	public String userLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
