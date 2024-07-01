package studentManagement.config.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import studentManagement.config.model.UserBean;
import studentManagement.config.persistant.dao.UserService;
import studentManagement.config.persistant.dto.UserRequestDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private ResourceLoader resourceLoader;
	
	@RequestMapping(value = "/SearchUser",method = RequestMethod.GET)
	public ModelAndView searchUser(ModelMap model, HttpSession session) {
		
		UserRequestDTO res = null;
		res = (UserRequestDTO) session.getAttribute("res");
		
		if(res == null) {
			
			model.addAttribute("error","Please login first !");
			return new ModelAndView("UserLogin","bean",new UserRequestDTO());
			
		}else {
			
			List<UserRequestDTO> list = userService.selectAllUser();
			model.addAttribute("excel","excel");
			model.addAttribute("pdf","pdf");
			model.addAttribute("list",list);

			return new ModelAndView("SearchUser","bean",new UserBean());
		}
	}
	
	@RequestMapping(value = "/SearchUserServlet",method = RequestMethod.POST)
	public String searchUserServlet(@ModelAttribute("bean") UserBean bean,ModelMap model,HttpSession session,RedirectAttributes ra) {
		
		UserRequestDTO res = null;
		res = (UserRequestDTO) session.getAttribute("res");
		if(res == null) {
			ra.addFlashAttribute("error","Please login first !");
			return "redirect:/";
		}else {
		
		if(bean.getUserId().equals("") && bean.getUserName().equals("")) {
			model.addAttribute("error","Please you put id or name !");
		}else {
			List<UserRequestDTO> list = userService.searchUser(bean);
			if(list.size() == 0) {
				model.addAttribute("error","No Using this id or name");
			}else {
				session.setAttribute("user",bean);
				model.addAttribute("excel","excel");
				model.addAttribute("pdf","pdf");
				model.addAttribute("list",list);
			}
		}
		}
		return "SearchUser";
	}
	
	@RequestMapping(value = "/UserRegistration",method = RequestMethod.GET)
	public ModelAndView userRegistration(ModelMap model,HttpSession session,RedirectAttributes ra) {
		UserRequestDTO res = null;
		res = (UserRequestDTO) session.getAttribute("res");
		if(res == null) {
			model.addAttribute("error","Please login first !");
			return new ModelAndView("UserLogin","bean",new UserRequestDTO());
		}else if(res.getUserRole().equals("User")) {
			ra.addFlashAttribute("msg","Can't registration user due to role is user !");
			return new ModelAndView("redirect:/SearchUser");
		}else {
			return new ModelAndView("UserRegistration","bean",new UserBean());
		}
	}
	
	@RequestMapping(value = "/AddUserServlet",method = RequestMethod.POST)
	public String addUser(@ModelAttribute("bean") @Validated UserBean bean,BindingResult bs,ModelMap model,RedirectAttributes ra,HttpSession session) {
		
		UserRequestDTO res = null;
		res = (UserRequestDTO) session.getAttribute("res");
		
		if(res == null) {
			ra.addFlashAttribute("error","Please login first !");
			return "redirect:/";
		}
		
		if(bs.hasErrors()) {
			
		}else if(!bean.getUserPassword().equals(bean.getConfPassword())){
			model.addAttribute("error","Please check your confirm password again.");
		}else {
			UserRequestDTO dto = new UserRequestDTO();
			dto.setUserId(bean.getUserId());
			dto.setUserName(bean.getUserName());
			dto.setUserEmail(bean.getUserEmail());
			dto.setUserPassword(bean.getUserPassword());
			dto.setUserRole(bean.getUserRole());
			
			var checkUser = userService.checkUserOne(bean.getUserId());
			
			if(checkUser.isPresent()) {
				model.addAttribute("error","Insert Failed !!! This ID has been.");
			}else {
				userService.insertUser(dto);
				ra.addFlashAttribute("succ","Registered Successfully !");
				return "redirect:/SearchUser";
			}
		}
		return "UserRegistration";
	}
	
	@RequestMapping(value = "/setupUpdateUser",method = RequestMethod.GET)
	public ModelAndView setupUpdateUser(@RequestParam("id") String id,ModelMap model,HttpSession session,RedirectAttributes ra) {
		UserRequestDTO res = null;
		res = (UserRequestDTO) session.getAttribute("res");
		if(res == null) {
			model.addAttribute("error","Please login first !");
			return new ModelAndView("UserLogin","bean",new UserRequestDTO());
		}else if(res.getUserRole().equals("User")) {
			ra.addFlashAttribute("msg","Can't update user due to role is user !");
			return new ModelAndView("redirect:/SearchUser");
		}else {
			
			UserBean bean = new UserBean();
		
		UserRequestDTO user = userService.searchUserOne(id);
		bean.setUserId(user.getUserId());
		bean.setUserName(user.getUserName());
		bean.setUserEmail(user.getUserEmail());
		bean.setUserPassword(user.getUserPassword());
		bean.setConfPassword(user.getUserPassword());
		bean.setUserRole(user.getUserRole());

		return new ModelAndView("UpdateUser","bean",bean);
		}
	}
	
	@RequestMapping(value = "/UpdateUserServlet",method = RequestMethod.POST)
	public String updatUser(@ModelAttribute("bean") @Validated UserBean bean,BindingResult bs,ModelMap model,HttpSession session,RedirectAttributes ra) {
		
		UserRequestDTO res = null;
		res = (UserRequestDTO) session.getAttribute("res");
		if(res == null) {
			ra.addFlashAttribute("error","Please login first !");
			return "redirect:/";
		}
		
		if(bs.hasErrors()) {
			
		}else if(!bean.getUserPassword().equals(bean.getConfPassword())){
			model.addAttribute("error","Please check your confirm password again.");
		}else {
			
			UserRequestDTO dto = new UserRequestDTO();
			
			dto.setUserId(bean.getUserId());
			dto.setUserName(bean.getUserName());
			dto.setUserEmail(bean.getUserEmail());
			dto.setUserPassword(bean.getUserPassword());
			dto.setUserRole(bean.getUserRole());
			
			 userService.updateUser(dto);
			 
			 UserRequestDTO usr = (UserRequestDTO) session.getAttribute("res");
				if(usr != null && usr.getUserId().equals(bean.getUserId())) {
					session.removeAttribute("res");
					UserRequestDTO usr1 = new UserRequestDTO();
					usr1.setUserId(bean.getUserId());
					usr1.setUserName(bean.getUserName());
					session.setAttribute("res",usr1);
				}
				model.addAttribute("msg","User Update Successfully !");
		}
		return "UpdateUser";
	}
	
	@RequestMapping(value = "/DeleteUserServlet",method = RequestMethod.GET)
	public String deleteUser(@RequestParam("id") String id,RedirectAttributes ra,HttpSession session) {
		
		UserRequestDTO res = null;
		res = (UserRequestDTO) session.getAttribute("res");
		if(res == null) {
			
			ra.addFlashAttribute("error","Please login first !");
			return "redirect:/";
			
		}else if(res.getUserRole().equals("User")) {
			
			ra.addFlashAttribute("msg","Can't update user due to role is user !");
		
		}
		
		if(res != null && res.getUserId().equals(id)) {
			
			ra.addFlashAttribute("error","Don't allow to delete user who login !");
			
		}else {
			
			 	userService.deleteUser(id);
			 
				ra.addFlashAttribute("msg","User Delete Successfully !");
	
		}
		return "redirect:/SearchUser";
	}
	
	
	@GetMapping("/ExportServlet/{export}")
	public void exportServlet(@PathVariable String export, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		JRBeanCollectionDataSource source;
		JasperReport jasperReport;
		JasperPrint print;

		List<UserRequestDTO> list;

		UserBean dto = (UserBean) session.getAttribute("user");

		if (dto != null) {
			list = userService.searchUser(dto);
		} else {
			list = userService.selectAllUser();
		}

		session.removeAttribute("user");

		// Parameters for report
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("ReportTitle", "User List");

		try {
			source = new JRBeanCollectionDataSource(list);
			jasperReport = JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:UserReport.jrxml").getAbsolutePath());
			print = JasperFillManager.fillReport(jasperReport, parameters, source);

			if ("excel".equals(export)) {
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=User.xlsx");

				JRXlsExporter exporterXLS = new JRXlsExporter();
				exporterXLS.setExporterInput(new SimpleExporterInput(print));
				exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

				SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
				configuration.setOnePagePerSheet(true);
				exporterXLS.setConfiguration(configuration);

				exporterXLS.exportReport();
			} else if ("pdf".equals(export)) {
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=User.pdf");

				JRPdfExporter exporterPdf = new JRPdfExporter();
				exporterPdf.setExporterInput(new SimpleExporterInput(print));
				exporterPdf.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

				SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
				exporterPdf.setConfiguration(configuration);

				exporterPdf.exportReport();
			}
		} catch (JRException e) {
			e.printStackTrace();
			// Handle exception
		}
	}
}
