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

import org.lhp.bean.CourseRt;
import org.lhp.bean.Notice;
import org.lhp.bean.RoleInfo;
import org.lhp.bean.Student_Course;
import org.lhp.service.RootCourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

/**
 * @author rcx
 * @date   2020年4月9日  下午6:05:54
 * @class  org.lhp.controller.RootCourseController
 * 
 * 
 */
@Controller
public class RootCourseController {
	
	@Resource(name="rootCourseService")
	RootCourseService service;
	
	@RequestMapping("queryNoAuditCourse.do")
	public void lpgin(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		List<CourseRt> list = service.queryNoAuditCourse();
		
		Gson gson=new Gson();
		String json = gson.toJson(list);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	@RequestMapping("onTermNoAuditCourse.do")
	public void otac(HttpServletResponse response,HttpServletRequest request) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String cname = request.getParameter("cname");
		String tname = request.getParameter("tname");
		String ctype = request.getParameter("ctype");
		Map<String, String> map = new HashMap<>();
		map.put("cname", cname);
		map.put("tname", tname);
		map.put("ctype", ctype);
		
		List list = service.onTermNoAuditCourse(map);
		
		Gson gson=new Gson();
		String json = gson.toJson(list);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	@RequestMapping("CourseAduitReturnYES.do")
	public void otac2(HttpServletResponse response,HttpServletRequest request) throws IOException{
		int cid = Integer.parseInt(request.getParameter("cid"));
		int tid = Integer.parseInt(request.getParameter("tid"));
		String cname = request.getParameter("cname");
		
		String flag = service.CourseAduitReturnYES(cid);

		Notice notice=new Notice();
		notice.setCheck(1);
		notice.setTid(tid);
		notice.setNoticeinfo("恭喜你，您申请的 "+cname+"已通过申请！！");
		notice.setNoticetype("申请结果 ");
		service.addModifyTeacherRoleNotice(notice);
		
		
		notice.setCheck(1);
		notice.setTid(1);
		notice.setNoticeinfo("课程  "+cname+"已经通过审核！！");
		notice.setNoticetype("课程审核  ");
		service.AddRootNotice(notice);
		
		Gson gson=new Gson();
		String json = gson.toJson(flag);
		
		PrintWriter out = response.getWriter();
		out.write(json);
		
	}
	@RequestMapping("CourseAduitReturnNO.do")
	public void otac3(HttpServletResponse response,HttpServletRequest request) throws IOException{
		int cid = Integer.parseInt(request.getParameter("cid"));
		int tid = Integer.parseInt(request.getParameter("tid"));
		String cname = request.getParameter("cname");
		
		String flag = service.CourseAduitReturnNO(cid);

		Notice notice=new Notice();
		notice.setCheck(1);
		notice.setTid(tid);
		notice.setNoticeinfo("您申请的  "+cname+"未通过申请，请重新申请！！");
		notice.setNoticetype("申请结果  ");
		service.addModifyTeacherRoleNotice(notice);
		
		notice.setCheck(1);
		notice.setTid(1);
		notice.setNoticeinfo("课程  "+cname+"没有通过审核！！");
		notice.setNoticetype("课程审核   ");
		service.AddRootNotice(notice);
		
		Gson gson=new Gson();
		String json = gson.toJson(flag);
		
		PrintWriter out = response.getWriter();
		out.write(json);
		
	}
}
