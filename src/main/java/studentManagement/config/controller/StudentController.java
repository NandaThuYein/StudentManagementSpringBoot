package studentManagement.config.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import studentManagement.config.model.StudentBean;
import studentManagement.config.persistant.dao.CourseService;
import studentManagement.config.persistant.dao.StudentService;
import studentManagement.config.persistant.dto.CourseRequestDTO;
import studentManagement.config.persistant.dto.StudentRequestDTO;
import studentManagement.config.persistant.dto.UserRequestDTO;

@Controller
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	CourseService courseService;
	
	@ModelAttribute("AllCourses")
	public List<CourseRequestDTO> getAllCourses() {
		List<CourseRequestDTO> courses = courseService.selectAllCourse();
		return courses;
	}
	
	@RequestMapping(value = "/AddStudent",method = RequestMethod.GET)
	public ModelAndView addStudent(ModelMap model,HttpSession session) {
		UserRequestDTO res = null;
		res = (UserRequestDTO) session.getAttribute("res");
		if(res == null) {
			model.addAttribute("error","Please login first !");
			return new ModelAndView("UserLogin","bean",new UserRequestDTO());
		}else if(res.getUserRole().equals("User")) {
			model.addAttribute("msg","Can't register student due to role is user !");
			return new ModelAndView("TopMenu");
		}else {
				StudentBean bean = new StudentBean();
				bean.setStudentGender("Male");
				bean.setStudentPhone("+95");
				return new ModelAndView("StudentRegistration","bean",bean);
				}
	}
	
	@RequestMapping(value = "/AddStudentServlet",method = RequestMethod.POST)
	public String addStudentServlet(@ModelAttribute("bean") @Validated StudentBean bean,BindingResult bs,ModelMap model,RedirectAttributes ra,HttpSession session) {
		
		UserRequestDTO res = null;
		res = (UserRequestDTO) session.getAttribute("res");
		if(res == null) {
			ra.addFlashAttribute("error","Please login first !");
			return "redirect:/";
		}
		
		if(bs.hasErrors()) {

			model.addAttribute("error","Field cannot be blank !");
			
		}else {
			
			Optional<StudentRequestDTO> checkStud = studentService.checkOneStudent(bean.getStudentId());
			
			if(checkStud.isPresent()) {
				
				model.addAttribute("error","Insert Student Failed !!! This ID has been.");
				
			}else {
			StudentRequestDTO dto = new StudentRequestDTO();
			
			dto.setStudentId(bean.getStudentId());
			dto.setStudentName(bean.getStudentName());
			dto.setStudentDob(bean.getStudentDob());
			dto.setStudentPhone(bean.getStudentPhone());
			dto.setStudentGender(bean.getStudentGender());
			dto.setStudentEducation(bean.getStudentEducation());
			dto.setCourses(bean.getCourses());
			
			studentService.insertStudent(dto);
						
			ra.addFlashAttribute("succ","Student Registration Successfully !");	
			
			return "redirect:/AddStudent";
			}
		}
		
		return "StudentRegistration";
	}
	
	@RequestMapping(value = "/SearchStudentServlet",method = RequestMethod.GET)
	public String searchStudent(ModelMap model,HttpSession session,RedirectAttributes ra) {
		
		UserRequestDTO res = null;
		res = (UserRequestDTO) session.getAttribute("res");
		if(res == null) {
			ra.addFlashAttribute("error","Please login first !");
			return "redirect:/";
		}
		
		List<StudentRequestDTO> studentList = studentService.selectAllStudent();
		
		if(studentList.size() == 0) {
			model.addAttribute("noStudent","No Student");
		}else {
				model.addAttribute("studentList",studentList);
		}
		
		return "SearchStudent";
	}
	
	@RequestMapping(value = "/updateStudent",method = RequestMethod.GET)
	public ModelAndView updateStudent(@RequestParam("studentId") String studentId,ModelMap model,HttpSession session,RedirectAttributes ra) {
		
		UserRequestDTO res = null;
		res = (UserRequestDTO) session.getAttribute("res");
		
		if(res == null) {
			
			model.addAttribute("error","Please login first !");
			return new ModelAndView("UserLogin","bean",new UserRequestDTO());
			
		}else if(res.getUserRole().equals("User")) {
			
			ra.addFlashAttribute("msg","Can't update and delete Student due to role is user !");
			return new ModelAndView("redirect:/SearchStudentServlet");
			
		}else {
			
			StudentBean bean = new StudentBean();
			
			StudentRequestDTO dto = studentService.selectOneStudent(studentId);
			
			bean.setStudentId(dto.getStudentId());
			bean.setStudentName(dto.getStudentName());
			bean.setStudentGender(dto.getStudentGender());
			bean.setStudentDob(dto.getStudentDob());
			bean.setStudentPhone(dto.getStudentPhone());
			bean.setStudentEducation(dto.getStudentEducation());
			bean.setCourses(dto.getCourses());
			
			return new ModelAndView("StudentUpdate","bean",bean);
			}
	}
	
	@RequestMapping(value = "/UpdateStudentServlet",method = RequestMethod.POST)
	public String updateStudentServlet(@ModelAttribute("bean") @Validated StudentBean bean,BindingResult bs,ModelMap model,HttpSession session,RedirectAttributes ra) {
		
		UserRequestDTO res = null;
		res = (UserRequestDTO) session.getAttribute("res");
		if(res == null) {
			ra.addFlashAttribute("error","Please login first !");
			return "redirect:/";
		}
		
		if(bs.hasErrors()) {
			model.addAttribute("error","Field cannot be blank !");
		}else {
			
			StudentRequestDTO dto = new StudentRequestDTO();
			
			dto.setStudentId(bean.getStudentId());
			dto.setStudentName(bean.getStudentName());
			dto.setStudentDob(bean.getStudentDob());
			dto.setStudentPhone(bean.getStudentPhone());
			dto.setStudentGender(bean.getStudentGender());
			dto.setStudentEducation(bean.getStudentEducation());
			dto.setCourses(bean.getCourses());
			
			studentService.updateStudent(dto);
			
			model.addAttribute("msg","Student Update Successfully !");
			
		}
		return "StudentUpdate";
	}
	
	@RequestMapping(value = "/DeleteStudentServlet",method = RequestMethod.GET)
	public String deleteStudent(@RequestParam("studentId") String studentId,RedirectAttributes ra) {
		
			studentService.deleteStudent(studentId);
	
			ra.addFlashAttribute("msg","Student Delete Successfully !");
		return "redirect:/SearchStudentServlet";
	}
}
