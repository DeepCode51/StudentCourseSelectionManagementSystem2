/**
 * 
 */
package org.lhp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.lhp.bean.Student;
import org.lhp.bean.StudentUser;
import org.lhp.service.StudentRegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

/**
 * @author rcx
 * @date   2020年4月9日  下午10:01:32
 * @class  org.lhp.controller.StudentRegisterColler
 * 
 * 
 */
@Controller
public class StudentRegisterController {
	
	@Resource(name="studentRegisterService")
	StudentRegisterService service;
	
	@RequestMapping("stuusernameCheck.do")
	public void stuUsernamecheck(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		String username = request.getParameter("username");
		String check = service.stuUsernameCheck(username);
		
		Gson gson=new Gson();
		String json = gson.toJson(check);
		
		PrintWriter out = response.getWriter();
		out.write(json);
		
	}
	
	@RequestMapping("stuRegisterUser.do")
	public void stuRegisterUser(HttpServletResponse response,HttpServletRequest request) throws IOException{
		StudentUser studentUser=new StudentUser();
		studentUser.setUsername(request.getParameter("username"));
		studentUser.setPassword(request.getParameter("password"));
		studentUser.setCheck(0);
		HttpSession session = request.getSession();
		session.setAttribute("studentUser", studentUser);
		
		Gson gson=new Gson();
		String json = gson.toJson("true");
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}	
	@RequestMapping("stuRegisterInfo.do")
	public void stuRegisterInfo(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		Student stu=new Student();
		
		stu.setIdCard(request.getParameter("IDNo"));
		stu.setName(request.getParameter("fullName"));
		stu.setPhoneNum(request.getParameter("phonenum"));
		stu.setProvinces(request.getParameter("province")+request.getParameter("city"));
		stu.setSex(request.getParameter("sex"));
		stu.setStuclass(request.getParameter("stuclass1")+request.getParameter("stuclass2"));
		
		HttpSession session = request.getSession();
		StudentUser studentUser = (StudentUser) session.getAttribute("studentUser");
		
		service.stuRegister(stu, studentUser);
		

		
		response.sendRedirect("pages/RegisterSuccess.html");
	}
	
}
