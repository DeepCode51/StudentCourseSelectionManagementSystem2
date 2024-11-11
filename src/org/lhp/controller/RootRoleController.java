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

import org.lhp.bean.Notice;
import org.lhp.bean.RoleInfo;
import org.lhp.bean.TeacherUserRt;
import org.lhp.service.RootRoleSerice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

/**
 * @author rcx
 * @date   2020年4月9日  下午12:25:29
 * @class  org.lhp.controller.RootRoleController
 * 
 * 
 */
@Controller
public class RootRoleController {
	
	@Resource(name="rootRoleSerice")
	RootRoleSerice service;
	
	@RequestMapping("AddRole.do")
	public void lpgin(HttpServletResponse response,HttpServletRequest request) throws IOException{
			

			RoleInfo role=new RoleInfo();
			role.setFn1(request.getParameter("fn1"));
			role.setFn2(request.getParameter("fn2"));
			role.setFn3(request.getParameter("fn3"));
			role.setFn4(request.getParameter("fn4"));
			role.setFn5(request.getParameter("fn5"));
			role.setFn6(request.getParameter("fn6"));
			
			role.setRoleinfo(request.getParameter("roleinfo"));
			role.setRolename(request.getParameter("rolename"));
			
			String flag = service.AddRole(role);
			
			Gson gson=new Gson();
			String json = gson.toJson(flag);
			
			PrintWriter out = response.getWriter();
			out.write(json);
			
	}
	@RequestMapping("QueryAllRole.do")
	public void lpgin2(HttpServletResponse response,HttpServletRequest request) throws IOException{
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
	
			List<RoleInfo> list = service.QueryAllRole();
			
			Gson gson=new Gson();
			String json = gson.toJson(list);
			
			PrintWriter out = response.getWriter();
			out.write(json);
	}
	@RequestMapping("ByIdQueryOneRole")
	public void lpgin3(HttpServletResponse response,HttpServletRequest request) throws IOException{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			Integer rid = Integer.parseInt(request.getParameter("rid"));
			RoleInfo byIdQueryOneRole = service.ByIdQueryOneRole(rid);
			
			Gson gson=new Gson();
			String json = gson.toJson(byIdQueryOneRole);
			
			PrintWriter out = response.getWriter();
			out.write(json);
		
	}
	@RequestMapping("RoleModify.do")
	public void lpgin4(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		RoleInfo role=new RoleInfo();
		
		role.setRoleid(Integer.parseInt(request.getParameter("rid")));
		
		role.setFn1(request.getParameter("fn1"));
		role.setFn2(request.getParameter("fn2"));
		role.setFn3(request.getParameter("fn3"));
		role.setFn4(request.getParameter("fn4"));
		role.setFn5(request.getParameter("fn5"));
		role.setFn6(request.getParameter("fn6"));
		
		role.setRoleinfo(request.getParameter("roleinfo"));
		role.setRolename(request.getParameter("rolename"));
		
		String flag = service.RoleModify(role);
		
		
		
		Gson gson=new Gson();
		String json = gson.toJson(flag);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	
	@RequestMapping("queryAllTeachRole.do")
	public void qntl(HttpServletResponse response,HttpServletRequest request) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		List<TeacherUserRt> list = service.queryAllTeach();
		List<RoleInfo> queryAllRole = service.QueryAllRole();
		
		Map<String,Object> map=new HashMap<>();
		map.put("list", list);
		map.put("queryAllRole", queryAllRole);
		
		Gson gson=new Gson();
		String json = gson.toJson(map);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	
	
	@RequestMapping("ModifyTeacherRole.do")
	public void mtr(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		Integer roleid = Integer.parseInt(request.getParameter("roleid"));
		Integer tid = Integer.parseInt(request.getParameter("tid"));
		
		Map<String,Object> map=new HashMap<>();
		map.put("roleid", roleid);
		map.put("tid", tid);
		
		String flag = service.ModifyTeacherRole(map);
		
		Notice notice=new Notice();
		notice.setCheck(1);
		notice.setTid(tid);
		notice.setNoticeinfo("您的角色已经被修改！！");
		notice.setNoticetype("信息-");
		service.addModifyTeacherRoleNotice(notice);
		
		notice.setCheck(1);
		notice.setTid(1);
		notice.setNoticeinfo("ID为"+tid+"的用户角色被修改！！");
		notice.setNoticetype("账号修改");
		service.AddRootNotice(notice);
		
		Gson gson=new Gson();
		String json = gson.toJson(flag);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	@RequestMapping("TeacherAuditAndModify.do")
	public void taam(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		Integer roleid = Integer.parseInt(request.getParameter("roleid"));
		Integer tid = Integer.parseInt(request.getParameter("tid"));
		
		Map<String,Object> map=new HashMap<>();
		map.put("roleid", roleid);
		map.put("tid", tid);
		
		String flag = service.ModifyTeacherRole(map);
		
		Notice notice=new Notice();
		notice.setCheck(1);
		notice.setTid(tid);
		notice.setNoticeinfo("您的信息已经通过审核");
		notice.setNoticetype("信息-");
		service.addModifyTeacherRoleNotice(notice);
		
		notice.setCheck(1);
		notice.setTid(1);
		notice.setNoticeinfo("ID为"+tid+"的用户通过了审核,其角色ID为"+roleid+"！！");
		notice.setNoticetype("账号修改");
		service.AddRootNotice(notice);
		
		Gson gson=new Gson();
		String json = gson.toJson(flag);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
}
