package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.DAO.CourseDAO;
import com.DTO.CourseRequestDTO;
import com.DTO.UserRequestDTO;
import com.Model.CourseBean;
import com.Model.UserBean;

@Controller
public class CourseController {
	@Autowired
	CourseDAO cdao = new CourseDAO();
	@GetMapping(value="/cour")
	public ModelAndView course_register() {
		return new ModelAndView("COR001","COR001",new CourseBean());
	}
	@PostMapping(value="/coureg")
	public String courregister(@ModelAttribute("COR001")CourseBean bean, Model m) {
		CourseRequestDTO cdto = new CourseRequestDTO();
		cdto.setId(bean.getId());
		cdto.setName(bean.getName());
			int i = cdao.insertCourse(cdto);
		if(i<=0) {
			m.addAttribute("error","Registration Failed");
			return "COR001";
		}else {
			m.addAttribute("error","Registration Successful");
			return "redirect:/cour";
		}
	}
}
