/**
 * 
 */
package org.lhp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lhp.bean.Notice;
import org.lhp.bean.RoleInfo;
import org.lhp.service.RootLoginSerice;
import org.lhp.service.StudentLoginSerice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

/**
 * @author rcx
 * @date   2020年4月9日  下午7:12:43
 * @class  org.lhp.controller.RootLoginController
 * 
 * 
 */
@Controller
public class RootLoginController {
	@Resource(name="rootLoginSerice")
	RootLoginSerice service;
	
	@RequestMapping("rootlogin.do")
	public void lpgin(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String flag = service.login(username, password);
		System.out.println(flag);
		if(flag.equals("true")){
			RoleInfo role = service.queryTeachIsRoot(username);
			if(role.getFn4().equals("true")||role.getFn5().equals("true")||role.getFn6().equals("true")){
				flag="true";
				HttpSession session = request.getSession();
				
			}else{
				flag="false";
			}
			System.out.println(role);
		}
		
		Gson gson=new Gson();
		String json = gson.toJson(flag);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	@RequestMapping("queryRootNotice.do")
	public void ptn(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		Integer stuid = (Integer) session.getAttribute("stuUserId");
		
		List<Notice> list = service.queryRootNotice();
		
		Gson gson=new Gson();
		String json = gson.toJson(list);
			
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	
}
