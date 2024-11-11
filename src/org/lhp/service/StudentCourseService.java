/**
 * 
 */
package org.lhp.service;

import java.util.List;
import java.util.Map;

import org.lhp.bean.CourseRt;
import org.lhp.bean.Notice;
import org.lhp.bean.Student_Course;
import org.springframework.stereotype.Service;

/**
 * @author rcx
 * @date   2020年4月9日  下午8:59:31
 * @class  org.lhp.service.StudentCourseController
 * 
 * 
 */
@Service("studentCourseService")
public class StudentCourseService extends BaseService{
	
	public List<CourseRt> queryAllCourse(int tid){
		return (List<CourseRt>) dao.selectList("lhpmapper.CourseMapperEXT.stu_queryAllCourse",tid);
	}

	/**
	 * @param map
	 * StudentCourseService.java
	 * 
	 */
	public List onTermCourse(Map<String, Object> map) {
		return dao.selectList("lhpmapper.CourseMapperEXT.stu_onTermCourse",map);
	}
	public String stu_addCourse(Student_Course sc){
		
		Integer one = (Integer) dao.selectOne("lhpmapper.CourseMapperEXT.queryStuAndCourse", sc);
		if(one<1){			
			dao.insert("lhpmapper.CourseMapperEXT.stu_addCourse", sc);
			return "true";
		}else{
			return "false";
		}
		
	}

	/**
	 * @param stuid
	 * @return
	 * StudentCourseService.java
	 * 
	 */
	public List<CourseRt> queryStuMyCourse(Integer stuid) {
		return (List<CourseRt>) dao.selectList("lhpmapper.CourseMapperEXT.queryStuMyCourse",stuid);
	}
	public List<Student_Course> queryStuMyCourseAndScore(Integer stuid) {
		return (List<Student_Course>) dao.selectList("lhpmapper.CourseMapperEXT.queryStuMyCourseAndScore",stuid);
	}

	/**
	 * @param map
	 * @return
	 * StudentCourseService.java
	 * 
	 */
	public String deleteStudCourse(Map<String, Object> map) {
		dao.delete("lhpmapper.CourseMapperEXT.deleteStudCourse", map);
		return "true";
	}

	/**
	 * @param notice
	 * StudentCourseService.java
	 * 
	 */
	public void addStudent_AddCourseNotice(Notice notice) {
		dao.insert("lhpmapper.NoticeMapper.addStudent_AddCourseNotice", notice);
	}
	
}
