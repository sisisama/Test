package com.Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.DAO.CourseDAO;
import com.DAO.StudentDAO;
import com.DTO.CourseRequestDTO;
import com.DTO.CourseResponseDTO;
import com.DTO.StudentRequestDTO;
import com.DTO.StudentResponseDTO;
import com.DTO.UserRequestDTO;
import com.DTO.UserResponseDTO;
import com.Model.StudentBean;
import com.Model.StudentBean1;
import com.Model.UserBean;
@Controller
public class StudentController {
	 @Autowired
	 StudentDAO sdao = new StudentDAO();
	 CourseDAO cdao = new CourseDAO();
	 @GetMapping(value="/stu")
		public ModelAndView student_register() {
			return new ModelAndView("STU001","STU001",new StudentBean());
		}
	 @GetMapping(value="/stu1")
		public ModelAndView student_register1() {
			return new ModelAndView("STU001-01","STU002",new StudentBean());
		}
		@PostMapping(value="/stureg1")
		public String studentregistration(@ModelAttribute("STU001")StudentBean bean, Model m,MultipartFile file)throws IOException  {
			file = bean.getPhoto();
			System.out.println(bean.getName());
			String imageFileName = file.getOriginalFilename();
			String uploadpath ="/Users/sithuramyo/Desktop/J2EEWorkSpace/Test/src/main/webapp/resources/image/"+imageFileName;
			StudentRequestDTO sdto = new StudentRequestDTO();
			sdto.setId(bean.getId());
			sdto.setName(bean.getName());
			sdto.setDob(bean.getDob());
			sdto.setGender(bean.getGender());
			sdto.setPhone(bean.getPhone());
			sdto.setEducation(bean.getEducation());
			sdto.setAttend(bean.getAttend());
			System.out.println(sdto.getAttend());
			sdto.setPhoto(imageFileName);
			int i = sdao.insertStU(sdto);
			try {
				FileOutputStream fos = new FileOutputStream(uploadpath);
				InputStream is = file.getInputStream();
				byte [] data = new byte[is.available()];
				is.read(data);
				fos.write(data);
				fos.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			if(i<=0) {
				m.addAttribute("error","Registration Failed");
				return "redirect:/stu";
			}else {
				m.addAttribute("error","Registration Successful");
				return "redirect:/stu";
			}
		}
		@PostMapping(value="/stureg2")
		public String studentregistration1(@ModelAttribute("STU002")StudentBean bean, Model m,MultipartFile file)throws IOException  {
			file = bean.getPhoto();
			System.out.println(bean.getName());
			String imageFileName = file.getOriginalFilename();
			String uploadpath ="/Users/sithuramyo/Desktop/J2EEWorkSpace/Test/src/main/webapp/resources/image/"+imageFileName;
			StudentRequestDTO sdto = new StudentRequestDTO();
			sdto.setId(bean.getId());
			sdto.setName(bean.getName());
			sdto.setDob(bean.getDob());
			sdto.setGender(bean.getGender());
			sdto.setPhone(bean.getPhone());
			sdto.setEducation(bean.getEducation());
			sdto.setAttend(bean.getAttend());
			sdto.setPhoto(imageFileName);
			int i = sdao.insertStU(sdto);
			try {
				FileOutputStream fos = new FileOutputStream(uploadpath);
				InputStream is = file.getInputStream();
				byte [] data = new byte[is.available()];
				is.read(data);
				fos.write(data);
				fos.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			if(i<=0) {
				m.addAttribute("error","Registration Failed");
				return "redirect:/stu1";
			}else {
				m.addAttribute("error","Registration Successful");
				return "redirect:/stu1";
			}
		}
		@GetMapping(value="/sstb")
		public String showTable(Model m) {
			ArrayList<StudentResponseDTO> list = sdao.ShowStu();
			for (StudentResponseDTO sdto : list) {
				String st = sdto.getAttend();
				System.out.println(st);
			}
			m.addAttribute("list",list);
			return "STU003";
		}
		@GetMapping(value="/sform")
		public String stuForm(@RequestParam("id")String id,Model m,StudentResponseDTO res) {
			StudentRequestDTO resdto = new StudentRequestDTO();
			ArrayList<CourseResponseDTO> cres = cdao.ShowCourse();
			resdto.setId(id);
			res = sdao.SelectOne(resdto);
			System.out.println(res.getDob());
			if(res.getGender().equals("Male")){
				int check = 1;
			m.addAttribute("check", check);
			}else {
				int check1 = 0;
				m.addAttribute("check1",check1);
			}
			String []st1 = res.getAttend().split(",");
			List<String> sl = new ArrayList<String>();
        	sl = Arrays.asList(st1);
        	for (CourseResponseDTO course: cres) {
        		if(sl.contains(course.getName())) {
        			boolean courses = true;
        			res.setAttend(course.getName());
        			m.addAttribute("courses", courses);
        		}
			}
			System.out.println(sl);
			
			m.addAttribute("st1",sl);
			
			//bean.setAttend(st1);		
			m.addAttribute("res",res);
			return "STU002";
		}
		@GetMapping(value="/sform2")
		public String stuform(@RequestParam("id")String id,Model m,StudentResponseDTO res) {
			StudentRequestDTO resdto = new StudentRequestDTO();
			ArrayList<CourseResponseDTO> cres = cdao.ShowCourse();
			resdto.setId(id);
			res = sdao.SelectOne(resdto);
			m.addAttribute("res",res);
			return "STU002-01";
		}
		@PostMapping(value="/ssearch")
		public String ssearch(@ModelAttribute()StudentBean bean,Model m) {
			StudentRequestDTO rdto = new StudentRequestDTO();
			rdto.setId(bean.getId());
			rdto.setName(bean.getName());
			rdto.setAttend(bean.getAttend());
			System.out.println(rdto.getAttend());
			ArrayList<StudentResponseDTO> list = sdao.searchData(rdto);
			list = sdao.SearchwithCourse(rdto.getAttend());
			m.addAttribute("list", list);
			return "STU003";
		}
		@PostMapping(value="/supdate")
		public String updatestudent(@ModelAttribute("res")StudentBean bean,Model m,MultipartFile file) throws IOException{
			file = bean.getPhoto();
			System.out.println(bean.getName());
			String imageFileName = file.getOriginalFilename();
			String uploadpath ="/Users/sithuramyo/Desktop/J2EEWorkSpace/Test/src/main/webapp/resources/image/"+imageFileName;
			StudentRequestDTO sdto = new StudentRequestDTO();
			sdto.setId(bean.getId());
			sdto.setName(bean.getName());
			sdto.setDob(bean.getDob());
			sdto.setGender(bean.getGender());
			sdto.setPhone(bean.getPhone());
			sdto.setEducation(bean.getEducation());
			sdto.setAttend(bean.getAttend());
			sdto.setPhoto(imageFileName);
			sdao.UpdateStu(sdto);
			try {
				FileOutputStream fos = new FileOutputStream(uploadpath);
				InputStream is = file.getInputStream();
				byte [] data = new byte[is.available()];
				is.read(data);
				fos.write(data);
				fos.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			return "redirect:/sstb";
		}
		@GetMapping(value="/dstutb")
		public String deletetable1(@RequestParam("id")String id) {
			StudentRequestDTO resdto = new StudentRequestDTO();
			resdto.setId(id);
			sdao.DeleteStu(resdto);
			return "redirect:/sstb";
			
		}
	
}
