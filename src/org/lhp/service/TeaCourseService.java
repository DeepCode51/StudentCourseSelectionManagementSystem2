/**
 * 
 */
package org.lhp.service;

import java.util.List;

import org.lhp.bean.Course;
import org.lhp.bean.Notice;
import org.lhp.bean.StudentUserRt;
import org.lhp.bean.Student_Course;
import org.springframework.stereotype.Service;

/**
 * @author rcx
 * @date   2020年4月9日  下午2:26:16
 * @class  org.lhp.service.CourseService
 * 
 * 
 */
@Service("courseService")
public class TeaCourseService extends BaseService{
	public int addCourse(Course course){
		int insert = dao.insert("lhpmapper.CourseMapperEXT.addCourse", course);
		System.out.println(insert);
		return insert;
	}

	/**
	 * @param teaid
	 * CourseService.java
	 * 
	 */
	public List queryTeaCorse(Integer teaid) {
		
		List<?> list = dao.selectList("lhpmapper.CourseMapperEXT.queryTeaCourse", teaid);
		
		return list;
	}


	/**
	 * @param integer
	 * CourseService.java
	 * 
	 */
	public Course queryTeaCorseOne(Integer cid) {
		return (Course) dao.selectOne("lhpmapper.CourseMapperEXT.queryTeaCourseOne", cid);
	}
	
	public String queryTeaname(Integer tuid){
		return (String) dao.selectOne("lhpmapper.CourseMapperEXT.queryTeaname", tuid);
	}

	/**
	 * @param cid
	 * @return
	 * TeaCourseService.java
	 * 
	 */
	public List<StudentUserRt> queryMystudent(int cid) {
		return (List<StudentUserRt>) dao.selectList("lhpmapper.TeaCourseMapperEXT.queryMytudent",cid);
	}

	/**
	 * @param sc
	 * TeaCourseService.java
	 * 
	 */
	public void insertStudentScore(Student_Course sc) {
		dao.update("lhpmapper.TeaCourseMapperEXT.insertStudentScore", sc);
	}

	/**
	 * @param teaid
	 * @return
	 * TeaCourseService.java
	 * 
	 */
	public List queryTeaCourseUpScore(Integer teaid) {
		List<?> list = dao.selectList("lhpmapper.CourseMapperEXT.queryTeaCourseUpScore", teaid);
		return list;
	}

	/**
	 * @param id
	 * TeaCourseService.java
	 * 
	 */
	public void ModifyScoreFlag(int id) {
		dao.update("lhpmapper.CourseMapperEXT.ModifyScoreFlag", id);
	}

	/**
	 * @param notice
	 * TeaCourseService.java
	 * 
	 */
	public void addStudent_AddScoreNotice(Notice notice) {
		dao.insert("lhpmapper.NoticeMapper.addStudent_AddCourseNotice", notice);
	}

	/**
	 * @param notice
	 * TeaCourseService.java
	 * 
	 */
	public void AddRootNotice(Notice notice) {
		dao.insert("lhpmapper.NoticeMapper.addRoot_Notice", notice);
	}
}
