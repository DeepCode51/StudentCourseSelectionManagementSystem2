/**
 * 
 */
package org.lhp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lhp.bean.Notice;
import org.lhp.bean.RoleInfo;
import org.lhp.bean.Teacher;
import org.lhp.bean.TeacherUserRt;
import org.lhp.service.TeacherLoginSerice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

/**
 * @author rcx
 * @date   2020年4月9日  下午8:47:50
 * @class  org.lhp.controller.TeacherLoginController
 * 教师登录
 * 
 */
@Controller
public class TeacherLoginController {
	
	@Resource(name="teacherLoginSerice")
	TeacherLoginSerice service;
	
	@RequestMapping("tealogin.do")
	public void lpgin(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println(password);
		
		String flag = service.login(username, password);
		
		if(flag.equals("true")){
			HttpSession session = request.getSession();
			session.setAttribute("TeaUserId", service.queryId(username));
		}
		
		Gson gson=new Gson();
		String json = gson.toJson(flag);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	
	@RequestMapping("teaUserCheck.do")
	public void tuc(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		Integer i = (Integer) session.getAttribute("TeaUserId");
		Map<String,Object> map=new HashMap<>();
		
		String flag=new String();
		if(i==null){
			flag="false";
		}else{
			flag="true";
			String name = service.queryName(i);
			RoleInfo role = service.queryRole(i);
			map.put("role", role);
			map.put("name", name);
		}
		
		map.put("flag", flag);
		Gson gson=new Gson();
		String json = gson.toJson(map);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	@RequestMapping("teaUserExit.do")
	public void tue(HttpServletResponse response,HttpServletRequest request) throws IOException{
		HttpSession session = request.getSession();
		session.removeAttribute("TeaUserId");
		System.out.println("sadsa");
		response.sendRedirect("pages/index.html");
	}
	@RequestMapping("queryTeachInfo.do")
	public void qti(HttpServletResponse response,HttpServletRequest request) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		Integer Teaid=(Integer) session.getAttribute("TeaUserId");
		
		TeacherUserRt queryTeachInfo = service.queryTeachInfo(Teaid);
		
		Gson gson=new Gson();
		String json = gson.toJson(queryTeachInfo);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	@RequestMapping("TeachInfoModify.do")
	public void tim(HttpServletResponse response,HttpServletRequest request) throws IOException{
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
		 Integer Teaid=(Integer) session.getAttribute("TeaUserId");
		 
		 TeacherUserRt tu=new TeacherUserRt();
		 tu.setId(Teaid);
		 tu.setTeachinfo(teacher);
		 
		 service.TeachInfoModify(tu);
		 
		 Gson gson=new Gson();
		 	String json = gson.toJson("true");
			
			PrintWriter out = response.getWriter();
			out.write(json);
			
		 response.sendRedirect("pages/tea_TeacherInfo.html");
	}
	@RequestMapping("passwordModify.do")
	public void pm(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String passwordOld = request.getParameter("passwordOld");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		Integer Teaid=(Integer) session.getAttribute("TeaUserId");
		
		String flag = service.passwordModify(Teaid,passwordOld,password);
		
		Gson gson=new Gson();
		String json = gson.toJson(flag);
			
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	@RequestMapping("queryTeaNotice.do")
	public void ptn(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		Integer Teaid=(Integer) session.getAttribute("TeaUserId");
		
		List<Notice> list = service.queryTeaNotice(Teaid);
		
		Gson gson=new Gson();
		String json = gson.toJson(list);
			
		PrintWriter out = response.getWriter();
		out.write(json);
	}
}
