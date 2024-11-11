/**
 * 
 */
package org.lhp.service;

import java.util.List;
import java.util.Map;

import org.lhp.bean.CourseRt;
import org.lhp.bean.Notice;
import org.lhp.bean.RoleInfo;
import org.lhp.bean.Student_Course;
import org.springframework.stereotype.Service;

/**
 * @author rcx
 * @date   2020年4月9日  下午6:07:29
 * @class  org.lhp.service.RootCourseSerice
 * 
 * 
 */
@Service("rootCourseService")
public class RootCourseService extends BaseService{

	/**
	 * @return
	 * RootCourseService.java
	 * 
	 */
	public List<CourseRt> queryNoAuditCourse() {
		return (List<CourseRt>) dao.selectList("lhpmapper.rootCourseMapper.queryNoAuditCourse");
	}

	/**
	 * @param map
	 * @return
	 * RootCourseService.java
	 * 
	 */
	public List<CourseRt> onTermNoAuditCourse(Map<String, String> map) {
		return (List<CourseRt>) dao.selectList("lhpmapper.rootCourseMapper.onTermNoAuditCourse",map);
	}

	/**
	 * @param cid
	 * @return
	 * RootCourseService.java
	 * 
	 */
	public String CourseAduitReturnNO(int cid) {
		dao.update("lhpmapper.rootCourseMapper.CourseAduitReturnNO", cid);
		return "true";
	}

	/**
	 * @param notice
	 * RootCourseService.java
	 * 
	 */
	public void addModifyTeacherRoleNotice(Notice notice) {
		dao.insert("lhpmapper.NoticeMapper.addNoAutitTeachNotice", notice);
	}

	/**
	 * @param cid
	 * @return
	 * RootCourseService.java
	 * 
	 */
	public String CourseAduitReturnYES(int cid) {
		dao.update("lhpmapper.rootCourseMapper.CourseAduitReturnYES", cid);
		return "true";
	}

	/**
	 * @param notice
	 * RootCourseService.java
	 * 
	 */
	public void AddRootNotice(Notice notice) {
		dao.insert("lhpmapper.NoticeMapper.addRoot_Notice", notice);
		
	}
	
}
