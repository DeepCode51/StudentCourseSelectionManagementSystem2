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
import org.lhp.service.RootAuditService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

/**
 * @author rcx
 * @date   2020年4月9日  下午8:43:15
 * @class  org.lhp.controller.RootAuditController
 * 
 * 
 */
@Controller
public class RootAuditController {
	@Resource(name="rootAuditService")
	RootAuditService service;
	
	@RequestMapping("queryNoAuditTeacherList.do")
	public void lpgin(HttpServletResponse response,HttpServletRequest request) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		List<TeacherUserRt> list = service.queryNoAuditTeacherList();
	
		Gson gson=new Gson();
		String json = gson.toJson(list);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	@RequestMapping("queryNoAuditTeacher.do")
	public void lpgin2(HttpServletResponse response,HttpServletRequest request) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		Integer tid = Integer.parseInt(request.getParameter("tid"));
		
		TeacherUserRt userRt = service.queryNoAuditTeacher(tid);
		
		Gson gson=new Gson();
		String json = gson.toJson(userRt);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	
	@RequestMapping("addNoAutitTeachNotice.do")
	public void atn(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		Notice notice=new Notice();
		notice.setCheck(1);
		notice.setTid(Integer.parseInt(request.getParameter("tid")));
		notice.setNoticeinfo(request.getParameter("yuanyin"));
		notice.setNoticetype("信息错误");
		
		String flag = service.addNoAutitTeachNotice(notice);
		
		Gson gson=new Gson();
		String json = gson.toJson(flag);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
}
