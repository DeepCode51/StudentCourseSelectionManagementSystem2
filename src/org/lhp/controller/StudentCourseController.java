/**
 * 
 */
package org.lhp.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lhp.bean.Course;
import org.lhp.bean.CourseRt;
import org.lhp.bean.Notice;
import org.lhp.bean.StudentUserRt;
import org.lhp.bean.Student_Course;
import org.lhp.service.StudentCourseService;
import org.lhp.util.ExportWord;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

/**
 * @author rcx
 * @date   2020年4月9日  下午8:57:58
 * @class  org.lhp.controller.StudentController
 * 
 * 
 */
@Controller
public class StudentCourseController {
	
	@Resource(name="studentCourseService")
	StudentCourseService service;
	
	@RequestMapping("queryAllCourse.do")
	public void lpgin(HttpServletResponse response,HttpServletRequest request) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		Integer stuid = (Integer) session.getAttribute("stuUserId");
		
		List<CourseRt> list = service.queryAllCourse(stuid);
		
		Gson gson=new Gson();
		String json = gson.toJson(list);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	@RequestMapping("onTermCourse.do")
	public void lpgin2(HttpServletResponse response,HttpServletRequest request) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		Integer stuid = (Integer) session.getAttribute("stuUserId");
		
		String cname = request.getParameter("cname");
		String tname = request.getParameter("tname");
		String ctype = request.getParameter("ctype");
		Map<String, Object> map = new HashMap<>();
		map.put("cname", cname);
		map.put("tname", tname);
		map.put("ctype", ctype);
		map.put("stuid", stuid);
		
		List list = service.onTermCourse(map);
		
		Gson gson=new Gson();
		String json = gson.toJson(list);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	@RequestMapping("stu_addCourse.do")
	public void sac(HttpServletResponse response,HttpServletRequest request) throws IOException, ServletException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int cid = Integer.parseInt(request.getParameter("cid"));
		String canme = request.getParameter("cname");
		
		HttpSession session = request.getSession();
		Integer stuid = (Integer) session.getAttribute("stuUserId");
		
		Student_Course sc = new Student_Course();
		
		StudentUserRt su = new StudentUserRt();
		su.setId(stuid);
		sc.setUserRt(su);
		CourseRt cr=new CourseRt();
		cr.setId(cid);
		sc.setCourseRt(cr);
		
		String flag = service.stu_addCourse(sc);
		
		if(flag.equals("true")){
			Notice notice=new Notice();
			notice.setCheck(1);
			notice.setTid(stuid);
			notice.setNoticeinfo("你已申请-"+canme+"课程的学习");
			notice.setNoticetype("日志");
			service.addStudent_AddCourseNotice(notice);
		}
		
		Gson gson=new Gson();
		String json = gson.toJson(flag);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	@RequestMapping("queryStuMyCourse.do")
	public void qsmc(HttpServletResponse response,HttpServletRequest request) throws IOException, ServletException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		Integer stuid = (Integer) session.getAttribute("stuUserId");
		
		List<CourseRt> list = service.queryStuMyCourse(stuid);
		
		Gson gson=new Gson();
		String json = gson.toJson(list);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	
	}
	@RequestMapping("queryStuMyCourseAndScore.do")
	public void qsmcas(HttpServletResponse response,HttpServletRequest request) throws IOException, ServletException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		Integer stuid = (Integer) session.getAttribute("stuUserId");
		
		List<Student_Course> list = service.queryStuMyCourseAndScore(stuid);
		
		Map<String, Object> map=new HashMap<>();
		List<Map<String, Object>> listMap=new ArrayList<>();
		for (Student_Course courseRt : list) {
			Map<String, Object> Sonmap=new HashMap<>();
			Sonmap.put("score", courseRt.getScore());
			Sonmap.put("id", courseRt.getCourseRt().getId());
			Sonmap.put("name", courseRt.getCourseRt().getCourse_name());
			Sonmap.put("info", courseRt.getCourseRt().getCourse_info());
			Sonmap.put("teacher", courseRt.getCourseRt().getTeachuid().getName());
			listMap.add(Sonmap);
		}
		map.put("tr1", listMap);
		String savePath = request.getServletContext().getRealPath("/WEB-INF/File");
		File file = new File(savePath);
		//判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
		System.out.println(savePath+"目录不存在，需要创建");
		//创建目录
  		file.mkdir();
		}
		
		ExportWord eWord=new ExportWord();
		eWord.createDoc(map,savePath+"/score.doc");

		
		
		Gson gson=new Gson();
		String json = gson.toJson(list);
		
		PrintWriter out = response.getWriter();
		out.write(json);
		
	}
	
	@RequestMapping("saveStudentScor.do")
	public void lpgin6(HttpServletResponse response,HttpServletRequest request) throws IOException{
		HttpSession session = request.getSession();
		Integer stuid = (Integer) session.getAttribute("stuUserId");
		
		List<Student_Course> list = service.queryStuMyCourseAndScore(stuid);
		
		Map<String, Object> map=new HashMap<>();
		List<Map<String, Object>> listMap=new ArrayList<>();
		for (Student_Course courseRt : list) {
			Map<String, Object> Sonmap=new HashMap<>();
			Sonmap.put("score", courseRt.getScore());
			Sonmap.put("id", courseRt.getCourseRt().getId());
			Sonmap.put("name", courseRt.getCourseRt().getCourse_name());
			Sonmap.put("info", courseRt.getCourseRt().getCourse_info());
			Sonmap.put("teacher", courseRt.getCourseRt().getTeachuid().getName());
			listMap.add(Sonmap);
		}
		map.put("tr1", listMap);
		String savePath = request.getServletContext().getRealPath("/WEB-INF/File");
		File file = new File(savePath);
		//判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
		System.out.println(savePath+"目录不存在，需要创建");
		//创建目录
  		file.mkdir();
		}
		
		ExportWord eWord=new ExportWord();
		eWord.createDoc(map,savePath+"/score.doc");

		Gson gson=new Gson();
		String json = gson.toJson("true");
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	
	@RequestMapping("deleteStudCourse.do")
	public void dsc(HttpServletResponse response,HttpServletRequest request) throws IOException{
		int cid = Integer.parseInt(request.getParameter("cid"));
		String cname = request.getParameter("cname");
		HttpSession session = request.getSession();
		Integer stuid = (Integer) session.getAttribute("stuUserId");
		Map<String, Object> map=new HashMap<>();
		map.put("cid", cid);
		map.put("stuid",stuid );
		
		String flag = service.deleteStudCourse(map);
		
		if(flag.equals("true")){
			Notice notice=new Notice();
			notice.setCheck(1);
			notice.setTid(stuid);
			notice.setNoticeinfo("你已退出"+cname+"课程的学习");
			notice.setNoticetype("日志");
			service.addStudent_AddCourseNotice(notice);
		}
		
		Gson gson=new Gson();
		String json = gson.toJson(flag);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}	
}
