package studentManagement.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import studentManagement.config.model.CourseBean;
import studentManagement.config.persistant.dao.CourseService;
import studentManagement.config.persistant.dto.CourseRequestDTO;
import studentManagement.config.persistant.dto.UserRequestDTO;

import javax.servlet.http.HttpSession;

@Controller
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	@RequestMapping(value = "/CourseRegistration",method = RequestMethod.GET)
	public ModelAndView addCourse(HttpSession session, ModelMap model) {
		
		UserRequestDTO res = null;
		res = (UserRequestDTO) session.getAttribute("res");
		
		if(res == null) {
			
			model.addAttribute("error","Please login first !");
			return new ModelAndView("UserLogin","bean",new UserRequestDTO());
			
		}else if(res.getUserRole().equals("User")) {
			
			model.addAttribute("msg","Can't registration course due to role is user !");
			
				return new ModelAndView("TopMenu");
			}else {
			
		return new ModelAndView("CourseRegistration","bean",new CourseBean());
			}
		}
	
	@RequestMapping(value = "/AddCourseServlet",method = RequestMethod.POST)
	public String addCourseServlet(@ModelAttribute("bean") @Validated CourseBean bean,BindingResult bs,ModelMap model,RedirectAttributes ra,HttpSession session) {
		
		UserRequestDTO res = null;
		res = (UserRequestDTO) session.getAttribute("res");
		if(res == null) {
			ra.addFlashAttribute("error","Please login first !");
			return "redirect:/";
		}
		
		if(bs.hasErrors()) {
			
		}else {
			
			List<CourseRequestDTO> list = courseService.selectAllCourse();
				
				CourseRequestDTO crd = list.stream().filter(c -> c.getCourseName().equals(bean.getCourseName()) ||
						c.getCourseId().equals(bean.getCourseId())).findFirst()
										.orElse(null);
				if(crd != null) {
					
					model.addAttribute("error","Course Id or Course name have been !");
					
					}else {
						
						CourseRequestDTO dto = new CourseRequestDTO();
						dto.setCourseId(bean.getCourseId());
						dto.setCourseName(bean.getCourseName());
						
							courseService.insertCourse(dto);
						
							ra.addFlashAttribute("msg","Course Registration Successfully !");
							return "redirect:/CourseRegistration";
					}
			}
		return "CourseRegistration";
	}
}
