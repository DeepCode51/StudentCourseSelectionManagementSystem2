package org.lhp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lhp.bean.Notice;
import org.lhp.bean.Teacher;
import org.lhp.bean.TeacherUser;
import org.lhp.service.TeacherRegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
/**
 * 
 * @author rcx
 * @date   2020年4月9日  下午8:48:05
 * @class  org.lhp.controller.TeacherRegisterController
 * 教师注册控制器
 *
 */
@Controller
public class TeacherRegisterController {
	
	@Resource(name="teacherRegisterService")
	TeacherRegisterService service;
	
	@RequestMapping("teausernameCheck.do")
	public void usernamecheck(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		String username = request.getParameter("username");
		
		String usernameCheck = service.usernameCheck(username);
		
		Gson gson=new Gson();
		String json = gson.toJson(usernameCheck);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	@RequestMapping("teacherRegisterUser.do")
	public void teacherRegisteUser(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	
		TeacherUser tUser=new TeacherUser();
		tUser.setUsername(username);
		tUser.setPassword(password);
		tUser.setRole(1);
		tUser.setInfoid(1);
		System.out.println(username+password);
		HttpSession session = request.getSession();
		session.setAttribute("tUser", tUser);
		
		Gson gson=new Gson();
		String json = gson.toJson("true");
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	
	
	@RequestMapping("teacherRegisterInfo.do")
	public void teacherRegisteInfo(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		 String name = request.getParameter("fullName");
		 String sex = request.getParameter("sex");
		 String IdNo = request.getParameter("IDNo");
		 String phoneNo = request.getParameter("PhoneNo");
		 String province = request.getParameter("province");
		 String city = request.getParameter("city");
		 String area = request.getParameter("area");
		 String eduction = request.getParameter("eduction");
		 String school_name = request.getParameter("schoolname");
		 String major = request.getParameter("major");
		 
		 Teacher teacher=new Teacher();
		 teacher.setEduction(eduction);
		 teacher.setIdcard(IdNo);
		 teacher.setMajor(major);
		 teacher.setName(name);
		 teacher.setPhonenumber(phoneNo);
		 teacher.setProvinces(province+city+area);
		 teacher.setSchool(school_name);
		 teacher.setSex(sex);
		 
		 HttpSession session = request.getSession();
		 TeacherUser tUser = (TeacherUser) session.getAttribute("tUser");
		 
		 service.usernameRegister(teacher, tUser);
		 
			Notice notice=new Notice();
			notice.setCheck(1);
			notice.setTid(1);
			notice.setNoticeinfo("用户"+tUser.getUsername()+"已经注册，请审核！！");
			notice.setNoticetype("账号申请");
			service.AddRootNotice(notice);
		 
		 
		 
		 response.sendRedirect("pages/RegisterSuccess.html");
	}
}
