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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lhp.bean.Notice;
import org.lhp.bean.Student;
import org.lhp.bean.StudentUser;
import org.lhp.bean.StudentUserRt;
import org.lhp.service.StudentLoginSerice;
import org.lhp.service.StudentRegisterService;
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
public class StudentLoginController {
	
	@Resource(name="studentLoginSerice")
	StudentLoginSerice service;
	
	@RequestMapping("stulogin.do")
	public void lpgin(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println(password);
		
		String flag = service.login(username, password);
		
		if(flag.equals("true")){
			HttpSession session = request.getSession();
			session.setAttribute("stuUserId", service.queryId(username));
		}
		Gson gson=new Gson();
		String json = gson.toJson(flag);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	@RequestMapping("stuUserCheck.do")
	public void lpgin2(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		Integer i = (Integer) session.getAttribute("stuUserId");
		Map map=new HashMap<>();
		
		String flag=new String();
		if(i==null){
			flag="false";
		}else{
			flag="true";
			String name = service.queryName(i);
			map.put("name", name);
		}
		map.put("flag", flag);
		Gson gson=new Gson();
		String json = gson.toJson(map);
		
		PrintWriter out = response.getWriter();
		out.write(json);
		
	}
	@RequestMapping("queryStudentInfo.do")
	public void lpgin3(HttpServletResponse response,HttpServletRequest request) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		Integer sid = (Integer) session.getAttribute("stuUserId");
		
		StudentUserRt queryStudentInfo = service.queryStudentInfo(sid);
		
		Gson gson=new Gson();
		String json = gson.toJson(queryStudentInfo);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	@RequestMapping("stuModifyInfo.do")
	public void lpgin4(HttpServletResponse response,HttpServletRequest request) throws IOException{
		Student stu=new Student();
		
		stu.setIdCard(request.getParameter("IDNo"));
		stu.setName(request.getParameter("fullName"));
		stu.setPhoneNum(request.getParameter("phonenum"));
		stu.setProvinces(request.getParameter("province")+request.getParameter("city"));
		stu.setSex(request.getParameter("sex"));
		stu.setStuclass(request.getParameter("stuclass1")+request.getParameter("stuclass2"));
		
		HttpSession session = request.getSession();
		Integer sid = (Integer) session.getAttribute("stuUserId");
		
		StudentUserRt sur=new StudentUserRt();
		sur.setId(sid);
		sur.setStudent(stu);
		service.stuModifyInfo(sur);
		
		response.sendRedirect("pages/stu_StudentInfo.html");
	}
	
	@RequestMapping("StuPasswordModify.do")
	public void lpgin5(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String passwordOld = request.getParameter("passwordOld");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		Integer Stuid=(Integer) session.getAttribute("stuUserId");
		
		String flag = service.passwordModify(Stuid,passwordOld,password);
		
		Gson gson=new Gson();
		String json = gson.toJson(flag);
			
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	@RequestMapping("ByIdQueryStudentPassword.do")
	public void lpgin6(HttpServletResponse response,HttpServletRequest request) throws IOException{
		HttpSession session = request.getSession();
		Integer stuid = (Integer) session.getAttribute("stuUserId");
		
		String password=request.getParameter("password");
		
		String flag = service.ByIdQueryStudentPassword(stuid,password);
		
		Gson gson=new Gson();
		String json = gson.toJson(flag);
			
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	@RequestMapping("queryStuNotice.do")
	public void ptn(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		Integer stuid = (Integer) session.getAttribute("stuUserId");
		
		List<Notice> list = service.queryStuNotice(stuid);
		
		Gson gson=new Gson();
		String json = gson.toJson(list);
			
		PrintWriter out = response.getWriter();
		out.write(json);
	}
}
