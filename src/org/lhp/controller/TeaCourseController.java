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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lhp.bean.Course;
import org.lhp.bean.CourseRt;
import org.lhp.bean.Notice;
import org.lhp.bean.StudentUserRt;
import org.lhp.bean.Student_Course;
import org.lhp.service.TeaCourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author rcx
 * @date   2020年4月9日  下午2:29:14
 * @class  org.lhp.controller.CourseController
 * 
 * 
 */
@Controller
public class TeaCourseController {

	@Resource(name="courseService")
	TeaCourseService service=new TeaCourseService();
	
	@RequestMapping("addCourse.do")
	public void lpgin(HttpServletResponse response,HttpServletRequest request) throws IOException, ServletException{
		
		HttpSession session = request.getSession();
		Integer Teaid=(Integer) session.getAttribute("TeaUserId");
		if(Teaid!=null){
		Course course=new Course();
		course.setCourse_name(request.getParameter("course_name"));
		course.setCourse_type(request.getParameter("course_type"));
		course.setCourse_info(request.getParameter("course_info"));
		course.setCourse_flag(0);
		course.setTeachid(Teaid);
		course.setCourse_stu_size(0);
		service.addCourse(course);
		
		String teaname = service.queryTeaname(Teaid);
		Notice notice=new Notice();
		notice.setCheck(1);
		notice.setTid(2);
		notice.setNoticeinfo("用户"+teaname+"已经申请了"+course.getCourse_name()+"课程，请审核！！");
		notice.setNoticetype("课程申请");
		service.AddRootNotice(notice);
		
		
		Gson gson=new Gson();
		String json = gson.toJson("true");
		
		PrintWriter out = response.getWriter();
		out.write(json);
		}
	}
	@RequestMapping("queryTeaCourse.do")
	public void qtc(HttpServletResponse response,HttpServletRequest request) throws IOException, ServletException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		HttpSession session = request.getSession();
		Integer Teaid=(Integer) session.getAttribute("TeaUserId");
		if(Teaid!=null){
			List list = service.queryTeaCorse(Teaid);
			System.out.println(list);
			Gson gson=new Gson();
			String json = gson.toJson(list);
			
			PrintWriter out = response.getWriter();
			out.write(json);
		}
		
	}
	@RequestMapping("queryTeaCourseUpScore.do")
	public void qtcus(HttpServletResponse response,HttpServletRequest request) throws IOException, ServletException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		HttpSession session = request.getSession();
		Integer Teaid=(Integer) session.getAttribute("TeaUserId");
		if(Teaid!=null){
			List list = service.queryTeaCourseUpScore(Teaid);
			System.out.println(list);
			Gson gson=new Gson();
			String json = gson.toJson(list);
			
			PrintWriter out = response.getWriter();
			out.write(json);
		}
		
	}
	
	@RequestMapping(name="queryCourseOne.do")
	public void qtci(HttpServletResponse response,HttpServletRequest request) throws IOException, ServletException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String cid = request.getParameter("cid");
		
		Course one = service.queryTeaCorseOne(Integer.parseInt(cid));
		
		int tuid = one.getTeachid();
		String teaname = service.queryTeaname(tuid);
		
		Map map=new HashMap<>();
		map.put("teaname", teaname);
		map.put("course", one);
		
		
		Gson gson=new Gson();
		String json = gson.toJson(map);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	@RequestMapping("queryMyStudent.do")
	public void qtci3(HttpServletResponse response,HttpServletRequest request) throws IOException, ServletException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int cid = Integer.parseInt(request.getParameter("cid"));
		List<StudentUserRt> list = service.queryMystudent(cid);
		
		Gson gson=new Gson();
		String json = gson.toJson(list);
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
	@RequestMapping("insertStudentScore.do")
	public void qtci4(HttpServletResponse response,HttpServletRequest request) throws IOException, ServletException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String string = request.getParameter("sz");
		
		Student_Course sc=new Student_Course();
		StudentUserRt su=new StudentUserRt();
		CourseRt cr=new CourseRt();
		
		JsonObject root=new JsonParser().parse(string).getAsJsonObject();
		JsonArray ja = root.getAsJsonArray("sz");
		for(int i=0;i<ja.size();i++){
			sc.setScore(Integer.parseInt(ja.get(i).getAsJsonObject().get("score").toString().replace("\"", "")));
			cr.setId(Integer.parseInt(ja.get(i).getAsJsonObject().get("cid").toString().replace("\"", "")));
			sc.setCourseRt(cr);
			su.setId(Integer.parseInt(ja.get(i).getAsJsonObject().get("sid").toString().replace("\"", "")));
			sc.setUserRt(su);
			
			Notice notice=new Notice();
			notice.setCheck(1);
			notice.setTid(su.getId());
			notice.setNoticeinfo("你学习的"+ja.get(i).getAsJsonObject().get("cname").toString().replace("\"", "")+"成绩已经出来,您的成绩为"+sc.getScore());
			notice.setNoticetype("日志");
			service.addStudent_AddScoreNotice(notice);
			
			service.insertStudentScore(sc);
		}
		
		service.ModifyScoreFlag(cr.getId());
		
		Gson gson=new Gson();
		String json = gson.toJson("true");
		
		PrintWriter out = response.getWriter();
		out.write(json);
	}
}
