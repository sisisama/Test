package com.Controller;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.DAO.UserDAO;
import com.DTO.UserRequestDTO;
import com.DTO.UserResponseDTO;
import com.Model.UserBean;



@Controller
public class UserController {
	@Autowired
	UserDAO udao = new UserDAO();
	@GetMapping(value="/")
	public ModelAndView showLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return new ModelAndView("LGN001","LGN",new UserBean());
}
	@GetMapping(value="/reg")
	public ModelAndView register() {
		return new ModelAndView("USR001-01","USR001",new UserBean());
	}
	@GetMapping(value="/reg1")
	public ModelAndView register1() {
		return new ModelAndView("USR001","USR001",new UserBean());
	}
	@PostMapping(value="/Ureg1")
	public String doregister1(@ModelAttribute("USR001") UserBean bean, Model m) {
		UserRequestDTO udto = new UserRequestDTO();
		udto.setId(bean.getId());
		udto.setName(bean.getName());
		udto.setEmail(bean.getEmail());
		udto.setPas(bean.getPas());
		udto.setRole(bean.getRole());
		int i = udao.insertUser(udto);
		if(i<=0) {
			m.addAttribute("error","Registration Failed");
			return "USR001";
		}else {
			m.addAttribute("error","Registration Successful");
			return "redirect:/stb";
		}
	}
	@PostMapping(value="/Ureg")
	public String doregister(@ModelAttribute("USR001") UserBean bean, Model m) {
		UserRequestDTO udto = new UserRequestDTO();
		udto.setId(bean.getId());
		udto.setName(bean.getName());
		udto.setEmail(bean.getEmail());
		udto.setPas(bean.getPas());
		udto.setRole(bean.getRole());
		int i = udao.insertUser(udto);
		if(i<=0) {
			m.addAttribute("error","Registration Failed");
			return "USR001-01";
		}else {
			m.addAttribute("error","Registration Successful");
			return "redirect:/";
		}
	}
	@PostMapping(value="/Ulog")
	public String login(@ModelAttribute() UserBean bean,Model m,HttpSession session) {
		UserRequestDTO rdto = new UserRequestDTO();
		rdto.setName(bean.getName());
		rdto.setPas(bean.getPas());
		LocalDate date = LocalDate.now();
		String today = date.toString();
		UserResponseDTO resdto = new UserResponseDTO();
		resdto = udao.CheckUser(rdto);
			if(resdto.getRole().equals("Admin")) {
				session.setAttribute("name", resdto.getName());
				session.setAttribute("date", today);
				m.addAttribute("","Welcome");
				return "MNU001";
			}else if(resdto.getRole().equals("User")) {
				session.setAttribute("name", resdto.getName());
				session.setAttribute("date", today);
				m.addAttribute("","Welcome");
				return "MNU001-01";
			}
			return "";
		
	}
	@GetMapping(value="/umenu")
	public String menu(Model m){
	return "MNU001";
	}
	@GetMapping(value="/umenu1")
	public String menu1(Model m){
	return "MNU001-01";
	}
	@GetMapping(value="/stb")
	public String showTable(Model m) {
		ArrayList<UserResponseDTO> list = udao.ShowAllResult();
		m.addAttribute("list",list);
		return "USR003";
	}
	@GetMapping(value="/form")
	public String userForm(@RequestParam("id")String id,Model m,UserResponseDTO res) {
		UserRequestDTO resdto = new UserRequestDTO();
		resdto.setId(id);
		res = udao.selectOne(resdto);
		System.out.println(res.getId());
		System.out.println(res.getName());
		m.addAttribute("res",res);
		return "USR002";
	}
	@GetMapping(value="/dtb")
	public String deletetable(@RequestParam("id")String id) {
		UserRequestDTO resdto = new UserRequestDTO();
		resdto.setId(id);
	ArrayList<UserResponseDTO>	res = udao.ShowAllResult();
		udao.CheckUser(resdto);
	if(res.size()>1) {
		for (UserResponseDTO user1 : res) {
			if(user1.getRole().equals("User")) {
				udao.deleteUser(resdto);
				return "redirect:/";
		}	
		}
	}
	return "redirect:/stb";
	}
	@PostMapping(value="/search")
	public String search(@ModelAttribute() UserBean bean,Model m) {
		UserRequestDTO rdto = new UserRequestDTO();
		rdto.setId(bean.getId());
		rdto.setName(bean.getName());
		ArrayList<UserResponseDTO> list = udao.searchData(rdto);
		m.addAttribute("list", list);
		return "USR003";
	}
	@PostMapping(value="/update")
	public String updateuser(@ModelAttribute("res") UserBean bean,Model m) {
		UserRequestDTO udto = new UserRequestDTO();
		udto.setId(bean.getId());
		udto.setName(bean.getName());
		udto.setEmail(bean.getEmail());
		udto.setPas(bean.getPas());
		udto.setRole(bean.getRole());
		int i = udao.updateUser(udto);
		if(i<=0) {
			m.addAttribute("error","Registration Failed");
			return "redirect:/form";
		}else {
			m.addAttribute("error","Registration Successful");
			return "redirect:/stb";
		}
	}
}
