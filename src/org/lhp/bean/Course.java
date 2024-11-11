/**
 * 
 */
package org.lhp.bean;

/**
 * @author rcx
 * @date   2020年4月9日  下午2:07:44
 * @class  org.lhp.bean.Course
 * 
 * 
 */
public class Course {
	private int id;
	private String  course_name;
	private String  course_type;
	private String  course_info;
	private int  teachid;
	private int  course_flag;
	private int course_stu_size;
	private int course_stu_max;
	private String score_flag;
	public int getCourse_stu_size() {
		return course_stu_size;
	}
	public void setCourse_stu_size(int course_stu_size) {
		this.course_stu_size = course_stu_size;
	}
	public void setCourse_flag(int course_flag) {
		this.course_flag = course_flag;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getCourse_type() {
		return course_type;
	}
	public void setCourse_type(String course_type) {
		this.course_type = course_type;
	}
	public String getCourse_info() {
		return course_info;
	}
	public void setCourse_info(String course_info) {
		this.course_info = course_info;
	}
	public int getTeachid() {
		return teachid;
	}
	public void setTeachid(int teachid) {
		this.teachid = teachid;
	}
	public int getCourse_flag() {
		return course_flag;
	}
	public int getCourse_stu_max() {
		return course_stu_max;
	}
	public void setCourse_stu_max(int course_stu_max) {
		this.course_stu_max = course_stu_max;
	}
	public String getScore_flag() {
		return score_flag;
	}
	public void setScore_flag(String score_flag) {
		this.score_flag = score_flag;
	}
	
}
